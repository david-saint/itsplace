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

		<div class="section">
			<div style="float:left">
				<a href="/admin/base/add" class="uibutton icon large add ">Add BASE</a>
			</div>
			<div  style="text-align: right">			
				<form:select id="grpcd" path="grpBasCdList" multiple="false">
					<form:option value="" label="전체" />
					<form:options items="${grpBasCdList}" itemValue="grpcd" itemLabel="grpName" />
				</form:select>
				</div>
			</div>

		<script type="text/javascript">
		 	$(document).ready(function(){
		 		var staticBase = $('.staticBase').dataTable({
		 			"bLengthChange": false, //로우수
		 			"bFilter": true, //search
		 			"bPaginate": true,
		 			
		 			"aaSorting": [],
		 		    "aoColumns": [
		 						{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false }
		 		  ]
		 		});
		 		$('.dataTables_filter').hide();
		 		$('#grpcd').change(function(){			
		 			console.log($(this).val());
		 			staticBase.fnFilter( $(this).val() );
		 			//staticBase.fnDraw();
				});
			});
		 </script>
		 
		<div class="tableName">	
			<table class="display staticBase" id="static">
				<thead>
					<tr>
						<th>No</th>
						<th>GroupCode</th>
						<th>GroupName</th>
						<th>CodeName</th>
						<th>BaseCode</th>
						<th>Remark</th>
						<th>isDelete</th>
						<th>Management</th>
					</tr>
				</thead>
			 	<tbody>
				<c:forEach items="${basCdList}" var="basCd">
				<tr>
					<td>${basCd.no}</td>
					<td>${basCd.grpName}</td>
					<td>${basCd.grpcd}</td>
					<td>${basCd.basName}</td>
					<td>${basCd.basecd}</td>
					<td>${basCd.remark}</td>
					<td>${basCd.isDelete}</td>
					<td><span class="tip"> <a title="Edit" href="/admin/base/edit?no=${basCd.no}"> <img
								src="/resources/admin/images/icon/icon_edit.png">
						</a>
					</span> <span class="tip"> <a id="1" class="Delete"
							name="Band ring" title="Delete"> <img
								src="/resources/admin/images/icon/icon_delete.png">
						</a>
					</span></td>
				</tr>
	 		 	</c:forEach>
				</tbody> 
			</table>
		</div>


		<!-- clear fix -->
	<div class="clear"></div>
	</div>
	<!-- End content -->
</div>
<!-- End full width -->

