import java.util.ArrayList;

public class UserManager {
    private static ArrayList<User> userlist = new ArrayList<>();

    public void addUser(User u){
        userlist.add(u);
    }

    public User getUser(int id){
        for(User u : userlist){
            if(u.getId() == id)
                return u;
        }
        return null;
    }

    public boolean verifyUser(int id, String password){
        for(User u : userlist){
            if(u.getId() ==id && u.getPassword().equals(password))
                return true;
        }
        return false;
    }

}

