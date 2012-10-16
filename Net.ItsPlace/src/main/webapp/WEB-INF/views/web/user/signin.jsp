<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#btnAdd').live('click',function() {
 			c.log("submit Form");
 			$('#stampType').submit();
 		});
 		
 		
 		   $('#stampType').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
	 			   // alert("The form status is: " +status+", it will never submit");
	 			   c.log("submit:"+status);
	 			   if(status==true){
	 				   var title = $('.loading').attr('title');
	 				  var overlay=$(this).attr('rel'); 
	 	 			  c.loading(title,overlay);
	 	 	    	  setTimeout("c.unloading()",1500); 
	 	 			$('#stampType').validationEngine('detach');
	 	 			$('#stampType').submit();
	 	 			
	 			   }
	 	  }  
	 	});  
 	});
 	
</script>
<div class="widget">
<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,email" />
        <input type="checkbox" id="on_off" name="_spring_security_remember_me" value="1" /> 
		<button type="submit">사인인 위드 페이스북</button>
	</form>

</div>

