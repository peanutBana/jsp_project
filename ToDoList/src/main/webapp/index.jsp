<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<%
	String name = request.getParameter("username");
	int id = Integer.parseInt(request.getParameter("userid")); 
%>
</head>
<body>
	<header><%=name%>님 의 TodoList, 아이디는<%=id%></header>
		<section>
			
		</section>
	<footer>footer area</footer>
</body>
</html>