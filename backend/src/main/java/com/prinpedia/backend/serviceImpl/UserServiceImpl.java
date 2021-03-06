package com.prinpedia.backend.serviceImpl;

import com.prinpedia.backend.dao.RoleDao;
import com.prinpedia.backend.dao.UserDao;
import com.prinpedia.backend.entity.Role;
import com.prinpedia.backend.entity.User;
import com.prinpedia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public Boolean createUser(User user) {
        if(user == null) return false;
        if(user.getUsername() == null || user.getPassword() == null) {
            return false;
        }
        userDao.update(user);
        return true;
    }

    @Override
    public Boolean register(String username, String password, String email) {
        if(userDao.findByName(username) != null) return false;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setEnabled(true);
        Role role = roleDao.findByRoleName("ROLE_USER");
        if(role == null) { role = new Role(); role.setRoleName("ROLE_USER"); }
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoleList(roleList);
        user.setUserId(-1);
        userDao.update(user);
        return true;
    }

    @Override
    public Boolean validate(String username, String password) {
        User user = userDao.findByName(username);
        if(user == null) return false;
        return user.getPassword().equals(password);
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public Boolean editUserDetail(User user) {
        if(user.getUsername() == null) return false;
        User oldUser = userDao.findByName(user.getUsername());
        if(oldUser == null) return false;
        oldUser.setEmail(user.getEmail());
        oldUser.setBirthday(user.getBirthday());
        oldUser.setAvatarBase64(user.getAvatarBase64());
        if(user.getPassword() != null) oldUser.setPassword(user.getPassword());
        userDao.update(oldUser);
        return true;
    }
}
