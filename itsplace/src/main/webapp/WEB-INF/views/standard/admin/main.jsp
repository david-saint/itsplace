<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
$(document).ready(function(){
	 $( "#tabs" ).tabs({
		 select: function(event, ui) {
			// alert(ui.index);
		       if(ui.index==0){ 
		    	   JQgrid.UserList();
		       }
		    },
		 ajaxOptions: {
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"Couldn't load this tab. We'll try to fix this as soon as possible. " +
						"If this wouldn't be a demo." );
				}
			} 
		});
	 
	// $("#tabs").tabs('select', 0);
	JQgrid.UserList();
});

</script>


	
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
				Administrator
			</h1>        		
        			
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<div id="tabs" style=""> 
				<ul>
				<li><a href="#tabs-1">회원목록</a></li>
				<li><a href="#tabs-2">Proin dolor</a></li>
				<li><a href="#tabs-3">Aenean lacinia</a></li>
				</ul>
				<div id="tabs-1">
					
						<table id="UserGrid"></table>
						<div id="UserPager"></div>
						
				</div>
				<div id="tabs-2"></div>
				<div id="tabs-3">
					<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
					<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
				</div>
			</div>
		</div><!-- content end -->
	</div>
	
</div>

