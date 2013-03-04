package com.validator.monitor.notifiers;

import java.io.IOException;

import net.contentobjects.jnotify.JNotify;

import com.google.inject.Singleton;
import com.validator.monitor.watchers.Watcher;

@Singleton
public class JNotifier implements Notifier {

	public int addWatcher(Watcher watcher) throws IOException {
		return JNotify.addWatch(watcher.getFolderPath(), watcher.getMask(),
				watcher.getWatchSubtree(), watcher.getListener());
	}

	public boolean removeWatcher(int watcherId) throws IOException {
		return JNotify.removeWatch(watcherId);
	}
}
