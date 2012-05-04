<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
	menuSelected("가맹점검색");
</script>
<style type="text/css">
html,body,#map {
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%
}

#btnZoomIn,#btnZoomOut {
	position: absolute;
	right: 1em;
	z-index: 1;
	padding: 10px;
	-webkit-border-radius: 0.5em;
	border: 1px solid #aaa;
	background: -webkit-gradient(linear, left top, left bottom, from(#EFEFF0),to(#BCBEC1) )
}

#roadview {
	position: absolute;
	top: 1em;
	right: 10em;
	z-index: 1;
}

#btnZoomIn {
	top: 1em
}

#btnZoomOut {
	top: 4em
}
</style>


<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3cc715fbd2c405578092bdae6c2a3a6867790d9f"
	charset="utf-8"></script>

<script type="text/javascript">
	var map;
	var lat = "37.54428363237073";
	var lng = "127.0205732625485";
	function init() {
		init2();
	}

	function zoomIn() {
		map.setLevel(map.getLevel() - 3);
	}

	function zoomOut() {
		map.setLevel(map.getLevel() + 1);
	}

	function jsCallAndroid() {
		alert(msg);
	}

	function successCallback(position) {
		var lat = position.coords.latitude;
		var lng = position.coords.longitude;
		map = new daum.maps.Map(document.getElementById('map'), {
			center : new daum.maps.LatLng(lat, lng)
		});

		var marker = new daum.maps.Marker({
			position : new daum.maps.LatLng(lat, lng)
		});

		marker.setMap(map);
	}

	function errorCallback(error) {
		alert("map failed: " + error.message);
	}

	/*지도 중심점 바로 이동하기*/
	function daummap_setCenter(lat, lng) {
		map.setCenter(new daum.maps.LatLng(lat, lng));

		var marker = new daum.maps.Marker({
			position : new daum.maps.LatLng(lat.lng)
		});

		marker.setMap(map);
	}

	function init2() {
		map = new daum.maps.Map(document.getElementById('map'), {
			center : new daum.maps.LatLng(lat, lng)
		});

		var marker = new daum.maps.Marker({
			position : new daum.maps.LatLng(lat, lng)
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
$(document).ready(function(){
	init();	
});	
</script>
	<div id="map" style="width: 100%; height: 500px;"></div>
	<div id="roadview" style="width: 600px; height: 400px;"></div>
	<span id="btnZoomIn" onclick="zoomIn()">확대</span>

	<span id="btnZoomOut" onclick="zoomOut()">축소</span>
	<img id="roadview" src="../resources/images/roadview.png"
		onclick="init3()" />
