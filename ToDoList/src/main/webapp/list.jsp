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
	String name = (String)session.getAttribute("name");
	int id = (int)session.getAttribute("id");
%>

</head>
<body>
	<header>[<%=name%>]님의 TodoList, 아이디는 <%=id%></header>
		<section>
			<div class="insert">
				<form method="post" name="frm_todo" action="insert">
					<label>해야 할 일 추가! :<input class="content" type="text" name="todo" maxlength="100" placeholder="할 일을 입력해주세요!"></label>
					<button class= "btns" type="submit" onclick="fn_todoInput_submit()">등록</button>
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
								<div class="a"><a href="#">edit</a>&nbsp;&nbsp;<a href="chkDelete(${todo.todoId}); return false;">delete</a></div>
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
								<div class="a"><a href="chkDelete(${todo.todoId}); return false;">delete</a></div>
							</div>
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</section>
	<footer>footer area</footer>
	<script>
	  <c:if test="${param.error != null}">
	    alert("${error}");
	  </c:if> 
	  <c:if test="${error != null}">
	    alert("${error}");
	  </c:if>
	</script>
	<script type="text/javascript" src="./javascript/script.js"></script>
</body>
</html>