package be.technocite.ecar.carapi.dto;

import java.util.Date;

public class CarDtoBuyer {

    private String id;
    private String brand;
    private double marketPrice;
    private String vin;
    private Date year;

    public CarDtoBuyer(String id, String brand, double marketPrice, String vin, Date year) {
        this.id = id;
        this.brand = brand;
        this.marketPrice = marketPrice;
        this.vin = vin;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
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
}
