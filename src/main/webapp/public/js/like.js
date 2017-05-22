/**
 * Created by Zamuna on 5/22/2017.
 */
$(function () {
    $("#commentId").hide();
    $("#btnComment").click(function(){
        $("#commentId").show();
    });
    var userName=$("#loggedUserName").val();
    // var userid = Integer.parseInt($(""));
    $("#btnLike").click(function () {
        // alert("hello");
        $.post("/likepost",{
            uname:"zamuna",
            postId:1,
            likeStatus:1
        }).done(function (data) {
            alert("My name is : "+data);
        });
    });



});