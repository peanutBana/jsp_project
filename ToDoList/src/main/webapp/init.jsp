<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="./css/style.css" />
	<script type="text/javascript" src="./javascript/script.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section>
		<fieldset>
		<legend>로그인</legend>
			<form method="post" name="frm" action="index.jsp" >
				<label>이름을 입력해주세요 : <input class="init" type="text" name="username"></label><br>
				<label>아이디도 입력해주세요 : <input class="init" type="text" name="userid"></label><br>
				<div class="btns">
					<button type="submit" onclick="location.href='index.html'">로그인</button> 
					<button type="reset" onclick="reset_alert()">다시하기</button>
				</div>
			</form>
		</fieldset>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>