
function calendar2(ifShowTime) //主调函数
{
    if(ifShowTime == null) ifShowTime = true;
    
    var e = event.srcElement;   
    var o = document.all("calendarLayer").style; 

	var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft, p = e.type;
	while (e = e.offsetParent){
		t += e.offsetTop; l += e.offsetLeft;
	}
    o.display = ""; 
    
    window.frames.calendarIframe.document.body.focus();
    var cw = document.all("calendarLayer").clientWidth, ch = document.all("calendarLayer").clientHeight;
    var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;
    
    if (document.body.clientHeight + dt - t - h >= ch) o.top = (p=="image")? t + h : t + h + 6;
    else o.top  = (t - dt < ch) ? ((p=="image")? t + h : t + h + 6) : t - ch;
    if (dw + dl - l >= cw) o.left = l; else o.left = (dw >= cw) ? dw - cw + dl : dl;
	
	window.frames.calendarIframe.initCalendar(event.srcElement, ifShowTime);
}

function hideCalendar()
{
	document.all("calendarLayer").style.display = "none";
}

