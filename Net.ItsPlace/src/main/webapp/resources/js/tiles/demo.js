//node socket.io 
var socket = io.connect('http://localhost:8070');
socket.on('connect', function () {
    console.log("connected socket:"+$('#userName').val());
    socket.emit('PlaceOn', { room: 'waitRoom', name:$('#userName').val()});
});
socket.on("PlaceOn", function (data) {
	console.log("입장:"+data.room); 
	if(data.room!="waitRoom"){
		$('#roomTitle').text(data.room);	
		$('.message').last().after('<div class="message"><span>'+data.success + '</span></div>');
	}
});
socket.on('SetUserList', function (data) {
  	
  //	console.log("유저목록:"+data.room +"("+ data.userList.length+")");
	if(data.room!="waitRoom"){
		$('#onlineUsers').empty();
	  	 for(var i=0;i<data.userList.length;i++){
	  		 //console.log("user name:"+data.userList[i]);	  
	  		 
	  		$('#onlineUsers').append('<div>'+data.userList[i]+'</div>');
	  	 }
	}
});
socket.on('message', function(data) {
	console.log("message receive");
    if (data.comment) {
    	$('.message').last().after('<div class="message"><span class="name">'+data.name + " : </span><span>" + data.comment+'</span></div>');
    	$('#chatContainer').animate({scrollTop:999999}, 'slow');
    }
});
socket.on('UserCountByRoom', function(data) {
	console.log(data);
	for(i=0;i<data.roomList.length;i++){
		console.log("UserCountByRoom:"+data.roomList[i].fname+"count:"+data.roomList[i].userCount);
		//console.log("UserCountByRoom:"+data.roomList[i].fname);
		$('#'+data.roomList[i].fid).find('.user-count').text("Place On-"+data.roomList[i].userCount);
		//$('#'+data.roomList[i].fid).remove();
	}
    
});
socket.on('error', function(data) {
	console.log("ddd");
	if (data.error) {
		alert(data.error);
	}
});

//Tiles 
var TILE_IDS = [
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,26,27,28,29,30,31,32
];

var DemoTemplateRows = [
                        [
                            " . . . . . . . . ",
                            " A A B B C C . .",
                            " . . . . . . . .",
                            " D D E E F F . .",
                            " . . . . . . . .",
                            " . . B B . . . ."
                        ], [
                            " A A A A A A ",
                            " B B C C D D ",
                            " B B C C D D ",
                            " B B C C D D "
                        ], [
                            " A A B B . ",
                            " A A B B . ",
                            " A A C C . ",
                            " . . . . ."
                        ], [
                            " A A . . ",
                            " A A . . ",
                            " B B . . ",
                            " C C . .",
                            " C C . ."
                        ], [
                            " A A ",
                            " B B ",
                            " C C ",
                            " . . ",
                            " A A ",
                            " . . "
                        ]
                    ];

