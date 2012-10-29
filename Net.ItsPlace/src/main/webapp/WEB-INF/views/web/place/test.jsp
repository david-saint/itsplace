<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
var socket = io.connect('http://localhost:8090');


$(document).ready(function() {
	 
	  
	
	 $('#btnPlaceOn').live('click',function(){	
		 socket.emit('PlaceOn', { room: $('#fid').val(),'name':$('#userId').val()});
		 $('#content').append('<p style="color:blue;">'+$('#userId').val() +'님 place on<p>');
	 });
	 $('#btnPlaceList').live('click',function(){	
		 socket.emit('getUserList', { room: $('#fid').val()});
		 		// socket.json.send({ 'room': $('#fid').val() });
	 });
	 $('#btnUser').live('click',function(){	
		 socket.emit('userJoin', { userId: $('#userId').val()});
	 });
	 $('#btnMessage').live('click',function(){	
		 //socket.emit('sendMessage', { room: $('#room').val(),userId: $('#userId').val(), message: $('#message').val()});
		 socket.json.send({ 'room': $('#fid').val(), 'name':$('#userId').val(),'data': $('#userId').val() + ": " + $('#message').val() });
		 $('#content').append('<p style="color:blue;">'+$('#userId').val() +": " +$('#message').val()+'<p>');
	 });
	 
	 
	 socket.on('connect', function () {
		    console.log("connectedconnectedconnectedconnectedconnected");
		    //socket.emit('Join', { userid: "gggggggggggggggggggggggggggggggg"});
		  });
	 //방 리스트 
	 socket.on('getUserList', function (data) {
     	console.log('getUserList');
     	console.log(data);
     	$('#userList').empty();
     	 for(var i=0;i<data.length;i++){
     		 console.log(data[i]);
      		
     		$('#userList').append('<p>'+data[i]+'</p>');
     	 }
     	
	 });
		socket.on("receiveMessage", function (data) {
			console.log("receiveMessage");

				$('#content').append('<p>'+data.userId +": " +data.message+'</p>');
		});
		socket.on("roomJoin", function (data) {
			console.log("받음");
		});
		socket.on('news', function (data) {
			 console.log("newsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnews:"+data.hello);
		//socket.emit('my other event', { my: 'data' });
		});
		
		socket.on('message', function(data) {
			console.log("message receive");
	        if (data.comment) {
	        	$('#content').append('<li>'+data.comment+'<li>');
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
	<div>place fid<input type="text" id="fid"><button id="btnPlaceOn">방생</button></div>
	<div>사용자<input type="text" id="userId"><button id="btnUser">보내기</button></div>
	<div>메세지<input type="text" id="message"><button id="btnMessage">보내기</button></div>
	<div>place 상태<input type="text" id="message"><button id="btnPlaceList">상태 </button></div>
	<h1>채팅</h1>
	<div id="content">
	</div>
	<h1>접속자목</h1>
	<div id="userList">
	 
	</div>
</div>


