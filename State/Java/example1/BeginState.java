package State.Java.example1;


public class BeginState implements IState {
    @Override
    public void submit(FileSub file) {
        System.out.println("begin-------");
        file.setState(new WorkingState());
    }
}
