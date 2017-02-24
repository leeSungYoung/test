<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<section class="content_body">
	<article>

		<head>

<script src="/resources/js/common/jquery-3.1.1.min.js">
	
</script>

<style type="text/css">
@IMPORT url("/resources/css/movie/boxoffice.css");
</style>

		</head>

		<body>
			<div id="container">
				<div id="main">
					<div class="movie_headline elem">BOXOFFICE<img src="/resources/images/movie/question-mark.png" title="영화 진흥 위원회 제공 서비스로 실시간 발권데이터를 전일기준까지 반영한 통계 정보입니다."></div>

					<div class="movie_line elem">

						<c:forEach items="${runningMovieList}" var="runningMovieList"
							varStatus="status" begin="0" step="1">
							<ul class="movie_table elem">
								<li class="movie_list elem">
                                    <div class="movie_rank">${runningMovieList.rank}</div>
									<a href="/movie?mKey=${runningMovieList.movieCd}">
										<span class="movie_img elem">
											<img class="running_poster" src="/resources/upload/poster/poster_${runningMovieList.movieCd}"
												alt="${movieNm}"
												onError="this.src='/resources/images/common/no_image_found.jpg'"
												style="display: block;" />
										</span>
									</a>

									<div class="movie_desc elem">
										<span>
											<a href="/movie?mKey=${runningMovieList.movieCd}"
												class="movie_title">${runningMovieList.movieNm}</a>
										</span>
										<dl>
											<dt class="tit_t1">개요</dt>
											<dd class="fontedit">
												<c:forEach items="${runningMovieList.genres}" var="genres"
													end="2">${genres.genreNm}</c:forEach>
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
												<c:forEach items="${runningMovieList.directors}"
													var="directors" end="3">${directors.peopleNm} </c:forEach>
											</dd>
											<br>
											<dt class="tit_t3">출연</dt>
											<dd class="fontedit">
												<c:forEach items="${runningMovieList.actors}" var="actors"
													end="2">${actors.peopleNm} </c:forEach>
											</dd>

										</dl>
									</div>

								</li>
							</ul>

						</c:forEach>

					</div>

				</div>

			</div>

		</body>

	</article>

</section>

<%@ include file="../include/footer.jsp"%>