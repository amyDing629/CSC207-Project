package User;

import Inventory.Inventory;
import Main.UI.UIcontoller;
import Trade.TradeManager;

import java.io.File;

//import Main.DataAccessFull;

public class mainRun {

    public static void main(String[] args) {
        Inventory iv = new Inventory();
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        ItemApprovalManager iam = new ItemApprovalManager();
//        DataAccessFull uaf = new DataAccessFull(um, tm, iv, iam);
        AdminActivityManager aam = new AdminActivityManager(tm, um);
        UIcontoller uc = new UIcontoller(um, aam, tm, iam, iv);
        uc.checkFileEmpty(new File("phase2/src/username.txt"));
        //uaf.readFile(tm,iv,um);
        LoginIGUI lo = new LoginIGUI(um, tm, iv, iam, uc, aam);
        lo.run();
    }
}
