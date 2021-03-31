package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(String firstName, String lastName, byte age);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    void update(User user);
}
