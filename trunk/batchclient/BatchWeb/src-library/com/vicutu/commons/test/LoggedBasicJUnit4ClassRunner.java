package com.vicutu.commons.test;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.vicutu.commons.config.Application;

public class LoggedBasicJUnit4ClassRunner extends BlockJUnit4ClassRunner {

	static {
		Application.initLog4j();
	}
	
	public LoggedBasicJUnit4ClassRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
}
