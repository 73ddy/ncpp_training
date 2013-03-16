package com.validator.monitor.watchers;

import com.validator.common.util.StringUtil;

/**
 * WatchKey object is the key object for mapping the watchers.
 * 
 * @author gaurav1935
 */
public abstract class WatchKey {

	// The folder being monitored
	private String folderPath;

	/**
	 * Returns the actual watch key object. e.g. For JNotify, the actual watch
	 * key is an integer.
	 * 
	 * @return {@link Object}, watch key object
	 */
	public abstract Object getWatchKey();

	/**
	 * Sets the actual watch key.
	 * 
	 * @param {@link Object} watch key object
	 */
	public abstract void setWatchKey(Object watchKey);

	/**
	 * Get folder path.
	 * 
	 * @return {@link String}, returns the folder being monitored.
	 */
	public String getFolderPath() {
		return folderPath;
	}

	/**
	 * Sets the folder path
	 * 
	 * @param {@link String} folderPath
	 */
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	@Override
	public String toString() {
		return StringUtil.concatenateStrings("Watchkey: folder path- ", folderPath);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((folderPath == null) ? 0 : folderPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WatchKey other = (WatchKey) obj;
		if (folderPath == null) {
			if (other.folderPath != null)
				return false;
		} else if (!folderPath.equals(other.folderPath))
			return false;
		return true;
	}
}
