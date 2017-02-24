<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="../include/header.jsp"%>


        <link rel="stylesheet" href="/resources/css/member/list.css">
        <section class="content_body">
            <article>
                <div class="m_list_body">
                    <div class="m_list_middle">
                        <table>
                            <tr class="m_list_col">
                                <td>번호</td>
                                <td>email</td>
                                <td>이름</td>
                                <td>생일</td>
                            </tr>
                            <c:forEach items="${list}" var="mem">
                                <tr>
                                    <td>${mem.memKey}</td>
                                    <td>${mem.memEmail}</td>
                                    <td>${mem.memName}</td>
                                    <td>${mem.memBirth}</td>
                                </tr>
                            </c:forEach>
                            <tr class="page">
                                <td colspan="4">
                                    <c:forEach varStatus="status" begin="${startPage}" end="${endPage}">

                                        <c:if test="${curPage!=status.index}">
                                            <a href='/member/list/${status.index}'>[${status.index}]</a>
                                        </c:if>

                                        <c:if test="${curPage==status.index}">
                                            <span class="curPage">[${status.index}]</span>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </article>
        </section>
        <%@ include file="../include/footer.jsp"%>