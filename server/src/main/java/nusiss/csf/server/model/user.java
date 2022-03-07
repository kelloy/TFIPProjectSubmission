package nusiss.csf.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class user {
    private int userId;
    private String username;
    private String password;
    private String email;
    private int contactNumber;

    public user(String username, String password, String email, int contactNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public user() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public JsonObject toJson(user user) {
        return Json.createObjectBuilder()
        .add("username",user.getUsername())
        .add("contactNumber",user.getContactNumber())
        .add("email",user.getEmail())
        .add("password",user.getPassword())
        .build();
    }
    
}
