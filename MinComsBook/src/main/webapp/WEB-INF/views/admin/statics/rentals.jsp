<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<c:set var="title" value="대출현황"/>
<html>
<head>
    <title>${title}</title>
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
 		$('.datepicker').live('change', function() {
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
		             "${context}/user/getUsersByDept?decorator=exception", 
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
 		$('#btnRestriction').click(function(){
 			var userNames = "";
 			var table="datatable";
			var id= this.id;
			$( "table#"+table+" tbody tr td:first-child input:checkbox").each(function() {	  					
				if (this.checked){
					userNames = userNames + $(this).attr('username') + ",";
				}
			});	
			
			var href="${context}/admin/restriction/add?decorator=fancy&userName=" +userNames; 
 			//$(this).attr('href',href);
 			
 			$.fancybox({
				'type':'iframe',
				'href':href,
				'showCloseButton' :true,
				'autoDimensions':false,
				'scrolling':'auto',
				'autoScale':false,
				
			});
 		
 		});
 	    datatable = $('#datatable').dataTable( {
 			"sDom": 'fCl<"clear">rtip', 
 			"bFilter": true, 
 			"bPaginate": true,
 			"bLengthChange": true, 
 			"sPaginationType": "full_numbers",
 			"bProcessing": true,
 			"oLanguage": {
 		         "sProcessing": "<div style='border:0px solid red'>조회중 ...</di>"
 		       },
 			"bServerSide": true,		 			
 			"sAjaxSource": "${context}/admin/statics/getRentalList",
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
							{ "mDataProp": "id" , "bSortable": false, "fnRender"  :function ( oObj ) {
 								return "<div class='checksquared'><input type='checkbox' id='"+oObj.aData['id']+"' username='"+oObj.aData['userName']+"' class='chk_restriction restriction' /><label for='"+oObj.aData['id']+"'></label></div>";
 								
 							} },
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
	  			$('.restriction').live('click',function(){
	  				 var table=$(this).parents('table').attr('id');
	  				 var checkedStatus ;
	  				 var id= this.id;
	  				 $( "table#"+table+" tbody tr td:first-child input:checkbox").each(function() {	  					
	  						if (this.checked) {
	  							checkedStatus = this.checked;
	  						}
	  				});	 
	  	 			if(checkedStatus){
	  	 				$('#btnRestriction').show();
	  	 			}else{
	  	 				$('#btnRestriction').hide();
	  	 			}
	  	 		});
 			},
 			"fnDrawCallback": function () { 		
 				
 				$('.chk_restriction').customInput();
 				$('.fancy').fancybox({//autodimensions false 후 width , height 가느
					'autoDimensions':false,
					'scrolling':'auto',
					'autoScale':false,
				});
 				
 			},	
 			"fnAlert": function () { 				
 				alert("d");
 			},	
 			"aaSorting": [[ 4, "desc" ]]
 		});//datatable
 		 	
 		
 		$(".dataTables_length select").addClass("small");
 	});//ready
	function make_actions(oObj) {
		var userName = oObj.aData['userName'];  
 		
 		var solveAction ='<span class="tip"><a class="rental fancy iframe" href="${context}/admin/restriction/add?decorator=fancy&userName='+userName+'"  original-title="대출정지"><img src="${context}/resources/images/icon/gray_18/book.png" /></a><span>';
 		
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
			<div style="position:absolute;left:450px;z-index:99 ">
            	<a id="btnRestriction" class="uibutton  large  iframe"  style="display:none" rel="1" >대출정지</a>
			</div>
			<div style="position:absolute;left:650px;z-index:99 ">
               		<input id="badGuys" type="checkbox" name="badGuys"  value="0" class="chkbox"/><label for="badGuys">반납예정일 초과</label>
			</div>
			<div style="position:absolute;right:450px;z-index:999">
				<form:select id="dept" path="deptList" multiple="false">
					<form:option value="" label="전체" />
					<form:options items="${deptList}" itemValue="deptid" itemLabel="deptName" />
				</form:select>                      
				<select id="user">
					<option value="">전체</option>
				</select>                      
			</div>
			<div style="position:absolute;right:200px;z-index:999">
				<span></span>
				<input type="text" id="startDate" name="startDate" class="datepicker meduim" />  ~                   
				<input type="text" id="endDate" name="endDate" class="datepicker meduim" />
				   <!-- <a id="btnEdit" class="uibutton loading submit_form large" title="Saving" rel="1" >저장</a> -->                       
			</div>
			</form>
			
				<table class="display" id="datatable">
					<thead>
						<tr>
							<th><div class=""><input type="checkbox"  id="checkAll"   class="checkAll restriction" /><label for="checkAll"></label></div>
							</th>
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