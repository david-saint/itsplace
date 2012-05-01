<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- full width -->
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
			

		<script type="text/javascript">
		 	$(document).ready(function(){
		 		var staticBase = $('.staticBase').dataTable({
		 			"bLengthChange": false, //로우수
		 			"bFilter": false, //search
		 			"bPaginate": true,
		 			
		 			"aaSorting": [],
		 		    "aoColumns": [
		 						  { "bSortable": false },
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
		 		});
		 		
			});
		 </script>
		 
		<div class="tableName">	
			<div class="setion">
				<table class="display staticBase" id="static">
				<thead>
					<tr>
						<th>StampTitle</th>
						<th>EditDate</th>
						<th>StartDate</th>
						<th>EndDate</th>
						<th>isDelete</th>
						<th>관리 </th>
					</tr>
				</thead>
				 <tbody>
					<c:forEach items="${placeStampList}" var="placeStamp">
						<tr>
							<td>${placeStamp.stampTitle}</td>
							<td><fmt:formatDate value="${placeStamp.editDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeStamp.startDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeStamp.endDate }" pattern="yyyy-MM-dd"/></td>
							<td>${placeStamp.isDelete}</td>
						    <td>
						    	<span class="tip"><a class="edit iframe" href="/admin/place/edit?fid='+id+'" original-title="수정"><img src="/resources/admin/images/icon/icon_edit.png"></a></span>
						    	<span class="tip"><a class="delete" fid="'+id+'" original-title="삭제"><img src="/resources/admin/images/icon/icon_delete.png"></a></span>
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

