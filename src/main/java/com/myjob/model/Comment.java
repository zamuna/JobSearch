package com.myjob.model;

/**
 * Created by manozct on 5/20/2017.
 */
public class Comment {
    public Comment() {
    }

    private Integer commentid;
    private Integer userid;
    private Integer postid;
    private String comment;
    private String datecreated;
    private String dateupdated;

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
