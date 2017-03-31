package com.xiaozan.chardet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UTFBOM文件处理 {
	public static void main(String[] args) throws Exception {
		List<String> resultList = readFileByLine("D:/DATA/ftpTMP/HA_PPM-WLAN-AC-PERF_1H_20170217050000_P000.csv");
		String tmpStr = resultList.get(0);
		System.out.println(tmpStr + "----len=" + tmpStr.length());
		String tmpStr2 = new String(tmpStr.substring(0, 1));
		System.out.println(tmpStr2 + "----hex=" + strtoHex(tmpStr2));
	}

	public static String strtoHex(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return "0x" + str;// 0x表示十六进制
	}

	public static List<String> readFileByLine(String filename) throws Exception {
		List<String> nameList = new ArrayList<String>();
		File file = new File(filename);
		InputStreamReader isr = new InputStreamReader(
				new FileInputStream(file), "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String tmp = reader.readLine();
		while (tmp != null && tmp.trim().length() > 0) {
			nameList.add(tmp);
			tmp = reader.readLine();
		}
		reader.close();
		return nameList;
	}
}