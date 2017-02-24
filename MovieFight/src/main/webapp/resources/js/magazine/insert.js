$(document).ready(function () {
    
    //textarea위치에 CKEDITOR 생성
    CKEDITOR.replace("textAreaContent", {
        skin: 'bootstrapck',
        width: '100%',
        height: '630px',
        filebrowserImageUploadUrl: '/magazine/insert/image'
    })

    $('form#mag_form').submit(function () {
        //등록버튼 클릭시
        
        /*위지윅 에디터 값을 textarea에 복사하기*/
        var ev = CKEDITOR.instances.textAreaContent.getData();
        $('#textAreaContent').val(ev);

        /*제목 체크*/
        var t = $('input#magTitle').val().trim();
        if (t == '') {
            alert('제목을 입력해주세요');
            $('input#magTitle').focus();
            return false;
        }

        /*부제 체크*/
        var st = $('input#magSubTitle').val().trim();
        if (st == '') {
            alert('부제목을 입력해주세요');
            $('input#magSubTitle').focus();
            return false;
        }

        /*textarea 값 체크*/
        var a = $('textarea#textAreaContent').val().trim();
        if (a == '') {
            alert('내용을 입력해주세요');
            var ev = CKEDITOR.instances.textAreaContent.focus();
            return false;
        }
    })
});