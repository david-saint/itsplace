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
			 	//인증 완료후 미투데이 콜백 url me2dayUpdate 이 호출된다
			 	//http://192.168.0.198:8090/MyPlace/user/me2dayUpdate
			 	
			}
			
		});
}

function new_window(url){
	window.open(url, 'ADpop','resizable=yes,status=no,toolbar=no,menubar=no,width=840,height=800,scrollbars=yes');
}
</script>

	
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
				<img src="${USERSESSION.profileImageUrl}" width="35" height="35" />${USERSESSION.email} ${USERSESSION.name} 님의 계정설정
			</h1>        		
        			
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<div> 
				<form:form  a commandName="user" method="post">
				<form:errors path="*" cssClass="errorblock" element="div" />
				
				<div class="field">
					<label for="email">이메일</label>
					<form:input id="email" path="email"  placeholder="이메일"  readonly="true" />
					<form:errors path="email" cssClass="error" />
				</div>
				<!-- <div class="field">
					<form:input id="password" path="password" placeholder="비밀번호"  />
					<form:errors path="password" cssClass="error" />
					</div>
				 -->	
				<div class="field">
					<label for="name">이름</label>
					<form:input id="name" path="name" placeholder="이름"  />
					<form:errors path="name" cssClass="error" />
				</div>	
			
				<div class="field">			
					<div style="margin-left:150px">
						<a id="me2day" onclick="me2day_getAuthUrl()" >me2day에 로그인하여 연동하세요</a>
					</div>
				</div>
				<div class="field">
				
					<div style="margin-left:150px">
						<a id="twitter" onclick="new_window('<c:url value="/connect/twitter" />')" ><img src="<c:url value="/resources/openapi/twitter/connect-with-twitter.png" />" /></a>
					</div>
				</div>
				<div class="field">
					<div style="margin-left:150px">
						<a id="facebook" onclick="new_window('<c:url value="/connect/facebook" />')" ><img src="<c:url value="/resources/openapi/facebook/connect_light_medium_short.gif" />" /></a>
					</div>
				</div>
				<div class="field2">
					<label style="float: left;width: 130px;text-align: right;padding-top: 15px;margin-right: 15px;">프로필 이미지</label>
					 <div style="padding-top: 15px;">
						<form:radiobutton id="ftype1" path="profileImageType" value="me2day"/><label for="ftype1">me2day</label>		
						<form:radiobutton id="ftype2" path="profileImageType" value="twitter"/><label for="ftype2">twitter</label>
						<form:radiobutton id="ftype3" path="profileImageType" value="facebook"/><label for="ftype3">facebook</label>
						<form:radiobutton id="ftype4" path="profileImageType" value="itsplace"/><label for="ftype4">itsPlace</label>
					</div>
				</div>	
				<div style="">
					<ul  class="column">
						<li style="width:600px;"></li>
						<li>
							<button type="submit" class="large blue awesome">저장하기 &raquo;</button>
						</li>
					</ul>
					
				</div>
				</form:form>
			</div>
		</div><!-- content end -->
	</div>
	<footer>Footer</footer>
</div>


