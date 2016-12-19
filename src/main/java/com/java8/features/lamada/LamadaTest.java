package com.java8.features.lamada;

public class LamadaTest {
	/**
	 * 以下是lambda表达式的重要特征:
	 * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
	 * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
	 * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
	 * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值
	 * @param args
	 */
	
	public static void main(String[] args) {
		LamadaTest lamada = new LamadaTest();
		// 有参数类型
        MathOperation addition = (int a, int b) -> a + b;

        // 无参数类型
        MathOperation subtraction = (a, b) -> a - b;

        // 有花括号，有return关键字
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 无花括号，无return关键字，单一表达式情况
        MathOperation division = (int a, int b) -> a / b;

        // MathOperation调用示例
        System.out.println("10 + 5 = " + lamada.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lamada.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + lamada.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lamada.operate(10, 5, division));

        // 有括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 无括号，单个参数情况
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        // GreetingService调用示例
        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");
        
        //有括号， 无参情况
        Runnable runTest = () -> System.out.println("Running");
        //Runnable调用示例
        runTest.run();
    }

	/**
	 * lamada 表达式需要返回函数式接口 
	 * 函数式接口：拥有 @FunctionalInterface 注解，并且只拥有一个抽象方法
	 * java解释器默认把只有一个抽象方法的接口认定为函数式接口
	 */
    // 内部接口
    interface MathOperation
    {
        int operation(int a, int b);
    }

    interface GreetingService
    {
        void sayMessage(String message);
    }

    interface Runnable
    {
        void run();
    }
    
    private int operate(int a, int b, MathOperation mathOperation)
    {
        return mathOperation.operation(a, b);
    }
}
