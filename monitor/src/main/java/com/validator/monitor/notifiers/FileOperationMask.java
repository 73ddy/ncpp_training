package com.validator.monitor.notifiers;

public interface FileOperationMask {
	public int getFileCreatedMask();

	public int getFileDeletedMask();

	public int getFileModifiedMask();

	public int getFileRenamedMask();

	public int getCompoundMask(int... fileOperationMask);
}
