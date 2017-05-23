package com.myjob.model;

import java.sql.Timestamp;

/**
 * Created by manozct on 5/20/2017.
 */
public class Post {
    public Post() {
    }

    public Post(Integer userid, String post, Integer posttype, String  datecreated, String dateupdated) {

        this.userid = userid;
        this.post = post;
        this.posttype = posttype;
        this.datecreated = datecreated;
        this.dateupdated = dateupdated;
    }

    private Integer postid;
    private Integer userid;
    private String post;
    private Integer posttype;
    private String datecreated;
    private String dateupdated;

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getPosttype() {
        return posttype;
    }

    public void setPosttype(Integer posttype) {
        this.posttype = posttype;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(String dateupdated) {
        this.dateupdated = dateupdated;
    }
}
