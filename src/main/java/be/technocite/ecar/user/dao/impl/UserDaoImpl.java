package be.technocite.ecar.user.dao.impl;

import be.technocite.ecar.user.dao.UserDao;
import be.technocite.ecar.user.model.User;
import be.technocite.ecar.user.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;

import static be.technocite.ecar.user.model.UserType.BUYER;
import static be.technocite.ecar.user.model.UserType.RETAILER;
import static java.util.UUID.randomUUID;

@Repository
//@Transactional peut aussi se mettre ici et pas dans les méthodes http du coup
public class UserDaoImpl implements UserDao, RowMapper<User> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(String id) {
        try {
            return jdbcTemplate.queryForObject("select * from public.user where id = ?", new Object[]{id},this );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(
                resultSet.getString("id"),
                resultSet.getString("pseudo"),
                resultSet.getString("password"),
                UserType.valueOf(resultSet.getString("user_type")));
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from public.user", Integer.class);
    }

    @PostConstruct
    private void postConstruct() {
        if(count() == 0) {
            jdbcTemplate.update("insert into public.user (id, pseudo, password, user_type)" +
                    "values (?,?,?,?)", randomUUID().toString() ,"dotista", "Spirou", RETAILER.toString());
            jdbcTemplate.update("insert into public.user (id, pseudo, password, user_type)" +
                    "values (?,?,?,?)", randomUUID().toString() ,"acheteurBauf23", "Baguette", BUYER.toString());
        }
    }
}
