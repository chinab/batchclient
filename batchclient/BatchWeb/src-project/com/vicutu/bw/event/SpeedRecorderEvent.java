package com.vicutu.bw.event;

import org.springframework.context.ApplicationEvent;

import com.vicutu.bw.vo.AccessDetail;

public class SpeedRecorderEvent extends ApplicationEvent {

	private static final long serialVersionUID = -6667043077223376819L;

	private AccessDetail accessDetail;

	private long bufferLength;

	public SpeedRecorderEvent(Object source, AccessDetail accessDetail, long bufferLength) {
		super(source);
		this.accessDetail = accessDetail;
		this.bufferLength = bufferLength;
	}

	public AccessDetail getAccessDetail() {
		return accessDetail;
	}

	public void setAccessDetail(AccessDetail accessDetail) {
		this.accessDetail = accessDetail;
	}

	public long getBufferLength() {
		return bufferLength;
	}

	public void setBufferLength(long bufferLength) {
		this.bufferLength = bufferLength;
	}

}
