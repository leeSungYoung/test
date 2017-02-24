<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%@ include file="../include/header.jsp"%>
        <script type="text/javascript" src="/resources/js/mf/update.js"></script>
        <link rel="stylesheet" href="/resources/css/mf/insert.css">
        <section class="content_body">
            <article>
                <div>
                    <form action="/mf/update" method="post" id="mf_form">
                       <input type="hidden" name="mfKey" value="${mf.mfKey}">
                        <div class="mf_top">
                            <div>

                                <input type="text" name="mfTitle" class="Title" id="mfTitle" value="${mf.mfTitle}" placeholder="Title">
                                <div>
                                    <input type="text" name="mfVDate" id="mfVDate" class="mfVDate" value="${mf.mfVDate}" readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="mf_middle">
                            <div class="mf_middle_box">
                                <div class="mv_pos_box">
                                    <div class="mv_pos_img">
                                        <img id="preview" src="${mf.left_pos}" onerror="this.src='/resources/images/common/no_image_found.jpg'">
                                    </div>
                                </div>
                                <div class="mf_vs">
                                    VS
                                </div>
                                <div class="mv_pos_box">
                                    <div class="mv_pos_img">
                                        <img id="preview" src="${mf.right_pos}" onerror="this.src='/resources/images/common/no_image_found.jpg'">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="mf_footer">
                            <textarea name="mfComment" cols="30" rows="10" placeholder="Content">${mf.mfComment}</textarea>
                        </div>
                        <div class="BtnArea">
                            <input type="submit" value="수정">
                        </div>
                    </form>
                </div>
            </article>
        </section>



        <%@ include file="../include/footer.jsp"%>