<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DTO.User" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<% 
	User user = (User)session.getAttribute("user");
	String name = user.getUserName();
	int id = user.getUserId();
%>
</head>
<body>
	<header>[<%=name%>]님의 TodoList, 아이디는 <%=id%></header>
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
						<li>
							<div class="item">
								<input type=checkbox name="todo_chk">
								<div class="todo_title">${todo.todoTitle}</div>
								<div class="a"><a href="#">edit</a>&nbsp;&nbsp;<a href="#">delete</a></div>
							</div>
						</li>
					</c:forEach>
					</ul>
				</div>
				<vr></vr>
				<div class="fin">
					<p>완료한 목록</p>
					<ul>
					<c:forEach var="todo" items="${todoListFin}" varStatus="status">
						<li>
							<div class="item">
								<input type=checkbox name="todo_chk">
								<div class="todo_title">${todo.todoTitle}</div>
								<div class="a"><a href="#">delete</a></div>
							</div>
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</section>
	<footer>footer area</footer>
</body>
</html>