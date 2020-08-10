package User.Adapter;

import Inventory.InventoryGUI;
import Inventory.MarketBuilder;
import Trade.TradeManager;
import User.Entity.ClientUser;
import User.GUI.View;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

public class LoginSystemPresenter implements IUserPresenter {

    // model
    UserManager um = new UserManager();

    // view
    JFrame view;

    public LoginSystemPresenter(JFrame view) {
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
        view.setVisible(true);
    }

    public void register(String name, String password) {
        ClientUser user = new ClientUser(name, password, false);
        um.addUser(user);
    }

    public boolean login(String name, String password) {
        return um.verifyUser(name, password);
    }


    public void explore() {
        new MarketBuilder(view);
    }

}
