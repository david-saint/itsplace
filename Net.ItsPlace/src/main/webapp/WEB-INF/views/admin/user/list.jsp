<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!-- full width -->
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 사용자관리  </span>
	</div>
	<!-- End header -->
	<div class="content">
		
		<script type="text/javascript">
		 	$(document).ready(function(){
		 		
		 		var user_datatable = $('#user_datatable').dataTable( {
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
		 			"sAjaxSource": "/admin/user/getUserList",
		 			"sAjaxDataProp": "rows",
		 			"aoColumns": [//"profileImageUrl", "email", "name","role", "mobile", "useyn", "emailyn"
		 				  			{ "mDataProp": "profileImageUrl" },
		 				  			{ "mDataProp": "email" },
		 				  			{ "mDataProp": "name" },
		 				  			{ "mDataProp": "role" },
		 				  			{ "mDataProp": "mobile" },
		 				  			{ "mDataProp": "useyn" },
		 				  			{ "mDataProp": "emailyn" },
		 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
		 				  	
		 				  		],
		 			
		 			"fnInitComplete":function(){
		 				$('.userEdit').fancybox({
		 					'autoDimensions':false,
		 					'scrolling':'auto',
		 					'autoScale':true,
		 					'height':700,
		 					'centerOnScroll':true,
		 					'title':'사용자 정보 수정'

		 				});
		 				
		 				
		 			},
		 			"aaSorting": [[ 2, "asc" ]]
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 		
			});
		 	function make_actions(oObj) {
		 		var id = oObj.aData['email'];
		 		
		 		var editAction = '<span class="tip"><a class="userEdit" href="/admin/user/edit?email='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		var deleteAction = '<span class="tip"><a class="userEdit" href="/admin/user/edit?email='+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		
		 		return  editAction + "&nbsp;&nbsp;" + deleteAction ; 
		 	}
		 </script>
		 <div class="tableName"><!-- tableName search box 이동  -->
		 	<span style="position:absolute"><a href="/admin/user/add" class="uibutton icon large add ">Add User</a></span>
			 <table class="display " id="user_datatable">
				<thead>
					<tr>
						<th width="">profile</th>
						<th width="">email</th>
						<th width="">name</th>
						<th width="">role</th>
						<th width="">mobile</th>
						<th width="">useyn</th>
						<th width="">emailyn</th>
						<th width="">Management</th>
							
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

