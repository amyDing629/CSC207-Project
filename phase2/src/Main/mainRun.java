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

        LoginGUI admin = new LoginGUI();
        admin.run();

        LoginGUI login1 = new LoginGUI();
        login1.run();

        LoginGUI login2 = new LoginGUI();
        login2.run();

    }
}
