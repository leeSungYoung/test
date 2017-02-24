var paObj;

function setThis(obj) {
    //팝업 오픈시 부모의 this저장
    paObj = obj;
}

$(document).ready(function () {
    opener.parent.setThis();

    $('input:button').click(function () {

        var mKey = $(this).nextAll("#mKey").val();
        //코드
        var mTitle = $(this).nextAll("#mTitle").val();
        //제목
        var poster = $(this).nextAll("#poster").val();

        $(paObj).parent('.mv_pos_btn').prevAll(".mv_pos_img").children("#preview").attr("src", poster);

        $(paObj).parent('.mv_pos_btn').nextAll("#mKey").val(mKey);

        window.close();
    });

})