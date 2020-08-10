package User;

import Inventory.Inventory;
import Main.DataAccessFull;
import User.Adapter.ClientUserController;
import User.Adapter.UIController;
import Trade.TradeManager;
import User.GUI.LoginGUI;
import User.UseCase.AdminActivityManager;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import java.io.File;
import java.io.FileNotFoundException;

public class mainRun {

    public static void main(String[] args) throws FileNotFoundException {

        DataAccessFull uaf = new DataAccessFull();
        ClientUserController uc = new ClientUserController();
        uc.checkFileEmpty(new File("phase2/src/user.ser"));
        uaf.readFile();

        LoginGUI lo = new LoginGUI();
        lo.run();
    }
}
