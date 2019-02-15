package singleton.Java;

import java.time.Period;

public class Main {
    public static void main(String[] args) {

    }
}

/*
    注意这是第一版静态对象构造方法。
    注意点：1.构造方法为静态
           2.创建对象的时候已经创建了（唯一）实例（但没有机制可以保证唯一性）
 */
class Production{
    public static final Production prod = new Production();
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //构造函数私有
    //此处注意为强制实现。
    private Production() {
    }

    public static Production getProd(){
        return prod;
    }
}


/*
    这是第二版单例对象构造方法。
    注意点：1.构造方法为静态
           2.在语法上判断是否为单例（对应Cpp文件第二个类）
 */

class Production2{
    private static Production2 prod;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //构造函数私有
    //此处注意为强制实现。
    private Production2() {
    }

    public static Production2 getProd(){
        if(prod == null){
            prod = new Production2();
        }
        return prod;
    }
}

/*
    在判断单例时添加线程锁，关键字synchronized
    缺点是不判断直接添加锁有锁的开销。
 */

class Production3{
    private static Production3 prod;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //构造函数私有
    //此处注意为强制实现。
    private Production3() {
    }

    //注意此处多了一个线程锁
    public static synchronized Production3 getProd(){
        if(prod == null){
            prod = new Production3();
        }
        return prod;
    }
}


/*

 */
class Production4{
    private static Production4 prod;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //构造函数私有
    //此处注意为强制实现。
    private Production4() {
    }


    //双加锁问题

    //注意此处多改为判断是否有锁之后再添加线程锁
    //必须判断两次结合synchronized关键字解决性能开销问题。
    public static synchronized Production4 getProd(){
        if(prod == null){
            synchronized (Production4.class){
                if (prod == null){
                    prod = new Production4();
                }
            }
        }
        return prod;
    }
}

/*
    加入关键字volatile.保证prod这个引用不从缓存中直接得到，避免双加锁问题中的内存reorder不安全
 */
class Production5{
    private static volatile Production5 prod;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //构造函数私有
    //此处注意为强制实现。
    private Production5() {
    }


    //双加锁问题
    public static synchronized Production5 getProd(){
        if(prod == null){
            synchronized (Production4.class){
                if (prod == null){
                    prod = new Production5();
                }
            }
        }
        return prod;
    }
}