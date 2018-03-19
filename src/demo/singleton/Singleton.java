package demo.singleton;

/**
 * 单例模式
 * 2.1 模式说明
 * 实现1个类只有1个实例化对象 & 提供一个全局访问点
 * 2.2 作用（解决的问题）
 * 保证1个类只有1个对象，降低对象之间的耦合度
 * 
 * @author shaw
 * 
 */
public class Singleton {
}

// 饿汉式
// JVM在类的初始化阶段(即 在Class被加载后、被线程使用前)，会执行类的初始化
// 在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化
class SingletonHungry {

	// 1. 加载该类时，单例就会自动被创建
	private static SingletonHungry ourInstance = new SingletonHungry();

	// 2. 构造函数 设置为 私有权限
	// 原因：禁止他人创建实例
	private SingletonHungry() {
	}

	// 3. 通过调用静态方法获得创建的单例
	public static SingletonHungry newInstance() {
		return ourInstance;
	}
}

// 懒汉式
// 与 饿汉式 最大的区别是：单例创建的时机
// 饿汉式：单例创建时机不可控，即类加载时 自动创建 单例
// 懒汉式：单例创建时机可控，即有需要时，才 手动创建 单例

// （基础实现）
class SingletonOne {
	// 1. 类加载时，先不自动创建单例
	// 即，将单例的引用先赋值为 Null
	private static SingletonOne ourInstance = null;

	// 2. 构造函数 设置为 私有权限
	// 原因：禁止他人创建实例
	private SingletonOne() {
	}

	// 3. 需要时才手动调用 newInstance（） 创建 单例
	public static SingletonOne newInstance() {
		// 先判断单例是否为空，以避免重复创建
		if (ourInstance == null) {
			ourInstance = new SingletonOne();
		}
		return ourInstance;
	}
}

// 懒汉式的改进
// 原理
// 使用同步锁 synchronized锁住 创建单例的方法 ，防止多个线程同时调用，从而避免造成单例被多次创建
// 即，getInstance（）方法块只能运行在1个线程中
// 若该段代码已在1个线程中运行，另外1个线程试图运行该块代码，则 会被阻塞而一直等待
// 而在这个线程安全的方法里我们实现了单例的创建，保证了多线程模式下 单例对象的唯一性
class SingletonTwo {
	// 类加载时，先不自动创建单例即，将单例的引用先赋值为 Null
	private static SingletonTwo instance = null;

	// 构造函数 设置为 私有权限 原因：禁止他人创建实例
	private SingletonTwo() {
	}

	public static SingletonTwo getInstance() {
		// 加入同步锁
		synchronized (SingletonTwo.class) {
			// 先判断单例是否为空，以避免重复创建
			if (instance == null)
				instance = new SingletonTwo();
		}
		return instance;
	}
}

//说明
//校验锁1：第1个if
//作用：若单例已创建，则直接返回已创建的单例，无需再执行加锁操作
//即直接跳到执行 return ourInstance

//校验锁2：第2个 if 
//作用：防止多次创建单例问题
//原理
// 1. 线程A调用newInstance()，当运行到②位置时，此时线程B也调用了newInstance()
// 2. 因线程A并没有执行instance = new Singleton();，此时instance仍为空，因此线程B能突破第1层 if 判断，运行到①位置等待synchronized中的A线程执行完毕
// 3. 当线程A释放同步锁时，单例已创建，即instance已非空
// 4. 此时线程B 从①开始执行到位置②。此时第2层 if 判断 = 为空（单例已创建），因此也不会创建多余的实例
class SingletonThree {
	private static SingletonThree ourInstance = null;

	private SingletonThree() {
	}

	public static SingletonThree newInstance() {
		// 加入双重校验锁
		// 校验锁1：第1个if
		if (ourInstance == null) {
			// ①
			synchronized (SingletonThree.class) { 
				// ②
				// 校验锁2：第2个 if
				if (ourInstance == null) {
					ourInstance = new SingletonThree();
				}
			}
		}
		return ourInstance;
	}
}


//4. 静态内部类
//原理
//根据 静态内部类 的特性，同时解决了按需加载、线程安全的问题，同时实现简洁
//在静态内部类里创建单例，在装载该内部类时才会去创建单例
//线程安全：类是由 JVM加载，而JVM只会加载1遍，保证只有1个单例

//调用过程说明：
// 1. 外部调用类的newInstance() 
// 2. 自动调用Singleton2.ourInstance
 // 2.1 此时单例类Singleton2得到初始化
 // 2.2 而该类在装载 & 被初始化时，会初始化它的静态域，从而创建单例；
 // 2.3 由于是静态域，因此只会JVM只会加载1遍，Java虚拟机保证了线程安全性
// 3. 最终只创建1个单例
class SingletonStatic {
    
    // 1. 创建静态内部类
    private static class SingletonInner {
       // 在静态内部类里创建单例
      private static  SingletonStatic ourInstance  = new SingletonStatic();
    }

    // 私有构造函数
    private SingletonStatic() {
    }
    
    // 延迟加载、按需创建
    public static  SingletonStatic newInstance() {
        return SingletonInner.ourInstance;
    }

}


//枚举类型
//原理
//根据枚举类型的下述特点，满足单例模式所需的 创建单例、线程安全、实现简洁的需求
enum SingletonEnum{

    //定义1个枚举的元素，即为单例类的1个实例
    INSTANCE;
    // 隐藏了1个空的、私有的 构造方法
    // private Singleton () {}
}

//获取单例的方式：
//Singleton singleton = Singleton.INSTANCE;
