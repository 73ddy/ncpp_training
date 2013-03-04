package com.validator.monitor.notifiers;

import java.io.IOException;

import com.validator.monitor.watchers.Watcher;

public interface Notifier {
	public int addWatcher(Watcher watcher) throws IOException ;
	public boolean removeWatcher(int watcherId) throws IOException ;
}
