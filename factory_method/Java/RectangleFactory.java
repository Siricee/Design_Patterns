package factory_method.Java;

/**
 * Created by Sirice on 2019/2/9.
 */
public class RectangleFactory extends ShapeFactory {
    @Override
    Shape productShape() {
        return new Rectangle();
    }
}
