<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<%-- value:encode("관리자")->name --%>
	<%
		String title = request.getParameter("title");	
	%>
	
	<h3><%=java.net.URLDecoder.decode(title)%></h3>
	Today is :<%=request.getParameter("date") %>
	
</body>
</html>