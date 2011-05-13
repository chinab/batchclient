package com.vicutu.batchdownload.engine.io.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.engine.io.AbstractFileHandler;
import com.vicutu.batchdownload.engine.io.FileHandler;

public class ArchiveFileHandler extends AbstractFileHandler implements FileHandler {

	private ConcurrentHashMap<String, ArchiveFile> archiveFiles = new ConcurrentHashMap<String, ArchiveFile>();

	private final ReentrantLock lock = new ReentrantLock();

	private long timeBetweenEvictionRunsMillis = 10000;

	private long closeIdleTimeout = 10000;

	private IdleArchiveFileEvictor idleArchiveFileEvictor;

	public ArchiveFileHandler() {
	}

	public void init() throws Throwable {
		idleArchiveFileEvictor = new IdleArchiveFileEvictor();
		idleArchiveFileEvictor.start();
	}

	public void cleanup() throws Throwable {
		if (idleArchiveFileEvictor != null) {
			idleArchiveFileEvictor.shutdown();
			idleArchiveFileEvictor.join();
			logger.info("IdleArchiveFileEvictor has been shutdown.");
		}
	}

	public void setCloseIdleTimeout(long closeIdleTimeout) {
		this.closeIdleTimeout = closeIdleTimeout;
	}

	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	@Override
	public boolean exists(String folder, String name) {
		ArchiveFile archiveFile = archiveFiles.get(folder);
		if (archiveFile != null) {
			return archiveFile.exists(name);
		} else {
			lock.lock();
			try {
				if (!archiveFiles.containsKey(folder)) {
					archiveFile = new ArchiveFile(folder);
					archiveFiles.put(folder, archiveFile);
					return archiveFile.exists(name);
				}
			} catch (IOException e) {

			} finally {
				lock.unlock();
			}
		}
		return false;
	}

	@Override
	public long save(AccessDetail accessDetail, InputStream input, String folder, String name) throws IOException {
		ArchiveFile archiveFile = archiveFiles.get(folder);
		if (archiveFile != null) {
			return archiveFile.save(accessDetail, name, input);
		} else {
			lock.lock();
			try {
				if (!archiveFiles.containsKey(folder)) {
					archiveFile = new ArchiveFile(folder);
					archiveFiles.put(folder, archiveFile);
					return archiveFile.save(accessDetail, name, input);
				}
			} catch (IOException e) {
				throw e;
			} finally {
				lock.unlock();
			}
		}
		return 0;
	}

	public class IdleArchiveFileEvictor extends Thread {

		private volatile boolean shutdown;

		@Override
		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(timeBetweenEvictionRunsMillis);
						logger.info("IdleArchiveFileEvictor is running.");
						lock.lock();
						try {
							Set<Entry<String, ArchiveFile>> archiveFileEntries = archiveFiles.entrySet();
							Iterator<Entry<String, ArchiveFile>> archiveFileIterator = archiveFileEntries.iterator();
							while (archiveFileIterator.hasNext()) {
								Entry<String, ArchiveFile> archiveFileEntry = archiveFileIterator.next();
								ArchiveFile archiveFile = archiveFileEntry.getValue();
								if ((System.currentTimeMillis() - archiveFile.getLastModify()) > closeIdleTimeout) {
									archiveFile.close();
									archiveFileIterator.remove();
									logger.info("close ArchiveFile : {}",archiveFile.getAbsoluteFile().getName());
								}
							}
						} finally {
							lock.unlock();
						}
					}
				}
			} catch (InterruptedException ex) {
				// terminate
			}
		}

		public void shutdown() {
			shutdown = true;
			synchronized (this) {
				notifyAll();
			}
		}
	}

	public class ArchiveFile {

		private File absoluteFile;

		private Set<String> entities = new HashSet<String>();

		private ZipArchiveOutputStream out;

		private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

		private final Lock r = rwl.readLock();

		private final Lock w = rwl.writeLock();

		private AtomicLong lastModify = new AtomicLong();

		public ArchiveFile(String absoluteFileName) throws IOException {
			String absoluteFileName0 = StringUtils.endsWithIgnoreCase(absoluteFileName, ".zip") ? absoluteFileName
					: absoluteFileName + ".zip";
			absoluteFile = new File(absoluteFileName0);
			if (absoluteFile.exists()) {
				ZipArchiveInputStream is = null;
				try {
					is = new ZipArchiveInputStream(FileUtils.openInputStream(absoluteFile));
					ArchiveEntry ae = null;
					while ((ae = is.getNextEntry()) != null) {
						entities.add(ae.getName());
					}
				} finally {
					IOUtils.closeQuietly(is);
				}
			}
			out = new ZipArchiveOutputStream(FileUtils.openOutputStream(absoluteFile));
			lastModify.set(System.currentTimeMillis());
		}

		public File getAbsoluteFile() {
			return absoluteFile;
		}

		public boolean exists(String name) {
			r.lock();
			try {
				return entities.contains(name);
			} finally {
				r.unlock();
			}
		}

		public long save(AccessDetail accessDetail, String name, InputStream input) throws IOException {
			w.lock();
			try {
				ZipArchiveEntry zipEntry = new ZipArchiveEntry(name);
				out.putArchiveEntry(zipEntry);
				lastModify.set(System.currentTimeMillis());
				return copyLarge(accessDetail, input, out);
			} finally {
				if (out != null) {
					out.closeArchiveEntry();
				}
				w.unlock();
			}
		}

		public void close() {
			w.lock();
			try {
				entities.clear();
				IOUtils.closeQuietly(out);
			} finally {
				w.unlock();
			}
		}

		public long getLastModify() {
			return lastModify.get();
		}
	}
}
