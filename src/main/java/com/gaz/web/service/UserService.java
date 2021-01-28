package com.gaz.web.service;

import com.gaz.web.entity.Role;
import com.gaz.web.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User getUserByName(String username);

    public List<User> getAllUsers();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public Role getRoleByName(String username);

    public User getUserById(Long id);

    List<Role> getListRole();
}
