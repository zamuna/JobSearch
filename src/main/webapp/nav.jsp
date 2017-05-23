<%--<c:if test="#{loggedInUser ne null}">--%>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">MUM JOB PORTAL</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                     <li><a href="/UserController">Profile</a></li>
                    <li><a href="/UserController" name="doSignOut">LogOut</a></li>
                    <%--<c:if test="loggedInUser  null">--%>
                        <li><a href="#">${loggedInUser.fullname}</a></li>
                    <img class="img-responsive" src="http://pipsum.com/70x70.jpg" alt="profile-picture" style="border-radius: 5px; padding: 2px; "/>
                    <%--</c:if>--%>

                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
<%--</c:if>--%>
