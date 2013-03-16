package com.validator.monitor.watchers;

import net.contentobjects.jnotify.JNotifyListener;

import com.validator.common.util.StringUtil;
import com.validator.monitor.notifiers.impl.ValidatorJNotifyListener;

/**
 * Watcher object represents a file system event listener/watcher.
 * 
 * @author gaurav1935
 */
public class Watcher {
	private WatchKey watchKey;
	private String folderPath;
	private int mask;
	private boolean watchSubtree;
	private JNotifyListener listener;

	@SuppressWarnings("unused")
	private Watcher() {
	}

	public Watcher(WatchKey watchKey, String folderPath, int mask, boolean watchSubtree,
			ValidatorJNotifyListener listener) {
		this.watchKey = watchKey;
		this.folderPath = folderPath;
		this.mask = mask;
		this.watchSubtree = watchSubtree;
		this.listener = listener;
	}

	public WatchKey getWatchKey() {
		return watchKey;
	}

	public void setWatchKey(WatchKey watchKey) {
		this.watchKey = watchKey;
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

	public JNotifyListener getListener() {
		return listener;
	}

	public void setListener(JNotifyListener listener) {
		this.listener = listener;
	}

	@Override
	public String toString() {
		return StringUtil.concatenateStrings("Watcher: ", watchKey.toString());
	}
}
