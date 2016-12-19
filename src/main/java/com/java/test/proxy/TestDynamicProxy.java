package com.java.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//公共的接口，代理类和被代理类都要实现这个接口
interface Subject {
	void action();
}

//定义被代理类
class RealSubject implements Subject {

	public void action() {
		System.out.println("this is proxy class.");
	}
	
}

//实现动态代理类

class MyInvocationHandler implements InvocationHandler {
	Object obj;//被代理对象的神明
	
	//该方法有两个作用，1.实例化被代理类。
	//2.返回代理类对象，通过Proxy.newProxyInstance()方法。
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
	
	//当生成的代理类调用被代理类的方法的时候执行这个invoke方法。
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnVal = method.invoke(obj, args);
		return returnVal;
	}
	
}
public class TestDynamicProxy {	
	
	public static void main(String[] args) {
		RealSubject real = new RealSubject();
		NickFactory nick = new NickFactory();
		MyInvocationHandler hand = new MyInvocationHandler();
		Subject obj = (Subject)hand.blind(real);
		obj.action();
		System.out.println();
		ClothFactory fatory = (ClothFactory)hand.blind(nick);
		String msg = fatory.showMes();
		System.out.println(msg);
	}
	
}
