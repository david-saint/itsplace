<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 <div id="alertMessage" class="error"></div>
<div id="header">
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#places').change(function(){
	    		//c.log("/place?fid="+$(this).val());
	    		changePlace($(this).val());
	    		
	    		//document.location.href="/place?fid="+$(this).val();
	    	});	 
			/* $.ajax({
	            url: "/places",
	            type:"POST",
	            beforeSend :function(){
	            },
	            success: function(data){
		           	 if(data.status=="SUCCESS"){
		           		 var tag = "<select id=\"places\" style=\"margin-left:100px;\">";	
		           		
		           		 
		           		 $.each(data.result, function(i){
		               		tag+="<option  value=\""+data.result[i].fid+"\">"+data.result[i].fname+"</option>";
		               	 });	 
		           		 tag += "</select>";
		           		 $("#selectPlace").append(tag);
		           		 $("#places").selectmenu({
		                    style: 'dropdown',
		                    transferClasses: false,
		                    width: null
		                }); 
		           		 
		           	 }else{
		           		 
		           	 }
	            },
	            error: function(jqXHR, textStatus, errorThrown){
	           		alert(textStatus+"j"+jqXHR+"e:"+errorThrown); 
	            },
	            complete:function(){
	            c.log(	'');
	            	 var fid = ${fid};
	            	 c.log("fid:"+ fid);
	            	//$('#places').selectmenu(fid,$('#places').children(":first").val());
	            	$('#places').change(function(){
	    	    		c.log("/place?fid="+$(this).val());
	    	    		//changePlace($(this).val());
	    	    		//document.location.href="/place?fid="+$(this).val();
	    	    	});	           
	            }
	    	});//ajax 
	    	 */
	    	
		});
		function changePlace(fid){
			$.ajax({
	            url: "/changePlace",
	            type:"POST",
	            data:"fid="+fid,
	            beforeSend :function(){
	            },
	            success: function(data){
		           	 if(data.status=="SUCCESS"){
		           		 c.log("change place :"+ fid);		           		 
		           	 }else{
		           		 
		           	 }
	            },
	            error: function(jqXHR, textStatus, errorThrown){
	           		alert(textStatus+"j"+jqXHR+"e:"+errorThrown); 
	            },
	            complete:function(){
	            	document.location.reload();
	            }
	    	});//ajax 
		}
		</script>
  	  
     	
    <div id="account_info"> 
    	<img src="<sec:authentication property="Principal.User.profileImageUrl" />" alt="Online" class="avatar"/>
		<div class="setting" title="Profile Setting"><b>반갑습니다,</b> <b class="red"><sec:authentication property="Principal.User.Name" />님</b><img src="<c:url value="/resources/admin/images/gear.png" />" class="gear"  alt="Profile Setting" ></div>
		<div class="logout" title="Disconnect"><b >Logout</b> <img src="<c:url value="/resources/admin/images/connect.png" />" name="connect" class="disconnect" alt="disconnect" ></div> 
    </div>
</div><!-- End Header -->
			<div id="shadowhead"></div>
                   
        <div id="left_menu">
        	<ul id="main_menu" class="main_menu">
                <li class="limenu0 select"><a href="<c:url value="/place" />"><span class="ico gray shadow home" ></span><b>Dashboard</b></a></li>
                <li class="limenu1" ><a href="<c:url value="/place/stamp/list" />" ><span class="ico gray shadow window"></span><b>스탬프관리 </b></a>
                </li>
                <li class="limenu" ><a href="<c:url value="/place/user/list" />"><span class="ico gray  dimensions" ></span><b>직원관리</b></a>
                </li>
                <li class="limenu" ><a href="<c:url value="/place/edit" />"><span class="ico gray shadow  spreadsheet"></span><b>가맹점관리 </b> </a></li>
                <li class="limenu" ><a href="#"><span class="ico gray shadow stats_lines"></span><b>Graph and Charts</b> </a>
             
                <ul>
                    <li><a href="modalchartLive.html" class="pop_box">live chart </a></li>
                    <li><a href="chart.html">all chart</a></li>
                </ul>
                </li>
                <li class="limenu" ><a href="filemanager.html"><span class="ico gray shadow  file"></span><b>File manager </b></a></li>
                <li class="limenu " ><a href="calendar.html"><span class="ico gray shadow calendar"></span><b>Calendar </b></a></li>
                <li class="limenu" ><a href="typography.html"><span class="ico gray  shadow paragraph_align_left"></span><b>Typography</b></a></li>
                <li class="limenu" ><a href="inelement.html"><span class="ico gray shadow abacus"></span><b>Interface elements </b></a></li>
                <li class="limenu" ><a href="map.html"><span class="ico gray shadow  location"></span><b>Map location </b></a></li>
                <li class="limenu" ><a href="icon.html"><span class="ico gray  shadow satellite"></span><b>Icon and Button </b></a></li>
 			    <li class="limenu" ><a href="404.html"><span class="ico gray  shadow firewall"></span><b>Error Pages</b></a></li>
                <li class="limenu" ><a href="doc.html"><span class="ico gray  notepad"></span><b>Documentation</b></a></li>
            </ul>
         </div>
               
<div id="content">
                <div class="inner">
					<div class="topcolumn">
						<div  id="selectPlace" style="border:0px solid red;position:absolute;padding-top:15px">
							<sec:authentication property="Principal.placeListSelect" htmlEscape="false"/>
						</div>
						<div  class="logo">
							
						</div>
                            <ul  id="shortcut">
                                <li> <a href="#" title="Back To home"> <img  src="<c:url value="/resources/admin/images/icon/shortcut/home.png"/>" alt="home"/><strong>Home</strong> </a> </li>
                                <li> <a href="#" title="Website Graph"> <img src="<c:url value="/resources/admin/images/icon/shortcut/graph.png"/>" alt="graph"/><strong>Graph</strong> </a> </li>
                                <li> <a href="#" title="Setting" > <img src="<c:url value="/resources/admin/images/icon/shortcut/setting.png"/>" alt="setting" /><strong>Setting</strong></a> </li> 
                                <li> <a href="#" title="Messages"> <img src="<c:url value="/resources/admin/images/icon/shortcut/mail.png"/>" alt="messages" /><strong>Message</strong></a><div class="notification" >10</div></li>
                            </ul>
							<div class="clear"></div>
					</div>   
                    <div class="clear"></div>
                    
          