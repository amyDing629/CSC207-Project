package Main.UI;

import Inventory.Item;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;
import Trade.*;
import Inventory.*;
import java.io.IOException;
import java.util.*;
/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class UserInventory {
    /**
     * read input
     */
    public Scanner sc;
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager um;
    /**
     * user in user system
     */
    public ClientUser user;
    /**
     * the object in the Item list of input gateway
     */
    public Inventory iv;
    /**
     * the object that edits the Item list of input gateway
     */
    public TradeManager tm;
    /**
     * the place we store information
     */
    public ItemApprovalManager iam;
    UIcontoller uc;
    /**
     * [constructor]
     * @param u  the input user
     * @param uc
     */
    public UserInventory(ClientUser u, UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, UIcontoller uc) {
        user = u;
        sc = new Scanner(System.in);
        this.um=um;
        this.tm = tm;
        this.iv = iv;
        this.iam = iam;
        this.uc=uc;
    }

    /**
     * run the system
     */
    public void run() throws IOException {
        int exit=-1;
        while(exit!=0) {
            uc.UserDisplayStatus(user);
            System.out.println("--------------------\nInventory");
            System.out.println("Hello," + um.getUsername(user));
            System.out.println("Actions:\n1.Lend wishes\n2.Borrow wishes\n3.Edit lend wishes\n4.Edit borrow wishes\n0.exit");
            int input =uc.getNumber("Please enter a number!");
            System.out.println("-----------------------------");
            switch (input) {
                case 1:
                    System.out.println("Lend wishes");
                    List<String> lw=um.getWishLend(user);
                    System.out.println(um.getWishLend(user));
                    for (String s : lw) {
                        System.out.println("item:" + s);
                    }
                    break;
                case 2:
                    System.out.println("Borrow wishes");
                    List<String> lb=um.getWishBorrow(user);
                    for (String value : lb) {
                        System.out.println("wish borrow item: " + value);
                    }
                    String input22=uc.getString("Select a item to start the trade! Enter -1 to quit to menu");
                    if(input22.equals("-1")){
                        break;
                    }
                    Item k=iv.getItem(input22);
                    if (k!=null){
                        RTradeGUI ru = new RTradeGUI(user, k, tm, um, iv);
                        ru.run();
                    }
                    break;
                case 3:
                    System.out.println("Edit lend wishes");
                    System.out.println("Menu:\n1.add wish\n2.delete wish");
                    String input44=uc.getString("Please enter a number!");
                    if (input44.equals("1")) {
                        String input25=uc.getString("Please enter the name of the item");
                        System.out.println("Please enter the description of the item");
                        String input1=uc.getString("Please enter the description of the item");
                            ArrayList<String> b= new ArrayList<>();
                            b.add("1");
                            b.add(input25);
                            b.add(input1);
                            b.add(um.getUsername(user));
                            iam.getItemApproval().add(b);
                            System.out.println("Request successfully");
                            System.out.println("Please wait for the administrator to approve");
                    }else if(input44.equals("2")){
                        List<String> lw1=um.getWishLend(user);
                        for (String s : lw1) {
                            System.out.println("item:" + s);
                        }
                        String input43=uc.getString("Input the item you wanted to delete");
                        if (um.getWishLend(user).contains(input43)) {
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
                    String input55=uc.getString("Please enter a number!");
                    if (input55.equals("1")) {
                        //WishBorrowAddGUI iui = new WishBorrowAddGUI(user, iv);
                        //iui.run();
                    }else if(input55.equals("2")){
                        List<String> bw1=um.getWishBorrow(user);
                        for (String s : bw1) {
                            System.out.println("item:" + s);
                        }
                        System.out.println("Input the item you wanted to delete");
                        String input54=uc.getString("Input the item you wanted to delete");
                        if (um.getWishBorrow(user).contains(input54)) {
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
