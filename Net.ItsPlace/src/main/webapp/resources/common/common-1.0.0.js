/*
 * 공통 스크립트
 * ver1.0 김동훈 2011-08-21
 * param
 * return
 * 
 */


$(document).ready(function() {
	/*$('input[@type=text]').focus(function() {
		  $(this).addClass("focused");
	 });
	
	$('input[@type=text]').blur(function() {
		  $(this).removeClass("focused");
	 });
	 */
	
});
var c = {
		
		movePage : function(url) {
		    document.location.href= url;
		  
		}
		,log : function(message) {
			if(window.console){
				console.log(message);
			}
		    
		}
		
};//common		
