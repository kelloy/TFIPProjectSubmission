package nusiss.csf.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.csf.server.Services.EmailService;
import nusiss.csf.server.Services.foodServices;

@RestController
@RequestMapping(path="api/food",produces="application/json;charset=utf8")

public class foodController {

    @Autowired
    private foodServices fdSvc;

    @Autowired
    private EmailService emailSvc;

    @GetMapping(path="{foodname}")
    public ResponseEntity<String> getFood(@PathVariable String foodname){
        ResponseEntity<String> resp = fdSvc.getFood(foodname);
        return resp;
    }

}
