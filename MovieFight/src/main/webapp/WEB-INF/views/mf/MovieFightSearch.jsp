<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html >
        <html>

        <head>
            <meta charset="UTF-8">
            <script src="/resources/js/common/jquery-3.1.1.min.js"></script>
            <script src="/resources/js/mf/search.js"></script>
            <link rel="stylesheet" href="/resources/css/common/default.css">
            <link rel="stylesheet" href="/resources/css/mf/search.css">
        </head>

        <body>
            <section>
                <article>
                    <div class="search_body">
                        <form action="/mf/insert/search" method="get">
                            <div class="search_top">
                                <input type="text" name="searchWord" value="${searchWord}">
                                <input type="submit" value="검색">
                            </div>
                            <div class="search_middle">
                                <p>페이지:${curPage}</p>
                                <table class="search_view">
                                    <tr class="movie_col">
                                        <td>영화코드</td>
                                        <td class="movie_poster">포스터</td>
                                        <td class="movie_title">영화명</td>
                                        <td></td>
                                    </tr>
                                    <c:forEach items="${list}" var="list">
                                        <tr>
                                            <td>${list.mKey}</td>
                                            <td class="movie_poster"><img src="${list.fileModify}" alt=""></td>
                                            <td class="movie_title">${list.mTitle}</td>
                                            <td>
                                                <input type="button" value="선택" id="selectMovie">
                                                <input type="hidden" name="" id="mKey" value="${list.mKey}">
                                                <input type="hidden" name="" id="mTitle" value="${list.mTitle}">
                                                <input type="hidden" name="" id="poster" value="${list.fileModify}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="search_footer">
                                <div class="page_box">

                                    <c:forEach varStatus="status" begin="${startPage}" end="${endPage}">

                                        <c:if test="${curPage!=status.index}">
                                            <a href='/mf/insert/search/${status.index}?searchWord=${searchWord}'>[${status.index}]</a>
                                        </c:if>

                                        <c:if test="${curPage==status.index}">
                                            <span class="curPage">[${status.index}]</span>
                                        </c:if>
                                    </c:forEach>

                                </div>
                            </div>
                        </form>

                    </div>
                </article>
            </section>
        </body>

        </html>