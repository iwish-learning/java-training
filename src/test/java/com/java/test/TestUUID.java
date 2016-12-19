package com.java.test;

import java.util.UUID;

import org.junit.Test;

public class TestUUID {

	@Test
	public void testUUID() {
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
	}

}
