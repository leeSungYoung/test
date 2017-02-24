<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<link rel="stylesheet" href="/resources/css/member/star.css">


<section class="content_body">
	<article>

		<head>


<!-- <style type="text/css">
#container {
	width: 930px;
	min-height: 890px;
	background-color: #F6F6F6;
	overflow: hidden;
}

div.movie_box {
	width: 100%;
	height: 200px;
}

#movie {
	height: 100%;
	float: left;
	background-color: white;
	width: 410px;
	margin-left: 35px;
	margin-top: 20px;
	border: 3px solid rgb(109, 121, 137);
}

#movie_imag {
	background-color: #D9E5FF;
	height: 160px;
	width: 110px;
	margin-left: 15px;
	float: left;
	margin-top: 20px
}

#movie_title {
	float: left;
	width: 150px;
	margin-top: 20px;
	margin-left: 10px;
	font-size: 25px;
	color: #6d7988;
}

#movie_star {
	float: left;
	width: 200px;
	margin-top: 40px;
	margin-left: 10px;
	font-size: 25px;
	color: #6d7988;
}

#movie_index {
	float: left;
	margin-top: 18px;
	margin-left: 15px;
	font-weight: bold;
	font-size: 25px;
	color: #6d7988;
}

#image {
	height: 160px;
	width: 110px;
	margin-left: 15px;
	float: left;
	margin-top: 20px
}

#title {
	margin-left: 30px;
	color: #6d7988;
	font-size: 20px;
}

#warning {
	margin-left: 30px;
	color: #6d7988;
	font-size: 20px;
}
</style> -->

		</head>

		<body>

			<h1 id="title">내가 평가한영화</h1>

			<div id="container">
				<c:if test="${!empty selectStar}">
					<c:forEach begin="0" end="${lineNum - 1}" varStatus="r_status">
						<div class="movie_box">
							<c:forEach begin="0" end="1" varStatus="c_status">
								<c:set var="i" value="${r_status.index*2+c_status.count}" />
								<c:if test="${fn:length(selectStar) >= i}">
									<div id="movie">
										<div id="movie_index">${i}</div>
										<div id="movie_img">
										<a href="/movie?mKey=${selectStar[i-1].mKey}">
											<input id="image" type="image"
												src="/resources/upload/poster/poster_${selectStar[i-1].mKey}"
												onerror="this.src='/resources/images/common/no_image_found.jpg'"></a>
										</div>
										<div id="movie_title">${selectStar[i-1].mTitle}</div>
										<div id="movie_star">별점 : ${selectStar[i-1].star}</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</c:forEach>
				</c:if>
				<br>
				<c:if test="${empty selectStar}">
					<a id="warning">평가한 영화가 없습니다.</a>
				</c:if>

			</div>

		</body>

	</article>
</section>



<%@ include file="../include/footer.jsp"%>