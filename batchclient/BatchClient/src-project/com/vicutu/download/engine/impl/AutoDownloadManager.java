package com.vicutu.download.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.download.descriptor.AuthenticationDescriptor;
import com.vicutu.download.engine.DownloadManager;
import com.vicutu.download.http.HttpClientProvider;
import com.vicutu.download.seek.Seeker;

public class AutoDownloadManager implements DownloadManager {
	protected static Logger logger = LoggerFactory.getLogger(AutoDownloadManager.class);

	private AuthenticationDescriptor authenticationDescriptor;

	private int threadCount = 1;

	private ExecutorService executorService;

	private HttpClientProvider httpClientProvider;

	private Seeker<Integer> seeker;

	private int timeout;

	private int interval = -1;

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void initialize() {
		executorService = Executors.newFixedThreadPool(threadCount + 1);
		logger.info("init thread pool with size : {}", Integer.valueOf(threadCount + 1));
	}

	public void startDownload() {
		if (executorService != null) {
			try {
				Future<Integer> seekerFutrue = executorService.submit(seeker);
				Thread.sleep(3000);
				List<AutoDownloader> autoDownloaders = new ArrayList<AutoDownloader>(threadCount);
				for (int i = 0; i < threadCount; i++) {
					AutoDownloader autoDownloader = new AutoDownloader(httpClientProvider.getHttpClient(),
							authenticationDescriptor, seeker, timeout, interval);
					autoDownloaders.add(autoDownloader);
				}

				List<Future<Integer>> futures = executorService.invokeAll(autoDownloaders);
				Integer seekCount = seekerFutrue.get();
				logger.info("seeker complete count : {}", Integer.valueOf(seekCount));
				int downloadCompleteCount = 0;
				for (int i = 0; i < futures.size(); i++) {
					Future<Integer> future = futures.get(i);
					Integer downloadCount = future.get();
					downloadCompleteCount = downloadCompleteCount + downloadCount.intValue();
					logger.info("downloader {} complete count {}", autoDownloaders.get(i).getName(), downloadCount);
				}
				logger.info("downloader complete count : {}", Integer.valueOf(downloadCompleteCount));
				executorService.shutdown();
			} catch (InterruptedException e) {
				logger.error("", e);
			} catch (ExecutionException e) {
				logger.error("", e);
			}
		}
	}

	public void setAuthenticationDescriptor(AuthenticationDescriptor authenticationDescriptor) {
		this.authenticationDescriptor = authenticationDescriptor;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public void setHttpClientProvider(HttpClientProvider httpClientProvider) {
		this.httpClientProvider = httpClientProvider;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setSeeker(Seeker<Integer> seeker) {
		this.seeker = seeker;
	}
}