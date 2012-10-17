<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<c:set var="title" value="나의도서"/>
<html>
<head>
    <title>${title}</title>
    <script  src="<c:url value="/resources/js/jquery.isotope.min.js" />" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/isotope.css" />" />	
    <script  src="<c:url value="/resources/components/mosaic/js/mosaic.1.0.1.min.js" />" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/mosaic/css/mosaic.css" />" />	

    <script  src="<c:url value="/resources/components/noty/jquery.noty.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/layouts/center.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/layouts/top.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/layouts/topRight.js" />" type="text/javascript"></script>
    <script  src="<c:url value="/resources/components/noty/themes/default.js" />" type="text/javascript"></script>
        
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		$('#btnRental').click(function(){  
	 			 $('#isotope').isotope({ filter: '.rental' });
	 		});
	 		$('#btnReservation').click(function(){
	 			 $('#isotope').isotope({ filter: '.reservation' });
	 		});
	 		$('#btnAll').click(function(){
	 			 $('#isotope').isotope({ filter: '.item' });
	 		});
	 		$('#btnHistory').click(function(){
	 			 $('#isotope').isotope({ filter: '.history' });
	 		});
	 		initialize();
	 		/* $('#isotope').isotope({
	 			itemSelector:'.item',
	 			layoutMode:'fitRows'
	 		});
	 		$('.bar2').mosaic({
				animation	:	'slide'	
			});   */
	 	});
	 	
	 	function initialize(){
	 		$('#isotope').empty();
	 		$('#isotope').isotope('destroy');
	    	getRentalBooks();
	 		getReservationBooks();
	 		
	 		//$('#isotope').isotope({ filter: '.rental' });
	 		
	 	}
	 	function removeItem(id){
	 		c.log(id);
	 		c.log(id);
	 		c.log(id);
	 		c.log(id);
	 		c.log(id);
	 		c.log(id);
	 		 $('#isotope').isotope( 'remove',$('#'+id).parent());
	 	}
	 	function getReservationBooks(){
	 		
	 		var url = "${context}/book/reservations";
 			url += "?decorator=exception";
 			$.ajax({
                 url: url,
                 type:"POST",                                
                 data:$("form").serialize(),                 
                 success: function(response){
                   if(response.status=="SUCCESS"){
                	   c.log(response.result);
                	    var  html = new StringBuffer();
                	    
                	   $.each(response.result, function(i){
                		   var reservationDate = new Date(this.reservationDate);
                		   var message = "";
                		   var day = (this.reservationDate -  new Date().getTime()) /1000/60/60/24 ;
                		   c.log(day);
                		   day = parseInt(Math.round(day));
                		   if(day==0){
                			   message = "오늘 예약하였습니다";
                		   }else if(day<0){
                			   message = "예약중입니다";
                		   }else{
                			   message = day + "일 남았습니다";
                		   }
                		   html.append('<div class=" item reservation bar2 ">');
                		   html.append('<a class="mosaic-overlay reservationBook" id="'+this.id+'">');
                		   html.append('<div class="details">');
                		   html.append('<h4>'+message+'</h4>');
                		   html.append('</div>');
                		   html.append('</a>');
                		   html.append('<div class="mosaic-backdrop">');
                		   html.append('<img src="'+this.bookInfo.thumbnail+'"/>');
                		   html.append('</div>');
                		   html.append('</div>');
                	   });
                	   $('#isotope').append(html.toString());
                   }else{                    	  
                	   c.log(response.result);   
                   }
                 },
                 
                 complete:function(){
                	$('.reservationBook').bind('click', function() {
                		reservationCancel($(this).attr('id'));
                	});
                 }
               });//ajax
	 	}
		function getRentalBooks(){
			var url = "${context}/book/rentals";
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
                	  
                	    var  html = new StringBuffer();
                	    
                	   $.each(response.result, function(i){
                		   c.log(this.bookInfo.title);
                		   var endDate = new Date(this.endDate);
                		   var message = "";
                		   var day = (endDate.getTime() -  new Date().getTime()) /1000/60/60/24 ;
                		   c.log(day);
                		   day = parseInt(Math.round(day));
                		   if(day==0){
                			   message = "오늘은 반납일입니다";
                		   }else if(day<0){
                			   message = "반납 예정일이 "+ Math.abs(day) +"일 지났습니다";
                		   }else{
                			   message = day + "일 남았습니다";
                		   }
                		   html.append('<div class="item rental bar2">');
                		   html.append('<a class="mosaic-overlay iframe fancy"  id="'+this.id+'" href="${context}/book/return?id='+this.id+'">');
                		   html.append('<div class="details">');
                		   html.append('<h4>'+message+'</h4>');
                		   html.append('<p>'+this.bookInfo.title+'</p>');
                		   html.append('</div>');
                		   html.append('</a>');
                		   html.append('<div class="mosaic-backdrop">');
                		   html.append('<img src="'+this.bookInfo.thumbnail+'"/>');
                		   html.append('</div>');
                		   html.append('</div>');
                	   });
                	   $('#isotope').append(html.toString());
                   }else{                    	  
                	   c.log(response.result);   
                   }
                 },
                 
                 complete:function(){
                	
         	 		getRentalBookHistory();
                 }
               });//ajax
		}
		function getRentalBookHistory(){
			var url = "${context}/book/rentalHistory";
 			url += "?decorator=exception";
 			$.ajax({
                 url: url,
                 type:"POST",                                
                 data:$("form").serialize(),
                 beforeSend :function(xhr){
                	 xhr.setRequestHeader("Accept", "application/json");
                 },
                 success: function(response){
                   if(response.status=="SUCCESS"){
                	  // c.log(response.result);
                	    var  html = new StringBuffer();
                	    
                	   $.each(response.result, function(i){
                		   var returnDate = new Date(this.returnDate);
                		   var message = "";
                		   var day = (   new Date().getTime() - this.returnDate) /1000/60/60/24 ;
                		   day = parseInt(Math.round(day));
                		   html.append('<div class="item history bar2">');
                		   html.append('<a class="mosaic-overlay">');
                		   html.append('<div class="details">');
                		   html.append('<h4>'+day+'일 되었습니다</h4>');
                		   html.append('</div>');
                		   html.append('</a>');
                		   html.append('<div class="mosaic-backdrop">');
                		   html.append('<img src="'+this.bookInfo.thumbnail+'"/>');
                		   html.append('</div>');
                		   html.append('</div>');
                	   });
                	   $('#isotope').append(html.toString());
                   }else{                    	  
                	   c.log(response.result);   
                   }
                 },
                 
                 complete:function(){
                	
                	 $('#isotope').isotope({
           	 			itemSelector:'.item',
           	 			layoutMode:'fitRows'
           	 		});
          	 		$('.bar2').mosaic({
          				animation	:	'slide'	
          			});  
          	 		
          	 		$('.fancy').fancybox({
     					'autoDimensions':false,
     					'scrolling':'auto',
     					'autoScale':false,
     					'height':400,
     				});
          	 		$('#isotope').isotope({ filter: '.rental' });
          	 		
                 }
               });//ajax
		}
		function reservationCancel(id){
			noty({
	 			 modal: true,
	 			 animation: {
	 			    open: {height: 'toggle'},
	 			    close: {height: 'toggle'},
	 			    easing: 'swing',
	 			    speed: 100 // opening & closing animation speed
	 			  },
	 			  layout: 'center',
	 			  text: '예약 취소하시겠습니까?', 
	 			  buttons: [
	 			    {addClass: 'uibutton normal', text: '확 인', onClick: function($noty) {
	 		 			var url = "${context}/book/reservationCancel";
	 		 			//url += "?decorator=exception";
	 		 			$.ajax({
	 	                     url: url,
	 	                     type:"POST",                                
	 	                     data:"id="+id,	 	  
	 	                     beforeSend :function(xhr){
	 	                  	     xhr.setRequestHeader("Accept", "application/json");
	 	                     },
	 	                     success: function(response){
	 	                       if(response.status=="SUCCESS"){
	 	                    	   c.showSuccess(response.result,1000);
	 	                    	  $('#isotope').isotope( 'remove',$('#'+id).parent());
	 	                    	  
	 	                       }else{
	 	                    	   c.showError(response.result,1000);
	 	                       }
	 	                     },	 	                     
	 	                     complete:function(){	 	                    	
	 	                    	$noty.close(); 
	 	                     }
	 	                   });//ajax
	 			      }
	 			    },
	 			    {addClass: 'uibutton normal', text: '취 소', onClick: function($noty) {
	 			        $noty.close();
	 			      }
	 			    }
	 			  ]
	 			});
		}
		function StringBuffer() {
			 this.buffer = [];
		} 
		StringBuffer.prototype.append = function append(string) {   
			 this.buffer.push(string);
			 return this;
		};
	    StringBuffer.prototype.toString = function toString() {
			return this.buffer.join(""); 
		}; 
	</script>
	<style type="text/css">
		.item{
			float: left;
			overflow:hidden;
			width:198px;
			height:286px;
			border:0px solid red;
			margin-right:15px;
			margin-top:15px;
		}
		.item img{
			width:198px;
			height:286px;
		}

		.details{ margin:18px 20px;  height:50px;}	
		h4{ font:300 11px 'Helvetica Neue', Helvetica, Arial, sans-serif; color:#fff; text-shadow:1px 1px 0 rgb(0,0,0); }
		p{ margin-top:15px; font:300 12px 'Lucida Grande', Tahoma, Verdana, sans-serif; color:#aaa; text-shadow:1px 1px 0 rgb(0,0,0);}
		a{ text-decoration:none; } 
	</style>
</head>
<body>
	<div class="widget">
		<div class="header">
			<span><span class="ico gray brightness"></span>${title}</span>
		</div>
		<div class="content">		
			<a id="btnAll" class="uibutton normal">전체</a>
			<a id="btnRental" class="uibutton normal">대출목록</a>
			<a id="btnReservation" class="uibutton normal">예약목록</a>
			<a id="btnHistory" class="uibutton normal">대출이력</a>
			<div id="isotope">
			<%-- <c:forEach var="rentalBook" items="${rentalBookList}" varStatus="status">
				<div class="item bar2">
					<a class="mosaic-overlay">
						<div class="details">
							<h4>${rentalBook.endDate}</h4>
							<p>반납</p>
						</div>
					</a>
					<div class="mosaic-backdrop">
						<img src="${rentalBook.bookInfo.thumbnail}"/>
					</div>	
				</div>
			</c:forEach>	 --%>			
			</div>			
		</div>
		</div><!--end content -->
	</div><!--end widget -->
</body>
</html>