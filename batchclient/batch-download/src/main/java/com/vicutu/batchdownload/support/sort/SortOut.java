package com.vicutu.batchdownload.support.sort;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.vicutu.commons.lang.FileUtils;

public class SortOut {

	/**
	 * @param args
	 */
	private static final String INPUT_DIR = "J:/sec/cast/oriental/picture/bandage/";

	private static final String OUTPUT_DIR = "J:/sec/cast/oriental/picture/sortout/bandage/";

	public static void main(String[] args) throws Exception{
		Iterator<File> it = FileUtils.iterateFiles(new File(INPUT_DIR), new String[] { "jpg", "JPG" }, true);
		while (it.hasNext()) {
			File file = it.next();
			String fileName = file.getName();
			String dirName = StringUtils.substringBeforeLast(fileName, "_");
			System.out.println("moving file : " + file);
			File folder = new File(OUTPUT_DIR + dirName);
			FileUtils.moveFileToDirectory(file, folder, true);
		}
	}
}
