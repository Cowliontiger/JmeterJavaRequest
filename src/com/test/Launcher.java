package com.test;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class Launcher  extends AbstractJavaSamplerClient {
	SampleResult results;

	@Override
	public Arguments getDefaultParameters() {
		Arguments arguments = new Arguments();
		arguments.addArgument("url", "");
		arguments.addArgument("cookie", "");
		return arguments;

	}

	@Override
	public void setupTest(JavaSamplerContext arg0) {

	}

	public SampleResult runTest(JavaSamplerContext arg0) {
		results = new SampleResult();
		results.sampleStart();

		String responseData = "";
		try {
			responseData = new HttpClientUtil().httpGet(arg0.getParameter("url"), arg0.getParameter("cookie"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		results.sampleEnd();
		if (responseData != null) {
			results.setSuccessful(true);
			results.setResponseData(responseData, null);
			results.setDataType(SampleResult.TEXT);
		} else {
			results.setSuccessful(false);
		}
		return results;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {

	}

}
