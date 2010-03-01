package com.vicutu;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.infonode.tabbedpanel.TabAdapter;
import net.infonode.tabbedpanel.TabEvent;
import net.infonode.tabbedpanel.TabRemovedEvent;
import net.infonode.tabbedpanel.TabStateChangedEvent;
import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.theme.ShapedGradientTheme;
import net.infonode.tabbedpanel.theme.TabbedPanelTitledTabTheme;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import net.infonode.tabbedpanel.titledtab.TitledTabProperties;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -6849794470754667L;

	JPanel contentPane;

	JMenuBar jMenuBar1 = new JMenuBar();

	JMenu jMenuFile = new JMenu();

	JMenuItem jMenuFileOpenFile = new JMenuItem();

	JMenuItem jMenuFileExit = new JMenuItem();

	JMenu jMenuHelp = new JMenu();

	JMenuItem jMenuHelpAbout = new JMenuItem();

	BorderLayout borderLayout1 = new BorderLayout();

	TabbedPanel tabbedPanel = new TabbedPanel();

	private TabbedPanelTitledTabTheme tabbedPanelTitledTabTheme = new ShapedGradientTheme();

	private File currenPath;

	// Construct the frame
	public MainFrame()
	{
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try
		{
			jbInit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception
	{
		this.setIconImage(new ImageIcon(com.vicutu.MainFrame.class.getResource("about.png")).getImage());
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setTitle("MonitorViewer");
		this.setSize(new Dimension(800, 600));
		jMenuFile.setText("File");
		jMenuFileOpenFile.setText("Open");
		jMenuFileOpenFile.addActionListener(new MainFrame_jMenuFileOpenFile_ActionAdapter(this));
		jMenuFileExit.setText("Exit");
		jMenuFileExit.addActionListener(new MainFrame_jMenuFileExit_ActionAdapter(this));
		jMenuHelp.setText("Help");
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout.addActionListener(new MainFrame_jMenuHelpAbout_ActionAdapter(this));
		jMenuFile.add(jMenuFileOpenFile);
		jMenuFile.addSeparator();
		jMenuFile.add(jMenuFileExit);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuHelp);
		this.setJMenuBar(jMenuBar1);

		tabbedPanel.setTabAreaComponents(new JComponent[] { createCloseAllTabsButton(tabbedPanel) });
		tabbedPanel.getProperties().addSuperObject(tabbedPanelTitledTabTheme.getTabbedPanelProperties());
		
		contentPane.add(tabbedPanel, BorderLayout.CENTER);
	}

	// File | Exit action performed
	public void jMenuFileExit_actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}

	public void addViewPane(JPanel p)
	{
		TitledTab tab = new TitledTab(p.getName(), null, p, null);
		tab.setHighlightedStateTitleComponent(createCloseTabButton(tab));
		TitledTabProperties titledTabProperties = new TitledTabProperties();
		titledTabProperties.addSuperObject(tabbedPanelTitledTabTheme.getTitledTabProperties());
		tab.getProperties().addSuperObject(titledTabProperties);
		tabbedPanel.addTab(tab);
		tabbedPanel.setSelectedTab(tab);
		this.validate();
	}

	private JButton createCloseAllTabsButton(final TabbedPanel tabbedPanel)
	{
		final JButton closeButton = createXButton();
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Iterate over all tabs and remove them.
				int tabCount = tabbedPanel.getTabCount();
				for (int i = 0; i < tabCount; i++)
				{
					tabbedPanel.removeTab(tabbedPanel.getTabAt(0));
				}
			}
		});

		tabbedPanel.addTabListener(new TabAdapter()
		{
			public void tabAdded(TabEvent event)
			{
				closeButton.setVisible(true);
			}

			public void tabRemoved(TabRemovedEvent event)
			{
				// Hide the close button when there are no tabs in the tabbed
				// panel
				closeButton.setVisible(event.getTabbedPanel().getTabCount() > 0);
			}
			
			public void tabSelected(TabStateChangedEvent event)
			{
				
			}
		});
		closeButton.setVisible(false);
		return closeButton;
	}

	private JButton createXButton()
	{
		JButton closeButton = new JButton("X");
		closeButton.setOpaque(false);
		closeButton.setMargin(null);
		closeButton.setFont(closeButton.getFont().deriveFont(Font.BOLD).deriveFont((float) 10));
		closeButton.setBorder(new EmptyBorder(1, 1, 1, 1));
		return closeButton;
	}

	private JButton createCloseTabButton(final TitledTab tab)
	{
		JButton closeButton = createXButton();
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Closing the tab by removing it from the tabbed panel it is a
				// member of
				tab.getTabbedPanel().removeTab(tab);
			}
		});
		return closeButton;
	}

	// Help | About action performed
	public void jMenuHelpAbout_actionPerformed(ActionEvent e)
	{
		MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void jMenuFileOpenFile_actionPerformed(ActionEvent e)
	{
		JFileChooser chooser = null;
		if (currenPath != null)
		{
			chooser = new JFileChooser(currenPath);
		}
		else
		{
			chooser = new JFileChooser();
		}

		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int ret = chooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			if (file.isFile())
			{
				FileLoader loader = new FileLoader(file, '\t', this);
				SwingUtilities.invokeLater(loader);
				currenPath = file.getParentFile();
			}

			//loader.start();
		}
	}

	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			jMenuFileExit_actionPerformed(null);
		}
		if (e.getID() == WindowEvent.WINDOW_OPENED)
		{
			try
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}

			SwingUtilities.updateComponentTreeUI(this);
		}
	}
}

class MainFrame_jMenuFileExit_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jMenuFileExit_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jMenuFileExit_actionPerformed(e);
	}
}

class MainFrame_jMenuFileOpenFile_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jMenuFileOpenFile_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jMenuFileOpenFile_actionPerformed(e);
	}
}

class MainFrame_jMenuHelpAbout_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jMenuHelpAbout_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jMenuHelpAbout_actionPerformed(e);
	}
}