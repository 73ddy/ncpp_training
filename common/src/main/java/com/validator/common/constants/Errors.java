package com.validator.common.constants;

import com.validator.common.util.StringUtil;

/**
 * List of all the errors occurring in the project.
 * 
 * @author gaurav1935
 */
public enum Errors {
	RECOGNIZED_EXTENSIONS_NOT_DEFINED(StringUtil.concatenateStrings("The property ",
			PropertyKeys.RECOGNIZED_FILE_EXTENSIONS.toString(), " is not set.")), FILE_NOT_FOUND(StringUtil
			.concatenateStrings("Failed to find a file in the specified location.")), FAILED_TO_UNMARSHAL_ENTITY(
			"Failed to unmarshal entity.");

	String value;

	Errors(String name) {
		this.value = name;
	}

	public String getKeyName() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
