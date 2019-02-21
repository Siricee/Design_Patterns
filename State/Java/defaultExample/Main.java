package State.Java.defaultExample;


/**
 * Created by Sirice on 2019/2/21.
 *
 * 个人感觉这个例子举得不好，这完全就是策略模式嘛？？！！
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new DefaultState());
        context.getState().doSomething(context);

        context.setState(new StartState());
        context.getState().doSomething(context);

        context.setState(new StopState());
        context.getState().doSomething(context);
    }
}
