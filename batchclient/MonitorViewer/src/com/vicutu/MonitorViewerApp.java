package com.vicutu;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MonitorViewerApp
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MainFrame frame = new MainFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height)
		{
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width)
		{
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.validate();
		frame.setVisible(true);
	}

}
