package com.myjob.dao;

import com.myjob.model.Comment;

import java.util.List;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public interface CommentDao {
    Comment get(Integer commentId);
    List<Comment> getAll(Integer postId);
    Comment update(Integer commentId,Comment comment);
    Comment delete(Integer commentId);
    Comment add(Integer postId,Comment comment);
}
