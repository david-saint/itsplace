<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
var socket = io.connect('http://localhost:8070');
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
});
$(document).ready(function() {
	 
	  
	$('#jqmWindow').jqm({onHide: function(h) { 
		$('#placeView').contents().find("body").empty();
		 h.o.remove(); // remove overlay
		 //h.w.hide();
		 h.w.slideUp('fast');
	      //h.w.fadeOut(888); // hide window
	} 
	    }); 
	
	$('#search').live('mouseover',function(){	
		var options = { to: { width: 183, height: 28 } };
		$('#search').effect( 'size', options, 100,mouseover );
	});
	$('#search').live('mouseout',function(){
		$(this).blur();
		
		
		//$('#search').effect('destrooy');
		//var options = { to: { width: 100, height: 28 } };
		//$('#search').effect( 'size', options, 500, mouseout );
	});

	 
});
function mouseover() {
	$('#search').css('padding','0 10px 0 32px');
	$('#search').focus();
  
};
function mouseout() {
	//$('#search').val("");
	  console.log("mouseout");
 
};
</script>
<style>

</style>

 <div class="jqmWindow" id="jqmWindow">
	<div class="placeContainer">
	<a id="closeButton" href="#" class="jqmClose">Close</a>
		<iframe id="placeView"></iframe>
	</div>
</div>


<div id="header">
	<div id="logo"> place </div>
	<div id="searchBox"><form><input type="text" id="search" name="searchWord" /></form></div>
	<div id="menuBox" ><a id="btnMyPlace" href="#">  메뉴</a></div>
	    <p><a id='customAlert' href="#" onclick='socket.send("customAlert")'>publish customAlert</a></p>
        <p><a id='customAlert2' href="#" onclick='socket.send("customAlert2")'>publish customAlert2</a></p>
</div> 
	

<div class="tileContainer">

	<div id="sample2-grid" class="grid" ></div>
</div>
  
  <input id="userName" type="hidden"  value="" <sec:authentication property="principal.user.name" />/> 
 <sec:authorize ifAnyGranted="ROLE_USER">
 	이미지: <sec:authentication property="principal.user.name" /> 
 	이미지: <sec:authentication property="principal.username" /> 
 	이미지: <sec:authentication property="principal.user.profileImageUrl" /> 
 </sec:authorize>

