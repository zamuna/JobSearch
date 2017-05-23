<%--
  Created by IntelliJ IDEA.
  User: Zamuna
  Date: 5/21/2017
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%@include file="nav.jsp"%>
<%
    if(request.getSession().getAttribute("loggedInUser")==null)
    {
response.sendRedirect("index.jsp");
    }

%>
<main class="container jobDetailsContainer">
    <section class="post_panel">
        <div class = "panel panel-default">
            <div class = "panel-heading">
                <h3 class = "panel-title">Panel title</h3>
            </div>

            <div class = "panel-body">

                <form action="#" method="post">
                    <div class="form-group ">
                        <textarea name="txtPost" rows="4" cols="80" placeholder="Type your post here"></textarea>

                    </div>
                    <button type="submit" name="addPost" class="btn btn-primary btn-sm">Submit</button>
                </form>
            </div>
        </div>
    </section>
    <section id="asideRight">
        <div id="div_job_offering">
            <h2>Job Offering</h2>
            <article class="post">
                <div class="media col-md-12">
                    <div class="media-body">
                        <h4 class="media-heading">Name Here</h4>
                        <input type="hidden" value="${1}" id="postIdView">
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                        reprehenderit in voluptate velit esse cillum dolore eu fugiat
                        nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                        sunt in culpa qui officia deserunt mollit anim id est laborum."
                    </div>
                </div>
            </article>
            <div class="btn_like">
                <button class="btn btn-sm" id="btnLike"><span class = "glyphicon  glyphicon-thumbs-up"> Like </span>
                    <span class="badge" id="badgeId"></span> </button>
                <button class="btn btn-sm" id="btnComment"><span class = "glyphicon  glyphicon-edit"> Comment</span></button>
            </div>

            <hr/>
            <section class="comment_here" id="commentId">
                <form class="form-inline">
                    <input type="hidden" name="postId" value=${'#postId'}>
                    <div class="form-group">
                        <%--<label for="comment" class="sr-only">Password</label>--%>
                        <textarea class="form-control col-lg-8" rows="2" cols="70" name="txtComment" placeholder="Comment Here"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Post Comment</button>
                </form>
                <article class="load_comments col-md-12">
                    <div class="media">
                        <div class="media-body">
                            <h4 class="media-heading">MY Name</h4>
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                            reprehenderit in voluptate velit esse cillum dolore eu fugiat
                            nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                            sunt in culpa qui officia deserunt mollit anim id est laborum."
                        </div>
                    </div>
                </article>
            </section>




        </div>

        <section id="div_job_souting">
            <h2>Job Souting</h2>


            <p>
                <b>Project Manager</b>
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum."
            </p>


            <p>
                <b>Java EE developer</b>
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum."
            </p>


        </section>
    </section>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Weather Map</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="modalMap col-sm-6 fromMap">
                            <h5>From Location </h5>
                            <div id="fromMap"></div>
                        </div>
                        <div class="modalMap col-sm-6 toMap">
                            <h5>To Location </h5>
                            <div id="toMap"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp"%>
