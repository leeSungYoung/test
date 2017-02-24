<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<head>
<script src="/resources/js/common/jquery-3.1.1.min.js"></script>
<script src="/resources/js/movie/MovieList.js"></script>
<link href="/resources/css/movie/MovieList.css" rel="stylesheet">
</head>

	<body>
		<section class="content_body">
			<article>
				<div id="container">
					<div id="main">
						<div class="movie_headline elem">
							영화
							<c:if test="${memLevel=='ADMIN'}">
							<div class="admin">
								<input class="in" type="button" value="등록">
							</div>
							</c:if>
						</div>
						<div class="movie_line elem">

							<c:forEach items="${getMovieList}" var="mList" varStatus="status"
								begin="0" step="1">
								<ul class="movie_table elem">
									<li class="movie_list elem"><a
										href="/movie?mKey=${mList.mKey}"> <span
											class="movie_img elem"> <img class="running_poster"
												src="/resources/upload/poster/poster_${mList.mKey}"
												onerror="this.src='/resources/images/common/no_image_found.jpg'">
										</span>
									</a>

										<div class="movie_desc elem">
											<span> <a href="/movie?mKey=${mList.mKey}"
												class="movie_title">${mList.mTitle}</a>
											</span>
											<dl>
												<dt class="tit_t1">개요</dt>
												<dd class="fontedit">
													|${mList.nationNm}
													<c:forEach items="${mList.genres}" var="genres" begin="0"
														step="1">
                                                                | ${genres.genreNm}
                                                            </c:forEach>
												</dd>
												<dt style="display: none;">런타임</dt>
												<dd class="fontedit">| ${mList.showTm}분</dd>

												<dt style="display: none;">개봉일</dt>
												<dd class="fontedit">| ${mList.prdtYear}作</dd>
												<br>
												<dt class="tit_t2">감독</dt>
												<dd class="fontedit">
													<c:forEach items="${mList.directors}" var="directors"> | ${directors.peopleNm} </c:forEach>
												</dd>
												<br>
												<dt class="tit_t3">출연</dt>
												<dd class="fontedit">
													<c:forEach items="${mList.actors}" var="actors" step="1"
														end="3"> | ${actors.peopleNm}</c:forEach>
												</dd>

											</dl>
										</div></li>
								</ul>

							</c:forEach>
							<div class="clear"></div>
							<form action="/movie/MovieList" method="get">
								<input type="text" name="searchMovie"> <input
									type="submit" value="검색">
							</form>
							<div class="page_box">

								<c:if test="${stPage>1}">
									<a href='/movie/MovieList?page=${endPage-curPage}'>&lt;이전페이지</a>
								</c:if>


								<c:forEach varStatus="status" begin="${stPage}" end="${endPage}">

									<c:if test="${curPage!=status.index}">
										<a href='/movie/MovieList?page=${status.index-1}'>[${status.index}]</a>
									</c:if>

									<c:if test="${curPage==status.index}">
										<span class="curPage">[${status.index}]</span>
									</c:if>
								</c:forEach>


								<c:if test="${totalPage-endPage!=0}">

									<a href='/movie/MovieList?page=${endPage}'>다음페이지&gt;</a>

								</c:if>

							</div>

						</div>

					</div>

				</div>
				<input class="memLevel" type="hidden" value="${memLevel}">
	</body>

	</article>
</section>



<%@ include file="../include/footer.jsp"%>