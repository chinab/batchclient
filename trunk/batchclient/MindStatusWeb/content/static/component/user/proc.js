function validate(editForm)
{
	var userNameValue=editForm.userName.value;
	var sexValue=editForm.sex.value;
	var realNameValue = editForm.realName.value;
	var passwordValue = editForm.password.value;
	var confirmPasswordValue=editForm.confirmPassword.value;
	if(userNameValue==""){
		alert("请输入用户名！");
		return false;
	} 
	if(sexValue==""){
		alert("请选择性别！");
		return false;
	} 
	
	if(realNameValue==""){
		alert("输入真实姓名！");
		return false;
	} 
	if(passwordValue==""){
		alert("请输入密码！");
		return false;
	}
	
	if(confirmPasswordValue==""){
		alert("请输入确认密码！");
		return false;
	}
	
	if(confirmPasswordValue!=passwordValue){
		alert("两次密码输入不一致！");
		return false;
	}
	
	return true;
}

function doValidate(editForm)
{
	var oldPwdValue = editForm.oldPwd.value;
	var passwordValue = editForm.password.value;
	var confirmPasswordValue=editForm.confirmPwd.value;
	if(oldPwdValue==""){
		alert("请输入旧密码！");
		return false;
	}
	if(passwordValue==""){
		alert("请输入新密码！");
		return false;
	}
	if(confirmPasswordValue==""){
		alert("请输入确认密码！");
		return false;
	}
	
	if(confirmPasswordValue!=passwordValue){
		alert("新密码和确认密码输入不一致！");
		return false;
	}
	
	return true;
}

function onDelete(id,userName)
{ 	
 	if(userName=="admin" || userName=="manager"){
 		alert("系统用户不能删除！");
 		return;
 	}
 	if(confirm("确定删除？")){
 		var strUrl=contextPath+"/processUserDelete.do";
		var result=RequestData(strUrl, id);
		if(result=="success"){
 			alert("删除成功！");
 			onQuerySubmit();
 		}
 	}
}

function onMovePage(startIndex1)
{
	document.userDeleteForm.startIndex.value=startIndex1;
	document.userDeleteForm.submit();
}

function onQuerySubmit()
{	
	document.userDeleteForm.submit();
}

function onChangePageSize()
{
	document.userDeleteForm.startIndex.value="";
	onQuerySubmit();
}