<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<script type="text/javascript" src="script.js"></script>
	<title>Insert title here</title>
</head>
<body>
<%@ include file="topmenu.jsp"%>
<section>
	<div class="title">백신접종예약</div>
	<form name="frm" action="insert">
		<div class="wrapper">
			<table>
				<tr>
					<th>접속예약번호</th>
					<td><input type="text" name="resrvNo">예) 20210001</td>
				</tr>
				<tr>
					<th>주민번호</th>
					<td><input type="text" name="jumin">예) 710101-1000001</td>
				</tr>
				<tr>
					<th>백신코드</th>
					<td><input type="text" name="vCode">예) V001 ~ V003</td>
				</tr>
				<tr>
					<th>병원코드</th>
					<td><input type="text" name="hospCode">예) H001</td>
				</tr>
				<tr>
					<th>예약일자</th>
					<td><input type="text" name="resvDate">예) 20211231</td>
				</tr>
				<tr>
					<th>예약시간</th>
					<td><input type="text" name="resvTime">예) 1230</td>
				</tr>
				<tr>
					<td class="btn" colspan="2">
						<button type="submit" onclick="fn_submit(); return false;">등록</button>
						<button type="reset" onclick="reset_alert()">다시쓰기</button>
					</td>
				</tr>
			</table>
		</div>
		
	</form>
</section> 

<%@ include file="footer.jsp" %> 
</body>
</html>