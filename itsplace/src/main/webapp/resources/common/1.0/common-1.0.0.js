/*
 * 공통 스크립트
 * ver1.0 김동훈 2011-08-21
 * param
 * return
 * 
 */


$(document).ready(function() {
	/*$('input[@type=text]').focus(function() {
		  $(this).addClass("focused");
	 });
	
	$('input[@type=text]').blur(function() {
		  $(this).removeClass("focused");
	 });
	 */
});

/*
 * 호스트 URL 가져오기
 * ver1.0 김동훈 2011-08-21
 * param none
 * return String URL
 */

function common_getHostUrl(){
	return 'http://localhost:8080/MyPlace';
}
function common_getDaumApiKey(){	
	return '3146347a8ed5125b834d3f6153230e6b3c60b8ba';
}
/*
 * 좌표를 주소로 변환 다음맵 openapi 크로스도메인: 쿼리스트링을  "&callback=?" 붙인다
 * ver1.0 김동훈 2011-08-21
 * param 위도,경도
 * return none
 */
function common_getDaumAddress(lat,lng){
	//http://apis.daum.net/maps/coord2addr?apikey=' + obj.apikey + '&output=json&callback=obj.pongSearch&inputCoordSystem=WGS84&latitude=' + lat + '&longitude=' + lng
	//lat = "35.78879895934866";
	//lng = "127.93130020103005";
	
	var url = 'http://apis.daum.net/maps/coord2addr?apikey=' + common_getDaumApiKey() + '&output=json&inputCoordSystem=WGS84&latitude=' + lat + '&longitude=' + lng;
	log.info(url);
	$.getJSON(url + "&callback=?",function(data){
		if(data.fullName.length>0){
			
			//{"name3":"양재1동","name":"양재1동","fullName":"서울특별시 서초구 양재1동","name1":"서울특별시","name2":"서초구"}
			//log.info(data.fullName);
			//log.info(data.name3);

			//log.info(data.name3+"Sssss");
			
			$('#infoAddress').text(data.fullName);
				//log.clear();
				//log.info(dongName);
			$('#fname').val(data.name3);
			
		    franchiser_getJsonFranchiserMemberList(1);
			
		}
	});
}

/*
 * 주소를 좌표로 변환 다음맵 openapi 크로스도메인: 쿼리스트링을  "&callback=?" 붙인다
 * ver1.0 김동훈 2011-08-21
 * param none
 * return String URL
 */

function common_getDaumCOORD(searchWord){
	//http://apis.daum.net/maps/addr2coord?apikey=' + obj.apikey + '&output=json&callback=obj.pongSearch&q=' + encodeURI(obj.q.value)
	//log.info("http://apis.daum.net/maps/addr2coord?apikey=c70f1cec73528a81f8888026073984d89ec37e38&output=json&q="+encodeURI(searchWord));
	
	//var searchWord="대구광역시 중구 공평로8길 29 0";
	var x = "";
	var y = "";
	var url = "http://apis.daum.net/maps/addr2coord?apikey="+common_getDaumApiKey()+"&output=json&q=";
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
					$('#latitude').val(this.point_y);
					$('#longitude').val(this.point_x);
					
				});
			}		
			
			
	});	
	
	//return new Array(y,x);
}

/*
 *  로그인 폼 
 */
function common_login(){
	var status = $('#dropdownLogin').css('display');
	if(status=="block"){
		$('#dropdownLogin').css('display','none');
	}else{
		$('#dropdownLogin').css('display','block');
	}
	
}
/*
 * 
 * 회원가입 폼
 */
function common_join(){
	var status = $('#dropdownLogin').css('display');
	if(status=="block"){
		$('#dropdownLogin').css('display','none');
	}else{
		$('#dropdownLogin').css('display','block');
	}
	
}