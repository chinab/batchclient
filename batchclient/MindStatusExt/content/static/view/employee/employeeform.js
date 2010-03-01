/**
 * @author 侯非
 * @date 2009年3月10日
 * @class PersonFileFormPanel
 * @extends Ext.form.FormPanel
 * @description 人员档案信息表单
 */
EmployeeFormPanel = Ext.extend(Ext.form.FormPanel, {
	myid : "myid",

	sexStore : null, // 下拉列表框数据源(性别)
	eduQualificationStore : null, // 下拉列表框数据源(学历)
	politicalAppearanceStore : null, // 下拉列表框数据源(政治面貌)
	categoryStore : null, // 下拉列表框数据源(工作类别)
	stationStore : null, // 下拉列表框数据源(岗位)
	kidneyStore : null, // 下拉列表框数据源(性格)
	interestStore : null, // 下拉列表框数据源(爱好)
	associateRelationStore : null, // 下拉列表框数据源(同事关系)
	communityStore : null, // 下拉列表框数据源(社交情况)
	studyAttitudeStore : null, // 下拉列表框数据源(学习态度)
	serviceAttitudeStore : null, // 下拉列表框数据源(服务态度)
	campaignStore : null, // 下拉列表框数据源(社会活动)
	afterHoursBusinessStore : null, // 下拉列表框数据源(是否业余经商)
	economyStore : null, // 下拉列表框数据源(家庭生活状况)
	familyRelationStore : null, // 下拉列表框数据源(夫妻关系)
	awardStore : null, // 下拉列表框数据源(奖励情况)
	performanceStore : null, // 下拉列表框数据源(近期综合表现)
	modelEffectStore : null, // 下拉列表框数据源(模范作用发挥)
	skillStore : null, // 下拉列表框数据源(岗位技能)
	enlightenStore : null, // 下拉列表框数据源(是否需要做工作)
	mindPatternStore : null, // 下拉列表框数据源(工作方式)
	jobResultStore : null, // 下拉列表框数据源(工作结果)
	ruleStore : null, // 下拉列表框数据源(遵守规定)
	responsibilityStore : null, // 下拉列表框数据源(履行职责)
	securityStore : null, // 下拉列表框数据源(责任意识)
	dutyStore : null, // 下拉列表框数据源(安全意识)
	faithStore : null, // 下拉列表框数据源(忠实守信)
	moralityStore : null, // 下拉列表框数据源(道德水平)
	promiseStore : null, // 下拉列表框数据源(践行承诺)
	honestyStore : null, // 下拉列表框数据源(诚实严谨)
	technologyStore : null, // 下拉列表框数据源(技术)
	managementStore : null, // 下拉列表框数据源(管理)
	artStore : null, // 下拉列表框数据源(文艺)
	sportsStore : null, // 下拉列表框数据源(体育)

	// 构造方法
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}
		Ext.apply(this, _config);

		// 下拉列表框数据源(性别)
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

		// 下拉列表框数据源(学历)
		this.eduQualificationStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "edu_qualification"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(政治面貌)
		this.politicalAppearanceStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "political_appearance"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(工作类别)
		this.categoryStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "category"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(岗位)
		this.stationStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "station"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(性格)
		this.kidneyStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "kidney"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(爱好)
		this.interestStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "interest"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(同事关系)
		this.associateRelationStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "associate_relation"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(社交情况)
		this.communityStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "community"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(学习态度)
		this.studyAttitudeStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "study_attitude"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(服务态度)
		this.serviceAttitudeStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "service_attitude"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(社会活动)
		this.campaignStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "campaign"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(是否业余经商)
		this.afterHoursBusinessStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "after_hours_business"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(家庭生活状况)
		this.economyStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "economy"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(夫妻关系)
		this.familyRelationStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "family_relation"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(奖励情况)
		this.awardStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "award"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(近期综合表现)
		this.performanceStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "performance"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(模范作用发挥)
		this.modelEffectStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "model_effect"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(岗位技能)
		this.skillStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "skill"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(是否需要做工作)
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

		// 下拉列表框数据源(遵守规定)
		this.ruleStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "rule"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(履行职责)
		this.responsibilityStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "responsibility"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(安全意识)
		this.securityStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "security"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(责任意识)
		this.dutyStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "duty"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(忠实守信)
		this.faithStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "faith"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(道德水平)
		this.moralityStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "morality"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(践行承诺)
		this.promiseStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "promise"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(诚实严谨)
		this.honestyStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "honesty"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(技术)
		this.technologyStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "technology"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(管理)
		this.managementStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "management"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(文艺)
		this.artStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "art"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 下拉列表框数据源(体育)
		this.sportsStore = new Ext.data.JsonStore({
					url : appContextPath
							+ "/dictionary/getDictionaryByTypeName.do",
					root : 'prop_list',
					baseParams : {
						"prop_type" : "sports"
					},
					fields : ["label", "value"],
					autoLoad : true
				});

		// 拷贝父类的构造方法到当前对象
		EmployeeFormPanel.superclass.constructor.call(this, {
			id : this.myid,
			bodyStyle : "backgroundColor:#DFE8F6;padding:10px;",
			autoScroll : true,
			border : false,
			items : [{
						xtype : "fieldset",
						title : "基本信息",
						autoHeight : true,
						autoWidth : true,
						labelWidth : 55,
						layout : "column",
						items : [{
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												fieldLabel : "姓名",
												name : "name",
												allowBlank : false
											}, {
												xtype : "combo",
												fieldLabel : "学历",
												emptyText : "请选择学历",
												hiddenName : "eduQualification",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.eduQualificationStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "datefield", // 日期框
												fieldLabel : "生日", // label
												format : "Y-m-d",
												name : "birthday",
												readOnly : true, // 只读
												allowBlank : false,
												emptyText : "" // 为空显示信息
											}, {
												xtype : "combo",
												fieldLabel : "政治面貌",
												emptyText : "请选择政治面貌",
												hiddenName : "politicalAppearance",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.politicalAppearanceStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "性别",
												emptyText : "请选择性别",
												hiddenName : "sex",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												allowBlank : false,
												store : this.sexStore
											}, {
												xtype : "combo",
												fieldLabel : "岗位",
												emptyText : "请选择岗位",
												hiddenName : "station",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.stationStore
											}]
								}, {
									columnWidth : .24,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "工作类别",
												emptyText : "请选择工作类别",
												hiddenName : "category",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.categoryStore
											}]
								}]
					}, {
						xtype : "fieldset",
						title : "常规行为特征",
						autoHeight : true,
						autoWidth : true,
						labelWidth : 55,
						layout : "column",
						items : [{
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "性格",
												emptyText : "请选择性格",
												hiddenName : "kidney",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.kidneyStore
											}, {
												xtype : "combo",
												fieldLabel : "爱好",
												emptyText : "请选择爱好",
												hiddenName : "interest",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.interestStore
											}, {
												xtype : "combo",
												fieldLabel : "家庭状况",
												emptyText : "请选择家庭生活状况",
												hiddenName : "economy",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.economyStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "同事关系",
												emptyText : "请选择同事关系",
												hiddenName : "associateRelation",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.associateRelationStore
											}, {
												xtype : "combo",
												fieldLabel : "社交情况",
												emptyText : "请选择社交情况",
												hiddenName : "community",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.communityStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "学习态度",
												emptyText : "请选择学习态度",
												hiddenName : "studyAttitude",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.studyAttitudeStore
											}, {
												xtype : "combo",
												fieldLabel : "服务态度",
												emptyText : "请选择服务态度",
												hiddenName : "serviceAttitude",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.serviceAttitudeStore
											}]
								}, {
									columnWidth : .24,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "社会活动",
												emptyText : "请选择社会活动",
												hiddenName : "campaign",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.campaignStore
											}, {
												xtype : "combo",
												fieldLabel : "业余经商",
												emptyText : "请选择是否业余经商",
												hiddenName : "afterHoursBusiness",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.afterHoursBusinessStore
											}]
								}]
					}, {
						xtype : "fieldset",
						title : "近期动态",
						autoHeight : true,
						autoWidth : true,
						labelWidth : 55,
						layout : "column",
						items : [{
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "夫妻关系",
												emptyText : "请选择夫妻关系",
												hiddenName : "familyRelation",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.familyRelationStore
											}, {
												xtype : "combo",
												fieldLabel : "岗位技能",
												emptyText : "请选择岗位技能",
												hiddenName : "skill",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.skillStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "奖励情况",
												emptyText : "请选择同事关系",
												hiddenName : "award",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.awardStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "近期表现",
												emptyText : "请选择近期综合表现",
												hiddenName : "performance",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.performanceStore
											}]
								}, {
									columnWidth : .24,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "模范作用",
												emptyText : "请选择模范作用发挥",
												hiddenName : "modelEffect",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.modelEffectStore
											}]
								}]
					}, {
						xtype : "fieldset",
						title : "针对性思想工作",
						autoHeight : true,
						autoWidth : true,
						labelWidth : 75,
						layout : "column",
						items : [{
									columnWidth : .30,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "需要做工作",
												emptyText : "请选择需要做工作",
												hiddenName : "enlighten",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.enlightenStore
											}]
								}, {
									columnWidth : .30,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "工作方式",
												emptyText : "请选择工作方式",
												hiddenName : "mindPattern",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.mindPatternStore
											}]
								}, {
									columnWidth : .30,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "工作结果",
												emptyText : "请选择工作结果",
												hiddenName : "jobResult",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.jobResultStore
											}]
								}]
					}, {
						xtype : "fieldset",
						title : "诚信记录",
						autoHeight : true,
						autoWidth : true,
						labelWidth : 55,
						layout : "column",
						items : [{
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "遵守规定",
												emptyText : "请选择遵守规定",
												hiddenName : "rule",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.ruleStore
											}, {
												xtype : "combo",
												fieldLabel : "忠实守信",
												emptyText : "请选择忠实守信",
												hiddenName : "faith",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.faithStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "履行职责",
												emptyText : "请选择履行职责",
												hiddenName : "responsibility",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.responsibilityStore
											}, {
												xtype : "combo",
												fieldLabel : "道德水平",
												emptyText : "请选择道德水平",
												hiddenName : "morality",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.moralityStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "安全意识",
												emptyText : "请选择安全意识",
												hiddenName : "security",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.securityStore
											}, {
												xtype : "combo",
												fieldLabel : "践行承诺",
												emptyText : "请选择践行承诺",
												hiddenName : "promise",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.promiseStore
											}]
								}, {
									columnWidth : .24,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "责任意识",
												emptyText : "请选择责任意识",
												hiddenName : "duty",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.dutyStore
											}, {
												xtype : "combo",
												fieldLabel : "诚实严谨",
												emptyText : "请选择诚实严谨",
												hiddenName : "honesty",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.honestyStore
											}]
								}]
					}, {
						xtype : "fieldset",
						title : "人才库",
						autoHeight : true,
						autoWidth : true,
						labelWidth : 55,
						layout : "column",
						items : [{
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "技术水平",
												emptyText : "请选择技术水平",
												hiddenName : "technology",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.technologyStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "管理水平",
												emptyText : "请选择管理水平",
												hiddenName : "management",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.managementStore
											}]
								}, {
									columnWidth : .25,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "文艺水平",
												emptyText : "请选择文艺水平",
												hiddenName : "art",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.artStore
											}]
								}, {
									columnWidth : .24,
									layout : "form",
									border : false,
									bodyStyle : "backgroundColor:#DFE8F6;",
									defaults : {
										anchor : "92%",
										xtype : "textfield",
										allowBlank : true
									},
									items : [{
												xtype : "combo",
												fieldLabel : "体育水平",
												emptyText : "请选择体育水平",
												hiddenName : "sports",
												triggerAction : "all", // 显示所有数据
												displayField : "label", // 要显示的值
												valueField : "value",
												store : this.sportsStore
											}]
								}]
					}, {
						xtype : "fieldset",
						title : "其他信息",
						autoHeight : true,
						autoWidth : true,
						labelWidth : 55,
						layout : "form",
						defaults : {
							xtype : "textarea",
							anchor : "92%",
							height : 52
						},
						items : [{
									fieldLabel : "备注",
									name : "memo"
								}]
					}]
		});
	}

});
