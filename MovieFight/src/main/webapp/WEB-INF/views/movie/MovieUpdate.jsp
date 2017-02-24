<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%@ include file="../include/header.jsp"%>
        <link rel="stylesheet" href="/resources/css/movie/insert.css">
        <script src="/resources/js/movie/insert.js"></script>
        <section class="content_body">
            <article>
                <div>
                    <form action="/movie/update" method="post" enctype="multipart/form-data" id="movie_form">
                        <!--영화 선택구역-->
                        <div class="movie_top">
                            <div class="movie_top_box">
                                <!--포스터-->
                                <div class="mv_pos_box">
                                    <div class="mv_pos_img">
                                        <img id="preview" src="${poster.fileModify}" onerror="this.src='/resources/images/common/no_image_found.jpg'">
                                        <input type="hidden" name="posterKey" value="${poster.fileKey}">
                                        <input type="hidden" name="posterName" value="${poster.fileModify}" id="fileName">
                                    </div>
                                    <div class="mv_pos_btn">
                                        <input type="file" name="poster" id="getfile" accept="image/*">
                                        <input type="button" value="삭제" id="delfile">
                                    </div>
                                </div>
                                <!--영화 ajax-->
                                <div class="movie_decArea">
                                    <div class="movie_dec">
                                        <input type="hidden" name="mKey" id="mKey" value="${movie.mKey}">
                                        <div class="box_top">
                                            <input type="text" name="mTitle" id="mTitle" class="Title" value="${movie.mTitle}" readonly>
                                        </div>
                                        <div class="clear"></div>
                                        <div class="box_bottom">

                                            <p>개봉일 : <span id="openDt"></span></p>
                                            <p>제작국가 : <span id="repNationNm"></span></p>
                                            <p>장르 : <span id="repGenreNm"></span></p>
                                            <p>감독 : <span id="directors"></span></p>
                                            <p>제작사 : <span id="companys"></span></p>
                                            <p></p>
                                            <p></p>
                                            <p></p>
                                            <p></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="clear"></div>
                        <!--줄거리,스틸컷-->
                        <div class="movie_middle">
                            <div class="movie_commentArea">
                                <textarea name="mSummary" id="mSummary" class="movie_comment" placeholder="Content">${movie.mSummary}</textarea>
                            </div>
                            <div class="movie_stillCut">
                                <c:set var="leng" value="${fn:length(StillShot)}" />
                                <c:forEach begin="1" end="3" varStatus="status">
                                    <div class="mv_pos_box">
                                        <div class="mv_pos_img">
                                            <c:if test="${leng >= status.index}">
                                                <img id="preview" src="${StillShot[status.index-1].fileModify}" onerror="this.src='/resources/images/common/no_image_found.jpg'">
                                                <input type="hidden" name="fileKey" value="${StillShot[status.index-1].fileKey}">
                                                <input type="hidden" name="fileName" value="${StillShot[status.index-1].fileModify}" id="fileName">
                                            </c:if>
                                            <c:if test="${leng < status.index}">
                                                <img id="preview" src="/resources/images/common/no_image_found.jpg">
                                                <input type="hidden" name="fileKey" value="">
                                                <input type="hidden" name="fileName" value="" id="fileName">
                                            </c:if>
                                        </div>
                                        <div class="mv_pos_btn">
                                            <input type="file" name="upload" id="getfile" accept="image/*">
                                            <input type="button" value="삭제" id="delfile">
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <!--예고편-->
                        <div class="movie_footer">
                            <c:forEach items="${mTrailer}" var="trailer" varStatus="status">
                                <div class="movie_trailer">
                                    <div class="mv_tr_box">
                                        <input type="text" name="mTrailer${status.count}" id="mTrailer${status.count}" value="${trailer}" class="movie_traText">
                                        <input class="preview" type="button" value="미리보기">
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="buttonArea">
                            <input type="submit" value="수정">
                        </div>
                    </form>
                    <div class="movie_youtube_preview">
                        <div class="layer" id="layer">
                            <div class="bg"></div>
                            <div id="layerTwo" class="pop-layer">
                                <div class="pop-container">
                                    <div class="pop-conts">
                                        <!--content //-->
                                        <p class="ctxt mb20" id="ctxt">
                                            <!-- <div id="player"></div> -->

                                        </p>
                                        <div class="btn-r">
                                            <a href="#" class="cbtn">Close</a>
                                        </div>
                                        <!--// content-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
        </section>



        <%@ include file="../include/footer.jsp"%>