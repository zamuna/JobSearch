package com.myjob.dao;

import com.myjob.model.User;

import java.util.List;

/**
 * Created by manozct on 5/20/2017.
 */
public interface UserDao {
    User get(Integer userId);
    List<User> getAll();
    User add(User user);
    User update(Integer userId, User user);
    Boolean delete(Integer userId);
    User login(String userEmail,String pass);
    User getUserByEmail(String useremail);
    User getLastUser();
}
