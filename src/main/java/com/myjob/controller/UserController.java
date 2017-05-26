package com.myjob.controller;

import com.myjob.dao.UserDao;
import com.myjob.dao.impl.UserDaoImpl;
import com.myjob.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("operation");
        HttpSession loginSession = request.getSession();
        // handle signin request
        if (request.getParameter("doSignIn") != null) {
            System.out.println(" sign in request cought here !!");
            String useremail = request.getParameter("userEmail");
            String password = request.getParameter("userPassword");

            request.setAttribute("hiddenStatusOfOperationResult","hidden");

            User newuser = tryLogin(useremail, password);
            if ((newuser) != null) {
                loginSession.setAttribute("loggedInUser", newuser);

                System.out.println(" user is logged in");
                // after successful login , perform load all post in page and then forward to home page
                request.setAttribute("LoadAllPost",true);
                request.getRequestDispatcher("PostController").forward(request,response);
            }else
            {
                System.out.println(" user invalid !!");
                request.setAttribute("operationResult","User Credentials didn't match !!");
                request.setAttribute("hiddenStatusOfOperationResult","");

                request.getRequestDispatcher("index.jsp").forward(request,response);
            }

        }// Sign out operation
        else if (request.getParameter("doSignOut") != null) {
            request.getSession().invalidate();
            System.out.println(" user signed out");
            // after user signout : should go to login page
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }// sign up operation
        else if (request.getParameter("doSignUp") != null)
        {
            User user = getUserInfoFromClient(request);
            Integer birthYear=Integer.parseInt(request.getParameter("birthYear"));
            if (LocalDate.now().getYear() - birthYear < 18) {
                request.setAttribute("operationResult","Invalid age");
                request.setAttribute("userCreated", null);
                request.getRequestDispatcher("index.jsp").forward(request,response); // index page signIn page for here
            }

            else if (createNewUser(user) != null) {
                request.setAttribute("userCreated", "True");
                System.out.println("New user created");
                request.setAttribute("operationResult","New User Created");

                request.getRequestDispatcher("home.jsp").forward(request,response); // index page signIn page for here
            }
        } // user profile change operation
        else if (request.getParameter("doUpdateUser") != null)
        {
            User user = getUserInfoFromClient(request);
            if (createNewUser(user) != null) {
                System.out.println("user updated success");
                request.setAttribute("userUpdated", "True");
                request.getRequestDispatcher("home.jsp").forward(request,response);
            }
        }// delete user
        else if (request.getParameter("deleteUser") != null)
        {   // this user id is of database id
            Integer userid = Integer.parseInt(loginSession.getAttribute("user_id").toString());
            // if user delete operation is successful clear the session and redirect user to new login secreen
            if (userDao.delete(userid)) {
                System.out.println("user deleted");
                request.setAttribute("userDeleted", "True");
                loginSession.invalidate();
                request.getRequestDispatcher("index.jsp");
            }

        }

    }

    //==================================================
    // Method to collect data from user client\
    // ==================================================

    private User getUserInfoFromClient(javax.servlet.http.HttpServletRequest request) {


        User user = new User();
        try {
            user.setFullname(request.getParameter("fullName"));
            System.out.println(request.getParameter("gender"));
            user.setGender(Integer.parseInt(request.getParameter("gender")));
            user.setState(request.getParameter("state"));
            user.setCity(request.getParameter("city"));
            user.setStreet(request.getParameter("street"));
            user.setZipcode(Integer.parseInt(request.getParameter("zipCode")));
            user.setBirthyear(Integer.parseInt(request.getParameter("birthYear")));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setDatecreated(new Timestamp(System.currentTimeMillis()));
            user.setDateupdated(new Timestamp(System.currentTimeMillis()));
        } catch (NumberFormatException ex) {
            System.out.println(" exception occur : " + ex);
        }
        return user;

    }

    protected void doGet(javax.servlet.http.HttpServletRequest reqauest, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
doPost(reqauest,response);
    }

    /**
     * Check if username : or userEmail already exist of or not
     *
     * @param useremail
     * @return true if user email is not exists on database
     */
    private boolean isUserExist(String useremail) {   // Each user must have different email address,  so if email found , means there exist user that have same email address that the user have requested
        return (userDao.getUserByEmail(useremail) != null);

    }

    /**
     * @param user is container object to hold data information for new user creation
     * @return User object that have been inserted in database as new user
     */
    //===========================================================================================
    // Just for creation of new User
    // If user email already used, Cant be created new user if not then only add user to database
    //returns true if userEmail already exist
    //=========================================================================================
    private User createNewUser(User user) {
        if (user.getEmail() == null || user.getEmail() == "") return null;
        if (isUserExist(user.getEmail()))
            return null; //  if Email address is already used you cant add user
        return (userDao.add(user));// returning null means adding operation failed and returning some value means user is added successfully

    }

    /**
     * This method will try to logIn to the system usign userEmail and Password
     *
     * @param userEmail
     * @param userPassword
     * @return appropriate user object if login Process sucess, otherwise returns Null
     */
    private User tryLogin(String userEmail, String userPassword) {
        if (userEmail == "" || userPassword == "" || userEmail == null || userPassword == null) return null;
        return (userDao.login(userEmail, userPassword));
    }

    /**
     * proifile request to userDao
     *
     * @param userid
     * @param user
     * @return the updated user information if success, else return null
     */
    private User tryUpdate(Integer userid, User user) {
        if (user == null || userid == null) return null;
        else return (userDao.update(userid, user));
    }

}
