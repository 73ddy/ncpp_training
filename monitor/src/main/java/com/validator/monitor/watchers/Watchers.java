package com.validator.monitor.watchers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.validator.monitor.listeners.Listener;
import com.validator.monitor.notifiers.Notifier;

@Singleton
public class Watchers {
	@Inject
	Notifier notifier;

	// Poor man's cache
	private static Map<Integer, Watcher> registeredWatchers = new HashMap<Integer, Watcher>();

	/*public static Watchers getInstance() {
		return watchers;
	}*/

	public int addWatcher(String folderPath, int mask, boolean watchSubtree) throws IOException {
		Watcher watcher = notifier.addWatcher(folderPath, mask, watchSubtree);
		registeredWatchers.put(watcher.getWatchId(), watcher);
		return watcher.getWatchId();
	}

	public boolean removeWatcher(int watchId) throws IOException {
		boolean isRemoved = notifier.removeWatcher(watchId);
		if (isRemoved) {
			registeredWatchers.remove(watchId);
		}
		return isRemoved;
	}

	public static List<Watcher> getRegisteredWatchers() {
		return (List<Watcher>) registeredWatchers.values();
	}

}
