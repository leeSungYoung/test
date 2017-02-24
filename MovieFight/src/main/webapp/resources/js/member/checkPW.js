
	function alter1() { // 기본정보 수정/탈퇴 버튼 페이지 링크
				var form = $('#form');
				form.attr('action', '/member/update1');
				form.attr('method', 'post');
				form.submit();
			}

			function alter2() { // 비밀변호변경 버튼 페이지 링크
				var form = $('#form');
				form.attr('action', '/member/updatePW1');
				form.attr('method', 'post');
				form.submit();
			}