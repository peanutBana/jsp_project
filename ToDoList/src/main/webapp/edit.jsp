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
	<div class="edit_list">
		<table>
		<form name="edit_frm" method="post" action="update?todoId=${todo.todoId}">
			<tr>
				<td>column</td>
				<td>value</td>
			</tr>
			<tr>
				<td>아이디 번호</td>
				<td><input type="text" name="todoId" value="${todo.todoId}"/></td>
			</tr>
			<tr>
				<td>Todo 제목</td>
				<td><input type="text" name="todoTitle" value="${todo.todoTitle}"/></td>
			</tr>
			<tr>
				<td>작성자 아이디 </td>
				<td><input type="text" name="userId" value="${todo.userId}"/></td>
			</tr>	
			<tr>
				<td>메모</td>
				<td><textarea name="todoMemo">${todo.todoMemo}</textarea></td>
			</tr>
			<tr>
				<td>수행 여부</td>
				<td><input type="text" name="isFinished" value="${todo.isFinished}"/></td>
			</tr>
			<a href="#" class="on" onclick="chkForm(); return false;">수정</a> 
			<a href="list">취소</a>
		</form>
		</table>
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