package com.vicutu.commons.test;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.commons.lang.Debugger;

public abstract class LoggedJUnit4SpringContextTests extends AbstractJUnit4SpringContextTests {
	protected Debugger debugger = new Debugger();
}
