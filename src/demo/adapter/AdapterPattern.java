package demo.adapter;

/**
 * 适配器模式
 * 
 * 1.1 模式说明
 * 定义一个包装类，用于包装不兼容接口的对象
 * 包装类 = 适配器Adapter； 被包装对象 = 适配者Adaptee = 被适配的类 1.2 主要作用
 * 把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。
 * 适配器模式的形式分为：类的适配器模式 & 对象的适配器模式 1.3 解决的问题
 * 原本由于接口不兼容而不能一起工作的那些类可以在一起工作
 * @author shaw
 * 
 */
public class AdapterPattern {
	//通过Adapter类从而调用所需要的方法
	public static void main(String[] args) {
		Target mAdapter220V = new Adapter220V();
		ImportedMachine mImportedMachine = new ImportedMachine();
		// 用户拿着进口机器插上适配器（调用Convert_110v()方法）
		// 再将适配器插上原有插头（Convert_110v()方法内部调用Output_220v()方法输出220V）
		// 适配器只是个外壳，对外提供110V，但本质还是220V进行供电
		mAdapter220V.Convert_110v();
		mImportedMachine.Work();
	}
}

// 步骤1： 创建Target接口（期待得到的插头）：能输出110V（将220V转换成110V）

interface Target {

	// 将220V转换输出110V（原有插头（Adaptee）没有的）
	public void Convert_110v();
}

// 步骤2： 创建源类（原有的插头） ；

class PowerPort220V {
	// 原有插头只能输出220V
	public void Output_220v() {
		System.out.println("使用220v");
	}
}

// 步骤3：创建适配器类（Adapter）

class Adapter220V extends PowerPort220V implements Target {
	// 期待的插头要求调用Convert_110v()，但原有插头没有
	// 因此适配器补充上这个方法名
	// 但实际上Convert_110v()只是调用原有插头的Output_220v()方法的内容
	// 所以适配器只是将Output_220v()作了一层封装，封装成Target可以调用的Convert_110v()而已

	@Override
	public void Convert_110v() {
		System.out.println("将110V转成220V");
		this.Output_220v();
	}
}

// 步骤4：定义具体使用目标类，并通过Adapter类调用所需要的方法从而实现目标（不需要通过原有插头）
// 进口机器类
class ImportedMachine {

	public void Work() {
		System.out.println("进口机器正常运行");
	}
}
