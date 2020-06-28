import java.util.*;
abstract class User {
    protected Integer id = 0;
    protected String password;
    protected String username;
    private List<String> notification;

    public User(String username, String password /**int id*/){
        this.username = username;
        this.password = password;
        //this.id = id;
    }

    public User() {

    }

    public abstract String getPassword();
    public abstract String getUsername();
    public abstract Integer getId();

    public abstract List<String> getNotification();
    public abstract void addNotification(String no);
    public abstract void changePassword(String password);
    public abstract void changeUsername(String username);

}