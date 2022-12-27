function chkForm() {
	var f = document.frm;
	if (f.title.value == '') {
		alert("성명을 입력해주십시오.");
		return false;
	}
	if (f.user_id.value == '') {
		alert("비밀번호를 입력해주십시오.");
		return false;
	}
	f.submit();
}

function chkDelete(board_no) {
	const result = confirm("삭제하시겠습니까?");
	
	if(result) {
		const url = location.origin;
		location.href = url + "/board2/delete?board_no=" + board_no;
	} else {
		return false;
	}		
}