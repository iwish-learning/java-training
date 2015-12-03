package com.augmentum.test.proxy;

///////////////////////////////////////////////////////////////////////////
//静态代理的坏处就是：被代理类和代理类都得继承同一套具体的接口，也就是说代理类和被代理类
//是一一对应的，不是动态的生成一个代理类，当我们想要将被代理类的任务交给代理类，那么就需要定义一个
//对应的代理类，这样的话不够灵活。
//好处：比较容易理解，并且掌握
///////////////////////////////////////////////////////////////////////////

//defined common interface.
//静态代理时，被代理对象和代理对象都要实现这个接口
interface ClothFactory {
	String showMes();
}

//被代理的对象
class NickFactory implements ClothFactory{

	public String showMes() {
		return "This is static proxy case.";
	}
	
}

//创建代理对象
class ProxyObjet implements ClothFactory{
	//利用的是公共的接口
	ClothFactory obj;
	//通过代理对象的构造方法将被代理对象注入到代理对象
	public ProxyObjet(ClothFactory obj) {
		this.obj = obj;
	}
	
	public String showMes() {
		//在执行被代理对象之前做一些事情
		System.out.println("before invoke proxy class");
		return obj.showMes();
		//在执行被代理对象方法之后做一些事情
	}
	
}

public class TestProxy {
	public static void main(String[] args) {
		//1. 创建被代理对象
		ClothFactory nick = new NickFactory();
		
		//2. 创建代理对象
		ProxyObjet proxy = new ProxyObjet(nick);
		
		//3. 通过调用代理对象的方法，来执行被代理对象的方法
		String msg = proxy.showMes();
		System.out.println(msg);
		
	}
}
