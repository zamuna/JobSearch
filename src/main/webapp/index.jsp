<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%@include file="nav.jsp"%>
  <main class="container">
    <section class="site-container padding-tb">

      <section class="card fadeInLeft">

        <h3 class=" glyphicon glyphicon-user" > Login Form</h3>

        <form action="UserController" class="form" method="post">
          <div class="form__wrapper" >
            <input type="email" class="form__input" id="email" name="userEmail" required>
            <label class="form__label" for="email">
              <span class="form__label-content">Email</span>
            </label>
          </div>

          <div class="form__wrapper ">
            <input type="password" class="form__input" id="password"  name="userPassword"
                   required>
            <label class="form__label" for="password">
              <span class="form__label-content">Password</span>
            </label>

          </div>

          <div class="form__wrapper--submit">
            <div class="form__input-submit">
              <button type="submit" name="doSignIn" class="btn btn-block btn-primary">Submit</button>
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
<%@include file="footer.jsp"%>
