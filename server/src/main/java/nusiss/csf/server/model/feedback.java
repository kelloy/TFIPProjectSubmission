package nusiss.csf.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;


@Document(collection = "feedback")
public class feedback {
    @Id
    private String username;
    private String email;
    private int contactNumber;
    private String comment;

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public feedback(String username, String email, int contactNumber, String comment) {
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.comment = comment;
    }

    public feedback() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public JsonObject toJson(feedback fb) {
        return Json.createObjectBuilder()
        .add("username",fb.getUsername())
        .add("contactNumber",fb.getContactNumber())
        .add("email",fb.getEmail())
        .add("comment",fb.getComment())
        .build();
    }

    
}
