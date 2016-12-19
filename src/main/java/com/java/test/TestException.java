package com.java.test;

public class TestException {
	public static void main(String[] args) {
		try {
			int i = 0;
			int j = 10;
			int res = j/i;
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=========================");
	}
}
