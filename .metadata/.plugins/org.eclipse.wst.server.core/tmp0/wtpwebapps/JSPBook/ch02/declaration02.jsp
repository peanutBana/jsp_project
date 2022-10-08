<html>
	<head>
		<title>Scripting Tag</title>
	</head>
	<body>
		<%-- 선언문 테그 안에는 변수와 메소드 선언 가 --%>
		<%!  
			int sum(int a, int b){
			return a+b;
		}
		%>
		
		<%
			out.println("2+3 = "+ sum(2,3));
		%>
		
	</body>
</html>