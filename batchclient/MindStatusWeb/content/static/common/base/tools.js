function RequestData(strURL, postData) 
{
	if (postData == null || postData == "") {
	 	postData = "someData";	// 不发送空串
	}
	
	var classname = "Microsoft.XMLHTTP";
	var objXMLReq = null;
	try {
		objXMLReq = new ActiveXObject(classname);
	} catch(ex) {
		return null;
	}
	try {
		objXMLReq.open("POST", strURL, false);
		objXMLReq.setRequestHeader("User-Agent", classname);
		objXMLReq.send(postData);		
	} catch (ex) {
		var errMsg = "不能建立 HTTP 连接, 资源\r\n" + strURL + "\r\n不可用.\r\n" + ex.description;
		//alert(errMsg);
		return null;
	}

	var statusCode = objXMLReq.status;
	if (statusCode == 200) {
		var strResult = objXMLReq.responseText;
		return strResult;
	} else {
		var errMsg = "服务器处理请求失败.\r\n" + statusCode + ":" + objXMLReq.statusText;
		//alert(errMsg);
		return null;
	}
}

function showModalDialogTool(strURL,args,pFeature)
{
	sFeature = " center:yes; location:no; toolbar:yes; titlebar:no; resizable:yes; scrollbars:yes; status:no;";
	sFeature=sFeature+pFeature;
	var dlgWin = window.showModalDialog(strURL, args, sFeature);
	return dlgWin;
}

function showModelessDialogTool(strURL,args,pFeature)
{
	sFeature = " center:yes; location:no; toolbar:yes; titlebar:no; resizable:yes; scrollbars:yes; status:no;";
	sFeature=sFeature+pFeature;
	var dlgWin = window.showModelessDialog(strURL, args, sFeature);
	return dlgWin;
}