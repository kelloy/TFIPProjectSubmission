package nusiss.csf.server.Services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import nusiss.csf.server.constant.CONSTANTS;
import nusiss.csf.server.model.food;

@Service
public class foodServices {

    String apiKey = System.getenv(CONSTANTS.ENV_TIH_API_KEY);
    
    public ResponseEntity<String> getFood(String food){
    String url = UriComponentsBuilder.fromUriString(CONSTANTS.STB_URL).queryParam("keyword", food).queryParam("apikey",apiKey)
    .toUriString();
    handlerService handler = new handlerService();
    RequestEntity req = RequestEntity.get(url).build();
    RestTemplate template = new RestTemplate();
    ResponseEntity<String> resp = template.exchange(req,String.class);
    String result = handler.toString(resp);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
    }

    public ResponseEntity<String> getFoodUUID(String uuid){
    String url = UriComponentsBuilder.fromUriString(CONSTANTS.STB_FOOD_DETAIL_URL).queryParam("uuid", uuid).queryParam("apikey",apiKey)
    .toUriString();
    RequestEntity req = RequestEntity.get(url).build();
    RestTemplate template = new RestTemplate();
    handlerService handler = new handlerService();
    ResponseEntity<String> resp = template.exchange(req,String.class);
    String result = handler.foodDetailsString(resp);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
    }

}

