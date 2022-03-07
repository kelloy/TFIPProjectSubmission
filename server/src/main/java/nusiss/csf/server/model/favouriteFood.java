package nusiss.csf.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class favouriteFood {
    private int userId;
    private String stallName;
    private String uuid;
    private String username;


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public favouriteFood() {
    }

    public favouriteFood(int userId, String uuid,String stallName, String username) {
        this.stallName = stallName;
        this.uuid = uuid;
        this.userId = userId;
        this.username = username;
    }

    public String getStallName() {
        return this.stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static favouriteFood populate(SqlRowSet rs){
        final favouriteFood ff = new favouriteFood();
        ff.setStallName(rs.getString("stallName"));
        ff.setUuid(rs.getString("uuid"));
        ff.setUserId(Integer.parseInt(rs.getString("userId")));
        ff.setUsername(rs.getString("username"));
        return ff;
    }

    public JsonObject toJson(favouriteFood ff) {
        return Json.createObjectBuilder()
         .add("uuid",ff.getUuid())
         .add("username",ff.getUsername())
        .add("userId",ff.getUserId())
         .add("stallName",ff.getStallName())
        .build();

    }
    
}
