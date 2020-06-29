import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class UserManager {

    public ArrayList<ArrayList<String>> readfile() throws IOException {
        ArrayList<ArrayList<String>> myList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("username.txt"));
            while(in.ready()) {
                String line = in.readLine();
                String[] parts = line.split(", ");
                ArrayList<String> lineList = new ArrayList<>();
                lineList.addAll(Arrays.asList(parts));
                myList.add(lineList);
            }
            return myList;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return myList;
    }

    public void addUser(User u) throws IOException {
        try{
            ArrayList<String> userlist = readfile();
            userlist.add(u.getUsername());
            FileWriter fw = new FileWriter("username.txt");
            fw.write(u.getUsername());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser(String name) throws IOException {
        try{
            ArrayList<String> userlist = readfile();
            for(String u : userlist){
                if(u.equals(name))
                    return u;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyUser(String name, String password) throws IOException {
        try{
            ArrayList<String> userlist = readfile();
            for(String u : userlist){
                if(u.equals(name) && u.getPassword().equals(password))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
