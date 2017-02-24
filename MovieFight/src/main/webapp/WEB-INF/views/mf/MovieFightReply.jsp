<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html >
            <html>

            <head>
                <meta charset="UTF-8">
                <script src="/resources/js/common/jquery-3.1.1.min.js"></script>
                <script src="/resources/js/mf/reply.js"></script>
                <link rel="stylesheet" href="/resources/css/common/default.css">
                <link rel="stylesheet" href="/resources/css/mf/reply.css">
                <script>
                    var mfKey = "<c:out value='${mfKey}'/>";
                </script>
            </head>

            <body>
                <section class="content_body">
                    <article>
                        <div class="mf_reList">
                            <div class="re_area">
                                <c:if test="${fn:length(list) <= 0 }">
                                    <div class="non_rep">
                                        <p>등록된 댓글이 없습니다.</p>
                                    </div>
                                </c:if>
                                <c:if test="${fn:length(list) > 0}">
                                    <c:forEach items="${list}" var="rep">
                                        <div class="re_line">
                                            <div class="re_top">
                                                <div class="re_top_left">
                                                    ${rep.repName}
                                                </div>
                                                <div class="re_top_middle">
                                                    ${rep.repDate}
                                                </div>
                                                <div class="re_top_right">
                                                    <img src="/resources/images/movie/like-512.png">
                                                    <div class="rep_good">${rep.repGood}</div>
                                                </div>
                                                <input type="hidden" value="${rep.repKey}">
                                                <div class="clear"></div>
                                            </div>
                                            <div class="re_middle">
                                                ${rep.repContent}
                                            </div>

                                        </div>
                                    </c:forEach>
                                    <div class="page_box">

                                        <c:forEach varStatus="status" begin="${startPage}" end="${endPage}">

                                            <c:if test="${curPage!=status.index}">
                                                <a href='/mf/reply/${mfKey}/${mKey}/${status.index}'>[${status.index}]</a>
                                            </c:if>

                                            <c:if test="${curPage==status.index}">
                                                <span class="curPage">[${status.index}]</span>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                    </article>
                </section>
            </body>

            </html>