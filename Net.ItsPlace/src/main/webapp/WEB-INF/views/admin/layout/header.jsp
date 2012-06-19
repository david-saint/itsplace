<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
 <div id="alertMessage" class="error"></div>
		<!-- Header -->
        <div id="header">
                <div id="account_info"> 
                    <img src="<sec:authentication property="Principal.User.profileImageUrl" />" alt="Online" class="avatar"/>
					<div class="setting" title="Profile Setting"><b>반갑습니다,</b> <b class="red"><sec:authentication property="Principal.User.Name" />님</b><img src="<c:url value="/resources/admin/images/gear.png" />" class="gear"  alt="Profile Setting" ></div>
					<div class="logout" title="Disconnect"><b >Logout</b> <img src="<c:url value="/resources/admin/images/connect.png" />" name="connect" class="disconnect" alt="disconnect" ></div> 
                </div>
        </div><!-- End Header -->
			<div id="shadowhead"></div>
                   
              <div id="left_menu">
                    <ul id="main_menu" class="main_menu">
                      <li class="limenu0 select"><a href="<c:url value="/admin/dashboard" />"><span class="ico gray shadow home" ></span><b>Dashboard</b></a></li>
                      <li class="limenu1" ><a href="<c:url value="/admin/base/list" />" ><span class="ico gray shadow window"></span><b>기초코드</b></a>
                      </li>
                      <li class="limenu" ><a href="<c:url value="/admin/user/list" />"><span class="ico gray  dimensions" ></span><b>사용자관리</b></a>
                      </li>
                      <li class="limenu" ><a href="<c:url value="/admin/place/list" />"><span class="ico gray shadow  spreadsheet"></span><b>가맹점관리 </b> </a></li>
                      <li class="limenu" ><a href="<c:url value="/admin/stamp/list" />"><span class="ico gray shadow pictures_folder"></span><b>스탬프관리  </b></a></li>
                      <li class="limenu" ><a href="<c:url value="/admin/event/list" />"><span class="ico gray shadow stats_lines"></span><b>이벤트관리</b> </a>                       
                      </li>
                    

                        
                    </ul>
               </div>
               
<div id="content">
                <div class="inner">
					<div class="topcolumn">
						<div class="logo"></div>
                            <ul  id="shortcut">
                                <li> <a href="#" title="Back To home"> <img  src="<c:url value="/resources/admin/images/icon/shortcut/home.png"/>" alt="home"/><strong>Home</strong> </a> </li>
                                <li> <a href="#" title="Website Graph"> <img src="<c:url value="/resources/admin/images/icon/shortcut/graph.png"/>" alt="graph"/><strong>Graph</strong> </a> </li>
                                <li> <a href="#" title="Setting" > <img src="<c:url value="/resources/admin/images/icon/shortcut/setting.png"/>" alt="setting" /><strong>Setting</strong></a> </li> 
                                <li> <a href="#" title="Messages"> <img src="<c:url value="/resources/admin/images/icon/shortcut/mail.png"/>" alt="messages" /><strong>Message</strong></a><div class="notification" >10</div></li>
                            </ul>
							<div class="clear"></div>
					</div>   
                    <div class="clear"></div>
                    
          