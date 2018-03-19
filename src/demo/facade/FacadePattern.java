package demo.facade;

/**
 * 外观模式
 * 1.1 定义
 * 定义了一个高层、统一的接口，外部与通过这个统一的接口对子系统中的一群接口进行访问。
 * 通过创建一个统一的类，用来包装子系统中一个或多个复杂的类，客户端可以通过调用外观类的方法来调用内部子系统中所有方法 如下图：
 * 原理图 给个网站的导航例子你就懂了：以前我需要在搜索栏逐个搜索网站地址;有了网站导航（用了外观模式）后，就方便很多了
 * 1.2 主要作用
 * 实现客户类与子系统类的松耦合 降低原有系统的复杂度 提高了客户端使用的便捷性，使得客户端无须关心子系统的工作细节，通过外观角色即可调用相关功能。
 * 引入外观角色之后，用户只需要与外观角色交互; 用户与子系统之间的复杂逻辑关系由外观角色来实现 1.3 解决的问题
 * 避免了系统与系统之间的高耦合度 使得复杂的子系统用法变得简单
 * @author shaw
 * 
 */
public class FacadePattern {
	public static void main(String[] args) {
		{
			SubSystemA_Light light = new SubSystemA_Light();
			SubSystemB_Television television = new SubSystemB_Television();
			SubSystemC_Aircondition aircondition = new SubSystemC_Aircondition();

			// 起床后开电器
			System.out.println("起床了");
			light.on();
			television.on();
			aircondition.on();
			System.out.println("可以看电视了");

			// 睡觉时关电器
			System.out.println("睡觉了");
			light.off();
			television.off();
			aircondition.off();
			System.out.println("可以睡觉了");
		}
	}
}

// 灯类
class SubSystemA_Light {
	public void on() {
		System.out.println("打开了灯....");
	}

	public void off() {
		System.out.println("关闭了灯....");
	}
}

// 电视类
class SubSystemB_Television {
	public void on() {
		System.out.println("打开了电视....");
	}

	public void off() {
		System.out.println("关闭了电视....");
	}
}

// 空调类
class SubSystemC_Aircondition {
	public void on() {
		System.out.println("打开了空调....");
	}

	public void off() {
		System.out.println("关闭了空调....");
	}
}

class Facade {

	SubSystemA_Light light;
	SubSystemB_Television television;
	SubSystemC_Aircondition aircondition;

	// 传参
	public Facade(SubSystemA_Light light, SubSystemB_Television television,
			SubSystemC_Aircondition aircondition) {
		this.light = light;
		this.television = television;
		this.aircondition = aircondition;

	}

	// 起床后一键开电器
	public void on() {
		System.out.println("起床了");
		light.on();
		television.on();
		aircondition.on();

	}

	public void off() {
		// 睡觉时一键关电器
		System.out.println("睡觉了");
		light.off();
		television.off();
		aircondition.off();
	}
}
