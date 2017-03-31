package com.xiaozan.common.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipFileUtil {

	private static final Logger logger = LoggerFactory.getLogger(ZipFileUtil.class);

	/**
	 * 把文件压缩成zip格式
	 * 
	 * @param files
	 *            需要压缩的文件
	 * @param zipFilePath
	 *            压缩后的zip文件路径 ,如"D:/test/aa.zip";
	 */
	public static void compressFiles2Zip(List<String> files, String zipFilePath) {
		if (files != null && files.size() > 0) {
			if (isEndsWithZip(zipFilePath)) {
				ZipArchiveOutputStream zaos = null;
				try {
					File zipFile = new File(zipFilePath);
					zaos = new ZipArchiveOutputStream(zipFile);
					// Use Zip64 extensions for all entries where they are
					// required
					zaos.setUseZip64(Zip64Mode.AsNeeded);
					// 将每个文件用ZipArchiveEntry封装
					// 再用ZipArchiveOutputStream写到压缩文件中
					for (String filename : files) {
						File file = new File(filename);
						if (file.exists()) {
							ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
							zaos.putArchiveEntry(zipArchiveEntry);
							InputStream is = null;
							try {
								is = new FileInputStream(file);
								byte[] buffer = new byte[1024 * 5];
								int len = -1;
								while ((len = is.read(buffer)) != -1) {
									// 把缓冲区的字节写入到ZipArchiveEntry
									zaos.write(buffer, 0, len);
								}
								// Writes all necessary data for this entry.
								zaos.closeArchiveEntry();
							} catch (Exception e) {
								logger.error("流读写异常:" + e.getMessage());
							} finally {
								if (is != null) {
									is.close();
								}
							}
						} else {
							logger.error("文件不存在:" + filename);
						}
					}
					zaos.finish();
				} catch (Exception e) {
					logger.error("压缩过程异常:" + e.getMessage());
				} finally {
					try {
						if (zaos != null) {
							zaos.close();
						}
					} catch (IOException e) {
						logger.error("流关闭异常:" + e.getMessage());
					}
				}
			} else {
				logger.error("指定压缩后的文件不是zip");
			}
		} else {
			logger.error("未指定需要压缩的文件列表");
		}

	}

	/**
	 * 把zip文件解压到指定的文件夹
	 * 
	 * @param zipFilePath
	 *            zip文件路径, 如 "D:/test/aa.zip"
	 * @param outputdir
	 *            解压后的文件存放路径, 如"D:/test/"
	 */
	public static List<String> decompressZip(String zipFilePath, String outputdir) throws Exception {
		List<String> filenames = new ArrayList<>();
		if (isEndsWithZip(zipFilePath)) {
			File zipfile = new File(zipFilePath);
			ZipFile zf = null;
			String filename = null;
			InputStream is = null;
			FileOutputStream fos = null;
			if (zipfile.exists()) {
				try {
					zf = new ZipFile(zipfile, "UTF-8");
					Enumeration<ZipArchiveEntry> zipArchiveEntrys = zf.getEntries();
					while (zipArchiveEntrys.hasMoreElements()) {
						try {
							ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) zipArchiveEntrys.nextElement();
							filename = zipArchiveEntry.getName();
							is = zf.getInputStream(zipArchiveEntry);
							fos = FileUtils.openOutputStream(new File(outputdir + filename));
							IOUtils.copy(is, fos);
							filenames.add(filename);
						} catch (IOException e) {
							logger.error("读写异常:" + e.getMessage());
						} finally {
							try {
								if (is != null) {
									is.close();
								}
								if (fos != null) {
									fos.close();
								}
							} catch (IOException e) {
								logger.error("流关闭异常:" + e.getMessage());
							}
						}
					}
				} catch (Exception e) {
					logger.error("文件关闭异常:" + e.getMessage());
				} finally {
					if (zf != null) {
						zf.close();
					}
				}
			} else {
				logger.error("文件不存在:" + zipFilePath);
			}
		} else {
			logger.error("指定解压的文件非zip类型");
		}
		return filenames;
	}

	/**
	 * 判断文件名是否以.zip为后缀
	 * 
	 * @param fileName
	 *            需要判断的文件名
	 * @return 是zip文件返回true,否则返回false
	 */
	public static boolean isEndsWithZip(String fileName) {
		boolean flag = false;
		if (fileName != null && !"".equals(fileName.trim())) {
			if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
				flag = true;
			}
		}
		return flag;
	}

}
