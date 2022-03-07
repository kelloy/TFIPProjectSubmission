package nusiss.csf.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nusiss.csf.server.Repository.SQLRepo;
import nusiss.csf.server.Services.foodServices;
import nusiss.csf.server.Services.handlerService;
import nusiss.csf.server.model.favouriteFood;

@RestController
@RequestMapping(path="api/food/fooddetails",produces="application/json;charset=utf8")

public class foodDetailController {

    @Autowired
    private foodServices fdSvc;

    @Autowired
    private SQLRepo repo;

    @GetMapping(path="{uuid}")
    public ResponseEntity<String> getFoodDetail(@PathVariable String uuid){
        ResponseEntity<String> resp = fdSvc.getFoodUUID(uuid);
        return resp;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFoodDetail(@RequestBody String json){
        handlerService handler = new handlerService();
        favouriteFood ff = new favouriteFood();
        ff = handler.buildfavourite(json);
        System.out.println(ff);
        repo.saveFood(ff);
        JsonObject response = ff.toJson(ff);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.toString());
    }

    @PostMapping(path = "delete", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFood(@RequestBody String json){
        System.out.println(json);
        handlerService handler = new handlerService();
        favouriteFood ff = new favouriteFood();
        ff = handler.buildfavourite(json);
        repo.deleteFood(ff);
        JsonObject response = Json.createObjectBuilder().add("message","entry deleted").build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.toString());
    }
}
