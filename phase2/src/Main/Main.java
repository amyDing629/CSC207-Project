package Main;

import Inventory.Inventory;
import Main.UI.MainUI;
import Trade.TradeManager;
import User.*;

import java.io.IOException;

public class Main {
    /**
     * @param args the arguments for run the system
     * run the system
     */
    public static void main(String[] args) throws IOException {
//        Inventory iv = new Inventory();
//        UserManager um = new UserManager();
//        TradeManager tm = new TradeManager();
//        ItemApprovalManager iam = new ItemApprovalManager();
//        DataAccessFull uaf = new DataAccessFull(um, tm, iv, iam);
//        MainUI mui=new MainUI(um,tm,iv,iam);
//        mui.run();
        LoginIGUI li = new LoginIGUI();
        li.run();
    }
}
