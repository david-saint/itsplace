<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>


<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function(){

	
});

$( document ).bind( "pageinit", function( event, data ){
	/* $('#footer').find('a').each(function(){
		 $(this).removeClass("ui-btn-active");
	 });

	 $('#footer_home').addClass("ui-btn-active");
	 if($("#j_username").val()!="" && $('#j_password').val()!=""){			
			 
		}else{
			//$('#test').text("size:"+$("#j_username").val().length+$('#j_password').val().length);
		}
	 */
});	

function home_search(){
	
	window.CallAndroid.getPlaceGeoLocation();
	
} 

function Android_getGeoLocation(locations){
	//$('#log').append("<br>"+locations);
	var arr_locations = locations.split("|");
	var lat = arr_locations[0];
	var lot = arr_locations[1];
	var url =  common_getHostUrl()+"/place/placebylocation/lat/"+lat+"/lot/"+lot+"/";
	 $.mobile.changePage( url , { 
 		transition: "slideup" 
	 });
	/* $.ajax({
	        type: 'get'
	        , async: true
	        , url: url
	        , dataType: 'json'		       
	        , success: function(data) {
	        	if(data=="0"){
	        		$('#console').text("위치를 찾을수가 없습니다");
	        		$('#console').append("<br>lat:"+lat);
	        		$('#console').append("<br>lot:"+lot);
	        	}else{
	        		//$('#aa').val(data);
	        		//$.mobile.loadPage(common_getHostUrl()+"/place/placeView/"+data);
	        		//http://192.168.123.102:8090/MyPlace/place/placebylocation/lat/35.8570479/lot/128.5444278/
		        	 $.mobile.changePage( common_getHostUrl()+"/place/placeView/"+data+"?site_preference=mobile", { 
		        		//transition: "slideup",
		        		type: "get",
		        		reloadPage :true
		        	} );
		        	
	        	}
	        	
	        	
	        }
	 		, error: function(data, status, err) {
	 			//log.info("error forward : "+data+err+status);
	 			alert('Android_getGeoLocation 서버와의 통신이 실패했습니다.'+data+err+status);
	 		}
	 		, complete: function() {
	 		
	 		}
	 });
	 */
}

function qrCodeReader(){
	window.CallAndroid.qrCodeReader();
}
</script>

<div data-role="content" id="main">	
	<div style="font-size: 20px;">
		
	</div>
	<sec:authorize ifNotGranted="ROLE_USER">	
	<form id="loginForm" action="<c:url value="/signin/authenticate" />" method="post" >
		
		
		<div data-role="fieldcontain">
			<label for="j_username">Email</label> 
			<input id="j_username" name="j_username" type="text" placeholder="이메일" />
			
			<label for="j_password">Password</label> 
			<input id="j_password" name="j_password" type="password" placeholder="비밀번호" />
		
			
			<label for="_spring_security_remember_me">remember me</label>
			<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" checked="checked" />
			
		</div>		
		
		<button id="submit" type="submit">로그인</button>
		
			<!-- <a data-role="button" data-transition="flip" href='<c:url value="/user/login" />' >로그인</a> -->
			<a data-role="button" data-transition="pop"  href='<c:url value="/user/newAccount" />' >회원가입</a>
			
	</form>
	</sec:authorize>
	
	<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
		<ul id="menuList" style="padding:0px;"   data-role="listview" data-inset="true" data-split-theme="d">
			<li>
				<a href="<c:url value="/place/search" />" rel="external" >
					<!-- <img src="<c:url value="/resources/home/1.0/search.png" />" alt="Place 검색" class="ui-li-icon ui-li-thumb"> --> 
					Place 검색
				</a>
			</li>
			<li>	
				<a href="<c:url value="/map/search" />?q=" rel="external" >
					<!-- <img src="<c:url value="/resources/home/1.0/search.png" />" alt="Place 검색" class="ui-li-icon ui-li-thumb"> --> 
					Place 지도
				</a>
			</li>
			<li>	
				<a href="<c:url value="/stamp/myStamp" />" rel="external" >
					<!-- <img src="<c:url value="/resources/home/1.0/search.png" />" alt="Place 검색" class="ui-li-icon ui-li-thumb"> --> 
					My Place
				</a>
			</li>
			<li>	
				<a onclick="common_kakao('우히히히','http://www')" >					
					kakao
				</a>
			</li>
			<li>	
				<a onclick="qrCodeReader()" >
					<!-- <img src="<c:url value="/resources/home/1.0/search.png" />" alt="Place 검색" class="ui-li-icon ui-li-thumb"> --> 
					QRcode
				</a>
			</li>
			<li>	
				<a href="<c:url value="/logout" />" rel="external" >
					<!-- <img src="<c:url value="/resources/home/1.0/search.png" />" alt="Place 검색" class="ui-li-icon ui-li-thumb"> --> 
					logout
				</a>
			</li>
		</ul>
		<!-- 
		<a data-role="button"  href="<c:url value="/partner/franchiserList" />?searchWord=" >Place Map</a>
		<a data-role="button"  href="<c:url value="/stamp/myStamp" />" >myStamp</a>
		<a data-role="button"  href="<c:url value="/logout" />"  >logout</a>
		<a data-role="button"  href="<c:url value="/partner/index" />"  >swipe</a>
		<div id="console"></div>
		 -->
	</sec:authorize>
	
</div>
<div id="test">
test</div>
	 
	
	

			

<!-- <a href="tel:01085931257">전화테스트</a> 
	<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
		${USERSESSION.name }
		<a data-role="button" rel="external" data-inline="true" href='<c:url value="/logout" />'>로그아윳 </a>
	</sec:authorize>
-->


