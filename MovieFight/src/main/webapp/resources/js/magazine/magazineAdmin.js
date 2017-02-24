/**
 * magaizneList
 */

$(document).ready(function() {
	// admin_option 클래스를 숨겨준다
	$(".admin_option").hide();
	var memLevel = $('.level').val();
	var magKey = $('.magKey').val();
	
	// memLevel이 ADMIN이면 admin_option 클래스를 보여준다
	if (memLevel !=null) {
		if (memLevel =='ADMIN') {
			$('.admin_option').show();
		}
	}
	
	// insert 클래스를 클릭했을 때 /magazine/insert로 보내준다
	$('.insert').click(function() {
		location.replace("/magazine/insert");
	});
	// update 클래스를 클릭했을 때 /magazine/update로 보내준다
	$('.update').click(function() {
		location.replace("/magazine/update/"+magKey);
	});
	// delete 클래스를 클릭했을 때 /magazine/delete로 보내준다
	$('.delete').click(function() {
		location.replace("/magazine/delete/"+magKey);
	});
	
});