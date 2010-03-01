<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html xhtml="true" lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统异常</title>
<jsp:include page="/static/common/scripts.jsp" flush="true"/>
</head>
<body>
<table> 
	<tr>
  		<td></td> 	
  	</tr> 
  	<tr>
  		<td><html:errors/></td> 	
  	</tr>
</table>
</html:html>