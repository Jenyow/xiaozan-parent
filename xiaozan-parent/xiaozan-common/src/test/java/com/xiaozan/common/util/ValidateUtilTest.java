package com.xiaozan.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.xiaozan.common.util.FileUtil;
import com.xiaozan.common.util.ValidateUtil;

@RunWith(PowerMockRunner.class)
public class ValidateUtilTest {

	@Before
	public void setUp() throws Exception {
		 
	}

	@Test
	public void testIsNotEmptyList() {
		List<String> list = new ArrayList<>();
		assertFalse(ValidateUtil.isNotEmpty(list));
		list.add("a");
		assertTrue(ValidateUtil.isNotEmpty(list));
		list = null;
		assertEquals(false, ValidateUtil.isNotEmpty(list));
	}

	@Test
	public void testIsNotEmptySet() {
		Set<String> set = new HashSet<>();
		assertEquals(false, ValidateUtil.isNotEmpty(set));
		set.add("a");
		assertEquals(true, ValidateUtil.isNotEmpty(set));
		set = null;
		assertEquals(false, ValidateUtil.isNotEmpty(set));
	}

	@Test
	public void testIsNumerical() {
		String positive = "1";// 正整数
		String minus = "-1";// 负整数
		String positiveDecimal = "1.1";// 正小数
		String negativeDecimal = "-1.1";// 负小数
		String scientificNotation = "-1.1E-1";// 科学计数
		String str = "str";// 非数字
		assertTrue(ValidateUtil.isNumerical(minus));
		assertTrue(ValidateUtil.isNumerical(positive));
		assertTrue(ValidateUtil.isNumerical(negativeDecimal));
		assertTrue(ValidateUtil.isNumerical(positiveDecimal));
		assertTrue(ValidateUtil.isNumerical(scientificNotation));
		assertFalse(ValidateUtil.isNumerical(str));
	}

	@Test
	public void testIsScientificNotation() {
		String str = null;
		str = "-1E1";
		assertTrue(ValidateUtil.isScientificNotation(str));
		str = "1E1";
		assertTrue(ValidateUtil.isScientificNotation(str));
		str = "-1.1E-1";
		assertTrue(ValidateUtil.isScientificNotation(str));
		str = "1.1E-1";
		assertTrue(ValidateUtil.isScientificNotation(str));
		str = "1E+1";
		assertTrue(ValidateUtil.isScientificNotation(str));
		str = "1";
		assertFalse(ValidateUtil.isScientificNotation(str));

		Double d = null;
		d = -0.0001;
		assertTrue(ValidateUtil.isScientificNotation(d));
		d = 0.0001;
		assertTrue(ValidateUtil.isScientificNotation(d));
		d = -10000000.0;
		assertTrue(ValidateUtil.isScientificNotation(d));
		d = 10000000.0;
		assertTrue(ValidateUtil.isScientificNotation(d));
		d = 1.0;
		assertFalse(ValidateUtil.isScientificNotation(d));
	}

	@Test
	@PrepareForTest({StringUtils.class, FileUtil.class})
	public void testIsZipTrueWhenNameIszip() {
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.mockStatic(FileUtil.class);
		boolean actual = false;
		PowerMockito.when(StringUtils.isNotBlank(Mockito.anyString())).thenReturn(true);
		PowerMockito.when(FileUtil.getFilenameExtension(Mockito.anyString())).thenReturn("zip");
		actual = ValidateUtil.isZip(Mockito.anyString());
		assertTrue(actual);
	}
	
	@Test
	@PrepareForTest({StringUtils.class, FileUtil.class})
	public void testIsZipTrueWhenNameIsZIP() {
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.mockStatic(FileUtil.class);
		boolean actual = false;
		PowerMockito.when(StringUtils.isNotBlank(Mockito.anyString())).thenReturn(true);
		PowerMockito.when(FileUtil.getFilenameExtension(Mockito.anyString())).thenReturn("ZIP");
		actual = ValidateUtil.isZip(Mockito.anyString());
		assertTrue(actual);
	}
	
	@Test
	@PrepareForTest({StringUtils.class, FileUtil.class})
	public void testIsZipFalseWhenNameIsNotZip() {
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.mockStatic(FileUtil.class);
		boolean actual = false;
		PowerMockito.when(StringUtils.isNotBlank(Mockito.anyString())).thenReturn(true);
		PowerMockito.when(FileUtil.getFilenameExtension(Mockito.anyString())).thenReturn("gz");
		actual = ValidateUtil.isZip(Mockito.anyString());
		assertFalse(actual);
	}
	
	@Test
	@PrepareForTest({StringUtils.class, FileUtil.class})
	public void testIsZipFalseWhenNameIsBlank() {
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.mockStatic(FileUtil.class);
		boolean actual = false;
		PowerMockito.when(StringUtils.isNotBlank(Mockito.anyString())).thenReturn(false);
		actual = ValidateUtil.isZip(Mockito.anyString());
		assertFalse(actual);
	}
	
	@Test
	@PrepareForTest({StringUtils.class, FileUtil.class})
	public void testIsZipFalseWhenFilenameExtensionIsBlank() {
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.mockStatic(FileUtil.class);
		boolean actual = false;
		PowerMockito.when(StringUtils.isNotBlank(Mockito.anyString())).thenReturn(true, false);
		PowerMockito.when(FileUtil.getFilenameExtension(Mockito.anyString())).thenReturn(null);
		actual = ValidateUtil.isZip(Mockito.anyString());
		assertFalse(actual);
	}
	
}
