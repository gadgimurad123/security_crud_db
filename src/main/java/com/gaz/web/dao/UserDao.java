package com.gaz.web.dao;

import com.gaz.web.entity.User;

import java.util.List;

public interface UserDao {
    public User getUserByName(String name);

    public List<User> getAllUsers();

    public void saveUser(User user);

    public void deleteUser(Long id);

    User getUserById(Long id);
}
