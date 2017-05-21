<%--
  Created by IntelliJ IDEA.
  User: Zamuna
  Date: 5/19/2017
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<main>
    <section class="site-container padding-tb">

        <section class="card wow fadeInLeft">

            <h3 class="wow fadeInDown glyphicon glyphicon-user" data-wow-delay="0.4s"> Login Form</h3>

            <form action="#" class="form" method="post">
                <div class="form__wrapper wow fadeInDown" data-wow-delay="0.5s">
                    <input type="text" class="form__input" id="fName" name="fName">
                    <label class="form__label" for="fName">
                        <span class="form__label-content">First Name</span>
                    </label>
                </div>
                <div class="form__wrapper wow fadeInDown" data-wow-delay="0.5s">
                    <input type="text" class="form__input" id="lName" name="lName">
                    <label class="form__label" for="lName">
                        <span class="form__label-content">Last Name</span>
                    </label>
                </div>
                <div class="form__wrapper checkbox-inline">
                    <input type="checkbox" class="" id="genderMale" name="gender"/>
                    <label class="form__label-content" for="genderMale">
                        <span class="form__label-content">Male</span>
                    </label>
                    <input type="checkbox" class="" id="genderFemale" name="gender"/>
                    <label class="form__label-content" for="genderFemale">
                        <span class="form__label-content">Male</span>
                    </label>
                </div>

                <div class="form__wrapper wow fadeInDown" data-wow-delay="0.6s">
                    <input type="password" class="form__input" id="password"  name="password">
                    <label class="form__label" for="password">
                        <span class="form__label-content">Password</span>
                    </label>

                </div>

                <div class="form__wrapper--submit wow fadeInLeft" data-wow-delay="0.7s">
                    <div class="form__input-submit">
                        <button type="submit" name="submit" class="btn btn-block">Submit</button>
                    </div>
                </div>
            </form>
            <div class="text-center text-small wow fadeInLeft" data-wow-delay="0.8s"><a href="#" class="modal__toggle">Forgot Password ?</a></div>


        </section><!-- /card -->

    </section>
</main>
</body>
</html>
