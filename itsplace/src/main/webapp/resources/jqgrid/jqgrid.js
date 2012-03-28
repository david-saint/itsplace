 var JQgrid = function () { };
 
 JQgrid.UserList = function () {
	  
	 $("#UserGrid").jqGrid({ 
		 	url:'user/JqUserList',
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
 };
 