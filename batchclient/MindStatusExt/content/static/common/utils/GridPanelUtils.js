// 命名空间
Ext.namespace("Ext.houfei.utils");

/**
 * @author 侯非
 * @date 2009-04-12
 * @description 表格工具类
 */
Ext.houfei.utils.GridPanelUtils = {
	/**
	 * @description 表格单元格tooltip提示信息方法(标准转换)
	 * @scope public
	 * @param {}
	 *            _v 表格单元格是值
	 * @return {} 返回一个格式化后的一段HMLT标记
	 */
	cellStandardRenderer : function(_v) {
		return "<span ext:qtip=" + _v + ">" + _v + "</span>";
	},

	/**
	 * @description 表格单元格tooltip提示信息方法(日期格式转换)
	 * @scope public
	 * @param {}
	 *            _v 表格单元格是值
	 * @return {} 返回一个格式化后的一段HMLT标记
	 */
	cellDateRenderer : function(_v) {
		var _value = Ext.util.Format.substr(_v, 0, 10);
		var _sdate = new Date();
		_sdate = Date.parseDate(_value, "Y-m-d");
		var _date = Ext.util.Format.date(_sdate, "Y年m月d日");
		return "<span ext:qtip=" + _date + ">" + _date + "</span>";
	}

};