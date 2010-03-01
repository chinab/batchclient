<%@page language="java" import="org.apache.commons.lang.StringUtils" contentType="text/html; charset=utf-8"%>
<%
	String appContextPath = request.getContextPath();
	String currentPath = appContextPath+StringUtils.substringBeforeLast(request.getServletPath(),"/");
    String imagePath = appContextPath + "/static/common/images";
%>

<script language="javascript">
	var imagePath = "<%=imagePath%>/";
	var appContextPath = "<%=appContextPath%>";
</script>

