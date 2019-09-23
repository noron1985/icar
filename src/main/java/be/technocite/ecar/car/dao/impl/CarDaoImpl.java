package be.technocite.ecar.car.dao.impl;

import be.technocite.ecar.car.dao.CarDao;
import be.technocite.ecar.car.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Repository
public class CarDaoImpl implements CarDao {

    Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<Car> cars = newArrayList();

    @Override
    public Car findById(String id) {
        return cars.stream().filter(car -> car.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public Car save(Car car) {
        int carIndex = cars.indexOf(car);

        if (carIndex == -1) {
            cars.add(car);
            logger.debug("Car" + car.getId() + " created");
            return cars.get(cars.size() - 1);
        } else {
            cars.set(carIndex, car);
            logger.debug("Car" + car.getId() + " updated");
            return cars.get(carIndex);
        }
    }

    @Override
    public boolean delete(String id) {
        Car carPersisted = findById(id);
        if (carPersisted != null) {
           return cars.remove(carPersisted);
        }
        return false;
    }

    @Override
    public int count() {
        return cars.size();
    }

    @Override
    public List<Car> findByUserId(String id) {
        return null;
    }

    @PostConstruct
    private void onPostConstruct() {
       /* try {
            cars.add(new Car(UUID.randomUUID().toString(),
                    "BMW",
                    10,
                    25000,
                    "lsnidcf543580",
                    simpleDateFormat.parse("2011-09-19")));
            cars.add(new Car(UUID.randomUUID().toString(),
                    "Mercedes",
                    15,
                    35000,
                    "d3r5c144d354x51f",
                    simpleDateFormat.parse("2019-01-20")));
            cars.add(new Car(UUID.randomUUID().toString(),
                    "Fiat",
                    8,
                    12000,
                    "e2c54s325df",
                    simpleDateFormat.parse("2013-02-02")));
            cars.add(new Car(UUID.randomUUID().toString(),
                    "Rover",
                    9,
                    18000,
                    "sex2x834sdfd4",
                    simpleDateFormat.parse("2014-04-15")));

        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
