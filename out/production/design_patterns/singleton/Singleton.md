# Singleton

## 动机(Motivation)
+ 在软件系统中，经常有这样一些特殊的类，必须保证它们在系统中只存在一个实例，才能确保它们的逻辑正确性、以及良好的效率。
+ 如何绕过常规的构造器，提供一种机制来保证一个类只有一个实例？
+ 这应该是类设计者的责任，而不是使用者的责任。

## 模式定义
保证一个类仅有一个实例，并提供一个该实例的全局访问点。
——《设计模式》GoF

## 要点总结
+ Singleton模式中的实例构造器可以设置为protected以允许子类派生。
+ Singleton模式一般不要支持拷贝构造函数和Clone接口，因为这有可能导致多个对象实例，与Singleton模式的初中违背。
+ 如何实现多线程环境下安全的Singleton？注意对双检查锁的正确实现。

***

#### *个人注释*
+ 单例模式中涉及一个基础问题：**多线程下访问互斥**
  
Cpp文件中使用了多种方式解释这个问题。
```cpp
   Singleton* Singleton::m_instance = nullptr;
   //线程非安全版本
   Singleton* Singleton::getInstance() {
       if (m_instance == nullptr) {
           m_instance = new Singleton();
       }
      return m_instance;
   }
```
为保证互斥访问：加锁方式。
```cpp
    //线程安全版本，但锁的代价过高
    Singleton* Singleton::getInstance() {
        Lock lock;
        if (m_instance == nullptr) {
            m_instance = new Singleton();
        }
        return m_instance;
    }
```
但访问到这个方法，每个线程都会加个锁，锁的开销会增大，降低性能。

于是有了**双检查锁**这个机制
```cpp
    //双检查锁，但由于内存读写reorder不安全
    Singleton* Singleton::getInstance() {
        if(m_instance==nullptr){
            Lock lock;
            if (m_instance == nullptr) {
                m_instance = new Singleton();
            }
        }
        return m_instance;
    }
```
这个问题来源于编译之后，`m_instance = new Singleton();`

正常编译器的执行顺序：
1. 分配内存
2. 创建m_instance变量
3. 将内存地址赋值给变量

但在编译之后，字节码文件的执行顺序可能不是按上述步骤进行，可能打乱顺序。在多线程中可能会造成创建变量之后地址赋值出现问题。

这个就是**双检查锁可能会引起的内存reorder不安全问题**

为了解决这个问题，java和C#使用关键字`volatile`强制控制指令的执行顺序，避免打乱顺序。

注：`volatile`和`synchronized`的区别看这个文章[volatile和synchronized的区别](https://www.cnblogs.com/kaleidoscope/p/9506018.html)

> volatile解决内存可见性的问题，会使得所有对volatile变量的读写都会直接刷到主存，即保证了变量的可见性。这样就能满足一些对变量可见性有要求而对读取顺序没有要求的需求。
>
> synchronized关键字解决的是执行控制的问题，它会阻止其它线程获取当前对象的监控锁，这样就使得当前对象中被synchronized关键字保护的代码块无法被其它线程访问，也就无法并发执行。
> 更重要的是，synchronized还会创建一个内存屏障，内存屏障指令保证了所有CPU操作结果都会直接刷到主存中，从而保证了操作的内存可见性，同时也使得先获得这个锁的线程的所有操作，都happens-before于随后获得这个锁的线程的操作。
 
> volatile关键字，当且仅当满足以下所有条件时可使用： 
> 1. 对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值。
> 2. 该变量没有包含在具有其他变量的不变式中。

> volatile和synchronized的区别
> * volatile本质是在告诉jvm当前变量在寄存器（工作内存）中的值是不确定的，需要从主存中读取； synchronized则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。
> * volatile仅能使用在变量级别；synchronized则可以使用在变量、方法、和类级别的
> * volatile仅能实现变量的修改可见性，不能保证原子性；而synchronized则可以保证变量的修改可见性和原子性
> * volatile不会造成线程的阻塞；synchronized可能会造成线程的阻塞。
> * volatile标记的变量不会被编译器优化；synchronized标记的变量可以被编译器优化

```cpp
//C++ 11版本之后的跨平台实现 (volatile)
std::atomic<Singleton*> Singleton::m_instance;
......这段代码在cpp文件中有，现阶段看不懂。
```

##### 其他人关于内存reorder问题的解释
[原文链接](http://study.ikuvn.com/#/docs/zh-cn/java/design-mode/single-mode)
[原文github](https://github.com/ainilili/snail)

在Java中看似顺序的代码在JVM中，可能会出现编译器或者CPU对这些操作指令进行了重新排序；在特定情况下，指令重排将会给我们的程序带来不确定的结果…..

对于`instance = new Single()`这一行代码，JVM执行的指令有多行：
  ```java
    memory = allocate(); //1：分配对象的内存空间
    ctorInstance(memory); //2：初始化对象
    instance = memory; //3：设置instance指向刚分配的内存地址
  ```
  经重排后如下：
  ```java
    memory = allocate(); //1：分配对象的内存空间
    instance = memory; //3：设置instance指向刚分配的内存地址，此时对象还没被初始化
    ctorInstance(memory); //2：初始化对象
  ```

若有A线程进行完重排后的第二步，且未执行初始化对象。此时B线程来取instance时，发现instance不为空，于是便返回该值，但由于没有初始化完该对象，此时返回的对象是有问题的。
