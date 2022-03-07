package nusiss.csf.server.Services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import nusiss.csf.server.model.favouriteFood;
import nusiss.csf.server.model.feedback;
import nusiss.csf.server.model.food;
import nusiss.csf.server.model.user;

public class handlerService {


    public String toString(ResponseEntity<String> resp){

        JsonArrayBuilder resultarray = Json.createArrayBuilder();
        try(InputStream is = new ByteArrayInputStream(resp.getBody().getBytes(StandardCharsets.UTF_8))){
            JsonReader reader = Json.createReader(is);
            JsonObject result = reader.readObject();
            JsonArray readings = result.getJsonArray("data");
    
            for (JsonValue s: readings){
                JsonObject o = s.asJsonObject();
                JsonObject data = Json.createObjectBuilder().add("name",o.getString("name"))
                .add("uuid",o.getString("uuid"))
                .build();
                resultarray.add(data);
            }
        }catch (IOException ie){

        }
        return resultarray.build().toString();        
    }

    public String foodDetailsString(ResponseEntity<String> resp){

        JsonObjectBuilder data = Json.createObjectBuilder();
        food food = new food();

        try(InputStream is = new ByteArrayInputStream(resp.getBody().getBytes(StandardCharsets.UTF_8))){
            JsonReader reader = Json.createReader(is);
            JsonObject result = reader.readObject();
            JsonArray readings = result.getJsonArray("data");
    
            for (JsonValue s: readings){
                JsonObject o = s.asJsonObject();
                JsonObject address = o.getJsonObject("address");
                JsonObject location = o.getJsonObject("location");
                JsonObject contact = o.getJsonObject("contact");
                JsonArray reviewslist = o.getJsonArray("reviews");
                JsonArrayBuilder reviews = Json.createArrayBuilder();

                    for (JsonValue v: reviewslist){
                        JsonObject  review = v.asJsonObject();
                        reviews.add(review.getString("text"));
                    }

                data.add("name",o.getString("name"))
                .add("rating",o.getInt("rating"))
                .add("block",address.getString("block"))
                .add("streetName",address.getString("streetName"))
                .add("floorNumber",address.getString("floorNumber"))
                .add("unitNumber",address.getString("unitNumber"))
                .add("postalCode",address.getString("postalCode"))
                .add("latitude",location.getJsonNumber("latitude").doubleValue())
                .add("longitude",location.getJsonNumber("longitude").doubleValue())
                .add("contact",contact.getString("primaryContactNo"))
                .add("uuid",o.getString("uuid"))
                .add("introduction",o.getString("body"))
                .add("reviews",reviews.build().toString());
            }

            
        }catch (IOException ie){
    
        }
        return data.build().toString();
        
    }

    public feedback buildfb(String resp){
        JsonReader reader = Json.createReader(new ByteArrayInputStream(resp.getBytes()));
        JsonObject result = reader.readObject();
        String username = result.getString("username");
        String email = result.getString("email");
        int contactNumber = Integer.parseInt(result.getString("contactNumber"));
        String comment = result.getString("comment");
        System.out.println(comment);
        final feedback fb = new feedback(username,email,contactNumber,comment);
/*         final feedback fb = new feedback(); */
        return fb;
    }

    public String emailString(feedback fb){
        String username = fb.getUsername();
        String comment = fb.getComment();
        String emailtext = "Hi "+username+", Thank you for your feedback"+"\nHere is a copy of your feedback: "+"\n"+ comment;
        return emailtext;
    }

    public user builduser(String resp){
        JsonReader reader = Json.createReader(new ByteArrayInputStream(resp.getBytes()));
        JsonObject result = reader.readObject();
        String username = result.getString("username");
        String email = result.getString("email");
        int contactNumber = Integer.parseInt(result.getString("contactNumber"));
        String password = result.getString("password");
        final user user = new user(username,password,email,contactNumber);
        return user;
    }

    public JsonObject userCredential(String resp){
        JsonReader reader = Json.createReader(new ByteArrayInputStream(resp.getBytes()));
        JsonObject result = reader.readObject();
        return result;
    }
 
    public favouriteFood buildfavourite(String resp){
        JsonReader reader = Json.createReader(new ByteArrayInputStream(resp.getBytes()));
        JsonObject result = reader.readObject();
        int userId = result.getInt("userId");
        String stallName = result.getString("stallName");
        String uuid = result.getString("uuid");
        String username = result.getString("username");
        favouriteFood ff = new favouriteFood();
        ff.setUsername(username);
        ff.setUuid(uuid);
        ff.setStallName(stallName);
        ff.setUserId(userId);
        return ff;

    }

    
    
}
