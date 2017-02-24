/**
 *  movie/insert script
 */

$(document).ready(function () {

                $('input:file').change(function (e) {
                    //그림파일 첨부시 미리보기 기능

                    var input = e.target;
                    var fileList = input.files;

                    var tempName = fileList[0].name;
                    var tempExt = tempName.slice(tempName.indexOf(".") + 1).toLowerCase();
                    var Ext = ["jpg", "gif", "png", "jpeg", "jpe", "jfif", "tif", "tiff"];
//                    var Ext = ['jpg', 'gif', 'png', 'jpeg', 'jpe', 'jfif', 'tif', 'tiff'];
//                    var Ext = new Array('jpg', 'gif', 'png', 'jpeg', 'jpe', 'jfif', 'tif', 'tiff');

                    var imgCheck = false;
                    for(var i = 0;i<Ext.length;i++){
                        if(Ext[i] == tempExt){
                            imgCheck = true;
                        }
                    }
                    
                        var img = $(input).parent('.mv_pos_btn').prev('.mv_pos_img').children()[0];
                    
                    if(!imgCheck){
                        img.src= '/resources/images/common/no_image_found.jpg';
                        $(input).val('');
                        return;
                    }
                    // 읽기
                    var reader = new FileReader();
                    reader.readAsDataURL(fileList[0]);

                    //로드 한 후
                    reader.onload = function () {
                        img.src = reader.result;
                    };
                });
    
                $('input[id=delfile]').click(function(){
                    //이미지 삭제 버튼
                    $(this).parent('.mv_pos_btn').prev('.mv_pos_img').children('#fileName').val('');
                    $(this).parent('.mv_pos_btn').prev('.mv_pos_img').children('#preview').attr('src','/resources/images/common/no_image_found.jpg');
                    $(this).prev('input[type=file]').val('');
                })

                /*팝업 띄우기*/
                $('#movie_select').click(function (e) {
                    var url = "/movie/insert/search";
                    window.open(url, "_blank", "fullscreen=no,location=no,menubar=no,toolbar=no,status=no,resizable=yes,width=750,height=500,left=570,top=150");
                });

                /*submit onclick*/
                $('form#movie_form').submit(function () {
                    /*영화 선택 체크*/
                    var m = $('input#mTitle').val().trim();
                    if (m == '') {
                        alert('영화를 선택해주세요');
                        $('input#movie_select').focus();
                        return false;
                    }

                    /*줄거리 입력 체크*/
                    var c = $('textarea#mSummary').val().trim();
                    if (c == '') {
                        alert('줄거리를 입력해주세요.');
                        $('textarea#mSummary').focus();
                        return false;
                    }

                });

                //유튜브 미리보기
                $('div.mv_tr_box .preview').click(function () {
                    var preview_layer = $('div.movie_youtube_preview #ctxt');
                    var url = $(this).prev('input').val().trim();
                    if (url == '') {
                        alert('정확한 키 값을 입력 해주세요.');
                        return;
                    } else {
                        var html = '';
                        html = '<iframe id="ytplayer" type="text/html" width="750px" height="422"';
                        html += 'src="http://www.youtube.com/embed/' + url + '?';
                        html += 'autoplay=1"frameborder="0"/>';

                        preview_layer.append(html);
                        layer_open();
                    }
                });

    
            });

            function layer_open() {
                //레이어 띄우기
                var temp = $('#layerTwo');
                var bg = temp.prev().hasClass('bg'); //dimmed 레이어를 감지하기 위한 boolean 변수

                if (bg) {
                    var la = $('#layer');
                    //$('.layer').fadeIn(); //'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다. 
                    la.fadeIn();

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

                temp.find('a.cbtn').click(function (e) {
                    var preview_layer = $('div.movie_youtube_preview #ctxt');
                    if (bg) {
                        var la = $('#layer');
                        //$('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다.
                        la.fadeOut();
                    } else {
                        temp.fadeOut();
                    }
                    ytb_preview_stop();
                    $(preview_layer).empty();
                    e.preventDefault();
                });

                $('#layer .bg').click(function (e) { //배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러

                    var preview_layer = $('div.movie_youtube_preview #ctxt');
                    var la = $('#layer');
                    la.fadeOut();
                    ytb_preview_stop();
                    $(preview_layer).empty();
                    e.preventDefault();
                });

            }

            function ytb_preview_stop() {
                //유튜브 정지 기능
                var testPlayer0 = $('#ytplayer');
                var testPlayer = testPlayer0[0];
                testPlayer.contentWindow.postMessage('{"event":"command","func":"stopVideo","args":""}', '*');
                //        playVideo > 재생
                //        pauseVideo > 일지정지
                //        stopVideo > 중지
            }