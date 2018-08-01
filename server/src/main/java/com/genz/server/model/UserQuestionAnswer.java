package com.genz.server.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nikos.Toulios
 */
public class UserQuestionAnswer implements Serializable {

    private static final long serialVersionUID = -6497735871470007398L;
    private Integer questionId;
    private Integer answerId;

    public UserQuestionAnswer() {
    }

    public UserQuestionAnswer(Integer questionId, Integer answerId) {
        this();
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuestionAnswer that = (UserQuestionAnswer) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, answerId);
    }

    @Override
    public String toString() {
        return "UserQuestionAnswer{" +
                "questionId=" + questionId +
                ", answerId=" + answerId +
                '}';
    }
}
