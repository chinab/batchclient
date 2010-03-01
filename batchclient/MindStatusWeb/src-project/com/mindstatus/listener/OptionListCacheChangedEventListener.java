package com.mindstatus.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.vicutu.cache.Cache;
import com.vicutu.event.Event;

public class OptionListCacheChangedEventListener implements ApplicationListener {

	private Cache optionListCache;

	public void setOptionListCache(Cache optionListCache) {
		this.optionListCache = optionListCache;
	}

	
	public void onApplicationEvent(ApplicationEvent e) {
		if (e instanceof Event) {
			Event event = (Event) e;
			if (event.getType().equals("option_list_changed")) {
				Integer parameter = (Integer) event.getData();
				optionListCache.update(parameter);
			}
		}

	}

}
