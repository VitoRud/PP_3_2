package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersList() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User getUser(long id) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :identity");
        query.setParameter("identity", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void editUser(User user) {
            entityManager.merge(user);
        }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery
                ("select u from User u left join fetch u.roles where u.name=:username", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role getById(Long id) {
        Role role = entityManager.find(Role.class, id);
        if (role == null) {
            throw new EntityNotFoundException();
        } else {
            return role;
        }
    }
}
