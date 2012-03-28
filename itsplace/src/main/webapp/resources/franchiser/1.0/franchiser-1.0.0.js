/**
  함수명은 파일명_함수명 으로 구성한다. 
  공통 스크립트
  ver1.0 김동훈
*/

$(document).ready(function() {
	// log.toggle(); 레디에서 블랙버드 실행하면 오류난다..
	
	
	//alert("");
//	window.CallAndroid.test("hello android....");
	//log.toggle();
//	log.toggle();
//	log.info("hhhhhhhhhhhhhh");
//	AndroidCalljava("sss");
	
});

function AndroidCalljava(msg) {
	//alert(msg);
   
	log.info("from android: "+msg);

}


//var markerArray = new Array();
/*
 * ajax 가맹점 승인
 * ver1.0 김동훈 2011-09-29
 * param 
 * return Json
 * 
 */
function franchiser_auth(fid,authyn){
	if(authyn=="Y"){
		authyn="N";
	}else{
		authyn="Y";
	}
	
	var url = common_getHostUrl()+'/admin/auth/'+fid+'/'+ authyn;
	
	 $.ajax({
	        type: 'get'
	        , async: false
	        , url: url
	        , dataType: 'json'		       
	        , success: function(data) {
	        	alert(authyn);
	        	if(authyn=="Y"){
	        		$('#auth_'+fid).text("승인취소");
	        		$('#auth_'+fid).attr("authyn","Y");
	        	}else{
	        		$('#auth_'+fid).text("승인");
	        		$('#auth_'+fid).attr("authyn","N");
	        	}
	        	
	        }
	 		, error: function(data, status, err) {
	 			//log.info("error forward : "+data+err+status);
	 			alert('franchiser_auth 서버와의 통신이 실패했습니다.'+data+err+status);
	 		}
	 		, complete: function() {
	 		
	 		}
	 });
}
/*
 * Json 가맹점 리스트를 가져온다
 * ver1.0 김동훈 2011-08-24
 * param 페이지번호
 * return Json
 * 
 */
