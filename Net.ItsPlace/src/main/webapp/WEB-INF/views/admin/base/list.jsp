<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.ui-corner-all, .ui-corner-top, .ui-corner-right, .ui-corner-tr {
-moz-border-radius-topright: 4px;
-webkit-border-top-right-radius: 4px;
-khtml-border-top-right-radius: 4px;
border-top-right-radius: 4px;
}
.ui-corner-all, .ui-corner-top, .ui-corner-left, .ui-corner-tl {
-moz-border-radius-topleft: 4px;
-webkit-border-top-left-radius: 4px;
-khtml-border-top-left-radius: 4px;
border-top-left-radius: 4px;
}



</style>
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
                    
					<!-- full width -->
                    <div class="widget">
                        <div class="header"><span><span class="ico gray home"></span> 기초코드  </span>
                            
						</div><!-- End header -->	
                        <div class="content">						
                        	
								<div class="" style="text-align:right">
								
									<form:select id="grpCd" path="grpBasCdList"  multiple="false" >
										<form:option value="" label="전체"/>
										<form:options items="${grpBasCdList}" itemValue="grpCd" itemLabel="grpName" />
									</form:select>
									 <script type="text/javascript">
										 $(document).ready(function(){
											$('#grpCd').change(function(){
												//alert($('#grpCd').val());
											});
									  	 });
									 </script>
								
								</div>																						
							
							 <table class="display static" id="static">
                                <thead>
                                  <tr>
                                    <th width="100" >그룹명</th>
                                    <th width="100" >그룹코드</th>
                                    <th width="100" >코드명</th>
                                    <th width="100" >코드</th>
                                    <th width="200" >Remark</th>
                                     <th width="199" >Management</th>
                                  </tr>
                                </thead>
                                <tbody>
                      	        <c:forEach items="${basCdList}" var="basCd">
			           	        	<tr>
	                                	<td align="left">${basCd.grpName}</td>
	                                    <td>${basCd.grpCd}</td>
	                                    <td>${basCd.basName}</td>
	                                    <td>${basCd.baseCd}</td>
	                                    <td>${basCd.remark}</td>
	                                    <td >
                                      	<span class="tip" >
                                          <a  title="Edit" >
                                              <img src="<c:url value="/resources/admin/images/icon/icon_edit.png" />" >
                                          </a>
                                      </span> 
                                      <span class="tip" >
                                          <a id="1" class="Delete"  name="Band ring" title="Delete"  >
                                              <img src="<c:url value="/resources/admin/images/icon/icon_delete.png" />" >
                                          </a>
                                      </span> 
                                        </td>
                                  </tr>
								</c:forEach>

                                
                                </tbody>
                              </table>
							
                            
	

                            <!-- clear fix -->
							<div class="clear"></div>
							
                        </div><!-- End content -->
                    </div><!-- End full width -->
                    
                   
					<!-- clear fix -->
					<div class="clear"></div>

                    <div id="footer"> &copy; Copyright 2012 <span class="tip"><a  href="#" title="Zice Admin" >ItsPlace</a> </span> </div>

                </div> <!--// End inner -->
              </div> <!--// End content -->    