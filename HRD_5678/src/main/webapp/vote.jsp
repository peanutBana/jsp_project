<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%	
	request.setCharacterEncoding("UTF-8");
	int custno = 0;
	custno = (Integer) request.getAttribute("custno");
%> --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<script type="text/javascript" src="script.js"></script>
	<title>Insert title here</title>
</head>
<body>
<%@ include file="topmenu.jsp" %>
<section>
	<div class="title">투표하기</div>
	<form name="frm" action="insert">
		<input type="hidden" id="GUBUN" value="insert">
		<div class="wrapper">
			<table>
				<tr>
					<th>주민번호</th>
					<td><input type="text" name="v_jumin"> 예 : 9806153154726</td>
				</tr>
				<tr>
					<th>성명</th>
					<td><input type="text" name="v_name"></td>
				</tr>
				<tr>
					<th>투표번호</th>
					<td>
						<select name="m_no">
							<option value="1">[1] 김후보</option>
							<option value="2">[2] 이후보</option> 
							<option value="3">[3] 박후보</option>
							<option value="4">[4] 조후보</option>
							<option value="5">[5] 최후보</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>투표시간</th>
					<td><input type="text" name="v_time"></td>
				</tr>
				<tr>
					<th>투표장소</th>
					<td><input type="text" name="v_area"></td>
				</tr>
				<tr>
					<th>유권자확인</th>
					<td>
						<label><input type="radio" name="v_confirm" value="Y">확인</label>
						<label><input type="radio" name="v_confirm" value="N">미확인</label>				
					</td>
				</tr>
				<tr>
					<td colspan="2" class="btn">
						<button type="submit" onclick="fn_submit(); return false;">투표하기</button>
						<button type="reset" onclick="reset_alert()">다시하기</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
</section> 

<%@ include file="footer.jsp" %> 
</body>
</html>