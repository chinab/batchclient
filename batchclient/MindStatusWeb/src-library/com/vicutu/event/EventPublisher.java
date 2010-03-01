package com.vicutu.event;

import org.springframework.context.ApplicationContextAware;

public interface EventPublisher extends ApplicationContextAware {
	
	void publish(Event event);

}
