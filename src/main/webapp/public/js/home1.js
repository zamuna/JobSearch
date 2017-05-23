/**
 * Created by Rabin Shrestha on 5/23/2017.
 */

$(document).ready(function () {




    $("#profile-menu").click(function (e) {
        $('#profile').css('display', 'block');
        $("#profile").delay(100).fadeIn(100);
        $("#posts").fadeOut(100);
        $('#navbar-container li').removeClass("active");
        $(this).addClass("active");

        e.preventDefault();
    });

    $("#home-menu").click(function (e) {
        $("#posts").delay(100).fadeIn(100);
        $("#profile").fadeOut(100);
        $('#navbar-container li').removeClass("active");
        $(this).addClass("active");
        e.preventDefault();
    });

    $("#my-post").click(function (e) {
        $("#my-post-content").delay(100).fadeIn(100);
        $("#my-profile-content").fadeOut(100);
        $('#my-profile').removeClass("active");
        $(this).addClass("active");
        e.preventDefault();
    });

    $("#my-profile").click(function (e) {
        $('#my-post-content').css('display', 'block');
        $("#my-profile-content").delay(100).fadeIn(100);
        $("#my-post-content").fadeOut(100);
        $('#my-post').removeClass("active");
        $(this).addClass("active");
        e.preventDefault();
    });

    $("#comment-btn").click(function () {
        $("#comments").toggle();
    });

    $("#postMenuDropDown").click(function () {
        $("#postMenu").toggle();
    });

    //update post
    $("#comment").click(function (e) {
        e.preventDefault();

        $.post("/PostController", {"postContent": $("#update-status-textarea").val(),"addPost":"TRUE","PostType":1}).done(function (response) {

          alert(response.Status);

            if (response.Status === "success") {
                alert("data is =>"+response.post)
                let post = JSON.parse(response.post);

                $("#update-status-textarea").val("");

                const content = `<div class="row">
                    <article class="col-md-12  col-centered article-container">
                        <header class="clearfix">
                            <img class="img-responsive" src="http://lorempixel.com/75/75" alt="profile-picture"/>
                            <h1>${post.PostedBy}
                                <br>
                                <small>Posted on: ${post.datecreated}</small>
                            </h1>
                            <p>${post.post}</p>
                        </header>
                        
                        <footer>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-heart-empty"></span>Like <span class="badge">${post.noOfLikes} is liked $("isLikedByme")</span></button>
                                <button id="comment-btn1" type="button" class="btn btn-link"><span class="glyphicon glyphicon-comment"></span> Comment <span class="badge">4</span></button>
                            </div>
                        </footer>
                    </article>
                    </div>`;
                alert(content);
                $(content).prepend($("#update-status-form")).delay( 100 ).fadeIn(200);

            } else {
                alert(" :[ Something went wrong")
            }
        });
    });
});
