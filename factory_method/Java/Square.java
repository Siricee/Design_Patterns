package factory_method.Java;

import sun.security.provider.SHA;

/**
 * Created by Sirice on 2019/2/9.
 */
public class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
