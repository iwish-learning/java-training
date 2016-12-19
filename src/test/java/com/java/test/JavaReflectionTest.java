package com.java.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

import com.java.test.reflect.People;
import com.java.test.reflect.TCase;

public class JavaReflectionTest{
	
	/**
	 * Mainly to test class's field.
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void test1() throws Exception {
		Class<?> clazz = People.class;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			//1.Get the field modifier name(private, protected, default, public).
			//If modifier is default than the result is empty.
			int mod = field.getModifiers();
			String modifierName =Modifier.toString(mod);
			System.out.print(modifierName + "\t");
			
			//2.Get the field name.
			System.out.print(field.getName() + "\t");
			
			//3.Get the field type.
			System.out.print(field.getType() + "  ");
			
			//4.Get the field value.
			field.setAccessible(true);
			System.out.print(field.get(clazz.newInstance()) + "  ");
			
			//5.Get the field annotation
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				String annotationName = annotation.getClass().getName();
				System.out.print(annotationName + "");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * Mainly to test class's methods.
	 */
	@Test
	public void test2() throws Exception{
		Class<?> clazz = People.class;
		//Use getMethods() can get People super class's methods that modified by public.
		Method[] methods1 = clazz.getMethods();
		for (Method method : methods1) {
			System.out.println(method);
		}
		
		System.out.println();
		
		//Use getDeclaredMethods() just can get People itself method.
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			//1.Get method modifier name.
			int mod = method.getModifiers();
			String methodName = Modifier.toString(mod);
			System.out.print(methodName + "\t");
			
			//2.Get method name.
			System.out.print(method.getName() + "  ");
			
			//3.Get method return type.
			System.out.print(method.getReturnType() + "  ");
			
			//4.Get method parameters count.
			System.out.print(method.getParameterCount() + "  ");
			
			//5.invoke the method
			
			if (method.getParameterCount() >0) {
				Class<?>[] paramsType = method.getParameterTypes();
				/*Parameter[] params = method.getParameters();
				for (Parameter parameter : params) {
					System.out.print(parameter + "  "); 
				}*/
				Object[] objs = new Object[method.getParameterCount()];
				System.out.println(paramsType[0].getName());
				for (int i = 0; i < method.getParameterCount(); i++) {
					String parameterType = paramsType[i].getName();
					
					if (parameterType.equals("java.lang.String")) {
						objs[i] = "This parameter type is java.lang.String";
					} else if (parameterType.equals("int")) {
						objs[i] = 5;
					} else if (parameterType.equals("long")) {
						objs[i] = 6;
					} else if (parameterType.equals("float")) {
						objs[i] = 2.0f;
					} else if (parameterType.equals("byte")) {
						objs[i] = (byte)127;
					} else if (parameterType.equals("char")) {
						objs[i] = 'a';
					} else if (parameterType.equals("boolean")) {
						objs[i] = true;
					} else if (parameterType.equals("double")) {
						objs[i] = 3.3;
					} 
				}
				
				method.invoke(clazz.newInstance(), objs);
			} else {
				method.invoke(clazz.newInstance());
			}
			
			System.out.println();
		}
	}
	
	/**
	 * Mainly to test class's constructor
	 */
	@Test
	public void test3() {
		Class<?> clazz = People.class;
		//Get constructor that modified by public
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		System.out.println("=====================================");
		//Get all declared constructors.
		Constructor<?>[] cons = clazz.getDeclaredConstructors();
		for (Constructor<?> constructor : cons) {
			System.out.println(constructor);
		}
	}

	/**
	 * Mainly to test class's superClass
	 */
	@Test
	public void test4() {
		Class<?> clazz = People.class;
		//1.Get the no generic superClass
		Class<?> superClazz = clazz.getSuperclass();
		System.out.println(superClazz);
		
		//2.Get generic superClass
		Type superGenericClazz = clazz.getGenericSuperclass();
		String typeName = superGenericClazz.getTypeName();
		System.out.println(superGenericClazz + "  " + typeName);
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//3.Get the generic type from superClass
		ParameterizedType pt = (ParameterizedType)superGenericClazz;
		Type[] actualTypeArguments = pt.getActualTypeArguments();
		for (Type type : actualTypeArguments) {
			System.out.println(type);
		}
	}

	/**
	 * Mainly to test calss's implemented interface.
	 */
	@Test
	public void test5() {
		Class<?> clazz = People.class;
		//1.Get the no generic interface.
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> class1 : interfaces) {
			System.out.println(class1);
		}
		System.out.println("==========================");
		//2.Get interface it has generic.
		Type[] genericInterfaces = clazz.getGenericInterfaces();
		for (Type type : genericInterfaces) {
			System.out.println(type);
			//3.Get the generic type of interface.
			ParameterizedType pt = null;
			try {
				pt = (ParameterizedType)type;
				Type[] actualTypeArguments = pt.getActualTypeArguments();
				for (Type type2 : actualTypeArguments) {
					System.out.println(type2);
				}
			} catch (Exception e) {
				System.out.println(type + "  this interface has no genric");
			}
			
		}
		
	}

	/**
	 * Mainly to test class's annotations.
	 */
	@Test
	public void test6() {
		Class<?> clazz = People.class;
		//1.Get Annotation modified by public
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
		}
		System.out.println("=================================");
		Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
		for (Annotation annotation : declaredAnnotations) {
			System.out.println(annotation);
		}
		
		//getComponentType is special method for Array.
		int[] arr = new int[5]; 
		Class<?> componentType = arr.getClass().getComponentType();
		System.out.println(componentType);
	}

	/**
	 * Mainly to test class's package.
	 */
	@Test
	public void test7() {
		Class<?> clazz = People.class;
		Package package1 = clazz.getPackage();
		System.out.println(package1);
		//获得内部类
		Class<?>[] classes = clazz.getDeclaredClasses();
		for (Class<?> class1 : classes) {
			System.out.println(class1);
		}
	}
	
	/**
	 * Mainly to test some boolean methods.
	 */
	@Test
	public void test8() {
		Class<?> clazz = People.class;
		System.out.println(clazz.isInstance(clazz));
		System.out.println(clazz.isAnnotation());
		System.out.println(clazz.isAnnotationPresent(TCase.class));
		System.out.println(clazz.isLocalClass());
		System.out.println(clazz.isInterface());
		System.out.println(clazz.isEnum());
		System.out.println(clazz.isAnonymousClass());
		//判断类的类型是否一致。作用和instanceof差不多
		System.out.println(clazz.isAssignableFrom(clazz));
	}

	/**
	 * Mainly to test methods exception.
	 */
	@Test
	public void test9() {
		Class<?> clazz = People.class;
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			Class<?>[] exceptionTypes = method.getExceptionTypes();
			for (int i = 0; i < exceptionTypes.length; i++) {
				System.out.println(exceptionTypes[i].getName());
			}
		}
	}
}
