package io.oliverj.chat.server.data.model;

import com.mongodb.hibernate.annotations.ObjectIdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @ObjectIdGenerator
    private Object id;

    private String username;
    private String hashedPassword;
    private String profileUrl;

    public User(String username, String hashedPassword, String profileUrl) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.profileUrl = profileUrl;
    }

    public User() {}

    public Object getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
