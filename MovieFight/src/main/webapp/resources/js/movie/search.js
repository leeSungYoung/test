$(document).ready(function () {
    $('input:button').click(function () {
        //팝업에서 영화 선택시 부모창에 데이터 전달
        var movieCd = $(this).nextAll("#movieCd").val();
        //코드
        var movieNm = $(this).nextAll("#movieNm").val();
        //제목
        var openDt = $(this).nextAll("#openDt").val();
        //개봉연도
        var repNationNm = $(this).nextAll("#repNationNm").val();
        //제작국가
        var repGenreNm = $(this).nextAll("#repGenreNm").val();
        //장르
        var directors = $(this).nextAll("#directors").val();
        //감독
        var companys = $(this).nextAll("#companys").val();
        //제작사
        $('input#mKey', opener.document).val(movieCd);
        $('input#mTitle', opener.document).val(movieNm);
        $('span#openDt', opener.document).text(openDt);
        $('span#repNationNm', opener.document).text(repNationNm);
        $('span#repGenreNm', opener.document).text(repGenreNm);
        $('span#directors', opener.document).text(directors);
        $('span#companys', opener.document).text(companys);

        window.close();

    });

})