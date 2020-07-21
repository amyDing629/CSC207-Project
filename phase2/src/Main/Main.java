package Main;

import Inventory.Inventory;
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
        int a=-1;
        Inventory iv = new Inventory();
        UserManager um = new UserManager();
        TradeManager tm = new TradeManager();
        ItemApprovalManager iam = new ItemApprovalManager();
        DataAccessFull uaf = new DataAccessFull(um, tm, iv, iam);
        File file = new File("phase1/src/username.txt");

        if(file.length() == 0){
            AdministrativeUser b = new AdministrativeUser("admin", "123", true, tm, um);
            um.addUser(b);
            new UserDataAccess(um).updateFile();
        }
        uaf.readFile(tm, iv, um);
        while (a!=0) {
            //print out the list of current users-------------------------------
            //gw = new GateWay();
            //w = new DataAccessFull(gw);
            //w.readFile();
            System.out.println("Users:");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(
                        "phase1/src/username.txt"));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //------------------------------------------------------------------
            System.out.println("\nMenu:\n1.login\n2.register\n0.quit");
            System.out.println("Please enter the number only.");
            Scanner sc = new Scanner(System.in);
            System.out.print(">");
            a = sc.nextInt();
            sc.nextLine();
            if (a==1){
                //System.out.println(gw.getUsers().get(3).getIsFrozen());
                Login login=new Login(um, tm, iv, iam);
                login.run();
            }
            else if (a == 2){
                Register reg=new Register(um);
                reg.run();
            }
            System.out.println("------------------------------");
            uaf.updateFile();
        }
    }
}
