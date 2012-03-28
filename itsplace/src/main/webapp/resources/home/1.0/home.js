 $(document).bind( "mobileinit", function(){
	 //jquery왈 jquery 모바일 중간에 와야함
           // alert("f");
           // $('#test').append("mobileinit");

        	//$('#footer_home').addClass("ui-btn-active");
        	$.extend($.mobile,{
        		defaultTransition : 'flip'
        		, loadingMessage : 'Now Loading...'
        		//,ajaxEnabled : false
        		//,autoInitailize : false
        		//,metaViewportContent : 'width=deviece-width, minimum-scale=0.5, maximum-scale=0.5'
        		});
        	
  });  
 
