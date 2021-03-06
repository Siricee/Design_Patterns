# Adapter

## 动机(Motivation)
+ 由于应用环境的变化，常常需要将”一些现存的对象“放在新的环境中应用，但是新环境要求的接口是这些现存对象所不满足。
+ 如何应对这些”迁移的变化“？

## 模式定义
将一个类的接口转换成客户希望的另一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
——《设计模式》GoF

## 要点总结
+ 在遗留代码复用、类库迁移等方面有用

![pic](https://upload-images.jianshu.io/upload_images/4119448-3b9e0816556b18af.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

### 个人注释

+ 适配器类继承父类接口，同时以组合的方式将子接口以参数方式包含在适配器类中。
  即**适配器类既有继承又有组合**。
+ GoF提出了两种适配器模式：
    - 对象适配器：对象的继承+组合
    - 类适配器：多继承（java中为多接口继承）
    
  对象适配器有很好的复用性，因为组合的是一个父类指针
  类适配器的多继承定死了，没有很好的灵活性。
  