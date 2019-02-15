package factory_method.Java;

/**
 * Created by Sirice on 2019/2/9.
 */
public class Main {
    public static void main(String[] args) {
        Form form = new Form(new CiricleFactory());
        form.event();
        form = new Form(new SquareFactory());
        form.event();
        form = new Form(new RectangleFactory());
        form.event();
    }
}
