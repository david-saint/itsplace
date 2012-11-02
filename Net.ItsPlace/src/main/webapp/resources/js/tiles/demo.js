var socket = io.connect('http://localhost:8070');
socket.on('connect', function () {
    console.log("connected socket:"+$('#userName').val());
    socket.emit('PlaceOn', { room: 'waitRoom', name:$('#userName').val()});
});
socket.on("PlaceOn", function (data) {
	console.log("입장:"+data.room); 
	$('.message').last().after('<div class="message"><span>'+data.success + '</span></div>');
});
socket.on('SetUserList', function (data) {
  	
  	console.log("유저목록:"+data.room +"("+ data.userList.length+")");
    $('#onlineUsers').empty();
  	
  	 for(var i=0;i<data.userList.length;i++){
  		 console.log("user name:"+data.userList[i]);	       		
  		$('#onlineUsers').append('<div>'+data.userList[i]+'</div>');
  	 }
  	
});
socket.on('message', function(data) {
	console.log("message receive");
    if (data.comment) {
    	$('.message').last().after('<div class="message"><span>'+data.name + ":</span><span>" + data.comment+'</span></div>');
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
// The grid manages tiles using ids, which you can define. For our
// examples we'll just use the tile number as the unique id.
var TILE_IDS = [
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,26,27,28,29,30,31,32
];



// templates in JSON matching the predefined selections you can
// choose on the demo page
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

// SAMPLE #2
var grid;
var rows;
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
    var el = document.getElementById('sample2-grid'),
        grid = new Tiles.Grid(el);

    // template is selected by user, not generated so just
    // return the number of columns in the current template
    grid.resizeColumns = function() {
        return this.template.numCols;
    };

    // by default, each tile is an empty div, we'll override creation
    // to add a tile number to each div
    grid.createTile = function(tileId) {
    	console.log("3-생성:"+rows[tileId-1].fname);
        var tile = new Tiles.Tile(tileId);
       // tile.$el.append('<div class="dev-tile-number">' + tileId + 'y</div>');
        
     /*   tile.$el.live('click',function() {
        	 console.log($(this).html());
        	 $('#placeView').attr('src','/place/view/'+rows[tileId-1].fid);
        	 $('#jqmWindow').jqmShow();
        	 
        });*/
        
        //console.log("data:"+rows[tileId-1].fname);
        //console.log("data:"+rows[tileId-1].fileName);
//        img = "<a href=\"/place/view/"+rows[tileId-1].fid+"\" class=\"story-bg iframe fancy\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >";
        var html = new StringBuffer();
        html.append("<div id=\""+rows[tileId-1].fid+"\"  class=\"place-bg\"");
        html.append("style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >");
        html.append("<div class=\"place-view\"></div>");
        html.append("<div class=\"place-info\"><p class=\"placeName\">"+rows[tileId-1].fname+"</p>");
//        html.append("<span class=\"user-count placeOn\">On-"+rows[tileId-1].placeOn+"</span>");
        html.append("<span class=\"user-count placeOn\"></span>");
        html.append("</div>");
        html.append("</div>");
        //img = "<div id=\""+rows[tileId-1].fid+"\"  class=\"place-bg\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" ><div class=\"place-title\">"+rows[tileId-1].fname+"</div></div>";
 //       img += "<div class=\"place-title\">"+rows[tileId-1].fileName+"</div>";
  //      img += "</div>";
        tile.$el.append(html.toString());
       
        return tile;
    };

    grid.setRows = function(data) {
    	rows = data;
    };
    grid.getRows = function(data) {
    	return rows;
    };
    // update the template selection
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
    
    
    $.ajax({
        type: 'POST'
        , url: "/web/search/place"
        , success: function(data) {
        	//console.log(data);
        	console.log("count:"+data.result.length);
        	for(i=0;i<data.result.length;i++){
        		console.log( data.result[i].fname);
        	}
        	socket.emit('UserCountByRoom', { roomList:data.result});
        	grid.setRows (data.result);
        	grid.init(data.result.length);
        	
//        	$('.fancy').fancybox({
//					'autoDimensions':false,
//					'scrolling':'auto',
//					'autoScale':false,
//					'height':400,
//					'speedIn':10,
//					'speedOut':10,
//					'changeSpeed':10,
//					
//			});
        }
 		, error: function(data, status, err) {
 			console.log(data);
 		}
 		, complete: function() {
 			 $('.place-info').live('click',function(e) {
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
 			});
 		}
	});

    //채팅
   
    
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
    $('#search').live('keyup',function(){
		console.log("sssss:"+$("form").serialize());
		 $.ajax({
		        type: 'POST',
		        url: "/web/search/place",
		        data:$("form").serialize() ,
		        success: function(data) {		        	
		        	if(data.result.length>0){
		        		console.log("count:"+data.result.length);
		        		setTiles(data.result);
//		            	$('.fancy').fancybox({
//		    					'autoDimensions':false,
//		    					'scrolling':'auto',
//		    					'autoScale':false,
//		    					'height':400,
//		    					'speedIn':10,
//		    					'speedOut':10,
//		    					'changeSpeed':10,
//		    					
//		    			});
		        	}
		        	
		        },
		 		error: function(data, status, err) {
		 			console.log(data);
		 		},
		 		complete: function() {
		 		
		 		}
			});
	});
    
});

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
	 $('#sample2-grid').empty();
	    var el = document.getElementById('sample2-grid'),	    
	        grid = new Tiles.Grid(el);
	    grid.resizeColumns = function() {
	        return this.template.numCols;
	    };

	    grid.createTile = function(tileId) {
	    	console.log("생성:");
	        var tile = new Tiles.Tile(tileId);
	        tile.$el.append('<div class="dev-tile-number">' + tileId + 'y</div>');
	        tile.$el.live('click',function() {
	        	 console.log($(this).html());
	        });
	       // console.log("data:"+rows[tileId-1].fname);
	       // console.log("data:"+rows[tileId-1].fileName);
	        var  html = new StringBuffer();
	        
	        html.append("<a href=\"\" class=\"story-bg iframe fancy\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >");
	        html.append("</a>");
	        tile.$el.append(html.toString());
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
	    var debouncedResize = debounce(function() {
	        grid.resize();
	        grid.redraw(true);
	    }, 200);
	    
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