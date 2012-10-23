<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
 
$(document).ready(function() {
	
	$('#search').live('mouseover',function(){
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

<div id="header">
	<form><input type="text" id="search" name="searchWord" /></form>
</div> 
	

    <div class="tileContainer">
    	 <div id="sample2-grid" class="grid"></div>
    </div>
   


 
