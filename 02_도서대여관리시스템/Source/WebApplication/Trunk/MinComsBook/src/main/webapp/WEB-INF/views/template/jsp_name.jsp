<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<c:set var="title" value="이름없음"/>
<html>
  <head>
    <title>{$title}</title>
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		
	 	});	 	
	</script>
</head>
<body>	
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> ${title} </span>
	</div>	
	<div class="content">
		<form:form  action="add" commandName="bookInfo" method="post">
           <div class="boxtitle">
	           <c:set var="errors"><form:errors path="*" /></c:set>
	           <c:if test="${not empty errors}">
	           <span class="ico color lightbulb"></span><span>Exception:</span>
	        	    ${errors }
	           </c:if>
           </div>
           <div class="section" >
               <label> ISBN <small>ISBN 코드입력</small></label>   
               <div>
               		<form:input path="isbn" cssClass="small"/><form:errors path="isbn" cssClass="error" />
               		<a id="btnGoogleBookApi" class="uibutton normal large icon add">도서정보API</a>                                  
               		<span class="f_help">기호없이 숫자만 입력하세요</span>
               </div>
          </div>  
       </form:form>
       
	</div>
</div>	
</body>

