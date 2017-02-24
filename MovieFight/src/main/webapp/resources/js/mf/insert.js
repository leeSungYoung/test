/**
 *  Movie Fight insert
 */

var chObj;
var paObj;

$(document).ready(function () {

    if ($('#mfVDate').val() == '') {
        //투표 날짜 자동 입력 메서드
        var today = new Date();
        var tomorrow = new Date(today.valueOf() + (7 * 24 * 60 * 60 * 1000));

        $('#mfVDate').val(tomorrow.toLocaleDateString());
    }

    /*팝업 띄우기*/
    $('input:button').click(function (e) {
        var url = "/mf/insert/search";
        chObj = window.open(url, "_blank", "fullscreen=no,location=no,menubar=no,toolbar=no,status=no,resizable=yes,width=850,height=500,left=570,top=150");
        paObj = this;


    });

    $('form#mf_form').submit(function () {
        
        /*영화1 선택 여부*/
        var m1 = $('input[name="mKey1"]').val().trim();
        if (m1 == '') {
            alert('영화를 선택 해주세요.');
            $('input[name="mKey1"]').focus();
            return false;
        }

        /*영화2 선택 여부*/
        var m2 = $('input[name="mKey2"]').val().trim();
        if (m2 == '') {
            alert('영화를 선택 해주세요.');
            $('input[name="mKey2"]').focus();
            return false;
        }

        /*제목 입력 여부*/
        var t = $('input#mfTitle').val().trim();
        if (t == '') {
            alert('제목을 입력해주세요');
            $('input#mfTitle').focus();
            return false;
        }

    });


});

function setThis() {
    //팝업 오픈시 this위치 저장
    chObj.setThis(paObj);
}