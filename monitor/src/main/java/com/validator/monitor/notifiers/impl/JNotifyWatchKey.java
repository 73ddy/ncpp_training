package com.validator.monitor.notifiers.impl;

import com.validator.monitor.watchers.WatchKey;

/**
 * Watcher implementation for JNotify.
 * 
 * @author gaurav1935
 */
public class JNotifyWatchKey extends WatchKey {

	private Integer watchKey;

	JNotifyWatchKey(Integer assignedWatchKey, String folderPath) {
		this.watchKey = assignedWatchKey;
		this.setFolderPath(folderPath);
	}

	@Override
	public Object getWatchKey() {
		return watchKey;
	}

	@Override
	public void setWatchKey(Object assignedWatchKey) {
		this.watchKey = (Integer) assignedWatchKey;
	}
}
