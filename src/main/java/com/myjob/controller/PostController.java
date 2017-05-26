package com.myjob.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myjob.dao.impl.PostDaoImpl;
import com.myjob.model.Post;
import com.myjob.model.PostDetail;
import com.myjob.model.User;
import javafx.geometry.Pos;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Post controller callded !!");
        HttpSession loginSession = request.getSession();
        User logedInuser = (User) loginSession.getAttribute("loggedInUser");
        System.out.println("addPost status:"+request.getParameter("addPost"));
        System.out.println(" Status of LoadAllPost :"+request.getParameter("LoadAllPost"));
        if (logedInuser == null)
        {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
           // Handle Add new post
            if (request.getParameter("addPost")!=null  )
            {
                // calling the function to add new post
                System.out.println("Add new post request accepted");
                String postText=request.getParameter("postContent");

                HashMap<String, String> jsonData = new HashMap<String, String>();

                String addedLastRow = addNewPost(logedInuser.getUserid(),postText,request,response);

                jsonData.put("post",addedLastRow);
                jsonData.put("Status","success");
                System.out.println("Gson data ==========>\n"+new Gson().toJson(jsonData));
                PrintWriter out = response.getWriter();
                out.write(new Gson().toJson(jsonData));
                out.close();
                return;
                //request.getRequestDispatcher("home.jsp").forward(request,response);
            }
            // updating existing Post case :
            else if (request.getParameter("updatePost")!=null)
            {   // calling the function to add new post
                System.out.println("Update post request accepted");
                Integer postId=Integer.parseInt(request.getParameter("postId"));
                updatePost(logedInuser.getUserid(),request);
                request.getRequestDispatcher("home.jsp").forward(request,response);
                return;
            }
            // Deleting existing post
            else if (request.getParameter("deletePost")!=null)
            {   // calling the function to delete existing post
                System.out.println("Delete post request accepted");
                Integer postId=Integer.parseInt(request.getParameter("postId"));
                updatePost(logedInuser.getUserid(),request);
                return;
            }// LOad all Post
            else if ((boolean)request.getAttribute("LoadAllPost"))
            {
                // if job post Type is 1 : display all job post offering
                // if type=2 : display all job post seeking
                System.out.println("Load ALL post request caught");
                Integer postType =  (request.getParameter("LoadSeekingJob") != null )? 2: 1;
                List<PostDetail> pd= getAllPostDetailsGson(postType, logedInuser.getUserid(),false,response); // the json response listOfPostsGson will be forwarded

                //HashMap<String, String> jsonData = new HashMap<String, String>();
               // jsonData.put("post",postDetailGson);
                //jsonData.put("Status","success");

                // writing json data as response
               /* PrintWriter out = null;
                try {
                    out = response.getWriter();
                    out.write(new Gson().toJson(jsonData));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.close();*/
                //Type listType = new TypeToken<ArrayList<PostDetail>>(){}.getType();

                //List<PostDetail> postDetailList = new Gson().fromJson(postDetailGson, listType);

               // System.out.println("Post detail before :"+postDetailGson);
                //System.out.println("Post detail after parsing :"+new Gson().toJson(postDetailGson));

                //JSON.parse(jsonData);
                System.out.println("Before Parsing :"+pd);
                request.setAttribute("allPosts",pd);
                System.out.println(" All Post detail"+new Gson().toJson(pd));
                System.out.println("Loading all post on page");
                request.getRequestDispatcher("home.jsp").forward(request,response);
                return;

            }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        doPost(request,response);


    }

    //=====================================================
    // Write all postlist data in to response in Gson string and returning
    // the response will be returned to the calling web page, automatically
    //=====================================================
    List<PostDetail> getAllPostDetailsGson(Integer postType,Integer userid,boolean isReadLastPostRow, HttpServletResponse response) {

        List<PostDetail> listOfPostdetails =postDao.getAllPostDetails(postType,userid,isReadLastPostRow);
        // creating Gsoon file from listOfPost
        String listOfPostsDetailsGson = new Gson().toJson(listOfPostdetails);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return listOfPostdetails;


    }

    //=====================================================
    // Write all postlist data in to response in Gson string and returning
    // the response will be returned to the calling web page, automatically
    //=====================================================
    void getLastPostDetailsGson(Integer postType,Integer userid, HttpServletResponse response) {

        List<PostDetail> listOfPostdetails =postDao.getAllPostDetails(postType,userid,false);
        // creating Gsoon file from listOfPost
        String listOfPostsDetailsGson = new Gson().toJson(listOfPostdetails);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // writing json data as response
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(listOfPostsDetailsGson);

        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();

    }


    //=====================================================
    // adding the new post in to the server database
    // if success, returnd added Post, else return null
    //=====================================================
    String addNewPost(Integer userId,String postContent, HttpServletRequest request,HttpServletResponse response)
    {
        Post newPost = null;
        System.out.println(" add new post metthod called on servlet");
        try {
            System.out.println(""+userId+postContent+new Timestamp(System.currentTimeMillis()).toString());
            newPost=new Post();
            newPost.setUserid(userId);
            newPost.setPost(postContent);
           // Integer postType = (request.getParameter("PostType") != null & request.getParameter("postType").equals("Seeking")) ? 2 : 1;
            newPost.setPosttype(1);
            // current system time is job post created time
            newPost.setDatecreated(new Timestamp(System.currentTimeMillis()).toString());
            newPost.setDateupdated(new Timestamp(System.currentTimeMillis()).toString());

            System.out.println(""+userId+postContent+new Timestamp(System.currentTimeMillis()).toString());

            Post addedPost = postDao.add(newPost);

            if (addedPost != null) {
                //get last post
                System.out.println("post is added ");
                List<PostDetail> lastPostDetail = postDao.getAllPostDetails(1, userId, true);
                if (lastPostDetail != null) {

                    String addedPostDetailsGson = new Gson().toJson(lastPostDetail.get(0));
                    System.out.println(" New post added successfully !!");
                    // if success
                    return addedPostDetailsGson;
                }


            } else {
                System.out.println(" Error, Cant added new post !!");

            }
        }
        catch (Exception e)
        {
            System.out.println(" exception occur post controller : "+e.getStackTrace()+e.getMessage());
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
        //newPost.setDatecreated(null);// in PostDaoImpl you should not set DateCreated column
        // current system time is job post updated time
        newPost.setDateupdated(new Timestamp(System.currentTimeMillis()).toString());
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
    // if success, returned deleted Post, else return null
    //=====================================================
    Boolean deletePost(Integer postId,HttpServletRequest request)
    {

        if (postDao.delete(postId) ==true)
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
