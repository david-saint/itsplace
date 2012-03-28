<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


 <script type="text/javascript">
 //캐차테마변경
 var RecaptchaOptions = {
    theme : 'white'
 };
 </script>

	
<h1>가맹점 가입</h1>
 
<div> 
	<form:form id="newFranchiser"  action="newFranchiser" commandName="franchiserMember" method="post">
	<form:errors path="*" cssClass="errorblock" element="div" />
	
	<div class="field">
		
		<form:radiobutton id="ftype1" path="ftype" value="1"/><label for="ftype1">개인(자영업)</label>		
		<form:radiobutton id="ftype2" path="ftype" value="0"/><label for="ftype2">프랜차이즈</label>
		<p style="clear: both">
	</div>
	<div class="field">
		<form:input id="name" path="name" cssClass="validate[required,minSize[2]]"   placeholder="담당자" />
		<form:errors path="name" cssClass="error" />
	</div>
	<div class="field">
		<form:input id="fname" path="fname" cssClass="validate[required,minSize[2]]"  placeholder="업체명" />
		<form:errors path="fname" cssClass="error" />
	</div>
	<div class="field">
		<form:input id="mobile" path="mobile" cssClass="validate[required,minSize[2]]" placeholder="휴대폰"  />
		<form:errors path="mobile" cssClass="error" />
	</div>	
	<div class="field">
		<form:input id="phone1" path="phone1" placeholder="업체전화번호"  />
		<form:errors path="phone1" cssClass="error" />
	</div>
	
	<div class="field">
			<input type="text" id="hdongname" class="validate[minSize[3]]"
				placeholder="주소" />
			<button type="button" id="addressSearch" class="large awesome"
				onclick="json_getJsonAddressList2()" style="height: 38px;">검색&raquo;</button>
			
			<div id="addressList" style="display:none;border:0px solid blue;padding:0;overflow: auto; width:400px;height:300px;">
				<ul id="addressList-ul" style="border:0px solid green;padding:0;margin:0;">
				</ul>
				
				<div id="ajax_load_indicator"
					style="border:0px solid red;text-align: center; display: none;">
					<img src="<c:url value="/resources/common/1.0/loadinfo.net.gif" />" />
				</div>
			</div>

	</div>
	
	<div class="field">
		<input type="text" readonly="readonly" id="address" name="address"  value="${franchiserMember.address}" placeholder="집주소"  />		
		<input type="hidden" id="nldno" name="nldno" value="${franchiserMember.nldno}" />		
		<input type="hidden" id="latitude" name="latitude" value="${franchiserMember.latitude}"/>		
		<input type="hidden" id="longitude" name="longitude" value="${franchiserMember.longitude}"/>		
	</div>
	
	
	
	<div class="field">
		<form:textarea id="remark" path="remark" placeholder="신청내용"  />
		<form:errors path="remark" cssClass="error" />
	</div>
	
	<div>
		<button type="submit" class="large orange awesome">가입하기 &raquo;</button>
	</div>
	
</form:form>
</div>





