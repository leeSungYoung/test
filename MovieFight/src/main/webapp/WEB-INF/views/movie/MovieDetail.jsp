<%@page import="java.awt.print.Printable"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<head>
<script src="/resources/js/common/jquery-3.1.1.min.js"></script>
<script src="/resources/js/movie/KobisOpenAPIRestService.js"></script>
<script src="/resources/js/movie/MovieDetail.js"></script>
<link href="/resources/css/movie/MovieDetail.css" rel="stylesheet">

</head>
<body>

	<section class="content_body" style="height: auto;">
		<article style="height: auto;">
		
	
		
<c:if test="${memLevel=='ADMIN'}">
			<div class="admin">
				<input class="up" type="button" value="수정"> <input
					class="del" type="button" value="삭제">
			</div>

</c:if>
			
			
	
			
			<div class="still">
				<link href="/resources/css/movie/fotorama.css" rel="stylesheet">
				<script src="/resources/js/movie/fotorama.js"></script>

				<div class="fotorama" data-minwidth="940" data-minheight="400"
					data-maxwidth="940" data-maxheight="400"
					data-transition="dissolve" data-autoplay="3000" data-fit="cover" data-loop="true" data-stopautoplayontouch="false">

					<c:forEach items="${stillShot}" var="st" varStatus="status"
						begin="0" step="1">
						<img src="${st.fileModify}">
					</c:forEach>

				</div>

			</div>
			<div class="wallpaper">
				<div class="poster">
					<img style="width: 220px; height: 313px;"
						src="/resources/upload//poster/poster_${mDetail.mKey}"
						onerror="this.src='/resources/images/common/no_image_found.jpg'">
				</div>
				<div class="info">
					<div class="title">${movieNm}</div>
				</div>
				<div class="desc">${movieNmEn},
					${prdtYear}, ${nationNm},
					<c:forEach items="${genres}" var="gen" varStatus="status" begin="0"
						step="1">

                                        ${gen.genreNm},

                                    </c:forEach>${watchGradeNm},
					${showTm}분
					<div class="zzim" onclick="zzim('${memKey}','${mDetail.mKey}')">♥
						찜하기</div>
				</div>

				<h2></h2>
				<div class="desc2">
					<span class="number"> ${avgStar}</span> <span class="totalstar">(
						${mStar}명 참여)</span>
				</div>

				<div class="count">
					<div class="Star">
						<select class="setStar" name="setStar">
							<option value="1">☆</option>
							<option value="2">☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
							<option value="6">☆☆☆☆☆☆</option>
							<option value="7">☆☆☆☆☆☆☆</option>
							<option value="8">☆☆☆☆☆☆☆☆</option>
							<option value="9">☆☆☆☆☆☆☆☆☆</option>
							<option value="10">☆☆☆☆☆☆☆☆☆☆</option>
						</select>
						<button type="button" id="setStar" onclick="setStar()">등록</button>

					</div>
					<div class="getStar"></div>
					<div class="bot">
						<span> <img style="width: 12px; height: 12px; color: gray;"
							src="/resources/images/movie/star.png"> ${mStar}
						</span><span> <img style="width: 10px; height: 10px;"
							src="/resources/images/movie/heart.png"> ${mZzim}
						</span> <span> <img style="width: 14px; height: 10px;"
							src="/resources/images/movie/bubble.png"> ${reSize}
						</span>
					</div>
				</div>
			</div>
			<div class="leftRight">
				<div class="left">
					<div class="summary">
						<h5>줄거리</h5>
						<div class="story">${Summary}</div>
					</div>
					<div class="reply">

						<c:if test="${memKey!=null}">
							<button type="button" id="viewReply" onclick="getReply()">내
								댓글 확인하기</button>
						</c:if>

						<div class="myContext">
							<h5>내 댓글</h5>
							<br>


							<div class="tag1">
								<img class="tag2" src="/resources/images/movie/men.jpg"
									onerror="this.src='/resources/images/common/no_image_found.jpg'">
							</div>
							<textarea class="textarea"></textarea>
							<input type="button"
								style="width: 50px; height: 50px; margin-top: 12px; margin-left: 12px;"
								value="입력" onclick="send1();">



						</div>

							<h5>
								코멘트<span id="reSize" style="color: #93939c;">(${reSize})</span>
							</h5>
							<div class="asd"></div>
							<div class="clear"></div>
							<div class="moreCallIn">더보기</div>
					
					</div>


				</div>
				<div class="right">
					<h5>예고편</h5>
					<div class="trailer">
						<img id="trailer"
							src="http://img.youtube.com/vi/${mDetail.mTrailer1}/0.jpg"
							onclick="layer_open('layerTwo',0)">
					</div>
					<div class="trailer">
						<img id="trailer"
							src="http://img.youtube.com/vi/${mDetail.mTrailer2}/0.jpg"
							onclick="layer_open('layerTwo',1)">
					</div>
					<div class="trailer">
						<img id="trailer"
							src="http://img.youtube.com/vi/${mDetail.mTrailer3}/0.jpg"
							onclick="layer_open('layerTwo',2)">
					</div>
					<c:forEach begin="0" end="2" varStatus="status">
						<div class="layer" id="layer${status.index}">
							<div class="bg"></div>
							<div id="layerTwo${status.index}" class="pop-layer">
								<div class="pop-container">
									<div class="pop-conts">
										<!--content //-->
										<p class="ctxt mb20">
											<!-- <div id="player"></div> -->
										<div id="player${status.index}"></div>
										</p>
										<div class="btn-r">
											<a href="#" class="cbtn">Close</a>
										</div>
										<!--// content-->
									</div>
								</div>
							</div>
						</div>

					</c:forEach>



					<div class="director">
						<h5>감독</h5>
						<dl>
							<c:forEach items="${directors}" var="direct" varStatus="status"
								begin="0" step="1">
								<dd>${direct.peopleNm}</dd>
								<dd style="font-size: 10px">${direct.peopleNmEn}</dd>
							</c:forEach>


						</dl>
					</div>
					<br>
					<hr>
					<div class="acter">
						<h5>배우</h5>
						<br>
						<dl>
							<c:forEach items="${actors}" var="act" varStatus="status"
								begin="0" step="1">
								<dd>${act.peopleNm}</dd>
								<dd style="font-size: 10px">${act.peopleNmEn}</dd>
								<br>
							</c:forEach>
						</dl>
					</div>
				</div>
			</div>





			<input class="mKey" type="hidden" value="${mDetail.mKey}"> <input
				class="memKey" type="hidden" value="${memKey}"> <input
				class="mTra1" type="hidden" value="${mDetail.mTrailer1}"> <input
				class="mTra2" type="hidden" value="${mDetail.mTrailer2}"> <input
				class="mTra3" type="hidden" value="${mDetail.mTrailer3}"> <input
				class="memLevel" type="hidden" value="${memLevel}"> 
				<input class="memName" type="hidden" value="${memName}">
		</article>
	</section>



	<%@ include file="../include/footer.jsp"%>