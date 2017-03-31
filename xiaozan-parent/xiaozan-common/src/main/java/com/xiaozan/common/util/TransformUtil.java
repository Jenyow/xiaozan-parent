package com.xiaozan.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 数据转换工具类
 */
public class TransformUtil {
	
	private static Logger logger = LoggerFactory.getLogger(TransformUtil.class);

	/**
	 * <P>
	 * 如果为科学计数法格式的字符串则转换为非科学计数法的形式
	 * 
	 * @param str
	 * @return
	 */
	public static String scientificNotationFormat(String str) {
		String value = null;
		try {
			BigDecimal bd = new BigDecimal(str);
			value = bd.toPlainString();
		} catch (Exception e) {
			logger.error("传入的字符串[{}]不符合十进制数的格式", str);
		}
		return value;
	}

	/**
	 * <p>
	 * 将符合科学计数法格式的字符串转换为非科学计数法的形式
	 * 
	 * @param obj
	 * @param maximumFractionDigits
	 *            数的小数部分所允许的最大位数
	 * @return
	 */
	public static String formatData(Object obj, int maximumFractionDigits) {
		String value = null;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false); // 设置此格式中不使用分组
		nf.setMaximumFractionDigits(maximumFractionDigits);
		value = nf.format(obj);
		return value;
	}

	/**
	 * <p>
	 * 按照给定模版格式化对象为字符串
	 * 
	 * <pre>
	 * 模版例子：
	 * 科学计数法:"0.##E0"
	 * 普通计数保留两位小数:"0.00"
	 * </pre>
	 * 
	 * @param obj
	 *            需格式化对象
	 * @param pattern
	 *            模版
	 * @return
	 */
	public static String formatData(Object obj, String pattern) {
		String value = null;
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		value = decimalFormat.format(obj);
		return value;
	}
	
	/**
	 * 去重合并数组,保留数组中的元素顺序
	 * @param list
	 * @return
	 */
	public static Object[] mergeArray(List<Object[]> list) {
		LinkedHashSet<Object> set = new LinkedHashSet<>();
		for (Object[] objects: list) {
			set.addAll(new LinkedHashSet<Object>(Arrays.asList(objects)));
		}
		return set.toArray();
	}

}
