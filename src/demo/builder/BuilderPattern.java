package demo.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式 
 * 1. 介绍
 * 1.1 定义
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 * 1.2 主要作用
 * 在用户不知道对象的建造过程和细节的情况下就可以直接创建复杂的对象。
 * 用户只需要给出指定复杂对象的类型和内容; 建造者模式负责按顺序创建复杂对象（把内部的建造过程和细节隐藏起来) 1.3 解决的问题
 * 方便用户创建复杂的对象（不需要知道实现过程） 代码复用性 & 封装性（将对象构建过程和细节进行封装 & 复用）
 * 应用场景
 * 需要生成的产品对象有复杂的内部结构，这些产品对象具备共性;
 * 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。
 * @author shaw
 * 
 */
public class BuilderPattern {
	// **步骤5： **客户端调用-小成到电脑城找老板买电脑
	public static void main(String[] args) {

		// 逛了很久终于发现一家合适的电脑店
		// 找到该店的老板和装机人员
		Director director = new Director();
		Builder builder = new ConcreteBuilder();

		// 沟通需求后，老板叫装机人员去装电脑
		director.Construct(builder);

		// 装完后，组装人员搬来组装好的电脑
		Computer computer = builder.GetComputer();
		// 组装人员展示电脑给小成看
		computer.Show();

	}
}

// 步骤1： 定义组装的过程（Builder）：组装电脑的过程
abstract class Builder {

	// 第一步：装CPU
	// 声明为抽象方法，具体由子类实现
	public abstract void BuildCPU();

	// 第二步：装主板
	// 声明为抽象方法，具体由子类实现
	public abstract void BuildMainboard();

	// 第三步：装硬盘
	// 声明为抽象方法，具体由子类实现
	public abstract void BuildHD();

	// 返回产品的方法：获得组装好的电脑
	public abstract Computer GetComputer();
}

// 步骤2： 电脑城老板委派任务给装机人员（Director）

class Director {
	// 指挥装机人员组装电脑
	public void Construct(Builder builder) {
		builder.BuildCPU();
		builder.BuildMainboard();
		builder.BuildHD();
	}
}

// **步骤3： **创建具体的建造者（ConcreteBuilder）:装机人员

// 装机人员1
class ConcreteBuilder extends Builder {
	// 创建产品实例
	Computer computer = new Computer();

	// 组装产品
	@Override
	public void BuildCPU() {
		computer.Add("组装CPU");
	}

	@Override
	public void BuildMainboard() {
		computer.Add("组装主板");
	}

	@Override
	public void BuildHD() {
		computer.Add("组装主板");
	}

	// 返回组装成功的电脑
	@Override
	public Computer GetComputer() {
		return computer;
	}
}

// **步骤4： **定义具体产品类（Product）：电脑

class Computer {

	// 电脑组件的集合
	private List<String> parts = new ArrayList<String>();

	// 用于将组件组装到电脑里
	public void Add(String part) {
		parts.add(part);
	}

	public void Show() {
		for (int i = 0; i < parts.size(); i++) {
			System.out.println("组件" + parts.get(i) + "装好了");
		}
		System.out.println("电脑组装完成，请验收");
	}

}