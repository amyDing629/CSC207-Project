package User;

public interface View {

    ILoginSystemBoundary getPresenter();

    void setPresenter(ILoginSystemBoundary presenter);

    void updateUIComponent(); //TODO
}
