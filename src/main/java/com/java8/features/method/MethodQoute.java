package com.java8.features.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodQoute {
	/**
	 * 方法应用，使得java8和java7的编程风格出现不同
	 * 方法引用通过方法的名字来指向一个方法。
	 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
	 * 方法引用使用一对冒号(::)。
	 */
	public static void main(String[] args) {
		 List<String> names = new ArrayList<String>();
	        
	      names.add("Google");
	      names.add("Runoob");
	      names.add("Taobao");
	      names.add("Baidu");
	      names.add("Sina");
	        
	      names.forEach(System.out::println);
	      
	      //构造器引用
	      final Car car = Car.create( Car::new );
	      final List< Car > cars = Arrays.asList( car );
	      
	      //静态方法引用
	      cars.forEach( Car::collide );
	      
	      //特定类的任意对象的方法引用
	      cars.forEach( Car::repair );
	      
	      //特定对象的方法引用
	      final Car police = Car.create( Car::new );
	      cars.forEach( police::follow );
	}
	
	/**
	 * 方法引用java中支持4种不同的引用
	 * 1. 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
	 * 2. 静态方法引用：它的语法是Class::static_method，实例如下：
	 * 3. 特定类的任意对象的方法引用：它的语法是Class::method实例如下：
	 * 4. 特定对象的方法引用：它的语法是instance::method实例如下：
	 */
	static class Car {
	    public static Car create( final Supplier< Car > supplier ) {
	        return supplier.get();
	    }              
	        
	    public static void collide( final Car car ) {
	        System.out.println( "Collided " + car.toString() );
	    }
	        
	    public void follow( final Car another ) {
	        System.out.println( "Following the " + another.toString() );
	    }
	        
	    public void repair() {   
	        System.out.println( "Repaired " + this.toString() );
	    }
	}
	
	
}
