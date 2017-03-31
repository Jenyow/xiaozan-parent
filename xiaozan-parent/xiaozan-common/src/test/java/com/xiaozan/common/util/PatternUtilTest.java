package com.xiaozan.common.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class PatternUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMatchIP() {
//		String patternStr = "(\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3})";
		String patternStr = "((?:\\d{0,3}(?:\\.)?){4})";
		String ip = "192.168.2.31";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(ip);
		List<String> values = new ArrayList<>();
		assertTrue(matcher.matches());
		for (int i = 1; i <= matcher.groupCount(); i++) {
			String groupi = matcher.group(i);
			values.add(groupi);
		}
		System.out.println(values.toString());
	}

	@Test
	public void testGroup() {
		String patternStr = "(a(b)?)+";
		String str = "aba";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(str);
		List<String> values = new ArrayList<>();
		assertTrue(matcher.matches());
		for (int i = 0; i <= matcher.groupCount(); i++) {
			String groupi = matcher.group(i);
			values.add(groupi);
		}
		System.out.println(values.toString());
	}
	
	@Test
	public void testMatch() {
		try {
			String patternStr = "\\./(\\{[a-zA-Z0-9]+\\})/(\\{[a-zA-Z0-9]+\\})/";
			String ip = "./{area}/{yyyyMMdd}/";
			Pattern pattern = Pattern.compile(patternStr);
			Matcher matcher = pattern.matcher(ip);
			List<String> values = new ArrayList<>();
			assertTrue(matcher.matches());
			for (int i = 1; i <= matcher.groupCount(); i++) {
				String groupi = matcher.group(i);
				values.add(groupi);
			}
			System.out.println(values.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMatch1() {
		try {
			String patternStr = "\\./(\\{[a-zA-Z0-9]+\\})/(\\{[a-zA-Z0-9]+\\})/";
			String ip = "./{area}/{yyyyMMdd}/";
			Pattern pattern = Pattern.compile(patternStr);
			Matcher matcher = pattern.matcher(ip);
			List<String> values = new ArrayList<>();
			assertTrue(matcher.matches());
			for (int i = 1; i <= matcher.groupCount(); i++) {
				String groupi = matcher.group(i);
				values.add(groupi);
			}
			System.out.println(values.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
