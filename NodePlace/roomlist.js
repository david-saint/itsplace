/*!
 * live-diag
 * Copyright(c) 2011 hekyou <ukasy4@gmail.com>
 * MIT Licensed
 */

module.exports = new RoomList;

function RoomList() {
    this.room_list = [];
}
RoomList.prototype.getRoomList = function() {
	var array = new Array();
	 for (var key in this.room_list) {
		 array.push(this.room_list[key].name);
	    }
    return array;
};
RoomList.prototype.getRoom = function(room_name) {
    var room = null;

    for (var key in this.room_list) {
        if (this.room_list[key].name == room_name) {
            room = this.room_list[key];
            break;
        }
    }
    if (room === null) {
        room = new Room(room_name);
        this.room_list.push(room);
    }
    return room;
};

RoomList.prototype.getRoomCount = function() {
    return this.room_list.length;
};

RoomList.prototype.disconnectUser = function(client) {
    for (var room in this.room_list) {
        if (this.room_list[room].disconnectUser(client)) {
            if (this.room_list[room].user_list.length === 0) { //방안에 없으면 방도 삭제
                this.room_list.splice(room, 1);
            }
            return this.room_list[room];
        }
    }
};
RoomList.prototype.getuserList = function(client) {
    for (var room in this.room_list) {
        if (this.room_list[room].GetClient(client)) {
           return this.room_list[room].getUserList();           
        }
    }
};


RoomList.prototype.gc = function() {
    var expires = 2 * 3600000; // 2 hour
    var now = new Date().getTime();

    for (var room in this.room_list) {
        if ((now - room.lasttime) > expires) {
            this.room_list.splice(room, 1);
        }
    }
};

// Room Class

function Room(name) {
    this.name = name;
    this.lasttime = new Date().getTime();
    this.user_list = [];
    this.send_type_json = false;
    this.current_client_id = null;
}

Room.prototype.connectUser = function(client, name) {
    this.lasttime = new Date().getTime();
    this.current_client_id = client.id;

    for (var key in this.user_list) {
        if (this.user_list[key].id == client.id) {
            return;
        }
    }
    var user = new User(client,name);
    this.user_list.push(user);
};
Room.prototype.getUserList = function() {
	var array = new Array();
	for (var key in this.user_list) {
		array.push(this.user_list[key].name);
	}
    return array;
};
Room.prototype.disconnectUser = function(client) {
    for (var key in this.user_list) {
        if (this.user_list[key].id == client.id) {
            this.user_list.splice(key, 1);
            return true;
        }
    }
    return false;
};
Room.prototype.GetClient = function(client) {
    for (var key in this.user_list) {
        if (this.user_list[key].id == client.id) {
            
            return true;
        }
    }
    return false;
};
Room.prototype.send = function(data) {
    var key;

    if (this.send_type_json) {
        for (key in this.user_list) {
            if (this.user_list[key].id != this.current_client_id) {
                this.user_list[key].sendJson(data);
            }
        }
    }
    else {
        for (key in this.user_list) {
            if (this.user_list[key].id != this.current_client_id) {
                this.user_list[key].send(data);
            }
        }
    }
    this.send_type_json = false;
};
Room.prototype.sendAll = function(data) {
    var key;

    if (this.send_type_json) {
        for (key in this.user_list) {
                this.user_list[key].sendJson(data);
        }
    }
    else {
        for (key in this.user_list) {
                this.user_list[key].send(data);
        }
    }
    this.send_type_json = false;
};
//자신을 제외한 방안에 있는사람
Room.prototype.emit = function(emit, data) {
    var key;

    if (this.send_type_json) {
        for (key in this.user_list) {
            if (this.user_list[key].id != this.current_client_id) {
                this.user_list[key].emitJson(emit, data);
            }
        }
    }
    else {
        for (key in this.user_list) {
            if (this.user_list[key].id != this.current_client_id) {
                this.user_list[key].emit(emit, data);
            }
        }
    }
    this.send_type_json = false;
};
//자신과 방안에 있는사람
Room.prototype.emitAll = function(emit, data) {
    var key;

    if (this.send_type_json) {
        for (key in this.user_list) {
        	 this.user_list[key].emitJson(emit, data);
        }
    }
    else {
        for (key in this.user_list) {
        	this.user_list[key].emit(emit, data);
        }
    }
    this.send_type_json = false;
};
Room.prototype.json = function() {
    this.send_type_json = true;
    return this;
};

// User Class

function User(client, name) {
    this.client = client;
    this.id = client.id;
    this.name = name;
    this.lasttime = new Date().getTime();
}

User.prototype.send = function(data) {
    this.client.send(data);
};

User.prototype.sendJson = function(data) {
    this.client.json.send(data);
};

User.prototype.emit = function(emit, data) {
    this.client.emit(emit, data);
};

User.prototype.emitJson = function(emit, data) {
    this.client.json.emit(emit, data);
};
