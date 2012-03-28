<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>



	
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
				
					<c:forEach var="franchiserMember" items="${franchiserMemberList}" >
						<div>
							<ul>
		  						<li>${franchiserMember.fid}</li>
								<li class="franchiserMemberList"><a href="<c:url value="/partner/franchiserDetail/${franchiserMember.fid}" />">${franchiserMember.fname}</a></li>
		  						<li>${franchiserMember.authyn}</li>
		  						<li></li>
		  					</ul>
						</div>				  					  					 
				  	</c:forEach>
				
					
		</div>
	</div>
	<footer>Footer</footer>
</div>


