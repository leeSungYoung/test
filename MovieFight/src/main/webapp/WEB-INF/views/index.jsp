<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="./include/header.jsp"%>
        <link rel="stylesheet" href="/resources/css/common/index.css">
        <script src="/resources/js/common/index.js"></script>
        <section class="content_body">
            <article>

                <!--mf 최신글 5개-->
                <div class="index_top">
                    <div class="mf_roll">
                        <link href="/resources/css/movie/fotorama.css" rel="stylesheet">
                        <script src="/resources/js/movie/fotorama.js"></script>

                        <div class="fotorama" data-click="false" data-minwidth="940" data-minheight="400" data-maxwidth="940" data-maxheight="400" data-transition="slide" data-autoplay="3000" data-loop="true" data-swipe="false" data-stopautoplayontouch="false">

                            <c:forEach items="${mf_list}" var="mf" varStatus="status">
                                <div class="mf_roll_box" onclick="location.href = '/mf/content/${mf.mfKey}';">
                                    <img src="${mf.left_pos}">
                                    <div class="mf_vs">VS</div>
                                    <img src="${mf.right_pos}">
                                </div>
                            </c:forEach>
                        </div>
                        <style type="text/css">
                            .fotorame__html {
                                position: relative !important;
                            }
                            
                            .fotorame__html div {
                                height: 100%
                            }
                            
                            .fotorame__html a {
                                z-index: 1;
                                display: block;
                                height: 100%
                            }
                        </style>

                    </div>
                </div>
                <!--mf끝-->
                <!--상영작 top10-->
                <div class="index_middle">

                    <div class="movie_line elem">
                        <c:forEach items="${runningMovieList}" var="runningMovieList" varStatus="status" begin="0" step="1">

                            <ul class="movie_table elem">
                                <li class="movie_list elem">

                                    <a href="/movie?mKey=${runningMovieList.movieCd}">
                                        <span class="movie_img elem">
											<img class="running_poster" src="/resources/upload/poster/poster_${runningMovieList.movieCd}"
											alt="${movieNm}" onError="this.src='/resources/images/common/no_image_found.jpg'"
 											style="display: block;" />
										</span>
                                    </a>

                                    <div class="movie_desc elem">
                                        <span>
											<a href="/movie?mKey=${runningMovieList.movieCd}" class="movie_title">${runningMovieList.movieNm}</a>
										</span>
                                        <dl>
                                            <dt class="tit_t1">개요</dt>
                                            <dd class="fontedit">
                                                <c:forEach items="${runningMovieList.genres}" var="genres" end="2">${genres.genreNm}</c:forEach>
                                            </dd>
                                            <span class="split">|</span>
                                            <dt style="display: none;">런타임</dt>
                                            <dd class="fontedit">${runningMovieList.showTm}분</dd>
                                            <span class="split">|</span>
                                            <dt style="display: none;">개봉일</dt>
                                            <dd class="fontedit">${runningMovieList.openDt}</dd>
                                            <br>
                                            <dt class="tit_t2">감독</dt>
                                            <dd class="fontedit">
                                                <c:forEach items="${runningMovieList.directors}" var="directors" end="3">${directors.peopleNm} </c:forEach>
                                            </dd>
                                            <br>
                                            <dt class="tit_t3">출연</dt>
                                            <dd class="fontedit">
                                                <c:forEach items="${runningMovieList.actors}" var="actors" end="2">${actors.peopleNm} </c:forEach>
                                            </dd>

                                        </dl>
                                    </div>

                                </li>
                            </ul>

                        </c:forEach>
                    </div>
                </div>
                <!--상영작 끝-->
            </article>
        </section>
        <%@ include file="./include/footer.jsp"%>