import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    public void mainUI () throws IOException {
        int a=-1;
        while (a!=0) {
            //print out the list of current users-------------------------------
            File file = new File("phase1/src/username.txt");
            UserManager user=new UserManager();
            if(file.length() == 0){
                AdministrativeUser b = new AdministrativeUser("admin", "123", true);
                user.addUser(b);
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
            System.out.println("\nMenu:\n 1.login\n 2.register\n 0.quit");
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
                int exit = 0;
                while (exit != 1) {
                    System.out.println("Notifications:\n");// display all user's notifications.
                    System.out.println("Actions:\n1.Edit information\n2.Message\n3.Inventory\n4.Message\n5.Trade History\n6.Market\n0.quit to menu");
                    System.out.print(">");
                    int op = sc.nextInt();
                    sc.nextLine();
                    if (op == 1) {
                        editInfo(a.getUser(username));
                    } else if (op == 2) {
                        message(a.getUser(username));
                    } else if (op == 3) {
                        inventory(a.getUser(username));
                    } else if (op == 4) {
                        message(a.getUser(username));
                    } else if (op == 5) {
                        tradeHistory(a.getUser(username));
                    } else if (op == 6) {
                        market(a.getUser(username));
                    } else if (op == 0) {
                        exit = 1;
                    } else {
                        System.out.println("Sorry I dont undertand your command, plz enter it again");
                    }
                    exit = 0;
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
                ClientUser user1 = new ClientUser(username, password, false);
                a.addUser(user1);
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
                if(user.getId()==1){
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
                        ClientUser ha= (ClientUser) a.getUser(input3);
                        if (ha==null){
                            System.out.println("Sorry there is no such user, returning to main menu.");
                        }
                        else{((AdministrativeUser)user).freeze(ha);}
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
                        ClientUser ha= (ClientUser) a.getUser(input4);
                        if (ha==null){
                            System.out.println("Sorry there is no such user, returning to main menu.");
                        }
                        else{((AdministrativeUser)user).addNewUser(ha.getUsername(),ha.getPassword());}
                    }
                    break;
                case 5:
                    System.out.println("add new item into the system");
                    int exit1=0;
                    Inventory v=new Inventory();
                    String name="";
                    while (exit1==0){
                        System.out.println("Type the name of the item");
                        name=sc.nextLine();
                        Boolean t=false;
                        for(Item n:v.getLendingList()){
                            if (n.getName().equals(name)){
                                t=true;
                            }
                        }
                        if (t){
                            System.out.println("The item is already exist,please enter the name again");
                        }
                        else{
                            exit1=1;
                        }
                    }
                    System.out.println("Type the description of the item");
                    String des=sc.nextLine();
                    Item i=new Item(name,user.getUsername());
                    i.setDescription(des);
                    v.addItem(i);

                case 0:
                    exit=0;
                    break;
            }
        }
    }
    public void inventory(User user){
        Scanner sc=new Scanner(System.in);
        int exit=-1;
        while(exit!=0) {
            System.out.println("--------------------\nInventory");
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
                    break;
                case 3:
                    System.out.println("Edit lend wishes");
                    System.out.println("Menu:\n1.add wish\n2.delete wish");
                    String input44=sc.nextLine();
                    if (input44.equals("1")) {

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
    public void message(User user){

    }
    public void tradeHistory(User user){

    }
    public void wishLendAdd(User user){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the name of the item");
        String input=sc.nextLine();
        System.out.println("Please enter the description of the item");
        String input1=sc.nextLine();


    }
    public void market(User user) throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Hello "+ user.username);
        UserManager a=new UserManager();
        a.updateFile();
        for (User b:a.splitUser(a.readFile())){
            System.out.println("User 1");
            for(String c:user.getWishBorrow()){
                System.out.println("Item:"+c);
            }
            System.out.println("--------------------------");
        }
        int exit=0;
        while (exit==0) {
            String ip = sc.nextLine();
            System.out.println("Please enter the name of user you wants to trade with.");
            if (a.getUser(ip) != null) {
                selectALendItem(user);
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
    public void selectALendItem(User user){
        Scanner sc=new Scanner(System.in);
        System.out.println("Hello,"+ user.username);
        System.out.println("Please select the item to trade");
        for (String a:user.getWishLend()){
            System.out.println("Item 1:"+a);
        }
        int exit=0;
        while (exit==0) {
            String ip = sc.nextLine();
            Boolean t=false;
            for (String a:user.getWishLend()){
                if (a.equals(ip)){
                    t=true;
                }
            }
            if (t=true){
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
