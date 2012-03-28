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
	window.CallAndroid.getPlaceGeoAddress();
	
	
	$('#gaddress').live("swipeleft", function(){
		
		
			$.mobile.changePage("#test", 'slide');
		
	});
});

function Android_getPlaceGeoAddress(address){
	$('#gaddress').text(address);
}

function Android_getGeoLocation(locations){
	//$('#log').append("<br>"+locations);
	$('#address').text(locations);
	var arr_locations = locations.split("|");
	var lat = arr_locations[0];
	var lot = arr_locations[1];
	
	$('#latitude').val(lat);
	$('#longitude').val(lot);
	//$('#address').text(locations);

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
	    			$('#daddress').text(data.fullName);
	    			
	    		}else{
	    			log.info("주소가 없습니다");
	    			//$('#address').text("주소가 없습니다");
	    		}
	          }
	        , error: function(data, status, err) {
	        	//log.info("주소:"+data+err+status);
	        	//$('#address').text("주소:"+data+err+status);
	           
	          }
	        , complete: function() {
	        	
	        	
	          }
	        
	    });
	
}

</script>

<div data-role="content" id="main">	
	<a href="<c:url value="/partner/location/${location.fid}" />" rel="external" data-role="button" >Refresh</a>
	
구글	<div id="gaddress"></div>
e다음	<div id="daddress"></div>


<form:form id=""  action="../location" commandName="location" method="post"  enctype="multipart/form-data">
	<form:errors path="*" cssClass="errorblock" element="div" />
	
	<div data-role="fieldcontain">
		<label for="fid">fid</label>			
		<form:input path="fid"   placeholder="fid"/>
		<form:errors path="fid" cssClass="error" />		
	</div>
	<div data-role="fieldcontain">
		<label for="latitude">latitude</label>
		<form:input path="latitude"  />
		<form:errors path="latitude" cssClass="error" />
		
	
		<label for="longitude">longitude</label>				
		<form:input path="longitude"  />
		<form:errors path="longitude" cssClass="error" />
	</div>
	
	<div data-role="fieldcontain">
		<label for="remark">remark</label>
		<form:textarea id="remark" path="remark" placeholder="remark"  />
		<form:errors path="longitude" cssClass="error" />
	</div>
					




	<div class="ui-body ui-body-b">
		<fieldset class="ui-grid-a">
				<div class="ui-block-a">
					
					<button type="submit" data-theme="d" class="ui-btn-hidden" >Cancel</button>
					
				</div>
				<div class="ui-block-b">
					<button type="submit" data-theme="a" class="ui-btn-hidden" >Submit</button>
				</div>	
	    </fieldset>
	</div>
</form:form>

<div id ="test">dddddddddddddddddd</div>
</div>
