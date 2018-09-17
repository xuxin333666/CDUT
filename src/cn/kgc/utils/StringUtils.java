package cn.kgc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StringUtils {
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static String[] map2StringArr(Map<String, String> map) {
		if(map != null) {
			String[] strs = new String[map.size()];
			Set<String> key = map.keySet();
			Iterator<String> iterator = key.iterator();
			int index = 0;
			while(iterator.hasNext()) {
				strs[index++] = map.get(iterator.next());
			}
			return strs;
		}
		return null;
	}
	
	public static String createTimeRandomId() {
		StringBuilder sb = new StringBuilder();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		sb.append(format.format(date));
		
		Random random = new Random();
		for(int i=0;i<6;i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	public static String parseURLCommand(String url) {
		int index = url.lastIndexOf("/");
		return url.substring(index+1);
	}
}
