package com.genz.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;

/**
 * @author Nikos.Toulios
 */
@ApiModel(description = "All user's choices per week")
public class UserStatisticsEntry implements Serializable {
    private static final long serialVersionUID = -1478129777799352997L;

    @ApiModelProperty(notes = "User's answers and points per week", required = true)
    private Map<Integer, WeeklyStatistics> userQuestionAnswers;

    public UserStatisticsEntry() {
    }

    public UserStatisticsEntry(Map<Integer, WeeklyStatistics> userQuestionAnswers) {
        this.userQuestionAnswers = userQuestionAnswers;
    }

    public Map<Integer, WeeklyStatistics> getUserQuestionAnswers() {
        return userQuestionAnswers;
    }

    public void setUserQuestionAnswers(Map<Integer, WeeklyStatistics> userQuestionAnswers) {
        this.userQuestionAnswers = userQuestionAnswers;
    }

    public void addNewWeeklyStatistic(Integer weekNum, Long questionId, Long answerId){
        boolean isQuestionFound = false;
        if(this.userQuestionAnswers == null){
            this.userQuestionAnswers = new HashMap<>();
        }

        if(this.userQuestionAnswers.containsKey(weekNum)){
            handleAlreadyExistedWeeklyEntry(weekNum, questionId, answerId, isQuestionFound);
        }
        else{
            handleNewWeeklyEntry(weekNum, questionId, answerId);
        }
    }

    private void handleNewWeeklyEntry(Integer weekNum, Long questionId, Long answerId) {
        WeeklyStatistics weeklyStatistics = new WeeklyStatistics();

        List<UserQuestionAnswer> userQuestionAnswers = new ArrayList<>();
        UserQuestionAnswer userQuestionAnswer = new UserQuestionAnswer();
        userQuestionAnswer.setAnswerId(answerId);
        userQuestionAnswer.setCorrect(false);
        userQuestionAnswer.setQuestionId(questionId);
        userQuestionAnswers.add(userQuestionAnswer);

        weeklyStatistics.setUserQuestionAnswers(userQuestionAnswers);
        this.userQuestionAnswers.put(weekNum, weeklyStatistics);
    }

    private void handleAlreadyExistedWeeklyEntry(Integer weekNum, Long questionId, Long answerId, boolean isQuestionFound) {
        WeeklyStatistics weeklyStatistics = userQuestionAnswers.get(weekNum);

        List<UserQuestionAnswer> userQuestionAnswers = weeklyStatistics.getUserQuestionAnswers();
        for(UserQuestionAnswer userQuestionAnswer : userQuestionAnswers){
            if(userQuestionAnswer.getQuestionId() == questionId){
                userQuestionAnswer.setAnswerId(answerId);
                isQuestionFound = true;
                break;
            }
        }

        if(!isQuestionFound){
            UserQuestionAnswer userQuestionAnswer = new UserQuestionAnswer();
            userQuestionAnswer.setAnswerId(answerId);
            userQuestionAnswer.setCorrect(false);
            userQuestionAnswer.setQuestionId(questionId);

            userQuestionAnswers.add(userQuestionAnswer);
        }

        weeklyStatistics.setUserQuestionAnswers(userQuestionAnswers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatisticsEntry that = (UserStatisticsEntry) o;
        return Objects.equals(userQuestionAnswers, that.userQuestionAnswers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userQuestionAnswers);
    }

    @Override
    public String toString() {
        return "UserStatisticsEntry{" +
                "userQuestionAnswers=" + userQuestionAnswers +
                '}';
    }
}