function franchiser_getJsonFranchiserMemberList(currentPage,searchWord){
	//log.toggle();
//	log.resize();
	//log.resize();
	//log.move();
	//log.move();
	//log.move();
	console.log("검색어 : " +searchWord);
	var _searchWord = searchWord // encodeURIComponent(searchWord);
	console.log("검색어 size: " +_searchWord);
	
	if($.trim(_searchWord) == "" || _searchWord.length < 2){
		console.log("검색어를 두자 이상 입력하세요");
		return false;
	}

	var searchType ="web"; //$("input:radio[name=searchType]:checked").val();	
	var url = common_getHostUrl()+'/partner/franchiserListJson/' + searchType +'/' + _searchWord  + '/' +currentPage+'/10/10';	
	console.log("url:"+url);
	
	var role =$('#ROLE').val();
	var complete = false;
	
	 $.ajax({
	        type: 'get'
	        , async: false
	        , url: url
	        , dataType: 'json'	
	       // , data: $("#frm").serialize() //form 
	       // , data: ({testParam : "안녕아작스"}) 
	        , beforeSend: function() {	  
	        	 
	             $('#ajax_load_indicator').show().fadeIn('fast'); 
	          }
	        , success: function(data) {
	        	if(data==null){
	        		complete = true;
	        		return null;
	        	}
	        	$('#list-ul').empty();
	        	var icon="";
	        	log.info("json: success"+data.length);	       
    			//var points = []; 
	        	$.each(data, function(i){
	        		log.info("루프시작");
	        		$('#list').css('display','block');
	        		var n = i+1;
	        		var fullAddress = this.address.sido + ' ' + this.address.gugun + ' ' + this.address.doroname + ' ' + this.address.bldmain + '-' + this.address.bldsubmain 
	        		+ ' (' + this.address.hdongname + ')';
	        		
	        		
	        		var authyn = "승인취소";
	        		if(this.authyn=="N"){
	        			authyn = "승인";
	        		}
	        		
	        		var listHtml = '<li class="franchiser li-hover mapitem mapitem' +i+ '" id="'+this.fid+'" latitude="'+this.latitude+'"  longitude="'+this.longitude+'">'
	        		+'<h5 class="title">'
	        		+'<a class="fname"  id="mapview_'+this.fid+'" latitude="'+this.latitude+'"  longitude="'+this.longitude+'">' +this.fname+ '</a>'		
	        		+'</h5>'
	        		+'<p class="phone">053-601-2123</p>'
	        		+'<p class="address" >' +fullAddress+ '</p>'	        		
	        		+' | <a class="CC" href="http://www.mywebpage.com" rel="clearbox">클리어박스</a>'
	        		+' | <a class="review" target="_blank" href="'+common_getHostUrl()+'/place/placeView/'+ this.fid+'">리뷰('+this.commentCount+')</a>'
	        		+' | <a class="auth" id="auth_'+this.fid+'"  fid="'+ this.fid +'" authyn="'+ this.authyn +'">'+ authyn +'</a>'
	        		
	        		+'</li>'; 
	        		
	        		log.info("루프 listHtml:"+listHtml);
	        		if(i==0){	        
	        			 
	        			 map = new daum.maps.Map(document.getElementById('map'), {

	        					center: new daum.maps.LatLng(this.latitude, this.longitude)

	        				});

	        			daummap_panTo(this.latitude,this.longitude);	        			
	        			$('#list-ul').append(listHtml);
	        			log.info("json: append"+listHtml);	  
	        		}else{
	         			$('#list-ul').append(listHtml);
	         			log.info("json: append"+listHtml);	  
	        		}
	        		log.info("머야");
	        		
	        		if(this.latitude!=""){
	        			//http://i1.daumcdn.net/localimg/localimages/07/2009/map/icon/ico_mn_b2_s.gif
	        			//icon = "http://i1.daumcdn.net/localimg/localimages/07/2009/map/icon/ico_mn_b" + n + "_s.gif";
	        			icon = "http://localhost:8090/MyPlace/resources/openapi/marker/map" + n + ".png";
	        			//icon = "http://localhost:8090/MyPlace/resources/openapi/marker/A.png"
	        			
	        			daummap_setMarkerImage(this.fid,'mapview_'+this.fid,this.latitude,this.longitude,icon,this.fname,this.fileName,fullAddress);
	        			//points.push(new daum.maps.LatLng(this.latitude,this.longitude);
	        		}
				});	           
	        	
	        	
	          }
	        , error: function(data, status, err) {
	        	//log.info("error forward : "+data+err+status);
	            alert('franchiser_getJsonFranchiserMemberList 서버와의 통신이 실패했습니다.'+data+err+status);
	          }
	        , complete: function(data) {
	        	
	        	$('.fname').click(function(i) {	        		        	
	        		daummap_panTo($(this).attr('latitude'),$(this).attr('longitude'));	        		
	        	});
	        	
	        	$('.auth').click(function(i) {
	        		franchiser_auth( $(this).attr('fid') , $(this).attr('authyn') );
	        	});
	        	
	        	
	        	CB_Init(); // 클리어박스 초기화 .
	        	//alert(role);
	        	if(role=="ROLE_ADMIN"){
	        		$('.auth').css("display","block");
	        	}else{
	        		$('.auth').css("display","none");	
	        	}
	        	
	        	$('#ajax_load_indicator').fadeOut();
	          }
	    });
	 
	 if(complete==true){
		 return null;
	 }
	 var url2 = common_getHostUrl()+'/partner/getFranchiserListPaging';
	
	 var pageUrl="";
	 $.ajax({
	        type: 'get'
	        , async: false
	        , url: url2
	        , dataType: 'json'	
	       // , data: $("#frm").serialize() //form 
	      //  , data: ({testParam : "안녕아작스"}) 
	        , beforeSend: function() {	  	        	 
	             //$('#ajax_load_indicator').show().fadeIn('fast'); 
	          }
	        , success: function(data) {
	       	 	$('#paging').empty();
	        	
	        	$('#paging').append(data);
	        	
	        	$('.pageNumber').each(function(i) { 	        		
	        		//common_getHostUrl()+'/common/getAddressList/' + searchWord +'/'+$(this).text()+'/10/10';
	        		pageUrl = "javascript:franchiser_getJsonFranchiserMemberList("+$(this).text()+")";
	        		$(this).attr('href', pageUrl);	
	        	});
	        	
	        	pageUrl = "javascript:franchiser_getJsonFranchiserMemberList("+$('.prevPage').attr('page')+")";
	        	$('.prevPage').attr('href', pageUrl);

	        	pageUrl = "javascript:franchiser_getJsonFranchiserMemberList("+$('.nextPage').attr('page')+")";
	        	$('.nextPage').attr('href', pageUrl);
	        	
	          }
	        , error: function(data, status, err) {
	        	
	            alert('paging 서버와의 통신이 실패했습니다.'+data+err+status);
	          }
	        , complete: function() {
	        
	          }
	    });
	 
}

function franchiser_AndroidCurrentPoint(address,lat,lng){
	log.info(address);
	log.info("lat:"+lat);
	log.info("lng:"+lng);
	
	daummap_setCenter(lat,lng);
	daummap_setMarker(lat,lng);
}

