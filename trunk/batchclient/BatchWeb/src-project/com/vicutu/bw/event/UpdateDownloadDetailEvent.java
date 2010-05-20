package com.vicutu.bw.event;

import org.springframework.context.ApplicationEvent;

import com.vicutu.bw.vo.DownloadDetail;

public class UpdateDownloadDetailEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8742937512259485503L;

	private DownloadDetail downloadDetail;

	public DownloadDetail getDownloadDetail() {
		return downloadDetail;
	}

	public UpdateDownloadDetailEvent(Object source, DownloadDetail downloadDetail) {
		super(source);
		this.downloadDetail = downloadDetail;
	}
}
