package com.validator.executor.threadexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.inject.Singleton;
import com.validator.common.constants.PropertyKeys;
import com.validator.common.util.ValidatorProperties;
import com.validator.executor.processors.Processor;

/**
 * The Pool Executor is responsible for processing/validating entities.
 * 
 * @author gaurav1935
 */
@Singleton
public class PoolExecutor {
	private static final ValidatorProperties validatorProperties = ValidatorProperties.getInstance();

	private static final int corePoolSize = validatorProperties.getIntProperty(
			PropertyKeys.EXECUTOR_CORE_POOL_SIZE.toString(), 10);
	private static final int maxPoolSize = validatorProperties.getIntProperty(
			PropertyKeys.EXECUTOR_MAX_POOL_SIZE.toString(), 15);
	private static final int keepAliveTime = validatorProperties.getIntProperty(
			PropertyKeys.EXECUTOR_KEEP_ALIVE_TIME.toString(), 10);

	private static final BlockingQueue<Runnable> processorQueue = new ArrayBlockingQueue<Runnable>(maxPoolSize);
	private static final RejectedExecutionHandler executionHandler = new FailedProcessorHandler();

	// Create the ThreadPoolExecutor
	private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
			TimeUnit.SECONDS, processorQueue, executionHandler);
	
	PoolExecutor() {
		executor.allowCoreThreadTimeOut(true);
	}

	/**
	 * @param {@link }processor
	 */
	public static void addProcessor(Processor processor){
		executor.execute(processor);
	}	
}
