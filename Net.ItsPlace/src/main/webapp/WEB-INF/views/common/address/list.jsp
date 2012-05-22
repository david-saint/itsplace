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
		var lat = "35.81473605375478";
		var lng = "128.52437732176546";	
		window.onload = function() {	
			map = new daum.maps.Map(document.getElementById('map'), {

				center: new daum.maps.LatLng(lat, lng)

			});

			var marker = new daum.maps.Marker({

				position: new daum.maps.LatLng(lat, lng)

			});
			
			//marker.setMap(map);
		};
			var datatable;
		
		 	$(document).ready(function(){
		 		
		 		 datatable = $('#datatable').dataTable( {
		 			"sDom": 'fCl<"clear">rtip', //컬럼숨김
		 			"bFilter": true, 
		 			"bPaginate": true,
		 			"bLengthChange": true, 
		 			"sPaginationType": "full_numbers",
		 			"bProcessing": true,
		 			"oLanguage": {
		 		         "sProcessing": "<div style='border:0px solid red'>Address Loading...</di>"
		 		       },
		 			"bServerSide": true,		 			
		 			"sAjaxSource": "/address/list",
		 			"sAjaxDataProp": "rows",
		 			"aoColumns": [
		 				  				 				  			
		 				  			{ "mDataProp": "sido", "sClass":"left", "sWidth": "150px"},
		 				  			{ "mDataProp": "gugun", "sClass":"left", "sWidth": "150px"},
		 				  			{ "mDataProp": "bupname", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "jimain", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "jisubmain", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "doroname", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "bldmain", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "bldsubmain", "sClass":"left" },
		 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
		 				  			
		 				  		],
		 			//"oLanguage": {
		 			//                "sUrl": "/resources/common/datatables.txt"
		 			//            },	  		
			  		"fnInitComplete":function(){
		 				$('.tip a ').tipsy({trigger: 'manual'});
		 				$('.tip a ').tipsy("hide");
		 			},
		 			"fnDrawCallback": function () {
		 				
		 				$('.edit').fancybox({//autodimensions false 후 width , height 가느
		 					'autoDimensions':false,
		 					'scrolling':'auto',
		 					'autoScale':false,
		 					'height':500,
		 					//'centerOnScroll':true
		 					//'title':'사용자 정보 수정'
	
		 				});
		 				
		 			},	  		
		 			"aaSorting": [[ 2, "desc" ]]
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 	
			});
		 	
		 	function make_actions(oObj) {
		 		
		 		//c.log(oObj.aData[ oObj.iDataRow ][1] );
		 		c.log(""+oObj.aData['bupname']);
		 		getDaumCOORD(oObj.aData['bupname']+oObj.aData['jimain']);
		 		var action = '<span class="tip"><a class="edit iframe" href="/admin/place/edit?fid=" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		
		 		return  action ; 
		 	}
		 	function refresh(){
		 		c.log("refresh");
		 		datatable.fnStandingRedraw();
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
		 						console.log(this.point_y);
		 						console.log(this.point_x);
		 		        		//$('#latitude').val(this.point_y);
		 						//$('#longitude').val(this.point_x);
		 						 new daum.maps.Marker({
		 				 			 position: new daum.maps.LatLng(this.point_y, this.point_x),		 
		 				 		 }).setMap(map);
		 						
		 					});
		 				}		
		 				
		 				
		 		});	
		 		
		 		
		 	}
		 	 /*기본마커 올리기*/
		 	 function daummap_setMarker(lat, lng){	
		 		 console.log("s");
		 		 new daum.maps.Marker({
		 			 position: new daum.maps.LatLng(lat, lng),		 
		 		 }).setMap(map);
		 		 
		 		 
		 	 }
		 	 function test(){
		 		daummap_setMarker(lat,lng);
		 	 }
		 </script>
		
		 <div class="tableName"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"></span>
			 <table class="display" id="datatable">
				<thead>
					<tr>
						<th >시도</th>
						<th >GUGUN</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						<th >Action</th>
						
					</tr>
				</thead>
			</table>
		</div> 
	
		<div class="section last right">
			
		</div>


		<!-- clear fix -->
		<div class="clear"></div>
		 <button onclick="test()">maker</button>
<div id="map" style="width:500px;height:500px"></div>
	</div>
	<!-- End content -->
</div>
<!-- End full width -->

