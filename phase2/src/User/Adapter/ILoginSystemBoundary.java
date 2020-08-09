package User.Adapter;

public interface ILoginSystemBoundary {

    void register(String name, String password);

    boolean login(String name, String password);

    void explore();

}
