<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3cc715fbd2c405578092bdae6c2a3a6867790d9f" charset="utf-8"></script>
<style>
.right{text-align:right;}
.left{text-align:left;}
.tableName th{text-align:center;}
</style>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 주소검색   </span>
	</div>
	<div class="content">
		<script type="text/javascript">
		var map;
		//var marker = new Array();
		var datatable;
		var index;
		var lat = "35.81473605375478";
		var lng = "128.52437732176546";	
		window.onload = function() {	
			map = new daum.maps.Map(document.getElementById('map'), {

				center: new daum.maps.LatLng(lat, lng)

			});

			/* var marker = new daum.maps.Marker({

				position: new daum.maps.LatLng(lat, lng)

			}); */
			
			//marker.setMap(map);
		};
			
		
		 	$(document).ready(function(){
		 		
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
			});
		 	
		 	function make_address(oObj) {
		 		var bldmain;
		 		var jimain;
		 		if(oObj.aData['bldsubmain']!=="0"){
		 			bldmain = oObj.aData['bldmain'] + "-" +oObj.aData['bldsubmain'];
		 		}else{
		 			bldmain = oObj.aData['bldmain'] ;
		 		}
		 		if(oObj.aData['jisubmain']!=""){
		 			jimain = oObj.aData['jimain'] + "-" +oObj.aData['jisubmain'];
		 		}else{
		 			jimain = oObj.aData['jimain'] ;
		 		}
		 		c.log(jimain);
		 		var address = oObj.aData['sido']+"&nbsp;"
		 					 +oObj.aData['gugun']+"&nbsp;"
		 					 +oObj.aData['doroname']+"&nbsp;"
		 					 +bldmain+"&nbsp;("
		 					 +oObj.aData['bupname']+"&nbsp;" + jimain +" )";
		 					// +jimain+" )";
		 		return address;
		 	}
		 	function make_actions(oObj) {
		 		 
		 		//c.log(oObj.aData[ oObj.iDataRow ][1] );
		 		//c.log("마:"+marker.length);
		 		//번지까지 넣으면 결과값이 하나만 나온다?
		 		var search = oObj.aData['bupname']+oObj.aData['jimain'];
		 		if(oObj.aData['jisubmain']!=""){
		 			search += "-" + oObj.aData['jisubmain'];
		 		}
		 		getDaumCOORD(search);
		 		
		 		var nldno = oObj.aData['nldno'];
		 		var address = search;
		 		var action = '<span class="tip"><a onclick="changeAddress(\''+nldno+'\',\''+address+'\')" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		
		 		return  action ; 
		 	}
		  	function changeAddress(nldno,address){
				console.log(nldno+address);
		 		parent.setAddress(nldno,lat,lng,address);
		 		parent.$.fancybox.close();
		 	} 
		 	function getDaumCOORD(searchWord){
		 		//http://apis.daum.net/maps/addr2coord?apikey=' + obj.apikey + '&output=json&callback=obj.pongSearch&q=' + encodeURI(obj.q.value)
		 		//log.info("http://apis.daum.net/maps/addr2coord?apikey=c70f1cec73528a81f8888026073984d89ec37e38&output=json&q="+encodeURI(searchWord));
		 		
		 		//var searchWord="대구광역시 중구 공평로8길 29 0";
		 		var key = "791f18f41d85d7e90a5d3f8004ae84b53dd5eafd";
		 		var x = "";
		 		var y = "";
		 		var url = "http://apis.daum.net/local/geo/addr2coord?apikey="+key+"&output=json&q=";
		 		url = url + encodeURIComponent(searchWord);
		 		$.getJSON(url + "&callback=?",function(data){				
		 			
		 				if(data.channel.item.length>0){
		 					$.each(data.channel.item, function(i){
		 						
		 						x=this.point_x;
		 						y=this.point_y;
		 						//log.info("리턴좌표:" + y+", " +x);
		 						//log.info("결과좌표:" + resultArray[0] +", " +resultArray[1]);
		 		        	    //$('#latitude').val(resultArray[0]);
		 		        		//$('#longitude').val(resultArray[1]);
		 						//console.log(this.point_y);
		 						//console.log(this.point_x);
		 		        		//$('#latitude').val(this.point_y);
		 						//$('#longitude').val(this.point_x);
		 						/*  new daum.maps.Marker({
		 				 			 position: new daum.maps.LatLng(this.point_y, this.point_x),		 
		 				 		 }).setMap(map);
		 						 */
		 						if(i==0){
		 							lat = this.point_y;
		 							lng =  this.point_x;
		 							map.setCenter(new daum.maps.LatLng(lat, lng));
		 						}
		 						daummap_setMarker(this.point_y, this.point_x, index);
		 					});
		 				}		
		 		});	
		 	}
		 	var  mark;
		 	 /*기본마커 올리기*/
		 	 function daummap_setMarker(lat, lng, index){	
		 		 mark = new daum.maps.Marker({
		 			 position: new daum.maps.LatLng(lat, lng),		 
		 		 }).setMap(map);
		 		
		 	 }
		 	 
		 	 
		 	 function test(){
		 		daummap_setMarker(lat,lng,1);
		 	 }
		 	  function test2(){
		 		 map = new daum.maps.Map(document.getElementById('map'), {

						center: new daum.maps.LatLng(lat, lng)

					});
		 	 } 
		 </script>
		
		 <div class="tableName" style="float:left;width:650px"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"></span>
			 <table class="display" id="datatable">
				<thead>
					<tr>
						<th>주소 </th>
						
						<th >Action</th>
						
					</tr>
				</thead>
			</table>
		</div> 
	<div id="map" style="float:right;width:500px;height:500px"></div>
		<div class="section last right">
			
		</div>


		<!-- clear fix -->
		<div class="clear"></div>
		 <button onclick="test()">maker</button>
		 <button onclick="test2()">maker2</button>

	</div>
	<!-- End content -->
</div>
<!-- End full width -->

