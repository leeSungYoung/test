

function check() {
				if (document.getElementById('memPw').value == '') { //비밀번호 미입력시 경고
					alert("비밀번호를 확인해주세요");
					return false;
				}

				if (document.getElementById('memPw').value !== document
						.getElementById('memPw2').value) { // 1차 비밀번호와 2차 비밀번호 값 불일치시 경고
					alert("두개의 비밀번호가 일치하지 않습니다.");
					return false;
				}
			}