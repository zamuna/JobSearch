/**
 * Created by Rabin Shrestha on 5/22/2017.
 */
//update post
$(function () {


    $("#comment").click(function (e) {
        e.preventDefault();

        $.post("/CommentController", {"post": $("#txtPostContent").val()}).done(function (response) {
            if (response.status === "success") {
                let post = JSON.parse(response.post);
                $("#txtPostContent").val("");

                const content = `<div class="row">
                    <article class="col-md-12  col-centered article-container">
                        <header class="clearfix">
                            <img class="img-responsive" src="http://lorempixel.com/75/75" alt="profile-picture"/>
                            <h1>${post.postedby}
                                <br>
                                <small>Posted on: ${post.datecreated}</small>
                            </h1>
                            <p>${post.postcontent}</p>
                        </header>
                        
                        <footer>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-heart-empty"></span>Like <span class="badge">${post.totallikes}</span></button>
                                <button id="comment-btn1" type="button" class="btn btn-link"><span class="glyphicon glyphicon-comment"></span> Comment <span class="badge">4</span></button>
                            </div>
                        </footer>
                    </article>
                    </div>`;
                $(content).insertAfter($("#update-status")).delay( 100 ).fadeIn(200);

            } else {
                alert(" :[")
            }
        });
    });





});
