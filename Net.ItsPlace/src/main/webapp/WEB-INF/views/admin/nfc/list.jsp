
<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="NFC 가맹점 리스트"/>

<script type="text/javascript">
$(document).ready(function() {
	search("");
	
	
	 
});
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
 
function search(searchWord){
	$('section').empty();
	 $.ajax({
	        type: 'POST',
	        url: "/web/search/place",
	        data: "searchWord="+searchWord,
	        success: function(data) {
	        	console.log(data.result);
	        	var html = new StringBuffer();
	        	$.each(data.result,function(i){
	        		console.log(this.fname);
	        		console.log(this.filename);
	        		console.log(this.dong);
	        		html.append('<div class="place-list">');
	        		html.append('<div class="post-preview">');
	        	    html.append('<a href=""><img src="http://lorempixel.com/48/48/nightlife/3" /></a>');
	        	    html.append('</div>');
	        	    html.append('<div class="post-content">');
	        	    html.append('<h2><a href="#">'+this.fname+'</a></h2>');
	        	    html.append('<p></p>');
	        	    html.append('<div class="date"><span>20 mar 2012</span></div>');
	        	    html.append('</div>');
	        	   	html.append('<div class="clear"></div>');
	        	   	html.append('</div>');
	        	   	$('section').append(html.toString());
	        	});
	        
	        }
	 		, error: function(data, status, err) {
	 			
	 		}
	 		, complete: function() {
	 			$('.place-list').click(function(){
	 				window.AndroidCall.nfcReady("hi");
	 			});
	 		}
		});
} 
</script>
<header>

</header>
<section>
	<div class="place-list">
		<div class="post-preview">
			<a href=""><img src="http://lorempixel.com/48/48/nightlife/3" /></a>
		</div>
		<div class="post-content">
			<h2><a href="basic_markup.html">Post with title, preview, excerpt and date</a></h2>
			<p>I wish! It's a nickel. Good man. Nixon's pro-war and pro-family. Hey, what kinda party is this? There's no booze and only one hooker.</p>
			<div class="date"><span>20 mar 2012</span></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="place-list ">
		<div class="post-preview">
			<a href=""><img src="http://lorempixel.com/48/48/nightlife/3" alt="title of the image"/></a>
		</div>
		<div class="post-content">
			<h2><a href="basic_markup.html">Post with title, preview, excerpt and date</a></h2>
			<p>I wish! It's a nickel. Good man. Nixon's pro-war and pro-family. Hey, what kinda party is this? There's no booze and only one hooker.</p>
			<div class="date"><span>20 mar 2012</span></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
</section>
<footer>
	<div class="inner">
		<nav>
			<ul>
				<li><a href="/about/">About</a></li>
				<li><a href="/?site_preference=Normal">Standard</a></li>
				<li><a href="http://blog.pulse.me">Blog</a></li>
				<li><a href="http://blog.pulse.me/faq">FAQ</a></li>
				<li><a href="/about/#/contact">Contact</a></li>
			</ul>
		</nav>
		<small class="copyright">All content Copyright 2012 by
			Alphonso Labs</small>
	</div>
</footer>