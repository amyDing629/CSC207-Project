package User;

public class LoginSystemPresenter implements ILoginSystemBoundary {

    // model
    UserManager um = new UserManager();

    // view
    View view;

    public LoginSystemPresenter(View view) {
        this.view = view;
    }

    public UserManager getModel() {
        return um;
    }

    public void run() {
        view.setPresenter(this);
//        view.run();
    }

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
