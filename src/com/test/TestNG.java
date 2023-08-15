package com.test;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNG {
	File dd = null;;
	List<FiddlerTemplate> alist = null;
	String result = "";
	SoftAssert assertion = null;
	@BeforeClass
	public void setup() {
		// System.out.println("begin test");
		assertion = new SoftAssert();
		try {
			dd = new File("C:\\Users\\mayn\\Desktop\\集成测试接口\\test.xlsx");
			alist = CreatAndReadExcel.read2007ExcelWithFiddlerTemplate(dd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		// System.out.println("at test");
		String actualString = "";
		String expectedString1 = "\"code\":100";
		String expectedString2 = "成功";
		
		for (int i = 1; i < alist.size(); i++) {
			if (alist.get(i) != null && !alist.get(i).toString().equals("")) {
				if (alist.get(i).getRequestMethod().equals("GET")) {
					actualString = HttpUtil.doGet(alist.get(i).getFullUrl(), alist.get(i).getContentType(), "");
				} else if (alist.get(i).getRequestMethod().equals("POST")) {
					actualString = HttpUtil.doPost(alist.get(i).getFullUrl(), alist.get(i).getContentType(), "",
							alist.get(i).getHead());
				} else if (alist.get(i).getRequestMethod().equals("PUT")) {
					actualString = HttpUtil.doPut(alist.get(i).getFullUrl(), alist.get(i).getContentType(), "",
							alist.get(i).getHead());
				} else if (alist.get(i).getRequestMethod().equals("DELETE")) {
					actualString = HttpUtil.doDelete(alist.get(i).getFullUrl(), alist.get(i).getContentType(), "",
							alist.get(i).getHead());
				} else {
					System.out.println("TODO method");
				}
			}
			if (actualString != null) {
				if (actualString.contains(expectedString1) || actualString.contains(expectedString2)) {
					// Assert.assertTrue(true);
					assertion.assertTrue(true);
					Reporter.log("<font color=green>测试通过</font><br>");
					Reporter.log("预期测试结果:"+expectedString1+" | "+expectedString2+"<br>");
					Reporter.log("实际测试结果:"+actualString+"<br><hr />");
				} else {
					// Assert.assertTrue(false);
					assertion.assertTrue(false);
					Reporter.log("<font color=red>测试不通过</font><br>");
					Reporter.log("预期测试结果:"+expectedString1+" | "+expectedString2+"<br>");
					Reporter.log("实际测试结果:"+actualString+"<br><hr />");
				}
			}
		}
	}

	@AfterClass
	public void teardown() {
		// System.out.println("end test");
		assertion.assertAll();
		alist = null;
		dd = null;
	}
}
