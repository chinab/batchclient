package com.vicutu.commons.config.support;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import com.vicutu.commons.crypto.DESCipher;

public class PasswordCoder {
	private static final String OPTION_DECODE = "d";

	private static final String OPTION_ENCODE = "e";

	private static final String OPTION_HELP = "help";

	public static final String FLAG_ENCODED_PWD = "{xor}";

	public static String decode(String password) {
		if (password.startsWith(FLAG_ENCODED_PWD)) {
			return DESCipher.decodeBase64(password.substring(FLAG_ENCODED_PWD.length()));
		} else {
			return DESCipher.decodeBase64(password);
		}
	}

	public static String encode(String password) {
		return DESCipher.encodeBase64(password);
	}

	public static void main(String[] args) {
		CommandLineParser parser = new GnuParser();
		Options options = new Options();

		Option helpOption = new Option(OPTION_HELP, false, "print the help message");

		Option decodeOption = new Option(OPTION_DECODE, false, "decode password");
		decodeOption.setArgName(OPTION_DECODE);

		Option encodeOption = new Option(OPTION_ENCODE, false, "encode password");
		decodeOption.setArgName(OPTION_ENCODE);

		options.addOption(helpOption);
		options.addOption(decodeOption);
		options.addOption(encodeOption);

		try {
			CommandLine command = parser.parse(options, args);

			if (command.hasOption(OPTION_HELP)) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("pwdcoder [-options] \"password\"", options);
			} else {
				String option = null;
				int counter = 0;

				if (command.hasOption(OPTION_DECODE)) {
					option = OPTION_DECODE;
					counter++;
				}

				if (command.hasOption(OPTION_ENCODE)) {
					option = OPTION_ENCODE;
					counter++;
				}

				if (args.length < counter + 1) {
					System.out.println("please use command: pwdcoder [-options] \"password\"");
					return;
				}

				String message = args[args.length - 1];

				if (option.equals(OPTION_DECODE)) {
					System.out.println(decode(message));
				} else {
					System.out.println(encode(message));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}