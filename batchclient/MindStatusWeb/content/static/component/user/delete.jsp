<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>删除用户</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>
<logic:notEmpty name="success">
	<script language=JavaScript type=text/JavaScript>
		alert("用户删除成功！");
	</script>
</logic:notEmpty> 
<html:form action="/processUserSearch">
	<html:hidden property="startIndex"/>
	<table cellspacing="1" class="main_table1">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">删除用户</td>      		
			    	</tr>
				</table>  
			</td>
		</tr>
		<logic:present name="msUserPage">
		<tr>
  			<td>
 				<table id="tblResult" sort="true" bordercolordark="#FFFFFF" bordercolorlight="#E8E8E8" cellspacing="0" bgcolor="#FFFFFF" class="main_table3">
					<thead>
						<tr class="clsListHead">	
							<td>用户名</td>
							<td>真实姓名</td>
							<td>性别</td>							
							<td>删除</td>
						</tr>
					</thead>
					<tbody>
							<bean:define id="msUserList" name="msUserPage" property="items"/>
							<logic:iterate id="element" name="msUserList">
								<tr>
									<td><bean:write name="element" property="userName"/></td>
									<td><bean:write name="element" property="realName"/>&nbsp;</td>
									<td>
										<logic:notEmpty name="element" property="sex">
											<bean:define id="index" name="element" property="sex"/>
											<%=getOptionLabel(request.getAttribute("sexOption"),index)%>	
										</logic:notEmpty>&nbsp;
									</td>										
									<td><img src="<%=imgPath%>/delete.gif" style="cursor:hand" onclick="onDelete('<bean:write name="element" property="id"/>','<bean:write name="element" property="userName"/>')"></img></td>
								</tr>
							</logic:iterate>
					</tbody>
				</table>
				<table width="100%">
					<tr>
						<td>共 <bean:write name="msUserPage" property="totalCount"/> 条记录&nbsp;第<bean:write name="msUserPage" property="currentPageNo"/>/<bean:write name="msUserPage" property="pageCount"/>页</td>
						<td>每页
							<html:select property="pageSize" onchange="onChangePageSize()">
								<html:options collection="pageSizeOption" property="value" labelProperty="label" />
							</html:select>
							条记录
						</td>
						<td align="right">
							<img src="<%=imgPath%>/gofirst.gif" style="cursor:hand" onclick="onMovePage('<bean:write name="msUserPage" property="firstIndex"/>')"></img>
							<img src="<%=imgPath%>/goprior.gif" style="cursor:hand" onclick="onMovePage('<bean:write name="msUserPage" property="previousIndex"/>')"></img>
							<img src="<%=imgPath%>/gonext.gif" style="cursor:hand" onclick="onMovePage('<bean:write name="msUserPage" property="nextIndex"/>')"></img>
							<img src="<%=imgPath%>/golast.gif" style="cursor:hand" onclick="onMovePage('<bean:write name="msUserPage" property="lastIndex"/>')"></img>
						</td>
					</tr>
				</table>		
			</td>		
		</tr>
		</logic:present> 	
	</table>
	
</html:form>
</body>
</html>