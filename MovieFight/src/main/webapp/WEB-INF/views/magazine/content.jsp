<%@page import="org.springframework.ui.Model"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<head>
<script src="/resources/js/magazine/magazineAdmin.js"></script>
<link href="/resources/css/magazine/magazineContent.css"
	rel="stylesheet">

</head>



<body>

	<section class="content_body">
		<article>

			<div id="container">


				<div id="main">

					<div class="magazine_main">

						<div class="magazine_title">
							<p>${magazineContent.magTitle}</p>
							<p class="magazine_view_count">hit ${magazineContent.magHit}</p>

						</div>

						<div class="magazine_content">

							<div class="magazine_content_view">
								<div class="magazine_content_data">${magazineContent.magContent}</div>
							</div>

							<div class="magazine_date">
								<b>${magazineContent.magDate}</b>
							</div>

							<div class="magazine_list">
								<c:if test="${memLevel eq 'ADMIN'}">
									<span class="admin_option">
										<input class="update" type="button" value="수정"> <input
											class="delete" type="button" value="삭제">
									</span>
								</c:if>
								<a href="/magazine/list/1">목록보기</a>
							</div>

						</div>


					</div>

				</div>

			</div>

			<input class="level" type="hidden" value="${memLevel}"> <input
				class="magKey" type="hidden" value="${magazineContent.magKey}">
</body>

</article>
</section>


<%@ include file="../include/footer.jsp"%>