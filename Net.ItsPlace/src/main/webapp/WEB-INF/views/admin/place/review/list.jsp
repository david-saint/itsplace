<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('.delete').bind('click', function() {
				$.ajax({
                  url: "/admin/place/review/delete",
                  type:"POST",
                  data:"rid="+$(this).attr('rid'),
                  beforeSend :function(){
	   	 	 			  c.loading("Delete",0);
                  },
                  success: function(response){
                  	if(response.status=="SUCCESS"){
                  		c.showSuccess("삭제되었습니다",1200);
                  	}
                 	
                  },
                  error: function(jqXHR, textStatus, errorThrown){
                 	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
                  },
                  complete:function(){
                	  setTimeout("c.unloading();refresh()",1000); 
                 
                  }
             });//ajax */
			});
 		$('.recovery').bind('click', function() {
			$.ajax({
              url: "/admin/place/review/recovery",
              type:"POST",
              data:"rid="+$(this).attr('rid'),
              beforeSend :function(){
   	 	 			  c.loading("Delete",0);
              },
              success: function(response){
              	if(response.status=="SUCCESS"){
              		c.showSuccess("복구되었습니다",1200);
              	}
             	
              },
              error: function(jqXHR, textStatus, errorThrown){
             	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
              },
              complete:function(){
             	 setTimeout("c.unloading();refresh()",1000); 
             	 
              }
         });//ajax */
		});
 	});//ready
	
 	function refresh(){
 		location.href =  "/admin/place/review/list?fid=${place.fid}";
 	}
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 리뷰 목록 - ${place.fname}  </span>
	</div>
	<div class="content">

			<div class="boxtitle">
			
			</div>
		<div class="section">
			<div style="float:left">
				<a href="/admin/place/review/add?fid=${place.fid}" class="uibutton icon large add ">리뷷등록</a>
			</div>			
		</div>
			<div class="setion">
			
				<table class="display staticBase" id="static">
				<thead>
					<tr>
						<th>리뷰제목</th>
						<th>리뷰내용</th>
						<th>사이트</th>
						<th>이미지</th>					
						<th>삭제여부</th>					
						<th>관리</th>
					</tr>
				</thead>
				 <tbody>
					<c:forEach items="${placeReviewList}" var="placeReview">
						<tr>
							<td>${placeReview.title}</td>
							<td>${placeReview.content}</td>
							<td>${placeReview.siteURL}</td>
							<td>${placeReview.filePath}</td>
							<td>${placeReview.isDelete}</td>							
							<td>
								<span class="tip"><a class="edit" href="/admin/place/review/edit?rid=${placeReview.rid}" original-title="리뷰수정"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>
								<span class="tip"><a class="edit" href="/admin/place/review/delete?rid=${placeReview.rid}" original-title="삭제"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>
								<span class="tip"> <a id="1"  rid="${placeReview.rid}" class="delete" > <img src="/resources/admin/images/icon/icon_delete.png"></a></span>
								<span class="tip"> <a id="1"  rid="${placeReview.rid}" class="recovery" > <img src="/resources/admin/images/icon/icon_delete.png"></a></span>
							</td>
						</tr>
					</c:forEach>
				</tbody> 
				</table>
			</div>
	</div>
</div>


