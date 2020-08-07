package User;

public class LoginSystemPresenter implements ILoginSystemBoundary {

    UserManager um = new UserManager();

    public void register(String name, String password) {
        ClientUser user = new ClientUser(name, password, false);
        um.addUser(user);
    }

    public boolean login(String name, String password) {
        return um.verifyUser(name, password);
    }

    // TODO
    public void explore() {
//        new InventoryGUI();
    }

}
