package com.gaz.web.dao;

import com.gaz.web.entity.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);

    Role getRoleById(Long id);

    List<Role> allRoles();

    Role getDefaultRole();
}
