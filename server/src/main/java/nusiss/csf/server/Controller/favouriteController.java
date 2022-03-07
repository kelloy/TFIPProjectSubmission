package nusiss.csf.server.Controller;

import java.lang.module.ResolutionException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import nusiss.csf.server.Repository.MongoDbRepo;
import nusiss.csf.server.Repository.SQLRepo;
import nusiss.csf.server.model.favouriteFood;

@RestController
@RequestMapping(path = "api/favourite", produces="application/json;charset=utf8")


public class favouriteController {

    @Autowired
    private SQLRepo repo;

    @GetMapping(path="{username}")
    public ResponseEntity<String> getFood(@PathVariable String username){
        List<favouriteFood> mylist = repo.getFavourites(username);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        mylist.stream().forEach(v->arrayBuilder.add(v.toJson(v)));
        String response = arrayBuilder.build().toString();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    }

    
