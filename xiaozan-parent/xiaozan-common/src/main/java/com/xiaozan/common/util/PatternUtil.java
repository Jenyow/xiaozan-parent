package com.xiaozan.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

	public static void main1(String[] args) {
		// String filenamePattern = "PM_IGResource Type
		// 30024_15_{dateTime}Z_{order}.csv";
		String filenamePattern = "{dateTime} {devicename} %%01SHELL/5/LOGOUT(s)[{port}]:The user succeeded in logging out of VTY0. (UserType=Telnet, UserName={username}, Ip={ip}, VpnName=)";
		// String filename = "PM_IGResource Type 30024_15_201702210500Z_01.csv";
		String filename = "Jul 20 2016 16:44:00 GDGZ-PS-IMS-CE07-HWNE40EX8 %%01SHELL/5/LOGOUT(s)[69573]:The user succeeded in logging out of VTY0. (UserType=Telnet, UserName=ipnet, Ip=10.201.36.41, VpnName=)";
		String patternStr = "\\{[a-zA-Z0-9]+\\}";
		String replacement = "(.+)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(filenamePattern);
		List<String> matcherStrs = new ArrayList<>();
		Map<String, String> keyMap = new HashMap<>();
		while (matcher.find()) {
			matcherStrs.add(matcher.group());
		}
		for (String regex : matcherStrs) {
			regex = regex.replace("{", "\\{");
			regex = regex.replace("}", "\\}");
			filenamePattern = filenamePattern.replaceFirst(regex, replacement);
		}
		Pattern pattern1 = Pattern.compile(filenamePattern);
		Matcher matcher1 = pattern1.matcher(filename);
		System.out.println(filenamePattern + " 与 " + filename + "是否匹配：" + matcher1.matches());
		for (int i = 1; i <= matcher1.groupCount(); i++) {
			String groupi = matcher1.group(i);
			keyMap.put(matcherStrs.get(i - 1), groupi);
		}
		System.out.println(keyMap.toString());
	}
}
