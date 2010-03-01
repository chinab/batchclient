package com.vicutu.commons.lang;

public interface IServiceable
{
	static final int STARTING = 0;

	static final int RUNNING = 1;

	static final int STOPPING = 2;

	static final int STOPPED = 3;

	public static final String OPERATION_START = "start";

	public static final String OPERATION_STOP = "stop";

	public static final String OPERATION_REPORT = "report";

	public static final String[] STATUS_NAMES = { "STARTING", "RUNNING", "STOPPING", "STOPPED" };

	void start();

	void stop();

	String getServiceName();

	int getStatus();
}