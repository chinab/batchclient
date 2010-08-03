package com.vicutu.commons.lang;

public final class SpellUtils
{
	private static final char[] charTable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座' };

	private static final char[] alphaTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static int[] table = new int[27];

	static
	{
		for (int i = 0; i < 27; ++i)
		{
			table[i] = gbValue(charTable[i]);
		}
	}

	private SpellUtils()
	{
	}

	private static int gbValue(char ch)
	{
		String str = new String();
		str += ch;
		try
		{
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
			{
				return 0;
			}

			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	private static char Char2Alpha(char ch)
	{
		if (ch >= 'a' && ch <= 'z')
		{
			return (char) (ch - 'a' + 'A');
		}

		if (ch >= 'A' && ch <= 'Z')
		{
			return ch;
		}

		if (ch >= '0' && ch <= '9')
		{
			return ch;
		}

		int gb = gbValue(ch);
		if (gb < table[0])
		{
			return '0';
		}

		int i;
		for (i = 0; i < 26; ++i)
		{
			if (match(i, gb))
			{
				break;
			}
		}

		if (i >= 26)
		{
			return '0';
		}
		else
		{
			return alphaTable[i];
		}
	}

	public static String String2Alpha(String SourceStr)
	{
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try
		{
			for (i = 0; i < StrLength; i++)
			{
				Result += Char2Alpha(SourceStr.charAt(i));
			}
		}
		catch (Exception e)
		{
			Result = "";
		}
		return Result;
	}

	private static boolean match(int i, int gb)
	{
		if (gb < table[i])
		{
			return false;
		}

		int j = i + 1;

		//字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i]))
		{
			++j;
		}

		if (j == 26)
		{
			return gb <= table[j];
		}
		else
		{
			return gb < table[j];
		}
	}

	public static void main(String[] args)
	{
		System.out.println(SpellUtils.String2Alpha("测试  ：中华人民共和国！"));
		System.out.println(SpellUtils.String2Alpha("裴贺先"));
		System.out.println(SpellUtils.String2Alpha("123abc中文测试吴永"));
	}
}