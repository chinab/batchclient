package com.vicutu.batchdownload.engine.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.engine.event.SpeedRecorderEvent;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class AbstractFileHandler {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	
	protected ApplicationContext applicationContext;

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	protected long copyLarge(AccessDetail accessDetail, InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
			applicationContext.publishEvent(new SpeedRecorderEvent(this, accessDetail, n));
		}
		return count;
	}
	
}
