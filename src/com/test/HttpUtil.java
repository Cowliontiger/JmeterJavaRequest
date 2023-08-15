package com.test;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	// private static Logger logger = Logger.getLogger(HttpUtil.class);

	/**
	 * ԭ���ַ�������get����
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String doGet(String url, String contentType, String token) {
		String result = "";
		// BasicConfigurator.configure();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Content-type", contentType);
//		httpGet.setHeader("Content-type", "application/json");
//		httpGet.setHeader("DataEncoding", "UTF-8");
//		httpGet.setHeader("token", token);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000)
				.setSocketTimeout(60000).build();
		httpGet.setConfig(requestConfig);
		httpGet.setHeader("DOSIGNORE", "1");
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
			// logger.info("����ɹ�");
			// System.out.println("get����ɹ�");
			//System.out.println(result);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * ԭ���ַ�������post����
	 * 
	 * @param url
	 * @param jsonStr
	 * @return
	 * @throws IOException
	 */
	public static String doPost(String url, String contentType, String token, String jsonStr) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000)
				.setSocketTimeout(60000).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-type", contentType);
		httpPost.setHeader("DOSIGNORE", "1");
		// httpPost.setHeader("DataEncoding", "UTF-8");
		// httpPost.setHeader("token", token);

		CloseableHttpResponse httpResponse = null;
		try {
			httpPost.setEntity(new StringEntity(jsonStr));
			httpResponse = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
			// System.out.println("post����ɹ�");
			// System.out.println(result);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * ԭ���ַ�������put����
	 * 
	 * @param url
	 * @param token
	 * @param jsonStr
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String doPut(String url, String contentType, String token, String jsonStr) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000)
				.setSocketTimeout(60000).build();
		httpPut.setConfig(requestConfig);
		httpPut.setHeader("Content-type", contentType);
		httpPut.setHeader("DOSIGNORE", "1");
//		httpPut.setHeader("Content-type", "application/json");
//		httpPut.setHeader("DataEncoding", "UTF-8");
//		httpPut.setHeader("token", token);

		CloseableHttpResponse httpResponse = null;
		try {
			httpPut.setEntity(new StringEntity(jsonStr));
			httpResponse = httpClient.execute(httpPut);
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
			// System.out.println("put����ɹ�");
			// System.out.println(result);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * ����delete����
	 * 
	 * @param url
	 * @param token
	 * @param jsonStr
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String doDelete(String url, String contentType, String token, String jsonStr) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000)
				.setSocketTimeout(60000).build();
		httpDelete.setConfig(requestConfig);
		httpDelete.setHeader("Content-type", contentType);
		httpDelete.setHeader("DOSIGNORE", "1");
//		httpDelete.setHeader("DataEncoding", "UTF-8");
//		httpDelete.setHeader("token", token);

		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpDelete);
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
			// System.out.println("delete����ɹ�");
			// System.out.println(result);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	
	public static String getURIStrBySplit(String str1,String str2,String str3,String str4) {
		String result = "";
		result = str1.split(str2)[1].split(str3)[1].split(str4)[0];
		return result;
	}
	
	public static String getResponseBodyBySplit(String str1,String str2,String str3,String str4) {
		String result = "";
		result = str1.split(str2)[1].split(str3)[1].split(str4)[0];
		return result;
	}
	
	//ֻȡ��һ��
	public static String getRequestHeadTypeBySplit(String str1,String str2,String str3,String str4) {
		String result = "";
		result = str1.split(str2)[1].split(str3)[0].split(str4)[1].split("\r\n")[0];
		return result;
	}
	

	public static String getRequestBodyBySplit(String str1,String str2,String str3,String str4,String str5) {
		String result = "";
		result = str1.split(str2)[1].split(str3)[0].split(str4)[1].split(str5)[0];
		return result;
	}
	
	public static String getHttpMethodBySplit(String str1,String str2,String str3,String str4,String str5) {
		String result = "";
		result = str1.split(str2)[1].split(str3)[0].split(str4)[1].split(str5)[0];
		return result;
	}
	
	
	
	public static void main(String[] args) {
		doGet("https://index.html?token=E1C50047BD9848F3A18E936502E46E56&gid=42130582#/203554953","","");
	}
	

}