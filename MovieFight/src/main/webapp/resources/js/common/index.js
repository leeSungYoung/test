$(document).ready(function () {
    $('.mf_roll_box').click(function () {
        //메인페이지 mf rolling 클릭시 해당 페이지로 이동
        var mfKey = $(this).find('input').val();
         location.href = '/mf/content/'+mfKey;
    });
});