package com.vicutu.batchdownload.engine.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.engine.io.AbstractFileHandler;
import com.vicutu.batchdownload.engine.io.FileHandler;

import de.schlichtherle.truezip.file.TArchiveDetector;
import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileOutputStream;
import de.schlichtherle.truezip.fs.archive.zip.ZipDriver;
import de.schlichtherle.truezip.socket.sl.IOPoolLocator;

public class ArchiveFileHandler extends AbstractFileHandler implements FileHandler {

	public ArchiveFileHandler() {

		TFile.setDefaultArchiveDetector(new TArchiveDetector("zip", new ZipDriver(IOPoolLocator.SINGLETON)));
	}

	@Override
	public boolean exists(String folder, String name) {
		return (new TFile(getZipFileName(folder) + "/" + name)).exists();
	}

	@Override
	public long save(AccessDetail accessDetail, InputStream input, String folder, String name) throws IOException {
		OutputStream os = null;
		try {
			os = new TFileOutputStream(getZipFileName(folder) + "/" + name);
			return this.copyLarge(accessDetail, input, os);
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	private String getZipFileName(String folder) {
		if (StringUtils.endsWith(folder, "/")) {
			return StringUtils.substringBeforeLast(folder, "/") + ".zip";
		}
		return folder + ".zip";
	}
}
