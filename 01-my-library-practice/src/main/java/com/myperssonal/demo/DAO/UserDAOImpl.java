package com.myperssonal.demo.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myperssonal.demo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserbyId(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        User theUser = currentSession.get(User.class, theId);
        return theUser;
    }

    @Override
    public List<User> getUsers() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User order by name", User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }

    @Override
    public void saveUser(User theUser) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theUser);
    }

    @Override
    public List<User> getUsersByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User order by name", User.class);
        List<User> users = theQuery.getResultList();
        List<User> usersByName = new ArrayList<>();
        for (User user : users) {
            if ((name.equals(user.getName()))) {
                usersByName.add(user);
            }
        }
        return usersByName;
    }

    @Override
    public void deleteUser(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from User where id=:userId");
        theQuery.setParameter("userId", theId);
        theQuery.executeUpdate();
    }
}
