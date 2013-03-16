package com.validator.executor.threadexecutor;

import java.io.File;
import java.util.List;

import com.google.inject.Inject;
import com.validator.executor.processors.Processor;
import com.validator.executor.processors.impl.EmployeeProcessor;
import com.validator.monitor.rawentitystore.RawEntityStore;

/**
 * The class is responsible for consuming the raw entities and feed them to the
 * correct processor.
 * 
 * @author gaurav1935
 */
public class RawEntityConsumer implements Runnable {

	@Inject
	RawEntityStore rawEntityStore;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		List<File> rawFiles = null;
		Processor processor = null;
		while (true) {
			if (null != (rawFiles = rawEntityStore.getRawFiles())) {
				for (File file : rawFiles) {
					processor = new EmployeeProcessor(file);
					PoolExecutor.addProcessor(processor);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
