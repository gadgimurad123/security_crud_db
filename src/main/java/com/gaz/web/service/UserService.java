package com.gaz.web.service;

import com.gaz.web.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User getUserByName(String name);

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUserById(Long id);

    public void deleteUser(Long id);

}
