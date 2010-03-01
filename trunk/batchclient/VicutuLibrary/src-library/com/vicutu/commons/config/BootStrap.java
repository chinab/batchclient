package com.vicutu.commons.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class BootStrap
{
	private static final String OPTION_START = "start";

	private static final String OPTION_STOP = "stop";

	private static final String OPTION_HELP = "help";

	public static void main(String[] args)
	{
		(new BootStrap()).process(args);
	}

	public void process(String args[])
	{
		CommandLineParser parser = new GnuParser();
		Options options = new Options();

		Option helpOption = new Option(OPTION_HELP, false, "print the help message");

		Option decodeOption = new Option(OPTION_START, false, "start server");
		decodeOption.setArgName(OPTION_START);

		Option encodeOption = new Option(OPTION_STOP, false, "stop server");
		decodeOption.setArgName(OPTION_STOP);

		options.addOption(helpOption);
		options.addOption(decodeOption);
		options.addOption(encodeOption);

		try
		{
			CommandLine command = parser.parse(options, args);

			if (command.hasOption(OPTION_HELP))
			{
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("usage [-options]", options);
			}
			else
			{
				if (command.hasOption(OPTION_START))
				{
					try
					{
						startup();
					}
					catch (Throwable ex)
					{
						System.err.println(ex);
						System.exit(1);
					}
				}
				else if (command.hasOption(OPTION_STOP))
				{
					try
					{
						shutdown();
					}
					catch (Throwable ex)
					{
						System.err.println(ex);
						System.exit(1);
					}
				}
				else
				{
					HelpFormatter formatter = new HelpFormatter();
					formatter.printHelp("usage [-options]", options);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void startup() throws Throwable
	{
		String systemPath = System.getProperty("systemPath");

		if (systemPath != null && !systemPath.equals(""))
		{
			Application.setSystemPath(systemPath);
		}

		Application.initialize();
		Application.bootstrap();
	}

	private void shutdown() throws Throwable
	{
	}
}