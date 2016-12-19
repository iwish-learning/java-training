package com.java.test.json;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

/**
 * This class mainly to test how to convert between json string and java
 * object<p>
 * mainly use jackson-databind-2.x.jar, jackson-core-2.x.jar and jackson-annotations-2.x.jar<p>
 * 
 * @author Danny.Wang
 *
 */
public class TestJson {
	private static ObjectMapper mapper;
	@BeforeClass
	public static void onlyOnce() {
		mapper = new ObjectMapper();
	}
	@Test
	public void testJson() throws IOException {
		
		Cat cat = new Cat("kitty", 21);
		//convert java object to json string with pretty style.
		String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cat);
		System.out.println(jsonStr + "\n");
		
		//Convert java object to json string as normal style.
		jsonStr = mapper.writeValueAsString(cat);
		System.out.println(jsonStr + "\n");
		
		//Convert java object to another java object(fromObject and toValueObject)
		//toValueObject must have all attributes that fromObject have.
		Animal animal = mapper.convertValue(cat, Animal.class);
		System.out.println(animal + "\n");
		//if two object have no common attributes then will throw java.lang.IllegalArgumentException
		//msg:Unrecognized file
		/*Dog dog = mapper.convertValue(cat, Dog.class);
		System.out.println(dog);*/
		
		//json string convert to java object
		//notice:json string must use "\" to add ";
		ObjectReader reader = mapper.reader(Cat.class);
		Cat c = reader.readValue("{" + "\"name\"" + ":" + "\"kitty\"" + "," + "\"age\"" + ":21}");
		System.out.println(c + "\n");
		
		//json string convert to java object simply
		Cat cat1 = mapper.readValue(jsonStr, Cat.class);
		System.out.println(cat1 + "\n");
		
		//convert file(it's content is json string) to java object
		cat = mapper.readValue(new File("cat.txt"), Cat.class);
		System.out.println(cat + "\n");
		
		//convert source url content to a java object
		cat = mapper.readValue(new URL("http://source.tunnel.qydev.com/wx/pic/cat.txt"), Cat.class);
		System.out.println(cat + "\n");
		
		//write java object to file use json string style.
		mapper.writeValue(new File("D:/temp/hello.txt"), cat);
		
		
	}
	
	@Test
	public void testJson2() throws JsonProcessingException {
		Fish fish = new Fish("grass", "goldFish", "water");
		/*
		 *use @JsonView to convert between json string and java object and control
		 *what attributes should be converted 
		 */
		String jsonStr = mapper.writerWithView(Views.Normal.class).writeValueAsString(fish);
		System.out.println(jsonStr + "\n");
		
		jsonStr = mapper.writerWithView(Views.Manager.class).writeValueAsString(fish);
		System.out.println(jsonStr + "\n");
	}
	
	@Test
	public void TestJson3() throws IOException {
		//convert java object with array attribute
		Animal animal = new Animal(12.2, 21, "dogs");
		Dog[] dogs = {new Dog("danny", "shanghai"), new Dog("tom", "shanghai"), new Dog("hellen", "shanghai")};
		animal.setDogs(dogs);
		String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(animal);
		System.out.println(jsonStr);
		
		Animal a = mapper.readValue(jsonStr, Animal.class);
		System.out.println(a + "\n");
		
		//convert array to json string
		jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dogs);
		System.out.println(jsonStr);
		
		// Convert a JSON Array format to a Java List object.
		List<Dog> list = mapper.readValue(jsonStr, new TypeReference<List<Dog>>(){ });
		System.out.println(list);
		
		List<Object> list2 = jsonStrToList(jsonStr);
		System.out.println("List2:" + list2);
		
		jsonStr = mapper.writeValueAsString(list2);
		System.out.println(jsonStr + "\n");
		list = mapper.readValue(jsonStr, new TypeReference<List<Dog>>(){ });
		System.out.println(list + "\n");
		
		Cat cat = new Cat("kitty", 22);
		jsonStr = mapper.writeValueAsString(cat);
		
		//Convert a JSON to a Map
		Map<String, String> map = mapper.readValue(jsonStr, new TypeReference<Map<String, String>>() { });
		System.out.println(map);
	}
	
	@Test
	public void testJsonStrToMap() throws IOException {
		Cat cat = new Cat("kitty", 22);
		String jsonStr = mapper.writeValueAsString(cat);
		
		//Convert a JSON to a Map
		Map<String, String> map =jsonStrToMap(jsonStr, String.class, String.class);
		System.out.println(map);
	}
	
	public static List<Object> jsonStrToList(String jsonStr) {
		List<Object> list = null;
		try {
			list = mapper.readValue(jsonStr, new TypeReference<List<Object>>() {});
		} catch (IOException e) {
			return list;
		}
		return list;
	}
	public static <K, V> Map<K, V> jsonStrToMap(String jsonStr, Class<K> K, Class<V> V) {
		Map<K, V> map = null;
		try {
			JavaType type = mapper.getTypeFactory().constructParametricType(HashMap.class, K, V);
			map = mapper.readValue(jsonStr, type);
		} catch (IOException e) {
			return map;
		}
		return map;
	}
	

}
 

class Cat {
	@JsonView()
	private String name;
	
	private int age;

	public Cat() {
		super();
	}
	public Cat(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + "]";
	}
	
}

class Animal {
	private double height;
	private int age;
	private String name;
	
	private Dog dogs[];
	public Animal() {
		super();
	}
	public Animal(double height, int age, String name) {
		super();
		this.height = height;
		this.age = age;
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Dog[] getDogs() {
		return dogs;
	}
	public void setDogs(Dog[] dogs) {
		this.dogs = dogs;
	}
	@Override
	public String toString() {
		return "Animal [height=" + height + ", age=" + age + ", name=" + name + ", dogs=" + Arrays.toString(dogs) + "]";
	}
}

class Dog {
	private String masterName;
	private String homeAddr;
	public Dog(String masterName, String homeAddr) {
		super();
		this.masterName = masterName;
		this.homeAddr = homeAddr;
	}
	public Dog() {
		super();
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}
	@Override
	public String toString() {
		return "Dog [masterName=" + masterName + ", homeAddr=" + homeAddr + "]";
	}
}

class Fish {
	@JsonView(Views.Normal.class)
	private String food;
	@JsonView(Views.Normal.class)
	private String name;
	@JsonView(Views.Manager.class)
	private String liveEnvrionment;
	
	public Fish() {
		super();
	}
	public Fish(String food, String name, String liveEnvrionment) {
		super();
		this.food = food;
		this.name = name;
		this.liveEnvrionment = liveEnvrionment;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLiveEnvrionment() {
		return liveEnvrionment;
	}
	public void setLiveEnvrionment(String liveEnvrionment) {
		this.liveEnvrionment = liveEnvrionment;
	}
	@Override
	public String toString() {
		return "Fish [food=" + food + ", name=" + name + ", liveEnvrionment=" + liveEnvrionment + "]";
	}
	
}

class Views {
	public static class Normal{};
	public static class Manager{};
}