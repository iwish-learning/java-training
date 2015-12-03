package com.augmentum.test.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class TestFileReder {

	@Test
	public void testFile() throws IOException {
		//1: create folder.
		File dir = new File("D:\\ac");
		dir.mkdir();
		//2：create file in folder.
		File file = new File("D:\\ac", "b.txt");
		file.createNewFile();
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testFileReder() throws IOException {
		FileReader reader = new FileReader("D:\\temp\\Desert.jpg");
		
		int read = reader.read();

		System.out.println(read);
		System.out.println(reader.toString());
		System.out.println(reader.ready());
		
		System.out.println("=============================================");
		File file = new File("D:\\temp\\Desert.jpg");
		String name = file.getName();
		
		String absolutePath = file.getAbsolutePath();
		
		long totalSpace = file.getTotalSpace();
		
		String path = file.getPath();
		
		System.out.println(path);
		
		System.out.println(totalSpace);
		
		System.out.println(absolutePath);
		System.out.println(name);
		
		
		System.out.println("=============================================");
		File absoluteFile = file.getAbsoluteFile();
		String name2 = absoluteFile.getName();
		String absolutePath2 = absoluteFile.getAbsolutePath();
		System.out.println(absolutePath2);
		System.out.println(name2);
	}

}
