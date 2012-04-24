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
		var user_datatable;
		
		 	$(document).ready(function(){
		 		
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
		 				  			{ "mDataProp": "isDelete" },
		 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
		 				  	
		 				  		],
		 			"aaSorting": [[ 2, "desc" ]]
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 		
			});
		 	
		 	function make_actions(oObj) {
		 		var id = oObj.aData['sid'];
		 		
		 		var editAction = '<span class="tip"><a class="userEdit iframe" href="/admin/stamp/edit?sid='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		var deleteAction = '<span class="tip"><a class="userDelete" email="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		
		 		return  editAction + "&nbsp;&nbsp;" + deleteAction ; 
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

