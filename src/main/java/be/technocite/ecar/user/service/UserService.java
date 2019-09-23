package be.technocite.ecar.user.service;

import be.technocite.ecar.user.dao.UserDao;
import be.technocite.ecar.user.model.User;
import be.technocite.ecar.userapi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserService implements Function<User, UserDto> {

    @Autowired
    private UserDao userDao;

    public UserDto findById(String id) {
        User user = userDao.findById(id);
        if(user != null) {
            return this.apply(user);
        }else{
            return null;
        }
    }

    @Override
    public UserDto apply(User user) {
        return new UserDto(user.getId(), user.getPseudo(), user.getUserType());
    }
}
