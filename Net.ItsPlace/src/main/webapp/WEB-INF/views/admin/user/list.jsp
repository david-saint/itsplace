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
		 			"aoColumns": [//"profileImageUrl", "email", "name","role", "mobile", "useyn", "emailyn"
		 				  			{ "mDataProp": "profileImageUrl" },
		 				  			{ "mDataProp": "email", "sClass": "left", "sWidth": "150px"},
		 				  			{ "mDataProp": "name", "sClass":"left" },
		 				  			{ "mDataProp": "role", "sClass":"left" },
		 				  			{ "mDataProp": "mobile", "sClass":"left" },
		 				  			{ "mDataProp": "isDelete","fnRender" :function ( oObj ) {
		 								return oObj.aData['useyn'] == " Y" ? "사용" : "탈퇴";
		 							} },
		 				  			{ "mDataProp": "isEmail","fnRender" :function ( oObj ) {
		 								return oObj.aData['emailyn'] == " Y" ? "사용" : "탈퇴";
		 							} },
		 				  			{ "mDataProp": "saveDate" },
		 				  			{ "mDataProp": "editDate","fnRender" : make_date },
		 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
		 				  	
		 				  		],
		 			"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
		 					//c.log("aDAta:"+ aData[0]+ aData[1]+ aData[6]+ aData[7]);
		 					//c.log("nROw:"+ iDisplayIndex);
		 			},
		 			"fnInitComplete":function(){
		 				//$('.datatable_tip a').tipsy({gravity: 's',live: false});
		 				
		 				
		 				$('.tip a ').tipsy({trigger: 'manual'});
		 				$('.tip a ').tipsy("hide");
		 				//$('.userDelete').tipsy("hide").hide();
		 				
		 				
		 			},
		 			"fnDrawCallback": function () {
		 				
		 				$('.userEdit').fancybox({//autodimensions false 후 width , height 가느
		 					'autoDimensions':false,
		 					'scrolling':'auto',
		 					'autoScale':false,
		 					'height':500,
		 					//'centerOnScroll':true
		 					//'title':'사용자 정보 수정'

		 				});
		 				$('.userDelete').bind('click', function() {
		 					c.log("deete");
		 					
		 					$.ajax({
		 	                     url: "/admin/user/delete",
		 	                     type:"POST",
		 	                     data:"email="+$(this).attr('email'),
		 	                     beforeSend :function(){
		 		   	 	 			  c.loading("delete",0);
		 	                     },
		 	                     success: function(response){
		 	                       
		 	                     	console.log("송고"+response.status);
		 	                    	 // user_datatable.fnClearTable(0);
		 	                    	 //user_datatable.fnDraw();
		 	                    	 // refreshTable(user_datatable);
		 	                    	 // user_datatable.fnReloadAjax();
		 	                    	//  user_datatable.fnStandingRedraw();
		 	                     },
		 	                     error: function(jqXHR, textStatus, errorThrown){
		 	                    	alert(textStatus+"j"+jqXHR+"e:"+errorThrown); 
		 	                     },
		 	                     complete:function(){
		 	                    	//$('#user').validationEngine('detach');
		 	                    	 setTimeout("c.unloading()",500); 
		 	                    	//$('.datatable_tip a').tipsy({gravity: 's',live: false});
		 	                    	
		 	                    	  user_datatable.fnStandingRedraw();
		 	                     }////
		 	                   });//ajax */
		 					
		 				});
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
		 <div class="tableName"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"><a href="/admin/user/add" class="uibutton icon large add ">Add User</a></span>
			 <table class="display" id="user_datatable">
				<thead>
					<tr>
						<th class="center">profile</th>
						<th >email</th>
						<th class="center">name</th>
						<th class="center">role</th>
						<th class="center">mobile</th>
						<th>useyn</th>
						<th>emailyn</th>
						<th>saveDate</th>
						<th>editDate</th>
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

