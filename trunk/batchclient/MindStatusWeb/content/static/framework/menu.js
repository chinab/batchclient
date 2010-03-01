function CLASS_OUTLOOKBAR(id,name)
{
    var THIS1 = this;

    this.id    = getUnique(id);
    this.name = getUnique(name);
    this.width = "140";
    this.height = "100%";
    this.titles = new Array();

    function getUnique(p){
        if(p!=null){
            return p;
        } else {
            return "outlook_" + new Date().getTime() + "_";
        }
    }

    this.addTitle = function(name){    

        function title(name){
            var THIS2    = this;
            this.name    = name;
            this.items    = new Array();
        
            this.addItem = function(name,target,url,image,align){

                function item(name,target,url,image,align){
                    this.name = name;
                    this.image = image;
                    this.target = target;
                    this.url = url;
                    this.align = align;
                }

                var        _item = new item(name,target,url,image,align);
                THIS2.items[THIS2.items.length] = _item;
                return _item;
            }
        }

        var _title = new title(name);
        this.titles[this.titles.length] = _title;
        return _title;
    }

    this.step    = 4;
    this.speed    = 10;
    this.selectedIndex = 0;
    this.timer  = 0;
    this.rate    = 100;
    this.run    = false;
    this.wait = new Array();

    this.select = function(index){
        if(this.selectedIndex!=index){
            if(this.run==false){
                this.rate = 100;
                this.run = true;
                this.timer = window.setInterval(function(){
                
                    THIS1.rate-= THIS1.step;                    
                    var oldI = document.getElementById(THIS1.id + THIS1.selectedIndex).nextSibling;
                    var newI = document.getElementById(THIS1.id + index).nextSibling;

                    var ooI = oldI.childNodes[0].childNodes[0];
                    var nnI = newI.childNodes[0].childNodes[0];

                    oldI.style.display = "";
                    newI.style.display = "";

                    if(THIS1.rate<0){
                        window.clearInterval(THIS1.timer);
                        THIS1.timer = 0;
                        ooI.style.overflow="auto";
                        nnI.style.overflow = "auto";
                        oldI.style.display = "none";
                        THIS1.run = false;
                        THIS1.selectedIndex = index;
                        if(THIS1.wait.length>0){
                            THIS1.select(THIS1.wait[0]);
                            THIS1.wait = THIS1.wait.slice(1,-1);
                        }
                    } else {                        
                        ooI.style.overflow = "hidden";
                        nnI.style.overflow = "hidden";
                        oldI.style.height = THIS1.rate +"%";
                        newI.style.height = (100 - THIS1.rate) + "%";
                    }

                },this.speed); 
            }else{
                this.wait[this.wait.length] = index;
            }
        }
    }

    this.setup = function(contain){
        if(contain!=null){        
            var table = document.createElement("table");
                table.style.width    = "100%";
                table.style.height    = "100%";
                table.cellPadding = "0px";
                table.cellSpacing = "0px";
                table.style.border = "0px solid red";
                table.bgColor = "#effaff";

                function getItem(width,height,display,title){
                    var tris = document.createElement("tr");
                    var tdis = document.createElement("td");
                        tdis.style.width = width;
                        tris.style.height = height;
                        tris.style.display = display;
                        tdis.className    = "outlook-item";
                        tdis.style.verticalAlign = "top";    

                        if(title!=null&&typeof(title.items)!="undefined"){
                            
                            var vv = document.createElement("div");
                                vv.style.overflow = "auto";
                                vv.style.height = "100%";
                                vv.style.width = "100%";

                            for(var i=0;i<title.items.length;i++){
                                var div = document.createElement("div");
                                    div.style.textAlign = "center";
                                    div.style.height = "24px";                                    
                                    div.style.paddingTop = "4px";

                                if(title.items[i].image!=null){
                                    var img = document.createElement("img");
                                        img.src = title.items[i].image;
                                        div.appendChild(img);
                                    if(typeof(title.items[i].align)!="undefined"&&title.items[i].align==1){
                                        var lbl = document.createElement("label");
                                            lbl.innerHTML = "<br>";
                                            div.appendChild(lbl);
                                    }
                                }

                                var a    = document.createElement("a");
                                    a.target    = title.items[i].target;
                                    a.innerHTML = title.items[i].name;
                                    a.href        = title.items[i].url;
                                    div.appendChild(a);
                                    vv.appendChild(div);                                                                                
                            }                    

                            tdis.appendChild(vv);
                        }

                        tris.appendChild(tdis);
                        return tris;
                }
        
                for(var i=0;i<this.titles.length;i++){        
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");

                        tr.style.height = "25px";
                        td.style.width    = "100%";                        
                        td.className    = "outlook-title";    

                        td.innerHTML    = this.titles[i].name;
                        tr.id            = this.id + i;
                                                                        
                        tr.appendChild(td);
                        table.appendChild(tr);
                        
                        if (i==this.selectedIndex){
                            var tris = getItem("100%","100%","",this.titles[i]);
                                table.appendChild(tris);                                
                        } else {
                            var tri     = getItem("100%","0%","none",this.titles[i]);
                                table.appendChild(tri);
                        }    
                                            
                }

                if(typeof(contain.outerHTML)=="undefined"){
                    contain.appendChild(table);
                } else {        
                    contain.innerHTML = table.outerHTML;
                }    
                
                //bind event
                for(var i=0;i<this.titles.length;i++){
                    var title = document.getElementById(this.id + i);
                    if(title){
                        function mapping(index){
                            try    {
                                title.onclick = function(){THIS1.select(index);}
                                title.oncontextmenu = function(){ return false;}
                                title.nextSibling.oncontextmenu = function(){return false;}
                            } catch (e){
                            }
                        }
                        mapping(i);
                    }
                }
        }
    }

    this.addItem = function(index,name,target,url,image,align){
        if(index>=0&&index<this.titles.length){
            return this.titles[index].addItem(name,target,url,image,align);
        }
    }

    this.setupById = function(id){
        var d = document.getElementById(id);
        if(d){
            this.setup(d);
        }
    }
}

