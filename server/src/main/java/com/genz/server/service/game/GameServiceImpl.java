package com.genz.server.service.game;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.*;
import com.genz.server.repository.AnswerRepository;
import com.genz.server.repository.GroupRepository;
import com.genz.server.repository.QuestionRepository;
import com.genz.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikos.Toulios
 */
@Service
@Transactional
public class GameServiceImpl implements GameService{

    private static final Integer MAX_WEEK_NUMBER = 53;
    private static final Integer MIN_WEEK_NUMBER = 1;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public User play(Integer weekNum, Long groupId, Long userId, List<Long> questionIds, List<Long> answerIds){
        if((weekNum > MAX_WEEK_NUMBER) || (weekNum < MIN_WEEK_NUMBER)){
            throw new ResourceValidationException("Week number should be between [ " + MIN_WEEK_NUMBER + "," + MAX_WEEK_NUMBER + " ]");
        }

        User user = userRepository.findOne(userId);
        Group group = groupRepository.findOne(groupId);

        try {
            validateUserAndGroup(user, group);
            for(int i = 0; i< questionIds.size() ; i++){
                Question question = questionRepository.findOne(questionIds.get(i));
                Answer answer = answerRepository.findOne(answerIds.get(i));
                validateQuestionAndAnswer(group, question, answer);
                user = executePlay(weekNum, user, group, question, answer);
            }
            return user;

        } catch(ResourceValidationException validationException){
            throw validationException;
        }
    }

    @Override
    public double calculatePotentialWinnings(List<Double> odds, Double money){
        double potentialWinnings = 0.0;
        if((odds == null) || (odds.isEmpty())) {
            return potentialWinnings;
        }
        Double oddsValue = odds.stream().reduce(1.0, (a, b) -> a * b);
        return oddsValue * money;
    }

    @Override
    public List<User> calculateUserPointsPerCorrectAnswer(List<Long> userIds, List<Long> questionIds, Integer weekNum){
        if((weekNum > MAX_WEEK_NUMBER) || (weekNum < MIN_WEEK_NUMBER)){
            throw new ResourceValidationException("Week number should be between [ " + MIN_WEEK_NUMBER + "," + MAX_WEEK_NUMBER + " ]");
        }

        List<Question> questionAnswers = getQuestionsWithCorrectAnswers(questionIds);
        List<User> users = getUsersWithPointsAndValidatedQuestions(userIds, weekNum, questionAnswers);
        users.forEach(user -> {
            user = userRepository.save(user);
        });

        return users;
    }

    private List<Question> getQuestionsWithCorrectAnswers(List<Long> questionIds) {
        List<Question> questions = new ArrayList<>();
        for (Long questionId : questionIds) {
            Question question = questionRepository.findOne(questionId);
            if(question == null){
                throw new ResourceNotFoundException("Question with ID: " + questionId + " NOT FOUND");
            }

            if((question.getCorrectAswer() == null) || (question.getCorrectAswer() <= 0)){
                throw new ResourceValidationException("Question with ID: " + questionId + " does not have correct answer");
            }

            questions.add(question);
        }
        return questions;
    }

    private List<User> getUsersWithPointsAndValidatedQuestions(List<Long> userIds, Integer weekNum, List<Question> questionAnswers) {
        List<User> users = new ArrayList<>();
        for(Long userId : userIds) {
            User user = userRepository.findOne(userId);
            if (user == null) {
                throw new ResourceNotFoundException("Not found user with ID: "+ userId);
            }

            if(!UserStatus.FINISHED.equals(user.getUserStatus())){
                throw new ResourceValidationException("User has not finished the game");
            }

            user = executeCalculationUserPointsAndCorrectAnswers(questionAnswers, weekNum, user);
            users.add(user);
        }
        return users;
    }

