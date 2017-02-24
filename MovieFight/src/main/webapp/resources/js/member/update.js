function check() {

	if (document.getElementById('memName').value == '') { //이름 미입력시 경고
		alert("이름을 입력해주세요");
		return false;
	}
}