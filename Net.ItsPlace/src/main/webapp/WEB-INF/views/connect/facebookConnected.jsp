<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

<h3>Connected to Facebook</h3>

<form id="disconnect" method="post" action="/connect/facebook">
	<div class="formInfo">
		<p>
			Spring Social Showcase is connected to your Facebook account.
			Click the button if you wish to disconnect.
		</p>		
	</div>
	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>
<script type="text/javascript">

$(document).ready(function(){
	console.log("facebook");
	//$('form').submit();
	opener.chageSocialStatus("facebook","connected");
	self.close();
	
});
</script>