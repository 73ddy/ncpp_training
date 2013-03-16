package com.validator.monitor.watchers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.validator.common.util.StringUtil;
import com.validator.monitor.notifiers.Notifier;

/**
 * The class is responsible for management of the watchers. It interacts with
 * the underlying Notifier object.
 * 
 * @author gaurav1935
 */
@Singleton
public class Watchers {

	private static Logger LOG = Logger.getLogger(Watchers.class);

	@Inject
	Notifier notifier;

	/**
	 * Poor man's cache, maps each {@link Watcher} with it watch key. The
	 * {@link WatchKey} is generated when the {@link Watcher} is registered.
	 */
	private static Map<WatchKey, Watcher> registeredWatchers = new HashMap<WatchKey, Watcher>();

	/**
	 * Adds/Registers the watcher.
	 * 
	 * @param {@link String}, folderPath- The path of the folder to be monitored
	 * @param <code>int</code>, mask- the mask responsible for determining which
	 *        activities are to be monitored. e.g. file creation, file
	 *        modification, file renaming and file deletion.
	 * @param <code>boolean</code>, watchSubtree if true, the sub-folders of the
	 *        target folders are also monitored.
	 * @return {@link WatchKey}, the watch key object for the registered watcher
	 *         {@link WatchKey}
	 * @throws IOException
	 */
	public WatchKey addWatcher(String folderPath, int mask, boolean watchSubtree) throws IOException {
		Watcher watcher = notifier.addWatcher(folderPath, mask, watchSubtree);
		Watcher previousWatcher = registeredWatchers.put(watcher.getWatchKey(), watcher);
		if (previousWatcher != null) {
			LOG.warn(StringUtil.concatenateStrings(
					"Failed to register a watcher. A watcher is already watching folder path: ", folderPath));
		}
		return watcher.getWatchKey();
	}

	/**
	 * Removes/unregisters a watcher.
	 * 
	 * @param {@link WatchKey}, watchKey for the {@link Watcher} to be
	 *        removed/unregistered.
	 * @return <code>boolean</code>, true if the {@link Watcher} was
	 *         successfully removed/unregistered. false is not.
	 * @throws IOException
	 */
	public boolean removeWatcher(WatchKey watchKey) throws IOException {
		boolean isRemoved = notifier.removeWatcher(watchKey);
		if (isRemoved) {
			registeredWatchers.remove(watchKey);
		}
		return isRemoved;
	}

	/**
	 * Returns the list of all the registered watchers.
	 * 
	 * @return {@link Collection} of all the watchers in the form of map values.
	 */
	public static Collection<Watcher> getRegisteredWatchers() {
		return registeredWatchers.values();
	}

	/**
	 * Unregisters all the watchers.
	 */
	public void clearAllWatchers() {
		if (registeredWatchers.size() > 0) {
			boolean isRemoved = false;
			LOG.info("About to unregister all watchers.");
			for (WatchKey watchKey : registeredWatchers.keySet()) {
				try {
					isRemoved = notifier.removeWatcher(watchKey);
					if (isRemoved) {
						registeredWatchers.remove(watchKey);
					}
				} catch (IOException e) {
					// Let other watchers be cleared off
				}
			}
			if (registeredWatchers.size() > 0) {
				LOG.info("Failed to clear all the watchers. Some watchers couldn't be unregistered.");
			}
		} else {
			LOG.warn("Failed to clear all the watchers as no watchers registered.");
		}

	}

}
