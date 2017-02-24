/**
 * 
 */
	$(document).ready(function() {
		$(".admin").hide();
		
		loadPlayer();
		//유튜브 동영상 로드
	 mKey=$('.mKey').val();
	 memKey=$('.memKey').val();
	 memLevel = $('.memLevel').val();
		
		
		if (memLevel != null) {
			if (memLevel == 'ADMIN') {
				$('.admin').show();
				}
			}//맴버 레벨 체크

		$('.up').click(function() {
			location.replace("/movie/update/"+mKey);
		});

		$('.del').click(function() {
			location.replace("/movie/delete/"+mKey);
		});
		//관리자 버튼
		$(".myContext").hide();
		$('#viewReply').click(function() {
			$('.myContext').fadeToggle();
						});
		//개인 덧글 보기
		
		$('.moreCallIn').click(startReply);
		
		startReply();
});
	var mKey;
	var memKey;
	var memLevel;
	var stNum = 0;//덧글 더보기 초기화값
	
	function startReply() {
		var mKey=$('.mKey').val();
		$.ajax({
			mothed : 'GET',
			url : '/movie/reply/moreCallIn',
			data : {
				mKey : mKey,
				stNum : stNum
			}
		}).done(function(msg) {
			
			for (var i = 0; i < msg.length; i++) {
				var html = '<div class="context">';
				html += '<div class="tag1">';
				html += '<img class="tag2" src="/resources/images/movie/men.jpg">';
				html += '</div>';
				html += '<div class="review">';
				html += '<a class="name">'+ msg[i].memName+ '</a> <span class="reviewStar">평가: '+ msg[i].star+ '</span>'
				html += '<p class="text">'+ msg[i].reContent.replace(/\n/gi, '<br>' )+ '</p>';
				html += '<div class="reviewFoot">';
				html += '<span class="updateDay">'+ msg[i].reDate+ '</span> ';
				html += '<span class="goodTotal">';
				html += '<input type="hidden" name="reKey" value="' + msg[i].reKey + '">';
				html += '<img class="goodImg" id="goodImg' + msg[i].reKey + '" src="/resources/images/movie/like-512.png"></span>';
				html += '<div class="goodNum" id="goodNum' + msg[i].reKey + '">'+ msg[i].reGood+ '</div>';
				html += '</div></div></div>';

				$('.asd').append(html);
				

			}
		stNum += msg.length;
		//덧글 더보기
if (0==msg.length) {
	$('.moreCallIn').text('마지막 덧글 입니다.');
}
		
		$('#reSize').text('('+ stNum+ ')');
		$('.goodImg').unbind('click');
		$('.goodImg').click(function() {
			var reKey = $(this).prev().val();
		$.ajax({
				method : 'GET',
				url : '/movie/Good/setGood',
				data : {
					mKey : mKey,
					reKey : reKey,
					memKey : memKey
				}
			}).done(function(msg) {
				if (msg == "missing") {
					alert("로그인 후 참여하실 수 있습니다.")
				} else if (msg == "overlap") {
					alert("이미 좋아요를 누르셨습니다.")
				}else if(msg=="myRep"){
						alert("내 덧글에는 좋아요를 누를 수 없습니다.")
				} else {$('#goodNum'+ reKey).text(msg);
				}
			});
		//덧글 좋아요
		});
	});
}

	var player = new Array();
	function loadPlayer() {
		var mTra1=$('.mTra1').val();
		var mTra2=$('.mTra2').val();
		var mTra3=$('.mTra3').val();
		
		var list = [ mTra1, mTra2, mTra3 ];

		var tag = document.createElement('script');

		tag.src = "https://www.youtube.com/iframe_api"; // 요게 api 스크립트

		var firstScriptTag = document.getElementsByTagName('script')[0];

		firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

		window.onYouTubePlayerAPIReady = function() {

			$.each(list, function(index, value) {
				onYouTubePlayer(index, value);
			});

		}
	}

	function onYouTubePlayer(i, url) {
		var id = 'player' + i;
		player[i] = new YT.Player(id, { //요게 html element id

			height : '422',

			width : '750',

			videoId : url

		});
	}

	function pauseVideo(i) {
		var t = player[i];
		player[i].pauseVideo();
	} //중지

	function stopVideo(i) {
		var t = player[i];
		player[i].stopVideo();
	} //스톱
	function playVideo(i) {
		var t = player[i];
		player[i].playVideo();
	} //재생
	
	//유튜브 API관련
	function send1() {
		var mKey=$('.mKey').val();
		var memKey=$('.memKey').val();
		var memName=$('.memName').val();
		if (mKey != "") {
			$.ajax({
				method : 'POST',
				url : '/movie/Reply/setReply',
				data : {
					memKey : memKey,
					mKey : mKey,
					memName : memName,
					reContent : $('.textarea').val()//텍스트값
				}
			}).done(function(msg) {
				if (0 == msg) {
					alert('로그인후 사용 가능 합니다.');
				} else if (1 == msg) {
					alert('입력성공!');
					$('.asd').html('');
					$('.moreCallIn').text('더보기');
					stNum = 0;
					startReply();
				} else if (2 == msg) {
					$('.asd').html('');
					$('.moreCallIn').text('더보기');
					stNum = 0;
					startReply();
					alert('수정성공!');
				} else if (3 == msg) {
					alert('별점 등록후 입력 가능 합니다.');
				}
			})
		} else {
			alert("영화정보가 없어 덧글을 등록할 수 없습니다.")
		}
	}//덧글 입력

	function getReply() {
		var mKey=$('.mKey').val();
		var memKey=$('.memKey').val();

		if (mKey != "") {
			$.ajax({
				method : 'GET',
				url : '/movie/reply/getReply',
				data : {
					memKey : memKey,
					mKey : mKey
				}
			}).done(function(msg) {

				encodeURIComponent($('.textarea').text(msg));

			})
		}
		$("#viewReply").hide();
	}//덧글 출력
	function setStar() {
		var mKey=$('.mKey').val();
		var memKey=$('.memKey').val();
		var setStar = $(".setStar option:selected").val();
		if (mKey != "") {
			$.ajax({
				method : 'GET',
				url : '/movie/star/setStar',
				data : {
					mKey : mKey,
					memKey : memKey,
					star : setStar
				}
			}).done(function(msg) {
				/* 	$(obj).부모.next */
				if (msg == "missing") {
					alert("로그인 후 참여하실 수 있습니다.")
				} else {
					$('.getStar').text(msg + '점으로 별점이 등록되었습니다');

				}
			})
		} else {
			alert("영화정보가 없어 등록할 수 없습니다.")
		}

	}//별점 등록 및 출력
	function zzim() {
		var mKey=$('.mKey').val();
		var memKey=$('.memKey').val();
		if (mKey != "") {
			$.ajax({
				method : 'GET',
				url : '/movie/Zzim/setZzim',
				data : {
					memKey : memKey,
					mKey : mKey
				}
			}).done(function(msg) {

				if (1 == msg) {
					alert("찜 목록에 추가 되었습니다.")
				} else if (2 == msg) {
					alert("찜 목록에서 삭제 되었습니다")
				} else if (0 == msg) {
					alert("로그인 후 찜목록에 추가할 수 있습니다.")
				}

			})

		} else {
			alert("영화정보가 없어 등록할 수 없습니다.")
		}
	}
	//찜 등록
	function layer_open(el, i) {
		var temp = $('#' + el + i);
		var bg = temp.prev().hasClass('bg'); //dimmed 레이어를 감지하기 위한 boolean 변수

		if (bg) {
			var la = $('#layer' + i);
			//$('.layer').fadeIn(); //'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다. 
			la.fadeIn();
			playVideo(i);

		} else {
			temp.fadeIn();
		}

		// 화면의 중앙에 레이어를 띄운다.
		if (temp.outerHeight() < $(document).height())
			temp.css('margin-top', '-' + temp.outerHeight() / 2 + 'px');
		else
			temp.css('top', '0px');
		if (temp.outerWidth() < $(document).width())
			temp.css('margin-left', '-' + temp.outerWidth() / 2 + 'px');
		else
			temp.css('left', '0px');

		temp.find('a.cbtn').click(function(e) {
			if (bg) {
				var la = $('#layer' + i);
				//$('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다.
				la.fadeOut();
				player[i].stopVideo();
			} else {
				temp.fadeOut();
			}
			e.preventDefault();
		});

		$('#layer' + i + ' .bg').click(function(e) { //배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
			function pauseVideo() {
				player[i].pauseVideo();
			}
			var la = $('#layer' + i);
			la.fadeOut();
			player[i].stopVideo();
			e.preventDefault();
		});

	}
	//레이어