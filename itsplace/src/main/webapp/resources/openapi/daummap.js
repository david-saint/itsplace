/**
  함수명은 파일명_함수명 으로 구성한다. 
  공통 스크립트
  ver1.0 김동훈
*/


	


 function daummap_initMap() {
	/* var map;
	 var position = new daum.maps.LatLng(37.537123, 127.005523);
		
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
 }

 function daummap_zoomIn() {	
 	map.setLevel(map.getLevel() - 1);
 }
 function daummap_oomOut() {
 	map.setLevel(map.getLevel() + 1);
 }

 /*이미지 마커 올리기*/


 
 
function daummap_setMarkerImage(fid,mapview_fid, lat, lng, icon,fname,fileName,fullAddress){
	
		var marker= new daum.maps.Marker({
	 		position: new daum.maps.LatLng(lat, lng),
			image: new daum.maps.MarkerImage(
					icon,
					new daum.maps.Size(28, 35),// 마커이미지 크기
					new daum.maps.Point(16,34),
					"poly",
					"1,20,1,9,5,2,10,0,21,0,27,3,30,9,30,20,17,33,14,33"
				)
		});
		marker.setMap(map);
	
		
		
		var contentHtml = 	'<div class="infoWindow">'
		+'<a class="polaroid" href="http://place.daum.net/place/Top.do?confirmid=11749016" target="_blank">'
		+'<img src='+common_getHostUrl()+'/resources/images/'+fileName+'/>'
		+'</a>'
		+'<h5 class="title">'
		+'<a href="http://place.daum.net/place/Top.do?confirmid=11749016" class="namelink" target="_blank" >' +fname+ '</a>'		
		+'</h5>'
		+'<p class="phone">053-601-2123</p>'
		+'<p class="address" >' +fullAddress+ '</p>'
		+'<p class="website" >'
		+'<a href="http://www.edavinci.co.kr" target="_blank">http://www.edavinci.co.kr</a>'
		+'</p>'
		+'<a href="#" id="close_'+fid+'" class="close"></a>'
		+'</div>';
		var infoWindow = new daum.maps.InfoWindow({
			//position: new daum.maps.LatLng(lat, lng),															   	   
			content: contentHtml,	
		//	removable: true
		});//.open(map,marker);
		
		daum.maps.event.addListener(marker, "click", function() {
			$('.close').trigger('click');
			infoWindow.open(map, marker);
			$('#close_'+id).click(function(i) { //리스트클릭시 인포윈도우 표시
				//alert("");
				infoWindow.close();
			});
		}); 
		
		$('#'+mapview_fid).click(function(i) { 
			//fid 리스트클릭시 인포윈도우 표시
			$('.close').trigger('click');
			
			infoWindow.open(map, marker);
			
			
			$('#close_'+fid).click(function(i) { 
				//인포윈도우 닫기
				infoWindow.close();
			});
		});
		

		
 }




function daummap_setMarkerImage(lat, lng, icon){
	
		var marker= new daum.maps.Marker({
	 		position: new daum.maps.LatLng(lat, lng),
			image: new daum.maps.MarkerImage(
					icon,
					new daum.maps.Size(28, 35),// 마커이미지 크기
					new daum.maps.Point(16,34),
					"poly",
					"1,20,1,9,5,2,10,0,21,0,27,3,30,9,30,20,17,33,14,33"
				)
		});
		marker.setMap(map);
	
		
	
		
 }

	
 /*기본마커 올리기*/
 function daummap_setMarker(lat, lng){	
	 //alert("s");
	 new daum.maps.Marker({
		 position: new daum.maps.LatLng(lat, lng),		 
	 }).setMap(map);
	 
	 
 }
 
 /*지도 중심점 바로 이동하기*/
 function daummap_setCenter(lat, lng) {
	 map.setCenter(new daum.maps.LatLng(lat, lng));
 }
 /*지도 중심점 부드럽게 이동하기*/
 function daummap_panTo(lat, lng) {
	 
	 map.panTo(new daum.maps.LatLng(lat, lng));
	 
 } 