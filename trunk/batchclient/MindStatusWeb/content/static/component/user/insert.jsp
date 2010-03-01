<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
<head>
<title>新建用户</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>
<logic:notEmpty name="success">
	<script language=JavaScript type=text/JavaScript>
		alert("数据保存成功！");
	</script>
</logic:notEmpty>
<logic:notEmpty name="fail">
	<script language=JavaScript type=text/JavaScript>
		alert("保存失败,用户已经存在!");
	</script>
</logic:notEmpty> 
<html:form action="/processUserInsert" onsubmit="return validate(this)">
	<table cellspacing="1" class="main_table1">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">新建用户（带*项必填）</td>      		
			    	</tr>
				</table>  
			</td>
		</tr>
		<tr>
  			<td>
 				<table id="tblData" cellspacing="0"  bgcolor="#FFFFFF" class="main_table3">
					<tr>
						<td width="30%">用户名</td>
					
						<td>
							<html:text property="userName" size="15" styleClass="clsInput" maxlength="20"/>
							&nbsp;<span class="clsFillin">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%">真实姓名</td>
					
						<td>
							<html:text property="realName" size="15" styleClass="clsInput" maxlength="20"/>
							&nbsp;<span class="clsFillin">*</span>
						</td>
					</tr>
					<tr>
 						<td>性别</td>
 						<td>
 							<div class="box">
 								<div class="box2">
 									<html:select property="sex">
										<html:options collection="sexOption" property="value" labelProperty="label"/>
									</html:select>&nbsp;<span class="clsFillin">*</span></div></div>
						</td> 
 					</tr>
 					<tr>
 						<td>密码</td>
						<td><html:password property="password" size="15" styleClass="clsInput" maxlength="30"/>
							&nbsp;<span class="clsFillin">*</span>
						</td>
					</tr>
 					<tr>
						<td>确认密码</td>
						<td>
							<html:password property="confirmPassword" size="15" styleClass="clsInput" maxlength="30"/>
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
</html:html>