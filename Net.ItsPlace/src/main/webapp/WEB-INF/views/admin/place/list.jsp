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
		<span><span class="ico gray home"></span> 가맹점관리   </span>
	</div>
	<div class="content">
		<script type="text/javascript">
			var datatable;
		
		 	$(document).ready(function(){
		 		 datatable = $('#datatable').dataTable( {
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
		 				  			{ "mDataProp": "fileName", "fnRender"  :function ( oObj ) {
		 				  				c.log("imagehost:"+"<img src=\""+oObj.aData['imageHost']);
		 								return "<img src=\""+oObj.aData['imageHost']+oObj.aData['fileName']+ "\" style=\"width:50px;\" />";
		 							} },
		 				  			{ "mDataProp": "fname", "sClass":"left", "sWidth": "150px"},
		 				  			{ "mDataProp": "name", "sClass":"left" },
		 				  			{ "mDataProp": "mobile", "sWidth": "150px"},
		 				  			{ "mDataProp": "isAuth", "fnRender" :function ( oObj ) {
		 								return oObj.aData['isAuth'] == " Y" ? "승인" : "미승인";
		 							} },
		 				  			{ "mDataProp": "dong" },
		 				  			{ "mDataProp": "saveDate", "fnRender"  :function ( oObj ) {
		 								return c.render_date(oObj.aData['editDate'],'yyyy-MM-dd');
		 							} },
		 				  			{ "mDataProp": "editDate","fnRender"  :function ( oObj ) {
		 								return c.render_date(oObj.aData['editDate'],'yyyy-MM-dd');
		 							} },
		 				  			{ "sDefaultContent": "","sWidth": "150px", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
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
		 					'height':600,
		 					'width':1300,
		 					//'centerOnScroll':true
		 					//'title':'사용자 정보 수정'
	
		 				}); 
		 				$('.delete').bind('click', function() {
		 					$.ajax({
		 	                     url: "/admin/place/disable",
		 	                     type:"POST",
		 	                     data:"fid="+$(this).attr('fid'),
		 	                     beforeSend :function(){
		 		   	 	 			  c.loading("delete",0);
		 	                     },
		 	                     success: function(response){
		 	                     	if(response.status=="SUCCESS"){
		 	                     		c.showSuccess("승인 취소되었습니",1000);
		 	                     	}
		 	                    	
		 	                     },
		 	                     error: function(jqXHR, textStatus, errorThrown){
		 	                    	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
		 	                     },
		 	                     complete:function(){
		 	                    	 setTimeout("c.unloading()",500); 
		 	                    	 datatable.fnStandingRedraw();
		 	                     }
		 	                });//ajax */
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
		 	<span style="position:absolute"><a href="/admin/place/add" class="uibutton icon large add ">가맹점 추가 </a></span>
			 <table class="display" id="datatable">
				<thead>
					<tr>
						<th class="center">FID</th>
						<th>대표사진</th>
						<th>업체명</th>
						<th>대표자</th>
						<th>휴대폰</th>
						<th>승인여부</th>
						<th>도로명</th>
						<th>신청일자</th>
						<th>수정일자</th>
						<th>관리</th>
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

