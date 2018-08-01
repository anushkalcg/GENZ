package com.genz.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.genz.server.utils.UserStatisticsEntryToJsonConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Nikos.Toulios
 */
@Entity
@Table(name = "userstatistics")
@ApiModel(description = "User's statistics")
public class UserStatistics {

    @Id
    Long id;

    @ApiModelProperty(notes = "User's answers and points per week", required = true)
    @Column(name = "user_answers_points_per_week")
    @Convert(converter=UserStatisticsEntryToJsonConverter.class)
    private UserStatisticsEntry userStatisticsEntry;

    @ApiModelProperty(notes = "User's id", required = true)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public UserStatistics() {
    }

    public UserStatistics(UserStatisticsEntry userStatisticsEntry, User user) {
        this();
        this.userStatisticsEntry = userStatisticsEntry;
        this.user = user;
    }

    public UserStatistics(Long id, UserStatisticsEntry userStatisticsEntry, User user) {
        this(userStatisticsEntry, user);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserStatisticsEntry getUserStatisticsEntry() {
        return userStatisticsEntry;
    }

    public void setUserStatisticsEntry(UserStatisticsEntry userStatisticsEntry) {
        this.userStatisticsEntry = userStatisticsEntry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatistics that = (UserStatistics) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userStatisticsEntry, that.userStatisticsEntry) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userStatisticsEntry, user);
    }

    @Override
    public String toString() {
        return "UserStatistics{" +
                "id=" + id +
                ", userStatisticsEntry=" + userStatisticsEntry +
                ", user=" + user +
                '}';
    }
}
