<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
 
$(document).ready(function() {
	$('#jqmWindow').jqm();
	
	$('#search').live('mouseover',function(){	$('#jqmWindow').jqmShow(); 
		var options = { to: { width: 183, height: 28 } };
		$('#search').effect( 'size', options, 100,mouseover );
	});
	$('#search').live('mouseout',function(){
		//$(this).blur();
		
		//$('#search').effect('destrooy');
		//var options = { to: { width: 100, height: 28 } };
		//$('#search').effect( 'size', options, 500, mouseout );
	});
	 
});
function mouseover() {
	$('#search').css('padding','0 10px 0 32px');
	$('#search').focus();
  
};
function mouseout() {
	$('#search').val("");
	  console.log("mouseout");
 
};
</script>
<style>

</style>

 <div class="jqmWindow" id="jqmWindow">

		
		<a href="#" class="jqmClose">Close</a>
		<hr>
		<em>READ33333333 ME</em> -->
		This is a "vanilla plain" jqModal window. Behavior and appeareance extend far beyond this.
		The demonstrations on this page will show off a few possibilites. I recommend walking
		through each one to get an understanding of jqModal <em>before</em> using it.
		
		<br /><br />
		You can view the sourcecode of examples by clicking the Javascript, CSS, and HTML tabs.
		Be sure to checkout the <a href="README">documentation</a> too!
		
		<br /><br />
		<em>NOTE</em>; You can close windows by clicking the tinted background known as the "overlay".
		Clicking the overlay will have no effect if the "modal" parameter is passed, or if the
		overlay is disabled.

</div>


<div id="header">
	<form><input type="text" id="search" name="searchWord" /></form>
</div> 
	

    <div class="tileContainer">
    	 <div id="sample2-grid" class="grid"></div>
    </div>
   


