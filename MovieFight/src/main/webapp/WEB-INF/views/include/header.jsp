<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                <!DOCTYPE html >
                <html>

                <head>
                    <meta charset="UTF-8">
                    <script type="text/javascript" src="/resources/js/common/jquery-3.1.1.min.js"></script>
                    <script type="text/javascript" src="/resources/js/common/header.js"></script>
                    <link rel="stylesheet" href="/resources/css/common/default.css">
                </head>

                <body>
                    <div class="body_size">
                        <header class="main_menu">
                            <div>
                                <ul class="left_menu">
                                    <li onclick="location.href='/'">메인</li>
                                    <li onclick="location.href='/mf/list'">MF</li>
                                    <li onclick="location.href='/movie/boxoffice'">TOP10</li>
                                    <li onclick="location.href='/magazine/list2/1'">매거진</li>
                                    <li onclick="location.href='/movie/MovieList'">영화DB</li>
                                    <c:if test="${memLevel eq 'ADMIN'}">
                                        <li onclick="location.href='/member/list'">회원</li>
                                    </c:if>
                                </ul>
                                <div class="right_menu">
                                    <ul>
                                        <c:if test="${empty memName}">
                                            <li onclick="location.href='/member/login1'">로그인</li>
                                        </c:if>
                                        <c:if test="${!empty memName}">
                                            <li>${memName}</li>
                                            <li onclick="location.href='/member/checkPW1'" class="down_menu">정보수정</li>
                                            <li onclick="location.href='/member/zzimMovie'" class="down_menu">찜</li>
                                            <li onclick="location.href='/member/star'" class="down_menu">평가</li>
                                            <li onclick="location.href='/member/logout'" class="down_menu">로그아웃</li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </header>
                        <div class="clear"></div>

                        <!--header 끝-->