package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NewTest {

	public final static String groupId = "42130582";
	public final static String firstFileId = "203554953";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fileId = "";
		
		String jsonStr = null;
		String firstFileId = "203554953";
		String baseUrl = "";
		baseUrl = "" + groupId
				+ "/share?method=get&shareToken=E1C50047BD9848F3A18E936502E46E56&fileId=" + fileId;
		System.out.println("1:" + baseUrl);
		jsonStr = HttpUtil.doGet(baseUrl, "", "");
		// System.out.println(jsonStr);
		String[] a = jsonStr.split("\"fileId\":");

		ArrayList<HashMap<String, String>> alist = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> m = new HashMap<String, String>();
		for (int i = 0; i < a.length; i++) {
			// System.out.println(a[i] + " ");
			if (a[i].indexOf("parentId") > -1 && !a[i].split(",")[0].equals(firstFileId)) {
				// System.out.println(a[i]+" ");
				System.out.println("fileId:" + a[i].split(",")[0]);
				System.out.println("parentId:" + a[i].split("\"parentId\":")[1].split(",")[0]);
				System.out.println("childrenNum:" + a[i].split("\"childrenNum\":")[1].split(",")[0]);
				m.put(a[i].split(",")[0], a[i].split("\"parentId\":")[1].split(",")[0] + "_"
						+ a[i].split("\"childrenNum\":")[1].split(",")[0]);
			}
		}
		
		
		itData(m);

//		Iterator iterator = m.entrySet().iterator();
//		// Map<String, String> m2 = null;
//		HashMap<String, String> m2 = null;
//		while (iterator.hasNext()) {
//			Map.Entry entry = (Map.Entry) iterator.next();
//			baseUrl = "" + groupId
//					+ "/share?method=get&shareToken=E1C50047BD9848F3A18E936502E46E56&fileId=" + entry.getKey();
//			jsonStr = HttpUtil.doGet(baseUrl, "", "");
//			System.out.println("2:" + baseUrl);
//
//			//if (!entry.getValue().toString().split("_")[1].equals("0")) {
//				String[] a2 = jsonStr.split("\"fileId\":");
//
//				alist = new ArrayList<HashMap<String, String>>();
//				m2 = new HashMap<String, String>();
//				String parentId = "\"parentId\":" + entry.getKey();
//				for (int i = 0; i < a2.length; i++) {
//					// System.out.println(a[i] + " ");
//					if (a2[i].indexOf("parentId") > -1 && !a2[i].split(",")[0].equals(firstFileId)
//							&& a2[i].indexOf(parentId) > -1 && a2[i].indexOf("childrenNum") > -1) {
//						// System.out.println(a[i]+" ");
//						System.out.println("fileId:" + a2[i].split(",")[0]);
//						System.out.println("parentId:" + a2[i].split("\"parentId\":")[1].split(",")[0]);
//						System.out.println("childrenNum:" + a2[i].split("\"childrenNum\":")[1].split(",")[0]);
//						m2.put(a2[i].split(",")[0], a2[i].split("\"parentId\":")[1].split(",")[0] + "_"
//								+ a2[i].split("\"childrenNum\":")[1].split(",")[0]);
//					}
//				}
//			//}
//
//		}
//
//		Iterator iterator2 = m2.entrySet().iterator();
//		// Map<String, String> m2 = null;
//		HashMap<String, String> m3 = null;
//		while (iterator2.hasNext()) {
//			Map.Entry entry = (Map.Entry) iterator2.next();
//			baseUrl = "" + groupId
//					+ "/share?method=get&shareToken=E1C50047BD9848F3A18E936502E46E56&fileId=" + entry.getKey();
//			jsonStr = HttpUtil.doGet(baseUrl, "", "");
//			System.out.println("3:" + baseUrl);
//
//			//if (!entry.getValue().toString().split("_")[1].equals("0")) {
//				String[] a3 = jsonStr.split("\"fileId\":");
//
//				alist = new ArrayList<HashMap<String, String>>();
//				m3 = new HashMap<String, String>();
//				String parentId = "\"parentId\":" + entry.getKey();
//				for (int i = 0; i < a3.length; i++) {
//					// System.out.println(a[i] + " ");
//					if (a3[i].indexOf("parentId") > -1 && !a3[i].split(",")[0].equals(firstFileId)
//							&& a3[i].indexOf(parentId) > -1 && a3[i].indexOf("childrenNum") > -1) {
//						// System.out.println(a[i]+" ");
//						System.out.println("fileId:" + a3[i].split(",")[0]);
//						System.out.println("parentId:" + a3[i].split("\"parentId\":")[1].split(",")[0]);
//						System.out.println("childrenNum:" + a3[i].split("\"childrenNum\":")[1].split(",")[0]);
//						m3.put(a3[i].split(",")[0], a3[i].split("\"parentId\":")[1].split(",")[0] + "_"
//								+ a3[i].split("\"childrenNum\":")[1].split(",")[0]);
//					}
//				}
//			//}
//
//		}

		// itData(m);
	}

	
	public static Iterator itData(HashMap<String, String> map) {
		HashMap<String, String> newMap = map;
		String baseUrl = "";
		String jsonStr = "";
		Iterator iterator2 = newMap.entrySet().iterator();
		// Map<String, String> m2 = null;
		HashMap<String, String> m3 = null;
		while (iterator2.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator2.next();
			baseUrl = "" + groupId
					+ "/share?method=get&shareToken=E1C50047BD9848F3A18E936502E46E56&fileId=" + entry.getKey();
			jsonStr = HttpUtil.doGet(baseUrl, "", "");
			System.out.println("3:" + baseUrl);

			//if (!entry.getValue().toString().split("_")[1].equals("0")) {
				String[] a3 = jsonStr.split("\"fileId\":");

				//alist = new ArrayList<HashMap<String, String>>();
				m3 = new HashMap<String, String>();
				String parentId = "\"parentId\":" + entry.getKey();
				for (int i = 0; i < a3.length; i++) {
					// System.out.println(a[i] + " ");
					if (a3[i].indexOf("parentId") > -1 && !a3[i].split(",")[0].equals(firstFileId)
							&& a3[i].indexOf(parentId) > -1 && a3[i].indexOf("childrenNum") > -1) {
						// System.out.println(a[i]+" ");
						System.out.println("fileId:" + a3[i].split(",")[0]);
						System.out.println("parentId:" + a3[i].split("\"parentId\":")[1].split(",")[0]);
						System.out.println("childrenNum:" + a3[i].split("\"childrenNum\":")[1].split(",")[0]);
						m3.put(a3[i].split(",")[0], a3[i].split("\"parentId\":")[1].split(",")[0] + "_"
								+ a3[i].split("\"childrenNum\":")[1].split(",")[0]);
					}
				}
			//}

		}
		
		
		
		return itData(newMap);
	}


}
