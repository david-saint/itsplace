<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">	
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="<c:url value="/resources/blackbirdjs/1.0/blackbird.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/common/1.0/common-1.0.0.js" />"></script>
	<link rel="stylesheet" href="<c:url value="/resources/blackbirdjs/1.0/blackbird.css" />" type="text/css" media="screen" />
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=ec32849ae3f1b19275f2a0ebe08051f046bda8fd" charset="utf-8"></script> 
		
		<link rel="stylesheet" href="<c:url value="/resources/common/1.0/paging.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/franchiser/1.0/franchiser.css" />" type="text/css" media="screen" />



<!--[if IE]>
<script type="text/javascript">
document.createElement('header');
document.createElement('aside');
document.createElement('article');
document.createElement('footer');
</script>
<![endif]-->
<style type="text/css">
body {
	margin: 0;
}
header {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	height: 100px;
	background-color: #ccc;
}
article {
	position: absolute;
	left: 200px;
	right: 0;
	top: 100px;
	bottom: 50px;
	overflow: auto;
}
aside {
	position: absolute;
	top: 100px;
	bottom: 50px;
	left: 0;
	width: 200px;
	overflow: auto;
	background-color: #eee;
}
footer {
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
	height: 50px;
	background-color: #ccc;
}
</style>

<script type="text/javascript">


$(document).ready(function() {
	var url;
	$('.pageNumber').each(function(i) { 
		log.info( "page :");
		url = common_getHostUrl()+'/test?pageNo='+$(this).text()+"&pageSize=5&pageGroupSize=10";
		$(this).attr('href', url)	
	});
	
	url = common_getHostUrl()+'/test?pageNo='+$('.prevPage').attr('page')+"&pageSize=5&pageGroupSize=10";
	$('.prevPage').attr('href', url);

	url = common_getHostUrl()+'/test?pageNo='+$('.nextPage').attr('page')+"&pageSize=5&pageGroupSize=10";
	$('.nextPage').attr('href', url);
	
	
});
	var map;
	window.onload = function() {		
		
		if (!!navigator.geolocation){
		  	navigator.geolocation.getCurrentPosition(successCallback,errorCallback); 
		}else{
		    alert("이 브라우저는 Geolocation를 지원하지 않습니다");
		}
	
		
		//$('.pageNumber').attr('href',common_getHostUrl()+'/test?pageNo=');
	};
	
	function successCallback(position){
	    var lat = position.coords.latitude;
	    var lng = position.coords.longitude;
	    //(37.47163412599279,127.02677155082887);
		//lat = "37.47163412599279";
		//lng = "127.02677155082887";	    
	    //var dongName = common_getDaumAddress(lat,lng);
	    
	   // log.info("위도:" + lat + ", 경도:"+lng + "동:" + dongName);
		
	    map = new daum.maps.Map(document.getElementById('map'), {

			center: new daum.maps.LatLng(lat, lng)

		});

		var marker = new daum.maps.Marker({

			position: new daum.maps.LatLng(lat, lng)

		});

		marker.setMap(map);
		
		
	}
	 
	function errorCallback(error){
	    alert("GeoLocation error: " + error.message);
	}  
	  
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
 
 </script>
</head>
<body>

</body>
</html>
			