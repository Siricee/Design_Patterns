package State.Java.example1;

/**
 * 本例中使用了状态切换，但没有实现C++版本中的单例引用。
 */
public class Main
{
    public static void main(String[] args)
    {
        FileSub file = new FileSub();
        file.setState(new BeginState());
        file.submit();
        file.submit();
        file.submit();

    }
}
