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
        
        tile.$el.live('click',function() {
        	 console.log($(this).html());
        	 $('#placeView').attr('src','/place/view/'+rows[tileId-1].fid);
        	 $('#jqmWindow').jqmShow();
        	 
        });
        
        //console.log("data:"+rows[tileId-1].fname);
        //console.log("data:"+rows[tileId-1].fileName);
//        img = "<a href=\"/place/view/"+rows[tileId-1].fid+"\" class=\"story-bg iframe fancy\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >";
        img = "<div fid=\""+rows[tileId-1].fid+"\"  class=\"place-bg\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" ><div class=\"place-title\">"+rows[tileId-1].fname+"</div></div>";
 //       img += "<div class=\"place-title\">"+rows[tileId-1].fileName+"</div>";
  //      img += "</div>";
        tile.$el.append(img);
        return tile;
    };

    grid.setRows = function(data) {
    	rows = data;
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
 		
 		}
	});

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