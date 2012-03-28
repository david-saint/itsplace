<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery("#franchiserDetailForm").validationEngine(); 
	

	//$('#phone1').mask("999-999?9-9999");
	//$('#mobile').mask("999-999?*-9999");
		
		$('#addressInput').bind("keydown", function(e) {
			  //입력 허용 키
			  if( e.keyCode == 13){
				  json_getJsonAddressList2(1);
			  }
		});
		
		
		var msg = "카카오링크를 사용하여 메세지를 전달해보세요.";
		var url = "http://link.kakao.com";
		var appid = "m.kakao.com";
		var appver = "2.0";
		var appname = "카카오";
		var link = new com.kakao.talk.KakaoLink(msg, url, appid, appver, appname);
		       $("#kakao").click(function() {// button click event
		    	   alert("");
			        link.execute();
		       
		       });				  
	
});


	function uploadImage(obj){
		
		var fileName = $(obj).val();
		var path = 'file://'+ fileName;
		//path = escape(path);
		//path = path.replace(/%5C/g, "/");
		//path = path.replace(/%3A/g, ":");
		$('#preview').attr('src',path);
		alert(fileName);
		var IMG_FORMAT = "\\.(gif|jpg|jpeg|png)$";
		if((new RegExp(IMG_FORMAT, "i")).test(fileName)){
			
			
			
		}else
		{
		    alert("이미지 파일만 첨부하실 수 있습니다.");
		}
		
	}
	function submit_validation(){
		
		
		var stamptype = $.trim($(":radio[name=stamptype]:checked").val());
		if (stamptype==""){
			alert("스탬프를 선택하세요");
		}
		
	}
