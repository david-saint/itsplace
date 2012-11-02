var app = require('http').createServer(handler)
  , io = require('socket.io').listen(app)
  , fs = require('fs')

app.listen(8070);

 var mysql      = require('mysql');
var client = mysql.createClient({
  host     : 'db.jdpp.net',
  user     : 'faya',
  password : 'qp2apdx',
});

client.query('USE faya', function(error, results){
    if(error) {
        console.log("데이터베이스 접속 실패: " + error);
        return;
    }
    console.log("faya 데이터베이스에 접속하였습니다.");
    getData(client);
});
 
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


getData = function(client)
{
    client.query(
        "SELECT * FROM PUSER",
        function(error, results, fields) {
            if(error){
                console.log("데이터베이스 조회 실패: " + error);
                client.end();
                return;
            }
            // 무진장 많이 뜨는 거 보고싶으면 주석 해제하세요.
            //console.log('Results:');
            //console.log(results);
            //console.log('Field metadata:');
            //console.log(fields);
            //console.log(util.inspect(results));
  
            if(results.length > 0)
            {
                var firstResult = results[0];
                console.log('이름: ' + firstResult['EMAIL']);
              
            }
        }
    );
    client.end();
    console.log("연결이 닫혔습니다.");
}

//io.socket.emit // 전역
//
var room_list = require('./roomlist');
var places = new Array();
io.sockets.on('connection', function (socket) {
	//대기실"waitRoom"
	socket.on('PlaceOn', function(data) {
		var room = room_list.getRoom(data.room);
		console.log(data.name +" 님 대기실 입장:"+data.room);
		var userList = room.getUserList();
		var result = "";
		for(var i=0; i<userList.length; i++){

			if(userList[i] == data.name){
				//result = "같은 대화명이 존재합니다";
				//break;
			}
		}
		if(result == ""){		
        	room.connectUser(socket, data.name);
        	result =  "place On " + data.name + "님 대기실입장" ;
        	room.emitAll('PlaceOn',  { success: result, room: data.room });
        	room.emitAll('SetUserList', { userList: room.getUserList(), room: data.room } );
		}else{
			socket.emit('error',  { error: result });
		}
		
		
//		console.log(result);
		var isPlace = false;
		//places[data.fid] = data.fid;
		//room_list.getRoom(data.fid).connectUser(socket);
		
		//socket.join(data.fid);
		
		
		//socket.broadcast.emit('receiveMessage', 'dddddddddddddddddddddddddd');
		 
		//io.sockets.emit('news',{ hello: 'world' });
		 // io.sockets.in('/priv/'+data.userid).emit('ReceiveMsg', {userid:data.userid, message: data.message});
		
	});
	//방 유저리스트 
	socket.on('GetUserList', function(data) {
   
        var room = room_list.getRoom(data.room);
        var userList = room.getUserList();
        console.log("room name:" +data.room );
       	for(var i=0; i<userList.length; i++){
			console.log("유저목록:" + userList[i] );
		}

       // io.sockets.in('waitRoom').emit('SetUserList', room.getUserList() );
          room.emitAll('SetUserList',  { userList: room.getUserList(), room: data.room } );
    });
    //방변경
    socket.on('PlaceChange', function(data) {
    	
    	room_list.disconnectUser(socket);
    	var prevRoom = room_list.getRoom(data.prevRoom);
    	
//    	io.sockets.emit('SetUserList', { userList: prevRoom.getUserList(), room: data.room } );  //이전방 리프레시
    	prevRoom.emitAll('SetUserList', { userList: prevRoom.getUserList(), room: data.room } );  //이전방 리프레시
		
		var room = room_list.getRoom(data.room);		
		var userList = room.getUserList();
		var result = "";
		for(var i=0; i<userList.length; i++){

			if(userList[i] == data.name){
				//result = "같은 대화명이 존재합니다";
				//break;
			}
		}
		if(result == ""){		
        	room.connectUser(socket, data.name);
        	result =  "place On " + data.name + data.room +" 입장" ;
//        	socket.emit('PlaceOn',  { success: result, room: data.room });
        	room.emitAll('PlaceOn',  { success: result, room: data.room });
        	room.emitAll('SetUserList',{ userList: room.getUserList(), room: data.room } );
		}else{
			socket.emit('error',  { error: result });
		}
	
		
	});
	//룸전체목록
	socket.on('RoomList', function(req) {
		var roomList = room_list.getRoomList(); 
		for(var i=0; i<roomList.length; i++){
			console.log("방목록:" + roomList[i] );
		}
        io.sockets.emit('SetRoomList', roomList ); //이전방 리프레시
	});

	//룸별 사용자 카운트
	socket.on('UserCountByRoom', function(data) {
		//console.log("방목록:"+data.roomList);
		for(var i=0; i<data.roomList.length; i++){
			//console.log("방이름:"+data.roomList[i].fname + "("+data.roomList[i].fid +")");
			var room = room_list.getRoom(data.roomList[i].fname);
			if(room){
				var userList = room.getUserList();
				data.roomList[i].userCount = userList.length;
				console.log("방이름:"+data.roomList[i].fname + "("+data.roomList[i].userCount +")");
			}else{
				data.roomList[i].userCount = 0;
			}
		}
		io.sockets.emit('UserCountByRoom', data );		
        
	});
	
	socket.on('disconnect', function(req) {
		console.log("good bye:"+socket.id);
		
		var room = room_list.disconnectUser(socket);
		if(room){
			var userList = room.getUserList();
			if(userList){
				for(var i=0; i<userList.length; i++){
					console.log("유저목록:" + userList[i] );
				}
				room.emit('SetUserList', { userList: room.getUserList(), room: room.name } );
			}
		}
		
     
       
	});
	socket.on('message', function(req) {
		console.log("보내는사람:"+req.name);
        var room = room_list.getRoom(req.room);
       // room.connectUser(socket, req.name);
		io.sockets.emit('getUserList', room.getUserList() );
        room.json().sendAll({ name:req.name, comment: req.data });
    });
});

//deprecated
BroadRoomStatusUpdate = function(data)
{
   		for(var i=0; i<data.roomList.length; i++){
			//console.log("방이름:"+data.roomList[i].fname + "("+data.roomList[i].fid +")");
			var room = room_list.getRoom(data.roomList[i].fname);
			if(room){
				var userList = room.getUserList();
				data.roomList[i].userCount = userList.length;
				console.log("방이름:"+data.roomList[i].fname + "("+data.roomList[i].userCount +")");
			}else{
				data.roomList[i].userCount = 0;
			}
		}
		io.sockets.emit('UserCountByRoom', data );		
}
