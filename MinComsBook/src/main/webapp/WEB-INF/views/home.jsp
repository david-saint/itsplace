<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Books API Example</title>
    <script type="text/javascript">
 	$(document).ready(function(){
 		
    });
	</script>      
  </head>
  <body>    <a href="<c:url value="${context}/logout" />" > Logout</a>
   <!--  <script>
      function handleResponse(response) {
      for (var i = 0; i < response.items.length; i++) {
        var item = response.items[i];
       // console.log("dddddddddddddddd");
       // console.log(item);
        // in production code, item.text should have the HTML entities escaped.
        document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title
        + "<br>" + item.volumeInfo.authors
        + "<br>" + item.volumeInfo.publisher
        + "<br>" + item.volumeInfo.publishedDate
        + "<br>" + item.volumeInfo.imageLinks.thumbnail
        + "<br>" + item.volumeInfo.imageLinks.
        ;
        
      }
    }
    </script>
    <script src="https://www.googleapis.com/books/v1/volumes?q=9788979144086&callback=handleResponse"></script> -->
    <form action="${context}/sendAll" method="post">
    <input type="submit" />
    </form>
  </body>
</html>
      