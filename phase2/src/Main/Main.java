package Main;

import Inventory.Inventory;
import Main.UI.*;
import Trade.TradeManager;
import User.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     * @param args the arguments for run the system
     * run the system
     */
    public static void main(String[] args) throws IOException {
        Inventory iv = new Inventory();
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        ItemApprovalManager iam = new ItemApprovalManager();
        DataAccessFull uaf = new DataAccessFull(um, tm, iv, iam);
        AdminActivityManager aam=new AdminActivityManager(tm,um);
        UIcontoller uc=new UIcontoller(um,aam,tm,iam,iv);
        MainUI mui=new MainUI(um,tm,uaf,iv,iam,uc,aam);
        mui.run();
    }
}
