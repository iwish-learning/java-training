package com.augmentum.test.csdn.question;

import org.junit.Test;

public class TestJavaOperator {

	@Test
	public void testJavaTypeOperator() {
		int i = 5;
		int j = 6;
		//integer change to binary(2)
		System.out.println(Integer.toBinaryString(i) + " toBinary");
		
		//integer change to octal(8)
		System.out.println(Integer.toOctalString(11) + " toOctal");
		
		//integer change to Hex(16)
		System.out.println(Integer.toHexString(17) + " toHex");
		
		//5 = 101, 6 = 110
		//  1 0 1
		//  -----
		//  1 1 0
		//= 1 1 1 = 7(十进制)
		
		int res = i | j;
		System.out.println("i | j = " + res);
		
		
		//5 = 101, 6 = 110
		//  1 0 1
		//  -----
		//  1 1 0
		//= 1 0 0 = 4(十进制)
		
		int res2 = i & j;
		
		System.out.println("i & j = " + res2);
		System.out.println(0xFF);
	}
	
	@Test
	public void testJavaLeftAndRightShift() {
		int i = -2;
		//when result not equals 1,the i >> 1 means i/2;
		System.out.println("i >> 1 ===> i right shift 1 byte and result = " + (i >> 1));
		
		//when result not equals 0,the i >> 1 means i/2;
		System.out.println("i << 1 ===> i left shift 1 byte and result = " + (i << 1));
		
		int j = 0;
		
		System.out.println("j >> 1 ===> i right shift 1 byte and result = " + (j >> 1));
		
		System.out.println("j << 1 ===> i left shift 1 byte and result = " + (j << 1));
		
		int k = 3;
		
		System.out.println("k >> 1 ===> i right shift 1 byte and result = " + (k >> 1));
		
		System.out.println("k << 1 ===> i left shift 1 byte and result = " + (k << 1));
	}

}
