package be.technocite.ecar.user.dao;

import be.technocite.ecar.user.model.User;

public interface UserDao {

    User findById(String id);

    int count();
}
