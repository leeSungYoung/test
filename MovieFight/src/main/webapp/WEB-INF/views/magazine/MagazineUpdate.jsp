<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%@ include file="../include/header.jsp"%>
        <section class="content_body">
            <link rel="stylesheet" href="/resources/css/magazine/insert.css">
            <script type="text/javascript" src="/resources/api/ckeditor/ckeditor.js"></script>
            <script type="text/javascript" src="/resources/js/magazine/insert.js"></script>
            <article>
                <form action="/magazine/update" method="post" id="mag_form">
                    <div class="mag_top">
                        <input type="hidden" name="magKey" value="${mag.magKey }">
                        <input type="text" name="magTitle" id="magTitle" class="Title" value="${mag.magTitle}" placeholder="Title">
                        <input type="text" name="magSubTitle" id="magSubTitle" class="Title" value="${mag.magSubTitle}" placeholder="Sub Title">
                    </div>
                    <div class="mag_middle">
                        <div class="mag_middle_box">
                            <textarea style="width: 100%" rows="10" name="magContent" id="textAreaContent" cols="80">${mag.magContent}</textarea>
                        </div>
                    </div>
                    <div class="BtnArea">
                        <input type="submit" value="수정">
                    </div>
                </form>

            </article>
        </section>



        <%@ include file="../include/footer.jsp"%>