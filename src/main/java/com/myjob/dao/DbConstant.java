package com.myjob.dao;

import java.sql.Timestamp;

/**
 * Created by manozct on 5/20/2017.
 */
public class DbConstant {
    //User Db fields
    public static class UserConstant {
        public static final String USER_ID = "userid";
        public static final String FULLNAME = "fullname";
        public static final String GENDER = "gender";
        public static final String STATE = "state";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String ZIP_CODE = "zipcode";
        public static final String BIRTHYEAR = "birthyear";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String DATECREATED = "datecreated";
        public static final String DATEUPDATED = "dateupdated";

    }

    public static class PostConstant {
        public static final String POST_ID = "postid";
        public static final String USER_ID = "userid";
        public static final String POST = "post";
        public static final String POST_TYPE = "posttype";
        public static final String DATECREATED = "datecreated";
        public static final String DATEUPDATED = "dateupdated";
    }
    public  static class CommentConstant{
        public static final String COMMENT_ID="commentid";
        public static final String USER_ID="userid";
        public static final String POST_ID="postid";
        public static final String COMMENT="comment";
        public static final String DATECREATED="datecreated";
        public static final String DATEUPDATED="dateupdated";

    }
    public static class LikeConstant{
        public static final String LIKE_ID="likeid";
        public static final String USER_ID="userid";
        public static final String POST_ID="postid";
        public static final String DATECREATED="datecreated";
        public static final String DATEUPDATED="dateupdated";

    }



}
