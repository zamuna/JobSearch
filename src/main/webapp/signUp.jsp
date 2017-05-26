<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<main class="container-fluid">
    <section class="sign_up card">
        <h3 class=" glyphicon glyphicon-user" > SignUp</h3>
        <form class="form" method="post" action="UserController" autocomplete="on">
            <div class="form-group col-lg-12 col-xs-12 has-success input-lg">
                    <input type="text" class="form-control" name="fullName" id="fullNameId" placeholder="Full Name"/>
            </div>
            <div class="col-lg-12 col-xs-12 has-success">
            <label class="checkbox-inline input-lg">

                <input type="radio" name="gender" id="inlineCheckbox1" value="0" checked> Male
            </label>
                <label class="checkbox-inline input-lg">

                    <input type="radio" name="gender" id="inlineCheckbox2" value="1"> Female
                </label>
            </div>
                <select name="state" class="form-control input-lg col-lg-10 col-xs-12 input-group-sm">
                    <option value="" disabled selected>Select State</option>
                    <option value="AL">AL</option>
                    <option value="AK">AK</option>
                    <option value="AZ">AZ</option>
                    <option value="AR">AR</option>
                    <option value="CA">CA</option>
                    <option value="CO">CO</option>
                    <option value="CT">CT</option>
                    <option value="DE">DE</option>
                    <option value="DC">DC</option>
                    <option value="FL">FL</option>
                    <option value="GA">GA</option>
                    <option value="HI">HI</option>
                    <option value="ID">ID</option>
                    <option value="IL">IL</option>
                    <option value="IN">IN</option>
                    <option value="IA">IA</option>
                    <option value="KS">KS</option>
                    <option value="KY">KY</option>
                    <option value="LA">LA</option>
                    <option value="ME">ME</option>
                    <option value="MD">MD</option>
                    <option value="MA">MA</option>
                    <option value="MI">MI</option>
                    <option value="MN">MN</option>
                    <option value="MS">MS</option>
                    <option value="MO">MO</option>
                    <option value="MT">MT</option>
                    <option value="NE">NE</option>
                    <option value="NV">NV</option>
                    <option value="NH">NH</option>
                    <option value="NJ">NJ</option>
                    <option value="NM">NM</option>
                    <option value="NY">NY</option>
                    <option value="NC">NC</option>
                    <option value="ND">ND</option>
                    <option value="OH">OH</option>
                    <option value="OK">OK</option>
                    <option value="OR">OR</option>
                    <option value="PA">PA</option>
                    <option value="RI">RI</option>
                    <option value="SC">SC</option>
                    <option value="SD">SD</option>
                    <option value="TN">TN</option>
                    <option value="TX">TX</option>
                    <option value="UT">UT</option>
                    <option value="VT">VT</option>
                    <option value="VA">VA</option>
                    <option value="WA">WA</option>
                    <option value="WV">WV</option>
                    <option value="WI">WI</option>
                    <option value="WY">WY</option>
                </select>
            <div class="form-group col-lg-12 col-xs-12 has-success input-lg">
                <input type="text" class="form-control" name="city" id="cityId" placeholder="City"/>
            </div>
            <div class="form-group col-lg-12 col-xs-12 has-success input-lg">
                <input type="text" class="form-control" name="street" id="stretId" placeholder="Street"/>
            </div>
            <div class="form-group col-lg-12 col-xs-12 has-success">
                <input type="number" class="form-control" name="zipCode" id="zipCodeId" pattern="[0-9]{5}" placeholder="Zip Code"/>
            </div>
            <div class="form-group col-lg-12 col-xs-12 has-success ">
                <input type="number" class="form-control" name="birthYear" id="birthYearId" pattern="[0-9]{5}" placeholder="Birth Year" required/>
            </div>
            <div class="form-group col-lg-12 col-xs-12 has-success input-lg">
                <input type="email" class="form-control" name="email" id="emailId" placeholder="Email" required  />
            </div>
            <div class="form-group col-lg-12 col-xs-12 has-success">
                <input type="password" class="form-control" name="password" id="passwordId" placeholder="Password" pattern="(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\d\W])|(?=.*\W)(?=.*\d))|(?=.*\W)(?=.*[A-Z])(?=.*\d)).{6,}" required
                title="Password must be atleast 6 characters and must have atleast a number and any characters from A-Z and a-z"/>
            </div>
            <div class="form__wrapper--submit">
                <div class="form__input-submit">
                    <button type="submit" name="doSignUp" class="btn btn-block btn-primary">Sign Up </button>
                </div>
            </div>

        </form>
    </section>
</main>
<%@include file="footer.jsp"%>
