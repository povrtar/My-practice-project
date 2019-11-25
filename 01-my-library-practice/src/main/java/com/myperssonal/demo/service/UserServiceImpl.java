package com.myperssonal.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myperssonal.demo.DAO.UserDAO;
import com.myperssonal.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDao;

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserbyId(id);
    }

    @Override
    @Transactional
    public List<User> getUsersByName(String name) {
        return userDao.getUsersByName(name);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }
}
