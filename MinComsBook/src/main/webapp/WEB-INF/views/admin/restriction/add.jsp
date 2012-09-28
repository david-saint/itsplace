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
	 	    	$('input[type=radio]').attr('name',"basecode.codeId");
	 	    	
	 			$('form').submit();
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
		<form:form  action="add" commandName="bookRestriction" method="post">
           <div class="boxtitle">
	           <c:set var="errors"><form:errors path="*" /></c:set>
	           <c:if test="${not empty errors}">
	           <span class="ico color lightbulb"></span><span>Exception:</span>
	        	    ${errors }
	           </c:if>
           </div>
             
          <div class="section" >
               <label>제재사유 <small></small></label>   
               <div> 
               		<div class="radiorounded"> 
				 	<c:forEach items="${restrictionList}" var="basecode">
				 		<input type="radio" id="${basecode.codeId}" name="" value="${basecode.codeId}"  />
				 		<label  for="${basecode.codeId}">${basecode.codeDesc} 정지기간:${basecode.codeKey}일</label>
				 		<br>
				 	</c:forEach>
				 	</div>
				 		
               	<span class="f_help"></span>
               	</div>                                  
          </div>       
          
                            <div class="section">
                              <label>select With search<small>Fix width</small></label>   
                              <div>
                              	  <select data-placeholder="Choose a Country..." class="chzn-select" tabindex="2" name="d.d" >
                                  <option value=""></option> 
                                
                                  <option value="Tokelau">Tokelau</option> 
                                  <option value="Tonga">Tonga</option> 
                                  <option value="Trinidad and Tobago">Trinidad and Tobago</option> 
                                  <option value="Tunisia">Tunisia</option> 
                                  <option value="Turkey">Turkey</option> 
                                  <option value="Turkmenistan">Turkmenistan</option> 
                                  <option value="Turks and Caicos Islands">Turks and Caicos Islands</option> 
                                  <option value="Tuvalu">Tuvalu</option> 
                                  <option value="Uganda">Uganda</option> 
                                  <option value="Ukraine">Ukraine</option> 
                                  <option value="United Arab Emirates">United Arab Emirates</option> 
                                  <option value="United Kingdom">United Kingdom</option> 
                                  <option value="United States">United States</option> 
                                  <option value="United States Minor Outlying Islands">United States Minor Outlying Islands</option> 
                                  <option value="Uruguay">Uruguay</option> 
                                  <option value="Uzbekistan">Uzbekistan</option> 
                                  <option value="Vanuatu">Vanuatu</option> 
                                  <option value="Venezuela">Venezuela</option> 
                                  <option value="Viet Nam">Viet Nam</option> 
                                  <option value="Virgin Islands, British">Virgin Islands, British</option> 
                                  <option value="Virgin Islands, U.S.">Virgin Islands, U.S.</option> 
                                  <option value="Wallis and Futuna">Wallis and Futuna</option> 
                                  <option value="Western Sahara">Western Sahara</option> 
                                  <option value="Yemen">Yemen</option> 
                                  <option value="Zambia">Zambia</option> 
                                  <option value="Zimbabwe">Zimbabwe</option>
                                </select>       
                        </div>
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

