package edu.hebeu.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

/*
 * 日期格式化类（必须掌握）
 * API:
 * G  Era 标志符  Text  AD  
 y  年  Year  1996; 96  
 M  年中的月份  Month  July; Jul; 07  
 w  年中的周数  Number  27  
 W  月份中的周数  Number  2  
 D  年中的天数  Number  189  
 d  月份中的天数  Number  10  
 F  月份中的星期  Number  2  
 E  星期中的天数  Text  Tuesday; Tue  
 a  Am/pm 标记  Text  PM  
 H  一天中的小时数（0-23）  Number  0  
 k  一天中的小时数（1-24）  Number  24  
 K  am/pm 中的小时数（0-11）  Number  0  
 h  am/pm 中的小时数（1-12）  Number  12  
 m  小时中的分钟数  Number  30  
 s  分钟中的秒数  Number  55  
 S  毫秒数  Number  978  
 z  时区  General time zone  Pacific Standard Time; PST; GMT-08:00  
 Z  时区  RFC 822 time zone  -0800  

 */
public class DateUtil {

	public static String FMT = "yyyy-MM-dd HH:mm:ss";
	public static String FMT_YYY_MM_DD = "yyyy-MM-dd";

	/**
	 * 格式化日期
	 * @param date 日期
	 * @param fmt 格式
	 * @return
	 */
	public static String formatDate(Date date, String fmt) {
		if(date == null) {
			return "";
		}
		if (StringUtils.isBlank(fmt)) {
			fmt = FMT;
		}
		DateFormat bf = new SimpleDateFormat(fmt);// 多态
		String format = bf.format(date);// 格式化 bf.format(date);
		return format;
	}

	public static void main(String[] args) {
		
		String fmt = "yyyy-MM-dd E a HH:mm:ss";
		DateFormat ft = new SimpleDateFormat(fmt);
		Date d = new Date();
		String format = ft.format(d);
		System.out.println(format);
	
		
	
//		// String ------->Date对象
//		String s = "2017-04-19 星期三 下午 20:17:38";// 有格式要求 必须和自定义模式严格一致
//		try {
//			Date parse = bf.parse(s);// df.parse(s);String转成对象
//			System.out.println(parse);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
}