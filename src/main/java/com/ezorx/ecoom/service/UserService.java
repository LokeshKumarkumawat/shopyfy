package com.ezorx.ecoom.service;

import com.ezorx.ecoom.dao.RoleDao;
import com.ezorx.ecoom.dao.UserDao;
import com.ezorx.ecoom.entity.Role;
import com.ezorx.ecoom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;




    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));


        return userDao.save(user);
    }




    public void initRolesAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserPassword(getEncodedPassword("admin"));

        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserFirstName("lokesh");
        user.setUserLastName("kumawat");
        user.setUserPassword(getEncodedPassword("1234"));
//        user.setUserPassword(getEncodedPassword("1234"));

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }



    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
