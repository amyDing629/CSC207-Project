import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws IOException {
        int a=0;
        while (a!=-1) {
            System.out.println("Users:");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(
                        "username.txt"));
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
            System.out.println("\nMenu:\n 1.login\n 2.register");
            Scanner sc = new Scanner(System.in);
            System.out.print(">");
            a = sc.nextInt();
            sc.nextLine();
            System.out.println("------------------------------");
            if (a==1){
                System.out.println("Please enter your account username!");
                System.out.print(">");
                String username = sc.nextLine();
                System.out.println("Please enter your password!");
                System.out.print(">");
                String password = sc.nextLine();

                //usermanger account verification? and returns a user object.

                System.out.println("Notifications:\n");// display all user's notifications.

                System.out.println("Actions:\n1.Trade\n2.Message");

            }
            else if (a == 2){
                System.out.println("Please enter your username!");
                System.out.print(">");
                String username = sc.nextLine();
                System.out.println("Please enter your password!");
                System.out.print(">");
                String password = sc.nextLine();
                //usermanger username verification?
                FileWriter fw = new FileWriter("username.txt");
                fw.write(username);
                fw.close();
                ClientUser user1 = new ClientUser(username, password);
                System.out.println("Your account has been successfully created!");
                System.out.println("Your id: " + user1.getId());
                System.out.println("Your username: " + user1.getUsername());
                System.out.println("Your password: " + user1.getPassword());
            }
            System.out.println("------------------------------");
        }
    }
}

