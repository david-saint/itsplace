<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
 <div id="alertMessage" class="error"></div>
		<!-- Header -->
        <div id="header">
                <div id="account_info"> 
                    <img src="images/avatar.png" alt="Online" class="avatar"/>
					<div class="setting" title="Profile Setting"><b>Welcome,</b> <b class="red">John Doe</b><img src="/resources/admin/images/gear.png" class="gear"  alt="Profile Setting" ></div>
					<div class="logout" title="Disconnect"><b >Logout</b> <img src="/resources/admin/images/connect.png" name="connect" class="disconnect" alt="disconnect" ></div> 
                </div>
            </div><!-- End Header -->
			<div id="shadowhead"></div>
                   
              <div id="left_menu">
                    <ul id="main_menu" class="main_menu">
                      <li class="limenu select"><a href="dashboard.html"><span class="ico gray shadow home" ></span><b>Dashboard</b></a></li>
                      <li class="limenu" ><a href="#" ><span class="ico gray shadow window"></span><b>Form elements</b></a>
                        <ul>
                          <li ><a href="form.html"> basic form </a></li>
                          <li ><a href="vform.html"> validation </a></li>
                          <li ><a href="wizard.html"> wizard </a></li>
                        </ul>
                      </li>
                      <li class="limenu" ><a href="#"><span class="ico gray  dimensions" ></span><b>Sample pages</b></a>
                        <ul>
                          <li ><a href="profile.html"> Profile setting </a></li>
                          <li ><a href="conversation.html"> conversation</a></li>
                          <li ><a href="imagesEditor.html"> Images Editor </a></li>
                          <li ><a href="barcode.html"> barcode </a></li>
                          <li ><a href="messages.html"> messages </a></li>
                          <li ><a href="grid.html"> Grid System </a></li>
                        </ul>
                      </li>
                      <li class="limenu" ><a href="table.html"><span class="ico gray shadow  spreadsheet"></span><b>Tables</b> </a></li>
                      <li class="limenu" ><a href="gallery.html"><span class="ico gray shadow pictures_folder"></span><b>Gallery </b></a></li>
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
          