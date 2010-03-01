function init()
{
	window.setInterval('refreshSession()', 10000);
}

function refreshSession()
{	
	var strUrl=contextPath+"/refesh.do";
	var result=RequestData(strUrl, "Hello!");
}
