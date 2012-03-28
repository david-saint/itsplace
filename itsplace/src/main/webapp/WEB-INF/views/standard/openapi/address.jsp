<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Daum 주소→좌표 변환API 테스트 페이지</title> 
<script type="text/javascript" src="http://apis.daum.net/maps/maps2.js?apikey=c70f1cec73528a81f8888026073984d89ec37e38"></script> 
<script type="text/javascript"> 
var obj = {
	apikey: "c70f1cec73528a81f8888026073984d89ec37e38",
	init : function()
	{
		obj.q = document.getElementById('q');
		obj.b = document.getElementById('b');
		obj.r = document.getElementById('r');
		obj.b.onclick = obj.pingSearch;
	},
	// 검색을 요청하는 함수 
 	pingSearch : function()
 	{
	    if (obj.q.value)
	    {
	      obj.s = document.createElement('script');
	      obj.s.type ='text/javascript';
	      obj.s.charset ='utf-8';		  
	      obj.s.src = 'http://apis.daum.net/maps/addr2coord?apikey=' + obj.apikey + '&output=json&callback=obj.pongSearch&q=' + encodeURI(obj.q.value);	
	      document.getElementsByTagName('head')[0].appendChild(obj.s);
	    }
 	},
 	// 검색 결과를 뿌리는 함수 
	pongSearch : function(z)
	{
		obj.r.innerHTML = '';
		if(!z.channel || z.channel.item.length <= 0)
		{
			obj.r.innerHTML = "검색 결과가 없습니다.";
			return;
		}
		else
		{
			for (var i = 0; i < z.channel.item.length; i++)
			{
				var li = document.createElement('li');
				var a = document.createElement('a');
				a.href = "javascript:obj.addMark(" + z.channel.item[i].point_y + ", " + z.channel.item[i].point_x + ");";
				a.innerHTML = obj.stripHTMLtag(obj.escapeHtml(z.channel.item[i].title));
 
				li.appendChild(a);
				obj.r.appendChild(li);
			}
		}
	},
	// HTML태그 안 먹게 하는 함수
	escapeHtml : function(str) 
	{
		str = str.replace(/&amp;/g, "&");
		str = str.replace(/&lt;/g, "<");
		str = str.replace(/&gt;/g, ">");
		return str;
	},
	// HTML태그 삭제하는 함수
	stripHTMLtag : function(string) {
		var objStrip = new RegExp();
		objStrip = /[<][^>]*[>]/gi;
		return string.replace(objStrip, "");
	},
	// 특정 좌표에 마커 추가
	addMark : function(lat, lng)
	{
		var point = new DLatLng(lat, lng);
		var mark = new DMark(point);
		map.addOverlay(mark);
		map.setCenter(point, 2);
	}
};
window.onload = function()
{
	obj.init();
	obj.pingSearch();
};
</script> 
 
</head> 
<body> 
	<div id="map" style="width:600px;height:500px;"></div> 
	<script type="text/javascript" defer="true"> 
var map = new DMap("map", {point:new DLatLng(35.78879895934866, 127.93130020103005), level:11}); 
</script> 
	주소 검색(<span style="color:red">검색한 주소를 클릭하면 해당 위치로 갑니다.</span>)
	<br/> 
	<input type="text" name="q" id="q" value="양재"/> 
	<button id="b">검색</button> 
	<div id="r"></div> 
	
</body> 
</html> 