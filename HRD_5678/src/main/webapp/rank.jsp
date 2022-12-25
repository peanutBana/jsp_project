<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.Candidate" %>
<%@ page import="java.util.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<Candidate> list = new ArrayList<Candidate>();
	list = (ArrayList<Candidate>) request.getAttribute("list"); 
%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="topmenu.jsp" %>
<section>
	<div class="title">후보자등수</div>
	<div class="wrapper">
		<table style="width:900px">
			<tr>
				<th>후보번호</th>
				<th>성명</th>
				<th>총투표건수</th>
			</tr>
			<% for (Candidate c : list) {%>
				<tr>  					
					<td><%=c.getM_no()%></td>
					<td><%=c.getM_name()%></td>
					<td><%=c.getCount()%></td>							
				</tr>
			<% } %>
		</table>
	</div>
</section> 
<%@ include file="footer.jsp" %> 
</body>
</html>