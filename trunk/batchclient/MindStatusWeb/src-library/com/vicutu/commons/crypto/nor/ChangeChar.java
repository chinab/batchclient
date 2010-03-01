package com.vicutu.commons.crypto.nor;

public class ChangeChar {
	String isoCode;

	public ChangeChar() {
		isoCode = "";
	}

	public String ChangeTOGB(String s) {
		try {
			isoCode = new String(s.getBytes("8859_1"), "GB2312");
		} catch (Exception ex) {
		}
		return isoCode;
	}

	public String gbToISO(String s) {
		try {
			isoCode = new String(s.getBytes("GB2312"), "8859_1");
		} catch (Exception ex) {
		}
		return isoCode;
	}

	public String ChangeToTitle(String s) {
		try {
			int i = s.trim().indexOf("</");
			if (i != -1) {
				String s1 = s.trim().substring(0, i);
				int j = s1.trim().lastIndexOf(">");
				isoCode = s1.trim().substring(j + 1);
			} else {
				isoCode = s.trim();
			}
		} catch (Exception ex) {
		}
		return isoCode;
	}

	public String replace(String parentStr, String ch, String rep) {
		int i = parentStr.indexOf(ch);
		StringBuffer sb = new StringBuffer();
		if (i == -1)
			return parentStr;
		sb.append(parentStr.substring(0, i) + rep);
		if (i + ch.length() < parentStr.length())
			sb.append(replace(parentStr.substring(i + ch.length(), parentStr
					.length()), ch, rep));
		return sb.toString();
	}
}
