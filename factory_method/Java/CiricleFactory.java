package factory_method.Java;

/**
 * Created by Sirice on 2019/2/9.
 */
public class CiricleFactory extends ShapeFactory {
    @Override
    Shape productShape() {
        return new Circle();
    }
}
