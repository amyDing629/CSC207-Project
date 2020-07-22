package Main;

import Inventory.Inventory;
import Main.UI.ApprovalDataAccess;
import Main.UI.MainUI;
import Main.UI.Register;
import Trade.TradeManager;
import User.AdministrativeUser;
import User.ItemApprovalManager;
import User.UserDataAccess;
import User.UserManager;
import Main.UI.Login;
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
        MainUI mui=new MainUI(um,tm,iv,iam);
        mui.run();
    }
}
