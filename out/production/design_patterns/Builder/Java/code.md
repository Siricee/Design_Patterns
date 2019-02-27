我们通常构造一个有很多参数的对象时有三种方式：构造器重载，JavaBeans模式和builder模式。通过一个小例子我们来看一下builder模式的优势。

#### 构造器重载方式
```java
public class Product {
    
    private int id;
    private String name;
    private int type;
    private float price;
    
    public Product() {
        super();
    }
    
    public Product(int id) {
        super();
        this.id = id;
    }

    public Product(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Product(int id, String name, int type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Product(int id, String name, int type, float price) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

}

```

> 使用构造器重载我们需要定义很多构造器，为了应对使用者不同的需求（有些可能只需要id，有些需要id和name，有些只需要name，......），理论上我们需要定义2^4 = 16个构造器，这只是4个参数，如果参数更多的话，那将是指数级增长，肯定是不合理的。要么你定义一个全部参数的构造器，使用者只能多传入一些不需要的属性值来匹配你的构造器。很明显这种构造器重载的方式对于多属性的情况是不完美的。

####  JavaBeans方式
```java
public class Product2 {
    
    private int id;
    private String name;
    private int type;
    private float price;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

}
```

构造对象
```java
    Product2 p2 = new Product2();
    p2.setId(10);
    p2.setName("phone");
    p2.setPrice(100);
    p2.setType(1);
```
> 这种方式弥补了构造器重载的不足，创建实例很容易，代码读起来也很容易。但是，因为构造过程被分到了几个调用中，在构造过程中JavaBeans可能处于不一致的状态，类无法仅仅通过检验构造器参数的有效性来保证一致性。

#### Builder模式
```java
public class Product3 {
    
    private final int id;
    private final String name;
    private final int type;
    private final float price;
    
    private Product3(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.price = builder.price;
    }
    
    public static class Builder {
        private int id;
        private String name;
        private int type;
        private float price;
        
        public Builder id(int id) {
            this.id = id;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder type(int type) {
            this.type = type;
            return this;
        }
        public Builder price(float price) {
            this.price = price;
            return this;
        }
        
        public Product3 build() {
            return new Product3(this);
        }
    }

}
```

可以看到builder模式将属性定义为不可变的，然后定义一个内部静态类Builder来构建属性，再通过一个只有Builder参数的构造器来生成Product对象。Builder的setter方法返回builder本身，以便可以将属性连接起来。我们就可以像下面这样使用了。

```java
Product3 p3 = new Product3.Builder()
                            .id(10)
                            .name("phone")
                            .price(100)
                            .type(1)
                            .build();
```



