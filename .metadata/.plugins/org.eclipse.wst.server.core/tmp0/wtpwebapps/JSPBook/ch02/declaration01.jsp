<html>
	<head>
		<title>Scripting Tag</title>
	</head>
	<body>
		
		<%-- 선언문 테그 내용은 서블릿 프로그램으로 번역될 때 _jspService() 메소드 외부에 배치되어 전역변수 선언--%>
		<%!  
			int data = 50;
		%>
	
		<%
			out.println("Value of the variable is : " + data);
		%>
		
	</body>
</html>