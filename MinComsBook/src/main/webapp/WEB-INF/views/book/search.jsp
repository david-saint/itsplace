<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<c:set var="title" value="도서목록"/>


<html>
<head>
    <title>${title}</title>

<script type="text/javascript">
	var datatable; 
 	$(document).ready(function(){
 	
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 		$('input[type=radio]').live('change', function() {
 			datatable.fnStandingRedraw();
 		});
 		
 		$('#bookCategoryRoot').change(function(){
 			var rootid = $('#bookCategoryRoot').val();
 			if(rootid!=""){
			  $.getJSON(
		             "${context}/book/getBookCategorySub?decorator=exception",  
		             {root_id: $('#bookCategoryRoot').val()},
		             function(data) {
		                  var html = '';
		                  var len = data.length;
		                  html += '<option value="">전체</option>';
		                  if(len>0){
		                	 for(var i=0; i<len; i++){
	 		                       html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
	 		                   }
	 		                  $('#bookCategorySub').children().remove();
	 		                  $('#bookCategorySub').append(html);
	 		                  $('#bookCategorySub').selectmenu('destroy');
	 		                  $('#bookCategorySub').selectmenu();
		                  }
		                 
		        });
 			}
		    datatable.fnStandingRedraw();
		});
		$('#bookCategorySub').change(function(){
			if($('#bookCategorySub').val()!=""){
			  $.getJSON(
		             "${context}/book/getBookCategory?decorator=exception", 
		             {sub_id: $('#bookCategorySub').val()},
		             function(data) {
		                  var html = '';
		                  var len = data.length;
		                  html += '<option value="">전체</option>';
		                  if(len>0){
		                	 for(var i=0; i<len; i++){
	 		                       html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
	 		                   }
	 		                  $('#bookCategory').children().remove();
	 		                  $('#bookCategory').append(html);
	 		                  $('#bookCategory').selectmenu('destroy');
	 		                  $('#bookCategory').selectmenu();
		                  }
		                  
		       });
			}
			datatable.fnStandingRedraw();
		});
	   
	
 		$('#bookCategory').change(function(){
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
 			"sAjaxSource": "${pageContext.request.contextPath}/book/getReservationGroupByBooks",
 			"fnServerParams": function (aoData, fnCallback) {
	              aoData.push( { "name": "bookCategoryRoot", "value": $('#bookCategoryRoot option:selected').val()} );		 			               
	              aoData.push( { "name": "bookCategorySub", "value": $('#bookCategorySub option:selected').val()} );		 			               
	              aoData.push( { "name": "bookCategory", "value": $('#bookCategory option:selected').val()} );		 			               
	              aoData.push( { "name": "isRental", "value": $('input[name=isRental]:checked').val()} );		 			               
			},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
							{ "mDataProp": "thumbnail", "bSortable": false, "fnRender"  :function ( oObj ) {
									
									return "<img src='"+oObj.aData['thumbnail']+ "' style=\"width:50px;\" />";
							}},
							{ "mDataProp": "bookCategoryRoot" },
							{ "mDataProp": "bookCategorySub" },
							{ "mDataProp": "bookCategory" },
							{ "mDataProp": "title", sClass: "dtLeft" },
 				  			{ "mDataProp": "authors", sClass: "dtLeft" },
 				  			{ "mDataProp": "count", "bSortable": false },
 				  			{ "mDataProp": "publishedDate" ,"bSortable": false},
 				  			{ "mDataProp": "reservationCount" ,"bSortable": false},
 				  			{ "mDataProp": "rentalCount" ,"bSortable": false},
 				  			{ "mDataProp": "regDate", "bSortable": false, "fnRender"  :function ( oObj ) {
								var count = eval(oObj.aData['count']);
								var total = eval(oObj.aData['reservationCount']) + eval(oObj.aData['rentalCount']);
								if(count>total){
									return "대출가능";
								}else{
									return "대출불가";
								}
								
						    }},
 				  		
 				  			
 				  			
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
 				
 				$('.reservation').bind('click', function() {
 					c.log($(this).attr('isbn'));
 					var url = "${context}/book/reservation";
 		 			url += "?decorator=exception";
 					$.ajax({
 	                     url: url,
 	                     type:"POST",                                
 	                     data:"isbn="+$(this).attr('isbn'),
 	                     success: function(response){
 	                       if(response.status=="SUCCESS"){
 	                    	   c.showSuccess(response.result,1000);
 	                    	   datatable.fnStandingRedraw();
 	                       }else{                    	  
 	                    	  c.showInfo(response.result,1000);
 	                       }
 	                     }
 	                });//ajax
 				});
 			},	 			
 			"aaSorting": [[ 6, "desc" ]]
 		});//datatable
 		
 	 
 		
 		 		
 		$(".dataTables_length select").addClass("small");
 		
 		
 		
 	});//ready
	function make_actions(oObj) {
		var isbn = oObj.aData['isbn'];
 		
 		var infoAction = '<span class="tip"><a class="edit fancy iframe" href="${context}/book/info?isbn='+isbn+'" original-title="반납 및 예약자"><img src="${context}/resources/images/icon/gray_18/clipboard.png"></a><span>';
 		var rentalAction ='<span class="tip"><a class="rental fancy iframe" href="${context}/book/rental?isbn='+isbn+'" isbn="'+isbn+'" original-title="대출"><img src="${context}/resources/images/icon/gray_18/book.png"></a><span>';
 		var reservationAction ='<span class="tip"><a class="reservation"  isbn="'+isbn+'" original-title="예약"><img src="${pageContext.request.contextPath}/resources/images/icon/gray_18/key.png"></a><span>';
 		
 		return  rentalAction + "&nbsp;&nbsp;"+ reservationAction + "&nbsp;&nbsp;"+ infoAction; 
 	}
 	function bindAction(){
 		$('.rental').bind('click', function() {
 			
 		
 			$(this).append(popup);
			/* $.ajax({
            	url: "${context}/book/rental",
                type:"POST",
                data:"fid="+$(this).attr('fid'),
                beforeSend :function(){
                	  xhr.setRequestHeader("Accept", "application/json");
	   	 	 		 // c.loading("delete",0);
                },
                success: function(response){
                	if(response.status=="SUCCESS"){
                  	//	c.showSuccess("승인 취소되었습니",1000);
                    }
                 	
                },
                error: function(jqXHR, textStatus, errorThrown){
                 //	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
                },
                complete:function(){
                 	 setTimeout("c.unloading()",500); 
                 	// datatable.fnStandingRedraw();
                }
             });//ajax */ 
		});
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
				<div style="position:absolute;z-index:100">
					<form:select id="bookCategoryRoot" path="categoryRootList" multiple="false">
						<option value="">전체</option>
						<form:options items="${categoryRootList}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>	
				<div style="position:absolute;left:430px;z-index:100">
				 	<select id="bookCategorySub" name="bookCategory.bookCategorySub.id">
				 		<option value="">중분류</option>
				 	</select>
				</div>	
				<div style="position:absolute;left:600px;z-index:100">
				 	<select id="bookCategory" name="bookCategory.id">
				 		<option value="">소분류</option>
				 	</select>
				</div>	
				<div style="position:absolute; ;right:200px">
				<div class="radiorounded"> 
               		<input id="isDeleted1" type="radio" name="isRental"  value="0" checked /><label for="isDeleted1" >전체</label>
               		<input id="isDeleted2" type="radio" name="isRental"  value="1" /><label for="isDeleted2" >대출가능</label>
               	</div>                        
			</div>
				<table class="display" id="datatable">
					<thead> 
						<tr>
							<th width="50"></th>
							<th width="80">대분류</th>
							<th width="80">중분류</th>
							<th width="100">소분류</th>
							<th width="500">제목</th>
							<th>저자</th>
							<th width="30">수량</th>
							<th>출판일</th>
							<th width="30">예약</th>
							<th width="30">대여</th>
							<th>대출여부</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</div><!--end datatable -->
		</div><!--end content -->
	</div><!--end widget -->
	


</body>
</html>