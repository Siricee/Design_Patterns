package State.Java.example1;

class FileSub
{
    private IState istate;
    public FileSub()
    {
        istate = new BeginState();
    }
    public void setState(IState state)
    {
        istate = state;
    }
    public void submit()
    {
        istate.submit(this);
    }
}