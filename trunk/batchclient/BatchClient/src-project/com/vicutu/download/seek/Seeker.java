package com.vicutu.download.seek;

import java.util.Set;
import java.util.concurrent.Callable;

import com.vicutu.download.task.TaskQueueProvider;

public interface Seeker<E> extends Callable<E>, TaskQueueProvider
{
	boolean isDone();

	Set<String> getContentTypes();
}
