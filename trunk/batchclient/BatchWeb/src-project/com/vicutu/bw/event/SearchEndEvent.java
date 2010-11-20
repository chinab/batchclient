package com.vicutu.bw.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class SearchEndEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3293881351342056525L;

	private String accessDetailName;

	private Date endTime;

	public String getAccessDetailName() {
		return accessDetailName;
	}

	public Date getEndTime() {
		return endTime;
	}

	public SearchEndEvent(Object source, String accessDetailName, Date endTime) {
		super(source);
		this.accessDetailName = accessDetailName;
		this.endTime = endTime;
	}
}
