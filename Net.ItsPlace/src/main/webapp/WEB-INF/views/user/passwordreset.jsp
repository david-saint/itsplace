<%@page import="java.util.Locale"%>
<%@ page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<c:set var="title" value="메인페이지 " />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<h2>  <spring:message code="signup" /> Forgot your password and can't sign in?</h2>
			<p>Please enter your email address below and we will send you an
				email with instructions on how to reset your password.</p>
		</div>		
		<div class="row">
		<div class="span6" style="border:0px solid blue">	
				
			<h3>Your email address</h3> 
		</div>
		</div>
		<div class="row-fluid" style="border:0px solid red;">		
			<form action="" method="post">
				<div style="display: none">
					<input type="hidden" name="csrfmiddlewaretoken"
						value="04ded5128fa6b75f1d3a503cb30fadca">
				</div>
								
				<div class="span6">	
				<div class="row" style="border:0px solid gray">
		          <div class="span8" style="padding-right: 16px;">
		         
		          		<input id="id_email" type="email" name="email" style="width:100%;padding-bottom: 11px;padding-top: 11px;">
		          
		          </div>
		          <div class="span4"><input type="submit" class="btn btn-large btn-block " value="Send Me"> <span>${error}</span>	</div>
		        </div>
				
				
				</div> 
				
					
				
			</form>
			
		
		</div>
		<div class="row-fluid" style="border:1px solid red;">		
			<hr>
			<p>
				Don't need to reset your password? <a class="link" href="/"
					title="Link back to Pulse.me homepage">Go back to the main page</a>
				<!--</p-->
			</p>
		</div>
	</div>
</body>
</html>
