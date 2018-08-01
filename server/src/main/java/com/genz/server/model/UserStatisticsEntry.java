package com.genz.server.model;

import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserStatisticsEntry implements Serializable {
    private static final long serialVersionUID = -1478129777799352997L;

    /**
     * pair of number of week an object that holds WeekAnswers and points
     */
    private Map<Integer, List<WeeklyStatistics>> userQuestionAnswers;

    public UserStatisticsEntry() {
    }

    public UserStatisticsEntry(Map<Integer, List<WeeklyStatistics>> userQuestionAnswers) {
        this.userQuestionAnswers = userQuestionAnswers;
    }

    public Map<Integer, List<WeeklyStatistics>> getUserQuestionAnswers() {
        return userQuestionAnswers;
    }

    public void setUserQuestionAnswers(Map<Integer, List<WeeklyStatistics>> userQuestionAnswers) {
        this.userQuestionAnswers = userQuestionAnswers;
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
