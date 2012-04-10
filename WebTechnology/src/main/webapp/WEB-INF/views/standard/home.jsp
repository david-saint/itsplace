<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script type="text/javascript">
	var socket = io.connect('http://127.0.0.1:8090');
	 $(function() {
		 
		 console.log("test");
		
		 
		 
		 socket.on('ReceiveMsg', function (data) {
			$('#receiveMsg').append(data.userid +":"+data.message+"<br>");	
		
		 });
	 });
	 function join(){
		 var userid = $('#userid').val();
		 socket.emit('Join', { userid: userid });
	 }
  			
	 function send(){
		 var sendMsg = $('#sendMsg').val();
		 var toUserId = $('#toUserId').val();
		 socket.emit('SendMessage', { userid: toUserId, message: sendMsg });
	 }
</script>
test
<input id="userid" type="text"  /><button onclick="join()">Join</button>
<input id="toUserId" type="text"  />
<input id="sendMsg" type="text"  /><button onclick="send()">send</button>

<div id="receiveMsg"></div>