package factory_method.Java;

/**
 * Created by Sirice on 2019/2/9.
 */
public class Form {
    ShapeFactory factory;

    public Form(ShapeFactory factory) {
        this.factory = factory;
    }

    public void event(){
        Shape shape = factory.productShape();
        shape.draw();
    }
}

