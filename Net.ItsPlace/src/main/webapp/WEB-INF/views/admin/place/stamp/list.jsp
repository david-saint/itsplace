<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
 	$(document).ready(function(){
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 		$('.edit').fancybox({//autodimensions false 후 width , height 가느
				'autoDimensions':false,
				'scrolling':'auto',
				'autoScale':false,
				'height':500,

			});
 		
 		var action= "/admin/place/stamp/add";
 		
 		$('#btnSubmit').live('click',function() {
 			c.log("submit Form");
 		

 			$('#placeStamp').submit();
 		});
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		});
 		$('#placeStamp').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					$.ajax({
 	                     url:action,
 	                     type:"POST",
 	                     data:$("form").serialize(),
 	                     beforeSend :function(){
 		                      var title = $('.loading').attr('title');
 		   	 				  var overlay=$(this).attr('rel'); 
 		   	 	 			  c.loading(title,overlay);
 	                     },
 	                     success: function(response){
 	                       if(response.status=="SUCCESS"){
 	                    		parent.$.fancybox.close();
 	                       }else{
 	                    	   var errorInfo="";
 	                    	   for(var i =0 ; i < response.result.length ; i++){
 	                               errorInfo = "<br>" + (i + 1) +". " + response.result[i].defaultMessage;
 	                           }
 	                    	   c.log(errorInfo);
 	                    	   c.showError(errorInfo,1000);
 	                       }
 	                     },
 	                     error: function(jqXHR, textStatus, errorThrown){
 	                    	alert(textStatus+jqXHR+errorThrown); 
 	                     },
 	                     complete:function(){
 	                    	 setTimeout("c.unloading()",1500); 
 	                    
 	                     }
 	                  });//ajax
 				 }
 			  }
	 	});//validation
 	});//ready
	function make_actions(oObj) {
 		var id = oObj.aData['fid'];
 		//c.log(oObj.aData[ oObj.iDataRow ][1] );
 		c.log(""+oObj.aData['placeStamp.sid']);
 		var editAction = '<span class="tip"><a class="edit iframe" href="/admin/place/edit?fid='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
 		var stampEditAction = '<span class="tip"><a class="edit" href="/admin/place/stamp/edit?fid='+id+'" original-title="stamp-edit"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>';
 		var stampAddAction = '<span class="tip"><a class=""" href="/admin/place/stamp/add?fid='+id+'" original-title="stamp-add"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>';
 		var eventAddAction = '<span class="tip"><a class="edit iframe" href="/admin/place/stamp/add?fid='+id+'" original-title="stamp-add"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>';
 		var deleteAction = '<span class="tip"><a class="delete" fid="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
 		
 		return  stampAddAction +"&nbsp;&nbsp;"+ stampEditAction +"&nbsp;&nbsp;"+ editAction + "&nbsp;&nbsp;" + deleteAction ; 
 	}
 	function refresh(){
 		c.log("refresh");
 		datatable.fnStandingRedraw();
 	}
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 스탬프목록 - ${place.fname}  </span>
	</div>
	<div class="content">

			<div class="boxtitle">
				시작일이 경과한 경우 수정이 불가능함, 스탬프를 추가해야합니다.
			</div>
		
			<div class="setion">
				<table class="display staticBase" id="static">
				<thead>
					<tr>
						<th>ID</th>
						<th>스탬프명</th>
						<th>시작일</th>
						<th>종료일</th>
						<th>삭제여부</th>
						<th>수정일</th>
						<th>관리</th>
					</tr>
				</thead>
				 <tbody>
					<c:forEach items="${placeStampList}" var="placeStamp">
						<tr>
							<td>${placeStamp.stampid}</td>
							<td>${placeStamp.stampTitle}</td>
							<td><fmt:formatDate value="${placeStamp.startDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeStamp.endDate }" pattern="yyyy-MM-dd"/></td>
							<td>${placeStamp.isDelete}</td>
							<td><fmt:formatDate value="${placeStamp.editDate }" pattern="yyyy-MM-dd"/></td>
							<td>
								<span class="tip"><a class="edit" href="/admin/place/stamp/edit?fid=${placeStamp.fid}&stampid=${placeStamp.stampid}" original-title="stamp-edit"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>
							</td>
						</tr>
					</c:forEach>
				</tbody> 
				</table>
			</div>
	</div>
</div>


