package com.validator.monitor.rawentitystore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.validator.common.util.StringUtil;

/**
 * Acts as the store for the raw entities.
 * 
 * @author gaurav1935
 */
public class RawEntityStore {
	private static Logger LOG = Logger.getLogger(RawEntityStore.class);
	private static final BlockingQueue<File> queue = new ArrayBlockingQueue<File>(10);
	private static RawEntityStore instance = new RawEntityStore();
	
	public static RawEntityStore getInstance() {
		return instance;
	}

	
	/**
	 * Stores the file to the raw entity store for processing later.
	 * 
	 * @param {@link File}, entity to be stored.
	 * @throws InterruptedException
	 */
	public void putRawFile(File file) throws InterruptedException {
		queue.put(file);
		LOG.info(StringUtil.concatenateStrings(
				"A new entity was successfully added to Raw Entity Store. Total size of store now is - ", new Integer(
						queue.size()).toString()));
		// LOG.info("Failed to put a new entity to the Raw Entity Store.", e);
	}

	/**
	 * Returns a list of all entities present in the queue.
	 * 
	 * @return {@link File}, object at the head of the queue.
	 */
	public List<File> getRawFiles() {
		File file = null;
		List<File> rawFiles = new ArrayList<File>();

		while (null != (file = queue.poll())) {
			rawFiles.add(file);
		}

		return rawFiles;
	}
}