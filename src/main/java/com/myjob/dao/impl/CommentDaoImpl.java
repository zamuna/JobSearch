package com.myjob.dao.impl;

import com.myjob.dao.CommentDao;
import com.myjob.dao.DBconnection;
import com.myjob.dao.DbConstant;
import com.myjob.model.Comment;
import com.myjob.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public class CommentDaoImpl implements CommentDao {
    @Override
    public Comment get(Integer commentId) {
        return null;
    }

    @Override
    public List<Comment> getAll(Integer postId) {

        List<Comment> comments = new ArrayList<>();
        Statement stmt = null;
        String readQuery = "SELECT * from comments where " + DbConstant.CommentConstant.POST_ID + "=" + postId + ";";
        try {
            stmt = DBconnection.getConnection().createStatement();
            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            Comment comment;
            while (rs.next()) {
                comment = new Comment();
                comment.setCommentid(rs.getInt(DbConstant.CommentConstant.COMMENT_ID));
                comment.setUserid(rs.getInt(DbConstant.CommentConstant.USER_ID));
                comment.setPostid(rs.getInt(DbConstant.CommentConstant.POST_ID));
                comment.setComment(rs.getString(DbConstant.CommentConstant.COMMENT));
                comment.setDatecreated(rs.getTimestamp(DbConstant.CommentConstant.DATECREATED));
                comment.setDateupdated(rs.getTimestamp(DbConstant.CommentConstant.DATEUPDATED));


                comments.add(comment);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comment update(Integer commentId, Comment comment) {
        return null;
    }

    @Override
    public Comment delete(Integer commentId) {
        return null;
    }

    @Override
    public Comment add(Integer postId, Comment comment) {
        String query = "INSERT INTO comments (" + DbConstant.CommentConstant.USER_ID + ", " + DbConstant.CommentConstant.POST_ID + "," + DbConstant.CommentConstant.COMMENT + " ," + DbConstant.PostConstant.DATECREATED + ", " + DbConstant.PostConstant.DATEUPDATED + ") " +
                "VALUES ('" + comment.getUserid() + "', '" + comment.getPostid() + "', '" + comment.getComment() + "', '" + comment.getDatecreated() + "', '" + comment.getDateupdated() + "' )";
        System.out.println(query);
        try {
            Statement stmt = DBconnection.getConnection().createStatement();

            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return comment;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        CommentDaoImpl commentDao=new CommentDaoImpl();
        java.util.Date today = new java.util.Date();

        /*add metthod is working*/
       commentDao.add(2,new Comment(2,2,"Oh! really",new java.sql.Timestamp(today.getTime()),new java.sql.Timestamp(today.getTime())));

       /*getAll method is working*/
        for(Comment cm:commentDao.getAll(2)){
            System.out.println(cm.getComment());
        }

    }
}
