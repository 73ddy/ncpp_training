package com.validator.monitor.notifiers;

import java.io.IOException;

import com.validator.monitor.watchers.Watcher;

public interface Notifier {
	public Watcher addWatcher(String folderPath, int mask, boolean watchSubtree)
			throws IOException;

	public boolean removeWatcher(int watcherId) throws IOException;
}
