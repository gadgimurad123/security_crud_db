package com.gaz.web.service;

import com.gaz.web.dao.RoleDao;
import com.gaz.web.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public List<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    @Transactional
    public Role getDefaultRole() {
        return roleDao.getDefaultRole();
    }

}
