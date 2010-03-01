function validate(editForm)
{
	var propListIdValue=editForm.propListId.value;
	var propListNameValue=editForm.propListName.value;
	if(propListIdValue==""){
		alert("请输入编号！");
		return false;
	} 
	if(propListNameValue==""){
		alert("请输入名称！");
		return false;
	} 
	
	if(!checknum(propListIdValue)){
		alert("编号请输入数字！");
		return false;
	}
	return true;
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

var namePrefix="newListName";
function onEdit(idValue,propListIdValue)
{
	var newPropListName=document.getElementById(namePrefix+idValue).value;
	document.propListEditForm.id.value=idValue;
	document.propListEditForm.propListId.value=propListIdValue;
	document.propListEditForm.propListName.value=newPropListName;
	document.propListEditForm.submit();
}

function onResume()
{
	document.all.resume.click();
}