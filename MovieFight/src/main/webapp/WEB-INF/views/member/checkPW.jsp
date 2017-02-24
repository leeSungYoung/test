<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<section class="content_body">
<meta charset="UTF-8">

            <script src="/resources/js/member/checkPW.js"></script>
            <link rel="stylesheet" href="/resources/css/member/checkPW.css">

	<article>

		<script>
/* 			function alter1() { // 기본정보 수정/탈퇴 버튼 페이지 링크
				var form = $('#form');
				form.attr('action', '/member/update1');
				form.attr('method', 'post');
				form.submit();
			}

			function alter2() { // 비밀변호변경 버튼 페이지 링크
				var form = $('#form');
				form.attr('action', '/member/updatePW1');
				form.attr('method', 'post');
				form.submit();
			} */
		</script>

<!-- 		<style>
div.checkPW_body {
	width: 100%;
}

div.checkPW_body table.checkPW_box {
	width: 70%;
	margin: 0 auto;
	margin-top: 300px;
	text-align: center;
	font-size: 22px;
	font-weight: bold;
}

div.checkPW_body table.checkPW_box tr {
	height: 40px;
	
}

div.checkPW_body table.checkPW_box tr.checkPW_input td:first-child {
	text-align: right;
}

div.checkPW_body table.checkPW_box tr.checkPW_input td:last-child {
	text-align: left;
	text-indent: 20px;
}

div.checkPW_body table.checkPW_box input[type=text] {
	width: 80%;
	font-size: 22px;
}

div.checkPW_body table.checkPW_box input[type=password] {
	width: 80%;
	font-size: 22px;
}


</style> -->

		<form id="form">
			<div class="checkPW_body">
				<table class="checkPW_box">
					<tr>
						<td colspan="2">MF 회원정보수정 비밀번호 확인</td>
					</tr>
					<tr class="checkPW_input">
						<td width="80">패스워드</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td></td>
						<td id="login_err" >${MSGPW } ${MSGPW2}</td>
					</tr>

					<tr>
						<td colspan="2"><input onclick="alter1()" type="submit"
								value="기본정보 수정/탈퇴"> <input onclick="alter2()" type="submit"
								value="비밀번호 수정"></td>
					</tr>
				</table>
				<br>
			</div>
		</form>



	</article>
</section>



<%@ include file="../include/footer.jsp"%>