package com.myperssonal.demo.service;

import java.util.List;

import com.myperssonal.demo.entity.User;

public interface UserService {
    public List<User> getUsers();

    public User getUserById(int id);

    public List<User> getUsersByName(String name);

    public void saveUser(User user);

    public void deleteUser(int userId);
}
