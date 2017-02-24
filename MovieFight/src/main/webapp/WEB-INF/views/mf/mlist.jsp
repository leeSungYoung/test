<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="../include/header.jsp"%>
        <link rel="stylesheet" href="/resources/css/mf/list.css">
        <section class="content_body">
            <article>
                <div class="mf_body">
                    <c:if test="${memLevel eq 'ADMIN'}">
                        <div class="mf_admin_menu">
                            <input type="button" value="등록" onclick="location.href='/mf/insert';">
                        </div>
                    </c:if>
                    <div class="mf_list_top">
                        <form action="/mf/list/1">
                            <input type="text" name="searchText" class="searchText" value="${searchText}">
                            <input type="submit" value="검색">
                        </form>
                    </div>
                    <div class="mf_list_middle">
                        <c:forEach items="${list}" var="mf" varStatus="status">
                            <div class="mf_list_box">
                                <!--<div class="rank_Num">${status.count}</div>-->
                                <div class="mf_list_img">
                                    <a href="/mf/content/${mf.mfKey}"><img src="${mf.left_pos}"> <img src="${mf.right_pos}">
                                        <div>VS</div>
                                    </a>
                                </div>
                                <div class="mf_list_content">
                                    <div class="Title">
                                        <a href="/mf/content/${mf.mfKey}">
                                    ${mf.mfTitle}
                                    </a>
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="mf_list_footer">
                        <div class="page_box">

                            <c:forEach varStatus="status" begin="${startPage}" end="${endPage}">

                                <c:if test="${curPage!=status.index}">
                                    <a href='/mf/list/${status.index}'>[${status.index}]</a>
                                </c:if>

                                <c:if test="${curPage==status.index}">
                                    <span class="curPage">[${status.index}]</span>
                                </c:if>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </article>
        </section>
        <%@ include file="../include/footer.jsp"%>