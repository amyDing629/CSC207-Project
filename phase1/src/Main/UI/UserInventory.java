package Main.UI;

import Inventory.InventoryUI;
import Inventory.Item;
import Main.GateWay;
import Trade.Trade;
import User.User;
import User.UserManager;
import Trade.*;
import Inventory.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserInventory {
    public Scanner sc;
    public UserManager um;
    public User user;
    public Inventory iv;
    public TradeManager tm;

    public UserInventory(User u, GateWay gw) {
        user = u;
        sc = new Scanner(System.in);
        um=new UserManager(gw);
        iv=new Inventory(gw);
        tm = new TradeManager(gw);
    }

    public void run() throws IOException {
        int exit=-1;
        while(exit!=0) {
            System.out.println("--------------------\nInventory.Inventory");
            System.out.println("Hello," + user.getUsername());
            System.out.println("Actions:\n1.Lend wishes\n2.Borrow wishes\n3.Edit lend wishes\n4.Edit borrow wishes\n0.exit");
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------------");
            switch (input) {
                case 1:
                    System.out.println("Lend wishes");
                    List<String> lw=user.getWishLend();
                    for (String s : lw) {
                        System.out.println("item:" + s);
                    }
                    break;
                case 2:
                    System.out.println("Borrow wishes");
                    List<String> lb=user.getWishBorrow();
                    for (int i=0;i<lb.size();i++){
                        System.out.println("wish borrow item:"+i+" "+lb.get(i));
                    }
                    System.out.println("Select a item to start the trade! Enter -1 to quit to menu");
                    String input22=sc.nextLine();
                    if(input22.equals("-1")){
                        break;
                    }
                    Item k=iv.getItem(input22);
                    if (k!=null){
                        try {
                            RequestTradeUI ru = new RequestTradeUI(user, k, tm, um, iv);
                            ru.run();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Edit lend wishes");
                    System.out.println("Menu:\n1.add wish\n2.delete wish");
                    String input44=sc.nextLine();
                    if (input44.equals("1")) {
                        System.out.println("Please enter the name of the item");
                        String input25=sc.nextLine();
                        System.out.println("Please enter the description of the item");
                        String input1=sc.nextLine();
                        try {
                            String data="1"+"/"+input25+"/"+input1+"/"+user.getUsername()+"\n";
                            File file = new File("phase1/src/ItemApproval.txt");
                            FileWriter fr = new FileWriter(file, true);
                            BufferedWriter br = new BufferedWriter(fr);
                            br.write(data);
                            br.close();
                            fr.close();
                            System.out.println("Request successfully");
                            System.out.println("Please wait for the administrator to approve");
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    }else if(input44.equals("2")){
                        List<String> lw1=user.getWishLend();
                        for (String s : lw1) {
                            System.out.println("item:" + s);
                        }
                        System.out.println("Input the item you wanted to delete");
                        String input43=sc.nextLine();
                        if (user.getWishLend().contains(input43)) {
                            user.removeLWishes(input43);
                            iv.deleteItem(iv.getItem(input43));
                        }
                        else{
                            System.out.println("The item does not exist in your wish lend list");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Edit borrow wishes");
                    System.out.println("Menu:\n1.add wish\n2.delete wish");
                    String input55=sc.nextLine();
                    if (input55.equals("1")) {
                        InventoryUI iui = new InventoryUI(user, iv);
                        iui.run();
                    }else if(input55.equals("2")){
                        List<String> bw1=user.getWishBorrow();
                        for (String s : bw1) {
                            System.out.println("item:" + s);
                        }
                        System.out.println("Input the item you wanted to delete");
                        String input54=sc.nextLine();
                        if (user.getWishBorrow().contains(input54)) {
                            user.removeBWishes(input54);
                            iv.deleteItem(iv.getItem(input54));
                        }
                        else{
                            System.out.println("The item does not exist in your wish borrow list");
                        }
                    }
                    break;
                case 0:
                    exit=0;
                    break;
            }
        }
    }
}
