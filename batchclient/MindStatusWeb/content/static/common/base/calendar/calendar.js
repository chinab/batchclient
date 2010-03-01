/** 
 * 日历对象，在构造函数中包含了生成日历对象并和 输入框 绑定的脚本
 * 一般调用方法	：日期输入框的 onfocus="if (window.calendar) new calendar(this[,format[,i]]);"
 * @author	: zhouxh 2002.11.04
 * @param	: oDateInput 和日历绑定的输入框对象;	
 * @param	: format 日期显示类型：0 YYYY-MM-DD；1 YYYY-MM-DD HH:MI:SS；2 YYYY-MM; 3 HH:MI:SS; 
 *			:			5 YYYYMMDD; 7 YYYYMM; 9 YYYY; 字符串 必须是前面定义的几种；
 * @param	: iDefaultSelect 缺省选中的元素的序号，从0开始
 * @param	: iScale 显示时间的精确度。0 年；1 月；2 日；3 时；4 分；5 秒；缺省为精确时间。
 * 说明		: 日期格式：^2002-09-13 23:59:59^。分隔符的位置从1开始，第一个^处记为1；
 */
function calendar(oDateInput, format, iDefaultSelect, iScale) {

	//如果该日期输入框已经绑定了一个日历对象，则进行简单处理后返回
	//如果该日期输入框还未和一个日历对象绑定，则创建并绑定日历对象(在后面调用boundCalendar();)
	if (oDateInput.oCalendar) {
		if (oDateInput.value=="")
			oDateInput.setTime(getCurrentDate(iScale));
		if (oDateInput.oCalendar.bMouseDown != true)
			oDateInput.oCalendar.selectDefaultText();
		
		return;
	}
	
	var iSepratePos;  									//分隔符的位置      					
	var iElementBegin;									//日期元素的起始位置
	var iElementEnd; 									//日期元素的终止位置
	var iElementLen;  									//日期元素的长度    
	var iElementMin;  									//日期元素的下限    
	var iElementMax;  									//日期元素的上限	
	var iElementDefault  =new Array(0,0,0,0,0,0);	//日期元素的缺省值为当前时间
	
	var bInitFlag = true;				//初始化标志
	var iDateType = 1;					//日期的类型，	0 YYYY-MM-DD；
										//		1 YYYY-MM-DD HH:MI:SS；
										//		2 YYYY-MM;
										//		3 HH:MI:SS;
										//		5 YYYYMMDD;
										//		7 YYYYMM;
										//		9 YYYY;
	var iDefaultSelect = iDefaultSelect;//缺省选中的元素
	var iElementNumber = 0;				//日期元素的数量
	
	this.bMouseDown = false;			//标识鼠标按下事件的发生

	
	//显示调试信息
	function showmsg(msg) {
		//CMPUtil.addDebugInfo("calendar", msg);
	}
		

	//创建并绑定calendar对象和日期输入控件
	this.boundCalendar = function() { 
		if (oDateInput.oCalendar == null) {
			if (!rInit()) return;
			oDateInput.oCalendar = this;
			oDateInput.attachEvent('onclick',     this.rClick);
			oDateInput.attachEvent('onkeydown',   this.rKeyDown);
			oDateInput.attachEvent('onblur',      this.rBlur);
			oDateInput.attachEvent('onmousedown', this.rMouseDown);
			oDateInput.attachEvent('onmouseup',   this.rMouseUp);
			oDateInput.attachEvent('onmousemove', this.rMouseUp);
			oDateInput.attachEvent('ondrag', 	  this.rDrag);
			oDateInput.setTime = function(oDate) {
				this.oCalendar.setTime(oDate);	
			}
			if (oDateInput.value == "")
				oDateInput.setTime(getCurrentDate(iScale));
			oDateInput.oCalendar.selectDefaultText();
		}
	}
	
	
	//根据设定的iScale返回当前的时间
	function getCurrentDate(iScale) {
		var date = new Date();
			
		switch(iScale) {
			case 0:
				date.setMonth(0);
			case 1:
				date.setDate(1);
			case 2:
				date.setHours(0);
			case 3:
				date.setMinutes(0);
			case 4:
				date.setSeconds(0);
			case 5:
				date.setMilliseconds(0);
				break;
			default:
				break;
		}
		return date;
	}
			
		
	//初始化
	function rInit() {
		showmsg("init start...");
		bInitFlag = true;
		//设置日期格式
		if (format != null) {
			if (isNaN(parseInt(format))) {
				var strFormat = format.toUpperCase();
				if (strFormat == "YYYY-MM-DD") 
					iDateType = 0;
				else if (strFormat == "YYYY-MM-DD HH:MI:SS") 
					iDateType = 1;
				else if (strFormat == "YYYY-MM") 
					iDateType = 2;
				else if (strFormat == "HH:MI:SS") 
					iDateType = 3;
				else if (strFormat == "YYYYMMDD") 
					iDateType = 5;
				else if (strFormat == "YYYYMM") 
					iDateType = 7;
				else if (strFormat == "YYYY") 
					iDateType = 9;
				else
					alert("日期格式错误");	
			} else {
				if ((format<0)||(format>10))
					alert("日期格式错误");
				else	
					iDateType = format;	
			}
		}		
		if (iDateType == 0)
			iElementNumber=3;
		else if (iDateType == 1)
			iElementNumber=6;
		else if (iDateType == 2)
			iElementNumber=2;
		else if (iDateType == 3)
			iElementNumber=3;
		else if (iDateType == 5)
			iElementNumber=3;
		else if (iDateType == 7)
			iElementNumber=2;
		else if (iDateType == 9)
			iElementNumber=1;	
		
		if(iDateType==3) {
			// ^12:23:45^
			// ^  ^  ^  ^       
			// 1  3  6  9 
			iSepratePos   = new Array(1,4,7,9);		//分隔符的位置
			iElementBegin = new Array(1,4,7);		//日期元素的起始位置
			iElementEnd   = new Array(3,6,9);		//日期元素的终止位置
			iElementLen   = new Array(2,2,2);		//日期元素的长度
			iElementMin   = new Array(0,0,0);		//日期元素的下限
			iElementMax   = new Array(23,59,59);	//日期元素的上限			
		} else if ( (iDateType==5) || (iDateType==7) || (iDateType==9) ) {
			// ^20020914^
			// ^    ^ ^ ^
			// 1    5 8 11
			iSepratePos   = new Array(1,4,7,11);	//分隔符的位置
			iElementBegin = new Array(1,5,7);		//日期元素的起始位置
			iElementEnd   = new Array(5,7,9);		//日期元素的终止位置
			iElementLen   = new Array(4,2,2);		//日期元素的长度
			iElementMin   = new Array(1900,1,1);	//日期元素的下限
			iElementMax   = new Array(2100,12,31);	//日期元素的上限	
		} else {
			// ^2002-09-14 12:23:45^
			// ^    ^  ^  ^  ^  ^  ^         
			// 1    5  8  11 14 17 20                  
			iSepratePos   = new Array(1,5,8,11,14,17,20);	//分隔符的位置
			iElementBegin = new Array(1,6,9,12,15,18);		//日期元素的起始位置
			iElementEnd   = new Array(5,8,11,14,17,20);		//日期元素的终止位置
			iElementLen   = new Array(4,2,2,2,2,2);			//日期元素的长度
			iElementMin   = new Array(1900,1,1,0,0,0);		//日期元素的下限
			iElementMax   = new Array(2100,12,31,23,59,59);	//日期元素的上限	
		}
		
		return true;
	}
	
	
	//设置日期输入框的时间
	//oDate Date对象
	this.setTime = function(oDate) {
		showmsg("settime start...");
		var curDate = oDate;
		if (iDateType == 3) {
			iElementDefault[0] = '0000'+curDate.getHours();
			iElementDefault[1] = '0000'+curDate.getMinutes();
			iElementDefault[2] = '0000'+curDate.getSeconds();
				
			iElementDefault[0] = iElementDefault[0].substr(iElementDefault[0].length-iElementLen[0],iElementLen[0]);
			iElementDefault[1] = iElementDefault[1].substr(iElementDefault[1].length-iElementLen[1],iElementLen[1]);
			iElementDefault[2] = iElementDefault[2].substr(iElementDefault[2].length-iElementLen[2],iElementLen[2]);
		} else if ( (iDateType == 5) || (iDateType == 7) || (iDateType==9) ) {
			iElementDefault[0] = '0000'+curDate.getYear();
			iElementDefault[1] = '0000'+(curDate.getMonth()+1);
			iElementDefault[2] = '0000'+curDate.getDate();
				
			iElementDefault[0] = iElementDefault[0].substr(iElementDefault[0].length-iElementLen[0],iElementLen[0]);
			iElementDefault[1] = iElementDefault[1].substr(iElementDefault[1].length-iElementLen[1],iElementLen[1]);
			iElementDefault[2] = iElementDefault[2].substr(iElementDefault[2].length-iElementLen[2],iElementLen[2]);
		} else {
			iElementDefault[0] = '0000'+curDate.getYear();
			iElementDefault[1] = '0000'+(curDate.getMonth()+1);
			iElementDefault[2] = '0000'+curDate.getDate();
			iElementDefault[3] = '0000'+curDate.getHours();
			iElementDefault[4] = '0000'+curDate.getMinutes();
			iElementDefault[5] = '0000'+curDate.getSeconds();
			
			iElementDefault[0] = iElementDefault[0].substr(iElementDefault[0].length-iElementLen[0],iElementLen[0]);
			iElementDefault[1] = iElementDefault[1].substr(iElementDefault[1].length-iElementLen[1],iElementLen[1]);
			iElementDefault[2] = iElementDefault[2].substr(iElementDefault[2].length-iElementLen[2],iElementLen[2]);
			iElementDefault[3] = iElementDefault[3].substr(iElementDefault[3].length-iElementLen[3],iElementLen[3]);
			iElementDefault[4] = iElementDefault[4].substr(iElementDefault[4].length-iElementLen[4],iElementLen[4]);
			iElementDefault[5] = iElementDefault[5].substr(iElementDefault[5].length-iElementLen[5],iElementLen[5]);
		}
		
		if (iDateType == 0) {
			oDateInput.value = iElementDefault[0]+'-'+iElementDefault[1]+'-'+iElementDefault[2];
		} else if (iDateType == 1) {
			oDateInput.value = iElementDefault[0]+'-'+iElementDefault[1]+'-'+iElementDefault[2]+' '+iElementDefault[3]+':'+iElementDefault[4]+':'+iElementDefault[5];
		} else if (iDateType == 2) {
			oDateInput.value = iElementDefault[0]+'-'+iElementDefault[1];
		} else if (iDateType == 3) {
			oDateInput.value = iElementDefault[0]+':'+iElementDefault[1]+':'+iElementDefault[2];
		} else if (iDateType == 5) {
			oDateInput.value = iElementDefault[0]+iElementDefault[1]+iElementDefault[2];
		} else if (iDateType == 7) {
			oDateInput.value = iElementDefault[0]+iElementDefault[1];
		} else if (iDateType == 9) {
			oDateInput.value = iElementDefault[0];
		}

		oDateInput.fireEvent("onchange");				
	}
	
	
	//设置缺省选中的文本
	this.selectDefaultText = function() {
		showmsg("selectdefaulttext start...");
		oDateInput.select();
		if ((isNaN(iDefaultSelect)) || (iDefaultSelect<0) || (iDefaultSelect>iElementNumber)) {
			if (iDateType == 0) {
				textSelect(iElementBegin[2],iElementLen[2]);//默认选择日
			} else if (iDateType == 1) {
				textSelect(iElementBegin[3],iElementLen[3]);//默认选择时	
			} else if (iDateType == 2) {
				textSelect(iElementBegin[1],iElementLen[1]);//默认选择月	
			} else if (iDateType == 3) {
				textSelect(iElementBegin[0],iElementLen[0]);//默认选择年
			} else if (iDateType == 5) {
				textSelect(iElementBegin[2],iElementLen[2]);//默认选择日
			} else if (iDateType == 7) {
				textSelect(iElementBegin[1],iElementLen[1]);//默认选择月
			} else if (iDateType == 9) {
				textSelect(iElementBegin[0],iElementLen[0]);//默认选择年
			}
		} else
			textSelect(iElementBegin[iDefaultSelect],iElementLen[iDefaultSelect]);//默认选择年
	}
	
	
	//失去焦点时的处理
	this.rBlur = function()	{
		showmsg("rblur start...");
		doElementValidate();
		if(doDateValidate()!=true)
			oDateInput.focus();
	}
	
	
	//鼠标键按下时的处理
	this.rMouseDown = function() {
		showmsg("rmousedown start...");
		oDateInput.oCalendar.bMouseDown = true;
	}
	
	
	//鼠标键放开时的处理
	this.rMouseUp = function() {
		showmsg("rmouseup start...");
		oDateInput.oCalendar.bMouseDown = false;
	}
	
	//鼠标拖拽时的处理
	this.rDrag = function() {
		event.returnValue = false;
	}
	
	//鼠标点击时的处理
	this.rClick = function() {
		showmsg("rclick start...");
		if (oDateInput.value=="") {
			oDateInput.setTime(getCurrentDate(iScale));
			return;
		}
		
		var iPos = getCursorPos();
		var iElementID = 0;
				
		if(iPos<iSepratePos[0] || iPos>iSepratePos[iElementNumber])
			return;
		
		bInitFlag = true;	
		
		doElementValidate();
		
		//选定点击区域
		for(var i=1;i<=iElementNumber;i++) {
			iElementID=i-1;
			if(iPos<=iSepratePos[i]) {
				textSelect(iElementBegin[iElementID],iElementLen[iElementID]);
				break;
			}
		}
	} 
	
	
	//按键按下时的处理
	this.rKeyDown = function() { 
		showmsg("rkeydown start...");
		var key = getKeyPress();

		if ((key!='delete')&&(key!='pass')) {
			if (oDateInput.value=="")
				oDateInput.setTime(getCurrentDate(iScale));
		}
		
		switch(key) {
			case 'space':
			case 'hyphen':
			case 'colon':
				onSeperatorClick(key);
				break;
			case 'delete':
				oDateInput.value = "";
				oDateInput.fireEvent("onchange");
				break;
			case 'pass':
				break;
			case 'left':
				onLeftClick();
				break;
			case 'up':
				onUpClick();
				break;
			case 'right':
				onRightClick();
				break;
			case 'down':
				onDownClick();
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				onNumberClick(key);
				break;
			default:
				break;		
		}		
	}  
	
	
	//按下分隔键时的处理
	function onSeperatorClick(key) {
		var pos = getCursorPos();
		var rpos = getElementBeginRight();
		
		//如果右面还有日期元素：
		if ((rpos>0) && (rpos>pos)) {
			var cSeperator = oDateInput.value.substring(rpos-2,rpos-1);
			if (cSeperator==' ') cSeperator='space';
			else if (cSeperator=='-') cSeperator='hyphen';
			else if (cSeperator==':') cSeperator='colon';
			//如果按下的分隔键等于当前日期元素后的分隔符，或是按了空格键（这里把空格键
			//做为通用的分隔符键），则光标移动到下一个（右面）日期元素上
			if ((cSeperator==key) || (key='space'))
				onRightClick();
		}
	}
		
	
	//按数字键时的处理
	function onNumberClick(key) {
		var oldValue = getSelectText();	
		var oldLen   = oldValue.length;	
		var tmpStr   = '0000000000';
			
		var newValue = oldValue.substr(1,oldLen-1)+key;
		
		if(bInitFlag==true) {
			newValue  = tmpStr.substr(1,oldLen-1)+key;
			bInitFlag = false;			
		} else if(parseInt(newValue,10)>getElementMax()) {
			newValue  = tmpStr.substr(1,oldLen-1)+key;		
		} else if(parseInt(newValue,10)<getElementMin()) {
			newValue  = oldValue.substr(1,oldLen-1)+key;		
		} else {
			newValue  = oldValue.substr(1,oldLen-1)+key;		
		}

		setSelectText(newValue);
	}
	
	
	//按上方向键的处理
	function onUpClick() {	
		bInitFlag=true;
		
		var oldValue = parseInt(getSelectText(),10);
		var newValue = oldValue+1;
				
		if(newValue>getElementMax())
			newValue = getElementMin();
		
		setSelectText(''+newValue);	
	}
	
	
	//按下下方向键的处理
	function onDownClick() {
		bInitFlag=true;
		
		var oldValue=parseInt(getSelectText(),10);
		var newValue=oldValue-1;
		
		if(newValue<getElementMin())
			newValue = getElementMax();
		
		setSelectText(''+newValue);
	}
	
	
	//按左方向键的处理
	function onLeftClick() {
		bInitFlag = true;
		
		moveCursor(getElementBeginLeft());
		
		var iBegin = getElementBegin();
		var iEnd   = getElementEnd();
		
		textSelect(iBegin,iEnd-iBegin);		
			
		doElementValidate();
	}
	
	
	//按右方向键的处理
	function onRightClick()	{
		bInitFlag=true;

		moveCursor(getElementBeginRight());
		
		var iBegin=getElementBegin();
		var iEnd  =getElementEnd();
		
		textSelect(iBegin,iEnd-iBegin);
			
		doElementValidate();
	}
	
	
	//获得按键
	function getKeyPress() {
		var keyCode = window.event.keyCode;
		var key = '';
		
		if ((keyCode!=9) && (keyCode!=13))
			window.event.returnValue = false;
			
		if(keyCode>=37 && keyCode<=40) {
			if(keyCode==37)	key='left';
			if(keyCode==38)	key='up';
			if(keyCode==39)	key='right';
			if(keyCode==40)	key='down';
		} else if(keyCode>=48 && keyCode<=57) {
			keyCode = keyCode-48;
			key = ''+keyCode;
		} else if(keyCode>=96 && keyCode<=105) {
			keyCode = keyCode-96;
			key = ''+keyCode;
		} else if (keyCode==46) {
			key = 'delete';
		} else if(keyCode==32) {
			key='space';
		} else if (keyCode==189) {
			key='hyphen';
		} else if ((keyCode==186)&&(event.shiftKey)) {
			key='colon';
		} else
			key = 'pass';	
			
		return key;
	}
	
	
	//文本选定
	//a 开始字符位置(从1开始)；b 字符个数
	function textSelect(a,b) {
		//起始点的判断
		a = parseInt(a,10);
		b = parseInt(b,10);	
		a = a<1 ? 1 : a;
		b = b<0 ? 0 : b;
		showmsg("	textselect ("+a+","+b+")");				
		var rngDate = oDateInput.createTextRange();
		rngDate.collapse();
		rngDate.moveStart('character',a-1);
		rngDate.moveEnd('character',b);
		rngDate.select();
	}

	
	//取得特定文本的值
	//a 开始字符；b 字符个数
	function getText(a,b) {
		//起始点的判断
		a = parseInt(a,10);
		b = parseInt(b,10);	
		a = a<1 ? 1 : a;
		b = b<0 ? 0 : b;
						
		var rngDate = oDateInput.createTextRange();
		rngDate.collapse();
		rngDate.moveStart('character',a-1);
		rngDate.moveEnd('character',b);	
		return rngDate.text;
	}
	
	
	//设定特定文本的值
	//a 开始字符；b 字符个数
	function setText(a,b,strValue)	{
		//起始点的判断
		a=parseInt(a,10);
		b=parseInt(b,10);	
		
		a=a<1 ? 1 : a;
		b=b<0 ? 0 : b;
					
		var rngDate=oDateInput.createTextRange();
		rngDate.collapse();
		rngDate.moveStart('character',a-1);
		rngDate.moveEnd('character',b);	
		rngDate.text=strValue;
	}
	
	
	//光标绝对移动
	//a 绝对移动位数
	function moveCursor(a) {
		//起始点的判断
		a=parseInt(''+a,10);	
		a=a<0 ? 0 : a;
			
		var rngDate=oDateInput.createTextRange();
		rngDate.move('character',a);
		rngDate.select();
	}
	
	
	//光标相对移动
	//a 相对移动位数（包括方向）
	function moveCursorRel(a) {
		//起始点的判断
		a=parseInt(a,10);	
		var b=getCursorPos();		//当前位置	
		var c=b+a;							//实际从头开始的位置	
		c=c<0 ? 0 : c;
		
		var rngDate=oDateInput.createTextRange();
		rngDate.move('character',c);
		rngDate.select();	
	}
	
	
	//得到光标当前位置
	function getCursorPos()	{
		var textValue = oDateInput.value;
		var textLen   = textValue.length;
		
		var rngPos    = document.selection.createRange();	
		var rngDate   = oDateInput.createTextRange();
		
		rngDate.collapse();	
		if(rngPos.inRange(rngDate))
			return 1;
				
		for(var i=2;i<=textLen+1;i++) {
			rngDate.move('character',1);
			if(rngPos.inRange(rngDate))
				return i;
		}
		
		return -1;
	}
	
	
	//得到选定文本
	function getSelectText() {
		var iBegin=getElementBegin();
		var iEnd  =getElementEnd();
		
		textSelect(iBegin,iEnd-iBegin);
		
		return document.selection.createRange().text;	
	}
	
	
	//设置选定文本
	function setSelectText(newValue) {
		showmsg("setselecttext start...");
		var textValue=getSelectText();
		
		//长度验证
		if(newValue.length!=textValue.length) {
			newValue='0000'+newValue;
			newValue=newValue.substr(newValue.length-textValue.length,newValue.length);		
		}
			
		var iBegin=getElementBegin();
		var iEnd  =getElementEnd();
		
		textSelect(iBegin,iEnd-iBegin);
		var rngSelect=document.selection.createRange();
		rngSelect.text=newValue;
		
		textSelect(iBegin,iEnd-iBegin);
	}
	
	
	//得到当前日期元素的起始位置
	function getElementBegin() {
		var curPos=getCursorPos();
		for(var ip=0;ip<iElementNumber;ip++) {
			if(curPos>=iElementBegin[ip] && curPos<iElementEnd[ip])
				return iElementBegin[ip];
		}
		
		return 0;
	}
	
	//得到当前日期元素的终止位置
	function getElementEnd() {
		var curPos=getCursorPos();
		for(var ip=0;ip<iElementNumber;ip++) {
			if(curPos>=iElementBegin[ip] && curPos<iElementEnd[ip])
				return iElementEnd[ip];
		}
		
		return 0;
	}
	
	
	//得到下一个日期元素的起始位置
	function getElementBeginRight() {
		var curPos=getCursorPos();
		for(var ip=0;ip<iElementNumber;ip++) {
			if(curPos>=iElementBegin[ip] && curPos<iElementEnd[ip]) {
				if(ip==iElementNumber-1)
					return iElementBegin[0];
				else
					return iElementBegin[ip+1];
			}
		}
		
		return 0;
	}
	
	
	//得到上一个日期元素的起始位置
	function getElementBeginLeft() {
		var curPos=getCursorPos();
		for(var ip=0;ip<iElementNumber;ip++) {
			if(curPos>=iElementBegin[ip] && curPos<iElementEnd[ip]) {
				if(ip==0)
					return iElementBegin[iElementNumber-1];
				else
					return iElementBegin[ip-1];
			} 
		}

		return 0;
	}
	
	
	//得到当前日期元素的下限
	function getElementMin() {
		var curPos=getCursorPos();
		for(var ip=0;ip<iElementNumber;ip++) {
			if(curPos>=iElementBegin[ip] && curPos<iElementEnd[ip])
				return iElementMin[ip];
		}
		
		return 0;
	}
	
	
	//得到当前日期元素的上限
	function getElementMax() {
		var curPos=getCursorPos();
		for(var ip=0;ip<iElementNumber;ip++) {
			if(curPos>=iElementBegin[ip] && curPos<iElementEnd[ip]) {
				if(ip==2)
					return getDayMax();				
				else
					return iElementMax[ip];
			}
		}
		
		return 65535;
	}
	
	
	//得到某月的天数上限
	function getDayMax() {
		var iYear  = parseInt(oDateInput.value.substr(0,4),10);
		var iMonth = parseInt(oDateInput.value.substr(5,2),10);
		
		if(iMonth==1 || iMonth==3 || iMonth==5 || iMonth==7 || iMonth==8 || iMonth==10 || iMonth==12)
			return 31;
		else if(iMonth!=2)
			return 30;
		else {		
			//是否为润年
			if( (iYear%400)==0 || ((iYear%4)==0 && (iYear%100)!=0) )		//润年
				return 29;
			else
				return 28;
		}	
	}
	
	
	//取得某元素的值
	function getElementValue(elementID) {
		return parseInt(getText(iElementBegin[elementID],iElementEnd[elementID]-iElementBegin[elementID]),10);
	}
	
	
	//设置某元素的值
	function setElementValue(elementID,elementValue) {
		setText(iElementBegin[elementID],iElementEnd[elementID]-iElementBegin[elementID],elementValue);
		oDateInput.fireEvent("onchange");
	}
	
	
	//检验日期元素的值
	function doElementValidate() {
		//将超范围的天数置为本月最大值
		if(iDateType==0 || iDateType==1) {
			if (getElementValue(2)>getDayMax())
				setElementValue(2,getDayMax());
		}
		for (var ip=0; ip<iElementNumber; ip++) {
			if (getElementValue(ip)<iElementMin[ip] || getElementValue(ip)>iElementMax[ip])
				setElementValue(ip,iElementDefault[ip]);
		}
	}
	
	
	//检验日期
	function doDateValidate() {
		if(iDateType==3) {
			var iYear  = "2002";
			var iMonth = "09";
			var iDay   = "01";
		} else if ( (iDateType==5) || (iDateType==7) || (iDateType==9) ) {
			var iYear  = parseInt(oDateInput.value.substr(0,4),10);
			var iMonth = parseInt(oDateInput.value.substr(4,2),10);
			if (iDay!=2) {
				var iDay   = parseInt(oDateInput.value.substr(6,2),10);
			} else {
				var iDay   = "01";
			}
		} else {  
			var iYear  = parseInt(oDateInput.value.substr(0,4),10);
			var iMonth = parseInt(oDateInput.value.substr(5,2),10);
			if (iDay!=2) {
				var iDay   = parseInt(oDateInput.value.substr(8,2),10);
			} else {
				var iDay   = "01";
			}
		}
		if (iDateType==1 || iDateType==3) {
			if(iDateType==1) {
				var iHour   =parseInt(oDateInput.value.substr(12,2),10);
				var iMinute =parseInt(oDateInput.value.substr(15,2),10);
				var iSecond =parseInt(oDateInput.value.substr(18,2),10);
			} else {
				var iHour   =parseInt(oDateInput.value.substr(1,2),10);
				var iMinute =parseInt(oDateInput.value.substr(4,2),10);
				var iSecond =parseInt(oDateInput.value.substr(7,2),10);
			}
			if(iHour<0 || iHour>23)	{
				alert('小时有误，请重新填写！');
				return false;
			}	
				
			if(iMinute<0 || iMinute>59) {
				alert('分钟有误，请重新填写！');
				return false;
			}
			if(iSecond<0 || iSecond>59) {
				alert('秒钟有误，请重新填写！');
				return false;
			}
		}
		
		if(iYear<1900 || iYear>2100) {
			alert('年限有误，请重新填写！');
			return false;
		}	
		
		if(iMonth<1 || iMonth>12) {
			alert('月份有误，请重新填写！');
			return false;
		}	
		
		//天数验证
		if((iMonth==1 || iMonth==3 || iMonth==5 || iMonth==7 || iMonth==8 || iMonth==10 || iMonth==12))	{
			if(iDay>31 || iDay<1) {
				alert('天数有误，请重新填写！');
				return false;
			}
		} else if(iMonth!=2) {	
			if(iDay>30 || iDay<1) {
				alert('天数有误，请重新填写！');
				return false;
			}
		} else {		
			//是否为润年
			if( (iYear%400)==0 || ((iYear%4)==0 && (iYear%100)!=0) ) {	
				if(iDay>29 || iDay<1) {
					alert('天数有误，请重新填写！');
					return false;
				}
			} else {	
				if(iDay>28 || iDay<1) {
					alert('天数有误，请重新填写！');
					return false;
				}
			}
		}
		
		return true;	
	}
	
	this.boundCalendar();
	
}
