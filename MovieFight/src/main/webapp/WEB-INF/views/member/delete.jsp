<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<link rel="stylesheet" href="/resources/css/member/delete.css">


<section class="content_body">

	<article style="margin-top: 200px">

	<!-- 	<style>
#delete {
	background-color: #D5D5D5;
	height: 100px;
	width: 450px;
}

#msg {
	text-align: center;
	font-size: 18px;
	font-weight: bold;
}
</style>
 -->
		<body>
			<form action="/member/delete2" method="POST">
				<center>
					<h1>MF 회원탈퇴</h1>
				</center>
				<br>
				<center>

					<div id="delete">
						<table border="0">
							<tr>
							<br>
								<td id="msg">회원탈퇴시 기존의 회원정보는 모두 삭제됩니다. <br>이점에 유의하시고 탈퇴하시기 바랍니다.
								</td>
							</tr>
						</table>
						<td width="400"></td> <br>



					</div>
					<br> <span><input type="submit" value="탈퇴"></span>
				</center>
		</body>

		</form>

	</article>
</section>



<%@ include file="../include/footer.jsp"%>