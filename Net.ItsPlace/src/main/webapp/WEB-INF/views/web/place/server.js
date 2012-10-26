var app = require('http').createServer(handler)
  , io = require('socket.io').listen(app)
  , fs = require('fs')

app.listen(8090);

function handler (req, res) {
  fs.readFile(__dirname + '/index.html',
  function (err, data) {
    if (err) {
      res.writeHead(500);
      return res.end('Error loading index.html');
    }

    res.writeHead(200);
    res.end(data);
  });
}

io.sockets.on('connection', function (socket) {

	socket.on('roomJoin', function(data) {
	
		
		
		
		// socket.broadcast.emit('user message', 'dddddddddddddddddddddddddd');
		 
		io.sockets.emit('news',{ hello: 'world' });
		 // io.sockets.in('/priv/'+data.userid).emit('ReceiveMsg', {userid:data.userid, message: data.message});
		
	});
	socket.on('sendMessage', function(data) {
	console.log("roomJoin recived------------------>:"+data.room);
		socket.join(data.room)
		io.sockets.emit('news',{ hello: 'world' });
		io.sockets.in('data.room').emit('receiveMessage', {userId:data.userId,   message: data.message} );
		
		
	});
  
 
 
});