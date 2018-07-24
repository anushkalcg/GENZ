package com.genz.server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answers")
public class Answer extends AbstractEntry {

    @Column(name = "text")
    private String text;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "points")
    private Integer points;

    @Column(name = "odds")
    private double odds;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    public Answer() {
    }

    public Answer(String text, Integer priority, Integer points, double odds, Question question) {
        this();
        this.text = text;
        this.priority = priority;
        this.points = points;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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
                Objects.equals(priority, answer.priority) &&
                Objects.equals(points, answer.points) &&
                Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), text, priority, points, odds, question);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", priority=" + priority +
                ", points=" + points +
                ", odds=" + odds +
                ", question=" + question +
                '}';
    }
}
