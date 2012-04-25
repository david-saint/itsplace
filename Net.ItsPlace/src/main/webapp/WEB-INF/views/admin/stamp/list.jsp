<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.right{text-align:right;}
.left{text-align:left;}
.tableName th{text-align:center;}
</style>

<!-- full width -->
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 스탬프관리  </span>
	</div>
	<!-- End header -->
	<div class="content">
		
		<script type="text/javascript">
		
		
		 	$(document).ready(function(){
		 		var stampType_datatable;
		 		stampType_datatable = $('#stampType_datatable').dataTable( {
		 			"sDom": 'fCl<"clear">rtip', //컬럼숨김
		 			"bFilter": true, //search
		 			"bPaginate": true,
		 			"bLengthChange": true, //로우수
		 			"sPaginationType": "full_numbers",
		 			"bProcessing": true,
		 			"oLanguage": {
		 		         "sProcessing": "<div style='border:0px solid red'>User List Loading...</di>"
		 		       },
		 			"bServerSide": true,		 			
		 			"sAjaxSource": "/admin/stamp/getStampTypeList",
		 			"sAjaxDataProp": "rows",
		 			"aoColumns": [
		 				  			{ "mDataProp": "sid" },
		 				  			{ "mDataProp": "title", "sClass": "left", "sWidth": "150px"},
		 				  			{ "mDataProp": "stampcount", "sClass":"left" },
		 				  			{ "mDataProp": "eventday", "sClass":"left" },
		 				  			{ "mDataProp": "remark"},
		 				  			{ "mDataProp": "dispseq"},
		 				  			{ "mDataProp": "editDate", "fnRender" :function ( oObj ) {
		 								return c.render_date(oObj.aData['editDate'],'yyyy-MM-dd');
		 							} },
		 				  			{ "mDataProp": "isDelete" ,"fnRender" :function ( oObj ) {
		 								return oObj.aData['isDelete'] == " Y" ? "삭제" : "사용";
		 							} },
		 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
		 				  	
		 				  		],
		 			"aaSorting": [[ 2, "desc" ]],
					"fnDrawCallback": function () {
		 				
		 				$('.edit').fancybox({
		 					'autoDimensions':false,
		 					'scrolling':'auto',
		 					'autoScale':false,
		 					'height':500,
		 					//'centerOnScroll':true
		 					//'title':'사용자 정보 수정'

		 				});//edit
		 				$('.delete').bind('click', function() {
		 					c.log("deete");
		 					
		 					$.ajax({
		 	                     url: "/admin/stamp/delete",
		 	                     type:"POST",
		 	                     data:"sid="+$(this).attr('sid'),
		 	                     beforeSend :function(){
		 		   	 	 			  c.loading("delete",0);
		 	                     },
		 	                     success: function(response){
		 	                       
		 	                     	if(response.status=="SUCCESS"){
		 	                     		c.showSuccess("삭제성공",1000);
		 	                     	};
		 	                     	
		 	                     },
		 	                     error: function(jqXHR, textStatus, errorThrown){
		 	                    	alert(textStatus+"j"+jqXHR+"e:"+errorThrown);
		 	                    	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
		 	                     },
		 	                     complete:function(){
		 	                    	 setTimeout("c.unloading()",500); 
		 	                    	stampType_datatable.fnStandingRedraw();
		 	                     }
		 	                 });
		 					
		 				});//delete
		 			},
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 		
			});
		 	
		 	function make_actions(oObj) {
		 		var id = oObj.aData['sid'];
		 		
		 		var editAction = '<span class="tip"><a class="edit iframe" href="/admin/stamp/edit?sid='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		var deleteAction = '<span class="tip"><a class="delete" sid="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		var restoreAction = '<span class="tip"><a class="restore" sid="'+id+'" original-title="Restore"><img src="/resources/admin/images/icon/color_18/redo.png"></a><span>';
		 		
		 		return  editAction + "&nbsp;&nbsp;" + deleteAction + "&nbsp;&nbsp;" + restoreAction ; 
		 	}
		 </script>
		 <div class="tableName"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"><a href="/admin/stamp/add" class="uibutton icon large add ">Add Stamp Type</a></span>
			 <table class="display" id="stampType_datatable">
				<thead>
					<tr>
						<th class="center">sid</th>
						<th>title</th>
						<th class="center">stampcount</th>
						<th class="center">eventday</th>
						<th class="center">remark</th>
						<th>dispseq</th>
						<th>editDate</th>
						<th>isDelete</th>
						<th>Management</th>
					</tr>
				</thead>
			</table>
		</div> 
	
		<div class="section last right">
			
		</div>


		<!-- clear fix -->
		<div class="clear"></div>

	</div>
	<!-- End content -->
</div>
<!-- End full width -->