//var grid;
//var rows;
var isMobile;
$(function() {
	$('#btnMessage').live('click',function(){	
		 //socket.emit('sendMessage', { room: $('#room').val(),userId: $('#userId').val(), message: $('#message').val()});
		 socket.json.send({ room: $('#currentRoom').val(), name:$('#userName').val(), data: $('#message').val() });
		 
		// $('#content').append('<p style="color:blue;">'+$('#userId').val() +": " +$('#message').val()+'<p>');
	 });
	 $('#message').keyup(function (event) {
			var keyCode = (event.which) ? event.which : event.keyCode;
			console.log("키코드:"+keyCode);
			if(keyCode==13){
				socket.json.send({ room: $('#currentRoom').val(), name:$('#userName').val(), data: $('#message').val() });
				
			}
	 });
  /*  var el = document.getElementById('sample2-grid'),
        grid = new Tiles.Grid(el);

  
    grid.resizeColumns = function() {
        return this.template.numCols;
    };

    grid.createTile = function(tileId) {
    	console.log("3-생성:"+rows[tileId-1].fname);
        var tile = new Tiles.Tile(tileId);
        tile.$el.append(makePlace(rows,tileId));
       
        return tile;
    };

    grid.setRows = function(data) {
    	rows = data;
    };
    grid.getRows = function(data) {
    	return rows;
    };
    
    grid.init = function(rowCount) {
        // get the JSON rows for the selection
    	if($(window).width()<=768){
    		templateRows = DemoTemplateRows[4];
    	}else{
    		templateRows = DemoTemplateRows[0];
    	}
    	
        // set the new template and resize the grid
        grid.template = Tiles.Template.fromJSON(templateRows);  
        grid.isDirty = true;
        grid.resize();

        // adjust number of tiles to match selected template
//        var ids = TILE_IDS.slice(0, grid.template.rects.length);
        var ids = TILE_IDS.slice(0, rowCount);
        console.log("2--ids:"+ids);
        grid.updateTiles(ids);
        grid.redraw(true);
    };

    // make the initial selection
   
    
    // wait until users finishes resizing the browser
    var debouncedResize = debounce(function() {
    	//console.log("resize:"+$(window).width());
    	var width = $(window).width();
    	//$('#jqmWindow').css("width",width+"px");
    	console.log("윈도우 리사이징");
    		grid.resize();
            grid.redraw(true);
            
            if(width<=768){
            	console.log("모바일");
            	isMobile = true;
            }
            if(width>768){
            	isMobile = false;
            	
            }
    	   
    }, 200);
    
    
    // when the window resizes, redraw the grid
    $(window).resize(debouncedResize);
    
    */
    $.ajax({
        type: 'POST'
        , url: "/web/search/place"
        , success: function(data) {
        	//console.log(data);
        	/*console.log("count:"+data.result.length);
        	for(i=0;i<data.result.length;i++){
        		console.log( data.result[i].fname);
        	}*/
        	socket.emit('UserCountByRoom', { roomList:data.result}); // place 채팅 카운트를 서버에 요청 표시
        	//grid.setRows (data.result);
        	//grid.init(data.result.length);
        	setTiles(data.result);
        }
 		, error: function(data, status, err) {
 			console.log(data);
 		}
 		, complete: function() {
 			 /*$('.place-info').live('click',function(e) {
 				socket.emit('PlaceChange', { prevRoom:$('#currentRoom').val(), room: $(this).find('p').text() ,'name':$('#userName').val()});
 				
 				console.log("방변경:"+ $('#currentRoom').val() +"--->"+$(this).find('p').text());
 				$('#currentRoom').val($(this).find('p').text());
 				socket.emit('UserCountByRoom', { roomList:grid.getRows()});
 				$('#placeOn').show();
 	        });
 			$('.place-view').live('click',function(e) {
 				 $('#placeView').attr('src','/place/view/'+$(this).parent().attr('id'));
 	        	 $('#jqmWindow').jqmShow();
 			});
 			 $('#chatClose').live('click',function(){
 				 //socket.disconnect();
 				 $('#placeOn').hide();
 				 socket.emit('PlaceChange', { prevRoom:$('#currentRoom').val(), room: 'waitRoom' ,'name':$('#userName').val()});
 				console.log("방변경:"+ $('#currentRoom').val() +"--->대길실로");
 				 socket.emit('UserCountByRoom', { roomList:grid.getRows()});
 				 $('#currentRoom').val('waitRoom');
 			});*/
 		}
	});

    $('#search').live('keyup',function(){
		console.log("sssss:"+$("form").serialize());
		 $.ajax({
		        type: 'POST',
		        url: "/web/search/place",
		        data:$("form").serialize() ,
		        success: function(data) {		        	
		        	if(data.result.length>0){
		        		console.log("ajax 콜 성공 count:"+data.result.length);
		        		//grid.setRows (data.result);
		            	//grid.init(data.result.length);
		        		socket.emit('UserCountByRoom', { roomList:data.result}); // place 채팅 카운트를 서버에 요청 표시
		        		setTiles(data.result);

		        	}
		        	
		        },
		 		error: function(data, status, err) {
		 			console.log(data);
		 		},
		 		complete: function() {
		 		}
			});
	});


    //미디어 쿼리
    enquire.register("screen and (max-width:468px)", {

	    match : function() {
	    	if(isMobile){
	    		console.log("match 468px");
		        templateRows = DemoTemplateRows[4];
		        grid.template = Tiles.Template.fromJSON(templateRows);	       
		        grid.isDirty = true;
		        grid.resize();
		       /// console.log("매치:"+grid.template.rects.length);
		       // var ids = TILE_IDS.slice(0, grid.template.rects.length);        
		      //  grid.updateTiles(ids);
		        grid.redraw(true);
	    	}
	        
	    },
	    unmatch : function() {
	    	if(!isMobile){
	        console.log("unmatch 768px");
	        templateRows = DemoTemplateRows[2];
	        grid.template = Tiles.Template.fromJSON(templateRows);	       
	        grid.isDirty = true;
	        grid.resize();
	      //  var ids = TILE_IDS.slice(0, grid.template.rects.length);        
	       // grid.updateTiles(ids);
	        grid.redraw(true);
	    	}
	    } 
    }).register("screen and (max-width:900px)", {
	    match : function() {
	    	if(!isMobile){
		        console.log("match 900px");
		        templateRows = DemoTemplateRows[3];
		        grid.template = Tiles.Template.fromJSON(templateRows);	       
		        grid.isDirty = true;
		        grid.resize();
		        grid.redraw(true);
	    	}
	    },
	}).register("screen and (max-width:1100px)", {

		  match : function() {
			  
			  if(!isMobile){
				  console.log("match 1100px");
				  templateRows = DemoTemplateRows[2];
			      grid.template = Tiles.Template.fromJSON(templateRows);	       
			      grid.isDirty = true;
			      grid.resize();		       
			      grid.redraw(true);
			  }
		        
		    },
		    unmatch : function() {
		    	 console.log(isMobile);
		    	  
				  if(!isMobile){
					  console.log("unmatch 1100px");
					  templateRows = DemoTemplateRows[0];
				      grid.template = Tiles.Template.fromJSON(templateRows);	       
				      grid.isDirty = true;
				      grid.resize();		       
				      grid.redraw(true);
				  }
		    }
	}).listen();
    
  /*  var query = "screen and (max-width:1000px)";
    var query2 = "screen and (max-width:800px)";

    enquire.register(query, {
        match : function() {
            console.log("handler 1000px matched");
        }
    }).listen();
    enquire.register(query, {
        match : function() {
            console.log("handler 800px matched");
        }
    }).listen();*/
});//function ready end

