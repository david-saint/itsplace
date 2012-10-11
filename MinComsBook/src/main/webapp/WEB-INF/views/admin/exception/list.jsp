<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<c:set var="title" value="예외"/>
<html>
<head> 
    <title>도서목록</title>
<script type="text/javascript">
	var datatable;
	var arrayData;
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
 		         "sProcessing": "<div style='border:0px solid red'>이벤트 조회중 ...</di>"
 		       },
 			"bServerSide": true,		 			
 			"sAjaxSource": "/admin/exception/getExceptionList",
 			"fnServerParams": function (aoData, fnCallback) {
	              aoData.push( 
	            		  { "name": "isDeleted", "value": $('input[name=isDeleted]:checked').val()},
	            		  { "name": "isCompleted", "value":  $('input[name=isCompleted]:checked').val()} 
	              );		 			               
	            
	          /*     var fields = $("form").serializeArray();
 				 $.each(fields, function(){
 			          var jsonStr = JSON.stringify(this);
 			          console.log(jsonStr);
 			          aoData.push(jsonStr);
 			     });  */
			},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
							{ "mDataProp": "id" },
 				  			{ "mDataProp": "message" },
 				  			{ "mDataProp": "isDeleted", "bSortable": false },
 				  			{ "mDataProp": "isCompleted", "bSortable": false, "fnRender" :function ( oObj ) {
 								return oObj.aData['isCompleted'] == "0" ? "완료" : "미승인";
 							} },
 							{ "mDataProp": "regDate", "fnRender"  :function ( oObj ) {
 								return c.render_date(oObj.aData['regDate'],'');
 							} },
 				  			
 				  			{ "sDefaultContent": "", "fnRender" : make_actions, "bSortable": false, "bSearchable": false },
 				  		],
	  		"fnInitComplete":function(){
 				//$('.tip a ').tipsy({trigger: 'manual'});
 				//$('.tip a ').tipsy("hide");
 			},
 			"fnDrawCallback": function () { 				
 				bindAction();
 			},	
 			"fnAlert": function () { 				
 				alert("d");
 			},	
 			"aaSorting": [[ 1, "desc" ]]
 		});//datatable
 		
 		$('.fancy').fancybox({//autodimensions false 후 width , height 가느
			'autoDimensions':false,
			'scrolling':'auto',
			'autoScale':false,
			'height':500,
		});
 		
 		
 		 		
 		$(".dataTables_length select").addClass("small");
 		
 		$('input[type=radio]').live('change', function() {
 			 
 			datatable.fnStandingRedraw();
 			//c.log($("form").serializeArray());
 		});
 		
 		
 	});//ready
	function make_actions(oObj) {
		var isbn = oObj.aData['isbn'];
 		
 		var editAction = '<span class="tip"><a class="edit iframe" href="/book/edit?isbn='+isbn+'" original-title="수정"><img src="/resources/images/icon/color_18/add.png"></a><span>';
 		var rentalAction ='<span class="tip"><a class="rental" isbn="'+isbn+'" original-title="대출"><img src="/resources/images/icon/color_18/book.png"></a><span>';
 		
 		return  editAction + "&nbsp;"+ rentalAction; 
 	}
 	function bindAction(){
 		$('.rental').bind('click', function() {
 			
 			var p = $(this).position();
 			console.log("p:"+p.left);
 			console.log("p:"+p.top);
 			var popup = '<div style="position:absolute;width:100px;height:100px;border:1px solid red;top:'+p.left+';left:'+p.left+' ">';
 			    popup += '<input type="text" style="width:40px;"/>';
 			    popup +='</div>';
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
			<span><span class="ico gray spreadsheet"></span>${title}</span>
		</div>
		<div class="content">			
			<div class="tableName">
			<span style="position:absolute"><a href="/book/add" class="uibutton icon large add ">블라브라</a></span>
			<form>
				<div style="position:absolute;right:350px">
				<div class="radiorounded"> 
               		<input id="isCompleted2" type="radio" name="isCompleted"  value="0" checked /><label for="isCompleted2" title="완료">미결</label>
               		<input id="isCompleted1" type="radio" name="isCompleted"  value="1" /><label for="isCompleted1" title="완료">완료</label>
               	</div>                        
				</div>
			</form>
			<div style="position:absolute; ;right:200px">
				<div class="radiorounded"> 
               		<input id="isDeleted1" type="radio" name="isDeleted"  value="0" checked /><label for="isDeleted1" >진행</label>
               		<input id="isDeleted2" type="radio" name="isDeleted"  value="1" /><label for="isDeleted2" >삭제</label>
               	</div>                        
			</div>
			
				<table class="display" id="datatable">
					<thead>
						<tr>
							<th>ID</th>
							<th>Message</th>
							<th>삭제여부</th>
							<th>처리여부</th>
							<th>등록일</th>
							<th>Management</th>
						</tr>
					</thead>
				</table>
			</div><!--end datatable -->
		</div><!--end content -->
	</div><!--end widget -->
	


</body>
</html>