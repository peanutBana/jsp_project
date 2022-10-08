<html>
	<head>
		<title>Scripting Tag</title>
	</head>
	<body>
		<h2>Scripting Tag</h2>
		
		<%-- 선언문 테그 --%>
		<%!  											
			int count = 3;		
			String makeItLower(String data){		
				return data.toLowerCase();
			}
		%>
		
		<%-- 스크립틀 테그 --%>
		<%
			for (int i=1 ; i<= count ; i++){
				out.println("Java Server Pages "+ i + "<br>");
			}
		%>
		
		<%-- 표현 테그 --%>
		<%=
			makeItLower("Hello World")
		%>
	</body>
</html>