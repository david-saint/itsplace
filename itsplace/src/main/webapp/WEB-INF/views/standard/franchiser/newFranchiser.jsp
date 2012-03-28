<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function(){

	//$('#phone1').mask("999-999?9-9999");
	//$('#mobile').mask("999-999?9-9999");
	
	$('#addressInput').bind("keydown", function(e) {
		  //입력 허용 키
		  if( e.keyCode == 13){
			  json_getJsonAddressList2(1);
		  }
	});
	
	
				  
});


	function uploadImage(obj){
		
		var fileName = $(obj).val();
		var path = 'file://'+ fileName;
		//path = escape(path);
		//path = path.replace(/%5C/g, "/");
		//path = path.replace(/%3A/g, ":");
		$('#preview').attr('src',path);
		//alert(fileName);
		var IMG_FORMAT = "\\.(gif|jpg|jpeg|png)$";
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
        	<h1>가맹점 가입신청</h1>		
		</div>	
	</header>
	
	<div id="main">
		<div id="content">
			<div style="border:0px solid blue;"> 
				<form:form id="newFranchiser"  action="newFranchiser" commandName="franchiserMember" method="post"  enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div" />
				
				<div style="margin-left:140px;margin-bottom:10px;">		
					<form:radiobutton id="ftype1" path="ftype" value="1"/><label for="ftype1">개인(자영업)</label>		
					<form:radiobutton id="ftype2" path="ftype" value="0"/><label for="ftype2">프랜차이즈</label>
					<p style="clear: both">
				</div>
				<div class="field">		
					<label for="name">담당자</label>
					<form:input id="name" path="name" cssClass="text-input validate[required,minSize[2]]"   placeholder="담당자" />
					<form:errors path="name" cssClass="error" />
				</div>
				<div class="field">
					<label for="name">가맹점명</label>
					<form:input id="fname" path="fname" cssClass="validate[required,minSize[2]]"  placeholder="가맹점명" />
					<form:errors path="fname" cssClass="error" />
				</div>
				<div class="field">					
					<label for="mobile">휴대폰</label>
					<form:input id="mobile" path="mobile" cssClass="validate[required,minSize[2]]" placeholder="예)053-632-0976"  />
					<form:errors path="mobile" cssClass="error" />
				</div>	
				<div class="field">
					<label for="phone1">전화번호</label>
					<form:input id="phone1" path="phone1" placeholder="예)053-632-0976"  />
					<form:errors path="phone1" cssClass="error" />
				</div>
				<div class="field">
					<label for="remark">남기실말</label>
					<form:textarea id="remark" path="remark" placeholder="남기실 말"  />
					<form:errors path="remark" cssClass="error" />
				</div>
				
				<div class="field">
					<form:input path="fileData" type="file"  onchange="uploadImage(this)" />
					<form:errors path="fileData" cssClass="error" />
				</div>
				<!-- 
				<div class="field2">
					<input type="radio" id="jibun" name="searchType" checked="checked"  value="1"/><label for="jibun">지번주소(동 번지)</label>		
					<input type="radio" id="doro" name="searchType" value="0"/><label for="doro">도로명주소</label>
					
										
				</div>
				 -->
				
				<div class="field">
					<input type="text" style="width:420px;" readonly="readonly" id="address" name="fullAddress"  value="${franchiserMember.fullAddress}" placeholder="집주소"  />		
					<input  type="hidden" id="nldno" name="nldno"  placeholder="nldno" value="${franchiserMember.nldno}" />		
					<input type="hidden" id="latitude" name="latitude"  placeholder="latitude" value="${franchiserMember.latitude}"/>		
					<input  type="hidden"id="longitude" name="longitude"  placeholder="longitude" value="${franchiserMember.longitude}"/>		
				</div>
				
				
				
				
				<div style="margin-left:50px;margin-bottom:10px;">		
					<form:radiobutton id="stype1" path="stype" value="N"/><label for="stype1">무료서비스</label>		
					<form:radiobutton id="stype2" path="stype" value="S"/><label for="stype2">스탬프서비스</label>
					<form:radiobutton id="stype3" path="stype" value="P"/><label for="stype3">프리미어서비스</label>
					<p style="clear: both">
				</div>
				<div>
					<button type="submit" class="large  awesome">신청하기 &raquo;</button>
				</div>
				
			</form:form>
			</div>
			<div id="addressAjax">
					<input type="text" id="addressInput" class="validate[minSize[3]]" placeholder="주소검색" />
					<button type="submit" id="addressSearch" class="large awesome" onclick="json_getJsonAddressList2(1)" style="height: 38px;">검색&raquo;</button>

				<div id="addressList" class="list">
					<ul id="addressList-ul" style="border:0px solid green;padding:0;margin:0;"></ul>
					<div id="ajax_load_indicator"
						style="border:0px solid red;text-align: center; display: none;">
						<img src="<c:url value="/resources/common/1.0/loadinfo.net.gif" />" />
					</div>
					<div id="paging"></div>
				</div>
						
			</div>
		</div><!-- contents -->
	</div>
	
	<footer>Footer</footer>
</div>





