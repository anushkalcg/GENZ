package com.genz.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikos.Toulios
 */
@ApiModel(description = "User's weekly choice")
public class WeeklyStatistics implements Serializable {


    private static final long serialVersionUID = 1938223562217517672L;

    @ApiModelProperty(notes = "User's weekly points", required = true)
    private Integer points;

    @ApiModelProperty(notes = "User's weekly choices", required = true)
    private List<UserQuestionAnswer> userQuestionAnswers;

    public WeeklyStatistics() {
    }

    public WeeklyStatistics(Integer points, List<UserQuestionAnswer> userQuestionAnswers) {
        this();
        this.points = points;
        this.userQuestionAnswers = userQuestionAnswers;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<UserQuestionAnswer> getUserQuestionAnswers() {
        return userQuestionAnswers;
    }

    public void setUserQuestionAnswers(List<UserQuestionAnswer> userQuestionAnswers) {
        this.userQuestionAnswers = userQuestionAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeeklyStatistics that = (WeeklyStatistics) o;
        return Objects.equals(points, that.points) &&
                Objects.equals(userQuestionAnswers, that.userQuestionAnswers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(points, userQuestionAnswers);
    }

    @Override
    public String toString() {
        return "WeeklyStatistics{" +
                "points=" + points +
                ", userQuestionAnswers=" + userQuestionAnswers +
                '}';
    }
}
