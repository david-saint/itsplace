<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!-- full width -->
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 기초코드 </span>
	</div>
	<!-- End header -->
	<div class="content">

		<div class="section" style="text-align: right">
			<div>
			
			</div>
		</div>
		<script type="text/javascript">
		 	$(document).ready(function(){
		 		
		 		var example = $('#example').dataTable( {
		 			 "bFilter": false, //search
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
		 				  			{ "sDefaultContent": "", "fnRender" : make_crud_actions, "bSortable": false, "bSearchable": false },
		 				  			/*{ "sName": "actions","fnRender": function ( oObj ) {
		 								return "3";
		 							}}*/
		 				  		],
		 			"fnServerParams": function (aoData, fnCallback) {
		 			               //aoData.push( { "name": "grpCD", "value":  "2" } );		 			               
		 			},
		 			"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
		 				//alert(""+nRow);
		 				/*if ( aData[4] == "A" )
					      {
					        $('td:eq(4)', nRow).html( '<b>A</b>' );
					      }*/
					      //로우 렌더링 가능
		 			},
		 			"fnInitComplete":function(){
		 				$('.userEdit').fancybox({
		 					'autoDimensions':false,'scrolling':'auto','autoScale':true,'height':700,'centerOnScroll':true,
		 					'title':'사용자 정보 수정'

		 				});
		 				c.log("dd");
		 			}
		 			//"aaSorting": [[ 0, "desc" ]],
		 		});
		 		
		 		$("#example tbody td").live('click',function(){
		 		    var aPos = $(example).dataTable().fnGetPosition(this);
		 			console.log("aPos:"+aPos);
		 		    var aData = $(example).dataTable().fnGetData(aPos[0]);
		 		    console.log("aData1:"+aData.email);
		 		    // at this point aData is an array containing all the row info, use it to retrieve what you need.
		 		});
		 		
		 		
			});
		 	function make_crud_actions(oObj) {
		 		var id = oObj.aData['email'];
		 		
		 		var detailAction = '<span class="tip"><a class="userEdit" href="/admin/user/edit?email=3" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		var editAction = '<a href="""' + id + '">Edit</a>';
		 		var deleteAction = '<a href=""' + id + '">Delete</a>';
		 		
		 		return detailAction + "&nbsp;&nbsp;" + editAction + "&nbsp;&nbsp;" + deleteAction ; 
		 	}
		 </script>
		 
		 <table class="display" id="example">
		<thead>
			<tr>
				<th width="">Group</th>
				<th width="">GrpCode</th>
				<th width="">CodeName</th>
				<th width="">Management</th>
					
			</tr>
		</thead>
	</table>
	
		


		<!-- clear fix -->
		<div class="clear"></div>

	</div>
	<!-- End content -->
</div>
<!-- End full width -->

ß