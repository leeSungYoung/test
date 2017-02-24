<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%@ include file="../include/header.jsp"%>
        <section class="content_body">
            <article>
                <style>
                    div.err_body {
                        width: 100%;
                        height: 400px;
                    }
                    
                    div.err_body div.err_box {
                        width: 100%;
                        margin-top: 100px;
                        text-align: center;
                        font-size: 40px;
                        font-weight: bold;
                    }
                </style>
                <div class='err_body'>
                    <div class='err_box'>
                        요청 처리상에 문제가 발생하였습니다.
                        <c:if test="${exception.message ne null}">
                      		<br> Exception :${exception.message}
                       </c:if>
                    </div>
                </div>

            </article>
        </section>



        <%@ include file="../include/footer.jsp"%>