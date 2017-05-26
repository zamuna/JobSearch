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
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("do Post in Comment COntroller");
        System.out.println(request.getParameter("commentText"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession loginSession = request.getSession();
        User logedInuser = (User) loginSession.getAttribute("loggedInUser");
        System.out.println(logedInuser);
        if (logedInuser != null) {

            if(request.getParameter("commentText")==null || request.getParameter("commentText")=="")
                return;

            System.out.println("logged in sucesss");
            Integer postId = Integer.parseInt(request.getParameter("postId")); // this post id is to be send from  javaScrip Ajax
            Integer userId = logedInuser.getUserid();
            // userId=1;
            System.out.println("text");
            System.out.println("postID" + postId);
            System.out.println("userId" + userId);
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
                    // System.out.println("JSonCOMMENT:"+jsonComment);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    // Adding json comment string in response
                    System.out.println("jsonComment "+jsonComment);
                    out.print(jsonComment);

                } else {
                    System.out.println("Comment add operation failed !!");
                }
            } catch (NullPointerException ex) {
                System.out.println(" null pointer ex on comment add: " + ex.getMessage());
            }

            //}

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession loginSession = request.getSession();
        User logedInuser = (User) loginSession.getAttribute("loggedInUser");
        System.out.println("login"+logedInuser);
        if (logedInuser != null) {
            System.out.println("test");
            Integer postId = Integer.parseInt(request.getParameter("postId"));
            System.out.println("post id" +postId);
            try {
                List<Comment> getAllComments=commentDao.getAll(postId);
              /*  for(Comment c:getAllComments){
                    System.out.println(c.getPostid()+" "+c.getComment());
                }*/
                //System.out.println(getAllComments);
                String jsonComment = new Gson().toJson(getAllComments);
                // System.out.println("JSonCOMMENT:"+jsonComment);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                System.out.println("JSONCOMMENT"+jsonComment);
                response.getWriter().write(jsonComment);
            }
            catch (NullPointerException ex){
                System.out.println("NUll pointer exception:"+ex.getMessage());
            }



        }
    }


}
