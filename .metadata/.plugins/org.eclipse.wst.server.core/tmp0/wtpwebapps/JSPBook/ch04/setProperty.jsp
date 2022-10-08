<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id ="person" class="ch04.com.dao.Person" scope="request"/>
	
	<%-- 스크립틀릿 태그에서 set 하지않고 setProperty 액션태그를 사용해 한번에 set  --%>
	<jsp:setProperty name="person" property="id" value="20182005" />
	<jsp:setProperty name="person" property="name" value="홍길동" />
	<p>아이디 : <%out.println(person.getId()); %>
	<p>이름 : <%out.println(person.getName()); %>	
</body>
</html>

