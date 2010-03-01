package com.vicutu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.vicutu.data.LineData;

public class ViewPanel extends JPanel implements ItemListener
{
	private static final long serialVersionUID = 6937751692202070568L;

	private LineData lineData;

	private ChartPanel chartpanel;

	private List<JRadioButton> buttons = new ArrayList<JRadioButton>();

	public ViewPanel(LineData lineData)
	{
		this.lineData = lineData;
		this.setName(lineData.getTitle());
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup buttonGroup = new ButtonGroup();
		String[] lineColumns = this.lineData.getLineColumns();
		for (int i = 0; i < lineColumns.length; i++)
		{
			JRadioButton radioButton = new JRadioButton(lineColumns[i]);
			radioButton.addItemListener(this);
			buttonGroup.add(radioButton);
			topPanel.add(radioButton);
			buttons.add(radioButton);
		}
		buttons.get(0).setSelected(true);
		XYDataset xydataset = createDataset(lineData.getLineColumns()[0]);
		JFreeChart jfreechart = createChart(lineData.getTitle(), xydataset, lineData.getLineColumns()[0]);
		chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setMouseZoomable(true, false);
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(chartpanel, BorderLayout.CENTER);
	}

	private TimeSeries createTimeSeries(String column)
	{
		TimeSeries timeseries = new TimeSeries(column, Minute.class);
		try
		{
			Object[] values = lineData.getLineData(column);
			Date[] dates = lineData.getTimeLine();
			for (int i = 0; i < values.length; i++)
			{
				timeseries.add(new Minute(dates[i]), (Double) values[i]);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return timeseries;
	}

	private JFreeChart createChart(String title, XYDataset xydataset, String column)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "Time", column, xydataset, true, true, false);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0"));
		xyitemrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
		return jfreechart;
	}

	private XYDataset createDataset(String column)
	{
		TimeSeries timeseries = createTimeSeries(column);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	public void itemStateChanged(ItemEvent e)
	{
		if (chartpanel != null)
		{
			this.remove(chartpanel);
			XYDataset xydataset = createDataset(((JRadioButton) e.getSource()).getActionCommand());
			JFreeChart jfreechart = createChart(lineData.getTitle(), xydataset, ((JRadioButton) e.getSource()).getActionCommand());
			chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(500, 270));
			chartpanel.setMouseZoomable(true, false);
			this.add(chartpanel, BorderLayout.CENTER);
			this.validate();
		}
	}
}