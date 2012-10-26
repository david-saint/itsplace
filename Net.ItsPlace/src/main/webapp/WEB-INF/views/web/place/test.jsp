<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
var socket = io.connect('http://127.0.0.1:8090');

socket.on('connect', function () {
    console.log("connectedconnectedconnectedconnectedconnected");
    //socket.emit('Join', { userid: "gggggggggggggggggggggggggggggggg"});
  });

socket.on('receiveMessage', function (data) {
	console.log("받음");
		$('#content').append(data.message);
});
socket.on('news', function (data) {
	 console.log("newsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnewsnews:"+data.hello);
//socket.emit('my other event', { my: 'data' });
});
$(document).ready(function() {
	 
	  
	
	 $('#btnRoom').live('click',function(){	
		 socket.emit('roomJoin', { room: $('#room').val()});
	 });
	 $('#btnUser').live('click',function(){	
		 socket.emit('userJoin', { userId: $('#userId').val()});
	 });
	 $('#btnMessage').live('click',function(){	
		 socket.emit('sendMessage', { room: $('#room').val(),userId: $('#userId').val(), message: $('#message').val()});
	 });
});
</script>
<style>

</style>

 <div>
	<div>방이름<input type="text" id="room"><button id="btnRoom">보내기</button></div>
	<div>사용자<input type="text" id="userId"><button id="btnUser">보내기</button></div>
	<div>메세지<input type="text" id="message"><button id="btnMessage">보내기</button></div>
	<div id="content">
	</div>
</div>