var debounce = function(func, wait, immediate) {
    var timeout;
    return function() {
      var context = this, args = arguments;
      var later = function() {
        timeout = null;
        if (!immediate) func.apply(context, args);
      };
      if (immediate && !timeout) func.apply(context, args);
      clearTimeout(timeout);
      timeout = setTimeout(later, wait);
    };
};
  
 
 function setTiles(rows){
	 //this.rows = rows;
	
	 $('#sample2-grid').empty();
	    var el = document.getElementById('sample2-grid'),	    
	    grid = new Tiles.Grid(el);
	    grid.resizeColumns = function() {
	        return this.template.numCols;
	    };

	    grid.createTile = function(tileId) {
	    	 var tile = new Tiles.Tile(tileId);
	         
	         tile.$el.append(makePlace(rows,tileId));
	        
	         return tile;
	    };

	  
	    	 
	    	templateRows = DemoTemplateRows[0];
	        // set the new template and resize the grid
	        grid.template = Tiles.Template.fromJSON(templateRows);  
	        grid.isDirty = true;
	        grid.resize();

	        // adjust number of tiles to match selected template
//	        var ids = TILE_IDS.slice(0, grid.template.rects.length);
	        var ids = TILE_IDS.slice(0, rows.length);
	        grid.updateTiles(ids);
	        grid.redraw(true);
	    

	    // make the initial selection
	   
	    
	    // wait until users finishes resizing the browser
	  /*  var debouncedResize = debounce(function() {
	        grid.resize();
	        grid.redraw(true);
	    }, 200);*/
	    var debouncedResize = debounce(function() {
	    	//console.log("resize:"+$(window).width());
	    	var width = $(window).width();
	    	//$('#jqmWindow').css("width",width+"px");
	    	console.log("윈도우 리사이징");
	    		grid.resize();
	            grid.redraw(true);
	            
	            if(width<=768){
	            	console.log("모바일");
	            	isMobile = true;
	            }
	            if(width>768){
	            	isMobile = false;
	            	
	            }
	    	   
	    }, 200);
	    
	    
	    // when the window resizes, redraw the grid
	    $(window).resize(debouncedResize);
	    $('.place-info').die();
		$('.place-info').live('click',function(e) {
			socket.emit('PlaceChange', { prevRoom:$('#currentRoom').val(), room: $(this).find('p').text() ,'name':$('#userName').val()});
			
			//console.log("방변경:"+ $('#currentRoom').val() +"--->"+$(this).find('p').text());
			$('#currentRoom').val($(this).find('p').text());
			//socket.emit('UserCountByRoom', { roomList:grid.getRows()});
			socket.emit('UserCountByRoom', { roomList:rows});
			$('#placeOn').show();
    });
		$('.place-view').die();
	$('.place-view').live('click',function(e) {
		 $('#placeView').attr('src','/place/view/'+$(this).parent().attr('id'));
		 $('#jqmWindow').jqmShow();
	});
	$('#chatClose').die();
	$('#chatClose').live('click',function(){
		 //socket.disconnect();
		 $('#placeOn').hide();
		 socket.emit('PlaceChange', { prevRoom:$('#currentRoom').val(), room: 'waitRoom' ,'name':$('#userName').val()});
		 //console.log("방변경:"+ $('#currentRoom').val() +"--->대길실로");
//		 socket.emit('UserCountByRoom', { roomList:grid.getRows()});
		 socket.emit('UserCountByRoom', { roomList:rows});
		 $('#currentRoom').val('waitRoom');
	});

 }
 
function makePlace(rows, tileId){
	 var html = new StringBuffer();
     html.append("<div id=\""+rows[tileId-1].fid+"\"  class=\"place-bg\"");
     html.append("style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >");
     html.append("<div class=\"place-view\"></div>");
     html.append("<div class=\"place-info\"><p class=\"placeName\">"+rows[tileId-1].fname+"</p>");
     html.append("<span class=\"user-count placeOn\"></span>");
     html.append("</div>");
     html.append("</div>");
     return html.toString();
} 

function StringBuffer() {
	 this.buffer = [];
} 
StringBuffer.prototype.append = function append(string) {   
	 this.buffer.push(string);
	 return this;
};
StringBuffer.prototype.toString = function toString() {
	return this.buffer.join(""); 
};   