/**
 *  include/header script
 */

$(document).ready(function () {
    $(".right_menu ul").mouseenter(function () {
        /*회원 메뉴 슬라이드 다운 기능*/
        if (!$('.down_menu').is(':animated')) {
            $('.right_menu ul li').css('background', '#faf8f5');
            $('.right_menu ul li').css('border-left', '1px solid rgb(109, 121, 137)');
            $('.right_menu ul li').css('border-right', '1px solid rgb(109, 121, 137)');
            $('.down_menu').slideDown("fast");
        }
    });

    $(".right_menu ul").mouseleave(function () {
        /*회원 메뉴 슬라이드 업 기능*/
        $('.right_menu ul li').css('background', 'transparent');
        $('.down_menu').slideUp("fast");
    });

});