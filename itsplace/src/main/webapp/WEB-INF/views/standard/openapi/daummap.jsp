<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html> 

<html> 

<head> 
	<link rel="stylesheet" href="<c:url value="/resources/blackbirdjs/1.0/blackbird.css" />" type="text/css" media="screen" />	
	<script type="text/javascript" src="<c:url value="/resources/blackbirdjs/1.0/blackbird.js" />"></script>
	
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/common/1.0/common-1.0.0.js" />"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, target-densityDpi=device-dpi">
	<title>Daum 지도 API v3 예제: 모바일 확대/축소 버튼 올리기</title> 
	
	<style type="text/css">
	
	html, body, #map {margin: 0; padding: 0; width: 100%; height: 100%}
	
	 #btnZoomIn, #btnZoomOut {position: absolute; right: 1em; z-index: 1; padding: 10px; -webkit-border-radius: 0.5em; border: 1px solid #aaa; background: -webkit-gradient(linear,left top,left bottom,from(#EFEFF0),to(#BCBEC1))}
	 
	 #roadview {position: absolute; top: 1em;right: 10em; z-index: 1; }
	
	#btnZoomIn {top: 1em}
	
	#btnZoomOut {top: 4em}
	
	</style>

 
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3146347a8ed5125b834d3f6153230e6b3c60b8ba" charset="utf-8"></script> 


<script type="text/javascript" src="<c:url value="/resources/franchiser/1.0/franchiser-1.0.0.js" />"></script>

<script type="text/javascript"> 

	var map;
	  var lat ="37.54428363237073";
	    var lng = "127.0205732625485";
	function init() {
/*
		  if (navigator.geolocation)
		  {
		  	navigator.geolocation.getCurrentPosition(successCallback,errorCallback); 
		  }
		  else
		  {
		    alert("이 브라우저는 Geolocation를 지원하지 않습니다");
		  }
		  
*/
	init2();
	//init3();
	
	}

	

	function zoomIn() {
		window.CallAndroid.test("hello android....");
		map.setLevel(map.getLevel() - 1);

	}

	

	function zoomOut() {

		map.setLevel(map.getLevel() + 1);

	}
	
	function jsCallAndroid() {
		alert(msg);
		log.toggle();
		log.info("call: "+msg);

	}
	

	  function successCallback(position)
	  {
	    var lat = position.coords.latitude;
	    var lng = position.coords.longitude;
	    //(37.47163412599279,127.02677155082887);
		//lat = "37.47163412599279";
		//lng = "127.02677155082887";
	    log.info("위도:" + lat + ", 경도:"+lng);
	    common_getDaumAddress(lat,lng);
		  
		map = new daum.maps.Map(document.getElementById('map'), {

			center: new daum.maps.LatLng(lat, lng)

		});



		var marker = new daum.maps.Marker({

			position: new daum.maps.LatLng(lat, lng)

		});

		marker.setMap(map);
	
	  }
	 
	  function errorCallback(error)
	  {
	    alert("map failed: "+error.message);
	  }   
	  
	  
	  
	  /*지도 중심점 바로 이동하기*/
	  function daummap_setCenter(lat, lng) {
	 	 map.setCenter(new daum.maps.LatLng(lat, lng));
	 	
	 	 var marker = new daum.maps.Marker({

			position: new daum.maps.LatLng(lat.lng)

		});

		marker.setMap(map);
		
	  }
	  
	  function init2(){
		
		    //(37.47163412599279,127.02677155082887);
			//lat = "37.47163412599279";
			//lng = "127.02677155082887";
		    log.info("위도:" + lat + ", 경도:"+lng);
		    common_getDaumAddress(lat,lng);
			  
			map = new daum.maps.Map(document.getElementById('map'), {

				center: new daum.maps.LatLng(lat, lng)

			});



			var marker = new daum.maps.Marker({

				position: new daum.maps.LatLng(lat, lng)

			});

			marker.setMap(map);
	  }
	  function init3() {

			var p = new daum.maps.LatLng(lat, lng);

			var rc = new daum.maps.RoadviewClient();

			var rv = new daum.maps.Roadview(document.getElementById("roadview"));



			rc.getNearestPanoId(p, 100, function(panoid) {

				rv.setPanoId(panoid, p);

			});

		

		}

</script> 

</head> 

<body onload="init()">

	<div id="map" style="width:100%;height:500px;"></div>
<div id="roadview" style="width:600px;height:400px;"></div>

	<span id="btnZoomIn" onclick="zoomIn()">확대</span>

	<span id="btnZoomOut" onclick="zoomOut()">축소</span>
	<img id="roadview" src="../resources/images/roadview.png"  onclick="init3()"/>
</body> 

</html> 
