<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<h3>param 액션 태그</h3>
	<%-- 데이터를 전송할 페이지 지  --%>
	<jsp:include page="param02_data.jsp">	
		<jsp:param name="title" value='<%=java.net.URLEncoder.encode("오늘의 날짜와 시각")%>' />
		<jsp:param name="date" value='<%=java.util.Calendar.getInstance().getTime()%>' />
	</jsp:include>	
</body>
</html>