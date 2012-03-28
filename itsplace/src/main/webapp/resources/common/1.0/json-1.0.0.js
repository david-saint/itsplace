 /**
  * 함수명은 파일명_함수명 으로 구성한다.
  */
function json_testAjax() {
	
	//log.info("ajax call");
    $.ajax({
        url:"../common/getAjax",
        data: ({testParam : "안녕아작스"}),
        success: function(data) {
        	log.info(data);
        	$('#testAjax').html(data);
        }
    });
}
function json_jsonAjax() {
	//log.info("json call");
	 $.getJSON(getHostUrl() + "/common/getJson?testParam=파라미터한글", function(data) {
		 $('#testJson').append(data.mail + data.name);
		 
	 });
	
}

function json_JsonObject() {
	//log.info("json Object call");
	$.getJSON(getHostUrl() + "/common/getJsonObject/파라미터한글3333", function(data) {
		$('#testObject').append(data.email + data.name);
		
	});
	
}
 
/*
 * Json 주소리스트를 가져온다
 * ver1.0 김동훈 2011-08-21
 * param 동이름
 * return 
 * 
 */
function getJsonAddressList(){
	//log.info("Json호출");
	$.getJSON("http://192.168.0.188:8090/MyPlace/common/getAddressList/공평로8",
	        function(data){				
				
					//log.info("주소리스트 사이즈: " + data.length);
			
				//	log.info("주소리스트(addressList) 사이즈: " + data.addressList.length);
				
				
				
				if(data.length>0){
					
				}		
				$.each(data, function(i){
				//$.each(data.macrowordContentList, function(i,item){
					$('#addressList').append(this.sido + this.gugun +'</br>');
				});
				
    });	
}
/*
 * Json 주소리스트를 가져온다
 * ver1.0 김동훈 2011-08-21
 * param 동이름
 * return 
 * 
 */
function json_getJsonAddressList2(currentPage){
	
	var searchWord = encodeURIComponent($('#hdongname').val());
	//log.info("검색어 size: " + 	 searchWord.length);
	
	if(searchWord == "" || searchWord.length < 2){
		//log.info("검색다시하세요");
		return false;
	}
	
	var searchType = $(":input:radio[name=searchType]:checked").val();
	
	var url = common_getHostUrl()+'/common/getAddressList/' + searchWord +'/' + searchType + '/' +currentPage+'/10/10';
	
	log.info("json url: " + url);
	
	
	$('#addressList-ul').empty();
	 $.ajax({
	        type: 'get'
	        , async: false
	        , url: url
	        , dataType: 'json'	
	       // , data: $("#frm").serialize() //form 
	      //  , data: ({testParam : "안녕아작스"}) 
	        , beforeSend: function() {	  
	        	 
	             $('#ajax_load_indicator').show().fadeIn('fast'); 
	          }
	        , success: function(data) {
	        	log.info("json: success"+data.length);
	        	$.each(data, function(i){
					//$.each(data.macrowordContentList, function(i,item){
	        		//
	        		
	        		$('#addressList').css('display','block');
	        		
	        		if(i==0){	        			
	        			$('#addressList-ul').append('<li class="address li-hover" style="border-top-left-radius:0.6em;border-top-right-radius:0.6em;" nldno="'+this.nldno+'">'+this.sido + ' ' + this.gugun + ' ' + this.doroname + ' ' + this.bldmain + '-' + this.bldsubmain +'</li>');
	        		}else{
	         			$('#addressList-ul').append('<li class="address li-hover" nldno="'+this.nldno+'">'+this.sido + ' ' + this.gugun + ' ' + this.doroname + ' ' + this.bldmain + '-' + this.bldsubmain +'</li>');
	        		}
						
				});	           	        		        
	          }
	        , error: function(data, status, err) {
	        	log.info("error forward : "+data+err+status);
	            alert('서버와의 통신이 실패했습니다.');
	          }
	        , complete: function() {
	        	$('.address').click(function(i) {
	        	
	        		log.info($(this).attr('nldno'));
	        		$('#nldno').val($(this).attr('nldno'));
	        		$('#address').val($(this).text());
	        		
	        		common_getDaumCOORD(  $(this).text() );
	        		
	        	});
	        	
	        	$('#ajax_load_indicator').fadeOut();
	          }
	    });
	 
	 var url2 = common_getHostUrl()+'/common/getPaging';

	 var pageUrl="";
	 $.ajax({
	        type: 'get'
	        , async: true
	        , url: url2
	        , dataType: 'json'	
	       // , data: $("#frm").serialize() //form 
	      //  , data: ({testParam : "안녕아작스"}) 
	        , beforeSend: function() {	  	        	 
	             //$('#ajax_load_indicator').show().fadeIn('fast'); 
	          }
	        , success: function(data) {
	       	 	$('#paging').empty();
	        	log.info("json: success"+data);
	        	
	        	$('#paging').append(data);
	        	
	        	$('.pageNumber').each(function(i) { 	        		
	        		//common_getHostUrl()+'/common/getAddressList/' + searchWord +'/'+$(this).text()+'/10/10';
	        		pageUrl = "javascript:json_getJsonAddressList2("+$(this).text()+")";
	        		$(this).attr('href', pageUrl);	
	        	});
	        	
	        	pageUrl = "javascript:json_getJsonAddressList2("+$('.prevPage').attr('page')+")";
	        	$('.prevPage').attr('href', pageUrl);

	        	pageUrl = "javascript:json_getJsonAddressList2("+$('.nextPage').attr('page')+")";
	        	$('.nextPage').attr('href', pageUrl);
	        	
	          }
	        , error: function(data, status, err) {
	        	log.info("error forward : "+data+err+status);
	            alert('서버와의 통신이 실패했습니다.');
	          }
	        , complete: function() {
	        
	          }
	    });
	
}
