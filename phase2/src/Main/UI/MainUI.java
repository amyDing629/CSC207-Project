package Main.UI;

//import Inventory.UseCase.Inventory;
//import Trade.UseCase.TradeManager;
//import User.*;
//import com.sun.deploy.ui.DialogTemplate;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Scanner;

//public class MainUI {
//    UserManager um;
//    TradeManager tm;
//    DataAccessFull w;
//    ItemApprovalManager iam;
//    UIcontoller uc;
//    Inventory iv;
//    AdminActivityManager aam;
//    public MainUI(UserManager um, TradeManager tm, DataAccessFull w, Inventory iv, ItemApprovalManager iam, UIcontoller uc, AdminActivityManager aam) {
//        this.um = um;
//        this.tm = tm;
//        this.w = w;
//        this.iam=iam;
//        this.uc=uc;
//        this.iv=iv;
//        this.aam=aam;
//    }
//
//    public void run() throws IOException {
//        int a = -1;
//        uc.checkFileEmpty(new File("phase1/src/username.txt"));
//        w.readFile(tm, iv, um);
//        while (a != 0) {
//            uc.printAllUserInfo();
//            System.out.println("\nMenu:\n1.login\n2.register\n0.quit");
//            a=uc.getNumber("Please enter the number only.");
//            if (a == 1) {
//                Login login = new Login(um, tm, iv, iam,aam,uc);
//                login.run();
//            } else if (a == 2) {
//                Register reg = new Register(um,uc);
//                reg.run();
//            }
//            System.out.println("------------------------------");
//            w.updateFile();
//        }
//    }
//}