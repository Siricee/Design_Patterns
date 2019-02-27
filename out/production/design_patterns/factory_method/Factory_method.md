# Factory Method

## 动机（Motivation）
+ 在软件系统中，经常面临着创建对象的工作；由于需求的变化，需要创建的对象的具体类型经常变化。
+ 如何应对这种变化？如何绕过常规的对象创建方法(new)，提供一种“封装机制”来避免客户程序和这种“具体对象创建工作”的紧耦合？

## 模式定义
定义一个用于创建对象的接口，让子类决定实例化哪一个类。Factory Method使得一个类的实例化延迟（目的：解耦，手段：虚函数）到子类。
——《设计模式》GoF

## 要点总结
+ Factory Method模式用于隔离类对象的使用者和具体类型之间的耦合关系。面对一个经常变化的具体类型，紧耦合关系(new)会导致软件的脆弱。
+ Factory Method模式通过面向对象的手法，将所要创建的具体对象工作<u>延迟</u>到子类，从而实现一种<u>扩展</u>（而非更改）的策略，较好地解决了这种紧耦合关系。
+ Factory Method模式解决“单个对象”的需求变化。缺点在于要求创建方法/参数相同。

***

### *个人注解*
+ 这个问题最开始是想遵循**依赖倒置原则**，即一个抽象类想办法摆脱对实现类的编译依赖。
  **将编译依赖转化为运行时依赖就是一个延迟的办法，让编译时的对象转化到运行时多态创建具体对象**
  C++中是虚函数，java中为抽象方法。
  
  `ISplitter * splitter = new BinarySplitter();//依赖具体类`  
  
  转换为
  
  `ISplitter * splitter = factory->CreateSplitter(); //多态new`

+ 主类中成员变量为`SplitterFactory* factory;`
  构造方法为
  ```cpp
  MainForm(SplitterFactory* factory){
        this->factory =  factory;
  }
  ```
  即在构造中就添加了创建对象的工厂方法。
  于是在成员方法中，触发了Button1_Click()事件就会自动创建对象。
  ```cpp
    class MainForm : public Form
    {
        SplitterFactory*  factory;//工厂
    
        public:
        
            MainForm(SplitterFactory*  factory){
                this->factory=factory;
            }
        
            void Button1_Click(){
     
                ISplitter * splitter=
                    factory->CreateSplitter(); //多态new
                
                splitter->split();
        
            }
    };
  ```
  
  
### 其他人的笔记
[工厂方法模式笔记](http://www.iocoder.cn/DesignPattern/xiaomingge/Factory-Method/)

> 六、工厂方法模式的优缺点
>
> 优点
> 1、在工厂方法中，用户只需要知道所要产品的具体工厂，无须关系具体的创建过程，甚至不需要具体产品类的类名。
> 2、在系统增加新的产品时，我们只需要添加一个具体产品类和对应的实现工厂，无需对原工厂进行任何修改，很好地符合了“开闭原则”。
> 
> 缺点
> 1、 每次增加一个产品时，都需要增加一个具体类和对象实现工厂，是的系统中类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。这并不是什么好事。
> 
> 七、**工厂方法适用场景**
> 1、一个类不知道它所需要的对象的类。在工厂方法模式中，我们不需要具体产品的类名，我们只需要知道创建它的具体工厂即可。
> 2、一个类通过其子类来指定创建那个对象。在工厂方法模式中，对于抽象工厂类只需要提供一个创建产品的接口，而由其子类来确定具体要创建的对象，在程序运行时，子类对象将覆盖父类对象，从而使得系统更容易扩展。
> 3、将创建对象的任务委托给多个工厂子类中的某一个，客户端在使用时可以无须关心是哪一个工厂子类创建产品子类，需要时再动态指定。
> 
> 八、总结
> 1、工厂方法模式完全符合“开闭原则”。
> 2、工厂方法模式使用继承，将对象的创建委托给子类，通过子类实现工厂方法来创建对象。
> 3、工厂方法允许类将实例化延伸到子类进行。
> 4、工厂方法让子类决定要实例化的类时哪一个。在这里我们要明白这并不是工厂来决定生成哪种产品，而是在编写创建者类时，不需要知道实际创建的产品是哪个，选择了使用哪个子类，就已经决定了实际创建的产品时哪个了。
> 5、在工厂方法模式中，创建者通常会包含依赖于抽象产品的代码，而这些抽象产品是、由子类创建的，创建者不需要真的知道在制作哪种具体产品。
  