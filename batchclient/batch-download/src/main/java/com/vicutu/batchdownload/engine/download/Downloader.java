package com.vicutu.batchdownload.engine.download;

import com.vicutu.batchdownload.engine.DownloadItem;

public interface Downloader {
	void download(DownloadItem downloadItem);
}
