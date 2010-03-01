<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数据字典维护</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>
<logic:notEmpty name="success">
	<script language=JavaScript type=text/JavaScript>
		alert("数据保存成功！");
	</script>
</logic:notEmpty>
	<table cellspacing="1" class="main_table1">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">数据字典维护</td>    
			      		<td align="right" valign="middle"><span class="clsopenquery" onclick="onDisplayBlock(this,'trQueryCon',7)"></span></td>		
			    	</tr>
				</table>  
			</td>
		</tr>
		<tr>
  			<td>
  			<html:form action="/processPropListEdit" onsubmit="return validate(this)">  			
 				<table id="tblData" bordercolordark="#FFFFFF" bordercolorlight="#E8E8E8" cellspacing="0"  bgcolor="#FFFFFF" class="main_table3">
 					<tr>
 						<td>编号</td>
						<td>
							<html:text property="propListId" size="15" styleClass="clsInput" maxlength="15"/>
						</td>
						<td>名称</td>
 						<td>
 							<html:text property="propListName" size="15" styleClass="clsInput" maxlength="15"/>
 						</td>
 					</tr>				
		 			<tr>
						<td style="height:40;" colspan=8 align="right">
							<table cellspacing="0" class="main_table1">
							    <tr> 
							      	<td align="right"> 
							      		<input type="submit" class="button1" value="确定"/>
							      		<input type="reset"  class="button1" value="重置"/>
							      		<input type="button" class="button1" value="返回" onclick="onResume()"/>
							      	</td>
							    </tr>
							</table>
						</td>
					</tr>				
 				</table>
 				<html:hidden property="propTypeId"/>
 				<html:hidden property="id"/>
				</html:form>
 			</td> 			
		</tr>
		<logic:present name="msPropList">
		<tr>
			<td> 
				<table id="tblResult" sort="true" bordercolordark="#FFFFFF" bordercolorlight="#E8E8E8" cellspacing="0" bgcolor="#FFFFFF" class="main_table3">
					<thead>
						<tr class="clsListHead">	
							<td>编号</td>
							<td>名称</td>
							<td>新名称</td>
							<td>设置</td>
						</tr>
					</thead>
					<tbody>
							<logic:iterate id="element" name="msPropList">
								<tr>
									<td><bean:write name="element" property="propListId"/></td>
									<td><bean:write name="element" property="propListName"/></td>
									<td><input type="text" id='newListName<bean:write name="element" property="id"/>'></td>
									<td><img src="<%=imgPath%>/save.gif" style="cursor:hand" 
									onclick="onEdit('<bean:write name="element" property="id"/>','<bean:write name="element" property="propListId"/>')"></img></td>
								</tr>
							</logic:iterate>
					</tbody>
				</table>				
			</td> 
		</tr>
		</logic:present>
	</table>			
<a id="resume" href="<%=appContextPath%>/processPropTypeSearch.do" style="display:none" taget="_self"></a>
</body>
</html>