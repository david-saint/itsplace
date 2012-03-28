<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>


<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function(){
	window.CallAndroid.getPlaceGeoLocation();
});


function Android_getGeoLocation(locations){
	//$('#log').append("<br>"+locations);
	var arr_locations = locations.split("|");
	var lat = arr_locations[0];
	var lot = arr_locations[1];
	var url =  common_getHostUrl()+"/place/placebylocation/lat/"+lat+"/lot/"+lot+"/";
	//GPS현재위치 또는 주소위치를 리턴함
	 $.mobile.changePage( url , { 
 		transition: "slideup" 
	 });
	
}

</script>

<div data-role="content" id="main">	

</div>



