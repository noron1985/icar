package be.technocite.ecar.car.dao.impl;

import be.technocite.ecar.car.dao.CarDao;
import be.technocite.ecar.car.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Repository
@Primary
public class CarDaoDBImpl implements CarDao, RowMapper<Car> {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Car findById(String id) {
        try {
            return jdbcTemplate.queryForObject("select * from car where id = ?", new Object[]{id}, this);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query("select * from car", this);
    }

    @Override
    public Car save(Car car) {

        Object[] params = new Object[]{
                car.getId(),
                car.getBrand(),
                car.getOriginalPrice(),
                car.getMarketPrice(),
                car.getVin(),
                car.getYear()
        };

        if(findById(car.getId()) == null) {
            jdbcTemplate.update("insert into car (id, brand, original_price, market_price, vin, year)" +
                    "values (?,?,?,?,?,?)", params);
        }else {
            jdbcTemplate.update("update car set brand = ?, original_price = ?, market_price = ?" +
                    "where id = ?", params[1], params[2], params[3], params[0]);
        }

        return findById(car.getId());
    }

    @Override
    public boolean delete(String id) {
        return jdbcTemplate.update("delete from car where id = ?", id) == 1;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from car", Integer.class);
    }

    @Override
    public Car mapRow(ResultSet resultSet, int index) throws SQLException {
         return new Car(
                 resultSet.getString("id"),
                 resultSet.getString("brand"),
                 resultSet.getDouble("original_price"),
                 resultSet.getDouble("market_price"),
                 resultSet.getString("vin"),
                 resultSet.getDate("year"),
                 resultSet.getString("user_id")
         );
    }

    @Override
    public List<Car> findByUserId(String id) {
        return jdbcTemplate.query("select * from car where user_id = ?", new Object[]{id}, this);
    }

    /* @PostConstruct
   ATTENTION à n'utiliser qu'une fois lors du premier démarrage du serveur afin de populer la db
    private void onPostConstruct() {
        try {
            save(new Car(UUID.randomUUID().toString(),
                    "BMW",
                    10,
                    25000,
                    "lsnidcf543580",
                    simpleDateFormat.parse("2011-09-19")));
            save(new Car(UUID.randomUUID().toString(),
                    "Mercedes",
                    15,
                    35000,
                    "d3r5c144d354x51f",
                    simpleDateFormat.parse("2019-01-20")));
            save(new Car(UUID.randomUUID().toString(),
                    "Fiat",
                    8,
                    12000,
                    "e2c54s325df",
                    simpleDateFormat.parse("2013-02-02")));
            save(new Car(UUID.randomUUID().toString(),
                    "Rover",
                    9,
                    18000,
                    "sex2x834sdfd4",
                    simpleDateFormat.parse("2014-04-15")));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/
}
