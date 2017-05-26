package com.myjob.dao.impl;

import com.myjob.dao.DBconnection;
import com.myjob.dao.DbConstant;
import com.myjob.dao.LikeDao;
import com.myjob.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public class LikeDaoImpl implements LikeDao {
    public Integer getLikeStatus(Integer userId, Integer postId) {
        Statement stmt=null;
        String readQuery = "SELECT * from likes where " + DbConstant.LikeConstant.POST_ID + "=" + postId + " and "+DbConstant.LikeConstant.USER_ID +"=" + userId+";";
        try {
            stmt = DBconnection.getConnection().createStatement();
            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            if(rs.next()){
                return 1;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getNumberOfLikes(Integer postId) {
        Integer totalLikes = 0;
        Statement stmt = null;
        String readQuery = "SELECT COUNT(*) from likes where " + DbConstant.LikeConstant.POST_ID + "=" + postId + ";";
        try {
            stmt = DBconnection.getConnection().createStatement();
            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            Comment comment;
            while (rs.next()) {
                return rs.getInt(1);


            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalLikes;

    }

    public Boolean addLike(Integer userId, Integer postId) {
        java.util.Date today = new java.util.Date();
        String query = "INSERT INTO likes (" + DbConstant.LikeConstant.USER_ID + ", " + DbConstant.LikeConstant.POST_ID + " ," + DbConstant.LikeConstant.DATECREATED + ", " + DbConstant.LikeConstant.DATEUPDATED + ") " +
                "VALUES ('" + userId + "', '" + postId + "',  '" + new java.sql.Timestamp(today.getTime()) + "', '" + new java.sql.Timestamp(today.getTime()) + "' )";
        System.out.println(query);
        try {
            Statement stmt = DBconnection.getConnection().createStatement();

            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Boolean deleteLike(Integer userId, Integer postId) {
        String query = "DELETE FROM likes WHERE " + DbConstant.LikeConstant.USER_ID + "=" + userId + " AND " +
                DbConstant.LikeConstant.POST_ID + "=" + postId
                + ";";
        System.out.println(query);
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
    // check if user have already liked the post or not

    public static void main(String[] args) {
        LikeDaoImpl likeDao = new LikeDaoImpl();
         /*add is working*/
        likeDao.addLike(1, 5);

        /*delete is working*/
        likeDao.deleteLike(1, 2);

        /*getNumberOfLikes is working*/
        System.out.println(likeDao.getNumberOfLikes(2));
    }
}
