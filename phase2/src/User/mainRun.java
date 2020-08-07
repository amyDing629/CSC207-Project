package User;

import Inventory.*;
//import Main.DataAccessFull;
import Main.DataAccessFull;
import Main.UI.UIcontoller;
import Trade.TradeManager;

import java.io.File;

public class mainRun {

    public static void main(String args[]){
        Inventory iv=new Inventory();
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        ItemApprovalManager iam = new ItemApprovalManager();
        DataAccessFull uaf = new DataAccessFull(um, tm, iv, iam);
        AdminActivityManager aam=new AdminActivityManager(tm,um);
        UIcontoller uc=new UIcontoller(um,aam,tm,iam,iv);
        uc.checkFileEmpty(new File("phase1/src/username.txt"));
        uaf.readFile(tm,iv,um);
        LoginIGUI lo=new LoginIGUI(um,tm,iv,iam,uc,aam);
        lo.run();
    }
}
