package com.validator.monitor.notifiers.impl;

import com.validator.monitor.watchers.WatchKey;

public class JNotifyWatchKey extends WatchKey {

	Integer watchKey;

	JNotifyWatchKey(Integer assignedWatchKey) {
		this.watchKey = assignedWatchKey;
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
