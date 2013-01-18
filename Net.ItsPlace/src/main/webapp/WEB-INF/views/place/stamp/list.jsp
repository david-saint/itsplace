<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>
<html>
	<head>
	
		<script type="text/javascript">
		 	$(document).ready(function(){
		 		
		 		
		 		var datatable = $('#static').dataTable({
		 			"bLengthChange": false, //로우수
		 			"bFilter": true, //search
		 			"bPaginate": true,
		 			"bServerSide": false,
		 			"aaSorting": [],
		 		    "aoColumns": [
		 						  { "bSortable": false },
		 						  { "bSortable": false },
		 						  { "bSortable": false },
		 						  { "bSortable": false },
		 						  { "bSortable": false },
		 						  
		 		                 ],
	                "oPaginate": {
					                "sFirst":    "처음",
					                "sPrevious": "이전",
					                "sNext":     "다음",
					                "sLast":     "마지막"
	           					},
		 		    "sInfoEmpty":    "게시 0 to 0 of 0 entries",
		 		    "fnDrawCallback": function () {
		 			  $('.delete').bind('click', function() {
		 				  //console.log($(this).attr('name'));
		 				Delete($(this).attr('name'),$(this).attr('stampid'));
		 				// $(this).remove();
		 				
		 				 //datatable.fnDraw(); 
		 			  });
		 		   }
		 		});
		 		
			});
		 	

		 	function Delete(title, stampid){
		 	
			 	$.confirm({
			 	'title': '삭제','message': " <strong>삭제하시겠습니까? </strong><br /><font color=red>' "+ title +" ' </font> ",
			    'buttons': {'Yes': {'class': 'special',
			 	'action': function(){
			 					DeleteAjax(stampid);
			 				 }},'No'	: {'class'	: ''}}});
		 	}
		   function DeleteAjax(stampid){
				$.ajax({
                     url: "/place/stamp/delete",
                     type:"POST",
                     data:"stampid="+stampid,
                     beforeSend :function(){
	   	 	 			  c.loading("delete",0);
                     },
                     success: function(response){
                     	if(response.status=="SUCCESS"){
                     		c.showSuccess(response.result,1000);
                     	}else{
                     		c.showError(response.result,1000);
                     	}
                    	
                     },
                     error: function(jqXHR, textStatus, errorThrown){
                    	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
                     },
                     complete:function(){
                    	 setTimeout("document.location.href=''",500); 
                     }
                });//ajax */
		   }
		 </script>
</head>
<body>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 스탬프 관리  </span>
	</div>
	<!-- End header -->
	<div class="content">

		<div class="section">
			<div style="float:left">
				<a href="/place/stamp/add" class="uibutton icon large add "> 스탬프 생성 </a>
			</div>
			

		 
		<div class="tableName">	
			<div class="setion">
				<table class="display staticBase" id="static">
				<thead>
					<tr>
						<th>스탬프명</th>
						<th>시작일 </th>
						<th>종료일 </th>
						<th>수정일</th>
						<th>관리 </th>
					</tr>
				</thead>
				 <tbody>
					<c:forEach items="${placeStampList}" var="placeStamp">
						<tr>
							<td>${placeStamp.stampTitle}</td>
							<td><fmt:formatDate value="${placeStamp.startDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeStamp.endDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeStamp.editDate }" pattern="yyyy-MM-dd"/></td>
						    <td>
						    	<span class="tip"><a class="edit" href="/place/stamp/edit?fid=${placeStamp.place.fid}&stampid=${placeStamp.stampid}" original-title="수정"><img src="/resources/admin/images/icon/icon_edit.png"></a></span>
						    	<span class="tip"><a class="delete" stampid="${placeStamp.stampid}" name="${placeStamp.stampTitle}" original-title="삭제"><img src="/resources/admin/images/icon/icon_delete.png"></a></span>
						    </td>
						</tr>
					</c:forEach>
				</tbody> 
				</table>
			</div>
		</div>


		<!-- clear fix -->
	<div class="clear"></div>
	</div>
	<!-- End content -->
</div>
<!-- End full width -->
</body>
</html>
