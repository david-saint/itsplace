<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
$(document).ready(function() {
});
</script>
<div id="page-container" style="min-height: 481px; ">  
    	       
    
        	<c:if test="${not empty error}">
        			${error}
        	  </c:if>
        	 
        	 
            <div id="splash">
                <div class="right">
                    <img width="30" height="30" alt="Secured Process" src="/media/images/lock-cc66fb94f61455035bbc800b0afceb1b4592811f.png">
                </div>
                <h2 class="title">Reset your Pulse.me password</h2>
                <p>Please type in your new password below.</p>    
                <form action="/passwordchange" method="post">
                    
                    <label>New Password</label>
                      <input type="hidden" name="passwordLink" value="${passwordLink}">
                    <input type="password" name="password" id="id_new_password1">
                    <label>New Password Again</label>
                    
                    <input type="password" name="confirmPassword" id="id_new_password2">
                    <input type="submit" value="Change my password">
                </form>
            </div>
     



<!--
                <a title="Go to Jobs page" href="/jobs">
                    <img id="hiring" src="/media/images/hiring-blue-2-2fe1b838c1ef05aad5c69aa8f89264c8f2906fa9.png"/>
                </a>
-->
            </div>