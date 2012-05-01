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
		<span><span class="ico gray home"></span> 직원등록  </span>
	</div>
	<!-- End header -->
	<div class="content">
		
		<script type="text/javascript">
		var user_datatable;
		
		 	$(document).ready(function(){
		 		
		 		 $('#btnSave').live('click',function() {
		 			 $('#fid').val($('#places').val());
		 			 
		  			c.log("submit Form");
		  			//return false;
		 			$('form').submit();
		 		 });
		 		 user_datatable = $('#user_datatable').dataTable( {
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
		 			"aoColumns": [
		 			             	{ "sDefaultContent": "", "fnRender" :function ( oObj ) {
		 								return "<input type=\"checkbox\" name=\"email\" class=\"ck\" value=\""+ oObj.aData['email']+"\" />";
		 							} },
		 				  			{ "mDataProp": "profileImageUrl" },
		 				  			{ "mDataProp": "email", "sClass": "left", "sWidth": "150px"},
		 				  			{ "mDataProp": "name", "sClass":"left" },
		 				  		],
		 			"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
		 			 
		 			},
		 			"fnInitComplete":function(){
		 				//$('.ck').customInput();	
		 				$('.tip a ').tipsy({trigger: 'manual'});
		 				$('.tip a ').tipsy("hide");
		 				
		 				
		 			},
		 			"fnDrawCallback": function () {
		 				
		 			},
		 			"aaSorting": [[ 7, "desc" ]]
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 		
			});
		 	
		 	
		 	function make_date(oObj) {
		 		var date = new Date( oObj.aData['editDate']);
		 		//date = oObj.aData['editDate'];
		 		c.log(date.getMonth()+date.getDay());
		 		var str = date.getFullYear()+"-"+date.getMonth()+1+"-"+date.getDate() ;
		 		return str;
		 	}
		 	function make_actions(oObj) {
		 		var id = oObj.aData['email'];
		 		
		 		var editAction = '<span class="tip"><a class="userEdit iframe" href="/admin/user/edit?email='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		var deleteAction = '<span class="tip"><a class="userDelete" email="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		
		 		return  editAction + "&nbsp;&nbsp;" + deleteAction ; 
		 	}
		 	function test(){
		 		c.log("test");
		 		user_datatable.fnStandingRedraw();
		 	}
		 </script>
		 <div class="tableName">
		 	<form action="/place/user/add" method="post">
		 	<input id="fid" type="hidden" name="fid" />
		 	<span style="position:absolute"><a id="btnSave"  class="uibutton icon large add">저 장 </a></span>
			 <table class="display" id="user_datatable">
				<thead>
					<tr>
						<th>선택 </th>
						<th class="center">profile</th>
						<th>email</th>
						<th>name</th>
					</tr>
				</thead>
			</table>
			</form>
		</div> 
	
		<div class="section last right">
			
		</div>


		<!-- clear fix -->
		<div class="clear"></div>

	</div>
	<!-- End content -->
</div>
<!-- End full width -->

