package com.xiaozan.common.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作类
 */
public final class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 获取文件扩展名
	 * 
	 * 如果没有扩展名则return null
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFilenameExtension(String filename) {
		String filenameExtension = null;
		int lastDotIndex = filename.lastIndexOf(".");
		if (lastDotIndex == -1) {
			logger.error("该文件[{}]没有扩展名", filename);
		} else {
			filenameExtension = filename.substring(lastDotIndex + 1);
		}
		return filenameExtension;
	}

	/**
	 * 如果文件存在则删除
	 * @param file
	 */
	public static void deleteExistsFile(File file) {
		if (file.exists()) {
			file.delete();
		}
	}
}
