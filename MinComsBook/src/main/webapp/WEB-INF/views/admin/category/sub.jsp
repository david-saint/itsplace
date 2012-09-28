<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="title" value="카테고리 중분류"/>
<html>
  <head>
    <title>{$title}</title>
    
    <script  src="<c:url value="/resources/components/noty/jquery.noty.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/layouts/center.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/layouts/top.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/layouts/topRight.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/themes/default.js" />" type="text/javascript"></script>
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		
			$('#btnSave').click(function(){
				$('form').submit();
	 		});
		    $('#bookCategoryRootList').change(function(){
				document.location.href='/admin/category/sub?rootid='+$(this).val();
			}); 
			$('.delete').click(function(){
				var id = $(this).attr('id');//alert();
				noty({
		 			 modal: true,
		 			 animation: {
		 			    open: {height: 'toggle'},
		 			    close: {height: 'toggle'},
		 			    easing: 'swing',
		 			    speed: 100 // opening & closing animation speed
		 			  },
		 			  layout: 'center',
		 			  text: '삭제 하시겠습니까?', 
		 			  buttons: [
		 			    {addClass: 'uibutton normal', text: '확 인', onClick: function($noty) {
		 		 			var url = "/admin/category/sub/delete?id=";
		 		 			document.location.href=url+id;
		 			      }
		 			    },
		 			    {addClass: 'uibutton normal', text: '취 소', onClick: function($noty) {
		 			        $noty.close();
		 			      }
		 			    }
		 			  ]
		 			});
	 		});
	 	});	 	
	</script>
</head>
<body>	
<div class="widget">
	<div class="header">
		<span><span class="ico gray gear"></span> ${title} </span>
	</div>	
	<div class="content">
		<form:form  action="/admin/category/sub/save" commandName="bookCategorySub" method="post">
			<form:hidden path="id"  cssClass="small"/>
           <div class="boxtitle">
	           <c:set var="errors"><form:errors path="*" /></c:set>
	           <c:if test="${not empty errors}">
	           <span class="ico color lightbulb"></span><span>Exception:</span>
	        	    ${errors }
	           </c:if>
           </div>
           <div class="section" >
            <label> 대분류 <small></small></label> 
					<form:select  id="bookCategoryRoot" path="bookCategoryRoot.id" multiple="false">
						<form:options items="${bookCategoryRootList}" itemValue="id" itemLabel="name" />
					</form:select>
					
		   </div>
           <div class="section" >
               <label> 카테고리명 <small></small></label>   
               <div>
               		<form:input path="name" cssClass="small"/><form:errors path="name" cssClass="error" />
               		<span class="f_help"></span>
               </div>
          </div>  
          <div class="section" >
               <label> 순서 <small></small></label>   
               <div>
               		<form:input path="dispSeq" cssClass="small"/><form:errors path="dispSeq" cssClass="error" />
               		<span class="f_help">기호없이 숫자만 입력하세요</span>
               </div>
          </div>  
           <div class="section" > 
               <label>삭제여부<small></small></label>   
               <div> 
               		<form:checkbox path="isDeleted" cssClass="on_off_checkbox" /><form:errors path="isDeleted" cssClass="error " />
               		<span class="f_help"></span>
               </div>                                  
          </div>
           
           <div class="section last right">
               <div>
                	<a id="btnSave" class="uibutton loading submit_form large" title="Saving" rel="1" >저장</a>
                	<a class="uibutton  clear_form large">추가</a>  
               </div>
           </div>
       </form:form>
	</div>
</div>	
<div class="widget">
	<div class="header">
		<span><span class="ico gray spreadsheet"></span> ${title} </span>
	</div>	
	<div class="content">
				<%-- <div >
					<form:select id="bookCategoryRootList" path="bookCategoryRootList" multiple="false">
						<option value="0">전체</option>
						<form:options items="${bookCategoryRootList}" itemValue="id" itemLabel="name" />
					</form:select>
					<script type="text/javascript">$('#bookCategoryRootList').val(${rootid});</script>
				</div> --%>
          <table class="display">
          	<thead>
				<tr>
					<th width="100">아이디</th>
					<th width="100">대분류</th>
					<th width="100">카테고리명</th>
					<th width="100">사용여부</th>
					<th width="100">순서</th>
					<th width="100">Actions</th>
				</tr>	
			<tbody>
				<c:forEach items="${bookCategorySubList}" var="bookCategorySub">
				<tr>
					<td>${bookCategorySub.id}</td>
					<td>${bookCategorySub.bookCategoryRoot.name}</td>
					<td>${bookCategorySub.name}</td>
					<td>${bookCategorySub.isDeleted}</td>
					<td>${bookCategorySub.dispSeq}</td>
					<td><span class="tip"><a class="edit iframe" href="/admin/category/sub?id=${bookCategorySub.id}" original-title="수정"><img src="/resources/images/icon/gray_18/pencil.png"></a><span>
					<span class="tip"><a class="delete iframe" id="${bookCategorySub.id}" original-title="삭제"><img src="/resources/images/icon/gray_18/delete.png"></a><span></td>
				</tr>
				</c:forEach>
			</tbody>	
          </table>       	 
       	
	</div>
</div>	
</body>

