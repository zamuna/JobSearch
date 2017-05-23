<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<!--nav starts-->
<%@include file="nav.jsp"%><%--
<nav id="navbar" class="navbar navbar-default navbar-static-top navbar-fixed-top">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                data-target="#navbar-container" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="navbar-container">
        <ul class="nav navbar-nav">
            <li id="home-menu" class="active"><a href="#"><span class="glyphicon glyphicon-home"></span>Home</a></li>
            <li id="profile-menu"><a href="#"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/Logout">Logout</a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
<!--nav ends-->--%>

<!--post section starts-->
<script src="public/js/home1.js"></script>
<section id="posts">
    <div class="container"><%--container starts--%>
        <div class="row">
            <header class="page-header col-sm-12">
                <h2>Lets help each other :</h2>
                <small>Get job soon</small>
            </header>
        </div>

        <!--row div starts-->
        <div id="update-status" class="row">
            <article class="col-md-12  col-centered article-container">
                <header class="clearfix">
                    <img class="img-responsive" src="http://loremflickr.com/50/50" alt="profile-picture"/>
                    <h1>Rabin Shrestha</h1>
                    <br>
                    <small>Share job post.</small>
                </header>
                <form id="update-status-form" class="clearfix" action="#" method="post">
                    <div class="form-group">
                        <%--<label for="update-status">Update Info</label>--%>
                        <textarea class="form-control" rows="5" id="update-status-textarea"
                                  name="update-status-textarea" placeholder="Post about the job post you know...."></textarea>
                    </div>
                    <button id="comment" type="submit" class="btn btn-primary">Post
                    </button>
                </form>
            </article>
        </div>

        <!--row div starts-->
        <c:forEach items="${posts}" var="post">

            <div class="row" xmlns:c="http://www.w3.org/1999/html">
                <article class="col-md-12  col-centered article-container">
                    <header class="clearfix">
                        <img class="img-responsive" src="http://lorempixel.com/75/75" alt="profile-picture"/>
                        <h1>${post.postedBy}
                            <br>
                            <small>Posted on: ${post.datecreated}</small>
                        </h1>
                        <p>${post.post}</p>
                    </header>


                    <footer>
                        <div class="btn-group" role="group">
                            <button type="button" value="${post.postid}" class="btn btn-link">
                                <span class="glyphicon glyphicon-heart-empty"></span>Like
                                <span class="badge">${post.noOfLikes}</span>
                            </button>

                            <button id="comment-btn1" type="button" value="${post.postid}" class="btn btn-link" >
                                <span class="glyphicon glyphicon-comment"></span> Comment <span class="badge">4
                                </span></button>
                        </div>
                    </footer>
                </article>
            </div>
        </c:forEach>
        <!--row div ends-->


    </div>
    <%--container ends--%>
</section>
<!--post section ends-->


