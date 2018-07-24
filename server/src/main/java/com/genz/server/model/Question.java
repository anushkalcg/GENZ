package com.genz.server.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question extends AbstractEntry{

    @Column(name = "text")
    private String text;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "correct_answer_id")
    private Integer correctAswer;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @OneToMany(mappedBy="question")
    private Set<Answer> answers;

    public Question() {
    }

    public Question(String text, Integer priority, Integer correctAswer, Group group, Set<Answer> answers) {
        this();
        this.text = text;
        this.priority = priority;
        this.correctAswer = correctAswer;
        this.group = group;
        this.answers = answers;
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

    public Integer getCorrectAswer() {
        return correctAswer;
    }

    public void setCorrectAswer(Integer correctAswer) {
        this.correctAswer = correctAswer;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer){
        if(answers == null){
            answers = new HashSet<>();
        }
        answers.add(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return Objects.equals(text, question.text) &&
                Objects.equals(priority, question.priority) &&
                Objects.equals(correctAswer, question.correctAswer) &&
                Objects.equals(group, question.group) &&
                Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), text, priority, correctAswer, group, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", priority=" + priority +
                ", correctAswer=" + correctAswer +
                ", group=" + group +
                ", answers=" + answers +
                '}';
    }
}
