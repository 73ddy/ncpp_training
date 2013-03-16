package com.validator.common.constants;

/**
 * All the property keys defined in the properties file.
 * 
 * @author gaurav1935
 */
public enum PropertyKeys {
	RECOGNIZED_FILE_EXTENSIONS("recognized.file.extensions"),
	DEFAULT_OBJECT_POOL_SIZE("default.object.pool.size"),
	VALIDATOR_FILE("validator.file"),
	FOLDERS_TO_BE_WATCHED("target.folders"),
	WATCH_SUBTREE("watch.subtree"),
	EXECUTOR_CORE_POOL_SIZE("executor.core.pool.size"),
	EXECUTOR_MAX_POOL_SIZE("executor.max.pool.size"),
	EXECUTOR_KEEP_ALIVE_TIME("executor.keep.alive.time"),
	EXECUTOR_PROCESSOR_QUEUE_SIZE("executor.processor.queue.size");

	String name;

	PropertyKeys(String name) {
		this.name = name;
	}

	public String getKeyName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
