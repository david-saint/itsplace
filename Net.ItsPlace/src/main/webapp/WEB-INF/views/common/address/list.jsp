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
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 주소검색   </span>
	</div>
	<div class="content">
		<script type="text/javascript">
			var datatable;
		
		 	$(document).ready(function(){
		 		 datatable = $('#datatable').dataTable( {
		 			"sDom": 'fCl<"clear">rtip', //컬럼숨김
		 			"bFilter": true, 
		 			"bPaginate": true,
		 			"bLengthChange": true, 
		 			"sPaginationType": "full_numbers",
		 			"bProcessing": true,
		 			"oLanguage": {
		 		         "sProcessing": "<div style='border:0px solid red'>Address Loading...</di>"
		 		       },
		 			"bServerSide": true,		 			
		 			"sAjaxSource": "/address/list",
		 			"sAjaxDataProp": "rows",
		 			"aoColumns": [
		 				  				 				  			
		 				  			{ "mDataProp": "sido", "sClass":"left", "sWidth": "150px"},
		 				  			{ "mDataProp": "gugun", "sClass":"left", "sWidth": "150px"},
		 				  			{ "mDataProp": "bupname", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "jimain", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "jisubmain", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "doroname", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "bldmain", "sClass":"left" },		 				  		
		 				  			{ "mDataProp": "bldsubmain", "sClass":"left" }		 				  		
		 				  			
		 				  		],
		 			//"oLanguage": {
		 			//                "sUrl": "/resources/common/datatables.txt"
		 			//            },	  		
			  		"fnInitComplete":function(){
		 				$('.tip a ').tipsy({trigger: 'manual'});
		 				$('.tip a ').tipsy("hide");
		 			},
		 			"fnDrawCallback": function () {
		 				
		 				$('.edit').fancybox({//autodimensions false 후 width , height 가느
		 					'autoDimensions':false,
		 					'scrolling':'auto',
		 					'autoScale':false,
		 					'height':500,
		 					//'centerOnScroll':true
		 					//'title':'사용자 정보 수정'
	
		 				});
		 				
		 			},	  		
		 			"aaSorting": [[ 2, "desc" ]]
		 		});
		 		
		 		//datatable row selectbox style
		 		$(".dataTables_length select").addClass("small");
		 		
		 		
			});
		 	
		 	function make_actions(oObj) {
		 		var id = oObj.aData['fid'];
		 		//c.log(oObj.aData[ oObj.iDataRow ][1] );
		 		c.log(""+oObj.aData['placeStamp.sid']);
		 		var editAction = '<span class="tip"><a class="edit iframe" href="/admin/place/edit?fid='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
		 		
		 		var stampAddAction = '<span class="tip"><a class="" href="/admin/place/stamp/add?fid='+id+'" original-title="stamp-add"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>';
		 		var eventAddAction = '<span class="tip"><a class="" href="/admin/place/event/list?fid='+id+'" original-title="event-add"><img src="/resources/admin/images/icon/color_18/bell.png"></a><span>';
		 		var deleteAction = '<span class="tip"><a class="delete" fid="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
		 		
		 		return  stampAddAction +"&nbsp;&nbsp;"+ eventAddAction +"&nbsp;&nbsp;"+ editAction + "&nbsp;&nbsp;" + deleteAction ; 
		 	}
		 	function refresh(){
		 		c.log("refresh");
		 		datatable.fnStandingRedraw();
		 	}
		 </script>
		 <div class="tableName"><!--클래 tableName search box를 타이 이동험   -->
		 	<span style="position:absolute"></span>
			 <table class="display" id="datatable">
				<thead>
					<tr>
						<th >시도</th>
						<th >GUGUN</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						<th >fname</th>
						
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

