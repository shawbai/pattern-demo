package demo.factory.simple;

/**
 * 简单工厂模式:
 * 简单工厂模式的实质是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品类（这些产品类继承自一个父类或接口）的实例。
 *         该模式中包含的角色及其职责 工厂（Creator）角色
 *         简单工厂模式的核心，它负责实现创建所有实例的内部逻辑。工厂类的创建产品类的方法可以被外界直接调用，创建所需的产品对象。
 *         抽象产品（Product）角色 简单工厂模式所创建的所有对象的父类，它负责描述所有实例所共有的公共接口。 具体产品（Concrete
 *         Product）角色 是简单工厂模式的创建目标，所有创建的对象都是充当这个角色的某个具体类的实例。
 * @author shaw 
 * 
 */
public class SimpleFactoryPattern {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Factory mFactory = new Factory();

		// 客户要产品A
		try {
			// 调用工厂类的静态方法 & 传入不同参数从而创建产品实例
			mFactory.Manufacture("A").Show();
		} catch (NullPointerException e) {
			System.out.println("没有这一类产品");
		}

		// 客户要产品B
		try {
			mFactory.Manufacture("B").Show();
		} catch (NullPointerException e) {
			System.out.println("没有这一类产品");
		}

		// 客户要产品C
		try {
			mFactory.Manufacture("C").Show();
		} catch (NullPointerException e) {
			System.out.println("没有这一类产品");
		}

		// 客户要产品D
		try {
			mFactory.Manufacture("D").Show();
		} catch (NullPointerException e) {
			System.out.println("没有这一类产品");
		}
	}

}

// 步骤1. 创建抽象产品类，定义具体产品的公共接口
abstract class AbstractProduct {
	public abstract void Show();
}

// 步骤2. 创建具体产品类（继承抽象产品类），定义生产的具体产品

// 具体产品类A
class ProductA extends AbstractProduct {

	@Override
	public void Show() {
		System.out.println("生产出了产品A");
	}
}

// 具体产品类B
class ProductB extends AbstractProduct {

	@Override
	public void Show() {
		System.out.println("生产出了产品C");
	}
}

// 具体产品类C
class ProductC extends AbstractProduct {

	@Override
	public void Show() {
		System.out.println("生产出了产品C");
	}
}

// 步骤3. 创建工厂类，通过创建静态方法从而根据传入不同参数创建不同具体产品类的实例

class Factory {
	public static AbstractProduct Manufacture(String ProductName) {
		// 工厂类里用switch语句控制生产哪种商品；
		// 使用者只需要调用工厂类的静态方法就可以实现产品类的实例化。
		switch (ProductName) {
		case "A":
			return new ProductA();
		case "B":
			return new ProductB();
		case "C":
			return new ProductC();
		default:
			return null;
		}
	}
}
