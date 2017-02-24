$(document).ready(function () {
    rep_refresh();
    //페이지 로딩후 댓글 가져오기

    $.ajax({ //페이지 로딩후 투표 현황 가져오기
        url: '/mf/getvote/' + mfKey,
        method: 'get'
    }).done(function (resultStr) {
        getVote(resultStr);
    });


    $('td.mf_vote_btn').click(function () {
        //영화 투표 기능
        var str = $(this).attr('id');
        var mKey = '';

        if (str == 'left_btn') {
            mKey = mKey1;
        } else if (str == 'right_btn') {
            mKey = mKey2;
        }
        $.ajax({
            url: '/mf/setvote/' + mfKey + "/" + mKey,
            method: 'get',
        }).done(function (resultStr) {
            //resultStr이 null이면 이미 투표. null이 아니면 투표함.
            if (resultStr == 'err') {
                alert('로그인 후 이용하여 주세요');
            } else if (resultStr == 'dateErr') {
                alert('투표기간이 지났습니다.');
            } else if (resultStr == '') {
                alert('이미 투표를 하셨습니다.');
            } else {
                getVote(resultStr);
                alert('투표하였습니다.');
            }

        });
    });

    $('div.mf_reWrite input').click(function () {
        //댓글 등록
        var repContent = $('textarea[name=repContent]').val().trim();

        if (repContent == '') {
            alert('내용을 입력하여 주세요.');
            return;
        }
        $.ajax({
            url: '/mf/reply/insert',
            method: 'post',
            data: {
                mfKey: mfKey,
                repContent: repContent
            }
        }).done(function (str) {
            var msg = '';
            if (str == 'insert') {
                msg = '댓글을 등록하였습니다';
            } else if (str == 'update') {
                msg = '댓글을 수정하였습니다.';
            } else if (str == 'err') {
                msg = '투표를 먼저 진행하여 주세요';
            }
            alert(msg);

            rep_refresh();
            //댓글 등록후 싹 밀고 새로 append
        })
    });

    $('div.BtnArea input[type=button]').click(function () {
        //리플 더보기 새창 기능
        var mKey = $(this).attr('id');

        var url = "/mf/reply/" + mfKey + "/" + mKey;
        window.open(url, "_blank", "fullscreen=no,location=no,menubar=no,toolbar=no,status=no,resizable=yes,width=850,height=500,left=520,top=100");
    })

});

function getVote(resultStr) {
    //투표 비율 & 투표 여부 갱신 메서드
    var voteData = JSON.parse(resultStr);
    var widthPer = 0;

    var totalvote = voteData.vote;
    var have = voteData.have;

    var jsonLength = Object.keys(totalvote).length;

    var total = 0;
    if (jsonLength == 0) {
        //아무도 투표하지 않음
        return;
    } else if (jsonLength > 0) {
        total = totalvote[0].count;

        if (jsonLength > 1) {
            //게시판 내에 2개이상 투표 되어있음.
            total += totalvote[1].count;
        }
    }

    var title = totalvote[0].count * 100 / total;
    var r_title = 100 - title;
    //투표 비율

    widthPer = title * 0.6;
    //양쪽 버튼 10% 제외,  투표 최소 영역 10% 제외 총 40%제외한 퍼센트
    widthPer += 10;
    //기본 영역 10% 추가

    var td = $('td.mf_vote_left');
    var r_td = $('td.mf_vote_right');
    if (totalvote[0].mKey == mKey2) {
        td = $('td.mf_vote_right');
        r_td = $('td.mf_vote_left');
    }


    $('td.mf_vote_left').css('width', 'auto');
    $('td.mf_vote_right').css('width', 'auto');


    td.css('width', widthPer + '%');
    td.attr('title', title + '%');
    r_td.attr('title', r_title + '%');

    if (!have) {
        $('.mf_reWrite textarea').removeAttr('disabled');
    }
}

function rep_refresh() {
    $('div.re_area').empty();
    //리플 전부 삭제.

    $.ajax({
        method: 'post',
        url: '/mf/content/reply/list',
        data: {
            mfKey: mfKey,
            mKey1: mKey1,
            mKey2: mKey2
        }
    }).done(function (msg) {
        var repList = JSON.parse(msg);
        //왼쪽 오른쪽 합친 리스트
        var repLeftObject = repList[0];
        var repRightObject = repList[1];
        //일반 댓글, 베스트댓글, 영화 키를 가진 Object

        var repList_left = repLeftObject.repList_left;
        var bestRep_left = repLeftObject.bestRep_left;

        var repList_right = repRightObject.repList_right;
        var bestRep_right = repRightObject.bestRep_right;

        //function으로 댓글 append하는 메서드 생성. 파라미터:mKey,댓글리스트,댓글 타입
        append_rep(mKey1, repList_left, 'normal');
        append_rep(mKey1, bestRep_left, 'best');

        append_rep(mKey2, repList_right, 'normal');
        append_rep(mKey2, bestRep_right, 'best');

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
            } else if(msg == 'self'){
                str = '본인 댓글은 추천이 불가능 합니다';
            }
            alert(str);

        });
    });
        
    });
}

function append_rep(mKey, list, sort) {
    var area = $('#re_' + mKey + ' div.re_area');
    var areaTest = area[0];
    var line = 6;
    //댓글은 최대 6개
    if (sort == 'best') {
        line = 3;
        //베스트 댓글은 최대 3개
    }

    $.each(list, function (index, value) {
        var html = '';

        html += '<div class="re_line">';
        html += '<div class="re_top">';
        html += '<div class="re_top_left">';
        if(sort == 'best'){
            html += '<span>BEST</span>'
            //best 이미지 표시
        }
        html += value.repName;
        
        html += '</div>';
        html += '<div class="re_top_middle">';
        html += value.repDate;
        html += '</div>';
        html += '<div class="re_top_right">';
        html += '<img src="/resources/images/movie/like-512.png">';
        html += '<div class="rep_good">';
        html += value.repGood;
        html += '</div>';
        html += '</div>';
        html += '<input type="hidden" value="';
        html += value.repKey;
        html += '">';
        html += '<div class="clear">';
        html += '</div>';
        html += '</div>';
        html += '<div class="re_middle">';
        html += value.repContent;
        html += '</div>';
        html += '</div>';

        area.append(html);

        var rep_count = $(area).children('div.re_line').length;
        //입력된 댓글 갯수 가져오기

        return (rep_count < line);
        //true면 반복 false는 빠져나오기
    });

}