package com.myjob.service;

import com.google.gson.Gson;
import com.myjob.dao.PostDao;
import com.myjob.dao.impl.PostDaoImpl;
import com.myjob.model.PostDetail;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rabin Shrestha on 5/23/2017.
 */
public class PostService {
    PostDaoImpl postDao;

    public PostService(PostDaoImpl postDao) {
        this.postDao = postDao;
    }
     /*public HashMap<String, String> getAllPostDetails(Integer ptype,Integer uid,boolean isReadLastPostedRow){
         String postDetailGson=getAllPostDetailsGson(ptype, uid,false,response); // the json response listOfPostsGson will be forwarded
         HashMap<String, String> jsonData = new HashMap<String, String>();
         jsonData.put("post",postDetailGson);
         jsonData.put("Status","success");
         return jsonData;
     }*/

    //=====================================================
    // Write all postlist data in to response in Gson string and returning
    // the response will be returned to the calling web page, automatically
    //=====================================================
    String getAllPostDetailsGson(Integer postType,Integer userid,boolean isReadLastPostRow, HttpServletResponse response) {

        List<PostDetail> listOfPostdetails =postDao.getAllPostDetails(postType,userid,isReadLastPostRow);
        // creating Gsoon file from listOfPost
        String listOfPostsDetailsGson = new Gson().toJson(listOfPostdetails);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return listOfPostsDetailsGson;
    }
}