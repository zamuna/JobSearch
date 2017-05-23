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
            postId:2
        }).done(changeLikeCss);
    });
function changeLikeCss(responseData) {

    console.log("Status of islike" + responseData[0]);
    console.log("total no of like" + responseData[1]);

    if (responseData[0] == 1)
        $("#btnLike").addClass("btn-primary");
    else
        $("#btnLike").removeClass("btn-primary");

    // $("totalLikeNo").val(responseData[1]);
    $("#badgeId").html(responseData[1]);

}


});