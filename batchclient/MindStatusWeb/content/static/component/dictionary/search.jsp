<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数据类型列表</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>
	<table cellspacing="1" class="main_table1">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">数据类型列表</td>      		
			    	</tr>
				</table>  
			</td>
		</tr>
		<logic:present name="msPropTypeList">
		<tr>
  			<td>
 				<table id="tblResult" sort="true" bordercolordark="#FFFFFF" bordercolorlight="#E8E8E8" cellspacing="0" bgcolor="#FFFFFF" class="main_table3">
					<thead>
						<tr class="clsListHead">	
							<td>序号</td>
							<td>名称</td>
							<td>说明</td>							
							<td>维护</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="element" name="msPropTypeList">
							<tr>
								<td><bean:write name="element" property="propTypeId"/></td>
								<td><bean:write name="element" property="propTypeName"/></td>
								<td><bean:write name="element" property="propTypeLabel"/></td> 
								<td><a href="<%=appContextPath%>/preparePropListEdit.do?propTypeId=<bean:write name="element" property="propTypeId"/>" target="_self">
									<img src="<%=imgPath%>/security.gif" border="0" style="cursor:hand" alt="编辑数组字典"></a>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>		
			</td>		
		</tr>
		</logic:present> 	
	</table>
</body>
</html>