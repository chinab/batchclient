<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>密码修改</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>
<logic:notEmpty name="success">
	<script language=JavaScript type=text/JavaScript>
		alert("密码修改成功！");
	</script>
</logic:notEmpty> 
<logic:notEmpty name="fail">
	<script language=JavaScript type=text/JavaScript>
		alert("旧密码错误！");
	</script>
</logic:notEmpty> 
<html:form action="/processPasswordModify" onsubmit="return doValidate(this)">
	<table cellspacing="1" class="main_table1">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">密码修改（带*项必填）</td>      		
			    	</tr>
				</table>  
			</td>
		</tr>
		<tr>
  			<td>
 				<table id="tblData" cellspacing="0"  bgcolor="#FFFFFF" class="main_table3">
					<tr>
						<td>旧密码</td>
						<td>
							<html:password property="oldPwd" style="ime-mode:disabled" styleClass="clsInput" size="15" maxlength="20"/>
							&nbsp;<span class="clsFillin">*</span></td> 						
 					</tr>
 					<tr>
 						<td>新密码</td>
						<td><html:password property="password" style="ime-mode:disabled" styleClass="clsInput" size="15" maxlength="30"/>
							&nbsp;<span class="clsFillin">*</span>
						</td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td>
							<html:password property="confirmPwd" style="ime-mode:disabled" styleClass="clsInput" size="15" maxlength="30"/>
							&nbsp;<span class="clsFillin">*</span>
						</td>
 					</tr> 					
 			</table>
		</td>		
	</tr>
 	<tr>
		<td style="height:30;">
			<table cellspacing="0" class="main_table1">
			    <tr> 
			      	<td align="right"> 
			      		<input type="submit" class="button1" value="保存" />
			      		<input type="reset"  class="button1" value="重置" />
			      	</td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
</html:form>

</body>
</html>