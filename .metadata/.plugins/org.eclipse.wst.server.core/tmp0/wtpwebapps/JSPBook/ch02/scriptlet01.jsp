<html>
	<head>
		<title>Scripting Tag</title>
	</head>
	<body>
		<%-- 스크립틀릿 테그에는 메소드x, only 변수만 선언 가능 --%>
		<%
			int a = 2;
			int b = 3;
			int sum = a+b;
			out.println("2 + 3 = "+sum);
			
		%>
		
	</body>
</html>