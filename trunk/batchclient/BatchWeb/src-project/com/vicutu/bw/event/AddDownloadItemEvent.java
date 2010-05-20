package com.vicutu.bw.event;

import org.springframework.context.ApplicationEvent;

import com.vicutu.bw.engine.DownloadItem;

public class AddDownloadItemEvent extends ApplicationEvent {

	/**
	 * 
	 */

	private static final long serialVersionUID = -4245799474430852688L;

	private DownloadItem downloadItem;

	public AddDownloadItemEvent(Object source, DownloadItem downloadItem) {
		super(source);
		this.downloadItem = downloadItem;
	}

	public DownloadItem getDownloadItem() {
		return downloadItem;
	}
}
