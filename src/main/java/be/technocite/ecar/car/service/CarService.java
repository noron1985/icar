package be.technocite.ecar.car.service;

import be.technocite.ecar.car.dao.CarDao;
import be.technocite.ecar.car.exception.CarConflictException;
import be.technocite.ecar.car.model.Car;
import be.technocite.ecar.carapi.dto.CarDtoBuyer;
import be.technocite.ecar.carapi.dto.CarDtoRetailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class CarService{

    @Autowired
    private CarDao carDao;
    @Autowired
    private MessageSource messageSource;

    private final Function<Car, CarDtoBuyer> carBuyerConverter = this::convertToBuyerDto;
    private final Function<Car, CarDtoRetailer> carRetailerConverter = this::convert;

    private CarDtoBuyer convertToBuyerDto(Car car) {
        return new CarDtoBuyer(
                car.getId(),
                car.getBrand(),
                car.getMarketPrice(),
                car.getVin(),
                car.getYear()
        );
    }

    private CarDtoRetailer convert(Car car) {
        return new CarDtoRetailer(
                car.getId(),
                car.getBrand(),
                car.getOriginalPrice(),
                car.getMarketPrice(),
                car.getVin(),
                car.getYear(),
                car.getUserId()
        );
    }

    public List<CarDtoBuyer> search(String brand, double maxPrice) {
        return carDao.findAll().stream()
                .filter(car -> car.getBrand().equals(brand)
                        &&  car.getMarketPrice() <= maxPrice)
                .map(carBuyerConverter)
                .collect(toList());
    }

    public CarDtoRetailer addCar(CarDtoRetailer carDto) {

        Car car = carDao.findById(carDto.getId());

        if(car != null) {
            throw new CarConflictException(messageSource.getMessage("error.conflict.id", new Object[]{car.getId()}, LocaleContextHolder.getLocale()));
        }else {
            return convert(carDao.save(new Car(carDto.getId(),
                    carDto.getBrand(),
                    carDto.getOriginalPrice(),
                    carDto.getMarketPrice(),
                    carDto.getVin(),
                    carDto.getYear(),
                    carDto.getUserId())));
        }
    }

    public CarDtoRetailer updateCar(CarDtoRetailer carDto) {

        Car car = carDao.findById(carDto.getId());

        if(car != null) {
            car.setBrand(carDto.getBrand());
            car.setOriginalPrice(carDto.getOriginalPrice());
            car.setMarketPrice(carDto.getMarketPrice());
            return carRetailerConverter.apply(carDao.save(car));
        }else {
            return null;
        }
    }

    public int count() {
        return carDao.count();
    }

    public CarDtoBuyer findById(String id) {
        Car car = carDao.findById(id);
        if(car != null) {
            return carBuyerConverter.apply(car);
        }else {
            return null;
        }
    }

    public boolean delete(String id) {
        return carDao.delete(id);
    }

    public List<CarDtoBuyer> getAll() {
        return carDao.findAll().stream().map(carBuyerConverter).collect(toList());
    }

    public List<CarDtoRetailer> findByUserId(String id) {
        return carDao.findByUserId(id).stream().map(carRetailerConverter).collect(toList());
    }
}
