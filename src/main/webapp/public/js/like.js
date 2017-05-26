/**
 * Created by Zamuna on 5/22/2017.
 */
$(function () {
    $("#commentId").hide();

});
function likehandler(postid) {
    // alert(postid);
    $.post("/likepost",{
        postId:postid
    }).done(changeLikeCss)
}
function changeLikeCss(responseData) {
    console.log("Status of islike" + responseData[0]);
    console.log("total no of like" + responseData[1]);
    var postId=responseData[2];
    var data=JSON.parse(responseData);
    console.log("New Data: "+data);
    // var badge=$(button).find("span.badge").text();

    if (responseData[0] == 1){
        $("#"+postId).addClass("btn-primary");
        $("#"+postId).removeClass("btn-link");
        $("#badgeId").html(data[1]);
        // $(button).find("span.badge").text((parseInt(badge))+1);

    }

    else{
        $("#"+postId).addClass("btn-link");
        $("#"+postId).removeClass("btn-primary");
        $("#badgeId").html(data[1]);
        // $(button).find("span.badge").text((parseInt(badge))+1);
    }


    // $("totalLikeNo").val(responseData[1]);


}