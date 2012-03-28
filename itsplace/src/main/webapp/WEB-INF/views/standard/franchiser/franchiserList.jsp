<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">

</style>
<!-- 다음맵 -->
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3146347a8ed5125b834d3f6153230e6b3c60b8ba" charset="utf-8" /></script>
<script type="text/javascript" src="<c:url value="/js/springMap/map.js" />"></script>
<script type="text/javascript">
 $(document).ready(function(){
	 $('#searchForm > button').click(function(){
		 franchiser_getJsonFranchiserMemberList(1,$('#search').val());
	 });	  
 });
  
	var map;  
	window.onload = function() {		
		var searchword = '${q}';
		
		if($.trim(searchword)==""){
			
			if (!!navigator.geolocation){
				  console.log("google geolocation call");
			  	navigator.geolocation.getCurrentPosition(successCallback,errorCallback); 
			}else{
			    alert("이 브라우저는 Geolocation를 지원하지 않습니다");
			}
		}else{
			
			if(franchiser_getJsonFranchiserMemberList(1,searchword)==null){
				
			}
		}
		
	}
	
	function successCallback(position){
	    var lat = position.coords.latitude;
	    var lng = position.coords.longitude;
	    //(37.47163412599279,127.02677155082887);
		//lat = "37.47163412599279";
		//lng = "127.02677155082887";	    
	  
		common_getDaumAddress(lat,lng);
	  
	    //log.info("위도:" + lat + ", 경도:"+lng + "동:" + dongName);
		
	    map = new daum.maps.Map(document.getElementById('map'), {
			center: new daum.maps.LatLng(lat, lng)
		});
	    
		var zoomControl = new daum.maps.ZoomControl();
		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
		
		var mapTypeControl = new daum.maps.MapTypeControl();
		map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

		var marker = new daum.maps.Marker({
			position: new daum.maps.LatLng(lat, lng)
		});

		marker.setMap(map);
		
		
	}
	 
	function errorCallback(error){
	    console.log("GeoLocation error: " + error.message);
	    lat = "37.47163412599279";
		lng = "127.02677155082887";	    
	  
		common_getDaumAddress(lat,lng);
	  
	    //log.info("위도:" + lat + ", 경도:"+lng + "동:" + dongName);
		
	    map = new daum.maps.Map(document.getElementById('map'), {
			center: new daum.maps.LatLng(lat, lng)
		});
	    
		var zoomControl = new daum.maps.ZoomControl();
		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
		
		var mapTypeControl = new daum.maps.MapTypeControl();
		map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

		var marker = new daum.maps.Marker({
			position: new daum.maps.LatLng(lat, lng)
		});

		marker.setMap(map);
	  
	 /* var position = new daum.maps.LatLng(37.537123, 127.005523);
		
	 map = new daum.maps.Map(document.getElementById('map'), {
		 center: position,
		 level: 4,
		 mapTypeId: daum.maps.MapTypeId.ROADMAP
	 });
	 var marker = new daum.maps.Marker({
		 position: position
	 });
	 
	 marker.setMap(map);
	 */
	/* var infowindow = new daum.maps.InfoWindow({
		 content: 'Hello, World!'
	 });
	 infowindow.open(map, marker);
	 */
	} 
 </script>
	

<header>
	<div class="field">
		<ul style="float: right;">
			
			<li style="border:1px solid blue;">
				<a>로그인</a>
			</li>
			<li style="border:1px solid blue;">
				<a>로그인</a>
			</li>
			<li style="border:1px solid blue;">
				<a>로그인</a>
			</li>
		</ul>
		
		<ul style="clear: both">
			<li style="width:372px;padding-left:10px;">
				<h2><a>its place</a></h2>
			</li>
			<li style="border:0px solid red">
			  <form id="searchForm">
				<!--<form id="searchForm"  action="<c:url value="/map/search" />" method="GET">-->
						   <!--  <span><i></i></span> -->
						    <input type="text" id="search"  placeholder="검색" name="q"  class="validate[minSize[3]]" placeholder="검색" value="${q}" />
							<input id="ROLE" type="hidden" value="${USERSESSION.role }" />
						    <button type="button" class="large awesome">검색&raquo;</button>			
			    </form>		
			</li>
			<li style="width:100%;order:2px solid red;">
			</li>
		</ul>
							
	</div>	
</header>
<div id="body">		
	<aside>		
		<div id="info_header" style="background: url(http://static.naver.net/maps2/bg_map_header2.gif) repeat-x scroll 0 0 transparent;">
			<div id="infoAddress"></div>
		</div>		
		<div id="info_body" style="">			
			<div id="list" style="display:none;border:0px solid blue;padding:0;">
					
					<ul id="list-ul"></ul>				
					<div id="ajax_load_indicator" style="border:0px solid red;text-align: center; display: none;">
						<img src="<c:url value="/resources/common/1.0/loadinfo.net.gif" />" />
					</div>
					<div id="paging"></div>
			</div>
		</div>
	
	</aside>
	
	
	
	
	<article>
		<div id="infomapheader" style="height: 28px;background: url(http://static.naver.net/maps2/bg_map_header2.gif) repeat-x scroll 0 0 transparent;">	
			<div id="category">
				<ul>
					<li>
						<!--  <a id="bar"><img src="<c:url value="/resources/openapi/mapicon/bar.png" />" /></a>
						<a id="bar"><img src="<c:url value="/resources/openapi/mapicon/icecream.png" />" /></a>
						<a id="bar"><img src="<c:url value="/resources/openapi/mapicon/pizzaria.png" />" /></a>
						<a id="restaurant"><img src="<c:url value="/resources/openapi/mapicon/restaurant.png" />" /></a>
						-->
					</li>
				</ul>
			</div>
		</div>
	    
	    <div id="map" style="height:100%"></div>
		
	</article>
	
</div>
	

	
	
	





