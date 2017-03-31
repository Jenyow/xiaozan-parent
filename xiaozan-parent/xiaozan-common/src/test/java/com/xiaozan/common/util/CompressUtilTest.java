package com.xiaozan.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.xiaozan.common.util.CompressUtil;
import com.xiaozan.common.util.FileUtil;
import com.xiaozan.common.util.ValidateUtil;

@RunWith(PowerMockRunner.class)
public class CompressUtilTest {
	
	private String filePath = null;

	@Before
	public void setUp() throws Exception {
		// 获取该测试类编译文件的路径
		// /xiaozan-common/target/test-classes/org/xiaozan/common/util/
		filePath = this.getClass().getResource("").getPath();
	}

	@SuppressWarnings("unchecked")
	@Test
	@PrepareForTest({ValidateUtil.class})
	public void testCompressFiles2ZipFilesIsEmpty() {
		PowerMockito.mockStatic(ValidateUtil.class);
		PowerMockito.when(ValidateUtil.isNotEmpty(Mockito.anyCollection())).thenReturn(false);
		File file = null;
		String zipFile = filePath + "test.zip";
		CompressUtil.compressFiles2Zip(Mockito.anyList(), zipFile);
		file = new File(zipFile);
		assertFalse(file.exists());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@PrepareForTest({ValidateUtil.class})
	public void testCompressFiles2ZipFilesIsNotZip() {
		PowerMockito.mockStatic(ValidateUtil.class);
		PowerMockito.when(ValidateUtil.isNotEmpty(Mockito.anyCollection())).thenReturn(true);
		PowerMockito.when(ValidateUtil.isZip(Mockito.anyString())).thenReturn(false);
		File file = null;
		String zipFile = filePath + "test.zip";
		CompressUtil.compressFiles2Zip(Mockito.anyList(), zipFile);
		file = new File(zipFile);
		assertFalse(file.exists());
	}
	
	@Test
	@PrepareForTest({CompressUtil.class, ValidateUtil.class})
	public void testCompressFiles2ZipFilesFileNotExists() throws Exception {
		String aFilePath = filePath + "1.a";
		String zipFileStr = filePath + "test.zip";
		List<String> files = new ArrayList<>();
		files.add(aFilePath);
		File mZFile = PowerMockito.spy(new File(zipFileStr));
		File mAFile = PowerMockito.spy(new File(aFilePath));
		PowerMockito.mockStatic(ValidateUtil.class);
		PowerMockito.when(ValidateUtil.isNotEmpty(Mockito.anyCollection())).thenReturn(true);
		PowerMockito.when(ValidateUtil.isZip(Mockito.anyString())).thenReturn(true);
		PowerMockito.whenNew(File.class).withArguments(zipFileStr).thenReturn(mZFile); 
		PowerMockito.whenNew(File.class).withArguments(aFilePath).thenReturn(mAFile); 
		PowerMockito.when(mAFile.exists()).thenReturn(false);
		CompressUtil.compressFiles2Zip(files, zipFileStr);
		File zipFile = new File(zipFileStr);
		assertFalse(zipFile.exists());
	}
	
	
	@Test
	@PrepareForTest({ValidateUtil.class, CompressUtil.class, InputStream.class})
	public void testCompressFiles2ZipFilesWriteException() throws Exception {
		String aFilePath = filePath + "1.a";
		String zipFileStr = filePath + "test.zip";
		byte[] buffer = new byte[1024 * 5];
		File zipFile = new File(zipFileStr);
		List<String> files = new ArrayList<>();
		File file = null;
		files.add(aFilePath);
		// 创建文件
		for (String fileAbsolutePath : files) {
			file = new File(fileAbsolutePath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		File mZFile = PowerMockito.spy(new File(zipFileStr));
		File mAFile = PowerMockito.spy(new File(aFilePath));
		FileInputStream mIs = PowerMockito.spy(new FileInputStream(mAFile));
		PowerMockito.mockStatic(ValidateUtil.class);
		PowerMockito.when(ValidateUtil.isNotEmpty(Mockito.anyCollection())).thenReturn(true);
		PowerMockito.when(ValidateUtil.isZip(Mockito.anyString())).thenReturn(true);
		PowerMockito.whenNew(File.class).withArguments(zipFileStr).thenReturn(mZFile); 
		PowerMockito.whenNew(File.class).withArguments(aFilePath).thenReturn(mAFile); 
		PowerMockito.when(mAFile.exists()).thenReturn(true);
		PowerMockito.whenNew(FileInputStream.class).withArguments(mAFile).thenReturn(mIs);
		PowerMockito.doThrow(new IOException()).when(mIs).read(buffer);;
		CompressUtil.compressFiles2Zip(files, zipFileStr);
		assertFalse(zipFile.exists());
		FileUtil.deleteExistsFile(mAFile);
	}
	
	
	
	@Test
	public void testCompressAndDecompressZip() {
		File file = null;
		List<String> files = new ArrayList<>();
		String zipFile = filePath + "test.zip";
		for (int i = 0; i < 3; i++) {
			files.add(filePath + i + ".a");
		}
		// 创建文件
		for (String fileAbsolutePath : files) {
			file = new File(fileAbsolutePath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		// 压缩文件
		CompressUtil.compressFiles2Zip(files, zipFile);
		file = new File(zipFile);
		assertTrue(file.exists());
		// 删除压缩包
		FileUtil.deleteExistsFile(file);
		// 删除文件
		for (String fileAbsolutePath : files) {
			file = new File(fileAbsolutePath);
			FileUtil.deleteExistsFile(file);
		}
	}
}
