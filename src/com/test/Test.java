package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


	public static Matcher getRegxStr(String str, String regex) {
		String result = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		return m;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		String url = "https://share?method=get&shareToken=E1C50047BD9848F3A18E936502E46E56&fileId=431579828";
		String regex = "\"fileId\":[1-9][0-9]{3,},\"groupId\":[1-9][0-9]{3,},\"name\":[\\s\\S]*,\"parentId\":[1-9][0-9]{3,}";
		//String regex = "\"fileId\":[1-9][0-9]{3,},\"groupId\":[1-9][0-9]{3,}";
		String jsonStr = HttpUtil.doGet(url, "", "");
		Matcher m = getRegxStr(jsonStr,regex);
		while(m.find()) {
			System.out.println(m.group());
		}
	}
	
	public static List getCatalogId(String str) {
		String[] a = str.split("\"catalogId\"");
		List alist = new ArrayList();
		for(int i = 1 ; i < a.length ; i++) {
			alist.add((a[i].replace("\"", "").replace(":", "")).split(",")[0]);
		}
		return alist;
	}
	
	public static String changeNumInString(String str1,String str2) {
		str1 = "/services/wares/v2/catalogs/3.json?plateForm=1";
		str2 = "123";
		String result = str1.split("catalogs/")[0] +"catalogs/" + str2 + "/subCatalogsAndProds"+ str1.split("subCatalogsAndProds")[1];
		System.out.println(result);
		return result;
	}

}
