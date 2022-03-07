package nusiss.csf.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import nusiss.csf.server.constant.CONSTANTS;

@Service
public class EmailService {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;
    
    private String username;

    private String password;

    @Autowired 
    private JavaMailSenderImpl emailSender;

    public void sendEmail(String to, String subject, String text){

        password = System.getenv(CONSTANTS.EMAIL_PASSWORD);
        username = System.getenv(CONSTANTS.EMAIL_USERNAME);

        emailSender.setHost(host);
        emailSender.setPort(port);
        emailSender.setUsername(username);
        emailSender.setPassword(password);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("me@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
    
}
