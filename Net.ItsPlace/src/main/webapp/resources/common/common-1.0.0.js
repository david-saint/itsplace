/*
 * 공통 스크립트
 * ver1.0 김동훈 2011-08-21
 * param
 * return
 * 
 */


$(document).ready(function() {
	$('.clear_form').live('click',function() {
		c.log("clear");
		c.clearForm();
	});
	
	
});
var c = {
		
		movePage : function(url) {
		    document.location.href= url;
		  
		},
		log : function(message) {
			if(window.console){
				console.log(message);
			}
		    
		},
		clearForm : function(){
			$('form').each(function(index) {	  
				var form_id=$('form:eq('+index+')').attr('id');
					  if(form_id){ 
						  $('#'+form_id).get(0).reset(); 
						  $('#'+form_id).validationEngine('hideAll');
								  var editor=$('#'+form_id).find('#editor').attr('id');
								  if(editor){
									   $('#editor').cleditor()[0].clear();
								  }
					  } 
			  });	
	
		}		
};//common		
	
