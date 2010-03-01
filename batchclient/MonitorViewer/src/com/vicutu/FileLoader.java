package com.vicutu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.csvreader.CsvReader;
import com.vicutu.data.LineData;

public class FileLoader extends Thread
{
	private CsvReader reader;

	private LineData lineData;

	private MainFrame mainFrame;

	public FileLoader(File file, char delimiter, MainFrame mainFrame)
	{
		try
		{
			reader = new CsvReader(new InputStreamReader(new FileInputStream(file)), '\t');
			this.mainFrame = mainFrame;
			lineData = new LineData();
			lineData.setTitle(file.getName());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public void run()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int num = 0;
		try
		{
			reader.readHeaders();
			String[] headers = reader.getHeaders();
			for (int i = 0; i < headers.length; i++)
			{
				num = num + headers[i].getBytes().length;
			}

			while (reader.readRecord())
			{
				Date date = sdf.parse(reader.get("Time"));
				lineData.addTimeLine(date);
				for (int j = 1; j < headers.length; j++)
				{
					try
					{
						Double value = Double.valueOf(reader.get(headers[j]));
						lineData.addLineData(headers[j], value);
					}
					catch (Exception e)
					{

					}
				}
				String rawRecord = reader.getRawRecord();
				num = num + rawRecord.getBytes().length;
			}
			if (lineData.getLineColumns().length > 0)
			{
				ViewPanel vp = new ViewPanel(lineData);
				mainFrame.addViewPane(vp);
			}
			else
			{
				JOptionPane.showMessageDialog(mainFrame, "Can't parese this File!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
