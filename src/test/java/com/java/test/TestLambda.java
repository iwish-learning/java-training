package com.java.test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.junit.Test;

public class TestLambda {
	/**
	 * lambda表达式能够简化语法。
	 * 能够替代Java中的匿名函数，这样做的好处解决了Java中匿名函数的冗余，因为一般的匿名函数属于重量级的操作，也就是说很多行代码，其中只有一两行是
	 * 有真正作用的
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Test
	public void testLambda() throws Exception {

		// traditional method
		File dir = new File("/an/dir/");
		   FileFilter directoryFilter = new FileFilter() {
		      public boolean accept(File file) {
		         return file.isDirectory();
		      }
		};
		dir.listFiles(directoryFilter);
		
		//lambda method
		File dir1 = new File("/an/dir/");
		FileFilter directoryFilter1 = (File f) -> f.isDirectory();
		File[] dirs = dir1.listFiles(directoryFilter1);
		
		File dir2 = new File("/an/dir/");
		File[] dirs2 = dir2.listFiles((File f) -> f.isDirectory());
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("tom");
		arr.add("danny");
		arr.add("kity");
		arr.add("hellen");
		arr.add("jack");
		for (String string : arr) {
			if (string.contains("danny")) {
				System.out.println("danny");
			}
		}
		
		int ssum = 0;
		arr.forEach(e -> {if (e.contains("danny")) {
			System.out.println("danny");
		}});
		
		ArrayList<String> list = new ArrayList<>();
		list.add("danny");
//		list.forEach(c -> {list.add(c);});
		list.forEach(e -> {System.out.println(e);});
		int sum = list.stream()
	              .mapToInt(f -> f.length())
	              .sum();
		
		sum = list.stream()
	              .mapToInt(e -> e.length())
	              .reduce(0 , (x, y) -> x + y);
		
		System.out.println("stream : " + sum);

		//没有参数，有返回值
		Callable<String> c = () ->  "get Value";
		String returnVal = c.call().intern();
		System.out.println(returnVal);
		
		//有两个参数的方法，并且有返回值
		Comparator<String> c1 = (s1, s2) -> s1.compareToIgnoreCase(s2);
		System.out.println("comparator res: " + c1.compare("danny8", "danny"));
		
		//没有参数有执行方法体
		Runnable r1 = () -> { System.out.println("test"); };
		r1.run();
		
		//有指定类型的参数和返回值
		BiFunction<Integer, Integer, Integer> bif = (Integer t, Integer u) -> t + u;
		int sum1 = bif.apply(5, 6);
		System.out.println(sum1);
		
	}



		
	@Test
	public void testLambda2() {

		Person[] people = {new Person("tom", 21), new Person("danny", 21), new Person("tom4", 21), new Person("milly", 21)};
		for (Person person : people) {
			System.out.println(person);
		}
		System.out.println();
		
		Comparator<Person> byName = Comparator.comparing(p -> p.getName());
		Arrays.sort(people, byName);
		for (Person person : people) {
			System.out.println(person);
		}
		System.out.println();
		
		//按照年龄排序，并且用方法引用来代替lambda方法Person::getAge
		Comparator<Person> byAge = Comparator.comparing(Person::getAge);
		Arrays.sort(people, byAge);
		for (Person person : people) {
			System.out.println(person);
		}
		System.out.println();
	}
	
	@Test
	public void testMethodReference() {
		String str = "test";
		Function<String, String> upperfier = String::toUpperCase;
		str = upperfier.apply(str);
		System.out.println(str);
		
		List<Person> people = new ArrayList<Person>();
		people.add(new Person("tom", 22));
		people.add(new Person("danny", 15));
		people.add(new Person("hellen", 26));
		people.add(new Person("jack", 30));
		//按照姓名排序
		people.sort(Comparator.comparing(Person::getName));
		people.forEach(e -> {System.out.println(e);});
		

	}
	
	public void testCommonClassOfJavaFunctionPackage() {
		//test function method
		Function<Person, String> fun = (p) -> {
			return p.getName();};
		System.out.println(fun.apply(new Person("rose", 99)));
		
		//test predicate method
		Predicate<String> match = s -> s.equalsIgnoreCase("danny");
		System.out.println(match.test("dann"));
		
		//test consumer method
		Consumer<Person> cs = (p) -> {
			p.setAge(44);
		};
		Person p = new Person("json", 22);
		cs.accept(p);
		System.out.println(p.getAge());
		
		//test UnaryOperator class method
		UnaryOperator<Person> uo = (per) -> {
			per.setAge(55);
			per.setName("tom");
			return per;
		};
		System.out.println(uo.apply(new Person()));
		
		//test BinaryOperator
		BinaryOperator<Person> bo = (p1, p2) -> {
			if (p1.equals(p2)) {
				System.out.println("p1 " + p1);
				return p1;
			} else {
				System.out.println("p2 " + p1);
				return p2;
			}
		};
		System.out.println(bo.apply(uo.apply(new Person()), uo.apply(new Person())));
		
		//test Bifunction
		BiFunction<Person, Person, Boolean> bf = (p1, p2) -> {
			if (p1.equals(p2)) {
				return true;
			} else {
				return true;
			}
		};
		System.out.println(bf.apply(uo.apply(new Person()), uo.apply(new Person())));
		
		//test BiConsumer
		BiConsumer<Person, String> bc = (per, s) -> {
			per.setName(s);
		};
		bc.accept(p, "danny");
		System.out.println(p.getName());
	}
}
class Person {
    private   String name;
    private   int age;
    
	public Person() {
		 
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
