import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
public class AdministrativeUser extends User {
    private boolean isAdmin;
    private String password;
    private String username;
    private static Integer id;

    private List<String> notification;

    public AdministrativeUser(String username, String password,List<String> notification,boolean isAdmin){
        super(username, password, notification, isAdmin);
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
    public Integer getId() {
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
        AdministrativeUser a = new AdministrativeUser(username, password,notification,isAdmin);}
    }

    public void confirmItem(ClientUser a, Item b){
        a.addWishLend(b.getName());
    }

    public void tradelimit(ClientUser a){
        a.setIsFrozen(a.getTradeNumber() > a.getWeekTransactiionLimit());
    }

    public void incompleteTransaction(ClientUser a){
        a.setIsFrozen(a.getIncompleteTransaction() <= a.getIncompleteTransactionLimit());
    }

    public void canBorrow(int c, ClientUser b){
        b.setBorrow(b.getLend().size() + c >= b.getBorrowed().size());
    }

}
