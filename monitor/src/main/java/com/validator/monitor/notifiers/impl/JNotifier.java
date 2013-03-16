package com.validator.monitor.notifiers.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import net.contentobjects.jnotify.JNotify;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.validator.common.util.StringUtil;
import com.validator.monitor.notifiers.Notifier;
import com.validator.monitor.watchers.WatchKey;
import com.validator.monitor.watchers.Watcher;

/**
 * The JNotifier implementation of the General Notifier Contract.
 * 
 * @author gaurav1935
 */
@Singleton
public class JNotifier implements Notifier {

	private static Logger LOG = Logger.getLogger(JNotifier.class);
	
	@Inject
	private ValidatorJNotifyListener jNotifyListener;

	@Override
	public Watcher addWatcher(String folderPath, int mask, boolean watchSubtree) throws IOException {
		int watcherId = JNotify.addWatch(folderPath, mask, watchSubtree, jNotifyListener);
		WatchKey watchKey = new JNotifyWatchKey(watcherId);
		Watcher watcher = new Watcher(watchKey, folderPath, mask, watchSubtree, jNotifyListener);
		watcher.setWatchKey(watchKey);
		LOG.info(StringUtil.concatenateStrings("A watcher was succeddfully registered. ", watcher.toString()));
		return watcher;
	}

	@Override
	public boolean removeWatcher(WatchKey watchKey) throws IOException {
		boolean isWatcherRemoved = JNotify.removeWatch((Integer) ((JNotifyWatchKey) watchKey).getWatchKey());
		if (isWatcherRemoved) {
			LOG.info(StringUtil.concatenateStrings("Watcher: ", watchKey.toString(), " was successfully unregistered."));
		} else {
			LOG.error(StringUtil.concatenateStrings("Failed to unregister watcher: ", watchKey.toString()));
		}
		return isWatcherRemoved;
	}
}