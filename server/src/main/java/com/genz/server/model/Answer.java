package com.genz.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Nikos.Toulios
 */
@Entity
@Table(name = "answers")
@ApiModel(description = "Answer model")
public class Answer extends AbstractEntry {

    @Column(name = "text")
    @ApiModelProperty(notes = "Answer's text", required = true)
    private String text;

    @ApiModelProperty(notes = "Answer's priority", required = true)
    @Column(name = "priority")
    private Integer priority;

    @ApiModelProperty(notes = "Answer's odds", required = true)
    @Column(name = "odds")
    private double odds;

    @ApiModelProperty(notes = "The associated question", required = true)
    @ManyToOne
    @JoinColumn(name="question_id")
    @JsonIgnore
    private Question question;

    public Answer() {
    }

    public Answer(String text, Integer priority, Integer points, double odds, Question question) {
        this();
        this.text = text;
        this.priority = priority;
        this.odds = odds;
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return Double.compare(answer.odds, odds) == 0 &&
                Objects.equals(text, answer.text) &&
                Objects.equals(priority, answer.priority);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), text, priority, odds);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", priority=" + priority +
                ", odds=" + odds +
                '}';
    }
}
