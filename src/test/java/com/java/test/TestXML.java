package com.java.test;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

public class TestXML {

	@Test
	public void testXStream() {
		// XStream
	}
	
	@Test
	public void testXml() {
		XML xml = new XML();
		String xmlStr = "<pe>" +
						"<name>tom" +
						"</name>" +
						"<age>24" +
						"</age>" +
						"</pe>";
		JSONObject jsonObject = XML.toJSONObject(xmlStr);
//		String name = (String) jsonObject.get("name");
		Object obj = JSONObject.wrap(jsonObject);
		System.out.println(jsonObject);
		System.out.println(obj);
		System.out.println(XML.stringToValue(xmlStr));
	}

}
class Pe {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Pe [name=" + name + "]";
	}
	
}