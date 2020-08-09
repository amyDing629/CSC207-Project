package User;

import Inventory.Inventory;
import Main.DataAccessFull;
import User.Adapter.UIcontoller;
import Trade.TradeManager;
import User.GUI.LoginIGUI;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import java.io.File;

public class mainRun {

    public static void main(String[] args) {
        Inventory iv = new Inventory();
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        ItemApprovalManager iam = new ItemApprovalManager();
        DataAccessFull uaf = new DataAccessFull(iam);

        AdminActivityManager aam = new AdminActivityManager();
        UIcontoller uc = new UIcontoller(um, aam, tm, iam, iv);
        uc.checkFileEmpty(new File("phase2/src/username.txt"));
        uaf.readFile();

        LoginIGUI lo = new LoginIGUI();
        lo.run();
    }
}
