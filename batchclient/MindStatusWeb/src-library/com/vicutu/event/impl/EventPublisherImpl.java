package com.vicutu.event.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.vicutu.event.Event;
import com.vicutu.event.EventPublisher;

public class EventPublisherImpl implements EventPublisher {

	private ApplicationContext ctx;
	
	public void publish(Event event) {
		ctx.publishEvent(event);
	}

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx=ctx;
	}
}
