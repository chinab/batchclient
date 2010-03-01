package com.vicutu.test;

import com.vicutu.commons.config.Application;
import com.vicutu.commons.lang.Debugger;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SpringBaseTestCase extends
		AbstractDependencyInjectionSpringContextTests {

	protected Debugger logger = new Debugger();

	public String[] getConfigLocations() {
		Application.bootstrap();
		String[] locations=new String[]{Application.getSystemPathUri()+"resource/ctx/**/*.xml"};
		return locations;
	}

	public Object getBean(String beanId) {
		return this.getApplicationContext().getBean(beanId);
	}
}