    private User executeCalculationUserPointsAndCorrectAnswers(List<Question> questionAnswers, Integer weekNum, User user) {

        if(user.getUserStatistics() != null){
            if(user.getUserStatistics().getUserStatisticsEntry() != null) {
                if(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers() != null){
                    if (user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().containsKey(weekNum)) {

                        WeeklyStatistics userWeeklyStatistics = user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().get(weekNum);
                        List<UserQuestionAnswer> userQuestionAnswers = userWeeklyStatistics.getUserQuestionAnswers();

                        Integer userPoints = 0;
                        if((userQuestionAnswers != null) && (questionAnswers.size() == userQuestionAnswers.size())){
                            for(int i=0; i< questionAnswers.size(); i++){

                                Question correctUserQuestionAnswer = questionAnswers.get(i);
                                for(int j = 0; j<userQuestionAnswers.size(); j++){
                                    UserQuestionAnswer userQuestionAnswer = userQuestionAnswers.get(j);
                                    if(correctUserQuestionAnswer.getId() == userQuestionAnswer.getQuestionId()){
                                        if(correctUserQuestionAnswer.getCorrectAswer() ==  userQuestionAnswer.getAnswerId()){
                                            userQuestionAnswer.setCorrect(true);
                                            userPoints += correctUserQuestionAnswer.getPoints();
                                        }
                                    }
                                }
                            }
                            userWeeklyStatistics.setPoints(userPoints);
                        }

                        Integer userScore = user.getScore() + userPoints;
                        user.setScore(userScore);
                    }
                }
            }
        }
        return user;
    }

    private void validateQuestionAndAnswer(Group group, Question question, Answer answer) {
        //TODO make validations for question and answer according to the associated group
    }

    private void validateUserAndGroup(User user, Group group) {

        if(group == null){
            throw new ResourceValidationException("Not found group");
        }

        if(user == null){
            throw new ResourceValidationException("Not found user ");
        }

        List<Group> userGroups = user.getGroups();
        if((userGroups == null) || userGroups.isEmpty()){
            throw new ResourceValidationException("User does not have groups");
        }

        boolean isUserInGroup = userGroups.stream().filter(groupVal -> groupVal.getId() == group.getId()).count() > 0;
        if(!isUserInGroup){
            throw new ResourceValidationException("The user with ID " + user.getId() + " is not in the associated group with Id: " + group.getId());
        }

        List<Question> groupQuestions = group.getQuestions();
        if((groupQuestions == null) || groupQuestions.isEmpty()){
            throw new ResourceValidationException("The group with ID: " + group.getId() + " should have questions");
        }
    }

    private User executePlay(Integer weekNum, User user, Group group, Question question, Answer answer) {
        if(UserStatus.FINISHED.equals(user.getUserStatus())){
            return user;
        }

        if(UserStatus.NOT_STARTED.equals(user.getUserStatus())){
            user.setUserStatus(UserStatus.STARTED);
        }

        if(user.getUserStatistics() == null){
            user.setUserStatistics(new UserStatistics());
        }

        user = calculateUserStatistic(weekNum, user, question, answer);
        user = checkIfTheUserIsFinishedTheGame(weekNum, user, group);
        return userRepository.save(user);
    }

    private User calculateUserStatistic(Integer weekNum, User user, Question question, Answer answer) {

        UserStatisticsEntry userStatisticsEntry = user.getUserStatistics().getUserStatisticsEntry();
        if( userStatisticsEntry == null){
            userStatisticsEntry = new UserStatisticsEntry();
        }

        userStatisticsEntry.addNewWeeklyStatistic(weekNum, question.getId(), answer.getId());

        UserStatistics userStatistics = user.getUserStatistics();
        userStatistics.setUser(user);
        userStatistics.setUserStatisticsEntry(userStatisticsEntry);
        user.setUserStatistics(userStatistics);

        return user;
    }

    private User checkIfTheUserIsFinishedTheGame(Integer weekNum, User user, Group group) {
        Integer userQuestionAnswerSize = user.getUserStatistics()
                .getUserStatisticsEntry()
                .getUserQuestionAnswers()
                .get(weekNum)
                .getUserQuestionAnswers().size();

        if (userQuestionAnswerSize == group.getQuestions().size()){
            user.setUserStatus(UserStatus.FINISHED);
        }

        return user;
    }




}
