package com.ypgly.yipin.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepEnchUtilImpl {

	private static Logger log= LoggerFactory.getLogger(RepEnchUtilImpl.class);

	public static String replace1(String paragraph){
		
		//全角 半角 符号转换
		char[] c = paragraph.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
			}else if (c[i] > 65280 && c[i] < 65375){
				c[i] = (char) (c[i] - 65248);
			}
		}
		paragraph = new String(c);
		
		paragraph = paragraph.replaceAll("，", ",");
		paragraph = paragraph.replaceAll("。", ".");
		paragraph = paragraph.replaceAll("；", ";");
		paragraph = paragraph.replaceAll("？", "?");
		paragraph = paragraph.replaceAll("！", "!");
		paragraph = paragraph.replaceAll("：", ":");
		paragraph = paragraph.replaceAll("＠", "@");
		paragraph = paragraph.replaceAll("“", "\"");
		paragraph = paragraph.replaceAll("”", "\"");
		paragraph = paragraph.replaceAll("—", "-");
		paragraph = paragraph.replaceAll("–", "-");
		paragraph = paragraph.replaceAll("‘", "'");
		paragraph = paragraph.replaceAll("’", "'");
		paragraph = paragraph.replaceAll("（", "(");
		paragraph = paragraph.replaceAll("）", ")");
		paragraph = paragraph.replaceAll("【", "[");
		paragraph = paragraph.replaceAll("】", "]");
		paragraph = paragraph.replaceAll("《", "'");
		paragraph = paragraph.replaceAll("》", "'");
		paragraph = paragraph.replaceAll(" +", " ");
		paragraph = paragraph.replaceAll(" \\. ", ". ");
		paragraph = paragraph.replaceAll(" \\? ", "? ");
		paragraph = paragraph.replaceAll(" ! ", "! ");
		paragraph = paragraph.replaceAll("　", " ");
		
		return paragraph;
	}


	public static String replace2(String paragraph) {
		paragraph = paragraph.replaceAll("~1", "/");
		paragraph = paragraph.replaceAll("~2", "\\");
		paragraph = paragraph.replaceAll("~3", "\"");
		paragraph = paragraph.replaceAll("~4", "[");
		paragraph = paragraph.replaceAll("~5", "]");
		paragraph = paragraph.replaceAll(",", "，");
		paragraph = paragraph.replaceAll(";", "；");
		paragraph = paragraph.replaceAll("\\?", "？");
		paragraph = paragraph.replaceAll("!", "！");
		paragraph = paragraph.replaceAll("<UNK>", "");
		paragraph = paragraph.replaceAll("<unk>", "");
		paragraph = paragraph.replaceAll(" ", "");
		return paragraph;
	}


	public static String toCapital(String paragraph) {
		return paragraph;
//		String str1 = paragraph;
//		StringBuffer strB = new StringBuffer();
//		int start = 0;
//		Pattern p = Pattern.compile("[a-zA-z]");
//		Matcher m = p.matcher(str1);
//		while(m.find()){
//			String str2 = str1.substring(start, m.start());
//			strB.append(str2);
//			String str3 = str1.substring(m.start(), m.end());
//			str3 = str3.toUpperCase();
//			strB.append(str3);
//			start = m.end();
//		}
//		if(start != str1.length()){
//			String str4 = str1.substring(start, str1.length());
//			strB.append(str4);
//		}
//		return String.valueOf(strB);
	}


	public static String dateToArabicNumerals(String paragraph) {
		String str1 = paragraph;
		StringBuffer strB = new StringBuffer();
		int start = 0;
		Pattern p = Pattern.compile("([一,二,三,四,五,六,七,八,九,十]{1,}日)|([一,二,三,四,五,六,七,八,九,十]{1,}号)|([一,二,三,四,五,六,七,八,九,十]{1,}月)");
		Matcher m = p.matcher(str1);
		while(m.find()){
			String str2 = str1.substring(start, m.start());
			strB.append(str2);
			String str3 = str1.substring(m.start(), m.end());
			str3 = toArabicNumerals(str3.substring(0, str3.length()-1)) + str3.substring(str3.length()-1);
			strB.append(str3);
			start = m.end();
		}
		if(start != str1.length()){
			String str4 = str1.substring(start, str1.length());
			strB.append(str4);
		}
		return String.valueOf(strB);
	}

	/**
	 * 作用：大写数字变成阿拉伯数字(1-99)
	 * @param
	 * @return
	 */
	public static String toArabicNumerals(String paragraph) {
		Map<String,String> mapNumber = new HashMap<String,String>(){{
			put("一","1");	put("二","2");	put("三","3");	put("四","4");	put("五","5");	put("六","6");
			put("七","7");	put("八","8");	put("九","9");		
		}};
		String str = paragraph;
		
		if(str.length()==1) {
			if(mapNumber.get(str)!=null) {
				str = mapNumber.get(str);
			}
		}else if(str.length()==2 && "十".equals(str.substring(0, 1)) && mapNumber.get(str.substring(1))!=null ) {
			str = ( 10 + Integer.valueOf( mapNumber.get(str.substring(1)) ) ) + "";
		}else if(str.length()==2 && "十".equals(str.substring(1)) && mapNumber.get(str.substring(0,1))!=null ) {
			str = ( 10 * Integer.valueOf( mapNumber.get(str.substring(0,1)) ) ) + "";
		}else if(str.length()==3 && "十".equals(str.substring(1, 2)) 
				&& mapNumber.get(str.substring(0, 1))!=null && mapNumber.get(str.substring(2))!=null ) {
			str = ( Integer.valueOf( mapNumber.get(str.substring(0, 1)) ) * 10 + Integer.valueOf( mapNumber.get(str.substring(2)) )) + "";
		}
		
		return str;
	}


	public static String timeToArabicNumerals(String paragraph) {
		String str1 = paragraph;
		
		StringBuffer strB = new StringBuffer();
		int start = 0;
		String pm = "[一,二,三,四,五,六,七,八,九,十]{1,}时[一,二,三,四,五,六,七,八,九,十]{1,}分至[一,二,三,四,五,六,七,八,九,十]{1,}时[一,二,三,四,五,六,七,八,九,十]{1,}分|"
				+ "[一,二,三,四,五,六,七,八,九,十]{1,}时至[一,二,三,四,五,六,七,八,九,十]{1,}时[一,二,三,四,五,六,七,八,九,十]{1,}分|"
				+ "[一,二,三,四,五,六,七,八,九,十]{1,}时[一,二,三,四,五,六,七,八,九,十]{1,}分至[一,二,三,四,五,六,七,八,九,十]{1,}时|"
				+ "[一,二,三,四,五,六,七,八,九,十]{1,}时至[一,二,三,四,五,六,七,八,九,十]{1,}时";
		Pattern p = Pattern.compile(pm);
		Matcher m = p.matcher(str1);
		while(m.find()){
			String str2 = str1.substring(start, m.start());
			strB.append(str2);
			String str3 = str1.substring(m.start(), m.end());
			
			String[] d = str3.split("至");
			
			String[] a = d[0].split("时");
			str3 = toArabicNumerals(a[0]) + "：";
			if(a.length>1) {
				String[] b = a[1].split("分");
				str3 += toArabicNumerals(b[0]);
			}else {
				str3 += "00";
			}
			
			str3 += "-";
			a = d[1].split("时");
			str3 += toArabicNumerals(a[0]) + "：";
			if(a.length>1) {
				String[] b = a[1].split("分");
				str3 += toArabicNumerals(b[0]) + "";
			}else {
				str3 += "00";
			}
			strB.append(str3);
			start = m.end();
		}
		if(start != str1.length()){
			String str4 = str1.substring(start, str1.length());
			strB.append(str4);
		}
		str1 = String.valueOf(strB);
		
		
		strB = new StringBuffer();
		start = 0;
		pm = "[一,二,三,四,五,六,七,八,九,十]{1,}时[一,二,三,四,五,六,七,八,九,十]{1,}分[一,二,三,四,五,六,七,八,九,十]{1,}秒|"
				+ "[一,二,三,四,五,六,七,八,九,十]{1,}时[一,二,三,四,五,六,七,八,九,十]{1,}分|"
				+ "[一,二,三,四,五,六,七,八,九,十]{1,}时";
		p = Pattern.compile(pm);
		m = p.matcher(str1);
		while(m.find()){
			String str2 = str1.substring(start, m.start());
			strB.append(str2);
			String str3 = str1.substring(m.start(), m.end());
			
			String[] a = str3.split("时");
			str3 = toArabicNumerals(a[0]) + "时";
			if(a.length>1) {
				String[] b = a[1].split("分");
				str3 += toArabicNumerals(b[0]) + "分";
				if(b.length>1) {
					String[] c = b[1].split("秒");
					str3 += toArabicNumerals(c[0]) + "秒";
				}
			}
			strB.append(str3);
			start = m.end();
		}
		if(start != str1.length()){
			String str4 = str1.substring(start, str1.length());
			strB.append(str4);
		}
		str1 = String.valueOf(strB);
		return str1;
	}
	
	
}
