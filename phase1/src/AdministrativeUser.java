import java.time.LocalDate;
import java.util.*;
public class AdministrativeUser extends User {
    private String password;
    private String username;
    private static Integer id;

    private List<String> notification;

    public AdministrativeUser(String username, String password){
        super(username, password);
        id ++;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<String> getNotification() {
        return notification;
    }

    @Override
    public void addNotification(String no) {
        notification.add(no);
    }

    @Override
    public void changePassword(String password) {
        this.password=password;
    }

    @Override
    public void changeUsername(String username) {
        this.username=username;
    }

    public void freeze(ClientUser a){a.setIsFrozen(false);}

    public void unfreeze(ClientUser a){a.setIsFrozen(true);}

    public void addNewUser(String username, String password){
        if (id == 0){
        AdministrativeUser a = new AdministrativeUser(username, password);}
    }

    public void confirmItem(ClientUser a, Item b){
        a.addWishLend(b.getName());
    }

    public void tradelimit(ClientUser a){
    }

    public void incompleteTransaction(ClientUser a){
        if(a.getIncompleteTransaction() > a.getIncompleteTransactionLimit()){
            a.setIsFrozen(false);
        }
        else{
            a.setIsFrozen(false);
        }
    }

    public void canBorrow(int a, ClientUser b){
        if(b.getLend().size() + a >= b.getBorrowed().size()){
            b.setBorrow(true);
        }
        else{
            b.setBorrow(false);
        }
    }



}
