package com.vicutu.commons.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FileUtils extends org.apache.commons.io.FileUtils
{
	private static final String PATH_SEPARATOR = "/";
	
	
	

	public static final String ENCODING = System.getProperty("file.encoding");

	private FileUtils()
	{
	}

	public final static String concatPath(String parent, String child)
	{
		if (parent == null || parent.trim().length() <= 0)
		{
			return child;
		}

		if (child == null || child.trim().length() <= 0)
		{
			return parent;
		}

		if (!parent.endsWith(PATH_SEPARATOR))
		{
			if (!child.startsWith(PATH_SEPARATOR))
			{
				parent = parent + PATH_SEPARATOR;
			}
		}
		else
		{
			if (child.startsWith(PATH_SEPARATOR))
			{
				child = child.substring(1);
			}
		}

		return parent.concat(child);
	}
	
	public static String[] getResourceLocation(File folderFile, String filePattern){
		File[] files=filterFiles(folderFile, filePattern);
		String[] locations=new String[files.length];
		for(int i=0;i<files.length;i++){
			locations[i]=files[i].toURI().toString();
		}
		return locations;
	}

	public static File[] filterFiles(File folderFile, String filePattern)
	{
		List<File> container = new ArrayList<File>();
		filterFiles(folderFile, filePattern, container);

		return container.toArray(new File[container.size()]);
	}

	private static void filterFiles(File folderFile, String filePattern, List<File> container)
	{
		File[] childFile = folderFile.listFiles();
		if (childFile != null)
		{
			for (int i = 0; i < childFile.length; i++)
			{
				File file = childFile[i];
				if (file.isFile())
				{
					if (file.getName().toLowerCase().matches(filePattern))
					{
						container.add(childFile[i]);
					}
				}
				else
				{
					filterFiles(file, filePattern, container);
				}
			}
		}
	}
}