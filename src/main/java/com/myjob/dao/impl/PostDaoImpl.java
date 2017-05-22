package com.myjob.dao.impl;

import com.myjob.dao.DBconnection;
import com.myjob.dao.DbConstant;
import com.myjob.dao.PostDao;
import com.myjob.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public class PostDaoImpl implements PostDao {
    @Override
    public Post get(Integer postId) {
        return null;
    }


    @Override
    public List<Post> getAll(Integer postType, Integer limit, Integer offset) {
        List<Post> posts = new ArrayList<>();
        Statement stmt = null;
        String readQuery = "SELECT * from posts where " + DbConstant.PostConstant.POST_TYPE + "=" + postType + " ORDER BY " + DbConstant.PostConstant.DATECREATED + " DESC " + " limit " + limit + " offset " + offset + ";";
        System.out.println(readQuery);
        try {
            stmt = DBconnection.getConnection().createStatement();
            //System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            Post post;
            while (rs.next()) {
                post = new Post();
                post.setPostid(rs.getInt(DbConstant.PostConstant.POST_ID));
                post.setUserid(rs.getInt(DbConstant.PostConstant.USER_ID));
                post.setPost(rs.getString(DbConstant.PostConstant.POST));
                post.setPosttype(rs.getInt(DbConstant.PostConstant.POST_TYPE));
                post.setDatecreated(rs.getTimestamp(DbConstant.PostConstant.DATECREATED));
                post.setDateupdated(rs.getTimestamp(DbConstant.PostConstant.DATEUPDATED));

                posts.add(post);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;

    }


    @Override
    public Post add(Post post) {
        String query = "INSERT INTO posts (" + DbConstant.PostConstant.USER_ID + ", " + DbConstant.PostConstant.POST + "," + DbConstant.PostConstant.POST_TYPE + " ," + DbConstant.PostConstant.DATECREATED + ", " + DbConstant.PostConstant.DATEUPDATED + ") " +
                "VALUES ('" + post.getUserid() + "', '" + post.getPost() + "', '" + post.getPosttype() + "', '" + post.getDatecreated() + "', '" + post.getDateupdated() + "' )";
        //System.out.println(query);
        try {
            Statement stmt = DBconnection.getConnection().createStatement();

            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return post;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Post update(Integer postId, Post post) {
        return null;
    }

    @Override
    public Post delete(Integer postId) {
        return null;
    }

    @Override

    public Boolean deleteByUserId(Integer userId, Integer postId) {
        String query = "DELETE FROM posts WHERE " + DbConstant.PostConstant.USER_ID + "=" + userId + " AND " +
                DbConstant.PostConstant.POST_ID + "=" + postId
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

    /*for pagination use this logic*/

    /*public int[] calculateOffset(int pageNo) {
        int[] arr = new int[2];
        int limit = 25;
        int offset = 0;
        if (pageNo == 2) {
            limit = 15;
            offset = 25;
        }
        if (pageNo >= 3) {
            limit = 15;
            offset = 25 + (pageNo - 2) * 15;
        }
        arr[0] = limit;
        arr[1] = offset;
        return arr;
    }*/

    public static void main(String[] args) {
        PostDaoImpl postDao = new PostDaoImpl();
        java.util.Date today = new java.util.Date();
        /*add method is working*/
        postDao.add(new Post(2, "Hi this is me m from nepal. I am studying at MUM", 2, new java.sql.Timestamp(today.getTime()), new java.sql.Timestamp(today.getTime())));


        /*for pagination its working pass this offset and limit as parameter to getAll method*/

    /*  int[]a=  postDao.calculateOffset(3);
      int limit=a[0];
      int offset=a[1];
        System.out.println("limit:"+limit);
        System.out.println("offset:"+offset);*/

        for (Post p : postDao.getAll(2, 10, 5)) {
            System.out.println(p.getPostid() + " " + p.getPost());
        }

        /*deleteByUserId is working*/
        postDao.deleteByUserId(2, 1);

    }
}
