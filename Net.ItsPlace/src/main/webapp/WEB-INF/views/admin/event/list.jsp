<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
	var datatable; 
 	$(document).ready(function(){
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 		$('input[type=radio]').live('change', function() {
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
 			"sAjaxSource": "/admin/event/getPlaceEventListAll",
 			"fnServerParams": function (aoData, fnCallback) {
 				 aoData.push( { "name": "isDelete", "value": $('input[name=isDelete]:checked').val()} );	 			               
			},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
							{ "mDataProp": "place.fname" },
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
 								
 				  				return oObj.aData['isAuth'] ? "승인":"미승인";
 								
 							} },
 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
 				  		],
	  		"fnInitComplete":function(){
 				$('.tip a ').tipsy({trigger: 'manual'});
 				$('.tip a ').tipsy("hide");
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
 					$.ajax({
 	                     url: "/admin/place/disable",
 	                     type:"POST",
 	                     data:"fid="+$(this).attr('fid'),
 	                     beforeSend :function(){
 		   	 	 			  c.loading("delete",0);
 	                     },
 	                     success: function(response){
 	                     	if(response.status=="SUCCESS"){
 	                     		c.showSuccess("승인 취소되었습니",1000);
 	                     	}
 	                    	
 	                     },
 	                     error: function(jqXHR, textStatus, errorThrown){
 	                    	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
 	                     },
 	                     complete:function(){
 	                    	 setTimeout("c.unloading()",500); 
 	                    	 datatable.fnStandingRedraw();
 	                     }
 	                });//ajax */
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
 	                     url:"/admin/place/event/add",
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
 		var editAction = '<span class="tip"><a class="fancy iframe" href="/admin/place/event/edit?eid='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
 	
 		var deleteAction = '<span class="tip"><a class="delete" fid="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
 		
 		return   editAction + "&nbsp;&nbsp;" + deleteAction ; 
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
				<table class="display" id="datatable">
					<thead>
						<tr>
							<th>가맹점명 </th>
							<th>이벤트명 </th>
							<th>시작일자</th>
							<th>종료일자</th>
							<th>등록일자</th>							
							<th>수정일자</th>							
							<th>승인여부</th>
							<th>Management</th>
						</tr>
					</thead>				
				</table>
			</div>
		</div>	
</div>



