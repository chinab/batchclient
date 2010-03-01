var pageSize = 50;
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
		this.editWin = new EditEmployeeWindow();
		/**
		 * 数据存储器
		 */

		// 下拉列表框数据源(性别) 用于表格
		this.sexStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "sex"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(是否需要做工作) 用于表格
		this.enlightenStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "enlighten"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(性别) 用于查询条件
		this.sexStoreCondition = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "sex"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(是否需要做工作) 用于查询条件
		this.enlightenStoreCondition = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "enlighten"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

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
					}, {
						header : "工作方式",
						sortable : true,
						dataIndex : "mindPattern",
						editor : new Ext.form.ComboBox({
									id : 'mindPatternCmb', // 为combobox定义一个ID,必须的
									hiddenName : '',
									triggerAction : 'all',
									displayField : "label", // 要显示的值
									valueField : "value",
									store : this.mindPatternStore,
									editable : false
								}),
						renderer : function(value, cellmeta, record) {
							var _cmp = Ext.getCmp('mindPatternCmb');
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
					}, {
						header : "工作结果",
						sortable : true,
						dataIndex : "jobResult",
						editor : new Ext.form.ComboBox({
									id : 'jobResultCmb', // 为combobox定义一个ID,必须的
									hiddenName : '',
									triggerAction : 'all',
									displayField : "label", // 要显示的值
									valueField : "value",
									store : this.jobResultStore,
									editable : false
								}),
						renderer : function(value, cellmeta, record) {
							var _cmp = Ext.getCmp('jobResultCmb');
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
						emptyText : "",
						hiddenName : "sex",
						editable : false,
						triggerAction : "all", // 显示所有数据
						displayField : "label", // 要显示的值
						valueField : "value",
						allowBlank : true,
						width : 100,
						store : this.sexStoreCondition
					}, "　生日：", {
						xtype : "datefield",
						readOnly : true, // 只读
						format : "Y-m-d"
					}, "至", {
						xtype : "datefield",
						readOnly : true, // 只读
						format : "Y-m-d"
					}, "　需要做工作：", {
						xtype : "combo",
						fieldLabel : "需要做工作",
						emptyText : "",
						hiddenName : "enlighten",
						editable : false,
						triggerAction : "all", // 显示所有数据
						displayField : "label", // 要显示的值
						valueField : "value",
						allowBlank : true,
						width : 100,
						store : this.enlightenStoreCondition
					}, "-", {
						text : "查询",
						iconCls : "houfei-searchIcon",
						handler : this.onSearch,
						tooltip : "查询员工思想状态信息",
						scope : this
					}, "-", {
						text : "清空",
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
							handler : this.onEditEmployee,
							scope : this
						}]
			})

		});

		/**
		 * 加载数据
		 */
		this.sexStore.on('load', function(store) {
					this.enlightenStore.load();
				}, this);
				
		this.enlightenStore.on('load', function(store) {
					this.mindPatternStore.load();
				}, this);
				
		this.mindPatternStore.on('load', function(store) {
					this.jobResultStore.load();
				}, this);

		this.jobResultStore.on('load', function(store) {
					this.getStore().baseParams = {
						enlighten : "1"
					}
					this.getStore().load({
								params : {
									start : 0,
									limit : pageSize
								}
							});
				}, this);

		this.sexStore.load();

		// 监听当前对象editWin的自定义事件onEditWinEditOk(变更窗体确认变更按钮单击事件)
		this.editWin.on("onEditWinEditOk", this.onEditWinEditOk, this);
	},

	/**
	 * 当前对象editWin的自定义事件onEditWinEditOk(变更窗体确认变更按钮单击事件)
	 */
	onEditWinEditOk : function() {
		// 获取主键id
		var _id = this.getSelectionModel().getSelected().get("id");
		// 如果表单验证通过则提交表单
		if (this.editWin.myForm.getForm().isValid() == true) {
			// 提交表单
			this.editWin.myForm.getForm().submit({
						url : appContextPath + "/employee/save.do",
						method : "post",
						waitTitle : "数据传输",
						waitMsg : "数据传输中,请稍候......",
						success : this.onEditSuccess,
						failure : this.onEditFailure,
						scope : this,
						params : {
							id : _id
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
		Ext.Msg.alert("系统消息", _action.result["message"]);
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
					"id" : _nowRecord.get("id"),
					"name" : _nowRecord.get("name"), // 姓名
					"birthday" : _nowRecord.get("birthday"), // 部门
					"sex" : _nowRecord.get("sex"), // 性别
					"eduQualification" : _nowRecord.get("eduQualification"), // 公司
					"politicalAppearance" : _nowRecord
							.get("politicalAppearance"), // 职称
					"category" : _nowRecord.get("category"), // 年龄
					"station" : _nowRecord.get("station"), // 邮编
					"kidney" : _nowRecord.get("kidney"), // 生日
					"interest" : _nowRecord.get("interest"), // 开户银行
					"associateRelation" : _nowRecord.get("associateRelation"), // 民族
					"community" : _nowRecord.get("community"), // 联系电话
					"studyAttitude" : _nowRecord.get("studyAttitude"), // 移动电话
					"serviceAttitude" : _nowRecord.get("serviceAttitude"),// QQ
					"campaign" : _nowRecord.get("campaign"), // 电子邮件
					"afterHoursBusiness" : _nowRecord.get("afterHoursBusiness"), // 爱好
					"economy" : _nowRecord.get("economy"),// 宗教信仰
					"familyRelation" : _nowRecord.get("familyRelation"), // 政治面貌
					"performance" : _nowRecord.get("performance"), // 国籍
					"modelEffect" : _nowRecord.get("modelEffect"), // 籍贯
					"skill" : _nowRecord.get("skill"),// 学历
					"enlighten" : _nowRecord.get("enlighten"), // 银行卡号
					"mindPattern" : _nowRecord.get("mindPattern"), // 专业
					"jobResult" : _nowRecord.get("jobResult"), // 身份证号
					"rule" : _nowRecord.get("rule"),// 地址
					"responsibility" : _nowRecord.get("responsibility"), // 职位
					"security" : _nowRecord.get("security"), // 薪酬标准名称
					"duty" : _nowRecord.get("duty"), // 薪酬标准号
					"faith" : _nowRecord.get("faith"), // 薪酬标准金额
					"morality" : _nowRecord.get("morality"),
					"promise" : _nowRecord.get("promise"),
					"honesty" : _nowRecord.get("honesty"),
					"honesty" : _nowRecord.get("technology"),
					"management" : _nowRecord.get("management"),
					"art" : _nowRecord.get("art"),
					"art" : _nowRecord.get("sports"),
					"memo" : _nowRecord.get("memo")
				});
		return _record;
	},

	/**
	 * 变更人员档案信息按钮单击事件
	 */
	onEditEmployee : function() {
		// 获取当前表格行选择模式
		var _sm = this.getSelectionModel();
		// 如果选择了一行记录
		if (_sm.getCount() > 0) {
			// 显示窗体
			this.editWin.show();
			// 获取表格当前选择行的record
			var _nowRecord = _sm.getSelected();

			var _record = this.createNewRecord(_nowRecord);
			// 加载数据
			this.editWin.myForm.getForm().loadRecord(_record);
		} else {
			Ext.Msg.alert("系统消息", "请您选择一条需要修改的记录!");
		}

	},

	/**
	 * 清空查询条件按钮单击事件
	 */
	onClearCondition : function() {
		// 获取top工具条
		var _tbar = this.getTopToolbar();
		// 获取text框的值
		var _name = _tbar.items.get(1).setValue("");
		var _sex = _tbar.items.get(3).setValue("");
		// 获取dateField框的值(Date类型)
		var _birthdayBegin = _tbar.items.get(5).setValue("");
		var _birthdayEnd = _tbar.items.get(7).setValue("");

		var _enlighten = _tbar.items.get(9).setValue("");
	},

	/**
	 * 查询按钮单击事件
	 */
	onSearch : function() {
		// 获取top工具条
		var _tbar = this.getTopToolbar();
		// 获取text框的值
		var _name = _tbar.items.get(1).getValue();

		var _sex = _tbar.items.get(3).getValue();

		// 获取dateField框的值(Date类型)
		var _birthdayBeginTxt = _tbar.items.get(5).getValue();
		var _sbirthdayBegin = new Date(_birthdayBeginTxt);
		// 获取日期(String)
		var _birthdayBegin = null;
		if (_birthdayBeginTxt != null && _birthdayBeginTxt != "") {
			_birthdayBegin = _sbirthdayBegin.format("Y-m-d");
		}

		// 获取dateField框的值(Date类型)
		var _birthdayEndTxt = _tbar.items.get(7).getValue();
		var _sbirthdayEnd = new Date(_birthdayEndTxt);
		// 获取日期(String)
		var _birthdayEnd = null;
		if (_birthdayEndTxt != null && _birthdayEndTxt != "") {
			_birthdayEnd = _sbirthdayEnd.format("Y-m-d");
		}

		var _enlighten = _tbar.items.get(9).getValue();

		/**
		 * 给当前表格的数据源添加请求参数
		 */
		this.getStore().baseParams = {
			name : _name,
			sex : _sex,
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
						limit : pageSize
					}
				});
	}

});