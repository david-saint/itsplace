	  $(document).ready(function () {	  
			onfocus();
			$(".on_off_checkbox").iphoneStyle();
			$('.tip a ').tipsy({gravity: 'sw'});
			$('#login').show().animate({   opacity: 1 }, 500);
			$('.logo').show().animate({   opacity: 1,top: '30%'}, 500,function(){			
				$('.logo').show().delay(500).animate({   opacity: 1,top: '0%' }, 300,function(){
					$('.formLogin').animate({   opacity: 1,left: '0' }, 100);
					$('.userbox').animate({ opacity: 0 }, 100).hide();
				 });		
			});	
		

	    $('.userload').click(function(e){
			$('.formLogin').animate({   opacity: 1,left: '0' }, 100);			    
			  $('.userbox').animate({ opacity: 0 }, 200,function(){
				  $('.userbox').hide();				
			   });
	    });
	    
	$('#but_login').click(function(e){		
		 
		  if(document.formLogin.j_username.value == "" || document.formLogin.j_password.value == "")
		  {
			  showError("Please Input Username / Password");
			  $('.inner').jrumble({ x: 4,y: 0,rotation: 0 });	
			  $('.inner').trigger('startRumble');
			  setTimeout('$(".inner").trigger("stopRumble")',500);
			  setTimeout('hideTop()',4000);
			  return false;
		  }	else{
			  securityLogin();
		  }	
		 
	});	
 });																	 
function AdminLogin(){
	$("#login").animate({   opacity: 1,top: '49%' }, 200,function(){
		 $('.userbox').show().animate({ opacity: 1 }, 500);
			$("#login").animate({   opacity: 0,top: '60%' }, 500,function(){
				$(this).fadeOut(200,function(){
				  $(".text_success").slideDown();
				  $("#successLogin").animate({opacity: 1,height: "200px"},500);   			     
				});							  
			 });	
     });	   
    setTimeout( "window.location.href='admin'", 3000 );
}
function UserLogin(){
/*$("#login").animate({   opacity: 1,top: '49%' }, 200,function(){
	 $('.userbox').show().animate({ opacity: 1 }, 500);
		$("#login").animate({   opacity: 0,top: '60%' }, 500,function(){
			$(this).fadeOut(200,function(){
			  $(".text_success").slideDown();
			  $("#successLogin").animate({opacity: 1,height: "200px"},500);   			     
			});							  
		 });	
 });*/	   

	
setTimeout( "window.location.href='/'", 100 );
}
function securityLogin(){
	$.ajax({
	    url: "signin/authenticate",
	    type: "POST",
	    data: $("#formLogin").serialize(),
	    beforeSend: function (xhr) {
	        xhr.setRequestHeader("X-Ajax-call", "true");
	    },
	    success: function(result) {
    		 hideTop();
    		 loading('Checking',1);		
    		 setTimeout( "unloading()", 100 );
			if (result == "error") {
	        	 showError("인증에 실패하였습니다.");
	              $('.inner').jrumble({ x: 4,y: 0,rotation: 0 });	
				  $('.inner').trigger('startRumble');
				  setTimeout('$(".inner").trigger("stopRumble")',500);
				  setTimeout('hideTop()',5000);
	        }else{
	        	 setTimeout( "UserLogin()", 0 );
	        }
	    },
	    error: function(data, status, err){
	    	alert("error:"+status+err);
	    },
	    complete:function(){
	    	
	    }
	}); 
}	
$('#alertMessage').click(function(){
	hideTop();
});

function showError(str){
	$('#alertMessage').addClass('error').html(str).stop(true,true).show().animate({ opacity: 1,right: '0'}, 500);	
	
}

function showSuccess(str){
	$('#alertMessage').removeClass('error').html(str).stop(true,true).show().animate({ opacity: 1,right: '0'}, 500);	
}

function onfocus(){
				if($(window).width()>480) {					  
						$('.tip input').tipsy({ trigger: 'focus', gravity: 'w' ,live: true});
				}else{
					  $('.tip input').tipsy("hide");
				}
}

function hideTop(){
	$('#alertMessage').animate({ opacity: 0,right: '-20'}, 500,function(){ $(this).hide(); });	
}	

function loading(name,overlay) {  
	  $('body').append('<div id="overlay"></div><div id="preloader">'+name+'..</div>');
			  if(overlay==1){
				$('#overlay').css('opacity',0.1).fadeIn(function(){  $('#preloader').fadeIn();	});
				return  false;
		 }
	  $('#preloader').fadeIn();	  
 }
 
 function unloading() {  
		$('#preloader').fadeOut('fast',function(){ $('#overlay').fadeOut(); });
 }