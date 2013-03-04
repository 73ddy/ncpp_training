package com.validator.monitor.watchers;

import com.validator.monitor.listeners.Listener;

public class Watcher {
	private int watchId;
	private String folderPath;
	private int mask;
	private boolean watchSubtree;
	private Listener listener;

	public Watcher(String folderPath, int mask, boolean watchSubtree,
			Listener listener) {
		this.folderPath = folderPath;
		this.mask = mask;
		this.watchSubtree = watchSubtree;
		this.listener = listener;
	}

	public int getWatchId() {
		return watchId;
	}

	public void setWatchId(int watchId) {
		this.watchId = watchId;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public int getMask() {
		return mask;
	}

	public void setMask(int mask) {
		this.mask = mask;
	}

	public boolean getWatchSubtree() {
		return watchSubtree;
	}

	public void setWatchSubtree(boolean watchSubtree) {
		this.watchSubtree = watchSubtree;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
