<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script language="Javascript" type="text/javascript">
var obj = {
	apikey: "3a8746bf6ea924e6cda4e5b54b78b89db61e85c2",
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
 		console.log("search:"+obj.q.value);
	    if (obj.q.value)
	    {
	      obj.s = document.createElement('script');
	      obj.s.type ='text/javascript';
	      obj.s.charset ='utf-8';
	      obj.s.src = 'http://apis.daum.net/search/blog?apikey=' + obj.apikey + '&output=json&sort=accu&callback=obj.pongSearch&q=' + encodeURI(obj.q.value);
	      document.getElementsByTagName('head')[0].appendChild(obj.s);
	    }
 	},
 	// 검색 결과를 뿌리는 함수 
	pongSearch : function(z)
	{
		console.log("search result:");
		obj.r.innerHTML = '';
		for (var i = 0; i < z.channel.item.length; i++)
		{
			console.log(z.channel.item[i].title);
			var li = document.createElement('li');
			var a = document.createElement('a');
			var p = document.createElement('p');
			a.href = z.channel.item[i].link;
			a.innerHTML = obj.escapeHtml(z.channel.item[i].title);
			p.innerHTML =  obj.escapeHtml(z.channel.item[i].description);
			
			li.appendChild(a);
			li.appendChild(p);
			obj.r.appendChild(li);
		}
	},
	// HTML태그 안 먹게 하는 함수
	escapeHtml : function(str) 
	{
		str = str.replace(/&/g, "&");
		str = str.replace(/</g, "<");
		str = str.replace(/>/g, ">");
		return str;
	}
};
window.onload = function()
{
  obj.init();
  obj.pingSearch(); 
};
</script>
<div id="divSearch">
		블로그 검색 예제
		<input id="q" type="text" value="안녕"/>
		<input id="b" type="submit" value="검색"/>
	</div>
	<div id="r"></div>