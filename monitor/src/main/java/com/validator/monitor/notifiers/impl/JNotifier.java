package com.validator.monitor.notifiers.impl;

import java.io.IOException;

import net.contentobjects.jnotify.JNotify;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.validator.monitor.listeners.Listener;
import com.validator.monitor.notifiers.Notifier;
import com.validator.monitor.watchers.Watcher;

@Singleton
public class JNotifier implements Notifier {

	@Inject
	Listener jNotifyListener;

	public Watcher addWatcher(String folderPath, int mask, boolean watchSubtree)
		throws IOException {
		Watcher watcher = new Watcher(folderPath, mask, watchSubtree, jNotifyListener);
		int watcherId = JNotify.addWatch(folderPath, mask, watchSubtree, jNotifyListener);
		watcher.setWatchKey(new JNotifyWatchKey(watcherId));
		return watcher;
	}

	public boolean removeWatcher(int watcherId) throws IOException {
		return JNotify.removeWatch(watcherId);
	}
}