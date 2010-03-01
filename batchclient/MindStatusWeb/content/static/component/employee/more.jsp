<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
<head>
<base target=_self>
<title>思想状况详情</title>
<%@ include file="/static/common/scripts.jsp"%>
<script language="javascript" src="<%=currentPath%>/proc.js"></script>
</head>
<body>
<logic:notEmpty name="success">
	<script language=JavaScript type=text/JavaScript>
		alert("数据保存成功！");
	</script>
</logic:notEmpty> 
<html:form action="/processEmployeeEdit" onsubmit="return validate(this)">
	<table cellspacing="1" class="main_table1">
		<tr>
        	<td> 
				<table cellspacing="0" class="main_table1">
			    	<tr> 
			      		<td class="text_title">思想状况详情</td>      		
			    	</tr>
				</table>  
			</td>
		</tr>
		<tr>
  			<td>
 				<table id="tblData" cellspacing="0"  bgcolor="#FFFFFF" class="main_table3">
					<tr>
						<td colspan="8" class="clsprompt">基本状况：</td>
					</tr>
					<tr>
						<td width="10%">姓名</td>
						<td width="15%"><html:text property="name" size="15" maxlength="20" styleClass="clsInput"/>&nbsp;<span class="clsFillin">*</span></td>
 						<td width="10%">生日</td>
 						<td width="15%">
 							<html:text property="birthday" size="15" maxlength="15" style="ime-mode:disabled" onfocus="if (window.calendar) new calendar(event.srcElement, 0, 1);" styleClass="clsInput"/>&nbsp;<span class="clsFillin">*</span>
 						</td>
 						<td width="10%">年龄</td>
						<td width="15%"><html:text property="age" size="15" maxlength="15" readonly="true"  styleClass="clsInput"/></td>
						<td width="10%">性别</td>
 						<td width="15%">
 							<div class="box"><div class="box2"><html:select property="sex" styleClass="clsInput">
								<html:options collection="sexOption" property="value" labelProperty="label"/>
							</html:select></div></div>
						</td>						
 					</tr>
 					<tr>
 						<td width="10%">学历</td>
						<td width="15%">
							<div class="box"><div class="box2"><html:select property="eduQualification">
								<html:options collection="eduQualificationOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>政治面貌</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="politicalAppearance">
								<html:options collection="politicalAppearanceOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>工作类别</td>
						<td>
							<div class="box"><div class="box2"><html:select property="category">
								<html:options collection="categoryOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>岗位</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="station">
								<html:options collection="stationOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 					</tr>
 					
 					<tr>
						<td colspan="8" class="clsprompt">常规行为特征：</td>
					</tr>
 					<tr>						
						<td>性格</td>
						<td>
							<div class="box"><div class="box2"><html:select property="kidney">
								<html:options collection="kidneyOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>爱好</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="interest">
								<html:options collection="interestOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td> 
 						<td>同事关系</td>
						<td>
							<div class="box"><div class="box2"><html:select property="associateRelation">
								<html:options collection="associateRelationOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>社交情况</td>
						<td>
							<div class="box"><div class="box2"><html:select property="community">
								<html:options collection="communityOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>						
 					</tr>
 					<tr>
 						<td>学习态度</td>
						<td>
							<div class="box"><div class="box2"><html:select property="studyAttitude">
								<html:options collection="studyAttitudeOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>服务态度</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="serviceAttitude">
								<html:options collection="serviceAttitudeOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>社会活动</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="campaign">
								<html:options collection="campaignOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td> 						
 						<td>是否业余经商</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="afterHoursBusiness">
								<html:options collection="afterHoursBusinessOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td> 
 					</tr>
 					<tr>
 						<td>家庭生活状况</td>
						<td colspan="7">
							<div class="box"><div class="box2"><html:select property="economy">
								<html:options collection="economyOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 					</tr>
 					<tr>
						<td colspan="8" class="clsprompt">近期动态：</td>
					</tr>
 					<tr>
 						<td>夫妻关系</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="familyRelation">
								<html:options collection="familyRelationOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>奖励情况</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="award">
								<html:options collection="awardOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>  
						<td>近期综合表现</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="performance">
								<html:options collection="performanceOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>模范作用发挥</td>
						<td>
							<div class="box"><div class="box2"><html:select property="modelEffect">
								<html:options collection="modelEffectOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td> 						 
 					</tr>
 					<tr>
 						<td>岗位技能</td>
						<td colspan="7">
							<div class="box"><div class="box2"><html:select property="skill">
								<html:options collection="skillOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 					</tr>
 					<tr>
						<td colspan="8" class="clsprompt">针对性思想工作：</td>
					</tr>
 					<tr>
						<td>是否需要做工作</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="enlighten">
								<html:options collection="enlightenOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>工作方式</td>
						<td>
							<div class="box"><div class="box2"><html:select property="mindPattern">
								<html:options collection="mindPatternOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>工作结果</td>
 						<td colspan="3">
 							<div class="box"><div class="box2"><html:select property="jobResult">
								<html:options collection="jobResultOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 					</tr>
 					<tr>	
						<td colspan="8" class="clsprompt">诚信记录：</td>
					</tr>
					<tr>	
						<td>遵守规定</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="rule">
								<html:options collection="ruleOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>履行职责</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="responsibility">
								<html:options collection="responsibilityOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>安全意识</td>
						<td>
							<div class="box"><div class="box2"><html:select property="security">
								<html:options collection="securityOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>责任意识</td>
						<td>
							<div class="box"><div class="box2"><html:select property="duty">
								<html:options collection="dutyOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td> 
 					</tr>
 					<tr>
						<td>忠实守信</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="faith">
								<html:options collection="faithOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>道德水平</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="morality">
								<html:options collection="moralityOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td> 
						<td>践行承诺</td>
						<td>
							<div class="box"><div class="box2"><html:select property="promise">
								<html:options collection="promiseOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>诚实严谨</td>
						<td>
							<div class="box"><div class="box2"><html:select property="honesty">
								<html:options collection="honestyOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td> 						
 					</tr> 					
 					<tr>	
						<td colspan="8" class="clsprompt">人才库：</td>
					</tr> 					
					<tr>
						<td>技术</td>
						<td>
							<div class="box"><div class="box2"><html:select property="technology">
								<html:options collection="technologyOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>管理</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="management">
								<html:options collection="managementOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
						<td>文艺</td>
						<td>
							<div class="box"><div class="box2"><html:select property="art">
								<html:options collection="artOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 						<td>体育</td>
 						<td>
 							<div class="box"><div class="box2"><html:select property="sports">
								<html:options collection="sportsOption" property="value" labelProperty="label" />
							</html:select></div></div>
						</td>
 					</tr>
 					<tr>
						<td>备注</td>
						<td colspan="7"><html:textarea property="memo" rows="3" cols="90"  styleClass="clstext"/></td>
 					</tr>
 			</table>
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
<html:hidden property="id"/>
</html:form>
</body>
</html:html>