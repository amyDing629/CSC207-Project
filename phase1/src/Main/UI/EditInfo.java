package Main.UI;

import Inventory.Inventory;
import Inventory.Item;
import Main.GateWay;
import User.AdministrativeUser;
import User.User;
import User.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * [User Interface]
 * shows the interface that the user uses
 */
public class EditInfo {

    /**
     * read input
     */
    public Scanner sc;
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager a;

    /**
     * user in user system
     */
    public User user;

    /**
     * the object in the Item list of input gateway
     */
    public Inventory v;

    /**
     * the place we store information
     */
    public GateWay gw;

    /**
     * [constructor]
     * @param u the input user
     * @param gw the input gateway
     */
    public EditInfo(User u, GateWay gw){
        user=u;
        sc = new Scanner(System.in);
        a=new UserManager(gw);
        v = new Inventory(gw);
        this.gw=gw;
    }

    /**
     * run the system
     */
    public void run(){
        Scanner sc=new Scanner(System.in);
        int exit=-1;
        while(exit!=0) {
            System.out.println("--------------------\nEdit user information");
            System.out.println("Hello,user," + user.getUsername());
            System.out.println("Admin:"+user.getIsAdmin());
            System.out.println("Actions:\n1.Change password\n2.User Freeze System");
            if (user.getIsAdmin()) {
                System.out.print("3.Change user's limit\n4.add new item into the system\n");
                if(user.getUsername().equals("admin")){
                    System.out.print("5.create new admin\n");
                }
            }
            System.out.println("0.exit");
            System.out.print(">");
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------------");
            switch (input) {
                case 1:
                    System.out.println("Change password");
                    System.out.println("Type in the password you wanted to change, type 0 to quit.");
                    String input2=sc.nextLine();
                    if (!input2.equals("0")){
                        user.setPassword(input2);
                        System.out.println("Changed password succesfully!");
                    }
                    break;
                case 2:
                    System.out.println("Freeze user ");
                    System.out.println("Menu");
                    if(user.getIsFrozen()){
                        System.out.println("1.request to remove freeze");
                    }
                    if(user.getIsAdmin()){
                        System.out.println("2.Freeze user\n3.unfreeze user");
                    }
                    if(!user.getIsFrozen()&&!user.getIsAdmin()){
                        System.out.println("Returning to menu.....");
                        break;
                    }
                    int inputF=sc.nextInt();
                    sc.nextLine();
                    if(inputF==2) {
                        System.out.println("Type in the username of user you want to freeze, type 0 to quit.");
                        String input3 = sc.nextLine();
                        if (!input3.equals("0")) {
                            User ha = a.getUser(input3);
                            if (ha == null) {
                                System.out.println("Sorry there is no such user, returning to main menu.");
                            } else {
                                ((AdministrativeUser)a.getUser("admin")).freeze(ha);
                                System.out.println("user.User:" + ha.getUsername() + " account has been frozen");
                                System.out.println("Username: " + ha.getUsername());
                                System.out.println("Username: " + ha.getPassword());
                            }
                        }
                    }
                    else if(inputF==3){
                       ArrayList<ArrayList<String>> usa=gw.getApprovalUser();
                        for (int i=0;i<usa.size();i++) {
                            System.out.println("User" + i + ": " + usa.get(i).get(1));
                            System.out.println("Reason: " + usa.get(i).get(2));
                            System.out.println("**************************");
                        }
                        if(usa.size()==0){
                            System.out.println("Currently there is no user freeze request");
                        }
                        System.out.println("Enter the User number to approve,enter -1 to quit.");
                        String inputU = sc.nextLine();
                        int k = Integer.parseInt(inputU);
                        if(k<usa.size()&&k>-1){
                            a.getUser(usa.get(k).get(1)).setFrozen(false);
                            usa.remove(k);
                        }
                    }
                    else if(inputF==1){
                        System.out.println("Please enter the reason why you should unfreeze...enter -1 to quit");
                        String des=sc.nextLine();
                        if(!des.equals("-1")){
                            ArrayList<String> b=new ArrayList<>();
                            b.add("2");
                            b.add(user.getUsername());
                            b.add(des);
                            gw.getApprovalUser().add(b);
                                System.out.println("Request successfully");
                                System.out.println("Please wait for the administrator to approve");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Change user's limit");
                    System.out.println("Menu\n1.Change trade limit\n2.Change Incomplete Transaction limit\n3.Change the difference between borrowed and lend");
                    int input30=sc.nextInt();
                    sc.nextLine();
                    if(input30==1){
                        System.out.println("Which user do you want to change?");
                        String input31=sc.nextLine();
                        if(input31.equals("-1")){
                            break;
                        }
                        if(a.getUser(input31)==null){
                            System.out.println("You entered wrong username");
                            break;
                        }
                        User b=a.getUser(input31);
                        System.out.println("Enter a number to change");
                        int input33=sc.nextInt();
                        sc.nextLine();
                        b.setWeekTransactionLimit(input33);
                    }
                    else if(input30==2){
                        System.out.println("Which user do you want to change?enter -1 to break");
                        String input31=sc.nextLine();
                        if(input31.equals("-1")){
                            break;
                        }
                        if(a.getUser(input31)==null){
                            System.out.println("You entered wrong username");
                            break;
                        }
                        User b=a.getUser(input31);
                        System.out.println("Enter a number to change");
                        int input33=sc.nextInt();
                        sc.nextLine();
                        b.setIncompleteTransaction(input33);
                    }
                    else if(input30==3){
                        System.out.println("Which user do you want to change? enter -1 to break");
                        String input31=sc.nextLine();
                        if(input31.equals("-1")){
                            break;
                        }
                        if(a.getUser(input31)==null){
                            System.out.println("You entered wrong username");
                            break;
                        }
                        User b=a.getUser(input31);
                        System.out.println("Enter a number to change");
                        int input33=sc.nextInt();
                        sc.nextLine();
                        b.setDiff(input33);
                    }
                    break;
                case 5:
                    System.out.println("Create new admin");
                    System.out.println("Enter the username of new admin type 0 to quit.");
                    String input4=sc.nextLine();
                    if (!input4.equals("0")){
                        System.out.println("Now enter the password of new admin");
                        String input5555=sc.nextLine();
                        ((AdministrativeUser)user).addNewUser(input4,input5555);
                        System.out.println("New admin created successfully! Returning to menu");
                    }
                    break;
                case 4:
                    System.out.println("add new item into the system");
                    System.out.println("Menu:\n1.Add item for yourself.\n2.Approve request from users");
                    String inputA=sc.nextLine();
                    if(inputA.equals("1")) {
                        int exit1 = 0;
                        String name = "";
                        while (exit1 == 0) {
                            System.out.println("Type the name of the item");
                            name = sc.nextLine();
                            boolean t = false;
                            for (Item n : v.getLendingList()) {
                                if (n.getName().equals(name)) {
                                    t = true;
                                    break;
                                }
                            }
                            if (t) {
                                System.out.println("The item already exists, please enter the name again");
                            } else {
                                exit1 = 1;
                            }
                        }
                        System.out.println("Type the description of the item");
                        String des = sc.nextLine();
                        Item i = new Item(name, user.getUsername());
                        i.setDescription(des);
                        v.addItem(i);
                        user.addWishes(name);

                        System.out.println("Added successfully!");
                    }
                    else if(inputA.equals("2")){
                            int x=0;
                            while(x==0) {
                                ArrayList<ArrayList<String>> hii=gw.getApprovalItem();
                                for (int i=0;i<hii.size();i++) {
                                    System.out.println("Item " + i + ": " + hii.get(i).get(1));
                                    System.out.println("Description: " + hii.get(i).get(2));
                                    System.out.println("Owner: " + hii.get(i).get(3));
                                    System.out.println("**************************");
                                }
                                if(hii.size()==0){
                                    System.out.println("There is no item currently");
                                }
                                System.out.println("Enter the item number to approve,enter -1 to quit.");
                                String inputs = sc.nextLine();
                                int k = Integer.parseInt(inputs);
                                System.out.println(k);
                                if (k > -1 && k < (hii.size())) {
                                    Item i = new Item(hii.get(k).get(1), hii.get(k).get(3));
                                    i.setDescription(hii.get(k).get(2));
                                    a.getUser(hii.get(k).get(3)).addWishes(hii.get(k).get(1));
                                    v.addItem(i);
                                    gw.getApprovalItem().remove(k);
                                    System.out.println("Approve successfully");
                                }else if(k==-1){
                                    x=1;
                                }
                                else {
                                    System.out.println("You enter the wrong number!");
                                }
                            }
                    }
                case 0:
                    exit=0;
                    break;
            }
        }
    }
}
