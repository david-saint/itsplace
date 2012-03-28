/**
  함수명은 파일명_함수명 으로 구성한다. 
  공통 스크립트
  ver1.0 김동훈
*/


$(document).ready(function() {
	log.info("call: ");
	/*$('input[@type=text]').focus(function() {
		  $(this).addClass("focused");
	 });
	
	$('input[@type=text]').blur(function() {
		  $(this).removeClass("focused");
	 });
	 */
	
	$('#btn_ajaxTest').click(function() {
		json_testAjax();
	});
	$('#btn_JsonTest').click(function() {
		json_jsonAjax();
	});
	$('#btn_JsonObject').click(function() {
		json_JsonObject();
	});
});

