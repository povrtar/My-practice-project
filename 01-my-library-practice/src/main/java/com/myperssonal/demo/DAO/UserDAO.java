package com.myperssonal.demo.DAO;

import java.util.List;

import com.myperssonal.demo.entity.User;

public interface UserDAO {
    public User getUserbyId(int theId);

    public List<User> getUsers();

    public void saveUser(User user);

    public List<User> getUsersByName(String name);

    public void deleteUser(int userId);
}
