/*
 * 공통 스크립트
 * ver1.0 김동훈 2011-08-21
 * param
 * return
 * 
 */


$(document).ready(function() {
	$('.clear_form').live('click',function() {
		c.log("clear Form");
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
	
		},
		loading : function(name,overlay) { 
			$('body').append('<div id="overlay"></div><div id="preloader">'+name+'..</div>');
					if(overlay==1){
					  $('#overlay').css('opacity',0.4).fadeIn(400,function(){  $('#preloader').fadeIn(400);	});
					  return  false;
			   }
			$('#preloader').fadeIn();	  
	   },
		unloading : function() { 
			$('#preloader').fadeOut(400,function(){ $('#overlay').fadeOut(); $.fancybox.close(); }).remove();
	   },
	    render_date : function(column_date, type) {
	 		var date = new Date(column_date);
	 		var str = "";
	 		if(type=="yyyy-MM-dd"){
	 			str = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	 		}
	 		
	 		return str;
	 	}
};//common		
	
