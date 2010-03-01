function validate(editForm)
{
	var nameValue=editForm.name.value;
	var sexValue=editForm.sex.value;
	var birthdayValue=editForm.birthday.value;
	if(nameValue==""){
		alert("请输入姓名！");
		return false;
	} 
	if(sexValue==""){
		alert("请选择性别！");
		return false;
	}
	if(birthdayValue==""){
		alert("请输入生日！");
		return false;
	} 
	
	return true;
}

function onPhy()
{
	var birthday=document.physiologyCycleComputeForm.birthday.value;
	if(birthday==""){
		alert("请输入生日！");
		return;
	}else
	{
		document.physiologyCycleComputeForm.submit();
	} 
}

function getAge(){
	var brith=document.getElementById("birthday").value;
	if(brith!=null && brith!='')
	{
		brith=brith.substr(0,4);
		var aDate=new Date();   
  		var thisYear=aDate.getYear();
		age=(thisYear-brith+1);
		document.getElementById("age").value=age;
	}
}

function checknum(p) 
{   
	var l = p.length; 
	var count=0; 
	for(var i=0; i<l; i++) 
	{ 
		var digit = p.charAt(i); 
		if(digit == "." ) 
		{ 
			++count; 
			if(count>1) 			
				return false; 			
		} 
		else if(digit < "0" || digit > "9") 			
			return false; 		
	} 
	return true; 
}

function onEdit(id)
{	
	var pFeature = "dialogWidth:960px; dialogHeight:550px;";
	var strUrl=contextPath+"/prepareEmployeeEdit.do?editId="+id;
	showModalDialogTool(strUrl,"", pFeature);
	document.employeeSearchForm.submit();
}

function onMore(id)
{	
	var pFeature = "dialogWidth:960px; dialogHeight:550px;";
	var strUrl=contextPath+"/prepareEmployeeMore.do?editId="+id;
	showModalDialogTool(strUrl,"", pFeature);
}

function onPhysiologyCycle(id)
{	
	var pFeature = "dialogWidth:960px; dialogHeight:600px;";
	var strUrl=contextPath+"/preparePhysiologyCycle.do?editId="+id;
	showModalDialogTool(strUrl,"", pFeature);
}

function onDelete(id)
{
 	if(confirm("确定删除？")){
 		var strUrl=contextPath+"/processEmployeeDelete.do";
		var result=RequestData(strUrl, id);
		if(result=="success"){
 			alert("删除成功！");
 			document.employeeSearchForm.submit();
 		}
 	}
}

function onQuery()
{
	document.employeeSearchForm.startIndex.value="";
	onQuerySubmit();
}


function onMovePage(startIndex)
{
	document.employeeSearchForm.startIndex.value=startIndex;
	onQuerySubmit();
}

function onChangePageSize()
{
	document.employeeSearchForm.startIndex.value="";
	onQuerySubmit();
}

function onQuerySubmit()
{
	document.employeeSearchForm.submit();
}

function onPrint(){
	document.all("tblBtn").style.display = "none";
	window.print();
	document.all("tblBtn").style.display = "";
}

//显示和隐藏一个工单块 
function onDisplayBlock(icoObj,blockName,iParentCount){
	//取得当前所处的table
	var tblObj = icoObj
	for(i=0; i<iParentCount; i++){
		tblObj = tblObj.parentElement
	}
	//如果此工单块是隐藏的则显示并改变图标
	//如果此工单块是显示的则隐藏并改变图标
	if(tblObj.all(blockName).style.display == "none"){
		tblObj.all(blockName).style.display = "";
		icoObj.className = "clsclosequery";
	}else{
		tblObj.all(blockName).style.display = "none";
		icoObj.className = "clsopenquery";
	}
}	