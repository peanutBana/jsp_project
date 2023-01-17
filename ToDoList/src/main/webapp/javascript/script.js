function fn_login_submit() {
   var fn = document.frm_login;
   //유효성 체크
   if (fn.username.value == '') {   //입력창 custname부분이 null이면
      alert("이름이 입력되지 않았습니다.");
      fn.username.focus();   //포커싱
      return false; 

   }else{
   	  fn.submit();  
	
   }
   if (fn.userid.value == '') {
      alert("아이디가 입력되지 않았습니다.");
      fn.userid.focus();   //포커싱
      return false;   //함수 끝
   }
   else{
   	  fn.submit();  
   }
   //fn.action="index.jsp";
}

function reset_alert(){
	alert("정보를 지우고 처음부터 다시 입력합니다!");
}

function fn_todoInput_submit(){
	var f = document.frm_todo;
	
	if(f.todo.value == ""){
	  alert("todo가 입력되지 않았습니다.");
      fn.todo.focus();   //포커싱
      return false; 
	}
	else{
		f.submit();	
	}
}

function chkDelete(todoId) {
	const result = confirm("삭제하시겠습니까?");
	
	if(result) {
		const url = location.origin;
		location.href = url + "/ToDoList/delete?todoId=" + todoId;
	} else {
		return false;
	}		
}

function chkUpdate(todoId){
	const result = confirm("갱신 하시겠습니까?");
	
	if(result){
		const url = location.origin;
		location.href = url + "/ToDoList/chkupdate?todoId=" + todoId;
	}else{
		return false;
	}
}

$(document).ready(function(){
	if($("todo_chk").is(":checked")){
		updateChk(todoId);
	}
})

function selectAllUndo(chk){
	var chks = document.getElementsByName("todo_chk");
	for(var i=0; i < chks.length; i++){
		chks[i].checked = chk;
	}
}

function selectAllFin(chk){
	var chks = document.getElementsByName("fin_chk");
	for(var i=0; i < chks.length; i++){
		chks[i].checked = chk;
	}
}

function cancelAllUndo(){
	const chks = document.getElementsByName("todo_chk");
	chks.forEach((chkBox) => {
		chkBox.checked = false;
	} )
}

function cancelAllFin(){
	const chks = document.getElementsByName("fin_chk");
	chks.forEach((chkBox) => {
		chkBox.checked = false;
	} )
}




