<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>
<script type="text/javascript">
	var datatable; 
 	$(document).ready(function(){
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 		$('input[name=isDelete]').live('change', function() {
 			datatable.fnStandingRedraw();
 		});
 		$('input[name=isAuth]').live('change', function() {
 			datatable.fnStandingRedraw();
 		});
 	    datatable = $('#datatable').dataTable( {
 			"sDom": 'fCl<"clear">rtip', //컬럼숨김
 			"bFilter": true, //search
 			"bPaginate": true,
 			"bLengthChange": true, //로우수
 			"sPaginationType": "full_numbers",
 			"bProcessing": true,
 			"oLanguage": {
 		         "sProcessing": "<div style='border:0px solid red'>이벤트 조회중 ...</di>"
 		       },
 			"bServerSide": true,		 			
 			"sAjaxSource": "/partner/event/getPlaceEventList",
 			"fnServerParams": function (aoData, fnCallback) {
	              aoData.push( { "name": "fid", "value":  ${place.fid}} );		 			               
	              aoData.push( { "name": "isDelete", "value":   $('input[name=isDelete]:checked').val()} );		 			               
	              aoData.push( { "name": "isAuth", "value":   $('input[name=isAuth]:checked').val()} );		 			               
			},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
 				  			{ "mDataProp": "title" },
 				  			{ "mDataProp": "startDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['startDate'],'yyyy-MM-dd');
 							} },
 							{ "mDataProp": "endDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['endDate'],'yyyy-MM-dd');
 							} },
 				  			{ "mDataProp": "saveDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['saveDate'],'yyyy-MM-dd');
 							} },
 				  			{ "mDataProp": "editDate","fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['editDate'],'yyyy-MM-dd');
 							} },
 							{ "mDataProp": "isAuth","fnRender" :function ( oObj ) {
 								return oObj.aData['isAuth']  ? "승인" : "대기";
 							} },
 							{ "mDataProp": "isDelete","fnRender" :function ( oObj ) {
 								return oObj.aData['isDelete']  ? "삭제" : "";
 							} },
 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
 				  		],
	  		"fnInitComplete":function(){
 				//$('.tip a ').tipsy({trigger: 'manual'});
 				//$('.tip a ').tipsy("hide");
 			},
 			"fnDrawCallback": function () {
 				
 				$('.fancy').fancybox({//autodimensions false 후 width , height 가느
 					'autoDimensions':false,
 					'scrolling':'auto',
 					'autoScale':false,
 					'height':500,
 					//'centerOnScroll':true
 					//'title':'사용자 정보 수정'

 				});
 				$('.delete').bind('click', function() {
 					if(confirm("삭제하시겠습니까?")){
 						$.ajax({
 	 	                     url: "/partner/event/delete",
 	 	                     type:"POST",
 	 	                     data:"eid="+$(this).attr('eid'),
 	 	                     beforeSend :function(){
 	 		   	 	 			  c.loading("delete",0);
 	 	                     },
 	 	                     success: function(response){
 	 	                     	if(response.status=="SUCCESS"){
 	 	                     		c.showSuccess("삭제 되었습니다 ",1000);
 	 	                     		datatable.fnStandingRedraw();
 	 	                     	}
 	 	                    	
 	 	                     },
 	 	                     error: function(jqXHR, textStatus, errorThrown){
 	 	                    	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
 	 	                     },
 	 	                     complete:function(){
 	 	                    	 setTimeout("c.unloading()",500); 
 	 	                    	 
 	 	                     }
 	 	                });
 					}
 				});
 				$('.deleteRevoke').bind('click', function() {
 					if(confirm("복구하시겠습니까?")){
 						$.ajax({
 	 	                     url: "/partner/event/deleteRevoke",
 	 	                     type:"POST",
 	 	                     data:"eid="+$(this).attr('eid'),
 	 	                     beforeSend :function(){
 	 		   	 	 			  c.loading("delete",0);
 	 	                     },
 	 	                     success: function(response){
 	 	                     	if(response.status=="SUCCESS"){
 	 	                     		c.showSuccess("복구 되었습니다 ",1000);
 	 	                     		datatable.fnStandingRedraw();
 	 	                     	}
 	 	                    	
 	 	                     },
 	 	                     error: function(jqXHR, textStatus, errorThrown){
 	 	                    	console.log(jqXHR.responseText);
 	 	                  		c.showError(jqXHR.responseText);
 	 	                     },
 	 	                     complete:function(){
 	 	                    	 setTimeout("c.unloading()",500); 
 	 	                    	 
 	 	                     }
 	 	                });
 					}
 				});
 			},	  		
 			"aaSorting": [[ 2, "desc" ]]
 		});//datatable
 		
 		$('.fancy').fancybox({//autodimensions false 후 width , height 가느
			'autoDimensions':false,
			'scrolling':'auto',
			'autoScale':false,
			'height':500,
		});
 		
 		
 		
 		$('#btnSubmit').live('click',function() {
 			c.log("submit Form");
 		

 			$('#placeEvent').submit();
 		});
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		});
 		$('#placeEvent').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					$.ajax({
 	                     url:"/partner/event/add",
 	                     type:"POST",
 	                     data:$("form").serialize(),
 	                     beforeSend :function(){
 		                      var title = $('.loading').attr('title');
 		   	 				  var overlay=$(this).attr('rel'); 
 		   	 	 			  c.loading(title,overlay);
 	                     },
 	                     success: function(response){
 	                    	c.log(response);
 	                       if(response.status=="SUCCESS"){
 	                    		//parent.$.fancybox.close();
 	                    		c.showSuccess("이벤트를 등록하였습니다 !",1000);
					
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
 	                    	 datatable.fnStandingRedraw();	                    
 	                     }
 	                  });//ajax
 				 }
 			  }
	 	});//validation
 		$(".dataTables_length select").addClass("small");
 	});//ready
 	
	function make_actions(oObj) {
 		var id = oObj.aData['eid'];
 		//c.log(oObj.aData[ oObj.iDataRow ][1] );
 		c.log(""+oObj.aData['placeStamp.sid']);
 		var editAction = '<span class="tip"><a class="fancy iframe" href="/partnere/event/edit?eid='+id+'" original-title="Edit"><i class="icon-edit icon-large  icon-border"></i></a></span>';
 	
 		var deleteAction = '<span class="tip"><a class="delete" eid="'+id+'" original-title="삭제"><i class="icon-trash icon-large  icon-border"></i></a></span>';
 		var deleteRevokeAction = '<span class="tip"><a class="deleteRevoke" eid="'+id+'" original-title="복구"><i class="icon-wrench icon-large  icon-border"></i></a></span>';
 		
 		return   editAction  + deleteAction + deleteRevokeAction; 
 	}
	function datatableRedraw(result,status){ 		
 		if(status){
	 		c.showSuccess(result,1500);
 		}else{
			c.showError(result,1500);		 			
 		}
 		datatable.fnStandingRedraw();
 	}
