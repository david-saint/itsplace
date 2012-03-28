<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>



<style type="text/css">
body .ui-li .ui-li-desc {
/* ... 말줄임표를 없앤다*/
 white-space: normal;
 
 }
</style>

<script type="text/javascript">
$( document ).bind( "pageinit", function( event, data ){
	
	 $('#footer_place').addClass("ui-btn-active");
});
$(document).ready(function(){
		

	if($.trim($('#placeList').text())==""){
		log.info("동으로 검색");
		
		var lot = '${longitude}';
		var lat = '${latitude}';
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
		    			$('#address').text(data.fullName);
		    			if(data.name!=""){
		    				$('#search').attr('href',common_getHostUrl()+"/place/getPlaceListByDong/" + encodeURIComponent(data.name));
		    				//var url2 =common_getHostUrl()+"/place/getPlaceListByDong/" + encodeURIComponent(data.name);	    				
		    				//log.info("주소:"+url2);
		    				//$.mobile.changePage(url2);//인코딩
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
	

});


</script>

<div data-role="content" id="content" >
		<c:if test="${empty franchiserMemberList}">
			GPS 위치가 확인되지 않습니다. 
			주소기반으로 검색
			<div id="address">
				
				<a id="search "href="">검색</a>
			</div>		
				
		</c:if>	
	<ul id="placeList" data-role="listview" data-theme="c">
		
			
			<c:forEach var="franchiserMember" items="${franchiserMemberList}"  >
				<li>
					<a data-transition="fade" href="<c:url value="/place/placeView/${franchiserMember.fid}" /> ">
						<img src="http://jquerymobile.com/demos/1.0rc1/docs/lists/images/album-bb.jpg"/>
						<h3>${franchiserMember.fname} (${franchiserMember.address.bupname})</h3>
						<p>${franchiserMember.address.sido} ${franchiserMember.address.gugun}  ${franchiserMember.address.doroname} ${franchiserMember.address.bldmain}-${franchiserMember.address.bldsubmain}</p>
						<p>
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
				</li>			
			</c:forEach>		
		
	
	</ul>		
	<div id="test"></div>
</div>
