/**
 * @author 侯非
 * @date 2009年3月10日
 * @class AddPersonFileFormPanel
 * @extends PersonFileFormPanel
 * @description 人员档案信息登记
 */
AddEmployeeFormPanel = Ext.extend(EmployeeFormPanel, {

			// 构造方法
			constructor : function(_config) {
				if (_config == null) {
					_config = {};
				}
				Ext.apply(this, _config);
				// 拷贝父类的构造方法到当前对象
				AddEmployeeFormPanel.superclass.constructor.call(this, {
							bbar : [{
										text : "确认登记",
										iconCls : "houfei-addicon",
										handler : this.onAddEmployee,
										scope : this
									}, "-", {
										text : "取消登记",
										iconCls : "houfei-tbarCancelIcon",
										handler : this.onCancelAddEmployee,
										scope : this
									}]
						});

			},

			/**
			 * 确认登记按钮单击事件
			 */
			onAddEmployee : function() {
				if (this.getForm().isValid() == true) {
					var _values = this.getForm().getValues();
					this.submitEmployee();
				}
			},
			/**
			 * 取消登记按钮单击事件
			 */
			onCancelAddEmployee : function() {
				this.getForm().reset();
			},

			/**
			 * 提交表单(人员档案登记)
			 */
			submitEmployee : function() {
				this.getForm().submit({
							url : appContextPath + "/employee/save.do",
							method : "post",
							waitTitle : "数据传输",
							waitMsg : "数据传输中,请稍候......",
							success : this.onInsertSuccess,
							failure : this.onInsertFailure,
							scope : this,
							params : {
								operator : "addPersonFile"
							}
						});
			},

			/**
			 * 人员档案信息添加成功事件
			 * 
			 * @param {}
			 *            _form
			 * @param {}
			 *            _response
			 */
			onInsertSuccess : function(_form, _action) {
				var _f = this.getForm();
				Ext.Msg.alert("系统消息", "操作成功,添加了一条员工信息!", function() {
							// 重置表单
							_f.reset();
						});

			},

			/**
			 * 人员档案信息添加失败事件
			 * 
			 * @param {}
			 *            _form
			 * @param {}
			 *            _response
			 */
			onInsertFailure : function(_form, _action) {
				Ext.Msg.alert("系统消息", "操作失败");
			}
		});
