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

    public ArrayList<User> splitUser(ArrayList<ArrayList<String>> a){
        ArrayList<User> myList = new ArrayList<>();
        for(ArrayList<String> b: a){
            if(b.get(3).equals("true")){
                String[] c = b.get(6).split("; ");
                ArrayList<String> lineList = new ArrayList<>();
                lineList.addAll(Arrays.asList(c));

                AdministrativeUser d = new AdministrativeUser(b.get(1), b.get(2),lineList, true);

                int i = Integer.parseInt(b.get(0));
                d.setId(i);

                d.setFrozen(b.get(4).equals("true"));

                d.setBorrow(b.get(5).equals("true"));

                String[] f = b.get(7).split("; ");
                ArrayList<String> lineList2 = new ArrayList<>();
                lineList2.addAll(Arrays.asList(f));
                d.setWishLend(lineList2);

                String[] g = b.get(8).split("; ");
                ArrayList<String> lineList3 = new ArrayList<>();
                lineList2.addAll(Arrays.asList(g));
                d.setWishBorrow(lineList3);

                String[] h = b.get(b.size() - 1).split("; ");
                ArrayList<String> lineList4 = new ArrayList<>();
                lineList4.addAll(Arrays.asList(h));
                ArrayList<Trade> lineList5 = new ArrayList<>();
                for(String t: lineList4){
                    String[] p = t.split(": ");
                    ArrayList<String> q = new ArrayList<>();
                    q.addAll(Arrays.asList(p));
                    if(q.size() == 4){
                        String[] r = q.get(2).split("| ");
                        ArrayList<String> s = new ArrayList<>();
                        s.addAll(Arrays.asList(r));
                        Item u = new Item(s.get(0), s.get(1));
                        int m = Integer.parseInt(q.get(3));
                        OnewayTrade t1 = new OnewayTrade(Integer.parseInt(q.get(0)), Integer.parseInt(q.get(1)), u, m);
                        lineList5.add(t1);
                    }
                    else{
                        String[] r = q.get(2).split("| ");
                        ArrayList<String> s = new ArrayList<>();
                        s.addAll(Arrays.asList(r));
                        Item u = new Item(s.get(0), s.get(1));

                        String[] v = q.get(3).split("| ");
                        ArrayList<String> w = new ArrayList<>();
                        w.addAll(Arrays.asList(r));
                        Item k = new Item(w.get(0), w.get(1));

                        int m = Integer.parseInt(q.get(4));
                        TwowayTrade t2 = new TwowayTrade(Integer.parseInt(q.get(0)), Integer.parseInt(q.get(1)), u, k, m);
                        lineList5.add(t2);
                    }
                }
                d.setTradeHistory(lineList5);
                myList.add(d);
            }
            if(b.get(3).equals("false")){
                String[] c = b.get(6).split("; ");
                ArrayList<String> lineList = new ArrayList<>();
                lineList.addAll(Arrays.asList(c));

                ClientUser d = new ClientUser(b.get(1), b.get(2),lineList, true);

                int i = Integer.parseInt(b.get(0));
                d.setId(i);

                d.setFrozen(b.get(4).equals("true"));

                d.setBorrow(b.get(5).equals("true"));

                String[] f = b.get(7).split("; ");
                ArrayList<String> lineList2 = new ArrayList<>();
                lineList2.addAll(Arrays.asList(f));
                d.setWishLend(lineList2);

                String[] g = b.get(8).split("; ");
                ArrayList<String> lineList3 = new ArrayList<>();
                lineList2.addAll(Arrays.asList(g));
                d.setWishBorrow(lineList3);

                String[] h = b.get(b.size() - 1).split("; ");
                ArrayList<String> lineList4 = new ArrayList<>();
                lineList4.addAll(Arrays.asList(h));
                ArrayList<Trade> lineList5 = new ArrayList<>();
                for(String t: lineList4){
                    String[] p = t.split(": ");
                    ArrayList<String> q = new ArrayList<>();
                    q.addAll(Arrays.asList(p));
                    if(q.size() == 4){
                        String[] r = q.get(2).split("| ");
                        ArrayList<String> s = new ArrayList<>();
                        s.addAll(Arrays.asList(r));
                        Item u = new Item(s.get(0), s.get(1));
                        int m = Integer.parseInt(q.get(3));
                        OnewayTrade t1 = new OnewayTrade(Integer.parseInt(q.get(0)), Integer.parseInt(q.get(1)), u, m);
                        lineList5.add(t1);
                    }
                    else{
                        String[] r = q.get(2).split("| ");
                        ArrayList<String> s = new ArrayList<>();
                        s.addAll(Arrays.asList(r));
                        Item u = new Item(s.get(0), s.get(1));

                        String[] v = q.get(3).split("| ");
                        ArrayList<String> w = new ArrayList<>();
                        w.addAll(Arrays.asList(r));
                        Item k = new Item(w.get(0), w.get(1));

                        int m = Integer.parseInt(q.get(4));
                        TwowayTrade t2 = new TwowayTrade(Integer.parseInt(q.get(0)), Integer.parseInt(q.get(1)), u, k, m);
                        lineList5.add(t2);
                    }
                }
                d.setTradeHistory(lineList5);
                myList.add(d);
            }
        }
        return myList;
    }

    public void addUser(User u) throws IOException {
        try{
            ArrayList<User> userlist = splitUser(readfile());
            userlist.add(u);
            FileWriter fw = new FileWriter("username.txt");
            fw.write(u.getUsername());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String name) throws IOException {
        try{
            ArrayList<User> userlist = splitUser(readfile());
            for(User u : userlist){
                if(u.getUsername().equals(name))
                    return u;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyUser(String name, String password) throws IOException {
        try{
            ArrayList<User> userlist = splitUser(readfile());
            for(User u : userlist){
                if(u.getUsername().equals(name) && u.getPassword().equals(password))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
