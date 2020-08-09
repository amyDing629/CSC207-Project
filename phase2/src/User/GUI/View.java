package User.GUI;

import User.Adapter.ILoginSystemBoundary;

public interface View {

    ILoginSystemBoundary getPresenter();

    void setPresenter(ILoginSystemBoundary presenter);

    void updateUIComponent(); //TODO
}
