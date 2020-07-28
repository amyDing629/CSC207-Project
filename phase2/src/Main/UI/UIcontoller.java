package Main.UI;
import Inventory.Inventory;
import Inventory.Item;
import Trade.TradeManager;
import User.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UIcontoller {
    public UserManager um;
    public AdminActivityManager am;
    public TradeManager tm;
    public ItemApprovalManager iam;
    Inventory iv;

    public UIcontoller(UserManager um, AdminActivityManager am,TradeManager tm,ItemApprovalManager iam,Inventory iv){
        this.um=um;
        this.am=am;
        this.tm=tm;
        this.iam=iam;
        this.iv=iv;
    }
    public void checkFrozen(ClientUser user){
        if(um.readDiff(user)>=um.getDiff(user)){
            System.out.println("You have been freeze due to exceed difference between borrow and lend");
            am.setFreeze(user,true);
        }
        else if(am.incompleteTransaction(user)){
               System.out.println("You have been freeze due to maximum incomplete transaction");
            am.setFreeze(user,true);
        }
        else if(am.tradeLimit(user)){
            System.out.println("You have been freeze due to maximum trade limit");
            am.setFreeze(user,true);
        }
    }

    public void checkFileEmpty(File file){
        if (file.length() == 0) {
            ClientUser b = new ClientUser("admin", "123", true);
            um.addUser(b);
            new UserDataAccess(um).updateFile();
        }
    }

    public void printAllUserInfo(){
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
    }

    public int getNumber(String words){
        Scanner sc=new Scanner(System.in);
        System.out.println(words);
        System.out.print(">");
        int a=sc.nextInt();
        return a;
    }
    public String getString(String words){
        Scanner sc=new Scanner(System.in);
        System.out.println(words);
        System.out.print(">");
        return sc.nextLine();
    }

    public void addApproval(String id,String s,String d){
        ArrayList<String> b = new ArrayList<>();
        b.add(id);
        b.add(s);
        b.add(d);
        iam.getUserApproval().add(b);
    }
//    public boolean checkItemExist(String item){
//        for (Item n : iv.getLendingList()) {
//            if (n.getName().equals(item)) {
//                return true;
//            }
//        }
//        return false;
//    }
    public void UserDisplayStatus(ClientUser user){
        System.out.println("Hello," + user.getUsername());
        System.out.println("Admin:"+um.getIsAdmin(user));
        checkFrozen(user);
        System.out.println("Freeze Status: " + um.getIsFrozen(user));
        System.out.println("Trade limit: " + tm.getTradeNumber(user) + "/"
                + um.getWeekTransactionLimit(user));
        System.out.println("Incomplete trade limit: " + (tm.getIncomplete(user).size()
                + "/" + um.getIncompleteTransactionLimit(user)));
        System.out.println("Difference between borrow and lend:"+user.readDiff()+"/"+um.getDiff(user));
        System.out.println("**************************************************************");
    }
}
