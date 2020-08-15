package Main;
import User.Adapter.ClientUserController;
import User.GUI.LoginGUI;
import java.io.File;
public class mainRun {

    public static void main(String[] args){

        DataAccessFull uaf = new DataAccessFull();
        ClientUserController uc = new ClientUserController();
        uc.checkFileEmpty(new File("phase2/src/user.ser"));
        uaf.readFile();

        // start with three login window (for easy test)
        LoginGUI user1 = new LoginGUI();
        user1.run();

        LoginGUI user2 = new LoginGUI();
        user2.run();

        LoginGUI admin = new LoginGUI();
        admin.run();
    }
}
