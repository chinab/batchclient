package com.vicutu.batchdownload.engine.event;

import org.springframework.context.ApplicationEvent;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.domain.DownloadDetail;

public class UpdateDownloadDetailEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8742937512259485503L;

	private DownloadDetail downloadDetail;

	private AccessDetail accessDetail;

	public DownloadDetail getDownloadDetail() {
		return downloadDetail;
	}

	public AccessDetail getAccessDetail() {
		return accessDetail;
	}

	public UpdateDownloadDetailEvent(Object source, AccessDetail accessDetail, DownloadDetail downloadDetail) {
		super(source);
		this.downloadDetail = downloadDetail;
		this.accessDetail = accessDetail;
	}
}
