package com.myjob.controller;

import com.google.gson.Gson;
import com.myjob.dao.impl.PostDaoImpl;
import com.myjob.model.Post;
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
 * Created by Rabin Shrestha on 5/21/2017.
 */
@WebServlet(name = "PostController")
public class PostController extends HttpServlet {
    PostDaoImpl postDao;

    @Override
    public void init() throws ServletException {
        super.init();
        postDao = new PostDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("operation");
        HttpSession loginSession = request.getSession();
        User logedInuser = (User) loginSession.getAttribute("loggedInUser");
        if (logedInuser != null) {
            if (request.getParameter("listAllPost")!=null) {
                // if job post Type is 1 : display all job post offering
                // if type=2 : display all job post seeking
                Integer postType =  (request.getParameter("btnLoadAllJobSeeking") != null )? 2: 1;
                writeAllPostListIntoGsson(postType, response);
                return;

            }
            else if (request.getParameter("addPost")!=null)
            {   // calling the function to add new post
                addNewPost(logedInuser.getUserid(),request);
                return;
            }
            else if (request.getParameter("updatePost")!=null)
            {   // calling the function to add new post
                Integer postId=Integer.parseInt(request.getParameter("postId"));
                updatePost(logedInuser.getUserid(),request);
                return;
            }
            else if (request.getParameter("deletePost")!=null)
            {   // calling the function to delete existing post
                Integer postId=Integer.parseInt(request.getParameter("postId"));
                updatePost(logedInuser.getUserid(),request);
                return;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    //=====================================================
    // Write all postlist data in to response in Gson string and returning
    // the response will be returned to the calling web page, automatically
    //=====================================================
    void writeAllPostListIntoGsson(Integer postType, HttpServletResponse response) {

        List<Post> listOfPosts = postDao.getAll(postType);

        // creating Gsoon file from listOfPost
        String listOfPostsGson = new Gson().toJson(listOfPosts);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // writing json data as response
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(listOfPostsGson);

        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();

    }

    //=====================================================
    // adding the new post in to the server database
    // if success, returnd added Post, else return null
    //=====================================================
    Post addNewPost(Integer userId, HttpServletRequest request)
    {
        Post newPost = new Post();

        newPost.setUserid(userId);
        newPost.setPost(request.getParameter("postDescription"));
        Integer postType =  (request.getParameter("postType") != null & request.getParameter("postType").equals("Seeking"))? 2: 1;
        newPost.setPosttype(postType);
        // current system time is job post created time
        newPost.setDatecreated(new Timestamp(System.currentTimeMillis()));
        newPost.setDateupdated(new Timestamp(System.currentTimeMillis()));
      Post newpost=postDao.add(newPost);
        if (newpost!= null)
        {
            System.out.println(" New post added successfully !!");
            request.setAttribute("Operation_Status", "Post added successfully");
            request.setAttribute("newPost", newpost);

            return newPost;
        } else {
            System.out.println(" Error, Cant adde new post !!");
            request.setAttribute("Operation_Status", "Post failed");
        }
        return null;

    }
    //=====================================================
    // updating the existing  post in to the server database
    // if success, returnd updated Post, else return null
    //=====================================================
    Post updatePost(Integer postId,HttpServletRequest request)
    {
        Post newPost = new Post();
        newPost.setPost(request.getParameter("postDescription"));
        Integer postType =  (request.getParameter("postType") != null & request.getParameter("postType").equals("Seeking"))? 2: 1;
        newPost.setPosttype(postType);
        // To Do
        newPost.setDatecreated(null);// in PostDaoImpl you should not set DateCreated column
        // current system time is job post updated time
        newPost.setDateupdated(new Timestamp(System.currentTimeMillis()));
       Post updatedPost=postDao.update(postId,newPost);
        if (updatedPost!= null)
        {
            System.out.println("  post Updated successfully !!");
            request.setAttribute("Operation_Status", "Post updated successfully");
            request.setAttribute("updatedPost",updatedPost);
            return newPost;
        } else {
            System.out.println(" Error, Cant update post !!");
            request.setAttribute("Operation_Status", "Post update failed");
        }
        return null;

    }
    //=====================================================
    // deliting the existing  post in to the server database
    // if success, returnd deleted Post, else return null
    //=====================================================
    Boolean deletePost(Integer postId,HttpServletRequest request)
    {

        if (postDao.delete(postId) != null)
        {
            System.out.println("  post deleted successfully !!");
            request.setAttribute("Operation_Status", "Post deleted successfully");
            return true;
        } else {
            System.out.println(" Error, Cant delete post !!");
            request.setAttribute("Operation_Status", "Post delete failed");
        }
        return false;

    }
}
