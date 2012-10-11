<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<c:set var="title" value="대출정지"/>
<html>
<head>
    <title>도서대출 정지 유저목록</title>
<script type="text/javascript">
	var datatable;
	var arrayData;
 	$(document).ready(function(){
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 		$('input[type=radio]').live('change', function() {
 			datatable.fnStandingRedraw();
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
 			"sAjaxSource": "/admin/restriction/getRestrictionUserList",
 			"fnServerParams": function (aoData, fnCallback) {
	              aoData.push( 
	            		  { "name": "isSolved", "value": $('input[name=isSolved]:checked').val()}
	              );		 			               	            	       
			},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
							{ "mDataProp": "id" },
 				  			{ "mDataProp": "userRname", "bSortable": false },
 				  			{ "mDataProp": "reason", "bSortable": false },
 				  			{ "mDataProp": "regDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['regDate'],'');
 							} },
 							{ "mDataProp": "solveDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['solveDate'],'');
 							} },
 							{ "mDataProp": "solveReason", "bSortable": false },
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
				});
 				
 			},	
 			"fnAlert": function () { 				
 				alert("d");
 			},	
 			"aaSorting": [[ 0, "desc" ]]
 		});//datatable
 		 	
 		
 		$(".dataTables_length select").addClass("small");
 	});//ready
	function make_actions(oObj) {
		var id = oObj.aData['id'];  
		var userId = oObj.aData['userId'];  
 		var solveAction ='<span class="tip"><a class="rental fancy iframe" href="/admin/restriction/solve?id='+id+'" original-title="해제"><img src="/resources/images/icon/gray_18/book.png"></a><span>';
 		var historyAction ='<span class="tip"><a class="rental fancy iframe" href="/admin/restriction/history?userId='+userId+'" original-title="대출정지 이력"><img src="/resources/images/icon/gray_18/hourglass.png"></a><span>';
 		
 		return  solveAction + "&nbsp" + historyAction; 
 	}
 	
 
	
</script>
</head>
<body>
	<div class="widget">
		<div class="header">
			<span><span class="ico gray spreadsheet"></span>${title}</span>
		</div>
		<div class="content">			
			<div class="tableName">
			<span style="position:absolute"><a href="/admin/restriction/add" class="uibutton icon large add ">대출 정지 등록</a></span>
			
			<div style="position:absolute;right:250px">
				<div class="radiorounded"> 
               		<input id="isCompleted2" type="radio" name="isSolved"  value="0" checked /><label for="isCompleted2" title="완료">대출정지</label>
               		<input id="isCompleted1" type="radio" name="isSolved"  value="1" /><label for="isCompleted1" title="완료">정지해제</label>
               	</div>                        
			</div>
		
			
				<table class="display" id="datatable">
					<thead>
						<tr>
							<th>ID</th>
							<th>이름</th>
							<th>사유</th>
							<th>정지일자</th>
							<th>해제일</th>
							<th>해제사유</th>
							<th>Management</th>
						</tr>
					</thead>
				</table>
			</div><!--end datatable -->
		</div><!--end content -->
	</div><!--end widget -->
	


</body>
</html>