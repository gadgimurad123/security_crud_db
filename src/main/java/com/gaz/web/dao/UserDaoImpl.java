package com.gaz.web.dao;

import com.gaz.web.entity.Role;
import com.gaz.web.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getUserByName(String username) {
        return entityManager.createQuery("from User user where user.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == 0) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
//        entityManager.remove(getUserById(id));
//        entityManager.createNativeQuery("delete from table_users where id = "+ id).executeUpdate();
        entityManager.createQuery("delete from User user where user.id=:id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Role getRoleByName(String username) {
        return entityManager.createQuery("from Role role where role.role=:username", Role.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<Role> getListRole() {
        return entityManager.createQuery("from Role").getResultList();
    }
}
