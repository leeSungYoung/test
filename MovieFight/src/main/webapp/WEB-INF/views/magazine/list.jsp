<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ include file="../include/header.jsp"%>
            <script src="/resources/js/magazine/magazineAdmin.js"></script>
            <link href="/resources/css/magazine/magazineList.css" rel="stylesheet">

            </head>

            <body>

                <section class="content_body">
                    <article>
                        <div id="container">

                            <div id="main">
                                <div class="magazine_list_box">
                                    <div class="magazine_array elem">
                                        <a href="/magazine/list/1">최신순</a>
                                        <a href="/magazine/hitList/1">조회순</a>
                                        <c:if test="${memLevel eq 'ADMIN'}">
                                            <div class="admin_option">
                                                <input class="insert" type="button" onclick="funtion(${memLevel})" value="등록">
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="magazine_headline elem">MAGAZINE</div>

                                    <div class="movie_magazine elem">

                                        <c:forEach items="${magazineList}" var="magazineList">

                                            <ul class="magazine_table elem">
                                                <a href="/magazine/content?magkey=${magazineList.magKey}" class="magazine_list elem">

                                                    <span class="magazineList_img elem">
											<img class="magazine_img" alt="${magazineList.magTitle}"
												src="${magazineList.magImg}"
												onError="this.src='/resources/images/magazine/type-article.png'">
										</span>

                                                    <span class="magazine_desc elem">
											<li class="magazine_title">${magazineList.magTitle}</li>
											<br>
											<li>${magazineList.magSubTitle}</li>
											<br>
											<li>${magazineList.magDate}</li>

										</span>

                                                </a>
                                            </ul>

                                        </c:forEach>

                                        <div class="magazine_page">

                                            <span class="magazine_page" style="margin-top: 0px;">
									<!-- 시작페이지가 1부터면 이전 표시("<<") ​ 안함 -->
									<c:if test="${start-1 ==0 }">

									</c:if>


									<c:if test="${start-1!=0 }">
										<span>
											<a href="/magazine/${classfy}/${start-1}">&laquo;</a>
										</span>
                                            </c:if>

                                            <!-- 		페이지 5개씩 표시					-->
                                            <c:forEach var="i" begin="${start }" end="${end }">
                                                <c:if test="${i ne seq}">
                                                    <span id="page${i }">
											            <a href="/magazine/${classfy}/${i}">${i}</a>
										            </span>
                                                </c:if>
                                                <c:if test="${i eq seq}">
                                                    <span id="curPage">
											            ${i}
										            </span>
                                                </c:if>
                                            </c:forEach>

                                            <!-- 		전체페이지 수가 엔드페이지보다 크면  다음 페이지 >> 바로가기 표시	-->

                                            <c:if test="${end % 5 == 0 && pageNum > end}">
                                                <span>
											<a href="/magazine/${classfy}/${end+1}">&raquo;</a>
										</span>
                                            </c:if>

                                            <c:if test="${end % 5 != 0 && end == pageNum }">

                                            </c:if>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <input class="level" type="hidden" value="${memLevel}">
            </body>
            </article>
            </section>



            <%@ include file="../include/footer.jsp"%>