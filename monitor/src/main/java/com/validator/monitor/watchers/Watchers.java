package com.validator.monitor.watchers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
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

	public void addWatcher(Watcher watcher) throws IOException {
		int watcherId = 0;
		watcherId = notifier.addWatcher(watcher);
		watcher.setWatchId(watcherId);
		registeredWatchers.put(watcherId, watcher);
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
