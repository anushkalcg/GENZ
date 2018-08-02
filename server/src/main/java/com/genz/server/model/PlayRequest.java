package com.genz.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nikos.Toulios
 */
@ApiModel(description = "Information for play action")
public class PlayRequest implements Serializable {

    private static final long serialVersionUID = -3770161220189243599L;

    @ApiModelProperty(notes = "Week to play", required = true)
    private Integer weekNumber;

    @ApiModelProperty(notes = "User's group identifier", required = true)
    private Long groupId;

    @ApiModelProperty(notes = "User identifier", required = true)
    private Long userId;

    @ApiModelProperty(notes = "User's palyed questions", required = true)
    private List<Long> questionIds;

    @ApiModelProperty(notes = "User's played answers", required = true)
    private List<Long> answerIds;

    public PlayRequest() {
    }

    public PlayRequest(Integer weekNumber, Long groupId, Long userId, List<Long> questionIds, List<Long> answerIds) {
        this.weekNumber = weekNumber;
        this.groupId = groupId;
        this.userId = userId;
        this.questionIds = questionIds;
        this.answerIds = answerIds;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public List<Long> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<Long> answerIds) {
        this.answerIds = answerIds;
    }

    @Override
    public String toString() {
        return "PlayRequest{" +
                "weekNumber=" + weekNumber +
                ", groupId=" + groupId +
                ", userId=" + userId +
                ", questionIds=" + questionIds +
                ", answerIds=" + answerIds +
                '}';
    }
}
