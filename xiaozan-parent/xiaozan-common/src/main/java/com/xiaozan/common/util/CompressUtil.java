package com.xiaozan.common.util;

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

/**
 * 压缩解压工具类
 */
public class CompressUtil {

	private static final Logger logger = LoggerFactory.getLogger(CompressUtil.class);

	/**
	 * 把文件压缩成zip格式
	 * 
	 * @param files
	 *            需要压缩的文件
	 * @param filePath
	 *            压缩后的zip文件路径 ,如"D:/test/aa.zip";
	 */
	public static void compressFiles2Zip(List<String> files, String filePath) {
		byte[] buffer = null;
		int len = -1;
		File zipFile = null;
		File file = null;
		ZipArchiveOutputStream os = null;
		ZipArchiveEntry zipArchiveEntry = null;
		InputStream is = null;
		if (ValidateUtil.isNotEmpty(files)) {
			if (ValidateUtil.isZip(filePath)) {
				try {
					zipFile = new File(filePath);
					os = new ZipArchiveOutputStream(zipFile);
					os.setUseZip64(Zip64Mode.AsNeeded);
					// 将每个文件用ZipArchiveEntry封装
					// 再用ZipArchiveOutputStream写到压缩文件中
					for (String filename : files) {
						file = new File(filename);
						if (file.exists()) {
							try {
								buffer = new byte[1024 * 5];
								len = -1;
								is = new FileInputStream(file);
								zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
								os.putArchiveEntry(zipArchiveEntry);
								while ((len = is.read(buffer)) != -1) {
									// 把缓冲区的字节写入到ZipArchiveEntry
									os.write(buffer, 0, len);
								}
								// Writes all necessary data for this entry.
								os.closeArchiveEntry();
							} catch (Exception e) {
								logger.error("流读写异常:" + e.toString());
							} finally {
								if (is != null) {
									is.close();
								}
							}
						} else {
							logger.error("文件不存在:" + filename);
							FileUtil.deleteExistsFile(zipFile);
						}
					}
					os.finish();
				} catch (Exception e) {
					logger.error("压缩过程异常:" + e.toString());
					FileUtil.deleteExistsFile(zipFile);
				} finally {
					try {
						if (os != null) {
							os.close();
						}
					} catch (IOException e) {
						logger.error("流关闭异常:" + e.toString());
						FileUtil.deleteExistsFile(zipFile);
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
	 * 
	 * 
	 * @param filePath
	 * 
	 * @param outputdir
	 * 
	 */
	/**
	 * 把zip文件解压到指定的路径
	 * 
	 * @param filePath
	 *            zip文件路径,如 "D:/test/aa.zip"
	 * @param encoding
	 *            zip的编码,如"UTF-8"
	 * @param outputdir
	 *            解压后的文件存放路径, 如"D:/test/"
	 * @return
	 * @throws Exception
	 */
	public static List<String> decompressZip(String filePath, String encoding, String outputdir) throws Exception {
		File zipfile = null;
		ZipFile zf = null;
		String filename = null;
		InputStream is = null;
		FileOutputStream fos = null;
		Enumeration<ZipArchiveEntry> zipArchiveEntrys = null;
		ZipArchiveEntry zipArchiveEntry = null;
		List<String> filenames = new ArrayList<>();
		if (ValidateUtil.isZip(filePath)) {
			zipfile = new File(filePath);
			if (zipfile.exists()) {
				try {
					zf = new ZipFile(zipfile, encoding);
					zipArchiveEntrys = zf.getEntries();
					while (zipArchiveEntrys.hasMoreElements()) {
						try {
							zipArchiveEntry = zipArchiveEntrys.nextElement();
							filename = zipArchiveEntry.getName();
							is = zf.getInputStream(zipArchiveEntry);
							fos = FileUtils.openOutputStream(new File(outputdir + filename));
							IOUtils.copy(is, fos);
							filenames.add(filename);
						} catch (IOException e) {
							logger.error("读写异常:" + e.toString());
						} finally {
							try {
								if (is != null) {
									is.close();
								}
								if (fos != null) {
									fos.close();
								}
							} catch (IOException e) {
								logger.error("流关闭异常:" + e.toString());
							}
						}
					}
				} catch (Exception e) {
					logger.error("解压过程异常:" + e.toString());
				} finally {
					if (zf != null) {
						zf.close();
					}
				}
			} else {
				logger.error("文件不存在:" + filePath);
			}
		} else {
			logger.error("指定解压的文件非zip类型");
		}
		return filenames;
	}

}
