<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="대출자 정지 등록"/>
<html>
  <head>
    <title>{$title}</title>
	<script type="text/javascript">
 		
	 	$(document).ready(function(){
	 		//$(".chzn-select").selectBox();
	 		$(".chzn-select").chosen(); 
		    
	 	    $('#btnAdd').live('click',function() {
	 	    	
	 	    	//custominput 에서  name을 소수점으로 넣으면 에러남
	 	    	//$('input[type=radio]').attr('name',"basecode.codeId");
	 	    	
	 			$('form').submit();
	 		}); 
	 	      
	 	    
	 	  
	 	   function split( val ) {
				return val.split( /,\s*/ );
			}
			function extractLast( term ) {
				return split( term ).pop();
			}

			$( "#restrictUsers" )
				// don't navigate away from the field on tab when selecting an item
				.bind( "keydown", function( event ) {
					if ( event.keyCode === $.ui.keyCode.TAB &&
							$( this ).data( "autocomplete" ).menu.active ) {
						event.preventDefault();
					}
				})
				.autocomplete({
					source: function( request, response ) {
						$.getJSON( "/user/getActiveUsers", {
							term: extractLast( request.term )
						}, response );
					},
					search: function() {
						// custom minLength
						var term = extractLast( this.value );
						if ( term.length < 2 ) {
							return false;
						}
					},
					focus: function() {
						// prevent value inserted on focus
						return false;
					},
					select: function( event, ui ) {
						var terms = split( this.value );
						// remove the current input
						terms.pop();
						// add the selected item
						terms.push( ui.item.value );
						// add placeholder to get the comma-and-space at the end
						terms.push( "" );
						this.value = terms.join( ", " );
						return false;
					}
				});
	 	});
	 	
	 
	</script>

</head>
<body>	
<div class="widget">
	<div class="header">
		<span><span class="ico gray pencil"></span> ${title} </span>
	</div>	
	<div class="content">
		<form:form  action="add" commandName="dtoBookRestriction" method="post">
           <div class="boxtitle">
	           <c:set var="errors"><form:errors path="*" /></c:set>
	           <c:if test="${not empty errors}">
	           <span class="ico color lightbulb"></span><span>Exception:</span>
	        	    ${errors }
	           </c:if>
           </div>
             
          <div class="section" >
               <label>정지사유 <small></small></label>   
               <div> 
               		<div class="radiorounded"> 
				 	<c:forEach items="${restrictionList}" var="basecode">
				 		<input type="radio" id="${basecode.codeId}" name="restrictReason" value="${basecode.codeId}"  />
				 		<label  for="${basecode.codeId}">${basecode.codeDesc} 정지기간:${basecode.codeKey}일</label>
				 		<br>				 	   	
				 	</c:forEach>				 		
				 	</div>
				 		<form:errors path="restrictReason" cssClass="error" />  
               	<span class="f_help"></span>
               	</div>                                  
          </div>       
          
          <div class="section">
          		<label>사용자 검색<small></small></label>   
                <div class="ui-widget">
					<label for="birds">사용자 아이디 </label>
					<input type="text" id="restrictUsers" name="restrictUsers" />
					<form:errors path="restrictUsers" cssClass="error" />     	
				</div>              
				<span class="f_help">예> bmkim, sckim, </span>
          </div> 
        
           
           <div class="section last right">
               <div>
                <a id="btnAdd" class="uibutton loading submit_form" title="Saving" rel="1" >submit</a> 
                <a id="btnAddAjax" class="uibutton loading submit_form" title="Saving" rel="1" >submit-aAjax</a> 
                <a class="uibutton special clear_form"  >clear form</a> 
                <a class="uibutton loading cancel" title="Checking" rel="0" >Cancel</a> 
               </div>
           </div>
        
       </form:form>
       
	</div>
</div>	
</body>

