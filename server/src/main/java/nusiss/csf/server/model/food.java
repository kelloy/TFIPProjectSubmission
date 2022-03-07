package nusiss.csf.server.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class food {

    private String name;
    private Integer rating;
    private String block;
    private String streetName;
    private String floorNumber;
    private String unitNumber;
    private String postalCode;
    private double longitude;
    private double latitude;
    private String uuid;
    private String introduction;
    private List<String> review;

    public List<String> getReview() {
        return this.review;
    }

    public void setReview(List<String> review) {
        this.review = review;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getBlock() {
        return this.block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getFloorNumber() {
        return this.floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getUnitNumber() {
        return this.unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public food(String name, Integer rating, String block, String streetName, String floorNumber, String unitNumber, String postalCode, double longitude, double latitude,String uuid, String introduction, List<String> review) {
        this.name = name;
        this.rating = rating;
        this.block = block;
        this.streetName = streetName;
        this.floorNumber = floorNumber;
        this.unitNumber = unitNumber;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.uuid = uuid;
        this.introduction = introduction;
        this.review = review;
    }

    public food(){

    }




    
}
