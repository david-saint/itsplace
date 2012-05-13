<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#btnAdd').live('click',function() { 			
 			$('#saveStamp').submit();
 		});
 		$('.StampedEventday').live('click',function() {
 			var pid = $(this).attr("pid");
 			 $.confirm({
 				  'title': '_DELETE DIALOG BOX','message': "<strong>스탬프를 소진하시겠습니까? </strong><br /><font color=red>'  ' </font> ",'buttons': {'Yes': {'class': 'special',
 				  'action': function(){
 					  	burn(pid);
 					 
 					}},'No'	: {'class'	: ''}}});
 			//$('#saveStamp').submit();
 		});
 		$('#saveStamp').validationEngine('attach', {
 			  onValidationComplete: function(form, status){
	 			   if(status==true){
	 				  $.ajax({
	 	                     url: "/place/stamp/save",
	 	                     type:"POST",
	 	                     data:$("#saveStamp").serialize(),// data:"{authcode:"+$('#authcode').val()+"\",stampid:\"8\"}",
	 	                     beforeSend :function(){
	 		                      var title = "적립중";
	 		   	 				  var overlay=1; 
	 		   	 	 			  //c.loading(title,overlay);
	 	                     },
	 	                     success: function(response){
	 	                        if(response.status=="SUCCESS"){
	 	                    	   //c.showSuccess(response.result,1500);
	 	                    	   parent.$.fancybox.close();
	 	                    	   parent.datatableRedraw(response.result,"true");
	 	                    		//iframe fancybox일경우 부모에서 처리한
	 	                        }else{
	 	                    	   
	 	                    	   parent.datatableRedraw(response.result,"false");
	 	                        }
	 	                        c.log("success");
	 	                     },
	 	                     error: function(jqXHR, textStatus, errorThrown){
	 	                     	c.showError(textStatus+jqXHR+errorThrown,1000); 
	 	                     },
	 	                     complete:function(){
	 	                    	 //setTimeout("c.unloading()",500); 
	 	                    	 c.log("complete:");
	 	                    	//$.get("/place/stamp/stampped?email="+$('#email').val(), {}, function(data) { $.fancybox(data); } );
	 	                    	
	 	                     }
	 	               });//ajax
	 			   }
	 	  	}  
	 	}); //validationEngine 
 	});//ready
 	
 	function burn(pid){
 		c.log("pid:"+pid);
 		$('#pid').val(pid);
 		 $.ajax({
             url: "/place/stamp/burn",
             type:"POST",
             data:$("#saveStamp").serialize(),// data:"{authcode:"+$('#authcode').val()+"\",stampid:\"8\"}",
             beforeSend :function(){
	 	 			  c.loading("스탬프 소진중",0);
             },
             success: function(response){
                if(response.status=="SUCCESS"){
                   parent.$.fancybox.close();
            	   parent.datatableRedraw(response.result,"true");
                }else{
            	   
            	   parent.datatableRedraw(response.result,"false");
                c.log("fa");
                }
             },
             error: function(jqXHR, textStatus, errorThrown){
             	alert("오류가 발생하였씁니다"+textStatus+jqXHR+errorThrown,1000); 
             },
             complete:function(){
            	 setTimeout("c.unloading()",500); 
            	 c.log("complete:");
            	//$.get("/place/stamp/stampped?email="+$('#email').val(), {}, function(data) { $.fancybox(data); } );
            	
             }
       });//ajax 
 	}
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 스탬프 적립 및 소진    </span>
	</div>
	<div class="content">
		<form id="saveStamp">
			<div class="boxtitle"></div>
			<div class="section">
				<label> 인증코드 <small></small></label>
				<div>
					<input id="authcode" type="text" name="authcode"
						class="validate[required,minSize[4],maxSize[4]] middle " value="" />
					<input id="stampid" type="hidden" name="stampid" value="${stampid}" />
					<input id="pid" type="hidden" name="pid" value="" />
					<input id="eamil" type="hidden" name="email" value="${email}" /> 
					<a
						id="btnAdd" class="uibutton icon add large" title="Saving" rel="1">적립</a>
				</div>
			</div>
		</form>

		<c:forEach var="stamppedList" items="${stamppedListAll}">
			<div 
				class="${stamppedList.placeStamp.theme}"
				stampid="${stamppedList.placeStamp.stampid}">

				<span class="stampTitle">${stamppedList.placeStamp.stampTitle}</span>
				<span class="ableDate">
					유효기간:
				</span>
				<span>
					<fmt:formatDate value="${stamppedList.placeStamp.startDate}"
						pattern="yyyy-MM-dd" />
					~
					<fmt:formatDate value="${stamppedList.placeStamp.endDate}"
						pattern="yyyy-MM-dd" />
				</span>

				<ul style="display: block">
					<c:forEach var="stamp" items="${ stamppedList.stampList}"
						varStatus="status">
						<li class="${stamp.attribute}" pid="${stamp.pid}"
							saveDate="<fmt:formatDate value="${stamp.saveDate}" pattern="yyyy-MM-dd" />">
							${status.index+1}
							${stamp.status}
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>




	</div>
</div>


