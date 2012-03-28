<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <script type="text/javascript">

 $(document).ready(function(){
	 
	 //$('.date').datepicker($.datepicker.regional['ko']);
	// $('.date').datepicker();
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
		'7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
		monthNamesShort: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
		'7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: '년'};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$('.date').datepicker();
		
	$("#accordion").accordion();
	
	$("#accordion button").click(function(){
		$(window.location).attr('href', common_getHostUrl()+"/place/event/edit/"+$(this).attr('eid'));
	});
					  
});
 </script>

	
	
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
				<c:choose>
					
	<c:when test="${event.new}">이벤트등록</c:when>
	<c:otherwise>이벤트수정</c:otherwise>
</c:choose>
			</h1>        		
        			
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<form:form   modelAttribute="event" method="post">
				<div id="userForm"> 
					
					<!--<form:errors path="*" cssClass="errorblock" element="div" />-->
					
					<div class="field">
						<form:hidden id="eid" path="eid"  />
						<form:errors path="eid" cssClass="error" />
					</div>
					<div class="field">
						<form:hidden id="fid" path="fid"  />
						<form:errors path="fid" cssClass="error" />
					</div>
					<div class="field">
					<label for="title">이벤트명</label>
						<form:input id="title" path="title" placeholder="이벤트명"  />
						<form:errors path="title" cssClass="error" />
					</div>	
					<div class="field">
					<label for="content">내용</label>
						<form:textarea id="content" path="content" placeholder="이벤트내용"  />
						<form:errors path="content" cssClass="error" />
					</div>	
					<div class="field">
						<label for="startDate">시작일</label>
						<form:input id="startDate" path="startDate"  readonly="true" cssClass="date" placeholder="시작월일"  />
						<form:errors path="startDate" cssClass="error" />
					</div>	
					<div class="field">
						<label for="endDate">종료일</label>
						<form:input id="endDate" path="endDate" readonly="true" cssClass="date" placeholder="종료월일"  />
						<form:errors path="endDate" cssClass="error" />
					</div>	
					<div class="field">
						<label for="enable">사용</label>
						<form:radiobutton id="enable" path="useyn" placeholder="사용여부" value="Y" />
						<label for="disable">종료</label>
						<form:radiobutton  id="disable" path="useyn" placeholder="사용여부" value="N" />
						<form:errors path="useyn" cssClass="error" />
					</div>	
					
				</div>
		
				<!--<div>
					<script type="text/javascript" src="http://api.recaptcha.net/challenge?k=6LerHscSAAAAAJ-Cpt77wpMi-smuw5XY-8eJjR3d">
					</script>
					<noscript>
					   <iframe src="http://api.recaptcha.net/noscript?k=6LerHscSAAAAAJ-Cpt77wpMi-smuw5XY-8eJjR3d"
					                height="300" width="500" frameborder="0"></iframe><br>
					   <textarea name="recaptcha_challenge_field" rows="3" cols="40">
					   </textarea> 
					   <input type="hidden" name="recaptcha_response_field" value="manual_challenge">
					</noscript>
				</div> catcaha -->
				
				
			<div id="buttons">
				<button type="submit" class="large orange awesome">등록 &raquo;</button>
				<button type="submit" class="large orange awesome">취소 &raquo;</button>
			</div>
			</form:form>
			
			<div id="accordion" style="height:300px; overflow: auto;">
				<c:forEach var="event" items="${eventList}" >
							<h3>
								<a href="#">
									<span>${event.title} </span>
									<span style="float: right">
										<button style="margin-top:-10px;margin-left:5px;" eid="${event.eid}" class="medium orange awesome">수정</button>
									</span>
									<span style="float: right"><fmt:formatDate value="${event.startDate}" pattern="yyyy-MM-dd"/> ~ <fmt:formatDate value="${event.endDate}" pattern="yyyy-MM-dd"/></span>
								</a>
								
							</h3>
							<div>
								<p>
								${event.content }
								</p>
							</div>		  					  					 
				</c:forEach>
			</div>	  	
		</div><!-- content end -->
	</div>
	<footer>Footer</footer>
</div>

<!-- 
<p>
	<button type="button" id="btn_ajaxTest" > AjaxTest</button>
	<div id="testAjax"></div>
</p> 
<p>
	<button type="button" id="btn_JsonTest" > JsonTest</button>
	<div id="testJson"></div>
</p> 
<p>
	<button type="button" id="btn_JsonObject" > JsonObject</button>
	<div id="testObject"></div>
</p>
 -->
	   
	   
