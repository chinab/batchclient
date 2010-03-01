/**
 * @author 侯非
 * @date 2009年3月20日
 * @class EditPersonFileWindow
 * @extends Ext.Window
 * @description 变更人员档案信息窗体
 */
EditEmployeeWindow = Ext.extend(Ext.Window, {
			myForm : null, // 人员档案信息表单
			/**
			 * 构造方法
			 */
			constructor : function() {
				// 人员档案信息表单
				this.myForm = new EmployeeFormPanel({
							myReadOnly : true,
							myDisabled : true
						});
				/**
				 * 拷贝父类的构造
				 */
				EditEmployeeWindow.superclass.constructor.call(this, {
							title : "变更人员档案信息",
							closeAction : "hide", // 关闭模式为隐藏
							iconCls : "houfei-editicon",
							modal : true,// 模态窗体
							width : 900,
							height : 600,
							constrain : true, // 不允许窗体超过浏览器
							collapsible : true, // 是否有伸缩按钮
							resizable : true, // 不允许改变窗体大小
							autoScroll : true,
							plain : true,
							items : [this.myForm],
							buttons : [{
										text : "确认变更",
										handler : this.onEditOk,
										scope : this
									}, {
										text : "关闭",
										handler : this.onCloseWin,
										scope : this
									}]

						});
				// 为当前组建添加自定义事件onEditWinCheckOk(审核窗体通过审核按钮单击事件)
				this.addEvents("onEditWinEditOk");
			},

			/**
			 * 通过审核按钮单击事件
			 */
			onEditOk : function() {
				// 引发事件
				this.fireEvent("onEditWinEditOk");
			},
			/**
			 * 关闭按钮单击事件
			 */
			onCloseWin : function() {
				this.hide();
			}

		});