<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
	var datatable; 
 	$(document).ready(function(){
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 	    datatable = $('#datatable').dataTable( {
 			"sDom": 'fCl<"clear">rtip', //컬럼숨김
 			"bFilter": true, //search
 			"bPaginate": true,
 			"bLengthChange": true, //로우수
 			"sPaginationType": "full_numbers",
 			"bProcessing": true,
 			"oLanguage": {
 		         "sProcessing": "<div style='border:0px solid red'> 조회중 ...</di>"
 		       },
 			"bServerSide": true,		 			
 			"sAjaxSource": "/partner/getMenuList", 			
 			"sAjaxDataProp": "rows",
 			"aoColumns": [ 				  			
 				  			{ "mDataProp": "filePath", "fnRender" :function ( oObj ) {
 				  				return "<img src=\""+oObj.aData['host']+oObj.aData['filePath']+ "\" style=\"width:50px;\" />";
 							} },
 				  			{ "mDataProp": "title" },
 				  			{ "mDataProp": "content" }, 
 				  			{ "mDataProp": "price" },
 				  			{ "mDataProp": "isSale", "fnRender" :function ( oObj ) {
 								return oObj.aData['isSale'] == "Y" ? "세일중" : "";
 							} },
 							{ "mDataProp": "salePrice" },
 							{ "mDataProp": "sort" },
 							
 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
 				  		],
 			
	  		"fnInitComplete":function(){
 				//$('.tip a ').tipsy({trigger: 'manual'});
 				//$('.tip a ').tipsy("hide");
 			},
 			"fnDrawCallback": function () {
 				
 				$('.fancy').fancybox({//autodimensions false 후 width , height 가느
 					'autoDimensions':false,
 					'scrolling':'auto',
 					'autoScale':false,
 					'height':500,
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
 			"aaSorting": [[ 0, "desc" ]]
 		});//datatable
 		
 		$('.fancy').fancybox({//autodimensions false 후 width , height 가느
			'autoDimensions':false,
			'scrolling':'auto',
			'autoScale':false,
			'height':500,
		});
 		 		 	
 		
 		$(".dataTables_length select").addClass("small");
 	});//ready
	function make_actions(oObj) {
 		var id = oObj.aData['mnid'];
 		c.log("id:"+id );
 		//c.log(""+oObj.aData['placeStamp.sid']);
 		var editAction = '<span class="tip"><a class="fancy iframe" href="/partner/menu/edit?decorator=fancy&mnid='+id+'" original-title="Edit"><i class="icon-edit icon-large  icon-border"></i></a><span>';
 	
 		var deleteAction = '<span class="tip"><a class="delete" fid="'+id+'" original-title="Delete"><i class="icon-trash icon-large  icon-border"></i></a><span>';
 		
 		return   editAction + "&nbsp;&nbsp;" + deleteAction ; 
 	}
	function datatableRedraw(result,status){ 		
 		if(status){
	 		c.showSuccess(result,1500);
 		}else{
			c.showError(result,1500);		 			 
 		}
 		datatable.fnStandingRedraw();
 	}
	function refresh(){
 		c.log("refresh");
 		datatable.fnStandingRedraw();
 	}
</script>

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 메뉴 목록 - ${place.fname}</span>
	</div>
	<div class="content">
			<div class="tableName">
			<span style="position:absolute"><a href="/partner/menu/add" class=" uibutton icon large add ">메뉴 생성  </a></span>
				<table class="display" id="datatable">
				<thead>
					<tr>
						<th>사 진 </th>
						<th>메뉴명 </th>
						<th>메뉴설명</th>
						<th>가격 </th>
						<th>세일여부 </th>
						<th>세일가격 </th>
						<th>순서 </th>							
						<th>Management</th>
					</tr>
				</thead>
				 <!-- <tbody>
					<c:forEach items="${placeEventList}" var="placeEvent">
						<tr>
							<td>${placeEvent.title}</td>
							<td><fmt:formatDate value="${placeEvent.editDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeEvent.startDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeEvent.endDate }" pattern="yyyy-MM-dd"/></td>
							<td>${placeEvent.isDelete}</td>
							<td>
								<span class="tip"><a class="edit" href="/admin/place/stamp/edit?fid=${placeEvent.eid}&stampid=${placeEvent.eid}" original-title="stamp-edit"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				 --> 
				</table>
			</div>
		</div>	
</div>



