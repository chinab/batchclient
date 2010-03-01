package com.mindstatus.component.employee;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.mindstatus.bean.vo.MsEmployee;
import com.mindstatus.bo.IMsEmployeeBo;
import com.mindstatus.common.ApplicationConst;
import com.mindstatus.phy.PhysiologyCycle;
import com.mindstatus.phy.PhysiologyCycleUtils;

public class PreparePhysiologyCycleAction extends Action
{

	protected IMsEmployeeBo msEmployeeBo;

	public void setMsEmployeeBo(IMsEmployeeBo msEmployeeBo)
	{
		this.msEmployeeBo = msEmployeeBo;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaActionForm dynaForm = (DynaActionForm) form;
		String editId = request.getParameter("editId");
		MsEmployee msEmployee = null;
		if (editId == null)
		{
			msEmployee = msEmployeeBo.findById((Integer) dynaForm.get("id"));
		}
		else
		{
			msEmployee = msEmployeeBo.findById(Integer.valueOf(editId));
		}
		
		dynaForm.set("name", msEmployee.getName());
		dynaForm.set("birthday", msEmployee.getBirthday());
		
		Date birthday = msEmployee.getBirthday();
		if(birthday!=null)
		{
			msEmployee.setAge(Integer.valueOf(PhysiologyCycleUtils.computeAgeByBirthday(birthday)));
			dynaForm.set("age", msEmployee.getAge());
			PhysiologyCycle pc = PhysiologyCycleUtils.computePhysiologyCycle(birthday);
			dynaForm.set("lifeDays", pc.getLifeDays());
			dynaForm.set("physical", Double.valueOf(pc.getPhysical()).intValue());
			dynaForm.set("feeling", Double.valueOf(pc.getFeeling()).intValue());
			dynaForm.set("intelligence", Double.valueOf(pc.getIntelligence()).intValue());
			dynaForm.set("average", Double.valueOf(pc.getAverage()).intValue());
			this.saveChart(request, birthday);
		}

		return mapping.findForward(ApplicationConst.ACTION_FORWARD_SUCCESS);
	}

	private void saveChart(HttpServletRequest request, Date birthday) throws Exception
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date[] days = PhysiologyCycleUtils.getSeriesMothDays(c.getTime());

		TimeSeries timeSeriesPhysical = new TimeSeries("体能指数", Day.class);
		TimeSeries timeSeriesFeeling = new TimeSeries("情感指数", Day.class);
		TimeSeries timeSeriesIntelligence = new TimeSeries("智力指数", Day.class);
		//时间曲线数据集合

		for (int i = 0; i < days.length; i++)
		{
			PhysiologyCycle pc = PhysiologyCycleUtils.computePhysiologyCycle(birthday, days[i]);
			timeSeriesPhysical.add(new Day(days[i]), pc.getPhysical());
			timeSeriesFeeling.add(new Day(days[i]), pc.getFeeling());
			timeSeriesIntelligence.add(new Day(days[i]), pc.getIntelligence());
		}

		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		lineDataset.addSeries(timeSeriesPhysical);
		lineDataset.addSeries(timeSeriesFeeling);
		lineDataset.addSeries(timeSeriesIntelligence);

		JFreeChart chart = ChartFactory.createTimeSeriesChart("生理周期曲线(" + DateFormatUtils.ISO_DATE_FORMAT.format(new Date())+")", "日期", "指数", lineDataset, true, true, true);
		XYPlot plot = (XYPlot) chart.getPlot();

		ValueAxis domainAxis = plot.getDomainAxis();
		ValueAxis rAxis = plot.getRangeAxis();

		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		((DateAxis) domainAxis).setDateFormatOverride(formatter);

		plot.setBackgroundPaint(Color.white);//设置网格背景颜色
		plot.setDomainGridlinePaint(Color.pink);//设置网格竖线颜色
		plot.setRangeGridlinePaint(Color.pink);//设置网格横线颜色
		plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));//设置曲线图与xy轴的距离，即曲线与xy轴贴近的距离

		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		//		设置X轴坐标上的文字
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		//		设置X轴的标题文字
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		//		设置Y轴坐标上的文字
		rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		//		设置Y轴的标题文字
		rAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));

		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		chart.setAntiAlias(true);
		String fileName = ServletUtilities.saveChartAsJPEG(chart, 740, 400, request.getSession());
		String graphUrl = request.getContextPath() + "/DisplayChart?filename=" + fileName;
		request.setAttribute("chartFileName", fileName);
		request.setAttribute("graphUrl", graphUrl);
	}
}
