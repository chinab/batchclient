package com.vicutu.commons.test;

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class LoggedJUnit4BasicTests {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
}
