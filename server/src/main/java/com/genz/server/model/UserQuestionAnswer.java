package com.genz.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nikos.Toulios
 */
@ApiModel(description = "User's choice")
public class UserQuestionAnswer implements Serializable {

    private static final long serialVersionUID = -6497735871470007398L;
    @ApiModelProperty(notes = "User's played question", required = true)
    private Long questionId;

    @ApiModelProperty(notes = "User's played answer", required = true)
    private Long answerId;

    @ApiModelProperty(notes = "Result of user's choice", required = true)
    private boolean isCorrect;

    public UserQuestionAnswer() {
    }

    public UserQuestionAnswer(Long questionId, Long answerId, boolean isCorrect) {
        this();
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrect = isCorrect;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuestionAnswer that = (UserQuestionAnswer) o;
        return isCorrect == that.isCorrect &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionId, answerId, isCorrect);
    }

    @Override
    public String toString() {
        return "UserQuestionAnswer{" +
                "questionId=" + questionId +
                ", answerId=" + answerId +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
