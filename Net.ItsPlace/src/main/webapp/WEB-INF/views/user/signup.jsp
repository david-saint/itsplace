<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="place"/>

<script type="text/javascript">
$(document).ready(function(){ 	
	

});

</script>
<style>

</style>
<section class="centered"> 
	
			
		
					<form:form   commandName="user" method="get">
           <div class="boxtitle">
	          ${error }
           </div>
			           <fieldset>
			                <div>
			                	<form:input path="name" cssClass="border-box"/><form:errors path="name" cssClass="error" />			                 
			                </div>
			           </fieldset>
			           <fieldset>
			                <div> 
			                	<form:input path="email" cssClass="border-box"/><form:errors path="email" cssClass="error" />	
			                </div>                                  
			           </fieldset>
				      	
				     
				       <fieldset>
				       	 <button id="btnRegister" class="greenButton"><spring:message  code="signup"/></button>
				       </fieldset>
					</form:form>
				  	  
				       
	
</section>

