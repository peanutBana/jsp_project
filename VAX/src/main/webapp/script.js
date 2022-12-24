function fn_submit() {
   var fn = document.frm;

   //유효성 체크
   if (fn.resrvNo.value == "") {   //입력창 custname부분이 null이면
      alert("주민번호가 입력되지 않았습니다.");
      fn.resrvNo.focus();   //포커싱
      
      return false;   //함수 끝
   }
   if (fn.jumin.value == "") {
      alert("백신코드가 입력되지 않았습니다.");
      fn.jumin.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.vCode.value == "") {
      alert("백신코드가 입력되지 않았습니다.");
      fn.m_vCodeno.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.hospCode.value == "") {
      alert("병원코드가 입력되지 않았습니다.");
      fn.hospCode.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.resvDate.value == "") {
      alert("예약 일자가 입력되지 않았습니다.");
      fn.resvDate.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.resvTime.value == "") {
      alert("예약시간이 입력되지 않았습니다.");
      fn.resvTime.focus();   //포커싱
      return false;   //함수 끝
   }
   fn.submit();  
   //fn.action="index.jsp";
}

function reset_alert(){
	alert("정보를 지우고 처음부터 다시 입력합니다!");
}