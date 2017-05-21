package com.myjob.dao.impl;

import com.myjob.dao.DBconnection;
import com.myjob.dao.DbConstant;
import com.myjob.dao.UserDao;
import com.myjob.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manozct on 5/20/2017.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User get(Integer userId) {
        User user = null;
        Statement stmt = null;
        String readQuery = "SELECT * from users where " + DbConstant.UserConstant.USER_ID + "=" + userId + ";";
        try {
            stmt = DBconnection.getConnection().createStatement();
            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            user = new User();
            while (rs.next()) {
                user.setFullname(rs.getString(DbConstant.UserConstant.FULLNAME));
                user.setGender(rs.getInt(DbConstant.UserConstant.GENDER));
                user.setState(rs.getString(DbConstant.UserConstant.STATE));
                user.setCity(rs.getString(DbConstant.UserConstant.CITY));
                user.setStreet(rs.getString(DbConstant.UserConstant.STREET));
                user.setZipcode(rs.getInt(DbConstant.UserConstant.ZIP_CODE));
                user.setBirthyear(rs.getInt(DbConstant.UserConstant.BIRTHYEAR));
                user.setEmail(rs.getString(DbConstant.UserConstant.EMAIL));
                user.setPassword(rs.getString(DbConstant.UserConstant.PASSWORD));
                user.setDatecreated(rs.getTimestamp(DbConstant.UserConstant.DATECREATED));
                user.setDateupdated(rs.getTimestamp(DbConstant.UserConstant.DATEUPDATED));

            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Statement stmt = null;
        String readQuery = "SELECT * from users;";
        try {
            stmt = DBconnection.getConnection().createStatement();
           // System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            User user;
            while (rs.next()) {
                user = new User();
                user.setUserid(rs.getInt(DbConstant.UserConstant.USER_ID));
                user.setFullname(rs.getString(DbConstant.UserConstant.FULLNAME));
                user.setGender(rs.getInt(DbConstant.UserConstant.GENDER));
                user.setState(rs.getString(DbConstant.UserConstant.STATE));
                user.setCity(rs.getString(DbConstant.UserConstant.CITY));
                user.setStreet(rs.getString(DbConstant.UserConstant.STREET));
                user.setZipcode(rs.getInt(DbConstant.UserConstant.ZIP_CODE));
                user.setBirthyear(rs.getInt(DbConstant.UserConstant.BIRTHYEAR));
                user.setEmail(rs.getString(DbConstant.UserConstant.EMAIL));
                user.setPassword(rs.getString(DbConstant.UserConstant.PASSWORD));
                user.setDatecreated(rs.getTimestamp(DbConstant.UserConstant.DATECREATED));
                user.setDateupdated(rs.getTimestamp(DbConstant.UserConstant.DATEUPDATED));
                users.add(user);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User add(User user) {
       /* String query = "INSERT INTO users (
                DbConstant.UserConstant.FULLNAME ,
                DbConstant.UserConstant.GENDER,
                DbConstant.UserConstant.STATE ,
                DbConstant.UserConstant.CITY,
                DbConstant.UserConstant.STREET,
                DbConstant.UserConstant.ZIP_CODE,
                DbConstant.UserConstant.BIRTHYEAR,
                DbConstant.UserConstant.EMAIL,
                DbConstant.UserConstant.PASSWORD,
                DbConstant.UserConstant.DATECREATED,
                DbConstant.UserConstant.DATEUPDATED)" +
                "VALUES(user.getFullname(),user.getGender(),user.getState(),user.getCity(),user.getStreet(),user.getZipcode(),user.getBirthyear(),user.getEmail(),user.getPassword(),user.getDatecreated(),user.getDateupdated())";
*/

        String query = "INSERT INTO users (" + DbConstant.UserConstant.FULLNAME + ", " + DbConstant.UserConstant.GENDER + "," + DbConstant.UserConstant.STATE + " ," + DbConstant.UserConstant.CITY + ", " + DbConstant.UserConstant.STREET + "," + DbConstant.UserConstant.ZIP_CODE + "," + DbConstant.UserConstant.BIRTHYEAR + "," + DbConstant.UserConstant.EMAIL + "," + DbConstant.UserConstant.PASSWORD + "," + DbConstant.UserConstant.DATECREATED + " ," + DbConstant.UserConstant.DATEUPDATED + ") " +
                "VALUES ('" + user.getFullname() + "', '" + user.getGender() + "', '" + user.getState() + "', '" + user.getCity() + "', '" + user.getStreet() + "', '" + user.getZipcode() + "', '" + user.getBirthyear() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getDatecreated() + "', '" + user.getDateupdated() + "')";


        try {
            Statement stmt = DBconnection.getConnection().createStatement();
           // System.out.println("the query: " + query);
            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return user;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(Integer userId, User user) {
        //Incorrect query
        String query = "UPDATE users (" + DbConstant.UserConstant.FULLNAME + "', '" +
                DbConstant.UserConstant.GENDER + "', '" +
                DbConstant.UserConstant.CITY + "', '" +
                DbConstant.UserConstant.STATE + "', '" +
                DbConstant.UserConstant.STREET + "', )' VALUES ('" + user.getFullname();

        try {
            Statement stmt = DBconnection.getInstance().getConnection().createStatement();
            System.out.println("the query: " + query);
            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return user;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(Integer userId) {
        String query = "DELETE FROM users WHERE "+DbConstant.UserConstant.USER_ID+"=" + userId + ";";
        try {
            Statement stmt = DBconnection.getConnection().createStatement();
            //System.out.println("the query: " + query);
            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        java.util.Date today = new java.util.Date();
            /*add method is working*/
        //userDao.add(new User("manoj", 1, "iowa", "fairfield", "1000nth", 52557, 1992, "ct@gmail.com", "tharu", new java.sql.Timestamp(today.getTime()), new java.sql.Timestamp(today.getTime())));

        /*getAll method is working*/
       for(User u:userDao.getAll()){

           System.out.println( u.getFullname()+" "+u.getEmail());
       }


        /*get method is working*/
       User u=userDao.get(1);
        System.out.println(u.getFullname());
        System.out.println(u.getZipcode());
        System.out.println(u.getState());

        /*delete method is working*/
        userDao.delete(1);

    }
}
