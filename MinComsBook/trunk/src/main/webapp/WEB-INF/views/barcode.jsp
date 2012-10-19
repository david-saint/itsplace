<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<c:set var="title" value="도서 대출"/>
<style>
html {
	font-size: 100%;
}

body {
	margin: 0;
	padding: 0;
	font-family: sans-serif;
}

article,aside,details,figcaption,figure,footer,header,hgroup,nav,section
	{
	display: block;
}

#m-container {
	position: relative;
	overflow: hidden;
	padding: 0 0 50px 0;
	background: #222 repeat top left;
	height: auto;
}

.inner {
	border: 0px solid red;
	position: relative;
	width: 100%;
	margin: 0 auto;
	padding: 35px 15px;
	max-width: 960px;
	overflow: hidden;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

#header {
	padding: 0;
	width: 100%;
	height: 49px;
}

.Sketchetik-Light {
	font-family: Sketchetik-Light;
	font-weight: normal;
	font-style: normal;
}

#header .logo {
	float: left;
}

#header #signin,#header #signup {
	position: relative;
	float: right;
	z-index: 2;
}

#header a {
	display: block;
	height: 49px;
	line-height: 49px;
	text-decoration: none;
	color: white;
	font-weight: 700;
	font-size: 18px;
}

#infiniteCarousel {
	width: 960px;
	height: 240px;
	position: relative;
}

#info-container {
	position: relative;
	overflow: hidden;
	background:
		url(/media/images/light_gray_noise-994fecd3fbd995349a86ef01bbe5efadc91939a9.jpg)
		#222 repeat 0 0;
}

.info-headline {
	margin: 0 auto;
	padding: 20px 0;
	max-width: 688px;
	width: 100%;
	font-size: 30px;
	font-weight: 400;
	line-height: 1.25em;
	text-align: center;
	color: gray;
	font-weight: bold
}

footer {
	background: black;
	min-height: 50px;
}

footer ul {
	position: relative;
	float: left;
}

footer li {
	position: relative;
	float: left;
	display: block;
}

footer li a {
	font-size: 12px;
	color: #3A3A3A;
	text-decoration: none;
	
	margin: 0 30px 0 0;
	font-weight: 600;
}

.copyright {
	float: right;
	font-size: 12px;
	color: #3A3A3A;
}

nav ul,nav ol {
	list-style: none;
	list-style-image: none;
}

ul,ol {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

#background {
	width: 1040px;
	height: 470px;
	background:
		url('http://work.mincoms.com:62560/Images/Layout/login_bg_120618.jpg')
		no-repeat;
}
.blur{

   font-size:10px;
    color: #D2D2D2; 
   
}
</style>
<html>
  <head>
    <title>{$title}</title>
	<script type="text/javascript">
	function init(){
		$('#userIdNumber').val("");
    	$('#isbn').val("");
    
    	$('#isbn').focus();
	}
	 	$(document).ready(function(){
	 		init();
	 		
	 		
        	$('#userIdNumber').click(function(){
        		$(this).val("");
        		$('#userIdNumber').removeClass("blur");
        	});
        	
        	$('#isbn').click(function(){
        		$(this).val("");
        		$('#isbn').removeClass("blur");
        	});
        	
			$('#btnRental').click(function(){
				var id =$('#userIdNumber').val();
            	var isbn = $('#isbn').val();
            	if(id=="" || isbn==""){
            		return false;
            	}
	 			var url = "${context}/rental";
	 			url += "?decorator=exception";
	 			$.ajax({
                     url: url,
                     type:"POST",                                
                     data:$("form").serialize(),
                     beforeSend :function(xhr){
                    	 xhr.setRequestHeader("Accept", "application/json");
	                      var title = $('.loading').attr('title');
	   	 				  var overlay=$(this).attr('rel'); 
	   	 	 			  //c.loading("title",1);
                     },
                     success: function(response){
                       if(response.status=="SUCCESS"){
                    	   var delay =1000;
                    	   c.showSuccess(response.result,delay);
                       }else{
                    	   c.showError(response.result,delay);
                    	   
                       }
                       c.clearForm();
                      
                     },
                     
                     complete:function(){
                    	 init();
                     }
                   });//ajax
	 		});
			
			
			$('#btnReturnBook').click(function(){
				var id =$('#userIdNumber').val();
            	var isbn = $('#isbn').val();
            	if(id=="" || isbn==""){
            		return false;
            	}
	 			var url = "${context}/return";
	 			url += "?decorator=exception";
	 			$.ajax({
                     url: url,
                     type:"POST",                                
                     data:$("form").serialize(),
                     beforeSend :function(xhr){
                    	 xhr.setRequestHeader("Accept", "application/json");
	                      var title = $('.loading').attr('title');
	   	 				  var overlay=$(this).attr('rel'); 
	   	 	 			  //c.loading("title",1);
                     },
                     success: function(response){
                       if(response.status=="SUCCESS"){
                    	   var delay =1000;
                    	   c.showSuccess(response.result,delay);
                       }else{
                    	   c.showError(response.result,delay);
                       }
                     },
                     
                     complete:function(){
                    	 init();
                     }
                   });//ajax
	 		});
	 	});	 	
	</script>
</head>
<body>	

<section id="info">
	<div id="info-container" style="">
		<div id="background" class="inner">
		</div>
	</div>
</section>		
<section id="m">
	<div id="m-container">
	
		<div class="inner" style="text-align: center;min-height: 300px;">
			<h2 class="info-headline">
				도서관리시스템
			</h2>
		
		<form>
           <div style="margin-top:30px">
           		<input type="text" id="isbn" name="isbn" Class="medium" value=""  style="height:60px;font-size:30px;text-align: center;width:400px" placeholder="ISBN 코드"/>
           </div>
           <div style="margin-top:30px">
           		<input type="text" id="userIdNumber" name="userIdNumber" Class="medium" value="" style="height:60px;font-size:30px;text-align: center;width:400px" placeholder="주민등록번호"/>
           </div>
           <div class="section" style="display:none" >
               <label> 대출 일수 <small></small></label>   
               <div  style="font-size:30px">
               		<select id="day" name="day" style="font-size:30px" >
               			<option value="1">1일</option>	
               			<option value="2">2일</option>	
               			<option value="3">3일</option>	
               			<option value="4">4일</option>	
               			<option value="5">5일</option>	
               			<option value="6">6일</option>	
               			<option value="7" selected>7일</option>	
               		</select>
               		                              
               		<span class="f_help"></span>
               </div>
          </div> 
          	</form> 
           <div style="margin-top:30px;font-size:30px;">
               <div>
           			<button id="btnRental" style="width:400px;height:50px;font-size:20px;font-weight: bold;">대 출</button>
           	   </div>
           </div>    
           <div  style="margin-top:10px;">
               <div>
           			<button id="btnReturnBook" style="width:400px;height:50px;font-size:20px;font-weight: bold;">반  납</button>
           	   </div>
           </div>    
       	 
		</div>
	
	</div>
</section>	
<footer>
	<div class="inner">
		<nav>
			<ul>
				<li><a href="/">도서관리</a></li>
				
			</ul>
		</nav>
		<small class="copyright">All content Copyright 2012 by
			Mincommunication</small>
	</div>
</footer>
</body>
</html>
