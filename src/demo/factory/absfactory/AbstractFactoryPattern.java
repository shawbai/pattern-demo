package demo.factory.absfactory;
/**
 * 抽象工厂模式
 * 
 * 抽象工厂模式，即Abstract Factory Pattern，提供一个创建一系列相关或相互依赖对象的接口，而无须指定它们具体的类；具体的工厂负责实现具体的产品实例。
 * 允许使用抽象的接口来创建一组相关产品，而不需要知道或关心实际生产出的具体产品是什么，这样就可以从具体产品中被解耦。
 * @author shaw
 *	
 */
public class AbstractFactoryPattern {
    public static void main(String[] args){
        Factory mFactoryA = new FactoryA();
        Factory mFactoryB = new FactoryB();
        //A厂当地客户需要容器产品A
        mFactoryA.ManufactureContainer().Show();
        //A厂当地客户需要模具产品A
        mFactoryA.ManufactureMould().Show();

        //B厂当地客户需要容器产品B
        mFactoryB.ManufactureContainer().Show();
        //B厂当地客户需要模具产品B
        mFactoryB.ManufactureMould().Show();

    }
}

//步骤1： 创建抽象产品族类 ，定义具体产品的公共接口；
abstract class AbstractProduct{
    public abstract void Show();
}

//步骤2： 创建抽象工厂类，定义具体工厂的公共接口
abstract class Factory{
   public abstract AbstractProduct ManufactureContainer();
    public abstract AbstractProduct ManufactureMould();
}
//步骤3： 创建抽象产品类 ，定义具体产品的公共接口；

//容器产品抽象类
abstract class ContainerProduct extends AbstractProduct{
    @Override
    public abstract void Show();
}

//模具产品抽象类
abstract class MouldProduct extends AbstractProduct{
    @Override
    public abstract void Show();
}

//步骤4： 创建具体产品类（继承抽象产品类）， 定义生产的具体产品；

//容器产品A类
class ContainerProductA extends ContainerProduct{
  @Override
  public void Show() {
      System.out.println("生产出了容器产品A");
  }
}

//容器产品B类
class ContainerProductB extends ContainerProduct{
  @Override
  public void Show() {
      System.out.println("生产出了容器产品B");
  }
}

//模具产品A类
class MouldProductA extends MouldProduct{

  @Override
  public void Show() {
      System.out.println("生产出了模具产品A");
  }
}

//模具产品B类
class MouldProductB extends MouldProduct{

  @Override
  public void Show() {
      System.out.println("生产出了模具产品B");
  }
}

//步骤5：创建具体工厂类（继承抽象工厂类），定义创建对应具体产品实例的方法；

//A厂 - 生产模具+容器产品
class FactoryA extends Factory{

  @Override
  public AbstractProduct ManufactureContainer() {
      return new ContainerProductA();
  }

  @Override
  public AbstractProduct ManufactureMould() {
      return new MouldProductA();
  }
}

//B厂 - 生产模具+容器产品
class FactoryB extends Factory{

  @Override
  public AbstractProduct ManufactureContainer() {
      return new ContainerProductB();
  }

  @Override
  public AbstractProduct ManufactureMould() {
      return new MouldProductB();
  }
}
