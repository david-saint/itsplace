<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		 $('form#userEdit').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
	 			    alert("The form status is: " +status+", it will never submit");
	 			  }  
	 			});

		
 		 
 		 
 	
 		 
 		 
 		/* $('.loading').live('click',function() { 
 			
 			//  var str=$(this).attr('title'); 
 			  //var overlay=$(this).attr('rel'); 
 			 // loading(str,overlay);
 			//  setTimeout("unloading()",1500); 
 		  });  */
 		  $('.submit_form').live('click',function(){
 			//c.log("edit");
 			//alert($('#userEdit').validationEngine());
 				$('form').submit();
 			
 		});  
 		
 	});
 	
</script>

		

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 사용지 등록  </span>
	</div>
	<!-- End header -->
	<div class="content">
					<!-- title box -->
                        <div class="boxtitle"><span class="ico gray audio_knob"></span> Basic Form ,Elements and custom input , go to advace <span class="netip"><a href="vform.html" class="red" title="Live demo  Validation"> form Validation and  Live demo  </a><img src="images/icon/link.png" alt="link"/></span>
                          </div>
						  
                        <form id="userEdit" action=""> 
                              <div class="section" >
                                  <label> full <small>Text custom</small></label>   
                                  <div> <input id="email" type="text" name="email" class="validate[required,minSize[3],maxSize[20]] medium "  value="${user.email }"/><span class="f_help">Text custom help</span></div>
                                  
                             </div>
                             <div class="section" >
                                  <label> full <small>Text custom</small></label>   
                                  <div> <input id="email2" type="text" name="email" class="validate[required,minSize[3],maxSize[20]] full "  /><span class="f_help">Text custom help</span></div>
                                  
                             </div>
                        
                        
                              
                              <div class="section last">
                                  <div>
	                                  <a class="uibutton loading submit_form" title="Saving" rel="1" >submit</a> 
	                                  <a class="uibutton special clear_form"  >clear form</a> 
	                                  <a class="uibutton loading confirm" title="Checking" rel="0" >Check</a> </div>
                             </div>
                          </form>
		
		
	
		


	
		
	</div>
</div>


