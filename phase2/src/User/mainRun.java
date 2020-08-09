package User;

import Inventory.Inventory;
import Main.DataAccessFull;
import User.Adapter.UIController;
import Trade.TradeManager;
import User.GUI.LoginIGUI;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import java.io.File;
import java.io.FileNotFoundException;

public class mainRun {

    public static void main(String[] args) throws FileNotFoundException {
        Inventory iv = new Inventory();
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        ItemApprovalManager iam = new ItemApprovalManager();
        DataAccessFull uaf = new DataAccessFull(iam);

        AdminActivityManager aam = new AdminActivityManager();
        UIController uc = new UIController(um, aam, tm, iam, iv);
        uc.checkFileEmpty(new File("phase2/src/username.txt"));
        uaf.readFile();

        LoginIGUI lo = new LoginIGUI();
        lo.run();
    }
}
