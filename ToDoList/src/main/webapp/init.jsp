<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />

</head>

<body>
	<%@ include file="header.jsp"%>
	<section>
	<div class="init">
		<div class="side">a</div>
		<div class="main">
			<form method="post" name="frm" action="index.jsp" >
				<label>이름을 입력해주세요 : <input type="text" name="username"></label><br>
				<label>아이디도 입력해주세요 : <input type="text" name="userid"></label>
			</form>
		</div>
		<div class="side">b</div>
	</div>
		
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>