</script>

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 이벤트 리스트 - ${place.fname}</span>
	</div>
	<div class="content">
			<div class="tableName">
				<div style="position:absolute; ;right:200px">
					<div class="radiorounded"> 
	               		<input id="isDelete1"  type="radio" name="isDelete"  value="" checked /><label for="isDelete1" >전체</label>
	               		<input id="isDelete2" type="radio" name="isDelete"  value="0" /><label for="isDelete2" >사용</label>
	               		<input id="isDelete3" type="radio" name="isDelete"  value="1" /><label for="isDelete3" >삭제</label>
	               	</div>  
	            </div>   
				<div style="position:absolute; ;right:500px">
					<div class="radiorounded"> 
	               		<input id="isAuth1"  type="radio" name="isAuth"  value="1"  /><label for="isAuth1" >승인</label>
	               		<input id="isAuth2" type="radio" name="isAuth"  value="0" checked /><label for="isAuth2" >대기</label>
	               	</div>  
	            </div>   
			<span style="position:absolute"><a href="/partner/event/add?fid=${place.fid}" class="uibutton icon large add ">이벤트 생성  </a></span>
				<table class="display" id="datatable">
				<thead>
					<tr>
						<th>이벤트명 </th>
						<th>StartDate</th>
						<th>EndDate</th>
						<th>SaveDate</th>
						<th>EditDate</th>
						<th>승인여부</th>
						<th>삭제여부</th>
						<th>Management</th>
					</tr>
				</thead>
				 <!-- <tbody>
					<c:forEach items="${placeEventList}" var="placeEvent">
						<tr>
							<td>${placeEvent.title}</td>
							<td><fmt:formatDate value="${placeEvent.editDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeEvent.startDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeEvent.endDate }" pattern="yyyy-MM-dd"/></td>
							<td>${placeEvent.isDelete}</td>
							<td>
								<span class="tip"><a class="edit" href="/admin/place/stamp/edit?fid=${placeEvent.eid}&stampid=${placeEvent.eid}" original-title="stamp-edit"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				 --> 
				</table>
			</div>
		</div>	
</div>



