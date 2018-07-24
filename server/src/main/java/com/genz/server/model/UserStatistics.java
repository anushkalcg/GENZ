package com.genz.server.model;

import com.genz.server.utils.UserStatisticsEntryToJsonConverter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "userstatistics")
public class UserStatistics {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private User userInfo;

    @Column(name = "user_answers")
    @Convert(converter=UserStatisticsEntryToJsonConverter.class)
    private UserStatisticsEntry userStatisticsEntry;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public UserStatistics() {
    }

    public UserStatistics(User userInfo, UserStatisticsEntry userStatisticsEntry, User user) {
        this();
        this.userInfo = userInfo;
        this.userStatisticsEntry = userStatisticsEntry;
        this.user = user;
    }

    public UserStatistics(Long id, User userInfo, UserStatisticsEntry userStatisticsEntry, User user) {
        this(userInfo, userStatisticsEntry, user);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
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
                Objects.equals(userInfo, that.userInfo) &&
                Objects.equals(userStatisticsEntry, that.userStatisticsEntry) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userInfo, userStatisticsEntry, user);
    }

    @Override
    public String toString() {
        return "UserStatistics{" +
                "id=" + id +
                ", userInfo=" + userInfo +
                ", userStatisticsEntry=" + userStatisticsEntry +
                ", user=" + user +
                '}';
    }
}
