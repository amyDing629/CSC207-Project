package User;
import Main.DataAccessFull;
import User.Adapter.ClientUserController;
import User.GUI.LoginGUI;
import java.io.File;
public class mainRun {

    public static void main(String[] args){

//        DataAccessFull uaf = new DataAccessFull();
//        ClientUserController uc = new ClientUserController();
//        uc.checkFileEmpty(new File("phase2/src/user.ser"));
//        uaf.readFile();

        LoginGUI lo = new LoginGUI();
        lo.run();
    }
}
