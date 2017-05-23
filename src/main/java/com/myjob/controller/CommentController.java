package com.myjob.controller;

import com.google.gson.Gson;
import com.myjob.dao.CommentDao;
import com.myjob.dao.impl.CommentDaoImpl;
import com.myjob.model.Comment;
import com.myjob.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by Rabin Shrestha on 5/22/2017.
 */
@WebServlet(name = "CommentController")
public class CommentController extends HttpServlet {
    CommentDao commentDao;

    @Override
    public void init() throws ServletException {
        super.init();
        commentDao = new CommentDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession loginSession = request.getSession();
        User logedInuser = (User) loginSession.getAttribute("loggedInUser");
        if (logedInuser != null) {
            if (request.getParameter("addComment")!=null) {
                Integer postId = Integer.parseInt(request.getParameter("postId")); // this post id is to be send from  javaScrip Ajax
                Integer userId = logedInuser.getUserid();
                String commentText = request.getParameter("commentText");

                Comment comment = new Comment();
                comment.setPostid(postId);
                comment.setUserid(userId);
                comment.setComment(commentText);
                comment.setDatecreated(new Timestamp(System.currentTimeMillis()));
                comment.setDateupdated(new Timestamp(System.currentTimeMillis()));
                try {
                    Comment addedComment = commentDao.add(postId, comment);
                    if (addedComment != null) {
                        System.out.println("comment added successfully");
                        System.out.println("added comment is :" + addedComment);
                        String jsonComment = new Gson().toJson(addedComment);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        // Adding json comment string in response
                        response.getWriter().write(jsonComment);
                    } else {
                        System.out.println("Comment add operation failed !!");
                    }
                } catch (NullPointerException ex) {
                    System.out.println(" null pointer ex on comment add: " + ex.getMessage());
                }

            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


}
