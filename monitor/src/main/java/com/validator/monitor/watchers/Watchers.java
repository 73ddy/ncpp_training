package com.validator.monitor.watchers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.validator.monitor.notifiers.Notifier;

@Singleton
public class Watchers {
	@Inject
	Notifier notifier;

	// Poor man's cache
	private static Map<WatchKey, Watcher> registeredWatchers = new HashMap<WatchKey, Watcher>();

	/*public static Watchers getInstance() {
		return watchers;
	}*/

	public WatchKey addWatcher(String folderPath, int mask, boolean watchSubtree) throws IOException {
		Watcher watcher = notifier.addWatcher(folderPath, mask, watchSubtree);
		registeredWatchers.put(watcher.getWatchKey(), watcher);
		return watcher.getWatchKey();
	}

	public boolean removeWatcher(int watchId) throws IOException {
		boolean isRemoved = notifier.removeWatcher(watchId);
		if (isRemoved) {
			registeredWatchers.remove(watchId);
		}
		return isRemoved;
	}

	public static Collection<Watcher> getRegisteredWatchers() {
		return registeredWatchers.values();
	}

}
