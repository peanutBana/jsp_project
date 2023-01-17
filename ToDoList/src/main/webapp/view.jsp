<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<header>Todo 수정</header>

<section>
	<div class="view_list">
		<table>
			<tr>
				<td>column</td>
				<td>value</td>
			</tr>	
			<tr>
				<td>아이디 번호</td>
				<td>${todo.todoId}</td>
			</tr>
			<tr>
				<td>Todo 제목</td>
				<td>${todo.todoTitle}</td>
			</tr>
			<tr>
				<td>작성자 아이디 </td>
				<td>${todo.userId}</td>
			</tr>
			<tr>
				<td>메모</td>
				<td>${todo.todoMemo}</td>
			</tr>
			<tr>
				<td>수행 여부</td>
				<td>${todo.isFinished}</td>
			</tr>
		</table>
		<a href="edit?todoId=${todo.todoId}" class="a_edit">수정하기</a>
		<a href="list" class="a_edit">뒤로가기</a>
	</div>
</section>


<%@ include file="footer.jsp"%>
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