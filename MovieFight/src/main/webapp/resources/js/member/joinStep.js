

	function check() {

				if (document.getElementById('memEmail').value == '') { //아이디 미입력시 경고
					alert("아이디를 확인해주세요");
					return false;
				}
				if ($('#checkDup').val() == 'false') {//아이디 중복확인 하지 않았을경우 경고
					alert('아이디 중복 확인을 해주세요');
					return false;
				}

				if (document.getElementById('memPw').value == '') { // 비밀번호 미입력시 경고
					alert("비밀번호를 확인해주세요");
					return false;
				}

				if (document.getElementById('memPw').value !== document
						.getElementById('memPw2').value) { // 1차 비밀번호와 2차 비밀번호 값 불일치시 경고
					alert("두개의 비밀번호가 일치하지 않습니다.");
					return false;
				}

				if (document.getElementById('memName').value == '') { //이름 미입력시 경고
					alert("이름을 확인해주세요");
					return false;
				}
				if (document.getElementById('memBirth_Year').value == '') { //생년월일 미 입력시 경고
					alert("생년월일 확인해주세요");
					return false;
				}

			}

			function checkidDup() { //아이디 중복확인 기능 중복되었을때와 사용가능할때 alert
				var a = $("#memEmail").val().trim();
				$.ajax({
					method : 'post',
					url : '/member/checkidDup',
					data : {
						memEmail : a
					}

				}).done(function(i) {
					var str = '';
					if (i == 1) {
						str = '중복 되었습니다.';
						$('#checkDup').val('false');
					} else {
						str = '사용 가능한 ID입니다.';
						$('#checkDup').val('true');
					}
					alert(str);
				});
			}

			function email() { //이메일 유효성 검사  알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우 경고            
				var login_email = document.getElementById("memEmail").value;
				var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
				
				if (login_email == '') {
					return false;
				} else if (exptext.test(login_email) == false) {
					alert("이메일형식이 올바르지 않습니다.");
					return false;
				}
			}

			function changeId() {
				$('#checkDup').val('false');
			}