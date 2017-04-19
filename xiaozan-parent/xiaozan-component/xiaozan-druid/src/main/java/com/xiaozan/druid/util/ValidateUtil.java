package com.xiaozan.druid.util;

public class ValidateUtil {

	/**
	 * 如果 value是null,则返回defaultValue,否则返回value
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String getValueOrDefaultValue(String value, String defaultValue) {
		return value == null ? defaultValue : value;
	}
	
}
