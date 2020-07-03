import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    public void mainUI () throws IOException {
        int a=-1;
        while (a!=0) {
            //print out the list of current users-------------------------------
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
                    System.out.println("Actions:\n1.Edit information\n2.Message\n3.Inventory\n4.Message\n5.Trade History\n0.quit to menu");
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
            System.out.println("Actions:\n1.Change username\n2.Change password");
            if (user.getIsAdmin()) {
                user=(AdministrativeUser) user;
                System.out.print("3.Freeze a user\n4.Change user's limit\n");
                if(user.getId()==1){
                    System.out.print("5.Set user into admin\n");
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
                case 5:
                    System.out.println("Set a user into admin");
                    System.out.println("Type in the user you want to set to admin, type 0 to quit.");
                    String input4=sc.nextLine();
                    if (!input4.equals("0")){
                        ClientUser ha= (ClientUser) a.getUser(input4);
                        if (ha==null){
                            System.out.println("Sorry there is no such user, returning to main menu.");
                        }
                        else{((AdministrativeUser)user).addNewUser(ha.username,ha.password);}
                    }
                    break;
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
                        System.out.println("wish lend item:"+i+" "+lw.get(i));
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
                    break;
                case 4:
                    System.out.println("Edit borrow wishes");
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
}
