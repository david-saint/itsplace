<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<style type="text/css">
.ui-li .ui-btn-inner a.ui-link-inherit, .ui-li-static.ui-li {
	display: inline-block;
	padding: 0px 0px 0px 100px;
	padding-top:5px;
}

.ui-li-has-alt .ui-li-count {
right: 43px;
margin-top: 10px;
}

.ui-input-search {
width: 80%;
}
</style>

<div  id="franchiserContent" style="overflow: hidden;overflow-x: hidden;border:1px solid yellow;" >	
<form  id="mForm"  method="get"  data-ajax="false" data-transition="none">
	    		<input type="search" style="width:50px;" name="q" id="search" value="${q}" />
	    	 	<!-- <button type="button" style="float: right" data-inline="true" data-icon="search"></button> --> 
</form>	
		
<!-- 	
	<button id="btnZoomIn" onclick="zoomIn()">확대</button>
	<button id="btnZoomOut" onclick="zoomOut()">축소</button>
	<button id="btnZoomOut" onclick="setMarker()">마커</button>
	 -->
	
	<div id="map" style="height:200px;border:1px solid green;"></div>
							
</div>
  
  
  
<div  id="place"  data-scroll="y" style="overflow: hidden;overflow-x: hidden;border:1px solid yellow;" >	
	<ul id="placeList" style="padding:0px;"   data-role="listview" data-split-icon="map" data-split-theme="d">
	<c:if test="${empty franchiserMemberList}">
			검색결과가 없습니다			
	</c:if>
		<c:forEach var="franchiserMember" items="${franchiserMemberList}" varStatus ="status"  >
			<li style="padding:0px;" id="mapview_${franchiserMember.fid}" fid="${franchiserMember.fid}" latitude="${franchiserMember.latitude}"  longitude="${franchiserMember.longitude}" fname="${franchiserMember.fname}" >
				<a>
					<img  style="width:55px;height:60px;" class="fimage"  src="<c:url value="/resources/images/${franchiserMember.fileName}" />"/>
					<h3 style="margin:0px;margin-bottom:5px;margin-left:-30px;font-size: 14px;font-weight: bold;" class="fname"  id="mapview_${franchiserMember.fid}" latitude="${franchiserMember.latitude}"  longitude="${franchiserMember.longitude}">
						<img width=30 height=30 class="smarker" src=""/>
						${franchiserMember.fname} (${franchiserMember.address.bupname})
					</h3>						
					<p style="font-size:10px; margin:0px;margin-bottom:5px;margin-left:-30px;text-overflow:ellipsis">${franchiserMember.address.sido} ${franchiserMember.address.gugun}  ${franchiserMember.address.doroname} ${franchiserMember.address.bldmain}-${franchiserMember.address.bldsubmain}</p>
					<p style="margin:0px;margin-bottom:5px;margin-left:-30px;text-overflow:ellipsis">
						<!--<c:if test="${franchiserMember.stype != 'N'}">
							Place가 없습니다			
						</c:if>
						-->
						<c:choose>
						      <c:when test="${franchiserMember.stype == 'N'}">
						      	
						      </c:when>
						      
						 	  <c:when test="${franchiserMember.stype == 'S'}">
						      	STAMP
						      </c:when>
						      
						      <c:otherwise>	
						      	STAMP
						      </c:otherwise>
					    </c:choose>
					   
					</p>
					<span class="ui-li-count ui-btn-up-c ui-btn-corner-all">${franchiserMember.commentCount} 리뷰</span>
				</a>
				<a href="<c:url value="/place/placeView/${franchiserMember.fid}" />" rel="external" data-transition="slideup" title="ddd"></a>
			</li>			
		</c:forEach>		
	

		</ul>		
	  

	</div>


 <script type="text/javascript">
 
 var map;
 window.onload = function() {
	//window.onload = function() 이렇게 하지 않으면 지도가 제대로 로딩되지 않느다 Jqueyrmobile과 충돌남
	 //$('#search').val("아하하하");
	//window.CallAndroid.getPlaceGeoLocation();
   Android_getGeoLocation("35.8570479|128.5444278");	
 
 }

