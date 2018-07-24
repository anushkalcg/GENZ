package com.genz.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "groups")
@ApiModel(description = "Group model")
public class Group extends AbstractEntry{

    @ApiModelProperty(notes = "Associated users")
    @ManyToMany(mappedBy = "groups", targetEntity = User.class)
    @JsonBackReference
    private Set<User> users;

    @ApiModelProperty(notes = "Group's name", required = true)
    @Column(name = "name")
    private String name;

    @ApiModelProperty(notes = "Associated questions", required = true)
    @OneToMany(mappedBy="group")
    @JsonBackReference
    private Set<Question> questions;

    public Group() {
    }

    public Group(Set<User> users, String name, Set<Question> questions) {
        this();
        this.users = users;
        this.name = name;
        this.questions = questions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        if(questions == null){
            questions = new HashSet<>();
        }
        questions.add(question);
    }

    public void addUser(User user){
        if(users == null){
            users = new HashSet<>();
        }
        users.add(user);
    }

    public void removeUser(User user){
        if(users != null){
           if(users.contains(user)){
               users.remove(user);
           }
        }
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
        return Objects.equals(users, group.users) &&
                Objects.equals(name, group.name) &&
                Objects.equals(questions, group.questions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), users, name, questions);
    }

    @Override
    public String toString() {
        return "Group{" +
                "users=" + users +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
