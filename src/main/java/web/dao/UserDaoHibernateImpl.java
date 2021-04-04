package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(String firstName, String lastName, byte age, String username, String password) {
        entityManager.persist(new User(firstName, lastName, age, username, password));
    }

    @Override
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager
                        .createQuery("FROM User", User.class)
                        .getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
