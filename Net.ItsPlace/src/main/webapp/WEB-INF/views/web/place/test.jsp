<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
var socket = io.connect('http://localhost:8070');
//var socket = io.connect('http://test.faye12005.c9.io');


$(document).ready(function() {
	 
	  
	
	 $('#waitRoom').live('click',function(){	
		//대기실 입장
		    socket.emit('PlaceOn', { room: 'waitRoom',name:$('#userId').val()});
	 });
	 $('#roomlist').live('click',function(){	
		//대기실 입장
		    socket.emit('RoomList', { room: 'waitRoom',name:$('#userId').val()});
	 });
	 
	 //방변경
	 $('#btnRoom').live('click',function(){	
		 socket.emit('PlaceChange', { prevRoom:'waitRoom', room: $('#fid').val() ,'name':$('#userId').val()});
		 
	 });
	 $('#quit').live('click',function(){	
		 socket.disconnect();
		 //socket.emit('disconnect', { room: $('#fid').val() ,'name':$('#userId').val()});
	 });
	 $('#btnUser').live('click',function(){	
		 socket.emit('userJoin', { userId: $('#userId').val()});
	 });
	 $('#btnMessage').live('click',function(){	
		 //socket.emit('sendMessage', { room: $('#room').val(),userId: $('#userId').val(), message: $('#message').val()});
		 socket.json.send({ room: 'waitRoom', name:$('#userId').val(), data: $('#message').val() });
		// $('#content').append('<p style="color:blue;">'+$('#userId').val() +": " +$('#message').val()+'<p>');
	 });
	 
	 
	 socket.on('connect', function () {
		    console.log("connectedconnectedconnectedconnectedconnected");
		    
		
		  });
	
	 	socket.on("PlaceOn", function (data) {
			console.log("입장:"+data.room);
			 $('#content').append('<p style="color:blue;">'+data.success +'<p>');
			 
		});
	 	
	 	socket.on('SetUserList', function (data) {
	      	
	      	console.log("유저목록:"+data);
	      	$('#userList').empty();
	      	
	      	 for(var i=0;i<data.length;i++){
	      		 console.log("user name:"+data[i]);	       		
	      		$('#userList').append('<p>'+data[i]+'</p>');
	      	 }
	      	
	 	 });

	 	
	 	socket.on("SetRoomList", function (data) {
	 		console.log("방목록:"+data);
	      	$('#roomList').empty();
	      	
	      	 for(var i=0;i<data.length;i++){
	      		 console.log("user name:"+data[i]);	       		
	      		$('#roomList').append('<p>'+data[i]+'</p>');
	      	 }
		});
	 	
	 	
		
	
		
		socket.on('message', function(data) {
			console.log("message receive");
	        if (data.comment) {
	        	$('#content').append('<div><span>'+data.name + ":</span><span>" + data.comment+'</span></div>');
	        }
	    });
		socket.on('error', function(data) {
			console.log("ddd");
	        if (data.error) {
	        	alert(data.error);
	        }
	    });
});
</script>
<style>

</style>

 <div>
	<div>place fid<input type="text" id="fid"><button id="btnRoom">방변경</button></div>
	<div>사용자<input type="text" id="userId" value=""><button id="btnUser">보내기</button></div>
	<div>메세지<input type="text" id="message"><button id="btnMessage">보내기</button></div>
	<div>place 상태<input type="text" id="message"><button id="btnPlaceList">상태 </button></div>
	
	<div>
		<button id="waitRoom">대기실</button>
		<button id="roomlist">방목록</button>
		<button id="quit">종료</button>
	</div>
	<h1>채팅</h1>
	<div id="content">
	</div>
	<h1>접속자목</h1>
	<div id="userList">
	 
	</div>
	<h1>방목록</h1>
	<div id="roomList">
	 
	</div>
</div>


