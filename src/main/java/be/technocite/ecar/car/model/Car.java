package be.technocite.ecar.car.model;

import java.util.Date;

//@JsonIgnoreProperties({"originalPrice"}) du json vers le java
public class Car {

    private String id;
    private String brand;
//    @JsonIgnore du java vers le json
    private double originalPrice;
    private double marketPrice;
    private String vin;
    private Date year;
    private String userId;

    public Car(String id, String brand, double originalPrice, double marketPrice, String vin, Date year, String userId) {
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", originalPrice=" + originalPrice +
                ", marketPrice=" + marketPrice +
                ", vin='" + vin + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id);
    }
}
