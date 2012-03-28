<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>


<div  data-role="header"> 
	<h1 id="title"></h1> 
</div>
<div data-role="content">
 결과:${result} ${returnUrl }
 
 <a  href="<c:url value="${returnUrl}"/>" data-ajax="false" data-transition="flip" data-role="button">확인</a>
 <a id="test"  data-rel="back" data-transition="flip" data-role="button">재시도</a>
</div>


<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function(){
	
});

</script>
