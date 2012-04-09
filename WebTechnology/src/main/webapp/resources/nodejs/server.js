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
	
	socket.on('Join', function(data) {
		console.log("join recived------------------>:"+data.userid);
		//socket.broadcast.emit('news', { hello: 'world' });
		socket.join('/priv/'+data.userid);
		
	});
  
      socket.on('SendMessage', function(data) {
		console.log("SendMsg recived------------------>:"+data.message);
			console.log("SendMsg recived------------------>:"+data.userid);
		 io.sockets.in('/priv/'+data.userid).emit('ReceiveMsg', {userid:data.userid, message: data.message});
	});
 
 
});