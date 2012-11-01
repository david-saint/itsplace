<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
$(document).ready(function() {
});
</script>
<div id="splash">        
                <h2>Forgot your password and can't sign in?</h2>
                <p>Please enter your email address below and we will send you an email with instructions on how to reset your password.</p>
                <form action="" method="post">
                    <div style="display:none"><input type="hidden" name="csrfmiddlewaretoken" value="04ded5128fa6b75f1d3a503cb30fadca"></div>
                	<p>
                	   
                	   <label>Your email address</label>
                       <input id="id_email" type="text" name="email" maxlength="100">            	
                        <input type="submit" value="Send me reset instructions">
                        <span>${error}</span>
                	</p>
                </form>
                <hr>
                <p> Don't need to reset your password? <a class="link" href="/" title="Link back to Pulse.me homepage">Go back to the main page</a><!--</p-->
            </p></div>