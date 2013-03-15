package com.validator.monitor.notifiers.impl;

import net.contentobjects.jnotify.JNotify;

import com.validator.monitor.notifiers.FileOperationMask;

public class JNotifierFileOperationMask implements FileOperationMask {

	public int getFileCreatedMask() {
		return JNotify.FILE_CREATED;
	}

	public int getFileDeletedMask() {
		return JNotify.FILE_DELETED;
	}

	public int getFileModifiedMask() {
		return JNotify.FILE_MODIFIED;
	}

	public int getFileRenamedMask() {
		return JNotify.FILE_RENAMED;
	}

	public int getCompoundMask(int... fileOperationMask) {
		int resultantMask = 0;
		if (fileOperationMask.length == 0) {
			throw new IllegalArgumentException();
		}

		// Jnotifier uses a bitwise OR of all the masks usedto create a
		// compounded mask
		for (int mask : fileOperationMask) {
			resultantMask = resultantMask | mask;
		}
		return resultantMask;
	}

}
