# Mediator

## 动机(Motivation)
+ 多个对象相互关联的情况，对象之间常常会维持一种复杂的引用关系，如果遇到一些需求的更改，这种直接的引用关系将面临不断的变化。
+ 在这种情况下，可以使用一种”中介对象“来管理对象间的关联关系，避免相互交互的对象之间的紧耦合引用关系，从而更好地抵御变化。

## 模式定义
用一个中介对象来封装(封装变化)一系列的对象交互。中介者使各对象不需要显式的相互引用(编译时依赖->运行时依赖)，
从而使其耦合松散(管理变化)，并且可以独立地改变它们之间的交互。
——《设计模式》GoF

## 要点总结
+ 将多个对象间发杂的关联关系解耦
+ Facade模式是解耦系统间(单向)的对象关联关系；Mediator模式是解耦系统内各个对象之间(双向)的关联关系。


![pic](https://upload-images.jianshu.io/upload_images/4119448-d6fe1699a075d18a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/798/format/webp)

> 解释：这个图的意思是，Mediator和Colleague之间存在间接双向依赖关系，但由于concreteMediator->concreteColleague的存在，使这两个类之间不是直接的双向依赖。
>
> 至于这个图上的Colleague和concreteColleague实际并不是直接的继承关系。

举例来说，A<->B<->C,A<->C<->D,
中介者模式为Mediator<->A,Mediator<->B,Mediator<->C,Mediator<->D
这样原本A如果想调用D，不能直接调用（紧耦合），需要A<->Mediator<->D，让中介者去通知调用。

具体应用：前端MVVM框架，model和view之间有个ViewModel层通知虚拟节点的变化。





