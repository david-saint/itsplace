<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>



<script type="text/javascript">
$(document).ready(function(){
	var fid = '${franchiserMember.fid}';
	$("#services").val(fid);
	$("#services").change(function() {
		//alert($(this).val());
		//alert($(this).children("option:selected").text());
		//alert(common_getHostUrl()+"/partner/main/"+$(this).val());
		$(window.location).attr('href', common_getHostUrl()+"/partner/main/"+$(this).val());
		
	});
	
	//var fid = $("#services option:selected").val();
	
});
</script>
	
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
			 ${USERSESSION.name} 님의				
			</h1>        		
        			
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<h1></h1>
				<div>
					<form:select id="services" path="services">
						<form:options items="${services}" itemLabel="stypeName" itemValue="fid"/>
					</form:select>
					<div id="buttons" style="float:right">
							<a class="medium blue awesome" href="<c:url value="/partner/franchiserDetail/${franchiserMember.fid}" />">정보수정</a>
						<c:if test="${franchiserMember.stype != 'N'}">								
							<a class="medium blue awesome" href="<c:url value="/place/event/new/${franchiserMember.fid}" />">이벤트등록</a>
						</c:if>
					</div>
					
				</div> 
				
				<div id="storeBox">
					<a class="photo"  href="<c:url value="/place/placeView/${franchiserMember.fid}" />">
				   		<img src="<c:url value="/resources/images/" />${franchiserMember.fileName}" width="150" height="99" alt="프런트이미지">
					</a>
					<!-- <a href="<c:url value="/place/placeView/${franchiserMember.fid}" /> ">
						<h2>${franchiserMember.fname}</h2>
					</a>
					 -->
					<p>${franchiserMember.address.sido} ${franchiserMember.address.gugun} ${franchiserMember.address.doroname} ${franchiserMember.address.bupname}${franchiserMember.address.jimain}-${franchiserMember.address.jisubmain}</p>
			 		<p>${franchiserMember.phone1}</p>				
				</div>
				
				<div style="border:1px solid red">
					<table>
						<tr>
							<td>주소</td>
							<td>
								${franchiserMember.address.sido} ${franchiserMember.address.gugun} ${franchiserMember.address.doroname} ${franchiserMember.address.bldmain}-${franchiserMember.address.bldsubmain}(${franchiserMember.address.bupname} ${franchiserMember.address.jimain}-${franchiserMember.address.jisubmain})
							</td>							
						</tr>
						<tr>
							<td>대표전화</td>
							<td>
								${franchiserMember.phone1}
							</td>							
						</tr>
						<tr>
							<td>대표전화</td>
							<td>
								${franchiserMember.phone1}
							</td>							
						</tr>
						
					</table>
				</div>				
				
				
				
				
					<c:forEach var="franchiserMember" items="${franchiserMemberList}" >
						<div>
							<ul>
		  						<li>${franchiserMember.fid}</li>
								<li class="franchiserMemberList">
									<a href="<c:url value="/partner/franchiserDetail/${franchiserMember.fid}" />">${franchiserMember.fname}${franchiserMember.stype}</a>
									
									<c:if test="${franchiserMember.stype != 'N'}">								
										<a href="<c:url value="/place/event/new/${franchiserMember.fid}" />">이벤트등록</a>
									</c:if>
									
								</li>
		  						<li>${franchiserMember.authyn}</li>
		  						<li></li>
		  					</ul>
						</div>				  					  					 
				  	</c:forEach>
				
					
		</div>
	</div>
	<footer>Footer</footer>
</div>


