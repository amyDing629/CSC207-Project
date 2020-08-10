package User.Adapter;

import Trade.TradeManager;
import User.GUI.View;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

public class ClientUserPresenter implements IUserPresenter {

    // View
    JFrame view;

    // Use Case
    UserManager userManager = new UserManager();
    TradeManager tradeManager = new TradeManager();

    // Other
    UUID currUser;

    public ClientUserPresenter(UUID currUser, JFrame view) {
        this.currUser = currUser;
        this.view = view;
    }


    @Override
    public UserManager getUserModel() {
        return userManager;
    }

    @Override
    public TradeManager getTradeModel() {
        return tradeManager;
    }

    @Override
    public void performAction() {

    }

    @Override
    public UUID getCurrUser() {
        return currUser;
    }

    @Override
    public void run() {
        view.setVisible(true);
    }
}
