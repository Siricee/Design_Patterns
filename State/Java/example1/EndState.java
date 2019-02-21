package State.Java.example1;

class EndState implements IState {
    @Override
    public void submit(FileSub file) {
        System.out.println("end------------");
        file.setState(new BeginState());
    }
}