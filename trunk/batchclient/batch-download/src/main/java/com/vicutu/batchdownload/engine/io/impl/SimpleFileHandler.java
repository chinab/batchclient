package com.vicutu.batchdownload.engine.io.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.engine.io.AbstractFileHandler;
import com.vicutu.batchdownload.engine.io.FileHandler;
import com.vicutu.commons.lang.FileUtils;

@Component
public class SimpleFileHandler extends AbstractFileHandler implements FileHandler {

	@Override
	public boolean exists(String folder, String name) {
		return (new File(folder, name)).exists();
	}

	@Override
	public long save(AccessDetail accessDetail, InputStream input, String folder, String name) throws IOException {
		OutputStream os = null;
		try {
			File folderFile = new File(folder);
			if (!folderFile.exists()) {
				folderFile.mkdirs();
			}
			os = FileUtils.openOutputStream(new File(folderFile, name));
			return this.copyLarge(accessDetail, input, os);
		} finally {
			IOUtils.closeQuietly(os);
		}
	}
}
