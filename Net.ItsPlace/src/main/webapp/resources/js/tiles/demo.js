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
                            " A A A B B B ",
                            " A A A B B B ",
                            " A A A C C . ",
                            " . . . . . ."
                        ]
                    ];

// SAMPLE #2
var grid;
var rows;
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
    	console.log("생성:");
        var tile = new Tiles.Tile(tileId);
        tile.$el.append('<div class="dev-tile-number">' + tileId + 'y</div>');
        
        tile.$el.live('click',function() {
        	 console.log($(this).html());
        	 $('#placeView').attr('src','/place/view/'+rows[tileId-1].fid);
        	 $('#jqmWindow').jqmShow();
        	 
        });
        
        console.log("data:"+rows[tileId-1].fname);
        console.log("data:"+rows[tileId-1].fileName);
//        img = "<a href=\"/place/view/"+rows[tileId-1].fid+"\" class=\"story-bg iframe fancy\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >";
        img = "<div  class=\"story-bg\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >";
        img += "</div>";
        tile.$el.append(img);
        return tile;
    };

    grid.setRows = function(data) {
    	rows = data;
    };
    // update the template selection
    grid.init = function(rowCount) {
        // get the JSON rows for the selection
    	 
    	templateRows = DemoTemplateRows[0];
        // set the new template and resize the grid
        grid.template = Tiles.Template.fromJSON(templateRows);  
        grid.isDirty = true;
        grid.resize();

        // adjust number of tiles to match selected template
//        var ids = TILE_IDS.slice(0, grid.template.rects.length);
        var ids = TILE_IDS.slice(0, rowCount);
        console.log("ids:"+ids);
        grid.updateTiles(ids);
        console.log("111111111111");
        grid.redraw(true);
        console.log("222222222");
    };

    // make the initial selection
   
    
    // wait until users finishes resizing the browser
    var debouncedResize = debounce(function() {
    	//console.log("resize:"+$(window).width());
    	//var width = $(window).width() - 200;
    	//$('#jqmWindow').css("width",width+"px");
        grid.resize();
        grid.redraw(true);
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

    enquire.register("screen and (max-width:1000px)", {

	    match : function() {
	        console.log("handler max-width 1000px");
	        templateRows = DemoTemplateRows[1];
	        grid.template = Tiles.Template.fromJSON(templateRows);  
	        grid.isDirty = true;
	        grid.resize();
	        var ids = TILE_IDS.slice(0, grid.template.rects.length);        
	        grid.updateTiles(ids);
	        grid.redraw(true);
	    },
	 	unmatch : function() {
	        console.log("unmatch 1000px");
	        templateRows = DemoTemplateRows[0];
	        grid.template = Tiles.Template.fromJSON(templateRows);  
	        grid.isDirty = true;
	        grid.resize();
	        var ids = TILE_IDS.slice(0, grid.template.rects.length);        
	        grid.updateTiles(ids);
	        grid.redraw(true);
	    }
 
	}).listen();
    
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

	    // template is selected by user, not generated so just
	    // return the number of columns in the current template
	    grid.resizeColumns = function() {
	        return this.template.numCols;
	    };

	    // by default, each tile is an empty div, we'll override creation
	    // to add a tile number to each div
	    grid.createTile = function(tileId) {
	    	console.log("생성:");
	        var tile = new Tiles.Tile(tileId);
	        tile.$el.append('<div class="dev-tile-number">' + tileId + 'y</div>');
	        tile.$el.live('click',function() {
	        	 console.log($(this).html());
	        });
	        console.log("data:"+rows[tileId-1].fname);
	        console.log("data:"+rows[tileId-1].fileName);
	        img = "<a href=\"\" class=\"story-bg iframe fancy\" style=\"background-image: url(http://itsplace.sungwon-it.com/img/"+ rows[tileId-1].fileName + ")\" >";
	        img += "</a>";
	        tile.$el.append(img);
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
  
  