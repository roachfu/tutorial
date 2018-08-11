package com.roachfu.tutorial.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 服务端模拟客户端发送请求工具
 * 
 * @author roach
 *
 */

@Slf4j
public class HttpClientUtils {

	private static final String UTF_8 = "UTF-8";
	private static final String APPLICATION_JSON = "application/json";
	

	/**
	 * POST 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 */
	public static String httpPostRequest(String requestUrl, String params) {
		PostMethod post = new PostMethod(requestUrl);
		return returnResult(post, params, UTF_8);
	}

	/**
	 * POST 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            字符集
	 */
	public static String httpPostRequest(String requestUrl, String params, String charset) {
		PostMethod post = new PostMethod(requestUrl);
		return returnResult(post, params, charset);
	}

	/**
	 * GET 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpGetRequest(String requestUrl) {
		return httpGetRequest(requestUrl, UTF_8);
	}

	/**
	 * GET 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpGetRequest(String requestUrl, String charset) {
		GetMethod get = new GetMethod(requestUrl);
		return returnResult(get, charset);
	}

	/**
	 * PUT 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 */
	public static String httpPutRequest(String requestUrl, String params) {
		return httpPutRequest(requestUrl, params, UTF_8);
	}

	/**
	 * PUT 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            字符集
	 */
	public static String httpPutRequest(String requestUrl, String params, String charset) {
		PutMethod put = new PutMethod(requestUrl);
		return returnResult(put, params, charset);
	}

	/**
	 * Delete 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpDeleteRequest(String requestUrl) {
		return httpDeleteRequest(requestUrl, UTF_8);
	}

	/**
	 * Delete 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpDeleteRequest(String requestUrl, String charset) {
		DeleteMethod delete = new DeleteMethod(requestUrl);
		return returnResult(delete, charset);
	}

	/**
	 * 抽离封装POST 和 PUT 请求的公共代码
	 * 
	 * @param requestMethod
	 * @param params
	 * @param charset
	 * @return
	 */
	private static String returnResult(EntityEnclosingMethod requestMethod, String params, String charset) {

		String result = null;
		try {
			requestMethod.setRequestHeader("Content-Type", APPLICATION_JSON);
			if (params != null) {
				requestMethod.setRequestEntity(new StringRequestEntity(params, APPLICATION_JSON, charset));
			}

			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(requestMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] bytes = readInputStream(requestMethod.getResponseBodyAsStream());
			result = new String(bytes, charset);
		} catch (IOException e) {
			log.error("IO异常：", e);
		} finally {
			requestMethod.releaseConnection();
		}
		return result;
	}

	/**
	 * 抽离封装出GET, DELETE公共代码
	 * 
	 * @param requestMethod
	 * @param charset
	 * @return
	 */
	private static String returnResult(HttpMethodBase requestMethod, String charset) {

		requestMethod.setRequestHeader("Content-Type", APPLICATION_JSON);

		String result = null;
		try {
			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(requestMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] bytes = readInputStream(requestMethod.getResponseBodyAsStream());
			result = new String(bytes, charset);
		} catch (IOException e) {
			log.error("IO异常：", e);
		} finally {
			requestMethod.releaseConnection();
		}
		return result;
	}

	private static byte[] readInputStream(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		byte[] array = out.toByteArray();

		out.flush();
		out.close();
		in.close();
		return array;

	}

	public enum HttpMethod{
		POST, GET, PUT, DELETE
	}
}
