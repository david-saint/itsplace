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
								x
		<div class="section" style="text-align: right">
			<div>
			<ul class="uibutton-group">
                                    <li><a class="uibutton icon add "  title="Add Product">Add Product</a></li>
                                    <li><a class="uibutton special DeleteAll"  >Delete</a></li>
                              </ul>
			</div>
		</div>
		<script type="text/javascript">
		 	$(document).ready(function(){
		 		
		 		var example = $('#example').dataTable( {
		 			"bFilter": true, //search
		 			"bPaginate": true,
		 			"bLengthChange": false,
		 			"sPaginationType": "full_numbers",
		 			"bProcessing": true,
		 			"oLanguage": {
		 		         "sProcessing": "<div style='border:0px solid red'>Loading...</di>"
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
		 		
		 		
		 		
		 		
			});
		 	function make_actions(oObj) {
		 		var id = oObj.aData['email'];
		 		
		 		var editAction = '<span class="tip"><a class="userEdit" href="/admin/user/edit?email='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		var deleteAction = '<span class="tip"><a class="userEdit" href="/admin/user/edit?email='+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		
		 		return  editAction + "&nbsp;&nbsp;" + deleteAction ; 
		 	}
		 </script>
		 
		 <table class="display data_table2" id="example">
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
	<a class="uibutton icon large add right">button</a>
		<div class="section last right">
			<div>
				<a class="uibutton icon large add right">button</a>
			</div>
		</div>


		<!-- clear fix -->
		<div class="clear"></div>

	</div>
	<!-- End content -->
</div>
<!-- End full width -->

