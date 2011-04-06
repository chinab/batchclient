package com.vicutu.commons.test;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vicutu.commons.config.Application;

public class LoggedSpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

	static {
		Application.initLog4j();
	}

	public LoggedSpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}
}