<%--profile section starts--%>
<section id="profile" class="container">
    <div class="row">
        <header>
            <img class="img-responsive img-circle" src="http://lorempixel.com/150/150" alt="profile-picture"/>
            <h1>Zamuna Ghale</h1>
        </header>
    </div>

    <div id="profile-body" class="row"><%--div row starts--%>
        <%--my profile about starts--%>
        <aside class="col-md-5 col-sm-12" id="my-profile-content">
            <div class="panel panel-default panel panel-info">
                <div class="panel-heading clearfix">
                    <h3 class="panel-title">About</h3>
                    <!-- Button trigger modal -->
                    <button class="btn-link" data-toggle="modal" data-target="#edit-profile">Edit <span
                            class="glyphicon glyphicon-edit"></span></button>

                    <!-- Modal -->
                    <div class="modal fade" id="edit-profile" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Username</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" id="edit-Profile-form">
                                        <div class="form-group">
                                            <label for="edit-fullname" class="col-sm-2 control-label">Fullname</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="edit-fullname"
                                                       placeholder="Fullname" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="edit-email" class="col-sm-2 control-label">Email</label>
                                            <div class="col-sm-10">
                                                <input type="email" class="form-control" id="edit-email"
                                                       placeholder="Email" disabled>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="edit-password" class="col-sm-2 control-label">Password</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="form-control" id="edit-password"
                                                       placeholder="Password" disabled>
                                            </div>
                                        </div>

                                        <div class="form-group form-inline">
                                            <label for="edit-state" class="col-sm-2 control-label">State</label>
                                            <div class="col-sm-10">
                                                <select id="edit-state" class="form-control" tabindex="6"
                                                        id="register-state"
                                                        name="register-state">
                                                    <option value="AL">Alabama</option>
                                                    <option value="AK">Alaska</option>
                                                    <option value="AZ">Arizona</option>
                                                    <option value="AR">Arkansas</option>
                                                    <option value="CA">California</option>
                                                    <option value="CO">Colorado</option>
                                                    <option value="CT">Connecticut</option>
                                                    <option value="DE">Delaware</option>
                                                    <option value="DC">District Of Columbia</option>
                                                    <option value="FL">Florida</option>
                                                    <option value="GA">Georgia</option>
                                                    <option value="HI">Hawaii</option>
                                                    <option value="ID">Idaho</option>
                                                    <option value="IL">Illinois</option>
                                                    <option value="IN">Indiana</option>
                                                    <option value="IA">Iowa</option>
                                                    <option value="KS">Kansas</option>
                                                    <option value="KY">Kentucky</option>
                                                    <option value="LA">Louisiana</option>
                                                    <option value="ME">Maine</option>
                                                    <option value="MD">Maryland</option>
                                                    <option value="MA">Massachusetts</option>
                                                    <option value="MI">Michigan</option>
                                                    <option value="MN">Minnesota</option>
                                                    <option value="MS">Mississippi</option>
                                                    <option value="MO">Missouri</option>
                                                    <option value="MT">Montana</option>
                                                    <option value="NE">Nebraska</option>
                                                    <option value="NV">Nevada</option>
                                                    <option value="NH">New Hampshire</option>
                                                    <option value="NJ">New Jersey</option>
                                                    <option value="NM">New Mexico</option>
                                                    <option value="NY">New York</option>
                                                    <option value="NC">North Carolina</option>
                                                    <option value="ND">North Dakota</option>
                                                    <option value="OH">Ohio</option>
                                                    <option value="OK">Oklahoma</option>
                                                    <option value="OR">Oregon</option>
                                                    <option value="PA">Pennsylvania</option>
                                                    <option value="RI">Rhode Island</option>
                                                    <option value="SC">South Carolina</option>
                                                    <option value="SD">South Dakota</option>
                                                    <option value="TN">Tennessee</option>
                                                    <option value="TX">Texas</option>
                                                    <option value="UT">Utah</option>
                                                    <option value="VT">Vermont</option>
                                                    <option value="VA">Virginia</option>
                                                    <option value="WA">Washington</option>
                                                    <option value="WV">West Virginia</option>
                                                    <option value="WI">Wisconsin</option>
                                                    <option value="WY">Wyoming</option>
                                                </select>
                                                <input type="text" name="register-city" id="edit-city" tabindex="7"
                                                       class="form-control" placeholder="City" value="">
                                                <input type="text" name="register-zip" id="edit-zip" tabindex="8"
                                                       class="form-control" placeholder="Zip" value=""
                                                       pattern="[0-9]{5}" title="Zip Code should be 5 digits" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="edit-dob" class="col-sm-2 control-label">Birth Year</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="edit-dob"
                                                       placeholder="1995" pattern="[0-9]{4}"
                                                       title="Enter you 4 digit birth year" required>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary" form="edit-Profile-form">Save
                                        changes
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <p class="list-group-item">Full Name: ${loggedInUser.fullName}</p>
                        <p class="list-group-item">Username: ${loggedInUser.username}</p>
                        <p class="list-group-item">Email-Address: ${loggedInUser.email}</p>
                        <p class="list-group-item">
                            Address: ${loggedInUser.state}, ${loggedInUser.city}, ${loggedInUser.street}</p>
                        <p class="list-group-item">Zip Code: ${loggedInUser.zipcode}</p>
                        <p class="list-group-item">Birth Year: ${loggedInUser.birthyear}</p>
                        <p class="list-group-item">Gender: ${loggedInUser.gender}</p>
                    </div>
                </div>
            </div>
        </aside>
        <%--my profile about ends--%>


        <%--my post content article starts--%>
        <article class="col-md-7 col-sm-12" id="my-post-content">
            <!--row div starts-->
            <article class="article-container">
                <header class="clearfix">
                    <img class="img-responsive" src="http://lorempixel.com/75/75" alt="profile-picture"/>
                    <h1>Rabin Shrestha</h1>
                    <div class="dropdown">
                        <button class="btn btn-link dropdown-toggle" type="button" id="post-edit-menu"
                                data-toggle="dropdown"  area-has-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="post-edit-menu">
                            <li>Delete</li>
                        </ul>
                    </div>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has
                        been
                        the industry's standard dummy text ever since the 1500s. Lorem Ipsum is simply dummy text of
                        the
                        printing and typesetting industry. Lorem Ipsum has been
                        the industry's standard dummy text ever since the 1500s</p>
                </header>
                <footer>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-link"><span
                                class="glyphicon glyphicon-heart-empty"></span>
                            Like <span class="badge">4</span></button>
                        <button id="comment-btn3" type="button" class="btn btn-link"><span
                                class="glyphicon glyphicon-comment"></span> Comment <span class="badge">4</span>
                        </button>
                    </div>
                </footer>

            </article>
            <!--row div ends-->

        </article>
        <%--my post content article starts--%>
    </div>
    <%--div row ends--%>

</section>
<%--profile section ends--%>


<footer class="container-fluid">

</footer>
<%@include file="footer.jsp"%>