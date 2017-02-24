<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>


<link rel="stylesheet" href="/resources/css/member/login.css">


<section class="content_body">
	<article>
		<body>
			<form action="/member/login2" method="POST">
				<div class="login_body">
					<div class="login_box">
						<table class="login_form">
							<tr>
								<td width="150">E-MAIL</td>
								<td><input type="text" name="memEmail" maxlength="30"
										placeholder="E-MAIL"></td>
							</tr>
							<tr>
								<td width="150">패스워드</td>
								<td><input type="password" name="memPw" placeholder="PASSWORD">
								</td>
							</tr>
							<tr>
								<td></td>
								<td class="login_err">${MSG }</td> 
							
							</tr>
							<tr>
								<td></td>
								<td><a href="/member/joinStep1" class="btn_join">회원가입</a> /
									<a href="/member/findPW1">비밀번호 찾기</a></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="로그인"></td>
							</tr>
						</table>
						<div class="login_message"></div>
						<p class="btn_lnk"></p>
						<div class="login_btn"></div>
					</div>
				</div>


			</form>

		</body>

	</article>
</section>



<%@ include file="../include/footer.jsp"%>