/*
 * 공통 스크립트
 * ver1.0 김동훈 2011-08-21
 * param
 * return
 * 
 */


$(document).ready(function() {
	$('.clear_form').live('click',function() {
		

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
				this.reset();
				c.log("c");
				var form_id=$('form:eq('+index+')').attr('id');				
					  if(form_id){
						  c.log("clear Form:"+form_id);
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
			//$('#preloader').fadeOut(400,function(){ $('#overlay').fadeOut(); $.fancybox.close(); }).remove();
			$('#preloader').fadeOut(400,function(){ $('#overlay').fadeOut();  }).remove();
	   },
	    render_date : function(column_date, type) {
	 		var date = new Date(column_date);
	 		var str = "";
	 		if(type=="yyyy-MM-dd"){
	 			var month = date.getMonth()+1;
	 			var day = date.getDate();
	 			 if (month < 10)
	 	            month = "0" + month;
	 	        if (day < 10)
	 	            day = "0" + day;
	 	        
	 			str = date.getFullYear()+"-"+month+"-"+day;
	 			if(str=="2000-1-1"){
	 				str ="";
	 			}
	 		}
	 		
	 		return str;
	 	},
		showError : function(str,delay){	
			  if(delay){
				  $('#alertMessage').removeClass('success info warning').addClass('error').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500,function(){
						  $(this).delay(delay).animate({ opacity: 0,right: '-20'}, 500,function(){ $(this).hide(); });																														   																											
					});
				  return false;
			  }
				  	$('#alertMessage').addClass('error').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500);	
		 },
		showSuccess : function(str,delay){
			  if(delay){
				  $('#alertMessage').removeClass('error info warning').addClass('success').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500,function(){
						  $(this).delay(delay).animate({ opacity: 0,right: '-20'}, 500,function(){ $(this).hide(); });																														   																											
					});
				  return false;
			  }
				  $('#alertMessage').addClass('success').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500);	
		 },
		 showWarning :  function(str,delay){
			  if(delay){
				  $('#alertMessage').removeClass('error success  info').addClass('warning').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500,function(){
						  $(this).delay(delay).animate({ opacity: 0,right: '-20'}, 500,function(){ $(this).hide(); });																														   																											
					});
				  return false;
			  }
				  $('#alertMessage').addClass('warning').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500);	
		  },
		  showInfo : function(str,delay){
			  if(delay){
				  $('#alertMessage').removeClass('error success  warning').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500,function(){
						  $(this).delay(delay).animate({ opacity: 0,right: '-20'}, 500,function(){ $(this).hide(); });																														   																											
					});
				  return false;
			  }
				  $('#alertMessage').html(str).stop(true,true).show().animate({ opacity: 1,right: '10'}, 500);	
		  },
		  newWindow : function(url){
			window.open(url, 'ADpop','resizable=yes,status=no,toolbar=no,menubar=no,width=840,height=800,scrollbars=yes');
		  }
};//common		



$.fn.dataTableExt.oApi.fnStandingRedraw = function(oSettings) {
		$('.tipsy').remove();
	    if(oSettings.oFeatures.bServerSide === false){
	        var before = oSettings._iDisplayStart;
	        oSettings.oApi._fnReDraw(oSettings);
	        //iDisplayStart has been reset to zero - so lets change it back
	        oSettings._iDisplayStart = before;
	        oSettings.oApi._fnCalculateEnd(oSettings);
	    }
	    //draw the 'current' page
	    oSettings.oApi._fnDraw(oSettings);
};
	
