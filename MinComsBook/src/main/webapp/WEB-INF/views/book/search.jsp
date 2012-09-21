<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
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
 		$('#bookCategoryRoot').change(function(){
			  $.getJSON(
		             "/book/getBookCategory?decorator=exception", 
		             {rootid: $('#bookCategoryRoot').val()},
		             function(data) {
		                  var html = '';
		                  var len = data.length;
		                  html += '<option value="">전체</option>';
		                  for(var i=0; i<len; i++){
		                       html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
		                   }
		                  $('#bookCategory').children().remove();
		                  $('#bookCategory').append(html);
		                  $('#bookCategory').selectmenu('destroy');
		                  $('#bookCategory').selectmenu();
		             } );
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
 		         "sProcessing": "<div style='border:0px solid red'>이벤트 조회중 ...</di>"
 		       },
 			"bServerSide": true,		 			
 			"sAjaxSource": "/book/getReservationGroupByBooks",
 			"fnServerParams": function (aoData, fnCallback) {
	              aoData.push( { "name": "bookCategoryRoot", "value": $('#bookCategoryRoot option:selected').val()} );		 			               
	              aoData.push( { "name": "bookCategory", "value": $('#bookCategory option:selected').val()} );		 			               
			},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
							{ "mDataProp": "thumbnail", "bSortable": false, "fnRender"  :function ( oObj ) {
									
									return "<img src='"+oObj.aData['thumbnail']+ "' style=\"width:50px;\" />";
							}},
							{ "mDataProp": "bookCategoryRoot" },
							{ "mDataProp": "bookCategory" },
							{ "mDataProp": "title" },
 				  			{ "mDataProp": "authors" },
 				  			{ "mDataProp": "count", "bSortable": false },
 				  			{ "mDataProp": "publishedDate" ,"bSortable": false},
 				  			{ "mDataProp": "reservationCount", "bSortable": false, "fnRender"  :function ( oObj ) {
								var count = eval(oObj.aData['count']);
								var total = eval(oObj.aData['reservationCount']) + eval(oObj.aData['rentalCount']);
								if(count>total){
									return "대출가능";
								}else{
									return "대출불가";
								}
								
						    }},
 				  			{ "mDataProp": "rentalCount" ,"bSortable": false},
 				  			
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
 					var url = "/book/reservation";
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
 			"aaSorting": [[ 0, "desc" ]]
 		});//datatable
 		
 	 
 		
 		 		
 		$(".dataTables_length select").addClass("small");
 		
 		
 		
 	});//ready
	function make_actions(oObj) {
		var isbn = oObj.aData['isbn'];
 		
 		//var editAction = '<span class="tip"><a class="edit iframe" href="/book/edit?isbn='+isbn+'" original-title="수정"><img src="/resources/images/icon/gray_18/pencil.png"></a><span>';
 		var rentalAction ='<span class="tip"><a class="rental fancy iframe" href="/book/rental?isbn='+isbn+'" isbn="'+isbn+'" original-title="대출"><img src="/resources/images/icon/gray_18/book.png"></a><span>';
 		var reservationAction ='<span class="tip"><a class="reservation"  isbn="'+isbn+'" original-title="예약"><img src="/resources/images/icon/gray_18/bookmark.png"></a><span>';
 		
 		return  rentalAction + "&nbsp;"+ reservationAction; 
 	}
 	function bindAction(){
 		$('.rental').bind('click', function() {
 			
 		
 			$(this).append(popup);
			/* $.ajax({
            	url: "/book/rental",
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
			<span class="ico gray home"></span><span>${title}</span>
		</div>
		<div class="content">			
			<div class="tableName">
			<form>
				<div style="position:absolute;z-index:100">
					<form:select id="bookCategoryRoot" path="categoryRootList" multiple="false">
						<option value="">전체</option>
						<form:options items="${categoryRootList}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>	
				<div style="position:absolute;left:430px;z-index:100">
				 	<select id="bookCategory" name="bookCategory.id">
				 		<option value="">하위분류</option>
				 	</select>
				</div>	
			</form>	 		
				<table class="display" id="datatable">
					<thead>
						<tr>
							<th></th>
							<th>분류</th>
							<th>카테고리</th>
							<th>제목</th>
							<th>저자</th>
							<th>수량</th>
							<th>출판일</th>
							<th>예약</th>
							<th>대여</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</div><!--end datatable -->
		</div><!--end content -->
	</div><!--end widget -->
	


</body>
</html>