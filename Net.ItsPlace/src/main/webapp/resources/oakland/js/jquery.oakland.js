/*
 * Architector Slider v1.1 - jQuery Image Slider
 * 
 * (c) Copyright Steven "cmsmasters" Masters
 * http://cmsmastrs.net/
 * For sale on ThemeForest.net
 */

(function($) {
	$.fn.architectorSlider = function(options){
		var defaults = {
			sliderWidth: 600,
			sliderHeight: 300,
			pauseTime: 5000,
			animationSpeed: 500,
			effect: '',
			easing: '',
			descriptionOpacity: 0.8,
			numberNav: true,
			numberNavThumb: true,
			numberNavHide: false,
			thumbnailWidth: 90,
			thumbnailHeight: 45,
			timeLine: true,
			blocksize: {height: '', width: ''},
			customblocksize: {
				cubeinlinearhor: {height: 100, width: 100},
				cubeinlinearver: {height: 100, width: 100},
				cubeindiagonal: {height: 100, width: 100},
				cubeinrandom: {height: 100, width: 100},
				stripemovehor: {width: 40},
				stripemovever: {height: 20},
				stripefadehor: {width: 40},
				stripefadever: {height: 20},
				stripechangehor: {width: 40},
				stripechangever: {height: 20},
				stripehalfhor: {width: 40},
				stripehalfver: {height: 20},
				stripehalfotherhor: {width: 40},
				stripehalfotherver: {height: 20}
			},
			callback: function(){ }
		};
		
		var options = $.extend(defaults, options);
		
		// -------------------- Declaring variables ---------------------
		var slider = $(this);
		var li = slider.find('li');
		
		var pos, random_no, timer, image_timer, arr, index, block, w, h, k, t, m, i, j, src, parent, im, effect, easing, numberNav, override = false, in_animation = false;
		var current = li.eq(0).toggleClass('active');
		var prev = li.eq(0).addClass('reset');
		var bool = true, first_bool = true;
		var set_img = false;
		
		if (options.easing == ''){
			easing = 'linear';
		} else {
			easing = options.easing;
		}
		
		// -------------------- Initialization ---------------------
		init();
		
		function init(){
			slider.wrap('<div class="architector-slider" />');

			var sliderContainer = slider.parent();
			slider.css({width: options.sliderWidth, height: options.sliderHeight});
			sliderContainer.css({width: options.sliderWidth});

			if (options.numberNav == true){
				appendNumberNav();
			}

			if (options.timeLine == true){
				appendTimeLine();
			}
			
			if ($.browser.msie && $.browser.version < 9){
				li.first().find('.slide-description').fadeTo(options.animationSpeed, '0.8');
			} else {
				li.first().find('.slide-description').fadeTo(options.animationSpeed, options.descriptionOpacity);
			}
			sliderContainer.find('.architector-time-line').animate({width: options.sliderWidth}, options.pauseTime, function(){ 
				sliderContainer.find('.architector-time-line').css({width: 0}); 
			});
			current.children().hide();
			if (first_bool == true){
				current.children().show();
				li.not('.active').children().hide();
			}
			
			// -------------------- Slide switching module ---------------------
			function switcher(){
				if (current.prev().length > 0){
					prev = current.prev();
				} else {
					prev = li.eq(-1);
				} 
				
				li.removeClass('reset');
				current.toggleClass('active reset');

				if (current.next().length > 0){
					current = current.next();
				} else {
					current = li.eq(0);
				}
				
				current.children().hide();
				current.addClass('active');
				options.callback(current.children()[0]);
			}
			
			// --------------------- Cube In Linear Horizontal Image Animation Start --------------------------
			function cubeinlinearhor(image){
				in_animation = true;
			
				if (options.blocksize.width != '' && options.blocksize.height != ''){
					w = Math.floor(options.blocksize.width);
					h = Math.floor(options.blocksize.height);
				} else {
					w = Math.floor(options.customblocksize.cubeinlinearhor.width);
					h = Math.floor(options.customblocksize.cubeinlinearhor.height);	 
				}
				
				var cubeTime, cubeHor, cubeVer;
				cubeHor = Math.round(options.sliderWidth/w);
				cubeVer = Math.round(options.sliderHeight/h);
				cubeTime = cubeHor * cubeVer;
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css: {
						position: 'absolute',
						width: 0,
						height: 0,
						marginTop: h/2,
						marginLeft: w/2,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99
					}
				}).addClass('disblock');
				
				while (i < options.sliderWidth){
					j = 0;
					
					while (j < options.sliderHeight){
						arr[index] = block.clone().css({
							left: i, 
							top: j, 
							backgroundPosition: -i + 'px ' + -j + 'px'
						});
						parent.append(arr[index++]);
						
						j = j + h;
					}
					
					i = i + w;
				}
				i = 0;
				
				timer = setInterval(function(){
					if (i >= arr.length){
						endeffect(image);
						return;
					}
					
					arr[i++].animate({
						height: h, 
						width: w, 
						marginTop: 0, 
						marginLeft: 0
					}, {
						duration: options.animationSpeed, 
						easing: easing
					});
				}, options.animationSpeed/cubeTime);
			
			};
			// --------------------- Cube In Linear Horizontal Image Animation Finish --------------------------

			// --------------------- Cube In Linear Vertical Image Animation Start --------------------------
			function cubeinlinearver(image){
				in_animation = true;
			
				if (options.blocksize.width != '' && options.blocksize.height != ''){
					w = Math.floor(options.blocksize.width);
					h = Math.floor(options.blocksize.height);
				} else {
					w = Math.floor(options.customblocksize.cubeinlinearver.width);
					h = Math.floor(options.customblocksize.cubeinlinearver.height);	 
				}
				
				var cubeTime, cubeHor, cubeVer;
				cubeHor = Math.round(options.sliderWidth/w);
				cubeVer = Math.round(options.sliderHeight/h);
				cubeTime = cubeHor * cubeVer;
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css: {
						position: 'absolute',
						width: 0,
						height: 0,
						marginTop: h,
						marginLeft: w,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderHeight){
					j = 0;
					
					while (j < options.sliderWidth){
						arr[index] = block.clone().css({
							left: j, 
							top: i, 
							backgroundPosition: -j + 'px ' + -i + 'px'
						});
						parent.append(arr[index++]);
						
						j = j + w;
					}
					
					i = i + h;
				}
				i = 0;
				
				timer = setInterval(function(){
					if (i >= arr.length){
						endeffect(image);
						return;
					}
					
					arr[i++].animate({
						height: h, 
						width: w, 
						marginTop: 0, 
						marginLeft: 0,
						opacity: 1
					}, {
						duration: options.animationSpeed, 
						easing: easing
					});
				}, options.animationSpeed/cubeTime);
			};
			// --------------------- Cube In Linear Vertical Image Animation Finish --------------------------
			
			// --------------------- Cube In Diagonal Image Animation Start --------------------------
			function cubeindiagonal(image){
				in_animation = true;
			
				if (options.blocksize.width != '' && options.blocksize.height != ''){
					w = Math.floor(options.blocksize.width);
					h = Math.floor(options.blocksize.height);
				} else {
					w = Math.floor(options.customblocksize.cubeindiagonal.width);
					h = Math.floor(options.customblocksize.cubeindiagonal.height);	 
				}
				
				var cubeTime, cubeHor, cubeVer;
				cubeHor = Math.round(options.sliderWidth/w);
				cubeVer = Math.round(options.sliderHeight/h);
				cubeTime = cubeHor * cubeVer;
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css: {
						position: 'absolute',
						width: 0,
						height: 0,
						marginTop: h*2,
						marginLeft: w*2,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						opacity: 0
					}
				}).addClass('disblock');
				
				var wb, hb;
				wb = cubeHor;
				hb = cubeVer;
				k = 1;
				t = 1;

				while (k < (wb + hb)){
					j = 0;
					m = 0;
					
					if (k < hb){
						while (m < k){
							arr[index] = block.clone().css({
								left: i, 
								top: j, 
								backgroundPosition: -i + 'px ' + -j + 'px'
							});
							parent.append(arr[index++]);
							
							j = j + h;
							i = i - w;
							m++
						}
					} else {
						if (k > wb){
							m = t;
							i = options.sliderWidth - w;
						
							while (hb > m){
								j = h * m;
								
								arr[index] = block.clone().css({
									left: i, 
									top: j, 
									backgroundPosition: -i + 'px ' + -j + 'px'
								});
								parent.append(arr[index++]);
								
								j = j - h;
								i = i - w;
								m++
							}
							
							t++
						} else {
							m = 0;
						
							while (m < hb){
								arr[index] = block.clone().css({
									left: i, 
									top: j, 
									backgroundPosition: -i + 'px ' + -j + 'px'
								});
								parent.append(arr[index++]);
								
								j = j + h;
								i = i - w;
								m++
							}
						}
					}
					
					i = w * k;
					k++
				}
				i = 0;
				
				timer = setInterval(function(){
					if (i >= arr.length){
						endeffect(image);
						return;
					}
					
					arr[i++].animate({
						opacity: 1, 
						height: h, 
						width: w, 
						marginTop: 0, 
						marginLeft: 0
					}, {
						duration: options.animationSpeed, 
						easing: easing
					});
				}, options.animationSpeed/cubeTime);
			};
			// --------------------- Cube In Diagonal Image Animation Finish --------------------------
			
			// --------------------- Cube In Random Image Animation Start --------------------------
			function cubeinrandom(image){
				in_animation = true;
				
				if (options.blocksize.width != '' && options.blocksize.height != ''){ 
					w = Math.floor(options.blocksize.width);
					h = Math.floor(options.blocksize.height);
				} else { 
					w = Math.floor(options.customblocksize.cubeinrandom.width);
					h = Math.floor(options.customblocksize.cubeinrandom.height);	 
				}
				
				var cubeTime, cubeHor, cubeVer;
				cubeHor = Math.round(options.sliderWidth/w);
				cubeVer = Math.round(options.sliderHeight/h);
				cubeTime = cubeHor * cubeVer;
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css:{
						position: 'absolute',
						opacity: 0,
						width: 0,
						height: 0,
						top: options.sliderHeight,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99
					}
				}).addClass('disblock');
				
				 while (i < options.sliderWidth){
					j = 0;
					
					while (j < options.sliderHeight){
						arr[index] = block.clone().css({
							left: i, 
							top: j, 
							backgroundPosition: -i + 'px ' + -j + 'px'
						});
						parent.append(arr[index++]);
						
						j = j + h;
					}
					
					i = i + w;
				}
				i = 0;
				random_no = random_array(arr.length);
				
				timer = setInterval(function(){
					if (i >= arr.length){
						endeffect(image);
						return;
					}
					
					arr[random_no[i++]].animate({
						height: h, 
						width: w, 
						opacity: 1
					}, {
						duration: options.animationSpeed, 
						easing: easing
					});
				}, options.animationSpeed/cubeTime);
			};
			// --------------------- Cube In Random Image Animation Finish --------------------------
			
			// --------------------- Stripe Move Horizontal Image Animation Start --------------------------
			function stripemovehor(image){
				in_animation = true;
				
				if (options.blocksize.width != ''){
					w = Math.floor(options.blocksize.width);
				} else { 
					w = Math.floor(options.customblocksize.stripemovehor.width);
				}
				
				h = options.sliderHeight;
				var stripTime;
				stripTime = Math.round((options.sliderWidth/w)/2);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css:{
						position: 'absolute',
						width: w,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						marginTop: -h
					}
				}).addClass('disblock');
				
				while (i < options.sliderWidth){
					arr[index] = block.clone().css({
						left: i, 
						backgroundPosition: -i + 'px 0px'
					});
					parent.append(arr[index++]);
					
					i = i + w;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}
						
						arr[i++].animate({marginTop: 0}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed/stripTime);
			};	
			// --------------------- Stripe Move Horizontal Image Animation Finish --------------------------
			
			// --------------------- Stripe Move Vertical Image Animation Start --------------------------
			function stripemovever(image){
				in_animation = true;
				
				if (options.blocksize.height != ''){
					h = Math.floor(options.blocksize.height);
				} else {
					h = Math.floor(options.customblocksize.stripemovever.height);
				}
				
				w = options.sliderWidth;
				var stripTime;
				stripTime = Math.round((options.sliderHeight/h)/2);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css:{
						position: 'absolute',
						width: w,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						marginLeft: -w
					}
				}).addClass('disblock');
				
				while (i < options.sliderHeight){
					arr[index] = block.clone().css({
						top: i, 
						backgroundPosition: '0 ' + -i + 'px'
					});
					parent.append(arr[index++]);
					
					i = i + h;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}
						
						arr[i++].animate({marginLeft: 0}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed/stripTime);
			};
			// --------------------- Stripe Move Vertical Image Animation Finish --------------------------
			
			// --------------------- Stripe Fade Horizontal Image Animation Start --------------------------
			function stripefadehor(image){
				in_animation = true;
				
				if (options.blocksize.width != ''){
					w = Math.floor(options.blocksize.width);
				} else { 
					w = Math.floor(options.customblocksize.stripefadehor.width);
				}
				
				h = options.sliderHeight;
				var stripTime;
				stripTime = Math.round(options.sliderWidth/w);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css:{
						position: 'absolute',
						width: w,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderWidth){
					arr[index] = block.clone().css({
						left: i, 
						backgroundPosition: -i + 'px 0px'
					});
					parent.append(arr[index++]);
					
					i = i + w;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}
						
						arr[i++].animate({opacity: 1}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed/stripTime);
			};	
			// --------------------- Stripe Fade Horizontal Image Animation Finish --------------------------
			
			// --------------------- Stripe Fade Vertical Image Animation Start --------------------------
			function stripefadever(image){
				in_animation = true;
				
				if (options.blocksize.height != ''){
					h = Math.floor(options.blocksize.height);
				} else {
					h = Math.floor(options.customblocksize.stripefadever.height);
				}
				
				w = options.sliderWidth;
				var stripTime;
				stripTime = Math.round(options.sliderHeight/h);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', {
					css:{
						position: 'absolute',
						width: w,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderHeight){
					arr[index] = block.clone().css({
						top: i, 
						backgroundPosition: '0 ' + -i + 'px'
					});
					parent.append(arr[index++]);
					
					i = i + h;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}
						
						arr[i++].animate({opacity: 1}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed/stripTime);
			};
			// --------------------- Stripe Fade Vertical Image Animation Finish --------------------------
			
			// --------------------- Stripe Change Horizontal Image Animation Finish --------------------------
			function stripechangehor(image){
				in_animation = true;
				im = image;
				
				if (options.blocksize.width != ''){
					w = Math.floor(options.blocksize.width);
				} else { 
					w = Math.floor(options.customblocksize.stripechangehor.width);
				}
				
				h = options.sliderHeight;
				var stripTime;
				stripTime = Math.round(options.sliderWidth/w);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');

				var css, flag;
				flag = true;
				
				block = $('<div />', { 
					css:{
						position: 'absolute',
						width: w,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						marginTop: options.sliderHeight,
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderWidth){
					if (flag == true){
						css = {
							left: i, 
							backgroundPosition: -i + 'px 0px'
						};
						flag = false;
					} else {
						css = {
							left: i,
							backgroundPosition: -i + 'px 0px', 
							marginTop: -options.sliderHeight
						};
						flag = true;
					}
					
					arr[index] = block.clone().css(css);
					parent.append(arr[index++]);
					
					i = i + w;
				}
				i = 0;
				
				timer = setInterval(function(){
					if (i >= arr.length){
						endeffect(image);
						return;
					}
					
					arr[i++].animate({
						marginTop: 0,
						opacity: 1
					}, {
						duration: options.animationSpeed, 
						easing: easing
					});
				}, options.animationSpeed/stripTime);
			};
			// --------------------- Stripe Change Horizontal Image Animation Finish --------------------------
			
			// --------------------- Stripe Change Vertical Image Animation Finish --------------------------
			function stripechangever(image){
				in_animation = true;
				im = image;
				
				if (options.blocksize.height != ''){
					h = Math.floor(options.blocksize.height);
				} else { 
					h = Math.floor(options.customblocksize.stripechangever.height);
				}
				
				w = options.sliderWidth;
				var stripTime;
				stripTime = Math.round(options.sliderHeight/h);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');

				var css, flag;
				flag = true;
				
				block = $('<div />', { 
					css:{
						position: 'absolute',
						width: w,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						marginLeft: options.sliderWidth,
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderHeight){
					if (flag == true){
						css = {
							top: i, 
							backgroundPosition: '0px ' + -i + 'px'
						};
						flag = false;
					} else {
						css = {
							top: i,
							backgroundPosition: '0px ' + -i + 'px', 
							marginLeft: -options.sliderWidth
						};
						flag = true;
					}
					
					arr[index] = block.clone().css(css);
					parent.append(arr[index++]);
					
					i = i + h;
				}
				i = 0;
				
				timer = setInterval(function(){
					if (i >= arr.length){
						endeffect(image);
						return;
					}
					
					arr[i++].animate({
						marginLeft: 0,
						opacity: 1
					}, {
						duration: options.animationSpeed, 
						easing: easing
					});
				}, options.animationSpeed/stripTime);
			};
			// --------------------- Stripe Change Vertical Image Animation Finish --------------------------
			
			// --------------------- Stripe Half Horizontal Image Animation Start --------------------------
			function stripehalfhor(image){
				in_animation = true;
				
				if (options.blocksize.width != ''){
					w = Math.floor(options.blocksize.width);
				} else {
					w = Math.floor(options.customblocksize.stripehalfhor.width);
				}
				
				h = options.sliderHeight;
				var stripTime;
				stripTime = Math.round(options.sliderWidth/w);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');

				var css;
				
				block = $('<div />', { 
					css:{
						position:"absolute",
						width:w,
						height:h/2,
						'background-image':'url('+src+')',
						zIndex:99,
						display:'block',
						opacity:0
					}
				}).addClass('disblock');
				
				while (i < options.sliderWidth){
					j = 0;
					
					while (j < h){
						if (j == 0){
							css = {
								left: i, 
								top: j, 
								backgroundPosition: -i + 'px ' + (-j) + 'px', 
								marginTop: -(h/2)
							};
						} else {
							css = {
								left: i, 
								top: j, 
								backgroundPosition: -i + 'px ' + (-j) + 'px', 
								marginTop: h
							};
						}
						
						arr[index] = block.clone().css(css);
						parent.append(arr[index++]);
						
						j = j + h/2;
					}
					
					i = i + w;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}
						
						arr[i++].animate({
							opacity: 1, 
							marginTop: 0
						}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed/stripTime);
			};
			// --------------------- Stripe Half Horizontal Image Animation Finish --------------------------
			
			// --------------------- Stripe Half Vertical Image Animation Start --------------------------
			function stripehalfver(image){
				in_animation = true;
				
				if (options.blocksize.height != ''){
					h = Math.floor(options.blocksize.height);
				} else {
					h = Math.floor(options.customblocksize.stripehalfver.height);
				}
				
				w = options.sliderWidth;
				var stripTime;
				stripTime = Math.round(options.sliderHeight/h);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');
				
				var css;
				
				block = $('<div />', { 
					css:{
						position: 'absolute',
						width: w/2,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderHeight){
					j = 0;
					
					while (j < w){
						if (j == 0){
							css = {
								left: j, 
								top: i, 
								backgroundPosition: -j + 'px ' + (-i) + 'px', 
								marginLeft: -(w/2)
							};
						} else {
							css = {
								left: j, 
								top: i, 
								backgroundPosition: -j + 'px ' + (-i) + 'px', 
								marginLeft: w
							};
						}
						
						arr[index] = block.clone().css(css);
						parent.append(arr[index++]);
						
						j = j + w/2;
					}
					
					i = i + h;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}

						arr[i++].animate({
							opacity: 1, 
							marginLeft: 0
						}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed);
			};
			// --------------------- Stripe Half Vertical Image Animation Finish --------------------------
			
			// --------------------- Stripe Half Other Horizontal Image Animation Finish --------------------------
			function stripehalfotherhor(image){
				in_animation = true;
				
				if (options.blocksize.width != ''){
					w = Math.floor(options.blocksize.width);
				} else {
					w = Math.floor(options.customblocksize.stripehalfotherhor.width);
				}
				
				h = options.sliderHeight;
				var stripTime;
				stripTime = Math.round(options.sliderWidth/w);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');
				
				var css;
				
				block = $('<div />', {
					css:{
						position: 'absolute',
						width: w,
						height: h/2,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderWidth){
					j = 0;
					
					while (j < h){
						if (j == 0){
							css = {
								left: i, 
								top: j, 
								backgroundPosition: -i + 'px ' + (-j) + 'px', 
								marginLeft: -w
							};
						} else {
							css = {
								left: i, 
								top: j, 
								backgroundPosition: -i + 'px ' + (-j) + 'px', 
								marginLeft: -(w/2)
							};
						}
						
						arr[index] = block.clone().css(css);
						parent.append(arr[index++]);
						
						j = j + h/2;
					}
					
					i = i + w;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}
						
						arr[i++].animate({
							opacity: 1, 
							marginLeft: 0
						}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed*2);
			};
			// --------------------- Stripe Half Other Horizontal Image Animation Finish --------------------------
			
			// --------------------- Stripe Half Other Vertical Image Animation Start --------------------------
			function stripehalfotherver(image){
				in_animation = true;
				
				if (options.blocksize.height != ''){
					h = Math.floor(options.blocksize.height);
				} else {
					h = Math.floor(options.customblocksize.stripehalfotherver.height);
				}
				
				w = options.sliderWidth;
				var stripTime;
				stripTime = Math.round(options.sliderHeight/h);
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				j = 0;
				index = 0;
				src = image.attr('src');
				
				var css;
				
				block = $('<div />', { 
					css: {
						position: 'absolute',
						width: w/2,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < options.sliderHeight){
					j = 0;

					while (j < w){
						if (j == 0){
							css = {
								left: j, 
								top: i, 
								backgroundPosition: -j + 'px ' + (-i) + 'px', 
								marginTop: -h
							};
						} else {
							css = {
								left: j, 
								top: i, 
								backgroundPosition: -j + 'px ' + (-i) + 'px', 
								marginTop: -(h/2)
							};
						}
						
						arr[index] = block.clone().css(css);
						parent.append(arr[index++]);
						
						j = j + w/2;
					}
					
					i = i + h;
				}
				i = 0;
				
				setTimeout(function(){
					timer = setInterval(function(){
						if (i >= arr.length){
							endeffect(image);
							return;
						}
						
						arr[i++].animate({
							opacity: 1, 
							marginTop: 0
						}, {
							duration: options.animationSpeed, 
							easing: easing
						});
					}, options.animationSpeed/stripTime);
				}, options.animationSpeed*2);
			};
			// --------------------- Stripe Half Other Vertical Image Animation Finish --------------------------
			
			// --------------------- Slide Fade Image Animation Start --------------------------
			function slidefade(image){
				in_animation = true;
				
				h = options.sliderHeight;
				w = options.sliderWidth;
				
				parent = image.parent();
				arr = new Array();
				i = 0;
				index = 0;
				src = image.attr('src');
				
				block = $('<div />', { 
					css: {
						position: 'absolute',
						width: w,
						height: h,
						backgroundImage: 'url(' + src + ')',
						zIndex: 99,
						display: 'block',
						opacity: 0
					}
				}).addClass('disblock');
				
				while (i < h){
					arr[index] = block.clone().css({
						left: 0, 
						top: 0
					});
					parent.append(arr[index++]);
					
					i = i + h;
				}
				i = 0;
				
				timer = setInterval(function(){
					if (i >= arr.length){
						endeffect(image);
						return;
					}
					
					arr[i++].animate({opacity: 1}, {
						duration: options.animationSpeed*1.5, 
						easing: easing
					});
				}, options.animationSpeed);
			};
			// --------------------- Slide Fade Image Animation Finish --------------------------
			
			function endeffect(image){
				if ($.browser.msie && $.browser.version < 9){
					current.find('.slide-description').fadeTo(options.animationSpeed, '0.8');
				} else {
					current.find('.slide-description').fadeTo(options.animationSpeed, options.descriptionOpacity);
				}
				
				if (options.numberNav == true){
					numberNav.removeClass("active");
					numberNav.eq(current.index(".architector-slider li")).addClass("active");
				}
				
				clearInterval(timer);
				
				setTimeout(function(){
					sliderContainer.find('.architector-time-line').animate({width: options.sliderWidth}, options.pauseTime);
					
					image.show();
					$(".disblock").remove();
					
					in_animation = false;

					if (override == false){
						image_timer = setTimeout(function(){
							switcher();
							image.parent().find('.slide-description').fadeOut(options.animationSpeed);
							sliderContainer.find('.architector-time-line').stop(true, false).css({width: 0});
							effects();
						}, options.pauseTime);
					}
				}, 1000);
			};

			// -------------------- Effects selector module ---------------------
			function effects(){
				var ch = Math.floor(Math.random() * 15);
				
				if (options.effect != ''){
					if (options.effect == 'cubeInLinearHor'){
						effect = 0;
					} else {
						if (options.effect == 'cubeInLinearVer'){
							effect = 1;
						} else {
							if (options.effect == 'cubeInDiagonal'){
								effect = 2;
							} else {
								if (options.effect == 'cubeInRandom'){
									effect = 3;
								} else {
									if (options.effect == 'stripeMoveHor'){
										effect = 4;
									} else {
										if (options.effect == 'stripeMoveVer'){
											effect = 5;
										} else {
											if (options.effect == 'stripeFadeHor'){
												effect = 6;
											} else {
												if (options.effect == 'stripeFadeVer'){
													effect = 7;
												} else {
													if (options.effect == 'stripeChangeHor'){
														effect = 8;
													} else {
														if (options.effect == 'stripeChangeVer'){
															effect = 9;
														} else {
															if (options.effect == 'stripeHalfHor'){
																effect = 10;
															} else {
																if (options.effect == 'stripeHalfVer'){
																	effect = 11;
																} else {
																	if (options.effect == 'stripeHalfOtherHor'){
																		effect = 12;
																	} else {
																		if (options.effect == 'stripeHalfOtherVer'){
																			effect = 13;
																		} else {
																			if (options.effect == 'slideFade'){
																				effect = 14;
																			} else {
																				effect = ch;
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					
					ch = effect;
				}
				  
				if (bool == true){
					bool = false;
					li.first().find('.slide-description').fadeOut(options.animationSpeed);
				}

				if (first_bool == true && set_img != true){
					li.eq(0).removeClass('active');
					current = li.eq(1).addClass('active');
				}
				
				if ($.browser.msie && $.browser.version < 9){
					switch(ch){
						case ch: slidefade(current.find('img.slide-image')); break;
					}
				} else {
					switch(ch){
						case 0: cubeinlinearhor(current.find('img.slide-image')); break;
						case 1: cubeinlinearver(current.find('img.slide-image')); break;
						case 2: cubeindiagonal(current.find('img.slide-image')); break;
						case 3: cubeinrandom(current.find('img.slide-image')); break;
						case 4: stripemovehor(current.find('img.slide-image')); break;
						case 5: stripemovever(current.find('img.slide-image')); break;
						case 6: stripefadehor(current.find('img.slide-image')); break;
						case 7: stripefadever(current.find('img.slide-image')); break;
						case 8: stripechangehor(current.find('img.slide-image')); break;
						case 9: stripechangever(current.find('img.slide-image')); break;
						case 10: stripehalfhor(current.find('img.slide-image')); break;
						case 11: stripehalfver(current.find('img.slide-image')); break;
						case 12: stripehalfotherhor(current.find('img.slide-image')); break;
						case 13: stripehalfotherver(current.find('img.slide-image')); break;
						case 14: slidefade(current.find('img.slide-image')); break;
					}
				}
				
				if (first_bool == true){
					if (li.first().find('.slide-description').css('display') != 'none'){
						li.first().find('.slide-description').css({display: 'none'});
					}
				
					first_bool = false;
				}
			}
			
			function appendNumberNav(){
				var str = "<div class='architector-number-nav'>";
				
				for (var i = 0; i < li.length; i++){
					if (options.numberNavThumb == false) {
						str = str + '<a href="#">' + (i + 1) + '</a>';
					} else {
						str = str + '<a href="#"><img src="' + li.eq(i).find('img.slide-thumbnail').attr('src') + '" alt="" width="' + options.thumbnailWidth + '" height="' + options.thumbnailHeight + '" /><span style="width:' + options.thumbnailWidth + 'px; height:' + options.thumbnailHeight + 'px;">&nbsp;</span></a>';							
					}
				}
				
				str = str + "</div>";
				slider.after(str);
				
				numberNav = slider.parent().find(".architector-number-nav a");
				numberNav.first().addClass("active");
				
				numberNav.bind({
					click: function(){ setImage($(this).index()); return false; },
					mouseover: function(){ $(this).toggleClass("hover"); },
					mouseout: function(){ $(this).toggleClass("hover"); }
				});
				
				var numberNavContainer = numberNav.parent();
				
				if (options.numberNavHide == true){
					numberNavContainer.css({height: 0});
					sliderContainer.hover(function(){
						numberNavContainer.stop(true, false).animate({height: numberNav.outerHeight()}, options.animationSpeed);
					}, function(){
						numberNavContainer.stop(true, false).animate({height: 0}, options.animationSpeed);					
					});
				}
			}
			
			function appendTimeLine(){
				var timeLine = "<div class='architector-time-line' />";
				slider.after(timeLine);
				sliderContainer.find('.architector-time-line').css({top: options.sliderHeight-5});
			}
			 
			function setImage(index){
				if (in_animation == true || current.index('.architector-slider li') == index){ return; }
				
				set_img = true;
				li.removeClass('reset active');
				sliderContainer.find('.architector-time-line').stop(true, false).css({width: 0});
				current.find('.slide-description').fadeOut(options.animationSpeed);
				
				clearTimeout(image_timer);
				
				if (first_bool == true){
					li.first().find('.slide-description').fadeOut(options.animationSpeed);
					li.first().addClass("reset");
				}
				
				current.addClass("reset");
				current = li.eq(index).addClass("active");
				current.children().hide();
				
				effects();
			}

			image_timer = setTimeout(function(){ effects(); }, options.pauseTime);
		}
	}

	function random_array(maxn){
		var array = new Array();
		var temp;
		var flag = true;
		var index = 0;

		while (index < maxn){
			flag = true;
			temp = Math.floor(Math.random() * maxn);
			 
			for (i = 0; i < array.length; i++){
				if (temp == array[i]){
					flag = false;
					break;
				}
			}
			 
			if (flag == true) {
				array[index++] = temp;
			}
		}
		
		return array;
	};
})(jQuery);
