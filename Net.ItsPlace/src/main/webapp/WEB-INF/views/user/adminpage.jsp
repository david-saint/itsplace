<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Admin Page</h1>
<p>Only admins have access to this page.</p>
<p>Curabitur quis libero elit, dapibus iaculis nisl. Nullam quis velit eget odio 
adipiscing tristique non sed ligula. In auctor diam eget nisl condimentum laoreet..</p>

<p><sec:authentication property="name" /> 접속</p>
<p><sec:authentication property="Details" /></p>
<p><sec:authentication property="authorities" /></p>
<p><sec:authentication property="principal" /></p>

<p><sec:authorize access="hasRole('ROLE_ADMIN')">+</sec:authorize></p>

</body>
</html>