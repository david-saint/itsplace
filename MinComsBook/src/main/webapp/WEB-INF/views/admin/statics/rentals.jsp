<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<c:set var="title" value="대출현황"/>
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
 		$('form').find('select').live('change', function() {
 			datatable.fnStandingRedraw();
 		});
 		$('.datepicker').live('click', function() {
 			datatable.fnStandingRedraw();
 		});
 		$('#badGuys').live('click',function(){
 			if(this.checked){
 				$(this).val(1);
 			}else{
 				$(this).val(0);
 			}
 			datatable.fnStandingRedraw();
 		});
 		$('#dept').change(function(){
 			var deptId = $(this).val();
 			if(deptId!=""){
			  $.getJSON(
		             "/user/getUsersByDept?decorator=exception", 
		             {deptId: deptId},
		             function(data) {
		                  var html = '';
		                  var len = data.length;
		                  html += '<option value="">전체</option>';
		                  if(len>0){
		                	 for(var i=0; i<len; i++){
	 		                       html += '<option value="' + data[i].userId + '">' + data[i].userRname + '</option>';
	 		                   }
	 		                  $('#user').children().remove();
	 		                  $('#user').append(html);
	 		                  $('#user').selectmenu('destroy');
	 		                  $('#user').selectmenu();
		                  }
		                 
		        });
 			}
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
 		         "sProcessing": "<div style='border:0px solid red'>이벤트 조회중 ...</di>"
 		       },
 			"bServerSide": true,		 			
 			"sAjaxSource": "/admin/statics/getRentalList",
 			"fnServerParams": function (aoData, fnCallback) {
 				  aoData.push( { "name": "deptId", "value": $('#dept option:selected').val()} );		 			               
	              aoData.push( { "name": "userId", "value": $('#user option:selected').val()} );		 			               
	              aoData.push( { "name": "isRental", "value": $('input[name=isRental]:checked').val()} );		 			               
	              aoData.push( { "name": "badGuys", "value": $('#badGuys').val()} );		 			               
	              aoData.push( { "name": "startDate", "value": $('#startDate').val()} );		 			               
	              aoData.push( { "name": "endDate", "value": $('#endDate').val()} );		 			               
			},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
							{ "mDataProp": "title" },
 				  			{ "mDataProp": "deptName", "bSortable": false },
 				  			{ "mDataProp": "userRname", "bSortable": false },
 				  			{ "mDataProp": "startDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['startDate'],'yyyy-MM-dd');
 							} },
 							{ "mDataProp": "endDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['endDate'],'yyyy-MM-dd');
 							} },
 							{ "mDataProp": "returnDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['returnDate'],'yyyy-MM-dd');
 							} },
 							
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
 		
 		var solveAction ='<span class="tip"><a class="rental fancy iframe" href="/admin/restriction/solve?id='+id+'" original-title="해제"><img src="/resources/images/icon/gray_18/book.png"></a><span>';
 		
 		return  solveAction; 
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
			<form>
			<div style="position:absolute; ">
				<div class="radiorounded"> 
               		<!-- <input id="isRental1" type="radio" name="isRental"  value="" checked /><label for="isRental1" >전체</label> -->
               		<input id="isRental2" type="radio" name="isRental"  value="1"  checked/><label for="isRental2" >대출중</label>
               		<input id="isRental3" type="radio" name="isRental"  value="0" /><label for="isRental3" >반납완료</label> 
               	</div>                        
			</div>
			<div style="position:absolute;left:500px;z-index:99 ">
               		<input id="badGuys" type="checkbox" name="badGuys"  value="0" class="chkbox"/><label for="badGuys">반납예정일 초과</label>
			</div>
			<div style="position:absolute;right:750px;z-index:999">
				<form:select id="dept" path="deptList" multiple="false">
					<form:option value="" label="전체" />
					<form:options items="${deptList}" itemValue="deptid" itemLabel="deptName" />
				</form:select>                      
			</div>
			<div style="position:absolute;right:580px;z-index:999">
				<select id="user">
					<option value="">전체</option>
				</select>                      
			</div>
			<div style="position:absolute;right:200px;z-index:999">
				대출기간<input type="text" id="startDate" name="startDate" class="datepicker meduim" />  ~                   
				<input type="text" id="endDate" name="endDate" class="datepicker meduim" />                     
			</div>
			</form>
			
				<table class="display" id="datatable">
					<thead>
						<tr>
							<th width="400">제목</th>
							<th>부서</th>
							<th>대출자</th>
							<th width="100">대출일</th>
							<th width="100">반납예정일</th>
							<th width="100">반납일</th>
							<th>Management</th>
						</tr>
					</thead>
				</table>
			</div><!--end datatable -->
		</div><!--end content -->
	</div><!--end widget -->
	


</body>
</html>