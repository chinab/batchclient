<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
<head>
<title>职工思想状况管理系统</title>
<%@ include file="/static/common/scripts.jsp"%>
<style type="text/css">
<!--
.clsinput {
	font-size:9pt;
	border: 1px dashed #3B2C8A;
}
-->
</style>
</HEAD>

<body onload="init()" bottomMargin=0 bgColor=#ffffff leftMargin=0 topMargin=0 rightMargin=0 marginheight="0" marginwidth="0">

<html:form action="/processLogon" onsubmit="return validate(this)">
<table width="750" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr> 
		<td height="16" colspan="2">&nbsp;</td>
	</tr>
	<tr align="right" valign="top"> 
		<td  colspan="2" bgcolor="#000000" height=1 style="font-size:1px;"> 
		</td>
	</tr>	
	<tr align="center"> 
		<td  colspan="2"> 
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr> 
					<td  align="center" valign="middle" height="270"> 
						<table width="90%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" style="BORDER-COLLAPSE: collapse;font-size:9pt;color:#522929;">
							<tr> 
								<td  align="center" > 
									<table width="100%"  border="0">
										<tr>
											<td width="60%"  align="center"><img src="<%=imgPath%>/logon/logon.gif"></td>
											<td width="40%" align="center">
												<table width="90%" border="0" cellpadding="4" cellspacing="6" style="font-size:9pt;color:#3B2C8A;">
													<tr> 
														<td colspan="2">请输入用户信息：</td>
													</tr>
													<tr> 
														<td width="21%">帐&nbsp;&nbsp;号：</td>
														<td width="79%"><html:text style="font-size:9pt;border:1px dashed #3B2C8A;width=70px" maxlength="10" property="userName" size="10"  title="请输入您的帐号" onkeypress="moveFocus(this)"/></td>
													</tr>
													<tr> 
														<td>口&nbsp;&nbsp;令：</td>
														<td><html:password property="password" size="10" maxlength="10" style="font-size:9pt;border:1px dashed #3B2C8A;width=70px" title="请输入您的密码" onkeypress="moveFocus(this)"/>
														</td>
													</tr>													
													<tr align="left">
														<td colspan=2>
															<input type=submit  name=go style="BACKGROUND-IMAGE: url(<%=imgPath%>/logon/login.gif); 
                          										BACKGROUND-COLOR: #1094a1; border: none; BORDER-CORLOR:#1094a1; WIDTH: 40px; HEIGHT: 19px" value=" " title="填好了,现在登录">
                          										&#x00A0;&#x00A0;&#x00A0;
                          									<input type=reset  name=re style="BACKGROUND-IMAGE: url(<%=imgPath%>/logon/reset.gif); 
                          										BACKGROUND-COLOR: #1094a1; border: none; BORDER-CORLOR:#1094a1; WIDTH: 40px; HEIGHT: 19px" value=" " title="重新填写">
														</td>
													</tr>
													<tr>
    													<td colSpan=2 align=center><span id="errInfo" style='font-size:9pt;color:red;'></span>
    													</td>
  													</tr>
												</table>
											</td>
										</tr>
									</table> 
                    			</td>
							</tr>
						</table>
						<br>
					</td>
				</tr>
			</table>         
		</td>
	</tr>
	<tr align="right" valign="top"> 
		<td  colspan="2" bgcolor="#000000" height=1 style="font-size:1px;"> 
		</td>
	</tr>
	<tr align="right" valign="top"> 
		<td  colspan="2"> 
			<table width="62%" border="0" cellspacing=0 cellpadding=0>
				<tr>
					<td width="50%" style="font-size:9pt;">Copyright@2008&nbsp;版权所有&nbsp;大连供电公司</font></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:hidden property="errorCode"/>
</html:form>
</body>


<SCRIPT language=JavaScript>
function init() {
	setFocus();
	var errInfo = new Array ("",
			"该用户名不存在!",
	        "密码错误!");
	var errCode = logonForm.errorCode.value;
	if (errCode=="" || errCode > errInfo.length || errCode < 0) {
	        errCode = 0;
	}
	
	if (errCode!=null){
		logonForm.all('errInfo').innerHTML = errInfo[errCode];
	}
	if (errCode == 1) {
		logonForm.userName.focus();
		logonForm.userName.select();
	}
	if (errCode == 2) {
		logonForm.password.focus();
		logonForm.password.select();
	}
}

function validate() {
	var userNameValue=logonForm.userName.value;
	var passwordValue=logonForm.password.value;
	if(userNameValue==""){
		alert("请输入用户名！");
		return false;
	} 
	if(passwordValue==""){
		alert("请输入密码！");
		return false;
	}
	setCookie("userName",userNameValue,30);
	return true;
}


function moveFocus(field) {
	if (event.keyCode == 13) {
		if (field.name == "userName") {
			logonForm.password.focus();
			logonForm.password.select();
			event.returnValue = false;
		} else if (field.name == "password") {
			logon();
			event.returnValue = false;
		} 
	}
}

function setFocus() {
	var sUserName = getCookie("userName");
	if (sUserName!="")
		logonForm.userName.value = sUserName;
	logonForm.userName.focus();
	logonForm.userName.select();
}

function setCookie(name,value,expires) {
	if (!isNaN(parseFloat(expires))) {
		var expDate = new Date();
		expDate.setTime( expDate.getTime() + parseFloat(expires)*24*60*60*1000);
		var cookieExpires = expDate.toGMTString();
		document.cookie = name + "=" + escape(value) + ";expires=" + cookieExpires + ";";	
	} else
		document.cookie = name + "=" + escape(value) + ";";
}

function getCookie(name) {
	var arg  = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while(i < clen ) {
		var j = i + alen;
		if( document.cookie.substring(i,j) == arg ) {
			var endstr = document.cookie.indexOf(";",j);
			if( endstr == -1 )
				endstr = document.cookie.length;
			return unescape( document.cookie.substring(j, endstr) );	
		}
		i = document.cookie.indexOf(" ",i) + 1;
		if( i == 0 )
			break;
	}
	return "";
}

</SCRIPT>
</html:html>
