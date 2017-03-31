package com.xiaozan.common.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.xiaozan.common.util.FileUtil;

public class FileUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetFilenameExtension() {
		String filename = null;
		String actual = null;
		String expected = null;
		filename = "test.txt";
		expected = "txt";
		actual = FileUtil.getFilenameExtension(filename);
		assertEquals(expected, actual);
		filename = "test";
		actual = FileUtil.getFilenameExtension(filename);
		assertNull(actual);
	}

}
