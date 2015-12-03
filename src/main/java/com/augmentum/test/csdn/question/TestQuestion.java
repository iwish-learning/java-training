package com.augmentum.test.csdn.question;

import java.io.File;
import java.io.IOException;

public class TestQuestion {

	public static void main(String[] args) throws IOException {
		//1: create folder.
		File dir = new File("D:\\ac");
		dir.mkdir();
		//2：create file in folder.
		File file = new File("D:\\ac", "b.txt");
		file.createNewFile();
	}

}
