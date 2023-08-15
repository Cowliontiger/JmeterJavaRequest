package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class traverseFolder {

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
		String childUrl = "";
		jsonStr = HttpUtil.doGet(baseUrl, "", "");
		// System.out.println(jsonStr);
		String[] a = jsonStr.split("\"fileId\":");
		List pathList = new ArrayList();
		for (int i = 0; i < a.length; i++) {
			if (a[i].indexOf("\"parentId\"") > -1 && !a[i].split(",")[0].equals(firstFileId)) {
				childUrl = "" + groupId
						+ "/share?method=get&shareToken=E1C50047BD9848F3A18E936502E46E56&fileId=" + a[i].split(",")[0]
						+ "&childrenNum=" + a[i].split("\"childrenNum\":")[1].split(",")[0];
				System.out.println("childUrl:" + childUrl);
				// pathList.add(childUrl);
				traverseFolder(childUrl);
			}
		}
	}

	public static void traverseFolder(String path) {
		String[] a = {};
		int i = 0;
		try {
			String openUrl = "";
			String fileId = path.split("fileId=")[1].split("&childrenNum")[0];
			// 后缀为md结尾的文件
			if (path.indexOf("childrenNum=0") > -1) {
				openUrl = "42130582/file/" + fileId
						+ "?method=download&version=2&shareToken=E1C50047BD9848F3A18E936502E46E56&cstk=false";
				System.out.println("md文件：" + openUrl);
				//i++;
				//return;
			} else {
				openUrl = path;
				String jsonStr = HttpUtil.doGet(openUrl, "", "");
				a = jsonStr.split("\"fileId\":");
				for (i = 0; i < a.length; i++) {
					if (a[i].indexOf("\"parentId\"") > -1 && !a[i].split(",")[0].equals(firstFileId)) {
						if (!fileId.equals(a[i].split(",")[0])) {
							openUrl = "" + groupId
									+ "/share?method=get&shareToken=E1C50047BD9848F3A18E936502E46E56&fileId="
									+ a[i].split(",")[0] + "&childrenNum="
									+ a[i].split("\"childrenNum\":")[1].split(",")[0];
							//System.out.println("文件夹:" + openUrl);
							traverseFolder(openUrl);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public void traverseFolder2(String path) {
//
//		File file = new File(path);
//		if (file.exists()) {
//			File[] files = file.listFiles();
//			if (null == files || files.length == 0) {
//				System.out.println("文件夹是空的!");
//				return;
//			} else {
//				for (File file2 : files) {
//					if (file2.isDirectory()) {
//						System.out.println("文件夹:" + file2.getAbsolutePath());
//						traverseFolder2(file2.getAbsolutePath());
//					} else {
//						System.out.println("文件:" + file2.getAbsolutePath());
//					}
//				}
//			}
//		} else {
//			System.out.println("文件不存在!");
//		}
//	}

}
