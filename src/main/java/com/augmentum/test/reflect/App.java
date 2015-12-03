package com.augmentum.test.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class App {
    public static void main( String[] args ) throws Exception {
//    	test1(new Peopel());
//    	test2();
//    	test3();
    	test4();
    }
    
    public static void test1(People people) throws Exception {
    	//class name
    	String name = People.class.getName();
    	String name2 = people.getClass().getName();
    	System.out.println(name);
    	System.out.println(name2);
    	//get all methods from class include this class's superclass
    	Method[] methods = People.class.getMethods();
    	for (Method method : methods) {
			System.out.println(method.getName());
		}
    	Field[] fff = people.getClass().getDeclaredFields();
    	
    	//get declaredFields
    	System.out.println("=======================");
    	for (Field field : fff) {
    		//set accessible true, enable get method getValue.
    		field.setAccessible(true);
    		//Though the field.get() method to get the field value.
    		Object obj = field.get(people);
    		Class<?> cl = field.getType();
    		System.out.println(cl);
    		System.out.println(obj);
			System.out.println(field.getName());
		}
    	
    	Field[] fields = people.getClass().getFields();
    	System.out.println("=============");
    	for (Field field : fields) {
    		System.out.println(field.getName());
		}
    }
    
    public static void test2() throws IllegalArgumentException, IllegalAccessException {
    	Field[] fields = UserBean.class.getSuperclass().getDeclaredFields();
    	UserBean bean = new UserBean();
    	for (Field field : fields) {
    		field.setAccessible(true);
			if (field.isAnnotationPresent(TCase.class)) {
				System.out.println(field.get(bean));
				System.out.println(field.getName());
				System.out.println("====================================== ");
			}
		}
    }

    public static void test3() throws Exception {
    	//1. Get Class through Class.forname();
    	String className = "com.augmentum.test.test.Peopel";
    	Class<?> clazz = Class.forName(className);
    	System.out.println(clazz);
    	
    	//2. Get Class through Object.class;
    	Class<?> clazz1 = People.class;
    	System.out.println(clazz1);
    	
    	//3. Get Class through Object instance.
    	People p = new People();
    	Class<?> clazz2 = p.getClass();
    	System.out.println(clazz2);
    	
    	//4. Get Class through ClassLoader();
    	App app = new App();
    	app.testClassLoader(className);
    }
    
    public  void testClassLoader(String className) throws Exception {
    	ClassLoader loader = this.getClass().getClassLoader();
    	Class<?> clazz = loader.loadClass(className);
    	System.out.println(clazz);
    }
    
    //ClassLoader 
    public static void test4() throws Exception {
    	//System ClassLoader 
    	ClassLoader loader = ClassLoader.getSystemClassLoader();
    	System.out.println(loader);//Result is System ClassLoader
    	
    	//Extension ClassLoader
    	ClassLoader loader1 = loader.getParent();
    	System.out.println(loader1);//Result is Extension ClassLoader
    	
    	//Boot ClassLoader, it can not be get.
    	ClassLoader loader2 = loader1.getParent();
    	System.out.println(loader2);//Result is null that means Boot ClassLoader
    	
    	//User's defined class use System ClassLoader to loader .class file.
    	//let us see People class use which classLoader to loader .class file.
    	Class<?> clazz = People.class;
    	ClassLoader loader3 = clazz.getClassLoader();
    	System.out.println(loader3);//Result is System ClassLoader.
    	
    	//System's class such as Object
    	//System's class use Boot classLoader to loader .class file.
    	String className = "java.lang.Object";
    	Class<?> clazz1 = Class.forName(className);
    	ClassLoader loader4 = clazz1.getClassLoader();
    	System.out.println(loader4);//Result is Boot ClassLoader
    	
    	//Use classLoader to read resource such as .properties, .xml file and so on.
    	//Use classLoader can read file in some package.
    	ClassLoader loader5 = new App().getClass().getClassLoader();
    	InputStream ins = loader5.getResourceAsStream("com\\augmentum\\test\\test\\db.properties");
    	Properties pro = new Properties();
    	pro.load(ins);
    	System.out.println(pro.getProperty("jdbc.username"));
    	System.out.println(ins);
    	System.out.println("======================================");
    	
    	//Just use class.getResourceAsStream() method to read files.
    	Class<?> clazz2 = App.class;
    	InputStream ins2 = clazz2.getResourceAsStream("mongon.properties");
    	pro.load(ins2);
    	System.out.println(pro.getProperty("username"));
    	
    	//Use normal method to read file.
    	//this method just can read a file which location in project.if file in some package this 
    	//method can not read the file.
    	FileInputStream fis = new FileInputStream(new File("db1.properties"));
    	pro.load(fis);
    	System.out.println(pro.getProperty("username"));
    }

    
}
