function fn_login_submit() {
   var fn = document.frm_login;

   //유효성 체크
   if (fn.username.value == "") {   //입력창 custname부분이 null이면
      alert("이름이 입력되지 않았습니다.");
      fn.username.focus();   //포커싱
      
      return false;   //함수 끝
   }
   if (fn.v_name.value == "") {
      alert("성명이 입력되지 않았습니다.");
      fn.v_name.focus();   //포커싱
      return false;   //함수 끝
   }
   fn.submit();  
   //fn.action="index.jsp";
}

function reset_alert(){
	alert("정보를 지우고 처음부터 다시 입력합니다!");
}