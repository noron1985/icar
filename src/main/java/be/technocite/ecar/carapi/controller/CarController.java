package be.technocite.ecar.carapi.controller;

import be.technocite.ecar.car.service.CarService;
import be.technocite.ecar.carapi.dto.CarDtoBuyer;
import be.technocite.ecar.carapi.dto.CarDtoRetailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "${spring.data.rest.base-path}/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<CarDtoBuyer> getCar(@PathVariable String id) {
        CarDtoBuyer car = carService.findById(id);

        if(car != null) {
            return ResponseEntity.ok().body(car);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<CarDtoBuyer> getAllCars() {
        return carService.getAll();
    }

    @GetMapping(path = "/search")
    @Transactional(readOnly = true)
    public List<CarDtoBuyer> search(@RequestParam String brand, @RequestParam double maxPrice) {
        return carService.search(brand, maxPrice);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CarDtoBuyer> addCar(@RequestBody CarDtoRetailer car) {
        int preCount = carService.count();

        car = carService.addCar(car);

        if(preCount == carService.count() - 1) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(car.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity<CarDtoBuyer> deleteCar(@PathVariable String id) {
        if(carService.delete(id)) {
          return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CarDtoBuyer> updateCar(@RequestBody CarDtoRetailer car) {
        if(carService.updateCar(car) != null) {
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/user/{userId}")
    @Transactional(readOnly = true)
    public List<CarDtoRetailer> getCarsByUser(@PathVariable String userId) {
        return carService.findByUserId(userId);
    }
}
