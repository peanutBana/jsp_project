<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<div class="insert">
				<form method="post" name="frm_todo" action="insert">
					<label>해야 할 일 추가! :<input class="content" type="text" name="todo" maxlength="100" placeholder="할 일을 입력해주세요!"></label>
					<button class= "btns" type="submit" onclick="#">등록</button>
				</form>
			</div>
			<div class="list">
				<div class="undo">
					<p>할 일 목록</p>
					<ul>
					<c:forEach var="todo" items="${todoList}" varStatus="status">
						<li><div class="todo_title">•${todo.todoTitle}</div><div class="a"><a href="#">edit</a>&nbsp;&nbsp;<a href="#">delete</a></div></li>
					</c:forEach>
						<!-- <li><div class="todo_title">•강아지 산책시키기</div><div class="a"><a href="#">edit</a>&nbsp;&nbsp;<a href="#">delete</a></div></li>
						<li><div class="todo_title">•개인 프로젝트</div><div class="a"><a href="#">edit</a>&nbsp;&nbsp;<a href="#">delete</a></div></li>
						<li><div class="todo_title">•spring 예습</div><div class="a"><a href="#">edit</a>&nbsp;&nbsp;<a href="#">delete</a></div></li> -->
					</ul>
				</div>
				<vr></vr>
				<div class="comp">
					<p>완료한 목록</p>
					<ul>
						<!-- <li><div class="todo_title">•html 공부</div><a href="#">delete</a></li>
						<li><div class="todo_title">•css 공부</div><a href="#">delete</a></li></li>
						<li><div class="todo_title">•javascript 공부</div><a href="#">delete</a></li></li> -->
					</ul>
				</div>
			</div>
		</section>
	<footer>footer area</footer>
</body>
</html>