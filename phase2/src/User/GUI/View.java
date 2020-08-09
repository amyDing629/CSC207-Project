package User.GUI;

import User.Adapter.IUserPresenter;

public interface View {

    IUserPresenter getPresenter();

    void setPresenter(IUserPresenter presenter);

    void run();

    void updateUIComponent(); //TODO
}
