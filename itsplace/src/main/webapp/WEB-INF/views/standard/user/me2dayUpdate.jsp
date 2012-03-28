<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
function me2day_getAuthUrl(){
	var url = "http://me2day.net/api/get_auth_url.json?akey=2db5ebe151fa46f4df6cc3ee3320ae4f";
		$.getJSON(url + "&callback=?",function(data){
			if(data.url.length>0){
		
				$('#me2day_link').attr("href",data.url);
				$('#me2day_link').attr("rel","clearbox[width=800,height=600,title=333]");
			 	CB_Init(); // 클리어박스 초기화 .
				$('#me2day_link').trigger("click");
			}
			
		});
}

$(document).ready(function() {
	// log.toggle(); 레디에서 블랙버드 실행하면 오류난다..
	
	
	//alert("");
//	window.CallAndroid.test("hello android....");
	//log.toggle();
//	log.toggle();
//	log.info("hhhhhhhhhhhhhh");
//	AndroidCalljava("sss");
	
});
</script>


승인되었습니다.
<p>${akey}</p>
<p>${ukey}</p>