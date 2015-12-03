package com.augmentum.test;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

public class JavaURLTest {
	@Test
	public void testURL() throws Exception {
		URL url = new URL("http://7xoclc.com1.z0.glb.clouddn.com/test.jpg");
		URLConnection openConnection = url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		String contentType = openConnection.getContentType();
		String file1 = url.getFile();
		File file = new File(file1.substring(1, file1.length()));
		FileUtils.copyInputStreamToFile(inputStream, file);
		System.out.println(file1);
		System.out.println(file.getTotalSpace());
		System.out.println(file.getName());
		System.out.println(contentType);
		file.delete();
	}

	@Test
	public void testURI() throws Exception {
		URI uri = new URI("http://7xoclc.com1.z0.glb.clouddn.com/test.jpg");
		File file = new File(uri);
		System.out.println(file.getName());
		System.out.println(uri.getScheme());
		System.out.println(uri.getHost());
		System.out.println(uri.getPort());// default value is -1
		System.out.println(uri.getFragment());
		System.out.println(uri.getPath());
		System.out.println(uri.getQuery());
		System.out.println(uri.getRawUserInfo());
	}

	/**
	 * HttpClient 可以解析https://。。。类型的地址
	 * URL是不能解析这种格式的
	 */
	@Test
	public void testHttpClinet4_5() {

		String url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ZOEZ-48tyjabq4uTZeLhosQHDECOxuNQvA-WVNlLqcUlH_PeRNJME9KTtyGC2D3X0w0Bug_1QNG3IucGzf9I4w&media_id=1-DlyGe_rcabWVbYvQqICctSabgyh3CDMtkLtApdxTPAZrniEKCTXrbODAvmWWvzPDZd3hZiKrl8kVUfeFFqHfA";
		Request req = Request.Get(url);
		Content content;
		String type = "";
		File file = null;
		try {
			content = req.execute().returnContent();
			String mimeType = content.getType().getMimeType();
			type = mimeType.substring(0, mimeType.indexOf("/"));
			String suffix = mimeType.substring(mimeType.indexOf("/") + 1, mimeType.length());
			InputStream asStream = content.asStream();
			file = new File(UUID.randomUUID().toString() + "." + suffix);
			FileUtils.copyInputStreamToFile(asStream, file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if ("".equals(type) || file == null) {
			// TODO
		}
		file.delete();
	}
	
	@Test
	public void testWxGetTempMaterial() throws Exception{
		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ZOEZ-48tyjabq4uTZeLhosQHDECOxuNQvA-WVNlLqcUlH_PeRNJME9KTtyGC2D3X0w0Bug_1QNG3IucGzf9I4w&media_id=1-DlyGe_rcabWVbYvQqICctSabgyh3CDMtkLtApdxTPAZrniEKCTXrbODAvmWWvzPDZd3hZiKrl8kVUfeFFqHfA";
		URL url = new URL(urlStr);
		URLConnection openConnection = url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		String contentType = openConnection.getContentType();
		String file1 = url.getFile();
		File file = new File(file1.substring(1, file1.length()));
		FileUtils.copyInputStreamToFile(inputStream, file);
		System.out.println(file1);
		System.out.println(file.getTotalSpace());
		System.out.println(file.getName());
		System.out.println(contentType);
		file.delete();
	}
}
