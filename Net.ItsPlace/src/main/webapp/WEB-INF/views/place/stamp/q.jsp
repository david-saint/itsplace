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
		<span><span class="ico gray home"></span>   스탬프 적립 및 소진    </span>
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
		 			"sAjaxSource": "/place/stamp/getPlaceStampUserList",
		 			"sAjaxDataProp": "rows",
		 			"aoColumns": [
		 				  			{ "mDataProp": "user.profileImageUrl" , "fnRender"  :function ( oObj ) {
		 				  				return "<img src=\""+oObj.aData['user'].profileImageUrl+ "\" style=\"width:50px;\" />";
		 							} },
		 				  			{ "mDataProp": "user.email", "sClass": "left", "sWidth": "150px"},
		 				  			{ "mDataProp": "user.name", "sClass":"left" },
		 				  			{ "mDataProp": "user.mobile", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "stampedTotal" },
		 				  			{ "mDataProp": "stampedLastDate", "fnRender"  :function ( oObj ) {
		 								return c.render_date(oObj.aData['stampedLastDate'],'yyyy-MM-dd');
		 							} },
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
		 				
		 				$('.saveStamp').fancybox({//autodimensions false 후 width , height 가느
		 					'autoDimensions':false,
		 					'scrolling':'auto',
		 					'autoScale':false,
		 					'height':500,
		 					'width':520,
		 					//'centerOnScroll':true
		 					//'title':'사용자 정보 수정'

		 				});
		 				
		 			},
		 			"aaSorting": [[ 7, "desc" ]]
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 		$('.dataTables_filter').find('input').focus();
			});
		 	
		 	function make_actions(oObj) {
		 		var id = oObj.aData['user'].email;
		 		//	c.log(oObj.aData[1]);
		 		//iframe 펜시박스 일경우는 ok 아닐경우 자바스크립트 초기화 오류남 
		 		var ViewStampAction = '<span class="tip"><a class="saveStamp iframe" href="/place/stamp/stampped?email='+id+'" original-title="적립 및 소진"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		var saveAction = '<span class="tip"><a class="userDelete" email="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		var burnAction = '<span class="tip"><a class="userDelete" email="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		
		 		return  ViewStampAction; //"&nbsp;&nbsp;" + saveAction + "&nbsp;&nbsp;" + burnAction ; 
		 	}
		 	function datatableRedraw(result,status){
		 		c.log("dataTableRedraw");
		 		if(status=="true"){
			 		c.showSuccess(result,1500);
		 		}else{
					c.showError(result,1500);		 			
		 		}
		 		user_datatable.fnStandingRedraw();
		 	}
		 </script>
		 <div class="tableName"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"><a href="/admin/user/add" class="uibutton icon large add ">Add User</a></span>
			 <table class="display" id="user_datatable">
				<thead>
					<tr>
						<th>profile</th>
						<th>email</th>
						<th>name</th>
						<th>mobile</th>
						<th>stampcount</th>
						<th>마지막적립일</th>
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

