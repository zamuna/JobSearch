package com.myjob.dao.impl;

import com.myjob.dao.DBconnection;
import com.myjob.dao.DbConstant;
import com.myjob.dao.PostDao;
import com.myjob.model.Post;
import com.myjob.model.PostDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public class PostDaoImpl implements PostDao {


    public static void main(String[] args) {
        PostDaoImpl postDao = new PostDaoImpl();
        java.util.Date today = new java.util.Date();
        /*add method is working*/
      //  postDao.add(new Post(2, "Hi this is me m from nepal. I am studying at MUM", 2, new java.sql.Timestamp(today.getTime()), new java.sql.Timestamp(today.getTime())));


        /*for pagination its working pass this offset and limit as parameter to getAll method*/

    /*  int[]a=  postDao.calculateOffset(3);
      int limit=a[0];
      int offset=a[1];
        System.out.println("limit:"+limit);
        System.out.println("offset:"+offset);*/

        for (Post p : postDao.getAll(2, 10, 5)) {
            System.out.println(p.getPostid() + " " + p.getPost());
        }

        /*deleteByUserId is working*/
        postDao.deleteByUserId(2, 1);

    }

    @Override
    public Post get(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getAll(Integer postType, Integer limit, Integer offset) {

        List<Post> posts = new ArrayList<>();
        Statement stmt = null;
        String readQuery = "SELECT * from posts where " + DbConstant.PostConstant.POST_TYPE + "=" + postType + " ORDER BY " + DbConstant.PostConstant.DATECREATED + " DESC " + " limit " + limit + " offset " + offset + ";";
        System.out.println(readQuery);
        try {
            stmt = DBconnection.getConnection().createStatement();
            //System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            Post post;
            while (rs.next()) {
                post = new Post();
                post.setPostid(rs.getInt(DbConstant.PostConstant.POST_ID));
                post.setUserid(rs.getInt(DbConstant.PostConstant.USER_ID));
                post.setPost(rs.getString(DbConstant.PostConstant.POST));
                post.setPosttype(rs.getInt(DbConstant.PostConstant.POST_TYPE));
                post.setDatecreated(rs.getTimestamp(DbConstant.PostConstant.DATECREATED).toString());
                post.setDateupdated(rs.getTimestamp(DbConstant.PostConstant.DATEUPDATED).toString());

                posts.add(post);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;

    }

    @Override
    public Post add(Post post) {
        System.out.println(" add method called here post impl");
      /*  String query = "INSERT INTO posts (" + DbConstant.PostConstant.USER_ID + ", " + DbConstant.PostConstant.POST + "," + DbConstant.PostConstant.POST_TYPE + " ," + DbConstant.PostConstant.DATECREATED + ", " + DbConstant.PostConstant.DATEUPDATED + ") " +
                "VALUES ('" + post.getUserid() + "', '" + post.getPost() + "', '" + post.getPosttype() + "', '" + post.getDatecreated() + "', '" + post.getDateupdated() + "' )";

       */
      String query1 = "INSERT INTO posts (" + DbConstant.PostConstant.USER_ID + ", " + DbConstant.PostConstant.POST + "," + DbConstant.PostConstant.POST_TYPE + " ," + DbConstant.PostConstant.DATECREATED + ", " + DbConstant.PostConstant.DATEUPDATED + ") " +
                "VALUES (?,?,?,?,?)";

        try {
           // Statement stmt = DBconnection.getConnection().createStatement();
            PreparedStatement ps = DBconnection.getConnection().prepareStatement(query1);
            ps.setInt(1, post.getUserid());
            ps.setString(2, post.getPost());
            ps.setInt(3, post.getPosttype());
            ps.setString(4,  post.getDatecreated());
            ps.setString(5, post.getDateupdated() );
            Integer rowEffected = ps.executeUpdate();
            if (rowEffected > 0) {
                return getLastPost();
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

  private   Post getLastPost() {
        //   SELECT fields FROM table ORDER BY id DESC LIMIT 1;
        Post lastpost = null;
        Statement stmt = null;
        // Reading last Post Record
      //SELECT * from posts   order by dateupdated limit 1
        String readQuery = "SELECT * from posts ORDER BY " + DbConstant.PostConstant.DATEUPDATED+"  DESC LIMIT 1";
      System.out.println("Get last post query :"+readQuery);
        try {
            stmt = DBconnection.getConnection().createStatement();
            System.out.println("return last post query is: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            if (rs.next()) {
                lastpost = new Post();
                System.out.println("Last post found");
                lastpost.setUserid(rs.getInt(DbConstant.UserConstant.USER_ID));
                lastpost.setDateupdated(rs.getString(DbConstant.PostConstant.DATEUPDATED).toString());
                lastpost.setDatecreated(rs.getString((DbConstant.PostConstant.DATECREATED).toString()));
                lastpost.setPosttype(rs.getInt(DbConstant.PostConstant.POST_TYPE));
                lastpost.setPost(rs.getString(DbConstant.PostConstant.POST));
                lastpost.setPostid(rs.getInt(DbConstant.PostConstant.POST_ID));

            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastpost;

    }

    @Override
    public Post update(Integer postId, Post post) {
        return null;
    }

    @Override
    public boolean delete(Integer postId) {
        String query = "DELETE FROM posts WHERE " + DbConstant.PostConstant.POST_ID + "=" + postId + ";";
        System.out.println("The post delete query is ==> "+query);

        try {
            Statement stmt = DBconnection.getConnection().createStatement();
            //System.out.println("the query: " + query);
            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


@Override
public List<PostDetail> getAllPostDetails(Integer ptype,Integer uid,boolean isReadLastPostedRow)  {

    List<PostDetail> postDetailList = new ArrayList<>();
    String sql="";
    if(isReadLastPostedRow)
    {
        sql = "SELECT postid, posts.userid, post, posts.datecreated, fullname FROM posts INNER JOIN users ON posts.userid=users.userid  WHERE posts.posttype= "+ 1 +" ORDER BY posts.postid DESC limit 1 ";
    }else if(ptype==0)// means all post
    {
        sql = "SELECT postid, posts.userid, post, posts.datecreated, fullname FROM posts INNER JOIN users ON posts.userid=users.userid ORDER BY posts.postid DESC ";
    }
    else
    {
        sql = "SELECT postid, posts.userid, post, posts.datecreated, fullname FROM posts INNER JOIN users ON posts.userid=users.userid WHERE posts.posttype="+ ptype + " ORDER BY posts.postid DESC ";
    }

    System.out.println(" Query for getAllPostDetails ==>"+sql);
    try {
        PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {

            PostDetail post = new PostDetail();
            post.setPostid(resultSet.getInt("postid"));
            post.setPost(resultSet.getString("post"));
            post.setDatecreated(resultSet.getString("datecreated"));
            post.setPostedBy(resultSet.getString("fullname"));
            post.setUserid(resultSet.getInt("userid"));

            String sql2 = "SELECT COUNT(*) FROM likes WHERE postid=?";

            PreparedStatement ps2 = DBconnection.getConnection().prepareStatement(sql2);
            Integer postid = resultSet.getInt("postid");
            ps2.setInt(1, postid);

            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                post.setNoOfLikes(rs2.getInt(1));
            } else {
                post.setNoOfLikes(0);
            }

            // check if current record is liked by currently logged in user or not
            if (resultSet.getInt("userid") == uid) {
                post.setLikedByme(true);
            } else {
                post.setLikedByme(false);
            }

            postDetailList.add(post);
        }
    }catch (SQLException ex)
    {
        System.out.println("Error occur on database access !! "+ex.getMessage());
    }
    return postDetailList;

}



//*


    /*for pagination use this logic*/

    /*public int[] calculateOffset(int pageNo) {
        int[] arr = new int[2];
        int limit = 25;
        int offset = 0;
        if (pageNo == 2) {
            limit = 15;
            offset = 25;
        }
        if (pageNo >= 3) {
            limit = 15;
            offset = 25 + (pageNo - 2) * 15;
        }
        arr[0] = limit;
        arr[1] = offset;
        return arr;
    }*/

    @Override
    public Boolean deleteByUserId(Integer userId, Integer postId) {
        String query = "DELETE FROM posts WHERE " + DbConstant.PostConstant.USER_ID + "=" + userId + " AND " +
                DbConstant.PostConstant.POST_ID + "=" + postId
                + ";";
        System.out.println(query);
        try {
            Statement stmt = DBconnection.getConnection().createStatement();
            //System.out.println("the query: " + query);
            Integer rowEffected = stmt.executeUpdate(query);
            if (rowEffected > 0) {
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
