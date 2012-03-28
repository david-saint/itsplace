<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	
<div id="wrapper">

	
	<div id="main">
		<div id="content">
			
			<div>
				${franchiserMember.fname}
				${franchiserMember.mobile}
				${franchiserMember.phone1}
				<img id="preview" width="100px" height="100px" src="<c:url value="/resources/images/${franchiserMember.fileName}" />" />
				신청되었습니다. 감사합니다
				
			</div>
		</div>
	</div>
	
	<footer>Footer</footer>
</div>
