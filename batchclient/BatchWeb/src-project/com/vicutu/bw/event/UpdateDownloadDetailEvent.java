package com.vicutu.bw.event;

import org.springframework.context.ApplicationEvent;

import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;

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
