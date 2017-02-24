<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<script src="/resources/js/member/updatePW.js"></script>
<link rel="stylesheet" href="/resources/css/member/updatePW.css">

<section class="content_body">
	<article>

		<!-- <script type="text/javascript">
			function check() {
				if (document.getElementById('memPw').value == '') { //비밀번호 미입력시 경고
					alert("비밀번호를 확인해주세요");
					return false;
				}

				if (document.getElementById('memPw').value !== document
						.getElementById('memPw2').value) { // 1차 비밀번호와 2차 비밀번호 값 불일치시 경고
					alert("두개의 비밀번호가 일치하지 않습니다.");
					return false;
				}
			}
		</script>
 -->
		
		<!-- <style>
div.joinStep_body {
	width: 100%;
	height: 100%;
}

div.joinStep_body table.joinStep_box {
	width: 70%;
	margin: 0 auto;
	margin-top: 200px;
	color: rgb(109, 121, 137);
	font-size: 22px;
	font-weight: bold;
}

div.joinStep_body table.joinStep_box tr {
	height: 40px;
}

div.joinStep_body table.joinStep_box tr:first-child {
	text-align: center;
}

div.joinStep_body table.joinStep_box tr:last-child {
	text-align: center;
}

div.joinStep_body table.joinStep_box input[type=password] {
	width: 70%;
	font-size: 22px;
}

div.checkPW_body table.checkPW_box input[type=text] {
	width: 80%;
	font-size: 22px;
}
</style> -->


		<div class="joinStep_body">
			<form action="/member/updatePW2" method="post"
				onsubmit="return check();">
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