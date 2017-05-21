package com.myjob.controller;
import com.myjob.dao.UserDao;
import com.myjob.dao.impl.UserDaoImpl;
import com.myjob.model.User;
import org.omg.CORBA.INTERNAL;
/*import com.sun.java.util.jar.pack.Instruction;*/
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public class UserController extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        HttpSession loginSession = request.getSession();

        if (operation.equals("doSignIn")) {
            String useremail = request.getParameter("userEmail");
            String password = request.getParameter("userPassword");
            User newuser=tryLogin(useremail, password);
            if ((newuser) != null) {
                loginSession.setAttribute("loggedInUser", "newUser");
            }

        } else if (operation.equals("doSignout")) {
            request.getSession().invalidate();
            // after user signout : should go to login page
            request.getRequestDispatcher("login.jsp");
        } else if (operation.equals("doCreateUser")) {
            User user = getUserInfoFromClient(request);

            if (createNewUser(user) != null) {
                request.setAttribute("userCreated", "True");
                request.getRequestDispatcher("login.jsp");
            }
        } else if (operation.equals("doUpdateUser"))
        {
            User user = getUserInfoFromClient(request);
            if (createNewUser(user) != null) {
                request.setAttribute("userUpdated", "True");
                // request.getRequestDispatcher("login.jsp");
            }
        }
        else if(operation.equals("deleteUser"))
        {

            // this user id is of database id
            Integer userid= Integer.parseInt(loginSession.getAttribute("user_id").toString());
            // if user delete operation is successful clear the session and redirect user to new login secreen
            if(userDao.delete(userid))
            {
                request.setAttribute("userDeleted", "True");
                loginSession.invalidate();
                request.getRequestDispatcher("login.jsp");
            }

        }

    }

    //==================================================
    // Method to collect data from user client\
    // ==================================================

    private User getUserInfoFromClient(javax.servlet.http.HttpServletRequest request) {

        User user = new User();
        user.setFullname(request.getParameter("fullName"));
        user.setGender(Integer.parseInt(request.getParameter("gender")));
        user.setState(request.getParameter("state"));
        user.setCity(request.getParameter("city"));
        user.setStreet(request.getParameter("street"));
        user.setZipcode(Integer.parseInt(request.getParameter("zipCode")));
        user.setBirthyear(Integer.parseInt(request.getParameter("birthYear")));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        return user;

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

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
