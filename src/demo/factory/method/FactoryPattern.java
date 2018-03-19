package demo.factory.method;


/**
 * 工厂方法模式
 * 
 * 工厂方法模式，又称工厂模式、多态工厂模式和虚拟构造器模式，通过定义工厂父类负责定义创建对象的公共接口，而子类则负责生成具体的对象。
 * 将类的实例化（具体产品的创建）延迟到工厂类的子类（具体工厂）中完成，即由子类来决定应该实例化（创建）哪一个类。 组成（角色） 关系 作用
 * 抽象产品（Product） 具体产品的父类 描述具体产品的公共接口 具体产品（Concrete Product） 抽象产品的子类；工厂类创建的目标类
 * 描述生产的具体产品 抽象工厂（Creator） 具体工厂的父类 描述具体工厂的公共接口 具体工厂（Concrete Creator）
 * 抽象工厂的子类；被外界调用 描述具体工厂；实现FactoryMethod工厂方法创建产品的实例
 * 
 * 之所以可以解决简单工厂的问题，是因为工厂方法模式把具体产品的创建推迟到工厂类的子类（具体工厂）中，此时工厂类不再负责所有产品的创建，而只是给出具体工厂必须实现的接口，这样工厂方法模式在添加新产品的时候就不修改工厂类逻辑而是添加新的工厂子类，符合开放封闭原则，克服了简单工厂模式中缺点
 * @author shaw
 */
public class FactoryPattern {
	// 步骤5：外界通过调用具体工厂类的方法，从而创建不同具体产品类的实例
	public static void main(String[] args) {
		// 生产工作流程
		// 客户要产品A
		Factory mFactoryA = new FactoryA();
		mFactoryA.Manufacture().Show();

		// 客户要产品B
		Factory mFactoryB = new FactoryB();
		mFactoryB.Manufacture().Show();
	}
}

// 步骤1： 创建抽象产品类 ，定义具体产品的公共接口；
abstract class AbstractProduct {
	public abstract void Show();
}

// 步骤2： 创建抽象工厂类，定义具体工厂的公共接口
abstract class Factory {
	public abstract AbstractProduct Manufacture();
}

// 步骤3： 创建具体产品类（继承抽象产品类）， 定义生产的具体产品；

// 具体产品A类
class ProductA extends AbstractProduct {
	@Override
	public void Show() {
		System.out.println("生产出了产品A");
	}
}

// 具体产品B类
class ProductB extends AbstractProduct {

	@Override
	public void Show() {
		System.out.println("生产出了产品B");
	}
}

// 步骤4：创建具体工厂类（继承抽象工厂类），定义创建对应具体产品实例的方法；

// 工厂A类 - 生产A类产品
class FactoryA extends Factory {
	@Override
	public AbstractProduct Manufacture() {
		return new ProductA();
	}
}

// 工厂B类 - 生产B类产品
class FactoryB extends Factory {
	@Override
	public AbstractProduct Manufacture() {
		return new ProductB();
	}
}
