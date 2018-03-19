package demo.template;

/**
 * 模板方法模式
 * 
 * 1.1 定义 定义一个模板结构，将具体内容延迟到子类去实现。 
 * 1.2 主要作用 在不改变模板结构的前提下在子类中重新定义模板中的内容。
 * 模板方法模式是基于"继承"的； 
 * 1.3 解决的问题 提高代码复用性 将相同部分的代码放在抽象的父类中，而将不同的代码放入不同的子类中 实现了反向控制
 * 通过一个父类调用其子类的操作，通过对子类的具体实现扩展不同的行为，实现了反向控制 & 符合"开闭原则"
 * 
 * @author shaw
 * 
 */
public class TemplateMethod {
	public static void main(String[] args) {
		// 炒 - 手撕包菜
		AbstractClass baoCai = new ConcreteClass_BaoCai();
		baoCai.cookProcess();
		
		// 炒 - 蒜蓉菜心
		AbstractClass caiXin= new ConcreteClass_CaiXin();
		caiXin.cookProcess();
	}
}


abstract class AbstractClass {
	// 模板方法，用来控制炒菜的流程 （炒菜的流程是一样的-复用）
	// 申明为final，不希望子类覆盖这个方法，防止更改流程的执行顺序
	final void cookProcess() {
		// 第一步：倒油
		this.pourOil();
		// 第二步：热油
		this.HeatOil();
		// 第三步：倒蔬菜
		this.pourVegetable();
		// 第四步：倒调味料
		this.pourSauce();
		// 第五步：翻炒
		this.fry();
	}

	// 定义结构里哪些方法是所有过程都是一样的可复用的，哪些是需要子类进行实现的

	// 第一步：倒油是一样的，所以直接实现
	void pourOil() {
		System.out.println("倒油");
	}

	// 第二步：热油是一样的，所以直接实现
	void HeatOil() {
		System.out.println("热油");
	}

	// 第三步：倒蔬菜是不一样的（一个下包菜，一个是下菜心）
	// 所以声明为抽象方法，具体由子类实现
	abstract void pourVegetable();

	// 第四步：倒调味料是不一样的（一个下辣椒，一个是下蒜蓉）
	// 所以声明为抽象方法，具体由子类实现
	abstract void pourSauce();

	// 第五步：翻炒是一样的，所以直接实现
	void fry() {
		System.out.println("炒啊炒啊炒到熟啊");
	}
}

//炒手撕包菜的类
class ConcreteClass_BaoCai extends  AbstractClass{
  @Override
  public void  pourVegetable(){  
      System.out.println("下锅的蔬菜是包菜");  
  }  
  @Override
  public void  pourSauce(){  
      System.out.println("下锅的酱料是辣椒");  
  }  
}
//炒蒜蓉菜心的类
class ConcreteClass_CaiXin extends  AbstractClass{
  @Override
  public void  pourVegetable(){  
      System.out.println("下锅的蔬菜是菜心");  
  }  
  @Override
  public void  pourSauce(){  
      System.out.println("下锅的酱料是蒜蓉");  
  }  
}
