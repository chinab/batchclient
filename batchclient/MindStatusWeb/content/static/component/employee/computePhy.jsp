<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
<head>
<base target=_self>
<title>生理周期预测</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>

<html:form action="/processComputePhy"  onsubmit="return validatePhy(this)">
	<table cellspacing="1" class="main_table1" width="800">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">生理周期预测<br></td>    
			      		<td align="right" valign="middle"><span class="clsopenquery" onclick="onDisplayBlock(this,'trQueryCon',7)"></span></td>		
			    	</tr>
				</table>  
			</td>
		</tr>
		<tr id="trQueryCon">
  			<td>  			
 				<table class="main_table1">
 					<tr>
 						<td>
 							<table id="tblData" bordercolordark="#FFFFFF" bordercolorlight="#E8E8E8" cellspacing="0"  bgcolor="#FFFFFF" class="main_table3">
 					<tr>
						<td width="15%">出生日期<br></td>
 						<td width="35%">
 							<html:text property="birthday" size="15" maxlength="15" styleClass="clsInput" style="ime-mode:disabled" onfocus="if (window.calendar) new calendar(event.srcElement, 0, 1);"/>&nbsp;<span class="clsFillin">*</span>
 						<br></td> 
 						<td width="15%">当前日期<br></td>
 						<td width="35%">
 							<html:text property="nowDay" size="15" maxlength="15" styleClass="clsInput" style="ime-mode:disabled" onfocus="if (window.calendar) new calendar(event.srcElement, 0, 1);"/>
 						</td> 
 					</tr>
 				</table>
 						</td>
 					</tr>
 					<tr>
 						<td style="height:40;" colspan=8 align="right">
							<table cellspacing="0" class="main_table1">
								<tr> 
									<td align="right"> 
										<input type="button" class="button1" value="查询" onclick="onPhy()"/>
										<input type="reset"  class="button1" value="重置" />
									</td>
								</tr>
							</table>
						</td>
 					</tr>
 				</table>
 			</td>
 		</tr>	
					
 	
 			
		<logic:present name="chartFileName">
			<script language=JavaScript type=text/JavaScript>
				document.all("trQueryCon").style.display = "none";
			</script>
			<tr>
			<td> 
				<table id="tblData" cellspacing="0"  bgcolor="#FFFFFF" class="main_table3">
 					<tr>
 						<td width="20%">年龄</td>
						<td width="30%"><html:text property="age" size="15" styleClass="clsInput" maxlength="15" readonly="true"/></td>
						<td width="20%">生命日</td>
 						<td width="30%"><html:text property="lifeDays" size="15" styleClass="clsInput" maxlength="15" readonly="true"/></td>
 					</tr>
 					<tr>
						<td>体能指数</td>
						<td>
							<html:text property="physical" size="15" maxlength="15" styleClass="clsInput" readonly="true"/>
						</td>
 						<td>情感指数</td>
 						<td>
 							<html:text property="feeling" size="15" maxlength="15" styleClass="clsInput" readonly="true"/>
						</td> 
 					</tr>
 					<tr>
						<td>智力指数</td>
						<td>
							<html:text property="intelligence" size="15" maxlength="15" styleClass="clsInput" readonly="true"/>
						</td>
 						<td>平均值</td>
 						<td>
 							<html:text property="average" size="15" maxlength="15" styleClass="clsInput" readonly="true"/>
						</td> 
 						</tr>
 					</table>		
				</td> 
			</tr>
			<tr>
				<td align="center">
					<img background-color: #e9e9e9; src="<%=request.getAttribute("graphUrl")%>" width="850" height="400" border=0 usemap="#<%=request.getAttribute("fileName")%>">
				</td>
			</tr>
		</logic:present>
	</table>
	
</html:form>		

</body>
</html:html>