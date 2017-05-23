package com.myjob.controller;

import com.myjob.dao.LikeDao;
import com.myjob.dao.impl.LikeDaoImpl;
import com.myjob.model.Like;
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
@WebServlet(name = "LikeController")
public class LikeController extends HttpServlet {
    LikeDao likeDao;

    @Override
    public void init() throws ServletException {
        super.init();
        likeDao = new LikeDaoImpl();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession loginSession = request.getSession();
        System.out.println("Servlet entered");
        User logedInuser = (User) loginSession.getAttribute("loggedInUser");
        if (logedInuser != null) {
            if (request.getParameter("uname") != null) {
                Integer postId = Integer.parseInt(request.getParameter("postId")); // this post id is to be send from  javaScrip Ajax
                Integer userId = logedInuser.getUserid();
                System.out.println(postId+"===="+userId);

               /* Like newLike = new Like();
                newLike.setPostid(postId);
                newLike.setUserid(userId);
                newLike.setDatecreated(new Timestamp(System.currentTimeMillis()));
                newLike.setDateupdated(new Timestamp(System.currentTimeMillis()));*/
                try {
                    // check if the post is already liked or not
                    //if not, add new like record on database and send response 1
                    // if already liked, delete record from database and response 0
                    Integer likeStatus=likeDao.getLikeStatus(userId,postId);
                    System.out.println("From DAO: "+likeStatus);
                    String[] arr=new String[2];

                    if (likeStatus == 1) {
                        if (likeDao.deleteLike(userId, postId) != null) {
                            System.out.println("Now write");
                            arr[0]="0";
                            //response.getWriter().write("0"); // response 0 means likeStatus= 0
                            System.out.println("Like deleted");
                        }
                    }
                    else {
                        System.out.println("Status is 1");
                        if (likeDao.addLike(userId, postId) != null) {
                            arr[0]="1";
                            System.out.println("like done");
                        }
                    }
                    arr[1]=likeDao.getNumberOfLikes(postId).toString();
                    response.getWriter().write(arr[0]);
                    response.getWriter().write(arr[1]);
                    System.out.println("Response data is: "+arr[0]);
                    System.out.println("total like is ==>"+arr[1]);
                }
                catch (NullPointerException ex) {
                    System.out.println(" null pointer ex on comment add: " + ex.getMessage());
                }

            }

        }else
        {
            //user not logged in :
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
