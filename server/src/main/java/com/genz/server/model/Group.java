package com.genz.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "groups")
@ApiModel(description = "Group model")
public class Group extends AbstractEntry{

    @ApiModelProperty(notes = "Group's name", required = true)
    @Column(name = "name")
    private String name;


    @ApiModelProperty(notes = "Associated questions", required = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="group")
    @OrderBy("priority ASC")
    private List<Question> questions;

    public Group() {
    }

    public Group(List<User> users, String name, List<Question> questions) {
        this();
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        if(!questions.isEmpty()) {
            questions.forEach(question -> question.setGroup(this));
            this.questions = questions;
        }
    }

    public void addQuestion(Question question){
        if(questions == null){
            questions = new ArrayList<>();
        }
        question.setGroup(this);
        questions.add(question);
    }

    public void removeQuestion(Question question){
        if(questions != null){
            if(questions.contains(question)){
                questions.remove(question);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(questions, group.questions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, questions);
    }

    @Override
    public String toString() {
        return "Group{" +
                ", name='" + name + '\'' +
                '}';
    }
}
