package User.Adapter;

import Trade.TradeManager;
import User.UseCase.UserManager;

import java.util.UUID;

public interface IUserPresenter {

    UserManager getUserModel();

    TradeManager getTradeModel();

    void performAction();

    UUID getCurrUser();

    void run();
}
