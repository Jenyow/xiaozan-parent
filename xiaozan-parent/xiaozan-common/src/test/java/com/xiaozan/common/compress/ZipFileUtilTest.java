package com.xiaozan.common.compress;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xiaozan.common.compress.ZipFileUtil;

/**
 * 测试ZipFileUtil的压缩和解压缩方法
 * 
 * @author Luxh
 */
public class ZipFileUtilTest {

	/*@Test
	public void testCompressList2Zip() {
		// 存放待压缩文件的目录
		String filename1 = "D:/DATA/ftpTMP/HNYD_room_20160724.csv";
		String filename2 = "D:/DATA/ftpTMP/HNYD_station_20160724.csv";
		String filename3 = "D:/DATA/ftpTMP/HNYD_switchPower_20160724.csv";
		List<String> files = new ArrayList<>();
		files.add(filename1);
		files.add(filename2);
		files.add(filename3);
		// 压缩后的zip文件路径
		String zipFilePath = "D:/DATA/ftpTMP/QHYD_res_20160724_0098362.zip";
		ZipFileUtil.compressFiles2Zip(files, zipFilePath);
	}*/
	
	@Test
	public void testCompressList2Zip() {
		// 存放待压缩文件的目录
		String filename1 = "D:/DATA/ftpTMP/123.txt";
		List<String> files = new ArrayList<>();
		files.add(filename1);
		// 压缩后的zip文件路径
		String zipFilePath = "D:/DATA/ftpTMP/123.zip";
		ZipFileUtil.compressFiles2Zip(files, zipFilePath);
	}
	
	
	@Test
	public void testDecompressZip() throws Exception {
		// 压缩包所在路径
		String zipFilePath = "D:/DATA/ftpTMP/123.zip";
		// 解压后的文件存放目录
		String saveFileDir = "D:/DATA/ftpTMP/";
		// 调用解压方法
		List<String> filenames = ZipFileUtil.decompressZip(zipFilePath, saveFileDir);
		for (String string : filenames) {
			System.out.println(string);
		}
	}
}