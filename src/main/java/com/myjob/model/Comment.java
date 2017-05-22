package com.myjob.model;

import java.sql.Timestamp;

/**
 * Created by manozct on 5/20/2017.
 */
public class Comment {
    public Comment() {
    }

    public Comment(Integer userid, Integer postid, String comment, Timestamp datecreated, Timestamp dateupdated) {
        this.userid = userid;
        this.postid = postid;
        this.comment = comment;
        this.datecreated = datecreated;
        this.dateupdated = dateupdated;
    }

    private Integer commentid;
    private Integer userid;
    private Integer postid;
    private String comment;
    private Timestamp datecreated;
    private Timestamp dateupdated;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public Timestamp getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(Timestamp dateupdated) {
        this.dateupdated = dateupdated;
    }
}
