package State.Java.defaultExample;



/**
 * Created by Sirice on 2019/2/21.
 */
public class Context {
    private State state;
    public Context(){
        state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
