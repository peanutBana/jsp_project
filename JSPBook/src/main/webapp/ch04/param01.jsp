<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<h3>param 액션 태그</h3>
	<%--데이터를 전송할 페이지 지정--%>
	<jsp:forward page="param01_data.jsp">	
		<jsp:param name="id" value="admin" />
		<jsp:param name="name" value='<%= java.net.URLEncoder.encode("관리자") %>' />
	</jsp:forward>
	<p>Java Server Page
	
</body>
</html>