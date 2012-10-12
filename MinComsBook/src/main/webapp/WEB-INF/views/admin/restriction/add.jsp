<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<c:set var="title" value="대출자 정지 등록"/>
<html>
  <head>
    <title>{$title}</title>
	<script type="text/javascript">
 		
	 	$(document).ready(function(){
	 		//$(".chzn-select").selectBox();
	 		$(".chzn-select").chosen(); 
		    
	 	    $('#btnAdd').live('click',function() {
	 			var url = "${context}/admin/restriction/add";
	 			url += "?decorator=exception";
	 			$.ajax({
                     url: url,
                     type:"POST",                                
                     data:$("form").serialize(),                   
                     success: function(response){
                       if(response.status=="SUCCESS"){
                    	   var delay =1000;
                    	   c.showSuccess(response.result,delay);
                    	   setTimeout('c.location("${context}/admin/restriction/list")',delay);
                       }else{                    	  
                    	   for(var i =0 ; i < response.result.length ; i++){
                               var field = $("#"+response.result[i].field);                 
                               
                               if($(field).length <= 0 ){
                            	   field =  $('input[name='+response.result[i].field+']').next()//label;
                               }
                               $(field).attr('original-title',response.result[i].message);
                               $(field).tipsy({trigger: 'manual', gravity: 'w'});
                               $(field).tipsy("show");
                               $(field).bind('click',function(){
                            	   $(this).tipsy("hide");
                               });
                           }
                    	   
                       }
                     },
                     complete:function(){
                    	 //setTimeout("c.unloading()",1500); 
                     }
                   });//ajax
	 		});
	 	      
	 	    
	 	  
	 	   function split( val ) {
				return val.split( /,\s*/ );
			}
			function extractLast( term ) {
				return split( term ).pop();
			}

			$( "#restrictUsers" )
				.bind( "keydown", function( event ) {
					if ( event.keyCode === $.ui.keyCode.TAB && $( this ).data( "autocomplete" ).menu.active ) {
						alert("s");
						event.preventDefault();
					}
					if ( event.keyCode == 13){
						event.preventDefault();
					}
				})
				.autocomplete({
					source: function( request, response ) {
						//$.getJSON( "${context}/user/getActiveUsers", { term: extractLast( request.term ) }, response );
						$.ajax({
		                    url: "${context}/user/getActiveUsers",
		                    dataType: "json",
		                    data: {
		                    	term: extractLast( request.term )
		                    },
		                    success: function( data ) {
		                    	console.log(data);
		                        response( $.map( data, function( item ) {
		                            return {
		                                label: item.userRname +" ( "+item.deptInfo.deptName +" : "+ item.userName+" ) ",
		                                value: item.userName
		                            }
		                        }));
		                    }
		                });
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
<style>
* Autocomplete styles */
.ac_results {
        padding: 0px;
        border: 1px solid black;
        background-color: white;
        overflow: hidden;
        z-index: 99999;
}

.ac_results ul {
        width: 100%;
        list-style-position: outside;
        list-style: none;
        padding: 0;
        margin: 0;
}

.ac_results li {
        margin: 0px;
        padding: 2px 5px;
        cursor: default;
        display: block;
        /*
        if width will be 100% horizontal scrollbar will apear
        when scroll mode will be used
        */
        /*width: 100%;*/
        font: menu;
        font-size: 12px;
        /*
        it is very important, if line-height not setted or setted
        in relative units scroll will be broken in firefox
        */
        line-height: 16px;
        overflow: hidden;
}

.ac_loading {
        /* loader image */
        background: white url('indicator.gif') right center no-repeat;
}

.ac_odd {
        background-color: #eee;
}

.ac_over {
        background-color: #0A246A;
        color: white;
}
</style>
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
				 		<label for="${basecode.codeId}">${basecode.codeDesc} 정지기간:${basecode.codeKey}일</label>
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
					
					<input type="text" id="restrictUsers" class="medium" name="restrictUsers" value="${userName }" />
					<form:errors path="restrictUsers" cssClass="error" />    
					<span class="f_help">두글자 이상 입력하세요 </span> 	
				</div>              
				<span class="f_help">예> bmkim, sckim, </span>
          </div> 
        
           
           <div class="section last right">
               <div>
                <a id="btnAdd" class="uibutton loading submit_form" title="Saving" rel="1" >submit</a> 
                <a class="uibutton loading cancel" title="Checking" rel="0" >Cancel</a> 
               </div>
           </div>
        
       </form:form>
       
	</div>
</div>	
</body>

