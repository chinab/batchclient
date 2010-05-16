package com.vicutu.commons.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

@SuppressWarnings("unchecked")
public final class FileUtils extends org.apache.commons.io.FileUtils {
	private static String PATH_SEPARATOR = "/";

	public static final String ENCODING = System.getProperty("file.encoding");

	private FileUtils() {
	}

	public final static String concatPath(String parent, String child) {
		if (parent == null || parent.trim().length() <= 0) {
			return child;
		}

		if (child == null || child.trim().length() <= 0) {
			return parent;
		}

		if (!parent.endsWith(PATH_SEPARATOR)) {
			if (!child.startsWith(PATH_SEPARATOR)) {
				parent = parent + PATH_SEPARATOR;
			}
		} else {
			if (child.startsWith(PATH_SEPARATOR)) {
				child = child.substring(1);
			}
		}

		return parent.concat(child);
	}

	public static File[] filterFiles(File folderFile, String filePattern, boolean recursion) {
		return filterFiles(folderFile, filePattern, recursion, false);
	}

	public static File[] filterFiles(File folderFile, String filePattern, boolean recursion, boolean sort) {
		List container = new ArrayList();
		filterFiles(folderFile, filePattern, container, recursion, sort);

		return (File[]) container.toArray(new File[container.size()]);
	}

	private static void filterFiles(File folderFile, String filePattern, List container, boolean recursion, boolean sort) {
		File[] childFile = folderFile.listFiles();

		if (sort) {
			Arrays.sort(childFile);
		}

		if (childFile != null) {
			for (int i = 0; i < childFile.length; i++) {
				File file = childFile[i];
				if (file.isFile()) {
					if (file.getName().toLowerCase().matches(filePattern)) {
						container.add(childFile[i]);
					}
				} else {
					if (recursion) {
						filterFiles(file, filePattern, container, true, sort);
					}
				}
			}
		}
	}

	public final static String getFullFileName(String path) {
		return FilenameUtils.getName(path);
	}

	public final static String trimExtendName(String qualifiedName) {
		int pos = qualifiedName.lastIndexOf(".");
		if (pos > 0) {
			return qualifiedName.substring(0, pos);
		} else {
			return qualifiedName;
		}
	}

	public final static String getExtendName(String qualifiedName) {
		int pos = qualifiedName.lastIndexOf(".");
		if (pos > 0) {
			return qualifiedName.substring(pos + 1);
		} else {
			return "";
		}
	}
}