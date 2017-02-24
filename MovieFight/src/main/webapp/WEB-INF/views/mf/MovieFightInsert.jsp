<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%@ include file="../include/header.jsp"%>
        <script type="text/javascript" src="/resources/js/mf/insert.js"></script>
        <link rel="stylesheet" href="/resources/css/mf/insert.css">
        <section class="content_body">
            <article>
                <div>
                    <form action="/mf/insert" method="post" id="mf_form">
                        <div class="mf_top">
                            <div>

                                <input type="text" name="mfTitle" class="Title" id="mfTitle" placeholder="Title">
                                <div>
                                    <input type="text" name="mfVDate" id="mfVDate" class="mfVDate" value="" readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="mf_middle">
                            <div class="mf_middle_box">
                                <div class="mv_pos_box">
                                    <div class="mv_pos_img">
                                        <img id="preview" src="/resources/images/common/no_image_found.jpg">
                                    </div>
                                    <div class="mv_pos_btn">
                                        <input type="button" id="get_mv" value="영화 선택">
                                    </div>
                                    <input type="hidden" name="mKey1" id="mKey" value="">
                                </div>
                                <div class="mf_vs">
                                    VS
                                </div>
                                <div class="mv_pos_box">
                                    <div class="mv_pos_img">
                                        <img id="preview" src="/resources/images/common/no_image_found.jpg">
                                    </div>
                                    <div class="mv_pos_btn">
                                        <input type="button" id="get_mv" value="영화 선택">
                                    </div>
                                    <input type="hidden" name="mKey2" id="mKey" value="">
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="mf_footer">
                            <textarea name="mfComment" cols="30" rows="10" placeholder="Content"></textarea>
                        </div>
                        <div class="BtnArea">
                            <input type="submit" value="등록">
                        </div>
                    </form>
                </div>
            </article>
        </section>



        <%@ include file="../include/footer.jsp"%>