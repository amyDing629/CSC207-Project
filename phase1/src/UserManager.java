import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserManager {
    public ArrayList<String> readfile() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("username.txt"));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line); line = bufReader.readLine(); }
        bufReader.close();
        return null;
    }

    public void addUser(User u) throws IOException {

        ArrayList<String> userlist = readfile();
        userlist.add(u.getUsername());
    }

    public String getUser(String name) throws IOException {
        ArrayList<String> userlist = readfile();
        for(String u : userlist){
            if(u.equals(name))
                return u;
        }
        return null;
    }

    public boolean verifyUser(String name, String password) throws IOException {
        ArrayList<String> userlist = readfile();
        for(String u : userlist){
            if(u.equals(name) && u.getPassword().equals(password))
                return true;
        }
        return false;
    }

}

