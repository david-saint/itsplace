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
		var mapImage = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
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
		 			getDaumCOORD($('#search').val(),1);
		 		});
		 		$('#search').bind('keyup',function(e){
		 		     if( e.which == 13){
		 		    	getDaumCOORD($('#search').val(),1);
		 		    }
		 		});
		 		$('#search').focus();
		 		
		 		
			});
		 	
		 	
		  	function changeAddress(address,lat,lng,localName_1,localName_2,localName_3,newAddress){
		 		parent.setAddress(address,lat,lng,localName_1,localName_2,localName_3,newAddress);
		 		parent.$.fancybox.close();
		 	} 
		 	function getDaumCOORD(searchWord,pageno){
		 		//http://apis.daum.net/maps/addr2coord?apikey=' + obj.apikey + '&output=json&callback=obj.pongSearch&q=' + encodeURI(obj.q.value)
		 		//log.info("http://apis.daum.net/maps/addr2coord?apikey=c70f1cec73528a81f8888026073984d89ec37e38&output=json&q="+encodeURI(searchWord));
		 		$("#addressList").empty();
		 		$('#paginate').empty();
		 		//var searchWord="대구광역시 중구 공평로8길 29 0";
		 		var key = "791f18f41d85d7e90a5d3f8004ae84b53dd5eafd";
		 		var url = "http://apis.daum.net/local/geo/addr2coord?apikey="+key+"&output=json&pageno="+pageno+"&q=";
		 		url = url + encodeURIComponent(searchWord);
		 		initMap();
		 		$.getJSON(url + "&callback=?",function(data){				
		 			var html="";
		 			var btn="";
		 			
		 				if(data.channel.item.length>0){
		 					c.log("total:"+data.channel.totalCount);
		 					pageNavigation(searchWord, data.channel.pageno,data.channel.totalCount)

		 					$.each(data.channel.item, function(i){
		 						
		 						
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
		 						 c.log(this.localName_1 + this.localName_2 + this.localName_3 + this.mainAddress +"-"+ this.subAddress + this.newAddress);
		 						
		 						 var title =  this.localName_1 +" "+ this.localName_2  +" "+ this.localName_3  +" "+ this.mainAddress + "-" + this.subAddress  +" ["+ this.newAddress+"]";
		 						
		 						btn = '<span class="tip"><a title="선택" onclick="changeAddress(\''+title+'\',\''+this.lat+'\',\''+this.lng+'\',\''+this.localName_1+'\',\''+this.localName_2+'\',\''+this.localName_3+'\',\''+this.newAddress+'\')"><img src="/resources/admin/images/icon//color_18/location.png"></a></span>';
		 						html = html +  '<tr><td align="left">'+this.title+'</td><td>'+btn+'</td></tr>';
		 						
		 						if(i==0){
		 							lat = this.point_y;
		 							lng =  this.point_x;
		 							map.setCenter(new daum.maps.LatLng(lat, lng));
		 						}
		 						daummap_setMarker(this.point_y, this.point_x, i);
		 					});
		 					$("#addressList").append(html);
		 				}else{
		 					$('#paginate').append('검색결과가 없습니다');
		 				}		
		 		});	
		 	}
		 	var  mark;
		 	 /*기본마커 올리기*/
		 	 function daummap_setMarker(lat, lng, index){
		 		 c.log(index);
		 		var img = "/resources/images/marker/" + mapImage[index] + ".png";
		 		var icon = new daum.maps.MarkerImage(
		 				img,
		 				new daum.maps.Size(32, 32),
		 				new daum.maps.Point(16,34),
		 				"poly",
		 				"1,20,1,9,5,2,10,0,21,0,27,3,30,9,30,20,17,33,14,33"
		 			);
		 		 mark = new daum.maps.Marker({
		 			 position: new daum.maps.LatLng(lat, lng),	
		 			 image: icon
		 		 }).setMap(map);
		 		
		 	 }
		 	 
		 	 function pageNavigation(searchWord, currentPage, totalCount){
		 		var pageNo = new Array();
		 		var pageSize = 10;
		 		var pageGroupSize = 10;
		 		var pageCount    = (totalCount/pageSize)+(totalCount%pageSize==0?0:1);	// 총페이지수
		 			pageCount = Math.ceil(pageCount-1);
		 		console.log("pageCOunt:"+pageCount);
				var last         = pageCount;
				var currentGroup = Math.ceil(currentPage/pageGroupSize);	// 현재그룹
				var totalGroup   = pageCount/pageSize+1;								// 총그룹
				var startPage    = (currentGroup-1)*pageGroupSize+1; 					// 페이지 그룹의 시작페이지
				var endPage      = startPage+pageGroupSize; 							// 페이지 그룹의 마지막페이지
				var prev=0;
				var next=0;
				
				if(endPage>pageCount){
					endPage = pageCount+1;
				}
				var j = 0;
				for(var i=startPage;i<endPage;i++){
					pageNo[j]=i;
					j++;
				}
				if(currentGroup>1){
				    prev=(currentGroup-2)*pageGroupSize+pageSize;
				}

				if(currentGroup<totalGroup){
					next=currentGroup*pageGroupSize+1;
				}
				console.log("현재그룹:"+currentGroup);
				$('#paginate').empty();
				var btn="";
				btn +='<a tabindex="0" class="first paginate_button paginate_button_disabled" id="first"onclick="getDaumCOORD(\''+searchWord+'\',1)">First</a>';
				btn +='<a tabindex="0" class="previous paginate_button paginate_button_disabled" id="previous"onclick="getDaumCOORD(\''+searchWord+'\','+prev+')">Previous</a>';
				btn +="<span>";
				for(var i=0;i<pageNo.length;i++){
					if(currentPage==i+1){
						btn += '<a tabindex="0" class="paginate_active" onclick="getDaumCOORD(\''+searchWord+'\','+pageNo[i]+')">'+pageNo[i]+'</a>';
					}else{
						btn += '<a tabindex="0" class="paginate_button" onclick="getDaumCOORD(\''+searchWord+'\','+pageNo[i]+')">'+pageNo[i]+'</a>';
					}
				}
				btn += "</span>";
				btn += '<a tabindex="0" class="next paginate_button" id="next" onclick="getDaumCOORD(\''+searchWord+'\','+next+')">Next</a>';
				btn += '<a tabindex="0" class="last paginate_button" id="last" onclick="getDaumCOORD(\''+searchWord+'\','+last+')">Last</a>';
				console.log("last:"+last);
				$('#paginate').append(btn);
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
		<div>
			<input type="text" style="width:460px;" id="search" value="대학로"/>
			<a id="btnSearch" class="uibutton normal large">검색 </a>
		</div>
		
		<div class="tableName" style="width:560px;min-height:500px;">
		 	<span style="position:absolute"></span>
			 <table class="display" id="datatable">
				<thead>
					<tr>
						<th >주소 </th>
						<th >선택 </th>
					</tr>
				</thead>
				<tbody id="addressList" >
				</tbody>
			</table>
			<div class="dataTables_paginate paging_full_numbers" id="paginate">
				검색 결과가 없습니다 
			</div>
		</div> 
		
		<div id="map" style="position:absolute;top:105px;margin-left:600px;width:500px;height:550px"></div>

		<!-- clear fix -->
		<div class="clear"></div>

	</div>
	<!-- End content -->
</div>
<!-- End full width -->

