package nusiss.csf.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nusiss.csf.server.Repository.SQLRepo;
import nusiss.csf.server.Services.handlerService;
import nusiss.csf.server.model.user;

@RestController
@RequestMapping(path="api/register")
public class RegistrationController {

    @Autowired
    private SQLRepo repo;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newUser(@RequestBody String json){
        handlerService handler = new handlerService();
        user user = new user();
        user = handler.builduser(json);
        if(repo.saveUser(user)){
            JsonObject response = user.toJson(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
        };
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Json.createObjectBuilder().add("error","User already exist").build().toString());
    }
        
    }
    
