package be.technocite.ecar.carapi.dto;

import java.util.Date;

public class CarDtoRetailer {

    private String id;
    private String brand;
    private double originalPrice;
    private double marketPrice;
    private String vin;
    private Date year;
    private String userId;


    public CarDtoRetailer(String id, String brand, double originalPrice, double marketPrice, String vin, Date year, String userId) {
        this.id = id;
        this.brand = brand;
        this.originalPrice = originalPrice;
        this.marketPrice = marketPrice;
        this.vin = vin;
        this.year = year;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public String getVin() {
        return vin;
    }

    public Date getYear() {
        return year;
    }

    public String getUserId() {
        return userId;
    }
}
