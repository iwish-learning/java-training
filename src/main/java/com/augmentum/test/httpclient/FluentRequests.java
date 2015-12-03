package com.augmentum.test.httpclient;

import java.io.InputStream;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;  

public class FluentRequests {

	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		// 执行一个GET请求,同时设置Timeout参数并将响应内容作为String返回
		Request request = Request.Get("http://7xoclc.com1.z0.glb.clouddn.com/test.jpg");
		Response response = request.execute();
		//返回的是百度的主页面的html字符串
		Content content = response.returnContent();
		ContentType type = content.getType();
		String typeStr = type.getMimeType();
		System.out.println(typeStr.substring(0, typeStr.indexOf("/")));
		String[] t = typeStr.split("/");
		System.out.println(t[0]);
		System.out.println(typeStr);
		InputStream asStream = content.asStream();
		//System.out.println(contentStr);
		System.out.println();
		
		/*// 以Http/1.1版本协议执行一个POST请求，同时配置Expect-continue handshake达到性能调优,
		// 请求中包含String类型的请求体并将响应内容作为byte[]返回
		byte[] res2 = Request.Post("http://blog.csdn.net/vector_yi").useExpectContinue().version(HttpVersion.HTTP_1_1)
				.bodyString("Important stuff", ContentType.DEFAULT_TEXT).execute().returnContent().asBytes();
		System.out.println(res2);
		// 通过代理执行一个POST请求并添加一个自定义的头部属性,请求包含一个HTML表单类型的请求体
		// 将返回的响应内容存入文件
		Request.Post("http://www.baidu.com").addHeader("X-Custom-header", "stuff")
				.viaProxy(new HttpHost("myproxy", 8080))
				.bodyForm(Form.form().add("username", "vip").add("password", "secret").build()).execute()
				.saveContent(new File("result.dump"));*/

	}
	
	public static void test() {
		
	}

}
