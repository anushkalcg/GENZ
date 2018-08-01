package com.genz.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email","username"}) })
@ApiModel(description = "User's information.")
public class User extends AbstractEntry{

    @Column(name = "score")
    @ApiModelProperty(notes = "User's total score")
    private Integer score;

    @ApiModelProperty(notes = "User's email", required = true)
    @Column(name = "email")
    private String email;

    @ApiModelProperty(notes = "User's password", required = true)
    @Column(name = "password")
    private String password;

    @ApiModelProperty(notes = "User's firstname", required = true)
    @Column(name = "name")
    private String name;

    @ApiModelProperty(notes = "User's lastname", required = true)
    @Column(name = "surname")
    private String surname;

    @ApiModelProperty(notes = "User's age", required = true)
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty(notes = "User's phone number", required = true)
    @Column(name = "phonenumber")
    private String phoneNumber;

    @ApiModelProperty(notes = "User's username", required = true)
    @Column(name = "username")
    private String username;

    @ApiModelProperty(notes = "User's status", required = true)
    @Column(name = "user_status")
    private UserStatus userStatus;

    @ApiModelProperty(notes = "Groups where user is member", required = false)
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            targetEntity = Group.class)
    @JoinTable(name = "users_groups",
            joinColumns = { @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id")
            },
            inverseJoinColumns = { @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "id")
    })
    private List<Group> groups;

    @ApiModelProperty(notes = "User's statistics", required = false)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private UserStatistics userStatistics;

    public User() {
    }

    public User(Integer score, String email, String password, String name, String surname, Integer age, String phoneNumber, String username, UserStatus userStatus, List<Group> groups, UserStatistics userStatistics) {
        this();
        this.score = score;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.userStatus = userStatus;
        this.groups = groups;
        this.userStatistics = userStatistics;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }

    public void addGroup(Group group){
        if(groups == null){
            groups = new ArrayList<>();
        }
        if(!groups.contains(group)){
            groups.add(group);
        }
    }

    public void removeGroup(Group group){
        if(groups == null){
            return;
        }
        if(groups.contains(group)){
            groups.add(group);
        }
    }
}
