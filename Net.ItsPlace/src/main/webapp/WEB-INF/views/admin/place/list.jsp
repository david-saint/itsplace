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
		<span><span class="ico gray home"></span> 사용자관리  </span>
	</div>
	<!-- End header -->
	<div class="content">
		
		<script type="text/javascript">
		var user_datatable;
		
		 	$(document).ready(function(){
		 		
		 		 user_datatable = $('#userw_datatable').dataTable( {
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
		 			"sAjaxSource": "/admin/place/getPlaceList",
		 			"sAjaxDataProp": "rows",
		 			"aoColumns": [
		 				  			{ "mDataProp": "fid" },
		 				  			{ "mDataProp": "fileName", "sClass": "left", "sWidth": "150px"},
		 				  			{ "mDataProp": "fname", "sClass":"left" },
		 				  			{ "mDataProp": "name", "sClass":"left" },
		 				  			{ "mDataProp": "mobile",},
		 				  			{ "mDataProp": "authyn",},
		 				  			{ "mDataProp": "address.hdongname" },
		 				  			{ "mDataProp": "inpDate" },
		 				  			{ "sDefaultContent": ""},
		 				  	
		 				  		],
		 			"aaSorting": [[ 2, "desc" ]]
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 		
			});
		 	
		 	
		 </script>
		 <div class="tableName"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"><a href="/admin/user/add" class="uibutton icon large add ">Add User</a></span>
			 <table class="display" id="userw_datatable">
				<thead>
					<tr>
						<th class="center">fid</th>
						<th >fileName</th>
						<th class="center">fname</th>
						<th class="center">name</th>
						<th class="center">mobile</th>
						<th>authyn</th>
						<th>hdongname</th>
						<th>inpDate</th>
						
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

