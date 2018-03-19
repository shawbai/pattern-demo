package demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



/**
 * 代理模式
 * 可以做到在不修改目标对象的功能前提下,对目标功能扩展.
 * 1.1 定义
 * 给目标对象提供一个代理对象，并由代理对象控制对目标对象的引用
 * 代理对象：起到中介作用，连接客户端和目标对象
 * 例子：电脑桌面的快捷方式。电脑对某个程序提供一个快捷方式（代理对象），快捷方式连接客户端和程序，客户端通过操作快捷方式就可以操作那个程序 1.2 主要作用
 * 通过引入代理对象的方式来间接访问目标对象
 * 1.3 解决的问题
 * 防止直接访问目标对象给系统带来的不必要复杂性。
 * @author shaw
 * 
 */
public class ProxyPattern {
	// 步骤4：客户端调用
	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject();
		
		//静态代理
		Subject proxy = new StaticProxy(realSubject);
		proxy.call();

		//动态代理
		// 给目标对象，创建代理对象
		proxy = (Subject) new ProxyFactory(realSubject).getProxyInstance();
		// 执行方法 【代理对象】
		proxy.call();
		
		//Cglib代理
		CgRealSubject newRealSubject = new CgRealSubject();
		CgRealSubject realProxy = (CgRealSubject)new CglibProxyFactory(newRealSubject).getProxyInstance();
        //执行代理对象的方法
		realProxy.call();

	}
}
//(静态代理)
// 步骤1： 创建抽象对象接口（Subject）
interface Subject {
	public void call();
}

// 步骤2： 创建真实对象类（RealSubject）,即”我“

class RealSubject implements Subject {

	@Override
	public void call() {
		System.out.println("调用call");
	}
}

class CgRealSubject{

	public void call() {
		System.out.println("调用call");
	}
}

// 步骤3：创建代理对象类（Proxy），即”代购“，并通过代理类创建真实对象实例并访问其方法
class StaticProxy implements Subject {

	
	private Subject target;
	
	// 引用并创建真实对象实例，即”我“
    public StaticProxy(Subject target){
        this.target=target;
    }
	
	@Override
	public void call() {
		System.out.println("开始事务1");
		// 调用真实对象的方法，进行代理购买Mac
		target.call();
		// 代理对象额外做的操作
		System.out.println("提交事务1");
	}
}

//(动态代理)

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口,但是需要指定接口类型
 * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
 */
class ProxyFactory{

    //维护一个目标对象
    private Object target;
    public ProxyFactory(Object target){
        this.target=target;
    }

   //给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						 System.out.println("开始事务2");
	                        //执行目标对象方法
	                        Object returnValue = method.invoke(target, args);
	                        System.out.println("提交事务2");
	                        return returnValue;
					}
                }
        );
    }
}

//CGLIB包的底层是通过使用一个小而快的字节码处理框架ASM(Java字节码操控框架)，来转换字节码并生成新的类。
//代理无需实现接口的继承的类
class CglibProxyFactory implements MethodInterceptor{
    //维护目标对象
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开始事务...");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, args);
        System.out.println("提交事务...");
        return returnValue;
    }
}