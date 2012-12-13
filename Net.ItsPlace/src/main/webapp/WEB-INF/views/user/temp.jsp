<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="place"/>

<script type="text/javascript">
$(document).ready(function(){ 	
	$('form').submit();

});

</script>
	<form name="fb_signin" id="fb_signin" action="http://localhost:8080/signin/facebook" method="POST">
							
							        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,email" />
							        <input type="hidden" id="on_off" name="_spring_security_remember_me" value="1" />
							        <fieldset> 
									<button  type="submit" class="grayButton facebook"><i class="icon-facebook"></i><span>페이스북 아이디로 로그인</span></button>
                            </fieldset>
					    </form>
