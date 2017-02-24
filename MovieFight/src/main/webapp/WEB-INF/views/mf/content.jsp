<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%@ include file="../include/header.jsp"%>
        <script type="text/javascript" src="/resources/js/mf/content.js"></script>
        <link rel="stylesheet" href="/resources/css/mf/content.css">
        <script>
            var mfKey = ${mf.mfKey};
            var mKey1 = ${mf.mKey1};
            var mKey2 = ${mf.mKey2};
        </script>
        <section class="content_body">
            <article>
                <div>
                    <c:if test="${memLevel eq 'ADMIN'}">
                        <div class="mf_admin_menu">
                            <input type="button" value="수정" onclick="location.href='/mf/update/${mf.mfKey}';">
                            <input type="button" value="삭제" onclick="location.href='/mf/delete/${mf.mfKey}';">
                        </div>
                    </c:if>
                    <div class="mf_cnt_top">
                        <div>
                            <input type="text" name="mfTitle" class="Title" id="mfTitle" value="${mf.mfTitle}" readonly>
                            <div>
                                투표기간 : <input type="text" name="mfVDate" id="mfVDate" class="mfVDate" value="${mf.mfVDate}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="mf_cnt_middle">
                        <div class="mf_cnt_middle_box">
                            <div class="mv_pos_box">
                                <div class="mv_pos_img">
                                    <img id="preview" src="${mf.left_pos == null?'/resources/images/common/no_image_found.jpg':mf.left_pos}" onclick="location.href='/movie?mKey=${mf.mKey1}';">
                                </div>
                                <div class="mv_pos_btn">
                                </div>
                            </div>
                            <div class="mf_vs">
                                VS
                            </div>
                            <div class="mv_pos_box">
                                <div class="mv_pos_img">
                                    <img id="preview" src="${mf.right_pos == null?'/resources/images/common/no_image_found.jpg':mf.right_pos}" onclick="location.href='/movie?mKey=${mf.mKey2}'";>
                                </div>
                                <div class="mv_pos_btn">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="mf_cnt_footer">
                        <div class="footer_middle">
                            <div class="mf_vote">
                                <table class="mf_vote_table">
                                    <tr>
                                        <td class="mf_vote_btn" id="left_btn">투표</td>
                                        <td class="mf_vote_left" title="50%">${mf.mTitle1}</td>
                                        <td class="mf_vote_right" title="50%">${mf.mTitle2}</td>
                                        <td class="mf_vote_btn" id="right_btn">투표</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="footer_foot">
                                <div class="mf_reWrite">
                                    <textarea name="repContent" placeholder="투표 후 댓글을 등록 할 수 있습니다." disabled>${repContent}</textarea>
                                    <input type="button" value="${empty repContent?'댓글 등록':'댓글 수정'}">
                                </div>
                                <div class="mf_reList">
                                    <div class="re_left" id="re_${mf.mKey1}">
                                        <div class="re_area">
                                            <c:if test="${!empty bestRep_left}">
                                                <c:forEach items="${bestRep_left}" end="2" var="rep">
                                                    <div class="re_line">
                                                        <div class="re_top">
                                                            <div class="re_top_left">
                                                                <span>BEST</span>${rep.repName}
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
                                            </c:if>
                                            <c:set var="l_end" value="${(!empty bestRep_left)?(5 - (fn:length(bestRep_left))): 5 }" />

                                            <c:if test="${!empty repList_left}">
                                                <c:forEach items="${repList_left}" end="${l_end}" var="rep">
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
                                            </c:if>
                                        </div>
                                        <div class="BtnArea">
                                            <input type="button" id="${mf.mKey1}" value="댓글 더 보기">
                                        </div>
                                    </div>
                                    <div class="re_right" id="re_${mf.mKey2}">
                                        <div class="re_area">
                                            <c:if test="${!empty bestRep_right}">
                                                <c:forEach items="${bestRep_right}" end="2" var="rep">
                                                    <div class="re_line">
                                                        <div class="re_top">
                                                            <div class="re_top_left">
                                                                <span>BEST</span>${rep.repName}
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
                                            </c:if>

                                            <c:set var="r_end" value="${(!empty bestRep_right)?(5 - (fn:length(bestRep_right))): 5 }" />

                                            <c:if test="${!empty repList_right}">
                                                <c:forEach items="${repList_right}" end="${r_end}" var="rep">
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
                                            </c:if>
                                        </div>
                                        <div class="BtnArea">
                                            <input type="button" id="${mf.mKey2}" value="댓글 더 보기">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </article>
        </section>



        <%@ include file="../include/footer.jsp"%>