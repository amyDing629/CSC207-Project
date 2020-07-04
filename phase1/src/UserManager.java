import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserManager {

    public ArrayList<ArrayList<String>> readFile() throws IOException {
        ArrayList<ArrayList<String>> myList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("phase1/src/username.txt"));
            while(in.ready()) {
                String line = in.readLine();
                String[] parts = line.split(", ");
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(parts));
                myList.add(lineList);
            }
            in.close();
            if(!myList.isEmpty()){
                if(myList.get(0).size()==0){
                    myList.remove(0);
                }
            }
            return myList;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return myList;
    }

    public ArrayList<User> splitUser(ArrayList<ArrayList<String>> a) throws IOException {
        ArrayList<User> myList = new ArrayList<>();
        for(ArrayList<String> b: a){
            if(b.get(3).equals("true")){
                String[] c = b.get(6).split("; ");
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(c));

                AdministrativeUser d = new AdministrativeUser(b.get(1), b.get(2), true);
                d.setNotification(lineList);

                int i = Integer.parseInt(b.get(0));
                d.setId(i);

                d.setFrozen(b.get(4).equals("true"));

                d.setBorrow(b.get(5).equals("true"));

                ArrayList<String> lineList2 = new ArrayList<>();
                if (!b.get(7).equals("")){
                    String[] f = b.get(7).split("; ");
                    lineList2.addAll(Arrays.asList(f));
                    d.setWishLend(lineList2);
                }
                d.setWishLend(lineList2);

                ArrayList<String> lineList3 = new ArrayList<>();
                if (!b.get(7).equals("")){
                    String[] g = b.get(8).split("; ");
                    lineList3.addAll(Arrays.asList(g));
                    d.setWishBorrow(lineList3);
                }
                d.setWishLend(lineList3);

                ArrayList<String> lineList4 = new ArrayList<>();
                if(!b.get(9).equals("")){
                    String[] h = b.get(9).split("; ");
                    lineList4.addAll(Arrays.asList(h));
                }
                ArrayList<Integer> lineList5 = new ArrayList<>();

                for(String p: lineList4){
                    lineList5.add(Integer.parseInt(p));
                }

                d.setTradeHistory(lineList5);
                d.setWeekTransactionLimit(Integer.parseInt(b.get(10)));
                d.setIncompleteTransaction(Integer.parseInt(b.get(11)));

                myList.add(d);
            }
            if(b.get(3).equals("false")){
                String[] c = b.get(6).split("; ");
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(c));

                ClientUser d = new ClientUser(b.get(1), b.get(2), true);
                d.setNotification(lineList);


                int i = Integer.parseInt(b.get(0));
                d.setId(i);

                d.setFrozen(b.get(4).equals("true"));

                d.setBorrow(b.get(5).equals("true"));

                ArrayList<String> lineList2 = new ArrayList<>();
                if (!b.get(7).equals("")){
                    String[] f = b.get(7).split("; ");
                    lineList2.addAll(Arrays.asList(f));
                    d.setWishLend(lineList2);
                }
                d.setWishLend(lineList2);

                ArrayList<String> lineList3 = new ArrayList<>();
                if (!b.get(7).equals("")){
                    String[] g = b.get(8).split("; ");
                    lineList3.addAll(Arrays.asList(g));
                    d.setWishBorrow(lineList3);
                }
                d.setWishLend(lineList3);

                ArrayList<String> lineList4 = new ArrayList<>();
                if(!b.get(9).equals("")){
                    String[] h = b.get(9).split("; ");
                    lineList4.addAll(Arrays.asList(h));
                }
                ArrayList<Integer> lineList5 = new ArrayList<>();

                for(String p: lineList4){
                    lineList5.add(Integer.parseInt(p));
                }

                d.setTradeHistory(lineList5);
                d.setWeekTransactionLimit(Integer.parseInt(b.get(10)));
                d.setIncompleteTransaction(Integer.parseInt(b.get(11)));

                myList.add(d);
            }
        }
        return myList;
    }

    public void addUser(User u) throws IOException {
        try{
            FileOutputStream output = new FileOutputStream("phase1/src/username.txt", true);
            String name = u.getUsername();
            String s = u.getId()+ ", " + name + ", "  + u.getPassword()+ ", "  + u.getIsAdmin()+ ", "  + u.getIsfrozen()+ ", "  + u.getIsBorrow()+ ", ";
            StringBuilder m = new StringBuilder();
            for(String i: u.getNotification()){
                m.append(i).append("; ");
            }
            s = s + m + ", ";
            StringBuilder n = new StringBuilder();
            for(String i: u.getWishLend()){
                n.append(i).append("; ");
            }
            s = s + n + ", ";
            StringBuilder k = new StringBuilder();
            for(String i: u.getWishBorrow()){
                k.append(i).append("; ");
            }
            s = s + k + ", ";
            StringBuilder l = new StringBuilder();
            for(Integer i: u.getTradeHistory()){
                l.append(i).append("; ");
            }
            s = s + l + ", ";
            s = s+ u.getWeekTransactionLimit() + ", ";
            s = s+ u.getIncompleteTransactionLimit()+ ", ";
            s = s + "\n";
            output.write(s.getBytes());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String name) throws IOException {
        try{
            if(readFile().size() == 0){return null;}
            ArrayList<User> userList = splitUser(readFile());
            for(User u : userList){
                if(u.getUsername().equals(name))
                    return u;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int userId) throws IOException {
        try{
            if(readFile().size() == 0){return null;}
            ArrayList<User> userList = splitUser(readFile());
            for(User u : userList){
                if(u.getId().equals(userId))
                    return u;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Trade> findTrade(Integer id){
        try{
            if(readFile().size() == 0){return null;}
            TradeManager a = new TradeManager();
            ArrayList<User> userList = splitUser(readFile());
            for(User u : userList){
                if(u.getId().equals(id))
                    return u.getAllTrade();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyUser(String name, String password) throws IOException {
        try{
            ArrayList<User> userList = splitUser(readFile());
            for(User u : userList){
                if(u.getUsername().equals(name) && u.getPassword().equals(password))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateFile() throws IOException {
        ArrayList<User> userList = splitUser(readFile());
        Integer y = userList.get(userList.size() - 1).getId();
        PrintWriter writer = new PrintWriter("phase1/src/username.txt");
        writer.print("");
        writer.close();
        for (int i = 1; i < y + 1; i++){
            User u = getUser(i);
            addUser(u);
        }
    }

}

