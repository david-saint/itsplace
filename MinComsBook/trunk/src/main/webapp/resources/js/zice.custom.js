$.fn.imgdata = function(key){
	return this.find('.dataImg li:eq('+key+')').text();
}
$.fn.hdata = function(key){
	return this.find('.dataSet li:eq('+key+')').text();
}
var buttonActions = {
	  'close_windows':function(){
		  $.fancybox.close(); 
		  ResetForm();
	}	
}




$(function() {		
	LResize();	
	$(window).resize(function(){LResize(); Processgraph(); });
    $(window).scroll(function (){ scrollmenu(); });
		
	  
	  
  	//datepicker
	$("input.datepicker").datepicker({ 
		autoSize: true,
		//appendText: '(yy-mm-dd)',
		dateFormat: 'yy-mm-dd'
	});
	$( "div.datepickerInline" ).datepicker({ 
		dateFormat: 'dd-mm-yy',
		numberOfMonths: 1
	});	
	$( "input.birthday" ).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat:'yy-mm-dd'
    });
	

	//datetimepicker
   $("#datetimepicker").datetimepicker();
   $('#timepicker').timepicker({});
  

	//Button Click  Ajax Loading
	$('.loading').live('click',function() { 
		/*
		  var str=$(this).attr('title'); 
		  var overlay=$(this).attr('rel'); 
		  loading(str,overlay);
		  setTimeout("unloading()",1500);*/ 
	  });
	$('#preloader').live('click',function(){
			unloading();
	 });
	

