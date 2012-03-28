<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
	
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
			 My Stamp
				
			</h1>        		
        			
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<ul>
			<c:forEach var="stamp" items="${myStampList}" >				  					
		  		<li class="stampList">
		  			<img src=${stamp.filename} />
		  			<span>${stamp.fname}</span>
		  			
		  			<div>
			  			<iframe src="<c:url value="/stamp/mobile/?mobile=${stamp.mobile}&stamptype=${stamp.stamptype}&fid=${stamp.fid}&urlType=myStamp" />">
			  			</iframe>
		  			</div>			
		  		</li>
		  	</c:forEach>
		  	</ul>	
		</div>
		<!-- content -->
	</div>
	<footer>
		
<p>Site: <c:if test="${currentSitePreference.mobile}"><a href="?site_preference=normal">Normal</a> | Mobile</c:if><c:if test="${!currentSitePreference.mobile}">Normal | <a href="?site_preference=mobile">Mobile</a></c:if></p>
		Footer
	</footer>
</div>



