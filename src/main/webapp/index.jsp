<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Job Search Application</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="bootstrap/css/bootstrap.css"rel="stylesheet"/>
    <link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="public/css/main.css" rel="stylesheet"/>
    <link href="public/css/form.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <%--<script src="bootstrap/js/bootstrap.min.js"></script>--%>
  </head>
  <body class="main-body">
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Job Search</a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
          <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <main class="container">
    <section class="site-container padding-tb">

      <section class="card fadeInLeft">

        <h3 class=" glyphicon glyphicon-user" > Login Form</h3>

        <form action="#" class="form" method="post">
          <div class="form__wrapper" >
            <input type="email" class="form__input" id="email" name="email" required>
            <label class="form__label" for="email">
              <span class="form__label-content">Email</span>
            </label>
          </div>

          <div class="form__wrapper ">
            <input type="password" class="form__input" id="password"  name="password"
                   required pattern="(A-Z(a-z)[0,9],{,8})">
            <label class="form__label" for="password">
              <span class="form__label-content">Password</span>
            </label>

          </div>

          <div class="form__wrapper--submit">
            <div class="form__input-submit">
              <button type="submit" name="submit" class="btn btn-block btn-primary">Submit</button>
            </div>
          </div>
        </form>
        <div class="text-center text-small" ><a href="#forgotPswd" class="modal__toggle">Forgot Password ?</a></div>

        <div class="modal" id="forgotPswd">
          <a href="#" class="modal--close modal__toggle">&times;</a>

          <h3>Recover Password</h3>

          <p class="text-small">Your new password will be send to this email.</p>

          <form action="#" class="form2" method="post">
            <div class="form__wrapper">
              <input type="email" class="form__input" id="emaill" name="email">
              <label class="form__label" for="email">
                <span class="form__label-content">Email</span>
              </label>
            </div>

            <div class="form__wrapper--submit">
              <div class="form__input-submit">
                <button type="submit" name="submit" class="btn btn-block">Submit</button>
              </div>
            </div>

          </form>

        </div><!-- /modal -->

      </section><!-- /card -->

    </section>

  </main>
  </body>
</html>
