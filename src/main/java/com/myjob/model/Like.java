package com.myjob.model;

import java.sql.Timestamp;

/**
 * Created by manozct on 5/20/2017.
 */
public class Like {
    public Like() {
    }

    public Like(Integer userid, Integer postid, Timestamp datecreated, Timestamp dateupdated) {
        this.userid = userid;
        this.postid = postid;
        this.datecreated = datecreated;
        this.dateupdated = dateupdated;
    }

    private Integer likeid;
    private Integer userid;
    private Integer postid;
    private Timestamp datecreated;
    private Timestamp dateupdated;

    public Integer getLikeid() {
        return likeid;
    }

    public void setLikeid(Integer likeid) {
        this.likeid = likeid;
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
