<%@ page language="java"   contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>

<script type="text/javascript">
$(document).ready(function(){
	 var confirmEmail = '${confirmEmail}';
	 var emailYn = '${USERSESSION.emailYn}';
	// alert(confirmEmail+emailYn);
	 if(confirmEmail=="true" && emailYn=="Y"){
		 alert("메일인증이 되었습니다")
	 }else if(confirmEmail=="true" && emailYn=="N"){
		 alert("메일인증해주세요")
	 }
	 $('#storeBox').css('display','block');
	 $('#stampBox').css('display','block');
	 $('#placeTab').addClass('active');
});
 
function tab_change(obj,index){
	 $('.tab').each(function(i){
		 $(this).removeClass('active');		 
	 });
	 $('.section').each(function(i){
		 $(this).css('display','none');		 
	 });
	$(obj).addClass('active');
	
	if(index==1){
		$('#storeBox').css('display','block');
	}else if(index==2){
		$('#commentBox').css('display','block');
	}else{
		$('#commentBox').css('display','block');
	}
 }
</script>	
<div id="wrapper">
	<header>
		<div class="field">	
				
        	
		</div>	
	</header>
	<div id="main">
		<div class="content">	
			<div id="tabs">
				<ul>
					
					<li>
						<div id="placeTab" class="tab" onclick="tab_change(this,1);">Place</div>
					</li>
					<li >
						<div id="commentTab" class="tab" onclick="tab_change(this,2);">Comment</div>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<div id="contentWrapper" class="column">
				<div id="innerWrapper">
					
			  		
					  		
				  	<section id="storeBox" class="section">
					  	<h3>Latest Place</h3>
					   	<p></p>
						  	<c:forEach var="franchiserMember" items="${franchiserMemberList}" >
							   	<article class="store">
								   	<a class="photo"  href="<c:url value="/place/placeView/${franchiserMember.fid}" />">
								   		<img src="<c:url value="/resources/images/" />${franchiserMember.fileName}" width="150" height="99" alt="picture1">
								   	</a>
								   	<a href="<c:url value="/place/placeView/${franchiserMember.fid}" /> ">
								   		<h2>${franchiserMember.fname}</h2>
								   	</a>
								  	<p>${franchiserMember.address.sido} ${franchiserMember.address.gugun} ${franchiserMember.address.doroname} ${franchiserMember.address.bupname}${franchiserMember.address.jimain}-${franchiserMember.address.jisubmain}</p>
					 				<p>${franchiserMember.phone1}</p>
							   	</article>				   	
						  	</c:forEach>					  				
					</section><!-- PLACE BOX -->
					
					
					<section id="commentBox" class="section">
						<c:forEach var="placeComment" items="${placeCommentRecentList}" >
							<article class="comment">								
								<a class="userImage"><img  src="${placeComment.profileImageUrl}" width="40" height="40"></a>
								<span>${placeComment.name}( ${placeComment.email} )</span>
								
								<!--  <p><fmt:formatDate value="${placeComment.inpdate}" pattern="yyyy-MM-dd hh:mm:ss"/></p>-->
								<p>${placeComment.writeDate}</p>
								<p>${placeComment.comment}</p>
							</article>
					  	</c:forEach>	
				  	</section><!-- commentBox -->  
		  		</div><!-- innerWrapper -->
			</div><!-- contentWrapper -->
				
			<div id="rightWrapper" class="column">
				<a href="/join" rel="nofollow">
				회원가입</a>
				
				<section id="stampBox">			 
				  	<h3>Latest Stamp</h3>
					<c:forEach var="stamp" items="${stapmList}">
								 <article>								
										<a class="photo">
											<img  src="<c:url value="/resources/images/" />${stamp.filename}" width="40" height="40">
										</a>
										<p>${stamp.fname}</p>																		
										<p>${stamp.name}님이 스탬프를 획득하셨어요${stamp.stampDate}</p>
								</article>
				  	</c:forEach>	
			  	</section><!-- STAMPBOX -->
			  	
				<section id="noticeBox">			 
				  	<h3>공지사항</h3>
					<c:forEach var="notice" items="${noticeList}">
								 <article>																		
										<p>${notice.title}</p>																		
										
								</article>
				  	</c:forEach>	
			  	</section><!-- STAMPBOX -->
			</div>
				

		
		  	
		</div><!-- content -->
	</div>
	
</div>


	<!-- 
		<p><sec:authorize access="hasRole('ROLE_ADMIN')">나는 관리자입니다</sec:authorize></p>
				<p><sec:authorize access="hasRole('ROLE_USER')">나는 사용자입니다</sec:authorize></p>
				
				<p><sec:authorize ifNotGranted="ROLE_USER">난 익명인이야
					<a href="<c:url value="/user/login" />">로그인</a>
				</sec:authorize>
				</p>
				
				
				<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
				<p><sec:authentication property="name" /> 접속했어요
				<a href='<c:url value="/logout" />'>로그아윳 </a>
				</p>
				</sec:authorize>
				
				 	
					<form id="tw_signin" action="<c:url value="/connect/twitter"/>" method="POST">
						<button type="submit">트위터연동</button>
					</form>
				
					
					<form name="fb_signin" id="fb_signin" action="<c:url value="/connect/facebook"/>" method="POST">
				        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
						<button type="submit">페이스북연동</button>
					</form>
					
					
					<sec:authentication property="name" var="currentUserName"/>
							<c:if test="${currentUserName == placeComment.email}">
							 삭제버튼
							</c:if>
							
							
							<sec:authentication property="name" var="currentUserName"/>
							<c:if test="${currentUserName == placeComment.email}">
							 삭제버튼
							</c:if>
				 -->

