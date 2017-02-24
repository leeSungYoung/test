<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../include/header.jsp"%>

<script src="/resources/js/member/joinStep.js"></script>
<link rel="stylesheet" href="/resources/css/member/joinStep.css">

<section class="content_body">
	<article>
		<div class="joinStep_body">
			<form action="/member/joinStep2" method="POST"
				onsubmit="return check();">

				<table class="joinStep_box">
					<tr>
						<td colspan="2">MF 회원가입</td>
					</tr>
					<tr>
						<td width="150">E-MAIL</td>
						<td><input type="text" id="memEmail" name="memEmail"
								size="22" onblur="email()" onchange="changeId();"> <input
								type="button" name="checkIdDup" value="중복확인"
								onclick="checkidDup();" /> <input type="hidden" id="checkDup"
								value="false" />
					</tr>
					<tr>
						<td width="150">패스워드</td>
						<td><input type="password" id="memPw" name="memPw" size="22">
						</td>
					</tr>
					<tr>
						<td width="150">패스워드 확인</td>

						<td><input type="password" id="memPw2" name="memPw2"
								size="22"></td>
					</tr>
					<tr>
						<td width="150">이름</td>
						<td><input type="text" id="memName" name="memName" size="22">
						</td>
					</tr>
					<tr>
						<td width="150">생년월일</td>
						<td><select name="memBirth_Year" class="memBirth_Year">
								<c:forEach var="i" begin="1970" step="1" end="2017">
									<c:set var="y"
										value="<fmt:formatNumber type='number' value='${i}' pattern='00'/>" />
									<option value="${i}">${ i }</option>
								</c:forEach>
						</select>년 <select name="memBirth_Month">
								<c:forEach var="i" begin="01" step="1" end="12">
									<option
										value="<fmt:formatNumber type='number' value='${i}' pattern='00'/>"><fmt:formatNumber
											type='number' value='${i}' pattern='00' /></option>
								</c:forEach>
						</select> 월 <select name="memBirth_Day">
								<c:forEach var="i" begin="01" step="1" end="31">
									<option
										value="<fmt:formatNumber type='number' value='${i}' pattern='00'/>"><fmt:formatNumber
											type='number' value='${i}' pattern='00' /></option>
								</c:forEach>
						</select>일</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="가입완료"></td>
					</tr>
				</table>
			</form>
		</div>
	</article>
</section>



<%@ include file="../include/footer.jsp"%>