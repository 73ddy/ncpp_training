package com.validator.monitor.notifiers;

import java.io.IOException;

import com.validator.monitor.watchers.WatchKey;
import com.validator.monitor.watchers.Watcher;

/**
 * The notifier contract each notifier implementation used must adhere to.
 * 
 * @author gaurav1935
 */
public interface Notifier {
	/**
	 * Adds/registers a {@link Watcher}.
	 * 
	 * @param <code>String</code>, folderPath
	 * @param <b>int</b>, mask
	 * @param <b>boolean</b>, watchSubtree
	 * @return {@link Watcher}, the watcher object created and registered.
	 * @throws IOException
	 */
	public Watcher addWatcher(String folderPath, int mask, boolean watchSubtree) throws IOException;

	/**
	 * Remves/unregisters a {@link Watcher}.
	 * 
	 * @param {@link WatchKey}, watchKey
	 * @return <code>boolean</code>, true if the watcher was successfully
	 *         removed.
	 * @throws IOException
	 */
	public boolean removeWatcher(WatchKey watchKey) throws IOException;
}