$('.searchAutocomplete').click(function() {
 $('.searchText').toggle('slow', function() {
    // Animation complete.
  });
});
	
	 
	// Tipsy Tootip
	$('.tip a ').tipsy({gravity: 's',live: true, offset:13});	
	$('.ntip a ').tipsy({gravity: 'n',live: true});	
	$('.wtip a ').tipsy({gravity: 'w',live: true});	
	$('.etip a,.Base').tipsy({gravity: 'e',live: true});	
	$('.netip a ').tipsy({gravity: 'ne',live: true});	
	$('.nwtip a , .setting ').tipsy({gravity: 'nw',live: true});	
	$('.swtip a,.iconmenu li a ').tipsy({gravity: 'sw',live: true});	
	$('.setip a ').tipsy({gravity: 'se',live: true});	
	$('.wtip input').tipsy({ trigger: 'focus', gravity: 'w',live: true });
	$('.etip input').tipsy({ trigger: 'focus', gravity: 'e',live: true });
	$('.iconBox, div.logout').tipsy({gravity: 'ne',live: true });	


	
	
	
	// Sortable
	$("#picThumb").sortable({
		opacity: 0.6,handle : '.move', connectWith: '.picThumbUpload', items: '.picThumbUpload'
	});
	$("#main_menu").sortable({
		opacity: 0.6,connectWith: '.limenu',items: '.limenu'		
	});
	$( "#sortable" ).sortable({
		opacity: 0.6,revert: true,cursor: "move", zIndex:9000
	});
	

    // Effect 
	$('.SEclick, .SEmousedown, .SEclicktime,.SEremote,.SEremote2,.SEremote3,.SEremote4').jrumble();
	$('.SE').jrumble({
		x: 2,
		y: 2,
		rotation: 1
	});
	
	$('.alertMessage.error ').jrumble({
		x: 10,
		y: 10,
		rotation: 4
	});
	
	$('.alertMessage.success').jrumble({
		x: 4,
		y: 0,
		rotation: 0
	});
	
	$('.alertMessage.warning').jrumble({
		x: 0,
		y: 0,
		rotation: 5
	});

	$('.SE').hover(function(){
		$(this).trigger('startRumble');
	}, function(){
		$(this).trigger('stopRumble');
	});

	$('.SEclick').toggle(function(){
		$(this).trigger('startRumble');
	}, function(){
		$(this).trigger('stopRumble');
	});
	
	$('.SEmousedown').bind({
		'mousedown': function(){
			$(this).trigger('startRumble');
		},
		'mouseup': function(){
			$(this).trigger('stopRumble');
		}
	});
	
	$('.SEclicktime').click(function(){
		var demoTimeout;
		$this = $(this);
		clearTimeout(demoTimeout);
		$this.trigger('startRumble');
		demoTimeout = setTimeout(function(){$this.trigger('stopRumble');}, 1500)
	});
	$('.SEremote').hover(function(){
		$('.SEremote2').trigger('startRumble');
	}, function(){
		$('.SEremote2').trigger('stopRumble');
	});
	
	$('.SEremote2').hover(function(){
		$('.SEremote').trigger('startRumble');
	}, function(){
		$('.SEremote').trigger('stopRumble');
	})

	$('.SEremote3').hover(function(){
		$('.alertMessage').trigger('startRumble');
	}, function(){
		$('.alertMessage').trigger('stopRumble');
	})

	$('.SEremote4').hover(function(){
		$('.alertMessage.error').trigger('startRumble');
	}, function(){
		$('.alertMessage.error').trigger('stopRumble');
	})


	// Dual select boxes
	$.configureBoxes();
	
	// Textareaelastic
	$('#Textareaelastic').elastic();
	
	// Textarea limit
	 $('#Textarealimit').limit('140','.limitchars');
	
	// placeholder text 
	$('input[placeholder], textarea[placeholder]').placeholder();
	
	// Checkbox 
	$('.ck,.chkbox,.checkAll ,input:radio').customInput();	
	
	// Checkbox Limit
	$('.limit3m').limitInput({max:3,disablelabels:true});
	
	// Select boxes
	$(function() {
        $(' select').not("select.chzn-select,select[multiple],select#box1Storage,select#box2Storage").selectmenu({
            style: 'dropdown',
            transferClasses: true,
            width: null
        });
    });

	// Select boxes in Data table
	$(".dataTables_wrapper .dataTables_length select").addClass("small");
	$("table tbody tr td:first-child .custom-checkbox:first-child").css("margin: 0px 3px 3px 3px");
	
	 // Mutiselection
	$(".chzn-select").chosen(); 
	
	// Checkbox iphoneStyle
	$(".on_off_checkbox").iphoneStyle();  // Label On / Off
	
	$(".show_email").iphoneStyle({  //  Custom Label 
		  checkedLabel: "show Email",
		  uncheckedLabel: "Don't show ",
		  labelWidth:'85px'
	}); 
	$(".preOrder").iphoneStyle({  //  Custom Label 
		  checkedLabel: "in stocks",
		  uncheckedLabel: "out stocks",
		  labelWidth:'76px'
	}); 
	$(".online").iphoneStyle({  //  Custom Label 
		  checkedLabel: "online",
		  uncheckedLabel: "offline ",
		  labelWidth:'55px'
	}); 
	$(".show_conmap").iphoneStyle({ //  Custom Label  With  onChange function
		  checkedLabel: "show map",
		  uncheckedLabel: "Don't show ",
		  labelWidth:'85px',
		  onChange: function() {
				var chek=$(".show_conmap").attr('checked');
					  if(chek){
						  $(".disabled_map").fadeOut();
					  }else{
						 $(".disabled_map").fadeIn();
					  }
		}
	});


	// Checkbox  All in Data Table
	$(".checkAll").live('click',function(){
		  var table=$(this).parents('table').attr('id');
		  var checkedStatus = this.checked;
		  var id= this.id;
		 $( "table#"+table+" tbody tr td:first-child input:checkbox").each(function() {
			this.checked = checkedStatus;
				if (this.checked) {
					$(this).attr('checked', $('.' + id).is(':checked'));
					$('label[for='+$(this).attr('id')+']').addClass('checked ');
				}else{
					$(this).attr('checked', $('.' + id).is(''));
					$('label[for='+$(this).attr('id')+']').removeClass('checked ');
					}
		});	 
	});		
	
	
	// ShowCode 
	$('.showCode').sourcerer('js html css php'); 
	$('.showCodeJS').sourcerer('js'); 
	$('.showCodeHTML').sourcerer('html'); 
	$('.showCodePHP').sourcerer('php'); 
	$('.showCodeCSS').sourcerer('css'); 
	
	// icon  gray Hover
	$('.iconBox.gray').hover(function(){
		  var name=$(this).find('img').attr('alt');
		  $(this).find('img').animate({ opacity: 0.5 }, 0, function(){
			    $(this).attr('src','/resources/admin/images/icon/color_18/'+name+'.png').animate({ opacity: 1 }, 700);									 
		 });
	},function(){
		  var name=$(this).find('img').attr('alt');
		  $(this).find('img').attr('src','/resources/admin/images/icon/gray_18/'+name+'.png');
	 })
	
	// Animation icon  Logout 
	$('div.logout').hover(function(){
		  var name=$(this).find('img').attr('alt');
		  $(this).find('img').animate({ opacity: 0.4 }, 200, function(){
			    $(this).attr('src','/resources/admin/images/'+name+'.png').animate({ opacity: 1 }, 500);									 
		 });
	},function(){
		  var name=$(this).find('img').attr('name');
		  $(this).find('img').animate({ opacity: 0.5 }, 200, function(){
			    $(this).attr('src','/resources/admin/images/'+name+'.png').animate({ opacity: 1 }, 500);									 
		 });
	 })
	
	// Animation icon  setting 
	$('div.setting').hover(function(){
		$(this).find('img').addClass('gearhover');
	},function(){
		$(this).find('img').removeClass('gearhover');
	 })
	
	// shoutcutBox   Hover
	$('.shoutcutBox').hover(function(){
		  $(this).animate({ left: '+=15'}, 200);
	},function(){
		$(this).animate({ left: '0'}, 200);
	 })
	
	// shoutcutBox   Hover
	$("#shortcut li").hover(function() {
		  var e = this;
		$(e).find("a").stop().animate({ marginTop: "-7px" }, 200, function() {
		  $(e).find("a").animate({ marginTop: "-5px" }, 200);
		});
	  },function(){
		  var e = this;
		$(e).find("a").stop().animate({ marginTop: "2px" }, 200, function() {
			  $(e).find("a").animate({ marginTop: "0px" }, 200);
		});
	  });
	
	

	// hide notify  Message with click
	$('#alertMessage').live('click',function(){
	  $(this).stop(true,true).animate({ opacity: 0,right: '-20'}, 500,function(){ $(this).hide(); });						 
	});
	
	// jScrollPane  Overflow
	$('#albumsList,.albumpics,.overflow,.todate').jScrollPane({ autoReinitialise: true });

	// images hover
	$('.picHolder,.SEdemo').hover(
		  function() {
			  $(this).find('.picTitle').fadeTo(200, 1);
		  },function() {
			  $(this).find('.picTitle').fadeTo(200, 0);
		  }
	  )	
		  
				  
	//droppable 			   	
	// move images  to  news album
	$('.album').droppable({
		hoverClass: 'over',
		activeClass: 'dragging',
		drop:function(event,ui){
			
			 if($(this).hasClass('selected')) return false;
			loading('Moving');
			
			ui.helper.fadeOut(400);
			setTimeout("unloading()",1500); 		

		},
		tolerance:'pointer'
	});
	  $('.picPreview').droppable({
		  hoverClass: 'picPreview-hover',
		  activeClass: 'picPreview-hover',
		   drop: function( event, ui ) { 
			   $('#image-albumPreview').attr('src',ui.draggable.find('img').attr('src'));
		   }
	});	
  	$('.deletezone').droppable({
		hoverClass: 'deletezoneover',
		activeClass: 'deletezonedragging',
		drop:function(event,ui){	

		   var data ='id='+ ui.draggable.imgdata(0); 
		   var name =ui.draggable.imgdata(1); 

		$.confirm({
		'title': 'DELETE DIALOG BOX','message': "<strong>YOU WANT TO DELETE </strong><br /><font color=red>' "+ name +" ' </font> ",'buttons': {'Yes': {'class': 'special',
		'action': function(){
					loading('Deleting',1);
						   ui.helper.fadeOut(function(){ ui.helper.remove(); 
						  setTimeout("unloading()",1500); 	
					});
			}},'No'	: {'class'	: ''}}});
		},
		tolerance:'pointer'
	});
	$('.album.load').live('click',function(e){
		  $('.album').removeClass('selected');
		  var albumid=$(this).attr('id');
		  $(this).addClass('selected');
		  loadalbum(albumid);
	});
	$(".albumDelete").live('click',function() { 
		   var dataSet=$(this).parents('form');
		   var name = $(this).attr("name");
		   var data ='id='+ $(this).attr("id");   
		   albumDelete(data,name,dataSet);
	});
	$('#editAlbum.editOn').live('click',function(){
												   
	$('.album_edit').fadeIn(400);
	$('.boxtitle').css({'margin-left':'207px'});
	$('.boxtitle .texttip').hide();
		$(this).html('close edit').attr('title','Click here to Close edit  ').removeClass('editOn').addClass('editOff');
		imgRow();
	});
	$('#editAlbum.editOff').live('click',function(){			
												   
		$('.album_edit').fadeOut(400,function(){
		$('.boxtitle .texttip').show();
				 $('.boxtitle').css({'margin-left':'0'});
				 imgRow();
		});
		$(this).html('edit album').attr('title','Click here to edit  Album ').removeClass('editOff').addClass('editOn');
		 
	})
	
	// mouseenter Over album with  CSS3
	$(".preview").delegate('img', 'mouseenter', function() {
		  if ($(this).hasClass('stackphotos')) {
		  var $parent = $(this).parent();
		  $parent.find('img#photo1').addClass('rotate1');
		  $parent.find('img#photo2').addClass('rotate2');
		  $parent.find('img#photo3').addClass('rotate3');
		  }
	  }).delegate('img', 'mouseleave', function() {
		  $('img#photo1').removeClass('rotate1');
		  $('img#photo2').removeClass('rotate2');
		  $('img#photo3').removeClass('rotate3');
	});
	
	// Conversation box tool
	$('.msg').live({
        mouseenter: function(){
			$(this).find('.toolMsg').show();
           },mouseleave: function(){
			$(this).find('.toolMsg').hide();
           }
       }
    );
	

	// filemanager. 
	// onload
	$('#finder').elfinder({
		url : 'components/elfinder/connectors/php/connector-fileimport.php',
		docked : true,dialog : { title : 'File manager',modal : true,width : 700 }
	})
	// on click
	$('#filemanager').click(function(){	
		  var callback=$(this).attr('id');
		  var type=$(this).attr('title');
		  fileDialog(callback,type);					   
	});
	// on click callback
	$('#open_icon,#open_icon2,#open_icon3').click(function(){	
		  var callback=$(this).attr('id');
		  var type=$(this).attr('title');
		   var input=$(this).attr('rel');
		  fileDialogCallback(callback,type,input);					   
	});
	// on focus  callback
	$('.fileDialog').live('focus',function(){
		  var callback,input =$(this).attr('id');
		  var type=$(this).attr('title');
		  fileDialogCallback(callback,type,input);										
	})


	// Confirm Delete.
	$(".Delete").live('click',function() { 
		  var row=$(this).parents('tr');
		  var dataSet=$(this).parents('form');
		  var id = $(this).attr("id");
		  var name = $(this).attr("name");
		  var data ='id='+id;
		  Delete(data,name,row,0,dataSet);
	});
	$(".DeleteAll").live('click',function() {			
		  var rel=$(this).attr('rel');	
		  var row=$(this).parents('.tab_content').attr('id');	
		  var row=row+' .load_page ';
		  if(!rel) { 
			  var rel=0;
			  var row=$('#load_data').attr('id');	 
		  }  
		  var dataSet=$('form:eq('+rel+')');					   
		  var	data=$('form:eq('+rel+')').serialize();
		  var name = 'All File Select';
		 Delete(data,name,row,2,dataSet);
	});
	
	
	// Overlay form
	$(".on_load").live('click',function(){	
		$('body').append('<div id="overlay"></div>');
		$('#overlay').css('opacity',0.4).fadeIn(400);
		var activeLoad = $(this).attr("name");		
		var titleTabs = $(this).attr("title");		
		$("ul.tabs li").hide();		
				$('ul.tabs li').each(function(index) {
						var activeTab = $('ul.tabs li:eq('+index+')').find("a").attr("href")			
						if(activeTab==activeLoad){
							$("ul.tabs ").append('<li class=active><a    href="'+activeLoad+'" class=" prev on_prev "  id="on_prev_pro" name="'+activeLoad+'" >'+titleTabs+'</a></li>');
							$("ul.tabs li:last").fadeIn();	
							}
				});
		$('.widget .content').css({'position':'relative','z-index':'1001'});
		$(".load_page").hide();
		$('.show_add').show();
	 });
	$(".on_prev").live('click',function(){	 
		  $("ul.tabs li:last").remove();					 
		  $("ul.tabs li").fadeIn();
		  var pageLoad = $(this).attr("rel");	
		  var activeLoad = $(this).attr("name");		
			$(".show_add, .show_edit").hide();		
			$(".show_edit").html('').hide();		
				$(activeLoad).fadeIn();	
						$(' .load_page').fadeIn(400,function(){   
							   $('#overlay').fadeOut(function(){
										 $('.widget .content').delay(500).css({'z-index':'','box-shadow':'','-moz-box-shadow':'','-webkit-box-shadow':''});
								}); 
					}); 
		  ResetForm();
		 });	
	
	
              
      

    function showTooltip(x, y, contents) {
        $('<div id="tooltip" >' + contents + '</div>').css({
            position: 'absolute',
            display: 'none',
            top: y -13,
            left: x + 10
        }).appendTo("body").show();
    }


	
	
	});		


	// Check browser fixbug
	var mybrowser=navigator.userAgent;
	if(mybrowser.indexOf('MSIE')>0){$(function() {	
			   $('.formEl_b fieldset').css('padding-top', '0');
				$('div.section label small').css('font-size', '10px');
				$('div.section  div .select_box').css({'margin-left':'-5px'});
				$('.iPhoneCheckContainer label').css({'padding-top':'6px'});
				$('.uibutton').css({'padding-top':'6px'});
				$('.uibutton.icon:before').css({'top':'1px'});
				$('.dataTables_wrapper .dataTables_length ').css({'margin-bottom':'10px'});
		});
	}
	if(mybrowser.indexOf('Firefox')>0){ $(function() {	
			   $('.formEl_b fieldset  legend').css('margin-bottom', '0px');	
			   $('table .custom-checkbox label').css('left', '3px');
		  });
	}	
	if(mybrowser.indexOf('Presto')>0){
		$('select').css('padding-top', '8px');
	}
	if(mybrowser.indexOf('Chrome')>0){$(function() {	
				 $('div.tab_content  ul.uibutton-group').css('margin-top', '-40px');
				  $('div.section  div .select_box').css({'margin-top':'0px','margin-left':'-2px'});
				  $('select').css('padding', '6px');
				  $('table .custom-checkbox label').css('left', '3px');
		});
	}		
	if(mybrowser.indexOf('Safari')>0){}		

	  
	  function fileDialogCallback(callback,type,input){
			$('<div id="finder_'+callback+'"/>').elfinder({
				 url : 'components/elfinder/connectors/php/connector-'+type+'.php', editorCallback : function(url) { $('#'+input).val(url);
				},closeOnEditorCallback : true, dialog : { title : 'File manager',modal : true,width : 700 }
			})							   
	  }
	  function fileDialog(callback,type){
			$('<div id="finder_'+callback+'"/>').elfinder({
				  url : 'components/elfinder/connectors/php/connector-'+type+'.php',dialog : { title : 'File manager',modal : true,width : 700 }
			})							   
	  }
		  
		  
		function Delete(data,name,row,type,dataSet){
				var loadpage = dataSet.hdata(0);
				var url = dataSet.hdata(1);
				var table = dataSet.hdata(2);
				var data = data+"&tabel="+table;
		$.confirm({
		'title': '삭제','message': " <strong>삭제하시겠습니까? </strong><br /><font color=red>' "+ name +" ' </font> ",'buttons': {'Yes': {'class': 'special',
		'action': function(){
					loading('Checking');
									 $('#preloader').html('Deleting...');
									 if(type==0){ row.slideUp(function(){   showSuccess('Success',5000); unloading(); }); return false;}
									  if(type==1){ row.slideUp(function(){   showSuccess('Success',5000); unloading(); }); return false;}
										setTimeout("unloading();",900); 		 
						 }},'No'	: {'class'	: ''}}});}

	  function albumDelete(data,name,dataSet){
			  var loadpage = dataSet.hdata(0);
			  var row = dataSet.hdata(2);
			  $.confirm({
			  'title': '_DELETE DIALOG BOX','message': "<strong>YOU WANT TO DELETE </strong><br /><font color=red>' "+ name +" ' </font> ",'buttons': {'Yes': {'class': 'special',
			  'action': function(){
						  loading('Checking',1);
						  setTimeout("unloading()",1500); 	  
				}},'No'	: {'class'	: ''}}});
	  }
	  
	  
	  function loadalbum(albumid){
			  loading('Loading');
			  $(' .albumImagePreview').show();
			 imgRow();
			  $(' .albumImagePreview').fadeOut(function(){ 										
					  $("#uploadAlbum").attr('href','modalupload.html'); 		
					  $('#uploadAlbum').removeClass('disable secure ').addClass('special add ');
					  $('#uploadButtondisable').css({'display':'none'});
					  $('.screen-msg').hide();  setTimeout("unloading()",500); 	 																	
			  }).delay(400).fadeIn();			
			   
		  }


	  function ResetForm(){
		  $('form').each(function(index) {	  
			var form_id=$('form:eq('+index+')').attr('id');
				  if(form_id){ 
					  $('#'+form_id).get(0).reset(); 
					  $('#'+form_id).validationEngine('hideAll');
							  var editor=$('#'+form_id).find('#editor').attr('id');
							  if(editor){
								   $('#editor').cleditor()[0].clear();
							  }
				  } 
		  });	
	  }


	function hexFromRGB(r, g, b) {
		var hex = [
			r.toString( 16 ),
			g.toString( 16 ),
			b.toString( 16 )
		];
		$.each( hex, function( nr, val ) {
			if ( val.length === 1 ) {
				hex[ nr ] = "0" + val;
			}
		});
		return hex.join( "" ).toUpperCase();
	}
	function refreshSwatch() {
		var red = $( "#red" ).slider( "value" ),
			green = $( "#green" ).slider( "value" ),
			blue = $( "#blue" ).slider( "value" ),
			hex = hexFromRGB( red, green, blue );
		$( "#swatch" ).css( "background-color", "#" + hex );
	}
	  

	
	  
	 /* function loading(name,overlay) { 
			$('body').append('<div id="overlay"></div><div id="preloader">'+name+'..</div>');
					if(overlay==1){
					  $('#overlay').css('opacity',0.4).fadeIn(400,function(){  $('#preloader').fadeIn(400);	});
					  return  false;
			   }
			$('#preloader').fadeIn();	  
	   }
	   */
	  /*function unloading() { 
			$('#preloader').fadeOut(400,function(){ $('#overlay').fadeOut(); $.fancybox.close(); }).remove();
	   }*/
	
	   function imgRow(){	
			  var maxrow=$('.albumpics').width();
			  if(maxrow){
					  maxItem= Math.floor(maxrow/160);
					  maxW=maxItem*160;
					  mL=(maxrow-maxW)/2;
					  $('.albumpics ul').css({
							  'width'	:	maxW	,
							  'marginLeft':mL
			   })
		  }}	
		  
		  function scrollmenu(){	
				  if($(window).scrollTop()>=1){			   
					$("#header ").css("z-index", "50"); 
				}else{
					$("#header ").css("z-index", "47"); 
			   }
		  }

 function LResize(){	
  imgRow(); 
  scrollmenu();
	$("#shadowhead").show();
		if($(window).width()<=480) {
					$(' .albumImagePreview').show();
					$('.screen-msg').hide();
					$('.albumsList').hide();
		}
		if($(window).width()<=768){
			$('body').addClass('nobg');
			$('#content').css({ marginLeft: "70px" });	
			$('#main_menu').removeClass('main_menu').addClass('iconmenu');
					$('#main_menu li').each(function() {	  
							var title=$(this).find('b').text();
							$(this).find('a').attr('title',title);		
					});
					$('#main_menu li a').find('b').hide();	
					$('#main_menu li ').find('ul').hide();
		}else{
			//$('body').removeClass('nobg').addClass('dashborad');
			$('#content').css({ marginLeft: "240px" });	
			$('#main_menu').removeClass('iconmenu ').addClass('main_menu');
			$('#main_menu li a').find('b').show();	
			}
		if($(window).width()>1024) {
					$('#main_menu').removeClass('iconmenu ').addClass('main_menu');
					$('#main_menu li a').find('b').show();	
		}
}