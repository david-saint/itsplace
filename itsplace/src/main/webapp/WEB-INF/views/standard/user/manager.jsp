<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

 <script type="text/javascript">

 $(document).ready(function(){
	 
	 $( "#accordion" ).accordion();
	 
	 $("#UserGrid").jqGrid({
	 	url:'<c:url value="/user/JqUserList" />',
	    datatype: 'json',
	    //postData: {},
	    mtype: 'GET',
	    	colNames:[ 'Last Name', 'Last Name', 'Last Name'],
	        colModel:[
						{name:'email',index:'email',key: true, width:100,editable:true},
						{name:'email',index:'email',key: true, width:100,editable:true},
			       		{name:'email',index:'email',key: true, width:100,editable:true}
	      			],
	    pager: $('#UserPager'),
	    rowNum:10, 
	    rowList:[10,20,40,60],
	    height: '200px',
	    autowidth: true,
	    rownumbers: true,
	   
	    sortname: 'name',
	    viewrecords: true,
	    sortorder: "asc",
	    caption:"Users",
	    emptyrecords: "Empty records",
	    jsonReader: { repeatitems: false },
	    loadonce: false,
	    gridview: true, //처리속도를 빠르게 해준다. 시간측정시 절반가량 로딩시간 감소!!! 하지만 다음 모듈엔 사용할 수 없다!! ==> treeGrid, subGrid, afterInsertRow(event)
	
	  }) ;
	
		
		
					  
});
 </script>

	
	
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
				GRID
			</h1>   
				
        		
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<div id="jqgrid">
				<table id="UserGrid"></table>
				<div id="UserPager"></div>
			</div>	
		</div><!-- content end -->
	</div>
	<footer>Footer</footer>
</div>

<!-- 
<p>
	<button type="button" id="btn_ajaxTest" > AjaxTest</button>
	<div id="testAjax"></div>
</p> 
<p>
	<button type="button" id="btn_JsonTest" > JsonTest</button>
	<div id="testJson"></div>
</p> 
<p>
	<button type="button" id="btn_JsonObject" > JsonObject</button>
	<div id="testObject"></div>
</p>
 -->
	   
	   
