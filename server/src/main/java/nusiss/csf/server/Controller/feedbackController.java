package nusiss.csf.server.Controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nusiss.csf.server.Repository.MongoDbRepo;
import nusiss.csf.server.Repository.SQLRepo;
import nusiss.csf.server.Services.EmailService;
import nusiss.csf.server.Services.handlerService;
import nusiss.csf.server.model.feedback;

@RestController
@RequestMapping(path="api/feedback",produces = MediaType.APPLICATION_JSON_VALUE)
public class feedbackController {

    @Autowired
    private SQLRepo repo;

    @Autowired
    private MongoDbRepo mongodb;

    @Autowired
    private EmailService email;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendToServer(@RequestBody String json){
        System.out.println(json);
        handlerService handler = new handlerService();
        feedback fb = new feedback();
        fb = handler.buildfb(json);
        System.out.println(fb);
        repo.saveFeedBack(fb);
        System.out.println("checking");
        mongodb.save(fb);
        JsonObject response = fb.toJson(fb); 
        email.sendEmail(fb.getEmail(),"Your Feedback",handler.emailString(fb));
        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
        
    }
    
}
