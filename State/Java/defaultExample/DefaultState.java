package State.Java.defaultExample;



/**
 * Created by Sirice on 2019/2/21.
 */
public class DefaultState extends State {
    @Override
    public void doSomething(Context context) {
        System.out.println("context is in default state");
        context.setState(this);
    }

}
