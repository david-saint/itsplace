<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
/*jqgrid에서 사용 jquery ui css 사용안하고 아래 스타일로 지정*/
.ui-corner-all, .ui-corner-top, .ui-corner-right, .ui-corner-tr {
-moz-border-radius-topright: 4px;
-webkit-border-top-right-radius: 4px;
-khtml-border-top-right-radius: 4px;
border-top-right-radius: 4px;
}
.ui-corner-all, .ui-corner-top, .ui-corner-left, .ui-corner-tl {
-moz-border-radius-topleft: 4px;
-webkit-border-top-left-radius: 4px;
-khtml-border-top-left-radius: 4px;
border-top-left-radius: 4px;
}
</style>

<!-- full width -->
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 기초코드 </span>
	</div>
	<!-- End header -->
	<div class="content">

		<div class="section" style="text-align: right">
			<div>
				<form:select id="grpCd" path="grpBasCdList" multiple="false">
					<form:option value="" label="전체" />
					<form:options items="${grpBasCdList}" itemValue="grpCd"
						itemLabel="grpName" />
				</form:select>
				<script type="text/javascript">
					 $(document).ready(function(){
						 
						/*$('#grpCd').change(function(){											
							c.movePage("/admin/base/list?grpCd="+$('#grpCd').val());
							c.log("dddwwwwwwwwww");
						});*/
				  	 });
				 </script>
			</div>
		</div>
		<script type="text/javascript">
		 	$(document).ready(function(){
		 		$('.staticBase').dataTable({
		 			"sDom": '',
		 			"aaSorting": [],
		 		  "aoColumns": [
		 						{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false },{ "bSortable": false }
		 		  ]
		 		});
		 		
		 		
		 		var example = $('#example').dataTable( {
		 			 "bFilter": true, //search
		 			"bPaginate": true,
		 			"bLengthChange": false,
		 			"sPaginationType": "full_numbers",
		 			"bProcessing": true,
		 			"bServerSide": true,		 			
		 			"sAjaxSource": "<c:url value="/admin/base/table"/>",
		 			"sAjaxDataProp": "rows",
		 			"aoColumns": [
		 				  			{ "mDataProp": "grpCd" },
		 				  			{ "mDataProp": "grpName" },
		 				  			{ "mDataProp": "basName" }, 
		 				  		],
		 			"fnServerParams": function (aoData, fnCallback) {
		 			               aoData.push( { "name": "grpCd", "value":  $('#grpCd').val() } );		 			               
		 			        }
		 			//"aaSorting": [[ 0, "desc" ]],
		 		});
		 		
		 		$('#grpCd').change(function(){			
		 			console.log($(this).val());
		 			//example.fnFilter( $(this).val() );
		 			example.fnDraw();
				});
			});
		 </script>
		 
		 <table class="display" id="example">
		<thead>
			<tr>
				<th width="">Group</th>
					<th width="">GrpCode</th>
					<th width="">CodeName</th>
					
			</tr>
		</thead>
	</table>
	
		<table class="display staticBase" id="static">
			<thead>
				<tr>
					<th width="">Group</th>
					<th width="">GrpCode</th>
					<th width="">CodeName</th>
					<th width="">Code</th>
					<th width="">Remark</th>
					<th width="">Management</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${basCdList}" var="basCd">
					<tr>
						<td>${basCd.grpName}</td>
						<td>${basCd.grpCd}</td>
						<td>${basCd.basName}</td>
						<td>${basCd.baseCd}</td>
						<td>${basCd.remark}</td>
						<td><span class="tip"> <a title="Edit"> <img
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



		<!-- clear fix -->
		<div class="clear"></div>

	</div>
	<!-- End content -->
</div>
<!-- End full width -->

