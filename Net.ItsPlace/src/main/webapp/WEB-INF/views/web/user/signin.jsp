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


</div>


