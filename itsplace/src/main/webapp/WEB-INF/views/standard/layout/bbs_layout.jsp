<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<div><tiles:insertAttribute name="bbs_header" /></div>
<div>
	<div style="float: left;width:20%"><tiles:insertAttribute name="bbs_left" /></div>
	<div style="float: left;width:80%"><tiles:insertAttribute name="body" /></div>
</div>
<div style="clear: both;"><tiles:insertAttribute name="bbs_footer" /></div>
</body>
</html>
