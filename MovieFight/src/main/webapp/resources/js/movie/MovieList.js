/**
 * MOVIE LIST.JS
 */
	$(document).ready(function() {
		$(".admin").hide();
		var memLevel = $('.memLevel').val();
		if (memLevel !=null) {
			if (memLevel=='ADMIN') {
				$('.admin').show();
			}
		}//멤버 권한 체크

		$('.in').click(function() {
			location.replace("/movie/insert");
		});
	});
	//관리자 권한버튼