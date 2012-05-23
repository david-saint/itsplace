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
		 		$('#btnSearch').live('click',function() {
		 			getDaumCOORD($('#search').val());
		 		});
		 		$('#search').bind('keyup',function(e){
		 		     if( e.which == 13){
		 		    	getDaumCOORD($('#search').val());
		 		    }
		 		});
		 		$('#search').focus();
		 		
		 		
			});
		 	
		 	
		  	function changeAddress(address,lat,lng,localName_1,localName_2,localName_3,newAddress){
		 		parent.setAddress(address,lat,lng,localName_1,localName_2,localName_3,newAddress);
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
		 		initMap();
		 		$.getJSON(url + "&callback=?",function(data){				
		 			var html="";
		 			var btn="";
		 			$("#addressList").empty();
		 				if(data.channel.item.length>0){
		 					$.each(data.channel.item, function(i){
		 						//c.log("total:"+data.channel.totalCount);
		 						
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
		 						this.title = this.title.replace(/&lt;/g,"<");
		 						this.title = this.title.replace(/&gt;/g,">");
		 						
		 						this.localName_3 = this.localName_3.replace(/&lt;/g,"");
		 						this.localName_3 = this.localName_3.replace(/&gt;/g,"");
		 						this.localName_3 = this.localName_3.replace(/b/g,"");
		 						this.localName_3 = this.localName_3.replace(/\//g,"");
		 						c.log(this.localName_3);
		 						//c.log(this.title);
		 						 //this.title = this.title.replace(/<b>/g,"dddddddd");
		 						 //c.log(this.title);//address,lat,lng,localName_1,localName_2,localName_3,newAddress
		 						btn = '<span class="tip"><a title="Edit" onclick="changeAddress(\''+this.title+'\',\''+this.lat+'\',\''+this.lng+'\',\''+this.localName_1+'\',\''+this.localName_2+'\',\''+this.localName_3+'\',\''+this.newAddress+'\')"><img src="/resources/admin/images/icon/icon_edit.png"></a></span>';
		 						html = html +  '<tr><td align="left">'+this.title+'</td><td>'+btn+'</td></tr>';
		 						
		 						if(i==0){
		 							lat = this.point_y;
		 							lng =  this.point_x;
		 							map.setCenter(new daum.maps.LatLng(lat, lng));
		 						}
		 						daummap_setMarker(this.point_y, this.point_x, index);
		 					});
		 					$("#addressList").append(html);
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
		 	  function initMap(){
		 		 map = new daum.maps.Map(document.getElementById('map'), {

						center: new daum.maps.LatLng(lat, lng)

					});
		 	 } 
		 </script>
		<div><input type="text" id="search" value="진천동525"/></div><button id="btnSearch">검색</button>
		 <div class="tableName" style="float:left;width:650px"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"></span>
			 <table class="display" id="datatable">
				<thead>
					<tr>
						<th >주소 </th>
						<th >Action</th>
					</tr>
				</thead>
				<tbody id="addressList" >
				</tbody>
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

