package State.Java.example1;

class WorkingState implements IState {
    @Override
    public void submit(FileSub file) {
        System.out.println("working-------------");
        file.setState(new EndState());
    }
}
