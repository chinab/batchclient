package com.vicutu.bw.event;

import org.springframework.context.ApplicationEvent;

import com.vicutu.bw.vo.SearchStatus;

public class UpdateSearchStatusEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3035238538996672026L;

	private SearchStatus searchStatus;

	public UpdateSearchStatusEvent(Object source, SearchStatus searchStatus) {
		super(source);
		this.searchStatus = searchStatus;
	}

	public SearchStatus getSearchStatus() {
		return searchStatus;
	}
}
