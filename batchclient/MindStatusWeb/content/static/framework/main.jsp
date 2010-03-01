<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<HTML>
<HEAD>
<title>职工思想状况管理系统</title>
<%
String appContextPath = request.getContextPath();
String imgPath = appContextPath + "/static/framework/theme/images";
%>
<script language="javascript">
	var imgPath = "<%=imgPath%>/";
	var contextPath = "<%=appContextPath%>";
</script>
		<style type="text/css">
    		@import "<%=appContextPath%>/static/framework/theme/framework.css";
		</style>
		<script language="javascript" src="<%=appContextPath%>/static/common/base/tools.js"></script>
		<script language=JavaScript type=text/JavaScript src="<%=appContextPath%>/static/framework/proc.js"></script>
		<script language=JavaScript type=text/JavaScript>
		<!--
			function MM_reloadPage(init) 
			{  //reloads the window if Nav4 resized
  				if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    				document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  					else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
			}
			MM_reloadPage(true);
		//-->
		</SCRIPT>
	</HEAD>
<BODY onload="init()" leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
  		<TBODY>
  			<TR>
    			<TD width=432><IMG height=55 src="<%=imgPath%>/qtop5.jpg" width=432 border=0></TD>
    			<TD vAlign=bottom align=right noWrap width=500>
    				<span>
						<span class="loginperson"></span>&nbsp;登录用户：<span id="spanUserName" class="systemContent">&nbsp;
							<logic:present name="current_user">
								<bean:write name="current_user" property="realName"/>
							</logic:present>
						</span>
					</span>
    			</TD>
    			<TD vAlign=bottom align=right noWrap >
    				<span>
    					<span class="clsmessageOver" title="重新登陆" onclick="window.location='<%=appContextPath%>/processLogoff.do'"><span class="clsrelogin"></span>注 销
    				</span>
    			</TD> 
    			<TD vAlign=bottom align=right noWrap width=10>    				
    			</TD>     			
    		</TR>
    	</TBODY>
    </TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  		<TBODY>
  			<TR>
   				 <TD width=748 background=<%=imgPath%>/qback.gif height=30>
      				<TABLE cellSpacing=0 cellPadding=0 border=0>
        				<TBODY>
        					<TR>
          						
          					</TR>
          				</TBODY>
          			</TABLE>
          		</TD>
          	</TR>
          </TBODY>
		</TABLE>
		<TABLE height="80%" cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TBODY>
				<TR>
					<TD width=150px>
			 				<TABLE style="WIDTH: 145px; HEIGHT: 100%" cellSpacing=0 cellPadding=0 bgColor=#aca899 border=2 vspace="0" hspace="0">
								<TBODY>
									<TR>
										<TD id=outLookBarShow style="HEIGHT: 80%" align=middle name="outLookBarShow">
											<DIV id=o1 style="HEIGHT: 100%"></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
					</TD>
					<TD align=left><IFRAME id="frm" name="frm" src="<%=appContextPath%>/prepareEmployeeInsert.do" frameBorder=0 width="100%" height=100%></IFRAME>
				    </TD>
				 </TR>
			</TBODY>
		</TABLE>
		<SCRIPT language=JavaScript src="<%=appContextPath%>/static/framework/menu.js"></SCRIPT>
			
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  			<TBODY>
  				<TR>
    				<TD width=1 bgColor=#615f64 height=30><BR></TD>
      				<TD align=middle background=<%=imgPath%>/qback.gif>&nbsp;</TD>
    			</TR>
  			</TBODY>
		</TABLE>
	</BODY>
</HTML>
