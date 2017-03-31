package com.xiaozan.common.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 验证工具类
 */
public final class ValidateUtil {

	/**
	 * 验证集合不是空集合
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 验证符合科学计数法
	 * 
	 * Double类型的值,整数部分位数大于等于8时开始以科学计数法形式显示<br>
	 * 或整数位为0,小数位开始处0连续出现大于等于3时开始以科学计数法显示
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isScientificNotation(Object o) {
		String str = o.toString();
		String regex = "[-+]?\\d(\\.\\d+)?E[-+]?\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 验证字符串是数值：正整数，负整数，正小数，负小数,科学计数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumerical(String str) {
		String regex = "[-+]?\\d(\\.\\d+)?(E[-+]?\\d+)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断文件名是否以.zip为后缀
	 * 
	 * @param filename
	 *            需要判断的文件名
	 * @return 是zip文件返回true,否则返回false
	 */
	public static boolean isZip(String filename) {
		String filenameExtension = null;
		if (StringUtils.isNotBlank(filename)) {
			filenameExtension = FileUtil.getFilenameExtension(filename);
			if (StringUtils.isNotBlank(filenameExtension)) {
				if ("zip".equalsIgnoreCase(filenameExtension)) {
					return true;
				}
			} 
		}
		return false;
	}

}