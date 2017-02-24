<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<script src="/resources/js/member/update.js"></script>
<link rel="stylesheet" href="/resources/css/member/update.css">




<section class="content_body">
	<article>

	<!-- 	<style>
div.joinStep_body {
	width: 100%;
	height: 100%;
}

div.joinStep_body table.joinStep_box {
	width: 60%;
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
	width: 75%;
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
</style> -->


		<div class="joinStep_body">
			<form action="/member/update2" method="POST"  onsubmit="return check();">
				<table class="joinStep_box">
					<tr>
						<td colspan="2">MF 회원정보수정</td>
					</tr>
					<tr>
						<td width="150">이름</td>
						<td><input type="text" id="memName" name="memName"
								value="${member.memName}" size="22" ></td>
					</tr>
					<tr>
						<td width="150">생년월일</td>
						<td><select name="memBirth_Year" id="memBirth_Year"
							class="memBirth_Year">
								<c:forEach var="i" begin="1970" step="1" end="2017">
									<option value="${ i }"
										${i==member.memBirth_Year ? 'selected="selected"' : '' }>${ i } <!--db에 저장된 회원의 생년월일 보여주기  -->
									</option>

								</c:forEach>
						</select>년 <select name="memBirth_Month" id="memBirth_Month">
								<c:forEach var="i" begin="01" step="1" end="12">
									<option
										value="<fmt:formatNumber type='number' value='${i}' pattern='00'/>"
										" ${i==member.memBirth_Month ? 'selected="selected"' : '' }><fmt:formatNumber
											type='number' value='${i}' pattern='00' /></option>
								</c:forEach>
						</select> 월 <select name="memBirth_Day" id="memBirth_Day">
								<c:forEach var="i" begin="01" step="1" end="31">
									<option
										value="<fmt:formatNumber type='number' value='${i}' pattern='00'/>"
										" ${i==member.memBirth_Day ? 'selected="selected"' : '' }><fmt:formatNumber
											type='number' value='${i}' pattern='00' /></option>
								</c:forEach>
						</select>일</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="수정완료" > <input
								type="button" value="탈퇴"
								onclick="location.href='/member/delete1'"></td>
					</tr>
				</table>
			</form>
		</div>
	</article>
</section>



<%@ include file="../include/footer.jsp"%>