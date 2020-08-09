package User.Adapter;

import Trade.TradeManager;
import User.GUI.View;
import User.UseCase.UserManager;

import java.util.UUID;

public class LoginSystemPresenter implements IUserPresenter {

    // model
    UserManager um = new UserManager();

    // view
    View view;

    public LoginSystemPresenter(View view) {
        this.view = view;
    }

    @Override
    public UserManager getUserModel() {
        return um;
    }

    @Override
    public TradeManager getTradeModel() {
        return null;
    }

    @Override
    public void performAction() {

    }

    @Override
    public UUID getCurrUser() {
        return null;
    }

    public void run() {
        view.setPresenter(this);
        view.run();
    }

//    public void register(String name, String password) {
//        ClientUser user = new ClientUser(name, password, false);
//        um.addUser(user);
//    }
//
//    public boolean login(String name, String password) {
//        return um.verifyUser(name, password);
//    }
//
//
//    public void explore() {
//        new InventoryGUI();
//    }

}
