<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>
<html>
<head>
<script type="text/javascript">
/* var socket = io.connect('http://localhost:8070');
socket.on('connect', function () {
    console.log("connected socket");
    socket.emit('PlaceOn', { room: 'waitRoom',name:$('#userName').val()});
});
socket.on("PlaceOn", function (data) {
	console.log("입장:"+data.room);
});
socket.on('SetUserList', function (data) {
  	
  	console.log("유저목록:"+data);
  //	$('#userList').empty();
  	
  	 for(var i=0;i<data.length;i++){
  		 console.log("user name:"+data[i]);	       		
  		//$('#userList').append('<p>'+data[i]+'</p>');
  	 }
  	
});

	
socket.on("SetRoomList", function (data) {
		console.log("방목록:"+data);
  //	$('#roomList').empty();
  	
  	 for(var i=0;i<data.length;i++){
  		 console.log("user name:"+data[i]);	       		
  		//$('#roomList').append('<p>'+data[i]+'</p>');
  	 }
}); */
$.fn.modal.defaults.maxHeight = function(){
    // subtract the height of the modal header and footer
    return $(window).height() - 205; 
}
$(document).ready(function() {
	 
	/*   
	 $('#jqmWindow').jqm({
		 onShow: function(h) {
		       
		      //  h.w.css('opacity',0.92).slideDown(); 
		        //h.w.show();
		        //h.w.fadeIn("slow");
		        h.w.show();
		      //  h.w.animate( { width: "30%" }, { queue: false, duration: 0 })
		        },
		 onHide: function(h) { 
			$('#placeView').contents().find("body").empty();
			 h.o.remove(); // remove overlay
			 //h.w.hide();
			 h.w.fadeOut("fast");
		      //h.w.fadeOut(888); // hide window
		}
	});   */
	$('#userDetail').jqm();
	
	/* $('#search').live('mouseover',function(){	
		var options = { to: { width: 183, height: 28 } };
		$('#search').effect( 'size', options, 100,mouseover );
	});
	$('#search').live('mouseout',function(){
		$(this).blur();
		
		
		//$('#search').effect('destrooy');
		//var options = { to: { width: 100, height: 28 } };
		//$('#search').effect( 'size', options, 500, mouseout );
	}); */
	$('#userBox').live('click',function(){
		if($('#userDetail').hasClass('userDetailNone')){
			$('#userDetail').removeClass('userDetailNone');
			$('#userDetail').addClass('userDetailBlock');
			$('#userDetail').jqmShow();
			
		}else{
			$('#userDetail').removeClass('userDetailBlock');
			$('#userDetail').addClass('userDetailNone');
			$('#userDetail').jqmHide();
		}
		  
	});
	$('#logo').live('click',function(){
		if($('#tileContainer').hasClass('showMenu')){
			$('#tileContainer').removeClass( "showMenu", 500, '' );;
			$('#myPlace').removeClass( "showMyPlace", 500, '');
		}else{
			$('#tileContainer').addClass( "showMenu", 500, '' );
			$('#myPlace').addClass( "showMyPlace", 500, '');
		}
		
		return false;
	});
	$('#chatUsers').live('click',function(){
		$('.chatUsersOn').switchClass("chatUsersOn","chatUsersOff",100);
		$('.chatUsersOff').switchClass("chatUsersOff","chatUsersOn",100);
		return false;
	});
	
});
function showMenu() {
	console.log("콜백");
/*  setTimeout(function() {
     $( "#tileContainer" ).removeClass( "showMenu" );
 }, 1500 ); */
}
function mouseover() {
	$('#search').css('padding','0 10px 0 32px');
	$('#search').focus();
  
};
function mouseout() {
	//$('#search').val("");
	  console.log("mouseout");
 
};
</script>
</head>
<body>

 <div  id="jqmWindow"   class=" modal hide fade " data-width="1260">
	<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Modal header</h3>
  </div>
  <div class="modal-body" >
    <div class="">
		<%-- <div id="placeHeader">
			<a id="closeButton" href="#" class="jqmClose"><img src="${context}/resources/images/icon/keyamoon/32px/cancel1.png" /></a>
		</div> --%>
		<iframe id="placeView" ></iframe>
	</div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary">Save changes</button>
  </div>
		
	
