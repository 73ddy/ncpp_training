package com.validator.executor.threadexecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.validator.common.util.StringUtil;

/**
 * The custom {@link RejectedExecutionHandler} to handle the rejected tasks /
 * {@link Runnable}
 */
public class FailedProcessorHandler implements RejectedExecutionHandler {

	private static final Logger LOG = Logger.getLogger(FailedProcessorHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.
	 * lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
	 */
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		LOG.error(StringUtil.concatenateStrings("Failed to process - " + runnable.toString()));
	}

}
