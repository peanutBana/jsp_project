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
	int id = (Integer)session.getAttribute("id");
%>
</head>
<body>

	<header>[<%=name%>]님의 TodoList, 아이디는 <%=id%></header>
		<section>
			<div class="insert">
				<form method="post" name="frm_todo" action="insert">
					<label>해야 할 일 추가! :<input class="content" type="text" name="todo" maxlength="100" placeholder="할 일을 입력해주세요!"></label>
					<input type="hidden" name="id" value="<%=id%>">
					<button class= "btns" type="submit" onclick="fn_todoInput_submit()">등록</button>
				</form>
			</div>
			<div class="list">
				<div class="undo">
					<form method="get" name="frm_list" action="update">
						<div class="list_header">
							<p>할 일 목록</p>
							<input type="button" value="전체선택" onclick='selectAllUndo(this)'/>
							<input type="button" value="전체취소" onclick='cancelAllUndo()'/>
							<input type="submit" value="갱신"/>
						</div>
						<ul>
							<c:forEach var="todo" items="${todoList}" varStatus="status">
								<li>
									<div class="item">
										<input type=checkbox name="todo_chk">
										<div class="todo_title"><a href="./view?todoId=${todo.todoId}">${todo.todoTitle}</a></div>
										<div><a href="#" onclick="chkDelete(${todo.todoId}); return false;">delete</a></div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</form>
				</div>
				<vr></vr>
				<div class="fin">
					<div class="list_header">
							<p>완료한 목록</p>
							<input type="button" value="전체선택" onclick='selectAllFin(this)'/>
							<input type="button" value="전체취소" onclick='cancelAllFin()'/>
						</div>
					<ul>
					<c:forEach var="todo" items="${todoListFin}" varStatus="status">
						<li>
							<div class="item">
								<input type=checkbox name="fin_chk">
								<div class="todo_title">${todo.todoTitle}</div>
								<div><a href="#" onclick="chkDelete(${todo.todoId}); return false;">delete</a></div>
							</div>	
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</section>
	<%-- <%@ include file="footer.jsp"%> --%>
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