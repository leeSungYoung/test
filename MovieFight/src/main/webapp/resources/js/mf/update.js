/**
 *  Movie Fight update
 */

$(document).ready(function () {

    $('form#mf_form').submit(function () {
        
        /*제목 입력 여부*/
        var t = $('input#mfTitle').val().trim();
        if (t == '') {
            alert('제목을 입력해주세요');
            $('input#mfTitle').focus();
            return false;
        }

    });


});