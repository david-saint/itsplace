<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<c:set var="title" value="도서관리"/>
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
 			"sAjaxSource": "/admin/book/findBookList",
 			//"fnServerParams": function (aoData, fnCallback) {
	        //      aoData.push( { "name": "fid", "value":  $('#places').val()} );		 			               
			//},
 			"sAjaxDataProp": "rows",
 			"aoColumns": [
							{ "mDataProp": "thumbnail", "bSortable": false, "fnRender"  :function ( oObj ) {
									
									return "<img src='"+oObj.aData['thumbnail']+ "' style=\"width:50px;\" />";
							}},
							{ "mDataProp": "bookCategory.bookCategorySub.bookCategoryRoot.name" },
							{ "mDataProp": "bookCategory.bookCategorySub.name" },
							{ "mDataProp": "bookCategory.name" },
							{ "mDataProp": "title" },
 				  			{ "mDataProp": "authors" },
 				  			{ "mDataProp": "count", "bSortable": false },
 				  			{ "mDataProp": "isDeleted", "bSortable": false, "fnRender" :function ( oObj ) {
 								return oObj.aData['isDeleted'] == "0" ? "등록됨" : "삭제됨";
 							} },
 				  			{ "mDataProp": "publishedDate" ,"bSortable": false},
 				  			
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
					//'height':400,
					//'width':700,
					//'centerOnScroll':true
					//'title':'사용자 정보 수정'

				});
 			},	 			
 			"aaSorting": [[ 3, "desc" ]]
 		});//datatable
 		
 	
 		
 		 		
 		$(".dataTables_length select").addClass("small");
 		
 		
 		
 	});//ready
	function make_actions(oObj) {
		var isbn = oObj.aData['isbn'];
 		
 		var editAction = '<span class="tip"><a class="edit iframe" href="/admin/book/edit?isbn='+isbn+'" original-title="수정"><img src="/resources/images/icon/gray_18/pencil.png"></a><span>';
 		//var rentalAction ='<span class="tip"><a class="rental fancy iframe" href="/book/rental?isbn='+isbn+'" isbn="'+isbn+'" original-title="대출"><img src="/resources/images/icon/gray_18/book.png"></a><span>';
 		
 		return  editAction + "&nbsp;";
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
			<span><span class="ico gray spreadsheet"></span>${title}</span>
		</div>
		<div class="content">			
			<div class="tableName">
			<span style="position:absolute"><a href="/admin/book/add" class="uibutton icon large add ">도서등록</a></span>
				<table class="display" id="datatable">
					<thead>
						<tr>
							<th width="100">이미지</th>
							<th width="100">대분류</th>
							<th width="100">중분류</th>
							<th width="100">소분류</th>
							<th width="300">제목</th>
							<th>저자</th>
							<th width="50">수량</th>
							<th width="60">사용여부</th>
							<th width="70">출판일</th>
							<th>Management</th>
						</tr>
					</thead>
				</table>
			</div><!--end datatable -->
		</div><!--end content -->
	</div><!--end widget -->
	


</body>
</html>