</script>
	


	
<div id="wrapper">
	<header>
		<div class="field">						        	
        	<h1>${franchiserMember.fname} 정보수정</h1>		
		</div>	
	</header>
	
	<div id="main">
		<div id="content">
			<div style="border:1px solid blue;"> 
				<div id="storeBox">
					<a class="photo"  href="<c:url value="/place/placeView/${franchiserMember.fid}" />">
				   		<img src="<c:url value="/resources/images/" />${franchiserMember.fileName}" width="150" height="99" alt="프런트이미지">
					</a>
					<!-- <a href="<c:url value="/place/placeView/${franchiserMember.fid}" /> ">
						<h2>${franchiserMember.fname}</h2>
					</a>
					 -->
					<p>${franchiserMember.address.sido} ${franchiserMember.address.gugun} ${franchiserMember.address.doroname} ${franchiserMember.address.bupname}${franchiserMember.address.jimain}-${franchiserMember.address.jisubmain}</p>
			 		<p>${franchiserMember.phone1}</p>				
				</div>
				
				<form:form id="franchiserDetailForm"  action="../franchiserDetail" commandName="franchiserMember" method="post"  enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div" />
				
				<div id="detail" style="clear: both">
					<h3>상세정보 </h3>
					<div style="margin-left:150px;margin-bottom:10px;">		
						<form:radiobutton id="ftype1" path="ftype" value="1"/><label for="ftype1">개인(자영업)</label>		
						<form:radiobutton id="ftype2" path="ftype" value="0"/><label for="ftype2">프랜차이즈</label>
						<p style="clear: both">
					</div>
					<div class="field">		
						<label for="name">담당자</label>
						<form:input id="name" path="name" cssClass="validate[required,minSize[2]]"   placeholder="담당자" />
						<form:errors path="name" cssClass="error" />
					</div>
					<div class="field">
						<label for="name">가맹점명</label>
						<form:input id="fname" path="fname" cssClass="validate[required,minSize[2]]"  placeholder="가맹점명" />
						<form:errors path="fname" cssClass="error" />
					</div>
					<div class="field">					
						<label for="mobile">휴대폰</label>
						<form:input id="mobile" path="mobile" cssClass="validate[required,minSize[2]]" placeholder="예)010-6514-1014"  />
						<form:errors path="mobile" cssClass="error" />
					</div>	
					<div class="field">
						<label for="mobile">대표전화</label>
						<form:input id="phone1" path="phone1" placeholder="예)053-632-0976"  />
						<form:errors path="phone1" cssClass="error" />
					</div>
					<div class="field">
						<label for="mobile">주차정보</label>
						<form:input id="park" path="park" placeholder="예)주차가능"  />
						<form:errors path="park" cssClass="error" />
					</div>
					<div class="field">
						<label for="closedday">휴무일</label>
						<form:input id="closedday" path="closedday" placeholder="예)매주 월요일"  />
						<form:errors path="closedday" cssClass="error" />
					</div>
					<div class="field">
						<label for="openday">영업시간</label>
						<form:input id="openday" path="openday" placeholder="예)09:00 - 18:00"  />
						<form:errors path="openday" cssClass="error" />
					</div>
					<div class="field">
						<label for="website">웹사이트 http://</label>
						<form:input id="website" path="website" placeholder="예)www.itsplace.net"  />
						<form:errors path="website" cssClass="error" />
					</div>
					<div class="field">
						<label for="info">설명</label>
						<form:textarea id="info" path="info" placeholder="예)업소 소개 및 설명"  />
						<form:errors path="info" cssClass="error" />
					</div>
					
					
					<div class="field">
						<form:input path="fileData" type="file"  onchange="uploadImage(this)" />
						<form:errors path="fileData" cssClass="error" />
					</div>
					
					
					
							
					<div class="field">
						<input type="text" style="width: 430px;" readonly="readonly" id="address" name="fullAddress"  value="${franchiserMember.address.sido} ${franchiserMember.address.gugun} ${franchiserMember.address.doroname} ${franchiserMember.address.bldmain}-${franchiserMember.address.bldsubmain} (${franchiserMember.address.bupname} ${franchiserMember.address.jimain}-${franchiserMember.address.jisubmain}) " placeholder="집주소"  />		
						<input type="hidden" id="nldno" name="nldno" value="${franchiserMember.nldno}" />		
						<input type="hidden" id="latitude" name="latitude" value="${franchiserMember.latitude}"/>		
						<input type="hidden" id="longitude" name="longitude" value="${franchiserMember.longitude}"/>		
						<input type="hidden" id="fid" name="fid" value="${franchiserMember.fid}"/>		
					</div>
				</div><!-- detail 상세정보 -->
				<div id="stamp_box">
					<h3>스탬프 서비스</h3>
					 <c:choose>
				      <c:when test="${franchiserMember.stype == 'N'}">
				            스탬프서비스란
				      		<button  class="large orange awesome">저장하기</button>	
				      		
				      		<button  class="large orange awesome">스탬를 신청하시겠습니까?</button>	
				      		<button  class="large orange awesome">프리미어 서비스 신청하시겠습니까?</button>	
				      			
				      </c:when>
				      <c:otherwise>
							<table>				
								<tr>
									<th>스탬프 종류</th>
									<th>전체 횟수</th>
									<th>당첨 횟수</th>
									<th>sample</th>
								</tr>
									<c:forEach var="stamptype" items="${stamptypeList}" >
										<tr>
											<td class="stamptype">
												<form:radiobutton  cssClass="validate[required]" path="stamptype" id="${stamptype.stamptype}" value="${stamptype.stamptype}"/>
												<label for="${stamptype.stamptype}">${stamptype.stampname}</label>
											</td>				  									
											<td class="count">${stamptype.stampcount}</td>
											<td class="count">${stamptype.eventday}</td>
											<td class="sample">
												<ul>
													<c:forEach var="i" begin="1" end="${stamptype.stampcount}" step="1" varStatus ="status">
														<c:choose>
		  															<c:when test="${i % stamptype.eventday == 0}">
		  																<li class="stamp_column eventday">
																			<c:out value="${i}" /> e
																		</li>  
		  															</c:when>
		  															<c:otherwise>
		  																<li class="stamp_column">
																			<c:out value="${i}" />
																		</li>
		  															</c:otherwise>
		  												</c:choose>		        														
													</c:forEach>
												</ul>
											</td>
										</tr>
									</c:forEach>
							</table>				
							<div class="field">
								<label for="remark">스탬프이벤트내용</label>
								<form:textarea id="remark" path="remark" placeholder="예) 5회마다 아메리카노 무료"  />
								<form:errors path="remark" cssClass="error" />
							</div>													
					  </c:otherwise>
					</c:choose>
					
				</div><!-- stamp_box 스탬프서비스 -->
	  					
				<div>
					<button  type="submit" class="large orange awesome">저장하기 &raquo;</button>
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
						
			</div><!-- 주소검색 -->
				
			<div>
				<pre>${franchiserMember.remark }
				</pre>
			</div>		
				  					
				  
				  	
		</div><!-- contents -->
		
	</div>
	
	<footer>
		<button id="kakao">카카오톡 보내기</button>
	</footer>
</div>





