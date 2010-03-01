<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target=_self>
<title>生理周期详情</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>
<html:form action="/preparePhysiologyCycle" onsubmit="return validate(this)">
	<table cellspacing="1" class="main_table1">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">生理周期详情</td>      		
			    	</tr>
				</table>  
			</td>
		</tr>
		<tr>
  			<td>
 				<table id="tblData" cellspacing="0"  bgcolor="#FFFFFF" class="main_table3">
					<tr>
						<td width="20%">姓名</td>
						<td width="30%"><html:text property="name" size="15" maxlength="20"  styleClass="clsInput" readonly="true"/></td>
 						<td width="20%">生日</td>
 						<td width="30%">
 							<html:text property="birthday" size="15" maxlength="15"  styleClass="clsInput" readonly="true"/>
 						</td>
 					</tr>
 					<tr>
 						<td>年龄</td>
						<td><html:text property="age" size="15" maxlength="15"  styleClass="clsInput" readonly="true"/></td>
						<td>生命日</td>
 						<td><html:text property="lifeDays" size="15" maxlength="15"  styleClass="clsInput" readonly="true"/></td>
 					</tr>
 					<tr>
						<td>体能指数</td>
						<td>
							<html:text property="physical" size="15" maxlength="15"  styleClass="clsInput" readonly="true"/>
						</td>
 						<td>情感指数</td>
 						<td>
 							<html:text property="feeling" size="15" maxlength="15"  styleClass="clsInput" readonly="true"/>
						</td> 
 					</tr>
 					<tr>
						<td>智力指数</td>
						<td>
							<html:text property="intelligence" size="15" maxlength="15"  styleClass="clsInput" readonly="true"/>
						</td>
 						<td>平均值</td>
 						<td>
 							<html:text property="average" size="15" maxlength="15"  styleClass="clsInput" readonly="true"/>
						</td> 
 					</tr>
 			</table>
		</td>		
	</tr>
	<tr>
		<td>
			<logic:notEmpty name="chartFileName">
				<img src="<%=request.getAttribute("graphUrl")%>" width="740" height="400" border=0 usemap="#<%=request.getAttribute("fileName")%>">
			</logic:notEmpty>
		</td>
	</tr>
 	<tr>
		<td style="height:30;">
			<table id="tblBtn" cellspacing="0" class="main_table1">
			    <tr> 
			      	<td align="right"> 
			      		<input type="button" class="button1" value="打印" onclick="onPrint()"/>
			      		<input type="button" class="button1" value="关闭" onclick="top.close()"/>
			      	</td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<!--html:hidden property="id"/-->
</html:form>

</body>
</html>