package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@SuppressWarnings("deprecation")
public class HttpClientUtil {

	/**
	 * @param url
	 * @param cookie
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String url,String cookie) throws Exception{  

        String result=""; 
        HttpGet request=new HttpGet(url);
        @SuppressWarnings("resource")
		HttpClient httpClient=new DefaultHttpClient();
        request.addHeader("Cookie", cookie);
        HttpResponse httpResponse=httpClient.execute(request);
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        if(statusCode==HttpStatus.SC_OK){
            HttpEntity responseHttpEntity=httpResponse.getEntity();
            InputStream in=responseHttpEntity.getContent();
            result=getData(in);
        }
        System.out.println(result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String mobBaseRes=jsonObject.getString("mobBaseRes");
        System.out.println("mobBaseRes=" + mobBaseRes );
        return result;
    }
	
	

	public static String httpGetHead(String url,String cookie,String header) throws Exception{  

        String strResult = "";

        HttpGet request=new HttpGet(url);

        @SuppressWarnings("resource")
		HttpClient httpClient=new DefaultHttpClient();

        request.addHeader("Cookie", cookie);

        HttpResponse httpResponse=httpClient.execute(request);

        int statusCode=httpResponse.getStatusLine().getStatusCode();
        if(statusCode==HttpStatus.SC_OK){

        	strResult=httpResponse.getHeaders(header)[0].getValue().toString();
//        	Header[] headers = httpResponse.getAllHeaders();//返回的HTTP头信�?
//            for (int i=0; i<headers.length; i++) {
//            	System.out.println(headers[i]);
//            }
        }
        return strResult;
    }
	
	

    private static String getData(InputStream in) {
        String result="";
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                //result = result + line;
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    } 
    

    @SuppressWarnings("unused")
	private static String InputStreamToString(InputStream is){
        BufferedReader reader = null;
        StringBuffer responseText = null;
        String readerText = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            responseText = new StringBuffer();
            readerText = reader.readLine();
            while(readerText != null){
                responseText.append(readerText);
                responseText.append(System.getProperty("line.separator"));
                readerText = reader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText.toString();
    }
    
    

    public static String httpPost(String url,Map<String,String> map,String cookie) {
    	String body = "";  
        @SuppressWarnings("resource")
		HttpClient httpClient=new DefaultHttpClient();
        HttpPost response=new HttpPost(url);
        

        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        if(map!=null){  
            for (Entry<String, String> entry : map.entrySet()) {  
            	params.add( new BasicNameValuePair(entry.getKey(),entry.getValue()) );  
            }         
        }      
   
        try {
			response.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}   
        System.out.println(url);  
        System.out.println(params.toString());   
        response.setHeader("Accept", "text/html, application/xhtml+xml, */*"); 
        response.setHeader("Content-Type", "application/x-www-form-urlencoded");
        response.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))"); 
        response.setHeader("Cookie", cookie);
        
 
        CloseableHttpResponse httpResponse;
		try {
			httpResponse = (CloseableHttpResponse) httpClient.execute(response);      
 
			HttpEntity entity = httpResponse.getEntity();  
			if (entity != null) {  
			
				body = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);

        httpResponse.close();          
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return body;  
    }

    public static String httpPostHead(String url,Map<String,String> map,String cookie,String headers) {   	

    	String head = "";  

        @SuppressWarnings("resource")
        CloseableHttpClient httpClient=new DefaultHttpClient();
  
        HttpPost response=new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        if(map!=null){  
            for (Entry<String, String> entry : map.entrySet()) {  

            	params.add( new BasicNameValuePair(entry.getKey(),entry.getValue()) );  
            }         
        }       
   
        try {
			response.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}  
       
 
        response.setHeader("Content-type", "application/x-www-form-urlencoded");  
        response.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  

        response.addHeader("Cookie", cookie);
 
        CloseableHttpResponse httpResponse;
		try {
			httpResponse = (CloseableHttpResponse) httpClient.execute(response);    

	        int statusCode=httpResponse.getStatusLine().getStatusCode();
	        if(statusCode == HttpStatus.SC_OK){

	        head = httpResponse.getHeaders(headers)[0].getValue().toString();
	        }		

        httpResponse.close();          
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return head;  
    }
    
//    public static void main(String [] args) throws Exception {
//    	httpGet("https://vj.viomi.com.cn/services/wares/catalog/query.json?parentCatalogId=304","");
//    }
}

    

