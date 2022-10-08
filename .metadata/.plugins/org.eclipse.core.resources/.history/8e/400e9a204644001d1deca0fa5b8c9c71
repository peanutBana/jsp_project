<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.Date" %>

<html>
<head>
	<title>Welcome</title>
	<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<%-- Bootstrap CSS 작성 --%>
	<nav class = "navbar navbar-expand nav-bar bg-dark">
		<div class = "container">
			<div class = "navbar-header">
				<a class = "navbar-brand" href = "./welcome.jsp">Home</a>
			</div>
		</div>	
	</nav>

	<%-- header --%>
	<%@ include file="menu.jsp" %>

	<%-- 선언문테그 --%>
	<%!
	String greeting = "Welcome to Web Shopping Mall";	
	String tagline = "Welcome to Web Market!";
	%>
	
	<div class = "jumbotron">
		<div class = "container">
			<h1 class = "display-3">
				<%= greeting %>
			</h1>
		</div>
	</div>
	
	<div class = "container">
		<div class = "text-center">
			<h3>
				<%= tagline %>
			</h3>
			<%
				response.setIntHeader("Refresh", 5);
				Date day = new java.util.Date();
				String am_pm;
				int hour = day.getHours();
				int minute = day.getMinutes();
				int second = day.getSeconds();
				if (hour/12 == 0){
					am_pm = "AM";
				} else{
					am_pm = "PM";
					hour = hour-12;
				}
				String CT = hour + ":" + minute +  ":" + second + " " + am_pm;
				out.println("현재 접속 시각 : " + CT + "\n");
			%>
		</div>
	</div>
	<hr>
	<%-- footer (include 디렉티브 태그 이용)--%>
	<%@ include file="footer.jsp" %>
</body>
</html>