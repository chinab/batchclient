var fs,win;

var buildWin = function(){
    fs = new Ext.form.FormPanel({
        layout:'form',
        defaultType:'textfield',
        defaults:{width:120},
        bodyStyle:'padding:50px 0 0',
        labelWidth:80,
        labelAlign:'right',
        height:200,
        frame:true,
        items:[{
            fieldLabel:'登录帐号',
            name:'username'
        },{
            fieldLabel:'登录密码',
            name:'password',
            inputType:'password'
        },{
            fieldLabel:'记住密码',
            name:'savepassword',
            xtype:'checkbox',
            width:20,
            style:'margin-top:8'
        }]
    });
    win = new Ext.Window({
        el:'win',
        width:380,
        height:240,
        title:'员工思想状态管理系统',
        layout:'column',
        defaults:{border:false},
        items:[{
            width:120,
            contentEl:'login'
        },{
            columnWidth:1,
            items:fs
        }],
        buttons:[{
            text:'登录',
            handler:loginSys
        },{
            text:'取消',
            handler:function(){win.hide();}
        }]
    });
};

var loginSys = function(){
    fs.form.submit({
        waitMsg: '正在登录。。。',
        url:'/trade/home/handleLogin',
        method:'POST',
        success:function(form,action){
            window.location = '/trade/home/index';
        },
        failure:function(form,action){
            Ext.MessageBox.alert('警告','登录失败，请检查帐号密码是否正确。');
        }
    });
};

var init = function(){
    buildWin();
    win.show();
};

Ext.onReady(init);