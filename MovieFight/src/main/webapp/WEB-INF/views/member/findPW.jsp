<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>


<link rel="stylesheet" href="/resources/css/member/findPW.css">


<section class="content_body">
	<article>
	<!-- 	<style>
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

div.joinStep_body table.joinStep_box input[type=text] {
	width: 70%;
	font-size: 22px;
}

div.checkPW_body table.checkPW_box input[type=password] {
	width: 80%;
	font-size: 22px;
}

div.joinStep_body table.joinStep_box select {
	width: 60px;
	margin: 0 8px;
	font-size: 22px;
	color: rgb(109, 121, 137);
}

div.joinStep_body table.joinStep_box select.memBirth_Year {
	width: 80px;
}

div.joinStep_body table findPW_box td.login_err {
	color: #FF6C6C;
	font-size: 20px;
	height: 0 auto;
	
}
</style> -->
		<div class="joinStep_body">
			<form action="/member/findPW2">
				<table class="joinStep_box">
					
					<tr>
						<td colspan="2">MF 비밀번호 찾기</td>
					</tr>
					<tr>
					
						<td width="150">아이디</td>
						<td><input type="text" id="memEmail" name="memEmail"
								size="22" onblur="email()">
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
									<option value="${ i }">${ i }</option>
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
								<td></td>
								<td class="login_err">${MSG }</td> 
							
							</tr>
					
					<tr>
						<td colspan="2"><input type="submit" value="비밀번호 변경">
						</td>
					</tr>
				</table>
			</form>
		</div>



	</article>
</section>



<%@ include file="../include/footer.jsp"%>