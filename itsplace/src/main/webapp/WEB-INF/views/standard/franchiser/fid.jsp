<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">


	$(document).ready(function(){
	
		/*$('.stamp_column').cluetip({
			   hoverClass: 'highlight',
			   sticky: true,
			   closePosition: 'bottom',
			   closeText: '<img src="cross.png" alt="close" />',
			   truncate: 60,			 
			  });
		*/
		document.oncontextmenu = function(e){			
			return false;
		}
		$('.stamp_column').cluetip({width: '200px', showTitle: false});	
		$('.stamp_column').rightClick( function(e) {			
	      	var pid = this.attr('pid');
	      	if(pid!=""){
	      		//alert(pid);
	      		 $('#dialogContent').text(this.attr('title'));
	      		// $('#dialog').dialog('open');
	      		$('#dialog').dialog({
	                autoOpen: true,
	                width: 400,
	                modal: true,
	                resizable: false,
	                buttons: {
	                    "삭제": function() {
	                    	stamp_delete(pid);
	                    	$(this).dialog("close");
	                    },
	                    "취소": function() {
	                        $(this).dialog("close");
	                    }
	                }
	            });
	      	}
	    });
		/*			
		$.pnotify({
						pnotify_title: '스탬프 등록',
						pnotify_text: '등록되었습니다.',
						pnotify_opacity: .8,
						//pnotify_animate_speed: 'fast',
						pnotify_animation: 'show'
		});
		*/
		
 
	});
	/*
	 *
	 */
	 function stamp_delete(pid){
		
		 var url = common_getHostUrl()+'/stamp/delete/'+pid;
			alert(pid);
		 $.ajax({
		        type: 'get'
		        , async: false
		        , url: url
		        , dataType: 'json'		       
		        , success: function(data) {
		        	$('#'+pid).text("취소");
		        	
		        }
		 		, error: function(data, status, err) {
		 			//log.info("error forward : "+data+err+status);
		 			alert('stamp_delete 서버와의 통신이 실패했습니다.'+data+err+status);
		 		}
		 		, complete: function() {
		 		
		 		}
		 });
	}
	/*
	 * ajax 스탬프 휴대폰번호로 등록
	 * ver1.0 김동훈 2011-09-29
	 * param 
	 * return Json
	 * 
	 */
	function stamp_register(){
		var mobile = $('#mobile').val();
		var stamptype = '${franchiserMember.stamptype}';
		if(mobile==""){
			alert("전화번호를 입력해주세요");
			$('#mobile').focus();
			return false;
		}
		///stamp/register{mobile}/stamptype/{stamptype}
		var url = common_getHostUrl()+'/stamp/register/'+mobile+'/stamptype/'+ stamptype;
		
		 $.ajax({
		        type: 'get'
		        , async: false
		        , url: url
		        , dataType: 'json'		       
		        , success: function(data) {
		        	$.pnotify({
						pnotify_title: '스탬프 등록',
						pnotify_text: data,
						pnotify_opacity: .8,
						//pnotify_animate_speed: 'fast',
						pnotify_animation: 'show'
					});
		        	
		        }
		 		, error: function(data, status, err) {
		 			//log.info("error forward : "+data+err+status);
		 			alert('stamp_register 서버와의 통신이 실패했습니다.'+data+err+status);
		 		}
		 		, complete: function() {
		 		
		 		}
		 });
	}		
	
	
	function stamp_search(){
		
	}	

</script>
	 
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
				<img src="${USERSESSION.profileImageUrl}" width="35" height="35" />${USERSESSION.email} ${USERSESSION.name} 님의 계정설정
				
			</h1>        		
        			
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<h1>전화번호로 스탬프 남기기 그로우...저장하기 그로우 단축키로 등록할것 ,,툴팁으로 등록시간 보여주고 오른쪽마우스 클릭시 취소컨펌</h1>
			<a href="<c:url value="/partner/franchiserDetail/${USERSESSION.email}" />">스탬츠 설정</a>
			<form id="search" action="<c:url value="/stamp/mobile/" />" method="get">
			<label for="label">휴대폰</label>
			<input type="text" name="mobile" id="mobile" value="${mobile}"/>
			<input type="text" name="fid" id="fid" value="${USERSESSION.email}"/>
			<input type="hidden" name="stamptype" value="${franchiserMember.stamptype}"/>
			
			
			
			<button  type="submit" class="large blue awesome">스탬프 조회 &raquo;</button> <br /><br />
			</form>
			<button onclick="stamp_register()" class="large blue awesome">스탬프 등록 &raquo;</button> <br /><br /> 
			
			
			
				
						<div class="stamp_box">
							<div>
								${user.name}
								<img src=${user.profileImageUrl} />
							</div>
							<p>${stamptypeRegister.stampname}</p>
							<p>${stamptypeRegister.remark}</p>
							
							<c:forEach var="stamppedList" items="${stamppedListAll}"  >
							<ul style="border:1px solid blue;">
								<c:forEach var="stamp" items="${stamppedList}" varStatus ="status">
									<li id="${stamp.pid}" class="stamp_column ${stamp.eventday}"  title="<fmt:formatDate value="${stamp.inpdate}" pattern="yyyy-MM-dd hh:mm:ss"/>"  pid="${stamp.pid}" date="">
			  						
			  						${status.index+1}
			  						
		  							</li>
								</c:forEach>
		  					</ul>	
		  					</c:forEach>	
		  					
						</div>				  					  					 
				 
				 
				 
		</div>
		<!--  content end-->
	</div>
	<footer>Footer</footer>
</div>


<div id="dialog" title="Its Place">
<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 0 0;"></span>스탬프를 취소하시겠습니까?</p>
<p id="dialogContent"></p>
</div>