$( document ).bind( "pageinit", function( event, data ){
	 $('#footer').find('a').each(function(){
		 $(this).removeClass("ui-btn-active");
	 });
		
	 $('#footer_map').addClass("ui-btn-active");
	 
	var height = $('body').height() -$('#franchiserContent').height();
	var headerHeight = $('#mHeader').height();
	var footerHeight = $('#mFooter').height();
	var contentHeight = $('#franchiserContent').height();
	var totalHeight = $(window).height();
	
	//var placeHeight = totalHeight - headerHeight + footerHeight + contentHeight ;
	$('#place').height($(window).height() - 300);
	
		// $('#search').val($(window).height() - 200);	
		// $('#place').height(height-100);
	 
});	
 $(document).ready(function(){
	 

	 // $('#placeList').height(height);
	
	 $('#map').bind('tap',function(event, ui){
//		 if($('#map').height()==200){
			 $('#map').height(300);
//		 }	
	 });
	 $('#map').bind('taphold',function(event, ui){
//		 if($('#map').height()==200){
			 $('#map').height(300);
//		 }	
	 });
});
 function Android_getGeoLocation(locations){
	
 	var searchWord =$('#search').val();
	
 	var arr_locations = locations.split("|");
 	var lat = arr_locations[0];
 	var lot = arr_locations[1];
 	
 	if($.trim(searchWord)==""){
 		var url = 'http://apis.daum.net/maps/coord2addr?apikey=' + common_getDaumApiKey() + '&output=json&inputCoordSystem=WGS84&latitude=' + lat + '&longitude=' + lot;
		$.ajax({
		        type: 'get'
		        , async: false
		        , url: url+ "&callback=?"
		        , dataType: 'json'	
		        , beforeSend: function() {	  	        	 
		          }
		        , success: function(data) {
		        	if(data.fullName.length>0){
		    			//{"name3":"양재1동","name":"양재1동","fullName":"서울특별시 서초구 양재1동","name1":"서울특별시","name2":"서초구"}
		    			log.info("주소:"+data.fullName);
		    			//alert("주소:"+data.fullName);
		    			if(data.name!=""){
		    				$('#search').val(data.name);
		    			//	$('#sido').text(data.name1);
		    				 //$.mobile.changePage( common_getHostUrl()+"/partner/mFranchiserList?searchWord="+data.name);
		    				// $('#mForm').submit();
		    			//	$('#test1').attr('href',common_getHostUrl()+"/partner/mFranchiserList?searchWord="+data.name);
		    			//	$('#test1').trigger('click');
		    			}
		    		}else{
		    			log.info("주소가 없습니다");
		    		}
		          }
		        , error: function(data, status, err) {
		        	log.info("주소:"+data+err+status);
		           
		          }
		        , complete: function() {
		        	
		        	
		          }
		        
		    });
 	}
 	 var position = new daum.maps.LatLng(lat, lot);
		
	 map = new daum.maps.Map(document.getElementById('map'), {

	 center: position,

	 level: 3,
	 mapTypeId: daum.maps.MapTypeId.ROADMAP
	 });
	 var marker = new daum.maps.Marker({
	 position: position
	 });
	 marker.setMap(map);
	/*
	 var infowindow = new daum.maps.InfoWindow({
		 content: 'Hello, World!'
	 });
	 infowindow.open(map, marker);
	*/
	var n = 0;
	 $('#placeList li').each(function(i){
		 
		 if(this.latitude!=""){
			
 			n = i+1;
 			icon = "http://itsplace.net/resources/openapi/marker/map" + n + ".png";
 			log.info(icon);
 			$(this).find('.smarker').attr('src',icon);
 			
 				//daummap_setMarkerImage($(this).attr('fid'),'mapview_'+$(this).attr('fid'),$(this).attr('latitude'),$(this).attr('longitude'),icon,$(this).attr('fname'),"","");
 				//daummap_setMarkerImage($(this).attr('latitude'),$(this).attr('longitude'),icon);
 				
 				marker= new daum.maps.Marker({
 			 		position: new daum.maps.LatLng($(this).attr('latitude'), $(this).attr('longitude')),
 					image: new daum.maps.MarkerImage(
 							icon,
 							new daum.maps.Size(28, 35),// 마커이미지 크기
 							new daum.maps.Point(16,34),
 							"poly",
 							"1,20,1,9,5,2,10,0,21,0,27,3,30,9,30,20,17,33,14,33"
 						)
 				});
 				marker.setMap(map);
 			
 					
 			$(this).bind('tap',function(event, ui){   
 			
        		daummap_panTo($(this).attr('latitude'),$(this).attr('longitude'));
        		$('#map').height(200);
        		
        	});
 			$(this).click('tap',function(event, ui){   
 			
        		daummap_panTo($(this).attr('latitude'),$(this).attr('longitude'));
        		$('#map').height(200);
        		
        	});
 		}
	 });
 }
 
 
 </script>