package User;

import Inventory.*;
import Main.GateWay;
import Trade.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Login {
    public void mainUI () throws IOException {
        int a=-1;
        while (a!=0) {
            //print out the list of current users-------------------------------
            File file = new File("phase1/src/username.txt");
            FileEditor fe=new FileEditor();
            UserManager user=new UserManager();
            if(file.length() == 0){
                AdministrativeUser b = new AdministrativeUser("admin", "123", true);
                fe.addToUsers(b);
            }
            System.out.println("Users:");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(
                        "phase1/src/username.txt"));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    // read next line
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
                login();
            }
            else if (a == 2){
                register();
            }
            System.out.println("------------------------------");
        }

    }
    public void login() throws IOException {
        Scanner sc = new Scanner(System.in);
        FileEditor fe=new FileEditor();
        int input=0;
        while (input==0) {
            System.out.println("Please enter your account username!");
            System.out.print(">");
            String username = sc.nextLine();
            System.out.println("Please enter your password!");
            System.out.print(">");
            String password = sc.nextLine();
            UserManager a=new UserManager();
            //usermanger account verification? and returns a user object.
            if (a.verifyUser(username, password)) {
                while (true) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Hello,"+username);
                    System.out.println("Freeze Status: "+a.getUser(username).getIsFrozen());
                    System.out.println("trade.Trade limit: " + a.getUser(username).getTradeNumber() + "/" + a.getUser(username).getWeekTransactionLimit());
                    System.out.println("Incomplete trade limit: " + (a.getUser(username).getIncomplete()).size() + "/" + a.getUser(username).getIncompleteTransactionLimit());
                    System.out.println("Borrwed:");
                    for(int i=0;i<a.getUser(username).getBorrowed().size();i++){
                       System.out.print(" "+a.getUser(username).getBorrowed().get(i));
                    }
                    System.out.println("Lended:");
                    for(int i=0;i<a.getUser(username).getLend().size();i++){
                        System.out.print(" "+a.getUser(username).getLend().get(i));
                    }
                    System.out.println("**************************************************************");
                    System.out.println("Actions:\n1.Edit information\n2.Message\n3.Inventory.Inventory\n4.Message\n5.UserTradeUI\n6.Market\n0.quit to menu");
                    System.out.print(">");
                    int op = sc.nextInt();
                    sc.nextLine();
                    if (op == 1) {
                        editInfo(a.getUser(username));
                    } else if (op == 2) {
                        UserTradeUI(a.getUser(username));
                    } else if (op == 3) {
                        inventory(a.getUser(username));
                    } else if (op == 4) {
                        UserTradeUI(a.getUser(username));
                    } else if (op == 5) {
                        UserTradeUI(a.getUser(username));
                    } else if (op == 6) {
                        market(a.getUser(username));
                    } else if (op == 0) {
                        input=1;
                        break;
                    } else {
                        System.out.println("Sorry I dont undertand your command, plz enter it again");
                    }
                }
            } else {
                System.out.println("You have incorrect username or password, please try to login again, enter any number to continue. enter 1 to exit.");
                input=sc.nextInt();
                sc.nextLine();
            }
        }
    }
    public void register() throws IOException {
        Scanner sc = new Scanner(System.in);
        FileEditor fe=new FileEditor();
        int input=0;
        while(input!=1) {
            System.out.println("--------------------\nRegister");
            System.out.println("Please enter your username!");
            System.out.print(">");
            String username = sc.nextLine();
            System.out.println("Please enter your password!");
            System.out.print(">");
            String password = sc.nextLine();
            UserManager a=new UserManager();
            //usermanger username verification?
            if (a.getUser(username) == null) {
                User user1 = new User(username, password, false);
                fe.addToUsers(user1);
                System.out.println("Your account has been successfully created!");
                System.out.println("Your id: " + user1.getId());
                System.out.println("Your username: " + user1.getUsername());
                System.out.println("Your password: " + user1.getPassword());
                input=1;
            }
            else{
                System.out.println("The username already exists, please try to register again, enter any number to continue. Enter 1 to exit.");
                input=sc.nextInt();
                sc.nextLine();
            }
        }
    }
    public void editInfo(User user) throws IOException {
        Scanner sc=new Scanner(System.in);
        int exit=-1;
        while(exit!=0) {
            System.out.println("--------------------\nEdit user information");
            System.out.println("Hello,user," + user.getUsername());
            System.out.println("Admin:"+user.getIsAdmin());
            System.out.println("Actions:\n1.Change username\n2.Change password");
            if (user.getIsAdmin()) {
                System.out.print("3.Freeze a user\n4.Change user's limit\n5.add new item into the system\n");
                if(user.getId()==null){
                    System.out.print("6.Set user into admin\n");
                }
            }
            System.out.println("0.exit");
            System.out.print(">");
            UserManager a=new UserManager();
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------------");
            switch (input) {
                case 1:
                    System.out.println("Change username");
                    System.out.println("Type in the username you wanted to change, type 0 to quit.");
                    String input1=sc.nextLine();
                    if (!input1.equals("0")){
                        user.setUsername(input1);
                    }
                    break;
                case 2:
                    System.out.println("Change password");
                    System.out.println("Type in the password you wanted to change, type 0 to quit.");
                    String input2=sc.nextLine();
                    if (!input2.equals("0")){
                        user.setPassword(input2);
                    }
                    break;
                case 3:
                    System.out.println("Freeze a user");
                    System.out.println("Type in the username of user you want to freeze, type 0 to quit.");
                    String input3=sc.nextLine();
                    if (!input3.equals("0")){
                        User ha=a.getUser(input3);
                        if (ha==null){
                            System.out.println("Sorry there is no such user, returning to main menu.");
                        }
                        else{
                            ((AdministrativeUser)user).freeze(ha);
                            System.out.println("user.User:"+ha.getUsername()+" account has been frozen");
                            System.out.println("Username: "+ha.getUsername());
                            System.out.println("Username: "+ha.getPassword());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Change user's limit");
                    break;
                case 6:
                    System.out.println("Set a user into admin");
                    System.out.println("Type in the user you want to set to admin, type 0 to quit.");
                    String input4=sc.nextLine();
                    if (!input4.equals("0")){
                        User ha=a.getUser(input4);
                        if (ha==null){
                            System.out.println("Sorry there is no such user, returning to main menu.");
                        }
                        else{((AdministrativeUser)user).addNewUser(ha.getUsername(),ha.getPassword());}
                    }
                    break;
                case 5:
                    Inventory v = new Inventory();
                    System.out.println("add new item into the system");
                    System.out.println("Menu:\n1.Add item your self.\n2.Approve request from users");
                    String inputa=sc.nextLine();
                    if(inputa.equals("1")) {
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
                                System.out.println("The item is already exist,please enter the name again");
                            } else {
                                exit1 = 1;
                            }
                        }
                        System.out.println("Type the description of the item");
                        String des = sc.nextLine();
                        Item i = new Item(name, user.getUsername());
                        i.setDescription(des);
                        v.addItem(i);
                    }
                    else if(inputa.equals("2")){
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(
                                    "phase1/src/ItemApproval.txt"));
                            String line = reader.readLine();
                            ArrayList<ArrayList<String>> hii=new ArrayList<>();
                            while (line != null) {
                                System.out.println(line);
                                String[] parts = line.split(",");
                                ArrayList<String> hi= new ArrayList<>(Arrays.asList(parts));
                                hii.add(hi);
                                line = reader.readLine();
                            }
                            reader.close();
                            int x=0;
                            for (int i=0;i<hii.size();i++) {
                                System.out.println("Inventory.Item " + i + ": " + hii.get(i).get(0));
                                System.out.println("Description: " + hii.get(i).get(1));
                                System.out.println("Owner: " + hii.get(i).get(2));
                                System.out.println("**************************");
                            }
                            while(x==0) {
                                System.out.println("Enter the item number to approval,enter -1 to quit.");
                                String inputs = sc.nextLine();
                                int k = Integer.parseInt(inputs);
                                System.out.println(k);
                                if (k > -1 && k < (hii.size())) {
                                    Item i = new Item(hii.get(k).get(0), hii.get(k).get(2));
                                    i.setDescription(hii.get(k).get(1));
                                    v.addItem(i);
                                    hii.remove(k);
                                    System.out.println("Approve successfully");
                                }else if(k==-1){
                                    x=1;
                                }
                                else {
                                    System.out.println("You enter the wrong number!");
                                }
                            }
                            PrintWriter writer = new PrintWriter("phase1/src/ItemApproval.txt");
                            writer.print("");
                            for (ArrayList<String> strings : hii) {
                                writer.write(strings.get(0)+","+strings.get(1)+","+strings.get(2));
                            }
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                case 0:
                    exit=0;
                    break;
            }
        }
    }
    public void inventory(User user){
        Scanner sc=new Scanner(System.in);
        Inventory iv=new Inventory();
        int exit=-1;
        while(exit!=0) {
            System.out.println("--------------------\nInventory.Inventory");
            System.out.println("Hello,user," + user.getUsername());
            System.out.println("Actions:\n1.Lend wishes\n2.Borrow wishes\n3.edit lend wishes\n4.edit borrow wishes\n0.exit");
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------------");
            switch (input) {
                case 1:
                    System.out.println("Lend wishes");
                    List<String> lw=user.getWishLend();
                    for (int i=0;i<lw.size();i++){
                        System.out.println("item:"+i+1+" "+lw.get(i));
                    }
                    break;
                case 2:
                    System.out.println("Borrow wishes");
                    List<String> lb=user.getWishLend();
                    for (int i=0;i<lb.size();i++){
                        System.out.println("wish borrow item:"+i+" "+lb.get(i));
                    }
                    System.out.println("Select a item to start the trade! enter -1 to quit to menu");
                    String input22=sc.nextLine();
                    if(input22.equals("-1")){
                        break;
                    }
                    Item k=iv.getItem(input22);
                    if (k!=null){
                        try {
                            RequestTradeUI ru = new RequestTradeUI(user, k);
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
                        wishLendAdd(user);
                    }else if(input44.equals("2")){
                        System.out.println("Input the item you wanted to delete");
                        String input43=sc.nextLine();
                        if (user.getWishLend().contains(input43)) {
                            user.removeLWishes(input43);
                        }
                        else{
                            System.out.println("The item does not contain in your wish lend list");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Edit borrow wishes");
                    System.out.println("Menu:\n1.add wish\n2.delete wish");
                    String input55=sc.nextLine();
                    if (input55.equals("1")) {
                        InventoryUI iui = new InventoryUI(user);
                        iui.run();
                    }else if(input55.equals("2")){
                        System.out.println("Input the item you wanted to delete");
                        String input54=sc.nextLine();
                        if (user.getWishBorrow().contains(input54)) {
                            user.removeBWishes(input54);
                        }
                        else{
                            System.out.println("The item does not contain in your wish borrow list");
                        }
                    }
                    break;
                case 0:
                    exit=0;
                    break;
            }
        }
    }
    public void UserTradeUI(User user) throws IOException {
        Scanner sc=new Scanner(System.in);
        int escape=0;
        List<Trade> iL=user.getIncomplete();
        List<Trade> iU=user.getUnconfirmed();
        while (escape==0) {
            System.out.println("--------------------\nMessage");
            System.out.println("Hello,user" + user.getUsername());
            System.out.println("Menu: 1.confirm trades\n2.complete trade\n3.Trade History\n0.quit");
            int input1 = sc.nextInt();
            sc.nextLine();
            switch (input1) {
                case 1:
                    for(int i=0;i<iU.size();i++){
                        System.out.println((i+1)+". "+iU.get(i).toString());
                    }
                    System.out.println("Which trade do you want to confirm? select trade ID to confirm");
                    int input2 = sc.nextInt();
                    sc.nextLine();
                    if((input2<(iU.size()+1))&&(input2>0)){
                        iU.get(input2-1).setStatus("confirm trade");
                    }
                    else{
                        System.out.println("Wrong Number, returning to UserTrade menu....");
                    }
                    break;
                case 2:
                    for(int i=0;i<iL.size();i++){
                        System.out.println((i+1)+". "+iL.get(i).toString());
                    }
                    System.out.println("Which trade do you want to complete? select trade ID to confirm");
                    int input3 = sc.nextInt();
                    sc.nextLine();
                    if((input3<(iL.size()+1))&&(input3>0)){
                        TradeUI tu=new TradeUI(user,iL.get(input3-1).getId());
                        tu.run();
                    }
                    else{
                        System.out.println("Wrong Number, returning to UserTrade menu....");
                    }
                case 3:
                    System.out.println("Hi user: "+user.getUsername());
                    System.out.println("Compeleted past trades:");
                    List<Trade> tHis=user.getTradeHistoryTop();
                    System.out.println("****************");
                    for(int i=0;i<tHis.size();i++){
                        System.out.println((i+1)+". "+tHis.get(i).toString());
                    }
                    System.out.println("****************");
                    System.out.println("Most frequent user:");
                    for(User a:user.getFrequentUser()){
                        System.out.println(a.username);
                    }
                case 0:
                    escape=1;
                    break;
            }

        }
    }
    public void wishLendAdd(User user){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the name of the item");
        String input=sc.nextLine();
        System.out.println("Please enter the description of the item");
        String input1=sc.nextLine();
        try {
            FileWriter myWriter = new FileWriter("phase1/src/ItemApproval.txt");
            myWriter.write(input+","+input1+","+user.getUsername());
            myWriter.close();
            System.out.println("Successfully");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void market(User user){
        Scanner sc=new Scanner(System.in);
        FileEditor fe=new FileEditor();
        System.out.println("Hello "+ user.username);
        UserManager a=new UserManager();
        for (User b: GateWay.users){
            System.out.println("user.User 1");
            for(String c:user.getWishBorrow()){
                System.out.println("Inventory.Item:"+c);
            }
            System.out.println("--------------------------");
        }
    }
    public void selectALendItem(User user){
        Scanner sc=new Scanner(System.in);
        System.out.println("Hello,"+ user.username);
        System.out.println("Please select the item to trade");
        for (String a:user.getWishLend()){
            System.out.println("Inventory.Item 1:"+a);
        }
        int exit=0;
        while (exit==0) {
            String ip = sc.nextLine();
            boolean t=false;
            for (String a:user.getWishLend()){
                if (a.equals(ip)) {
                    t = true;
                    break;
                }
            }
            if (t){
                exit=1;
            }
            else {
                System.out.println("You enter the wrong name,press 1 to exit.press anything else to try again.");
                String k=sc.nextLine();
                if(k.equals("0")){
                    exit=1;
                }

            }
        }
    }
}