</div>
<!-- 유저 상세정보 -->
<%--  <div  id="userDetail" class="userDetailNone">
	<sec:authorize ifAnyGranted="ROLE_USER">
 		 	<img src='<sec:authentication property="principal.user.profileImageUrl" />' alt='' /> 
 		 	<div id="name"><sec:authentication property="principal.user.name" /></div>
 		 	<div><sec:authentication property="principal.user.name" /></div> 
	 </sec:authorize>
	 <a href="${context}/logout">로그아웃</a>
	  <div class="arrow-border"></div>
 	  <div class="arrow"></div>
</div> --%>

<div  id="userDetail" class="userDetailNone">
 		<div class="userDetail mrl">
            <ul>
              <li>
                 <img src="<sec:authentication property='principal.user.profileImageUrl' />" />
                 <sec:authentication property="principal.user.name" />
                 
              </li>
              <li>
                 <sec:authentication property="principal.user.email" />
              </li>
              <li>
                <label class="share-label" for="share-toggle4">Twitter</label>
                <div class="toggle">
                  <label class="toggle-radio" for="share-toggle4">ON</label>
                  <input type="radio" name="share-toggles2" id="share-toggle4" value="toggle1" checked="checked">
                  <label class="toggle-radio" for="share-toggle3">OFF</label>
                  <input type="radio" name="share-toggles2" id="share-toggle3" value="toggle2">
                </div>
              </li>
              <li>
                <label class="share-label" for="share-toggle6">Pinterest</label>
                <div class="toggle">
                  <label class="toggle-radio" for="share-toggle6">ON</label>
                  <input type="radio" name="share-toggles3" id="share-toggle6" value="toggle1">
                  <label class="toggle-radio" for="share-toggle5">OFF</label>
                  <input type="radio" name="share-toggles3" id="share-toggle5" value="toggle2" checked="checked">
                </div>
              </li>
            </ul>
            <a href="${context}/logout" class="btn btn-primary btn-block btn-large">로그아웃</a>
          </div> <!-- /share -->
</div>          




<div id="header">
	<div id="logo"> place  </div>
	<div id="searchBox"><form><input type="text" id="search" name="searchWord" /></form></div>
	<%-- <div id="menuBox">
		<a id="btnMyPlace" href="#"> in places</a>
		<a href="/?lang=ko">한글 ${applicationScope.ImageHost} </a>
		<a href="/?lang=en">영어</a>
	</div> --%>
	<div id="userBox">
		 <sec:authorize ifAnyGranted="ROLE_USER">
 		 	<img src='<sec:authentication property="principal.user.profileImageUrl" />' alt='<sec:authentication property="principal.user.name" />' /> 
 		 	<%-- <div id="name"><sec:authentication property="principal.username" /></div>  --%>
		 </sec:authorize>
	</div>
	    
</div> 
	
<div id="myPlace"></div>

<div id="tileContainer" style="display:block;">
	<div id="sample2-grid" class="grid" ></div>
</div>
<div id="placeOn">
	<div id="chatHeader">
		<div id="roomTitle"></div>
		<img id="chatClose" src="${context}/resources/images/icon/keyamoon/16px/cancel.png" />
		<img id="chatUsers" src="${context}/resources/images/icon/keyamoon/16px/enter.png" />
	</div>
	<div id="chat">
		<div id="chatContainer">
			<div class="message"><span></span><span></span></div>
		</div>
		<div id="messageContainer">
			<textarea  id="message"></textarea>
		</div>
	</div>
	<div id="onlineUsers" class="chatUsersOff">
		<p>online</p>
	</div>
</div>
  
<input id="userName" type="hidden"  value="<img class='chatProfile' src='<sec:authentication property="principal.user.profileImageUrl" />'/><sec:authentication property="principal.user.name" />"/> 
<input id="currentRoom" type="hidden"  value="waitRoom"/> 

</body>
</html>