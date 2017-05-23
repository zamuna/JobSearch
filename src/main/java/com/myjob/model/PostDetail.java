package com.myjob.model;

import com.myjob.dao.LikeDao;
import com.myjob.dao.PostDao;
import com.myjob.dao.UserDao;
import com.myjob.dao.impl.LikeDaoImpl;
import com.myjob.dao.impl.UserDaoImpl;

import java.sql.Timestamp;

/**
 * Created by manozct on 5/20/2017.
 */
public class PostDetail {
    private Integer postid;
    private String post;
    private Integer userid;
    private Integer posttype;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    private String PostedBy;
    private Integer noOfLikes;
    private String datecreated;
    private String dateupdated;
    private boolean isLikedByme=false;

    public String getPostedBy() {
        return PostedBy;
    }

    public void setPostedBy(String postedBy) {
        PostedBy = postedBy;
    }

    public boolean isLikedByme() {
        return isLikedByme;
    }

    public void setLikedByme(boolean likedByme) {
        isLikedByme = likedByme;
    }

    public Integer getNoOfLikes() {
        return noOfLikes;

    }

    public void setNoOfLikes(Integer noOfLikes) {
        this.noOfLikes = noOfLikes;
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

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
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


}
