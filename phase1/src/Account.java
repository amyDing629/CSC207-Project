import java.util.HashMap;

public class Account {
    private HashMap<Integer, String> userlist;

    public HashMap<Integer, String> getUserlist() {
        return userlist;
    }

    public void add(User a){
        userlist.put(a.getId(), a.getPassword());
    }

    public void remove(User a){
        userlist.remove(a.getId());
    }
}
