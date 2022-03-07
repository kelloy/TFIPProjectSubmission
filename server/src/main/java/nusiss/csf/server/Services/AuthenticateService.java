/* package nusiss.csf.server.Services;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import nusiss.csf.server.Repository.SQLRepo;

@Service
public class AuthenticateService {

    @Autowired
    private SQLRepo repo;

    private SecretKey signKey;

    @PostConstruct
    public void init(){
        String passphrase = System.getenv("JWT_SIGNKEY");

        try{
            signKey = Keys.hmacShaKeyFor(passphrase.getBytes("UTF-8"));
        }catch(Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Creating HMAC Sign Key",e);

        }
    }

    public boolean validate(String token){
        try {
            Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e){
            e.printStackTrace();
        }
        return false;
    }

    public Optional<JsonObject> authenticate(String username, String password){

        if(!repo.checkUserLogin(username, password))
            return Optional.empty();

        String token = Jwts.builder().setSubject(username).signWith(signKey).compact();

        return Optional.of(Json.createObjectBuilder()
        .add("subject",username)
        .add("token",token)
        .build());
        
    }
    
}
  */