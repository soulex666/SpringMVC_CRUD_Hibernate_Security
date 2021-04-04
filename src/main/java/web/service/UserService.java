package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(String name, String lastName, byte age, String username, String password);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    void update(User user);

    User findByUsername(String username);
}
