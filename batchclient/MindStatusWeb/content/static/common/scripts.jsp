<%@page language="java" import="org.apache.struts.util.LabelValueBean,java.util.List,java.util.Iterator" contentType="text/html; charset=utf-8"%>
<%!
	String getOptionLabel(Object obj,Object index){
		Integer indexInt=(Integer)index;
		List optionList=(List)obj;
		Iterator it=optionList.iterator();
		while(it.hasNext()){
			LabelValueBean labelValueBean=(LabelValueBean)it.next();
			String value=labelValueBean.getValue().trim();
			if(value!=null && !"".equals(value) && Integer.parseInt(value)==indexInt.intValue()){
				return labelValueBean.getLabel();
			}
		}
		return "";
	}
%>
<%
	String appContextPath = request.getContextPath();
	String currentPath=appContextPath+request.getServletPath().substring(0,request.getServletPath().lastIndexOf('/'));
    String imgPath = appContextPath + "/static/common/theme/images";
%>
<style type="text/css">
    @import "<%=appContextPath%>/static/common/theme/css/default-manage.css";
</style>
<script language="javascript">
	var imgPath = "<%=imgPath%>/";
	var contextPath = "<%=appContextPath%>";
</script>

<script language="javascript" src="<%=appContextPath%>/static/common/base/calendar/calendar.js"></script>
<script language="javascript" src="<%=appContextPath%>/static/common/base/tools.js"></script>
		
