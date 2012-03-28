<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<script>
	function uploadImage(obj){
		
		var fileName = $(obj).val();
		var path = 'file://'+ fileName;
		//path = escape(path);
		//path = path.replace(/%5C/g, "/");
		//path = path.replace(/%3A/g, ":");
		$('#preview').attr('src',path);
		//alert(fileName);
		var IMG_FORMAT = "\\.(bmp|gif|jpg|jpeg|png)$";
		if((new RegExp(IMG_FORMAT, "i")).test(fileName)){
			
			
			
		}else
		{
		    alert("이미지 파일만 첨부하실 수 있습니다.");
		}
		
	}
</script>
	


	
<div id="wrapper">
	<header>
		<div class="field">						        	
        	<h1>가맹점 이미지 업로드</h1>		
		</div>	
	</header>
	
	<div id="main">
		<div id="content">
			<div style="border:1px solid blue;"> 
				<form:form id="newFranchiser"  action="../franchiserImageUpload" commandName="franchiserImage" method="post"  enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div" />
				
				
				<div class="field">		
					<label for="name">fid</label>
					<form:input id="fid" path="fid" cssClass="validate[required,minSize[2]]"   placeholder="fid" />
					<form:errors path="fid" cssClass="error" />
				</div>
				<div class="field">
					<label for="dispseq">순서</label>
					<form:input id="dispseq" path="dispseq"   placeholder="순서" value="1" />
					<form:errors path="dispseq" cssClass="error" />
				</div>
				
				
				<div class="field">
					<form:input path="fileData" type="file"  onchange="uploadImage(this)" />
					<form:errors path="fileData" cssClass="error" />
				</div>
				
			
				
				
				<div>
					<button type="submit" class="large orange awesome">업로드 &raquo;</button>
				</div>
				
			</form:form>
			</div>
			<div>
				<c:forEach var="franchiserImage" items="${franchiserImageList}"  >
					<img id="preview" width="100px" height="100px" src="<c:url value="/resources/images/${franchiserImage.fileName}" />" />
				</c:forEach>
			</div>
		</div>
	</div>
	
	<footer>Footer</footer>
</div>





