package nusiss.csf.server.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nusiss.csf.server.Repository.SQLRepo;
import nusiss.csf.server.Services.handlerService;
import nusiss.csf.server.model.user;

@RestController
@RequestMapping(path="api/login",produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    
    @Autowired
    private SQLRepo repo;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkUser(@RequestBody String json){
        handlerService handler = new handlerService();
        JsonObject o = handler.userCredential(json);
        String username = o.getString("username");
        String password = o.getString("password");
        if(repo.checkUserLogin(username,password) == true){
            return ResponseEntity.ok(Json.createObjectBuilder().add("message","success").build().toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Json.createObjectBuilder().add("message","no such user").build().toString());
    }

}
