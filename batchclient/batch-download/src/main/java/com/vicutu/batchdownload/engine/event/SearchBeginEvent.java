package com.vicutu.batchdownload.engine.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class SearchBeginEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8986493287564765931L;

	private String accessDetailName;

	private Date beginTime;

	public String getAccessDetailName() {
		return accessDetailName;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public SearchBeginEvent(Object source, String accessDetailName, Date beginTime) {
		super(source);
		this.accessDetailName = accessDetailName;
		this.beginTime = beginTime;
	}
}
