package com.java.test.reflect;

@SuppressWarnings("serial")
@TCase
@ClassAnnotation
public class People extends Animal<String, Integer> implements Uu, MyInterface<String> {

	@TCase
	private String name;
	
	public String password;
	
    long id;
	
	private boolean isDead;
	
	private char sex;
	
	private int age;
	
	private double height;
	
	private float feetSize;
	
	private byte ccc;

	public People() {
		super();
	}

	public People(String name, byte ccc) {
		super();
		this.name = name;
		this.ccc = ccc;
	}

	@SuppressWarnings("unused")
	private People(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public float getFeetSize() {
		return feetSize;
	}

	public void setFeetSize(float feetSize) {
		this.feetSize = feetSize;
	}

	public byte getCcc() {
		return ccc;
	}

	public void setCcc(byte ccc) {
		this.ccc = ccc;
	}
	//some test method.===================================================
	public void showMes(String msg) {
		System.out.println(msg);
	}
	
	@IsMethodAnnotation
	public void showMsg() throws Exception{
		System.out.println("this is no agrs showMsg() method!");
	}
	
	public String returnVal() {
		return "this is no args returnVal() method.";
	}
	
	public String returnVal(String msg) {
		return msg;
	}
	
	//Inner class
	class Man {
		private String job;

		public String getJob() {
			return job;
		}

		public void setJob(String job) {
			this.job = job;
		}
		
	}
}
