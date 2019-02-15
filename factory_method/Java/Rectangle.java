package factory_method.Java;

import sun.security.provider.SHA;

/**
 * Created by Sirice on 2019/2/9.
 */
public class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
