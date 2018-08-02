package com.genz.server.service;

import com.genz.server.ServerApplicationTests;
import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.*;
import com.genz.server.repository.GroupRepository;
import com.genz.server.repository.QuestionRepository;
import com.genz.server.repository.UserRepository;
import com.genz.server.service.game.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Nikos.Toulios
 */
public class GameServiceImplTests  extends ServerApplicationTests {

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Before
    @Override
    public void setUp(){
        userRepository.deleteAll();
        questionRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test(expected = ResourceValidationException.class)
    public void play_not_found_group(){
        //given



        //when
        gameService.play(1, 1l, 1l, Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_not_found_user(){
        //given

        Group group = new Group();
        group.setName("group");
        group = groupRepository.save(group);

        //when
        gameService.play(1, group.getId(), 1l, Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_user_does_not_have_groups_1(){
        //given

        Group group = new Group();
        group.setName("group");
        group = groupRepository.save(group);

        User user = createUser();
        user = userRepository.save(user);

        //when
        gameService.play(1, group.getId(), user.getId(), Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_user_does_not_have_groups_2(){
        //given

        Group group = new Group();
        group.setName("group");
        group = groupRepository.save(group);

        User user = createUser();
        user.setGroups(null);
        user = userRepository.save(user);

        //when
        gameService.play(1, group.getId(), user.getId(), Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_user_not_in_the_specified_group(){
        //given

        User user = createUser();

        Group group2 = new Group();
        group2.setName("group2");
        group2.addUser(user);

        group2 = groupRepository.save(group2);

        Group group = new Group();
        group.setName("group");

        group = groupRepository.save(group);

        //when
        gameService.play(1, group.getId(),group2.getUsers().get(0).getId(), Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_group_does_not_have_questions_1(){
        //given

        User user = createUser();

        Group group = new Group();
        group.setName("group");
        group.addUser(user);

        group = groupRepository.save(group);

        //when
        gameService.play(1, group.getId(),group.getUsers().get(0).getId(), Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_group_does_not_have_questions_2(){
        //given

        User user = createUser();

        Group group = new Group();
        group.setName("group");
        group.addUser(user);
        group.setQuestions(new ArrayList<>());
        group = groupRepository.save(group);

        //when
        gameService.play(1, group.getId(),group.getUsers().get(0).getId(), Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_weeknumber_less_than_allowed(){
        //given

        User user = createUser();

        //when
        gameService.play(0, 1L,1L, Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void play_weeknumber_greater_than_allowed(){
        //given

        User user = createUser();

        //when
        gameService.play(60, 1L,1L, Collections.emptyList(), Collections.emptyList());


        //then
    }

    @Test
    public void play_success_one_question_one_answer(){
        //given
        Integer weekNum = 1;

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);

        Question question2 = createDefaultQuestion();
        question2.setText("question2");
        Answer answer2 = createDefaultAnswer();
        answer2.setText("answer2");
        question2.addAnswer(answer2);

        User user = createUser();

        Group group = new Group();
        group.setName("group");
        group.addUser(user);
        group.addQuestion(question);
        group.addQuestion(question2);
        group = groupRepository.save(group);

        question = group.getQuestions().get(0);
        answer = question.getAnswers().get(0);

        List<Long> questionIds = Arrays.asList(question.getId());
        List<Long> answerIds = Arrays.asList(answer.getId());

        //when
        user = gameService.play(weekNum, group.getId(),group.getUsers().get(0).getId(), questionIds, answerIds);

        //then
        assertNotNull(user);
        assertEquals(UserStatus.STARTED, user.getUserStatus());
        assertNotNull(user.getUserStatistics());
        assertNotNull(user.getUserStatistics().getUserStatisticsEntry());
        assertFalse(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().isEmpty());
        assertTrue(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().containsKey(weekNum));

        WeeklyStatistics userWeeklyQuestionAnswers = user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().get(weekNum);
        assertNotNull(userWeeklyQuestionAnswers);
        assertTrue(!userWeeklyQuestionAnswers.getUserQuestionAnswers().isEmpty());
        assertEquals(1, userWeeklyQuestionAnswers.getUserQuestionAnswers().size());
        assertEquals(answer.getId(), userWeeklyQuestionAnswers.getUserQuestionAnswers().get(0).getAnswerId());
    }

    @Test
    public void play_success_all_the_questions_in_one_time(){
        //given
        Integer weekNum = 1;

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);

        Question question2 = createDefaultQuestion();
        question.setPriority(2);
        question2.setText("question2");
        Answer answer2 = createDefaultAnswer();
        answer2.setText("answer2");
        question2.addAnswer(answer2);

        User user = createUser();

        Group group = new Group();
        group.setName("group");
        group.addUser(user);
        group.addQuestion(question);
        group.addQuestion(question2);
        group = groupRepository.save(group);

        question = group.getQuestions().get(0);
        question2 = group.getQuestions().get(1);

        List<Long> questionIds = Arrays.asList(question.getId(), question2.getId());
        List<Long> answerIds = Arrays.asList(question.getAnswers().get(0).getId(),
                question2.getAnswers().get(0).getId());

        //when
        user = gameService.play(weekNum, group.getId(),group.getUsers().get(0).getId(), questionIds, answerIds);

        //then
        assertNotNull(user);
        assertEquals(UserStatus.FINISHED, user.getUserStatus());
        assertNotNull(user.getUserStatistics());
        assertNotNull(user.getUserStatistics().getUserStatisticsEntry());
        assertFalse(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().isEmpty());
        assertTrue(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().containsKey(weekNum));

        WeeklyStatistics userWeeklyQuestionAnswers = user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().get(weekNum);
        assertNotNull(userWeeklyQuestionAnswers);
        assertTrue(!userWeeklyQuestionAnswers.getUserQuestionAnswers().isEmpty());
        assertEquals(2, userWeeklyQuestionAnswers.getUserQuestionAnswers().size());
    }

    @Test
    public void play_success_one_question_two_times_with_different_answer(){
        //given
        Integer weekNum = 1;

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);

        Question question2 = createDefaultQuestion();
        question2.setText("question2");
        Answer answer2 = createDefaultAnswer();
        answer2.setText("answer2");
        question.addAnswer(answer2);

        User user = createUser();

        Group group = new Group();
        group.setName("group");
        group.addUser(user);
        group.addQuestion(question);
        group.addQuestion(question2);
        group = groupRepository.save(group);

        question = group.getQuestions().get(0);
        answer = question.getAnswers().get(0);

        List<Long> questionIds = Arrays.asList(question.getId());
        List<Long> answerIds = Arrays.asList(answer.getId());

        user = gameService.play(weekNum, group.getId(),group.getUsers().get(0).getId(), questionIds, answerIds);

        assertNotNull(user);
        assertEquals(UserStatus.STARTED, user.getUserStatus());
        assertNotNull(user.getUserStatistics());
        assertNotNull(user.getUserStatistics().getUserStatisticsEntry());
        assertFalse(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().isEmpty());
        assertTrue(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().containsKey(weekNum));

        WeeklyStatistics userWeeklyQuestionAnswers = user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().get(weekNum);
        assertNotNull(userWeeklyQuestionAnswers);
        assertTrue(!userWeeklyQuestionAnswers.getUserQuestionAnswers().isEmpty());
        assertEquals(1, userWeeklyQuestionAnswers.getUserQuestionAnswers().size());
        assertEquals(answer.getId(), userWeeklyQuestionAnswers.getUserQuestionAnswers().get(0).getAnswerId());

        //second time same question different answer
        answer2 = question.getAnswers().get(1);
        answerIds = Arrays.asList(answer2.getId());

        //when
        user = gameService.play(weekNum, group.getId(),group.getUsers().get(0).getId(), questionIds, answerIds);

        //then
        assertNotNull(user);
        assertEquals(UserStatus.STARTED, user.getUserStatus());
        assertNotNull(user.getUserStatistics());
        assertNotNull(user.getUserStatistics().getUserStatisticsEntry());
        assertFalse(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().isEmpty());
        assertTrue(user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().containsKey(weekNum));

        userWeeklyQuestionAnswers = user.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().get(weekNum);
        assertNotNull(userWeeklyQuestionAnswers);
        assertTrue(!userWeeklyQuestionAnswers.getUserQuestionAnswers().isEmpty());
        assertEquals(1, userWeeklyQuestionAnswers.getUserQuestionAnswers().size());
        assertEquals(answer2.getId(), userWeeklyQuestionAnswers.getUserQuestionAnswers().get(0).getAnswerId());
    }

    @Test
    public void calculatePotentialWinnings_empty_odds(){
        //given



        //when
        double potentialWinnings = gameService.calculatePotentialWinnings(new ArrayList<>(), 10.0);


        //then
        assertTrue(potentialWinnings == 0.0);

    }

    @Test
    public void calculatePotentialWinnings_null_odds(){
        //given



        //when
        double potentialWinnings = gameService.calculatePotentialWinnings(null, 10.0);


        //then
        assertTrue(potentialWinnings == 0.0);

    }

    @Test
    public void calculatePotentialWinnings_success(){
        //given

        List<Double> odds = Arrays.asList(1.5, 1.5);

        //when
        double potentialWinnings = gameService.calculatePotentialWinnings(odds, 10.0);


        //then
        assertTrue(potentialWinnings == 22.5);

    }

    @Test(expected = ResourceValidationException.class)
    public void calculateUserPointsPerCorrectAnswer_week_num_less_than_minimum(){
        //given



        //when
        gameService.calculateUserPointsPerCorrectAnswer(Collections.emptyList(), Collections.emptyList(), 0);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void calculateUserPointsPerCorrectAnswer_week_num_greater_than_maximum(){
        //given



        //when
        gameService.calculateUserPointsPerCorrectAnswer(Collections.emptyList(), Collections.emptyList(), 54);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void calculateUserPointsPerCorrectAnswer_not_found_question(){
        //given

        List<Long> questionIds = Arrays.asList(1L);

        //when
        gameService.calculateUserPointsPerCorrectAnswer(Collections.emptyList(), questionIds, 2);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void calculateUserPointsPerCorrectAnswer_question_does_not_have_correct_answer(){
        //given

        Question question = createDefaultQuestion();
        question = questionRepository.save(question);
        List<Long> questionIds = Arrays.asList(question.getId());

        //when
        gameService.calculateUserPointsPerCorrectAnswer(Collections.emptyList(), questionIds, 2);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void calculateUserPointsPerCorrectAnswer_user_not_found(){
        //given

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);
        question = questionRepository.save(question);
        question.setCorrectAswer(question.getAnswers().get(0).getId());
        question = questionRepository.save(question);

        List<Long> questionIds = Arrays.asList(question.getId());
        List<Long> userIds = Arrays.asList(1l);

        //when
        gameService.calculateUserPointsPerCorrectAnswer(userIds, questionIds, 2);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void calculateUserPointsPerCorrectAnswer_user_has_not_finished_his_game(){
        //given

        Question question = createDefaultQuestion();
        question.setPoints(100);
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);
        question = questionRepository.save(question);
        question.setCorrectAswer(question.getAnswers().get(0).getId());
        question = questionRepository.save(question);

        User user = createUser();
        user = userRepository.save(user);

        List<Long> questionIds = Arrays.asList(question.getId());
        List<Long> userIds = Arrays.asList(user.getId());

        //when
        gameService.calculateUserPointsPerCorrectAnswer(userIds, questionIds, 2);


        //then
    }

    @Test
    public void calculateUserPointsPerCorrectAnswer_success_with_only_one_question(){
        //given

        Integer weeknum = 1;
        Question question = createDefaultQuestion();
        question.setPoints(100);
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);
        question = questionRepository.save(question);
        question.setCorrectAswer(question.getAnswers().get(0).getId());
        question = questionRepository.save(question);

        User user = createUser();
        user.setUserStatus(UserStatus.FINISHED);

        UserStatisticsEntry userStatisticsEntry = new UserStatisticsEntry();
        userStatisticsEntry.addNewWeeklyStatistic(1, question.getId(), question.getAnswers().get(0).getId());
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setUserStatisticsEntry(userStatisticsEntry);
        user.setUserStatistics(userStatistics);
        user = userRepository.save(user);

        List<Long> questionIds = Arrays.asList(question.getId());
        List<Long> userIds = Arrays.asList(user.getId());

        //when
        List<User> users = gameService.calculateUserPointsPerCorrectAnswer(userIds, questionIds, 1);

        //then
        assertNotNull(users);
        User resultedUser = users.get(0);
        assertNotNull(resultedUser.getUserStatistics());
        assertEquals(100, resultedUser.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().get(weeknum).getPoints().intValue());
    }

    @Test
    public void calculateUserPointsPerCorrectAnswer_success_with_multiple_questions(){
        //given

        Integer weeknum = 1;

        Question question = createCustomQuestion(1, "question", 100);
        Answer answer = createCustomAnswer(0.1, 1, "answer");
        Answer answer2 = createCustomAnswer(0.2, 2, "answer2");
        Answer answer3 = createCustomAnswer(0.3, 3, "answer3");
        question.addAnswer(answer);
        question.addAnswer(answer2);
        question.addAnswer(answer3);
        question = questionRepository.save(question);
        question.setCorrectAswer(question.getAnswers().get(0).getId());
        question = questionRepository.save(question);

        Question question2 = createCustomQuestion(2, "question2", 200);
        answer = createCustomAnswer(0.1, 1, "answer");
        answer2 = createCustomAnswer(0.2, 2, "answer2");
        answer3 = createCustomAnswer(0.3, 3, "answer3");
        question2.addAnswer(answer);
        question2.addAnswer(answer2);
        question2.addAnswer(answer3);
        question2 = questionRepository.save(question2);
        question2.setCorrectAswer(question2.getAnswers().get(1).getId());
        question2 = questionRepository.save(question2);


        Question question3 = createCustomQuestion(3, "question3", 300);
        answer = createCustomAnswer(0.1, 1, "answer");
        answer2 = createCustomAnswer(0.2, 2, "answer2");
        answer3 = createCustomAnswer(0.3, 3, "answer3");
        question3.addAnswer(answer);
        question3.addAnswer(answer2);
        question3.addAnswer(answer3);
        question3 = questionRepository.save(question3);
        question3.setCorrectAswer(question3.getAnswers().get(2).getId());
        question3 = questionRepository.save(question3);

        User user = createUser();
        user.setUserStatus(UserStatus.FINISHED);

        UserStatisticsEntry userStatisticsEntry = new UserStatisticsEntry();
        userStatisticsEntry.addNewWeeklyStatistic(1, question.getId(), question.getAnswers().get(0).getId());
        userStatisticsEntry.addNewWeeklyStatistic(1, question2.getId(), question2.getAnswers().get(0).getId());
        userStatisticsEntry.addNewWeeklyStatistic(1, question3.getId(), question3.getAnswers().get(2).getId());

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setUserStatisticsEntry(userStatisticsEntry);
        user.setUserStatistics(userStatistics);
        user = userRepository.save(user);

        List<Long> questionIds = Arrays.asList(question.getId(),question2.getId(),question3.getId());
        List<Long> userIds = Arrays.asList(user.getId());

        //when
        List<User> users = gameService.calculateUserPointsPerCorrectAnswer(userIds, questionIds, 1);

        //then
        assertNotNull(users);
        User resultedUser = users.get(0);
        assertNotNull(resultedUser.getUserStatistics());
        assertEquals(400, resultedUser.getUserStatistics().getUserStatisticsEntry().getUserQuestionAnswers().get(weeknum).getPoints().intValue());
    }

}
