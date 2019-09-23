package be.technocite.ecar.car.dao;

import be.technocite.ecar.car.model.Car;

import java.util.List;

public interface CarDao {

    Car findById(String id);

    List<Car> findAll();

    Car save(Car car);

    boolean delete(String id);

    int count();

    List<Car> findByUserId(String id);
}
