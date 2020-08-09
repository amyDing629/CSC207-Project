package User;

import User.Entity.ClientUser;
import User.UseCase.UserManager;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        UserManager a = new UserManager();
        ClientUser b = new ClientUser("admin", "123", true);
        ClientUser c = new ClientUser("admin2", "1234", false);
        ClientUser d = new ClientUser("admin3", "12345", false);
        a.getUserList().add(b);
        a.getUserList().add(c);
        a.getUserList().add(d);
//        UserDataAccess uda = new UserDataAccess(a);
//        uda.serialize();
//        System.out.println(uda.deSerialize());
    }
}