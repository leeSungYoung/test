$(document).ready(function () {

    $('div.mf_reList div.re_top_right').click(function () {
        //리플 좋아요 기능
        var rep = this;
        var repKey = $(rep).next('input:hidden').val();
        $.ajax({
            url: '/mf/good',
            method: 'post',
            data: {
                repKey: repKey
            }
        }).done(function (msg) {
            var str = '';
            if (msg == 'err') {
                str = '로그인 후 이용해 주세요.';
            } else if (msg == 'insert') {
                var good = $(rep).children('.rep_good').text();
                good++;
                $(rep).children('.rep_good').text(good);
                str = '추천 하였습니다.';
            } else if (msg == 'have') {
                str = '이미 추천한 댓글입니다.';
            } else if( msg=='self'){
                str = '본인 댓글은 추천이 불가능 합니다';
            }
            alert(str);

        });
    });

});
