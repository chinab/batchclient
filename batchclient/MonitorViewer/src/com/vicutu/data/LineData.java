package com.vicutu.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineData
{
	private Map<String, List<Object>> lines = new HashMap<String, List<Object>>();

	private List<Date> timeLine = new ArrayList<Date>();

	private String title;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addTimeLine(Date date)
	{
		timeLine.add(date);
	}

	public Date[] getTimeLine()
	{
		return timeLine.toArray(new Date[timeLine.size()]);
	}

	public void addLineData(String lineColumn, Object value)
	{
		List<Object> list = lines.get(lineColumn);
		if (list != null)
		{
			list.add(value);
		}
		else
		{
			list = new ArrayList<Object>();
			list.add(value);
			lines.put(lineColumn, list);
		}
	}

	public Object[] getLineData(String lineColumn)
	{
		List<Object> list = lines.get(lineColumn);
		if (list != null)
		{
			return list.toArray(new Object[list.size()]);
		}
		else
		{
			return null;
		}
	}

	public String[] getLineColumns()
	{
		return lines.keySet().toArray(new String[lines.keySet().size()]);
	}
}
