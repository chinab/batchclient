<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title> 员工思想状态管理系统</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <%@ include file="/static/common/scripts.jsp"%>
	
    <%-- 自定义CSS样式 --%>
    <link href="<%=appContextPath%>/static/common/css/houfei-loading.css" rel="stylesheet" type="text/css" />
    <link href="<%=appContextPath%>/static/common/css/houfei-icon.css" rel="stylesheet" type="text/css" />
    
    <%-- Ext必需的js/css文件 --%>
	<link href="<%=appContextPath%>/static/common/ext/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=appContextPath%>/static/common/ext/resources/css/file-upload.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="<%=appContextPath%>/static/common/ext/adapter/ext/ext-base.js"></script>
	<script language="javascript" src="<%=appContextPath%>/static/common/ext/ext-all-debug.js"></script>
	<script language="javascript" src="<%=appContextPath%>/static/common/ext/ext-lang-zh_CN.js"></script>
	<script language="javascript" src="<%=appContextPath%>/static/common/ext/TabCloseMenu.js"></script>
	<script language="javascript" src="<%=appContextPath%>/static/common/ext/FileUploadField.js"></script>	
	
	<!-- 自定义工具类 -->
	<script type="text/javascript" src="<%=appContextPath%>/static/common/utils/GridPanelUtils.js"></script>
	<%-- 主页 --%>
 	<script language="javascript" src="<%=currentPath%>/index.js"></script>
    
    <script type="text/javascript" src="<%=appContextPath%>/static/view/employee/employeeform.js"></script>
    <script type="text/javascript" src="<%=appContextPath%>/static/view/employee/add.js"></script>
    <script type="text/javascript" src="<%=appContextPath%>/static/view/employee/editgrid.js"></script>
    <script type="text/javascript" src="<%=appContextPath%>/static/view/employee/editwindow.js"></script>
    
    
	<script type="text/javascript">
	  Ext.onReady(function(){
           <%-- 加载效果 --%>
			setTimeout(function() {
				Ext.get('loading').remove();
				Ext.get('loading-mask').fadeOut({remove:true});
			}, 1000); 
	       <%-- 开启提示 --%>
	        Ext.QuickTips.init() ;
			Ext.form.Field.prototype.msgTarget = "side" ;
			<%-- 实例化主页 --%>
		    var _index= new IndexPage();
		    <%-- 窗体大小改变延时事件 --%>
	        window.onresize=function(){
	        <%-- 过100毫秒在执行  --%>
	        setTimeout(_index.onActiveTabSize,100);
	        };
	  });
	</script>
  </head>
  
  <body>
         <div id="loading">
             <div  class="loading-indicator">
                  <img src="<%=imagePath%>/extanim32.gif" alt="" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>
         正在加载,请稍候......
             </div>
         </div>
         <div id="loading-mask">
         </div>
  </body>
</html>
