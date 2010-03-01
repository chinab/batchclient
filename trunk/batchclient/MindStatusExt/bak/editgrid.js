/**
 * @author 侯非
 * @date 2009年3月20日
 * @class EditPersonFileGridPanel
 * @extends Ext.grid.GridPanel
 * @description 人员档案信息表格(审核与变更)
 */
EditEmployeeGridPanel = Ext.extend(Ext.grid.GridPanel, {
	myid : "default",
	checkWin : null, // 审核人员档案信息窗体
	editWin : null, // 变更人员档案信息窗体

	sexStore : null, // 下拉列表框数据源(性别)
	enlightenStore : null, // 下拉列表框数据源(是否需要做工作)
	mindPatternStore : null, // 下拉列表框数据源(工作方式)
	jobResultStore : null, // 下拉列表框数据源(工作结果)
	/**
	 * 构造方法
	 */
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}
		Ext.apply(this, _config);
		// 审核人员档案信息窗体
		// this.checkWin = new CheckPersonFileWindow();
		// 变更人员档案信息窗体
		// this.editWin = new EditPersonFileWindow();
		/**
		 * 数据存储器
		 */

		// 下拉列表框数据源(性别)
		this.sexStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "sex"
					},
					fields : ["label", "value"]
				});
		this.sexStore.load();
		// 下拉列表框数据源(是否需要做工作)
		this.enlightenStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "enlighten"
					},
					fields : ["label", "value"]
				});
		this.enlightenStore.load();
		// 下拉列表框数据源(工作方式)
		this.mindPatternStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "mind_pattern"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(工作结果)
		this.jobResultStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "job_result"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		this["store"] = new Ext.data.JsonStore({
					url : appContextPath + "/employee/query.do",
					root : "rows",
					totalProperty : "total",
					fields : ["id", "name", "sex", "age", "birthday",
							"eduQualification", "politicalAppearance",
							"category", "station", "kidney", "interest",
							"associateRelation", "familyRelation", "community",
							"award", "studyAttitude", "responsibility",
							"campaign", "economy", "afterHoursBusiness",
							"morality", "security", "serviceAttitude", "skill",
							"performance", "modelEffect", "enlighten",
							"mindPattern", "jobResult", "memo", "promise",
							"rule", "honesty", "faith", "duty", "technology",
							"management", "art", "sports"]
				});

		/**
		 * 拷贝父类的构造
		 */
		EditEmployeeGridPanel.superclass.constructor.call(this, {
			id : this.myid,
			title : "员工思想状态维护",
			stripeRows : true, // 交替行效果
			iconCls : "houfei-treeNodeLeafIcon",
			viewConfig : {
				forceFit : true
			},
			// 列
			columns : [new Ext.grid.CheckboxSelectionModel({
								// 头
								header : ""
							}), new Ext.grid.RowNumberer(), {
						header : "姓名",
						sortable : true,
						dataIndex : "name"
					}, {
						header : "性别",
						sortable : true,
						dataIndex : "sex",
						editor : new Ext.form.ComboBox({
									id : 'sexCmb', // 为combobox定义一个ID,必须的
									hiddenName : '',
									triggerAction : 'all',
									displayField : "label", // 要显示的值
									valueField : "value",
									store : this.sexStore,
									editable : false
								}),
						renderer : function(value, cellmeta, record) {
							var _cmp = Ext.getCmp('sexCmb');
							var _store = _cmp.store;
							// 通过匹配value取得ds索引
							var _index = _store.find(_cmp.valueField, value);

							// 通过索引取得记录ds中的记录集
							var _record = _store.getAt(_index);

							// 返回记录集中的value字段的值
							if (_record) {
								return _record.data.label;
							} else {
								return "";
							}
						}
					}, {
						header : "生日",
						sortable : true,
						dataIndex : "birthday",
						renderer : function(_v) {
							var _value = Ext.util.Format.substr(_v, 0, 10);
							var _sdate = new Date();
							_sdate = Date.parseDate(_value, "Y-m-d");
							var _date = Ext.util.Format.date(_sdate, "Y年m月d日");
							return _date;
						}
					}, {
						header : "是否需要做工作",
						sortable : true,
						dataIndex : "enlighten",
						editor : new Ext.form.ComboBox({
									id : 'enlightenCmb', // 为combobox定义一个ID,必须的
									hiddenName : '',
									triggerAction : 'all',
									displayField : "label", // 要显示的值
									valueField : "value",
									store : this.enlightenStore,
									editable : false
								}),
						renderer : function(value, cellmeta, record) {
							var _cmp = Ext.getCmp('enlightenCmb');
							var _store = _cmp.store;
							// 通过匹配value取得ds索引
							var index = _store.find(_cmp.valueField, value);
							// alert(index);
							// 通过索引取得记录ds中的记录集
							var _record = _store.getAt(index);
							// 返回记录集中的value字段的值
							if (_record) {
								return _record.data.label;
							} else {
								return "";
							}
						}
					}],
			selModel : new Ext.grid.RowSelectionModel({
						// 单行选择模式
						singleSelect : true
					}),
			// 填充加载时间
			loadMask : {
				msg : "正在加载数据,请稍候......"
			},
			tbar : ["姓名：", {
						xtype : "textfield"
					}, "　性别：", {
						xtype : "combo",
						fieldLabel : "性别",
						emptyText : "请选择性别",
						hiddenName : "sex",
						triggerAction : "all", // 显示所有数据
						displayField : "label", // 要显示的值
						valueField : "value",
						allowBlank : true,
						store : this.sexStore
					}, "　生日：", {
						xtype : "datefield",
						readOnly : true, // 只读
						format : "Y-m-d"
					}, "至", {
						xtype : "datefield",
						readOnly : true, // 只读
						format : "Y-m-d"
					}, "-", {
						text : "查看",
						iconCls : "houfei-searchIcon",
						handler : this.onSearch,
						tooltip : "查询员工思想状态信息",
						scope : this
					}, "-", {
						text : "清空查询条件",
						iconCls : "houfei-closeCurrentTab",
						tooltip : "清空查询条件",
						handler : this.onClearCondition,
						scope : this
					}],
			bbar : new Ext.PagingToolbar({
				store : this["store"], // 数据存储器
				pageSize : 50, // 页大小
				displayMsg : "共<font color=\"green\"> {2} </font>条记录,当前页记录索引<font color=\"green\"> {0} - {1}</font>&nbsp;&nbsp;&nbsp;",
				displayInfo : true,
				items : ["-", {
							text : "生理周期",
							iconCls : "houfei-checkIcon",
							handler : this.onCheckPersonFile,
							scope : this
						}, "-", {
							text : "修改记录",
							iconCls : "houfei-editicon",
							handler : this.onEditPersonFile,
							scope : this
						}]
			})

		});

		/**
		 * 加载数据
		 */
		this.getStore().baseParams = {
			enlighten : "1"
		}
		this.getStore().load({
					params : {
						start : 0,
						limit : 50
					}
				});

		// 监听当前对象checkWin的自定义事件onCheckWinCheckOk(审核窗体通过审核按钮单击事件)
		// this.checkWin.on("onCheckWinCheckOk", this.onCheckWinCheckOk, this);

		// 监听当前对象editWin的自定义事件onEditWinEditOk(变更窗体确认变更按钮单击事件)
		// this.editWin.on("onEditWinEditOk", this.onEditWinEditOk, this);
	},

	/**
	 * 当前对象checkWin的自定义事件onCheckWinCheckOk(审核窗体通过审核按钮单击事件)
	 */
	onCheckWinCheckOk : function() {
		// 获取主键id
		var _pfId = this.getSelectionModel().getSelected().get("pfId");
		// 如果表单验证通过则提交表单
		if (this.checkWin.myForm.getForm().isValid() == true) {
			// 提交表单
			this.checkWin.myForm.getForm().submit({
						url : "personFile.do",
						method : "post",
						waitTitle : "数据传输",
						waitMsg : "数据传输中,请稍候......",
						success : this.onCheckSuccess,
						failure : this.onCheckFailure,
						scope : this,
						params : {
							operator : "checkPersonFile",
							pfid : _pfId
						}
					});
		}
	},

	/**
	 * 当前对象editWin的自定义事件onEditWinEditOk(变更窗体确认变更按钮单击事件)
	 */
	onEditWinEditOk : function() {
		// 获取主键id
		var _pfId = this.getSelectionModel().getSelected().get("pfId");
		// 如果表单验证通过则提交表单
		if (this.editWin.myForm.getForm().isValid() == true) {
			// 提交表单
			this.editWin.myForm.getForm().submit({
						url : "personFile.do",
						method : "post",
						waitTitle : "数据传输",
						waitMsg : "数据传输中,请稍候......",
						success : this.onEditSuccess,
						failure : this.onEditFailure,
						scope : this,
						params : {
							operator : "editPersonFile",
							pfid : _pfId
						}
					});
		}
	},

	/**
	 * 变更成功事件
	 * 
	 * @param {}
	 *            _form
	 * @param {}
	 *            _action
	 */
	onEditSuccess : function(_form, _action) {
		// 隐藏窗体
		this.editWin.hide();
		// 刷新表格
		this.getStore().reload();
	},

	/**
	 * 变更失败事件
	 * 
	 * @param {}
	 *            _form
	 * @param {}
	 *            _action
	 */
	onEditFailure : function(_form, _action) {
		Ext.Msg.alert("系统消息", _action.result["errorMsg"]);
	},

	/**
	 * 审核成功事件
	 * 
	 * @param {}
	 *            _form
	 * @param {}
	 *            _action
	 */
	onCheckSuccess : function(_form, _action) {
		// 隐藏窗体
		this.checkWin.hide();
		// 刷新表格
		this.getStore().reload();
	},

	/**
	 * 审核失败事件
	 * 
	 * @param {}
	 *            _form
	 * @param {}
	 *            _action
	 */
	onCheckFailure : function(_form, _action) {
		Ext.Msg.alert("系统消息", _action.result["errorMsg"]);
	},

	/**
	 * 
	 * 创建一个新数据集
	 * 
	 * @param {}
	 *            _nowRecord 表格的当前选择行的record
	 */
	createNewRecord : function(_nowRecord) {
		var _record = new Ext.data.Record({
					"pf.pfName" : _nowRecord.get("pfName"), // 姓名
					"pf.pfBranch" : _nowRecord.get("pfBranch"), // 部门
					"pf.pfSex" : _nowRecord.get("pfSex"), // 性别
					"pf.pfCompany" : _nowRecord.get("pfCompany"), // 公司
					"pf.pfDesignation" : _nowRecord.get("pfDesignation"), // 职称
					"pf.pfAge" : _nowRecord.get("pfAge"), // 年龄
					"pf.pfPost" : _nowRecord.get("pfPost"), // 邮编
					"pf.pfBirthday" : _nowRecord.get("pfBirthday"), // 生日
					"pf.pfBank" : _nowRecord.get("pfBank"), // 开户银行
					"pf.pfRace" : _nowRecord.get("pfRace"), // 民族
					"pf.pfTelephone" : _nowRecord.get("pfTelephone"), // 联系电话
					"pf.pfMobilephone" : _nowRecord.get("pfMobilephone"), // 移动电话
					"pf.pfQq" : _nowRecord.get("pfQq"),// QQ
					"pf.pfEmail" : _nowRecord.get("pfEmail"), // 电子邮件
					"pf.pfHobby" : _nowRecord.get("pfHobby"), // 爱好
					"pf.pfReligion" : _nowRecord.get("pfReligion"),// 宗教信仰
					"pf.pfParty" : _nowRecord.get("pfParty"), // 政治面貌
					"pf.pfNationality" : _nowRecord.get("pfNationality"), // 国籍
					"pf.pfBirthplace" : _nowRecord.get("pfBirthplace"), // 籍贯
					"pf.pfEducatedDegree" : _nowRecord.get("pfEducatedDegree"),// 学历
					"pf.pfAccount" : _nowRecord.get("pfAccount"), // 银行卡号
					"pf.pfEducatedMajor" : _nowRecord.get("pfEducatedMajor"), // 专业
					"pf.pfIdCard" : _nowRecord.get("pfIdCard"), // 身份证号
					"pf.pfAddress" : _nowRecord.get("pfAddress"),// 地址
					"pf.pfJob" : _nowRecord.get("pfJob"), // 职位
					"pf.miStandardName" : _nowRecord.get("miStandardName"), // 薪酬标准名称
					"pf.miMoneyStandardId" : _nowRecord
							.get("miMoneyStandardId"), // 薪酬标准号
					"pf.miCountMoney" : _nowRecord.get("miCountMoney"), // 薪酬标准金额
					"pf.pfImage" : _nowRecord.get("pfImage")
				});
		return _record;
	},

	/**
	 * 变更人员档案信息按钮单击事件
	 */
	onEditPersonFile : function() {
		// // 获取当前表格行选择模式
		// var _sm = this.getSelectionModel();
		// // 如果选择了一行记录
		// if (_sm.getCount() > 0) {
		// // 显示窗体
		// this.editWin.show();
		// // 获取表格当前选择行的record
		// var _nowRecord = _sm.getSelected();
		// // 设置照片
		// var _imageName = _nowRecord.get("pfImage");
		// // 图片地址
		// var _photoUrl = "personImages/" + _imageName;
		// // 如果为空则赋值默认图片
		// if (_imageName == "" || _imageName == null) {
		// _photoUrl = "personImages/default_person.gif";
		// }
		// // 修改图片路径
		// this.editWin.myForm.findByType("textfield")[13].getEl().dom.src =
		// _photoUrl;
		// // 重构一个新的record
		// var _record = this.createNewRecord(_nowRecord);
		// // 加载数据
		// this.editWin.myForm.getForm().loadRecord(_record);
		// } else {
		// Ext.Msg.alert("系统消息", "请您选择一条您要变更的人员档案信息!");
		// }

	},

	/**
	 * 审核人员档案信息按钮单击事件
	 */
	onCheckPersonFile : function() {
		// // 获取当前表格行选择模式
		// var _sm = this.getSelectionModel();
		// // 如果选择了一行记录
		// if (_sm.getCount() > 0) {
		// // 获取表格当前选择行的record
		// var _nowRecord = _sm.getSelected();
		// // 如果审核通过则不进行审核
		// if (_nowRecord.get("pfState") == "通过审核") {
		// Ext.Msg.alert("系统消息",
		// "该人员档案信息已经通过审核,请选择一条未审核或未通过审核的人员档案信息进行审核!");
		// return;
		// }
		// // 重置this.checkWin.myForm
		// this.checkWin.myForm.getForm().reset();
		// // 显示窗体
		// this.checkWin.show();
		// // 设置照片
		// var _imageName = _nowRecord.get("pfImage");
		// // 图片地址
		// var _photoUrl = "personImages/" + _imageName;
		// // 如果为空则赋值默认图片
		// if (_imageName == "" || _imageName == null) {
		// _photoUrl = "personImages/default_person.gif";
		// }
		// // 修改图片路径
		// this.checkWin.myForm.findByType("textfield")[13].getEl().dom.src =
		// _photoUrl;
		// // 重构一个新的record
		// var _record = this.createNewRecord(_nowRecord);
		// // 加载数据
		// this.checkWin.myForm.getForm().loadRecord(_record);
		// } else {
		// Ext.Msg.alert("系统消息", "请您选择一条您要审核的人员档案信息!");
		// }

	},

	/**
	 * 清空查询条件按钮单击事件
	 */
	onClearCondition : function() {
		// 获取top工具条
		var _tbar = this.getTopToolbar();
		// 获取text框的值
		var _name = _tbar.items.get(1).setValue("");
		// 获取dateField框的值(Date类型)
		var _birthdayBegin = _tbar.items.get(3).setValue("");
		var _birthdayEnd = _tbar.items.get(5).setValue("");

		var _enlighten = _tbar.items.get(7).setValue("");
	},

	/**
	 * 查询按钮单击事件
	 */
	onSearch : function() {
		// 获取top工具条
		var _tbar = this.getTopToolbar();
		// 获取text框的值
		var _name = _tbar.items.get(1).getValue();
		// 获取dateField框的值(Date类型)
		var _birthdayBeginTxt = _tbar.items.get(3).getValue();
		var _sbirthdayBegin = new Date(_birthdayBeginTxt);
		// 获取日期(String)
		var _birthdayBegin = null;
		if (_birthdayBeginTxt != null && _birthdayBeginTxt != "") {
			_birthdayBegin = _sbirthdayBegin.format("Y-m-d");
		}

		// 获取dateField框的值(Date类型)
		var _birthdayEndTxt = _tbar.items.get(5).getValue();
		var _sbirthdayEnd = new Date(_birthdayEndTxt);
		// 获取日期(String)
		var _birthdayEnd = null;
		if (_birthdayEndTxt != null && _birthdayEndTxt != "") {
			_birthdayEnd = _sbirthdayEnd.format("Y-m-d");
		}

		var _enlighten = _tbar.items.get(7).getValue();

		/**
		 * 给当前表格的数据源添加请求参数
		 */
		this.getStore().baseParams = {
			personName : _name,
			birthdayBegin : _birthdayBegin,
			birthdayEnd : _birthdayEnd,
			enlighten : _enlighten
		};
		/**
		 * 重新加载数据
		 */
		this.getStore().load({
					params : {
						start : 0,
						limit : 17
					}
				});
	}

});