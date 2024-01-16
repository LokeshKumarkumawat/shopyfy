package com.ezorx.ecoom.service;

import com.ezorx.ecoom.dao.RoleDao;
import com.ezorx.ecoom.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {


    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
        return roleDao.save(role);
    }
}
