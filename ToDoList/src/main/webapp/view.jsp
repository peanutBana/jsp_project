<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<%-- <%
	String name = (String)session.getAttribute("name");
	int id = (Integer)session.getAttribute("id");
%> --%>
</head>
<body>
<header><%-- [<%=name%>]님의 TodoList, 아이디는 <%=id%> --%>header</header>

<section>
	
	

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