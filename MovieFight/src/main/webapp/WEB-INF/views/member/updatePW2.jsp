<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<script src="/resources/js/member/updatePW.js"></script>
<link rel="stylesheet" href="/resources/css/member/updatePW2.css">

<section class="content_body">
	<article>
		<div class="joinStep_body">
			<form action="/member/updatePW3" method="post"
				onsubmit="return check();">
				<input type="hidden" name="memKey" value="${memKey}">
				<table class="joinStep_box">
					<tr>
						<td colspan="2">MF 비밀번호 변경</td>
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
						<td colspan="2"><input type="submit" value="비밀번호 수정">
						</td>
					</tr>
				</table>

			</form>
		</div>

	</article>
</section>



<%@ include file="../include/footer.jsp"%>