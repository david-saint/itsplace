<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/bootstrap.css" />" />
		<link rel="stylesheet" type="text/css" href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" /> 
 		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/font/font-awesome.css" />" />
 			<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/flat-ui.css" />" />
 	    <script type="text/javascript" src="<c:url value="/resources/web/js/jquery-1.8.2.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/web/js/bootstrap.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/web/js/common-1.0.0.js" />" ></script>
		
    
		<title><decorator:title default="place" /></title>
		<decorator:head />
		<style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
      .nav-collapse {
		float: right;
	   }
       
       
       
       @media (max-width: 980px) {
       
	       body {
	        padding-top: 0px !important; /* 60px to make the container go all the way to the bottom of the topbar */
	      }
	        /* Enable use of floated navbar text */
	        .navbar-text.pull-right {
	          float: right;
	          padding-left: 5px;
	          padding-right: 5px;
	        }
	        .nav-collapse {
				float: left;
			   }
	   }
    </style>
	</head>
	
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<button type="button" class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="brand" href="#">ITS PLACE</a>
					<div class="nav-collapse in collapse" style="height: auto;">
						<ul class="nav">
							
							
							<li><a href="#myModal" class="nav-login">시작하기</a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<decorator:body/>
	
	 	
	    
	</body>
</html>
  			
  		