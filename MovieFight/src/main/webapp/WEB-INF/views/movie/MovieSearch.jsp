<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html >
        <html>

        <head>
            <meta charset="UTF-8">
            <script src="/resources/js/common/jquery-3.1.1.min.js"></script>
            <script src="/resources/js/movie/search.js"></script>
            <link rel="stylesheet" href="/resources/css/common/default.css">
            <link rel="stylesheet" href="/resources/css/movie/search.css">
        </head>

        <body>
            <script>
                
            </script>
            <style>
                
            </style>
            <section>
                <article>
                    <div class="search_body">
                        <form action="/movie/insert/search" method="get">
                            <div class="search_top">

                                <input type="text" name="searchWord" value="${searchWord}">
                                <input type="submit" value="검색">

                            </div>
                            <div class="search_middle">
                                <p>총 영화 수:${totCnt}</p>
                                <table class="search_view">
                                    <tr class="movie_col">
                                        <td>영화코드</td>
                                        <td class="movie_title">영화명</td>
                                        <td>개봉연도</td>
                                        <td>제작국가</td>
                                        <td></td>
                                    </tr>
                                    <c:forEach items="${movieList}" var="list">
                                        <tr>
                                            <td>${list.movieCd}</td>
                                            <td class="movie_title">${list.movieNm}</td>
                                            <td>${list.openDt}</td>
                                            <td>${list.repNationNm}</td>
                                            <td>
                                                <input type="button" value="선택" id="selectMovie">
                                                <input type="hidden" name="" id="movieCd" value="${list.movieCd}">
                                                <input type="hidden" name="" id="movieNm" value="${list.movieNm}">
                                                <input type="hidden" name="" id="openDt" value="${list.openDt}">
                                                <input type="hidden" name="" id="repNationNm" value="${list.repNationNm}">
                                                <input type="hidden" name="" id="repGenreNm" value="${list.repGenreNm}">
                                                <input type="hidden" name="" id="directors" value="${list.directors[0].peopleNm}">
                                                <input type="hidden" name="" id="companys" value="${list.companys[0].companyNm}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="search_footer">
                                <div class="page_box">

                                    <c:forEach varStatus="status" begin="${startPage}" end="${endPage}">

                                        <c:if test="${curPage!=status.index}">
                                            <a href='/movie/insert/search?curPage=${status.index}&searchWord=${searchWord}'>[${status.index}]</a>
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