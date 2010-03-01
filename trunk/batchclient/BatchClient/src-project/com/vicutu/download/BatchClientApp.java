package com.vicutu.download;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vicutu.commons.config.Application;
import com.vicutu.download.engine.DownloadManager;

public class BatchClientApp {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable {
		Application.initialize();

		if (args != null && args.length > 0) {
			Application.initLog4j();
			String functionName = args[0];
			ApplicationContext applicationContext = new FileSystemXmlApplicationContext(Application.getSystemPath()
					+ "resource/ctx/" + functionName + "ApplicationContext.xml");
			Application.bootstrap(applicationContext, false, false, false);
		} else {
			Application.bootstrap(true, false, false);
		}

		DownloadManager downloadManager = (DownloadManager) Application.getBean(DownloadManager.class);
		downloadManager.startDownload();
	}
}