var o = new CLASS_OUTLOOKBAR();
	var menuImagePath=contextPath+"/static/framework/theme/images";
	o.addTitle("思想状况管理");
    o.addTitle("数据字典管理");
    o.addTitle("用户信息管理");
    //o.addTitle("个人信息管理");
    //o.addTitle("系统公告管理");

	o.addItem(0,"思想状况录入","frm",contextPath+"/prepareEmployeeInsert.do",menuImagePath+"/add.png",1);
	o.addItem(0,"思想状况查询","frm",contextPath+"/prepareEmployeeSearch.do",menuImagePath+"/find.png",1);
	o.addItem(0,"思想状况维护","frm",contextPath+"/prepareEmployeeModify.do",menuImagePath+"/control.png",1);
	o.addItem(0,"生理周期预测","frm",contextPath+"//prepareComputePhy.do",menuImagePath+"/imac.png",1);

    //o.addItem(1,"数据字典录入","frm","http://www.google.cn",menuImagePath+"/bookmark.png",1);
    o.addItem(1,"数据字典维护","frm",contextPath+"/processPropTypeSearch.do",menuImagePath+"/call.png",1);
	
	o.addItem(2,"新建用户","frm",contextPath+"/prepareUserInsert.do",menuImagePath+"/user.png",1); 	o.addItem(2,"删除用户","frm",contextPath+"/prepareUserSearch.do",menuImagePath+"/remove.png",1);
	o.addItem(2,"密码修改","frm",contextPath+"/preparePasswordModify.do",menuImagePath+"/malet.png",1); 
	//o.addItem(2,"登录日志查询","frm","http://www.google.cn",menuImagePath+"/notepad.png",1);

	//o.addItem(3,"基本信息浏览","frm","http://www.google.cn",menuImagePath+"/home.png",1);
	//o.addItem(3,"基本信息维护","frm","http://www.google.cn",menuImagePath+"/malet.png",1);

	//o.addItem(3,"添加公告","frm","http://www.google.cn",menuImagePath+"/preferences.png",1);
	//o.addItem(3,"公告维护","frm","http://www.google.cn",menuImagePath+"/Internet.png",1);

  //o1是一个层
    o.setupById("o1");
//-->