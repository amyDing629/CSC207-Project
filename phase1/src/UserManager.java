import trade.Trade;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * [UseCase class]
 * The renew and modification of users
 */
public class UserManager {
    /**
     * read the username.txt file, return a list of list that contains all the information of the user
     */
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

    /**
     * @param a the  list of list that contains all the information of the user
     * return list of all users , set the attribute information of all the users
     */
    public ArrayList<User> splitUser(ArrayList<ArrayList<String>> a) throws IOException {
        ArrayList<User> myList = new ArrayList<>();
        for (ArrayList<String> b : a) {
            if (b.get(3).equals("true")) {
                String[] c = b.get(6).split("; ");
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(c));

                AdministrativeUser d = new AdministrativeUser(b.get(1), b.get(2), true);
                d.setId(UUID.fromString(b.get(0)));
                d.setNotification(lineList);

                UUID uid = UUID.fromString(b.get(0));
                d.setId(uid);

                d.setFrozen(b.get(4).equals("true"));

                d.setBorrow(b.get(5).equals("true"));

                ArrayList<String> lineList2 = new ArrayList<>();
                if (!b.get(7).equals("")) {
                    String[] f = b.get(7).split("; ");
                    lineList2.addAll(Arrays.asList(f));
                    d.setWishLend(lineList2);
                }
                d.setWishLend(lineList2);

                ArrayList<String> lineList3 = new ArrayList<>();
                if (!b.get(7).equals("")) {
                    String[] g = b.get(8).split("; ");
                    lineList3.addAll(Arrays.asList(g));
                    d.setWishBorrow(lineList3);
                }
                d.setWishLend(lineList3);

                ArrayList<String> lineList4 = new ArrayList<>();
                if (!b.get(9).equals("")) {
                    String[] h = b.get(9).split("; ");
                    lineList4.addAll(Arrays.asList(h));
                }
                ArrayList<UUID> lineList5 = new ArrayList<>();

                for (String p : lineList4) {
                    lineList5.add(UUID.fromString(p));
                }

                d.setTradeHistory(lineList5);
                d.setWeekTransactionLimit(Integer.parseInt(b.get(10)));
                d.setIncompleteTransaction(Integer.parseInt(b.get(11)));

                myList.add(d);
            }
            if (b.get(3).equals("false")) {
                String[] c = b.get(6).split("; ");
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(c));

                User d = new User(b.get(1), b.get(2), true);
                d.setId(UUID.fromString(b.get(0)));
                d.setNotification(lineList);


                UUID uid = UUID.fromString(b.get(0));
                d.setId(uid);

                d.setFrozen(b.get(4).equals("true"));

                d.setBorrow(b.get(5).equals("true"));

                ArrayList<String> lineList2 = new ArrayList<>();
                if (!b.get(7).equals("")) {
                    String[] f = b.get(7).split("; ");
                    lineList2.addAll(Arrays.asList(f));
                    d.setWishLend(lineList2);
                }
                d.setWishLend(lineList2);

                ArrayList<String> lineList3 = new ArrayList<>();
                if (!b.get(7).equals("")) {
                    String[] g = b.get(8).split("; ");
                    lineList3.addAll(Arrays.asList(g));
                    d.setWishBorrow(lineList3);
                }
                d.setWishLend(lineList3);

                ArrayList<String> lineList4 = new ArrayList<>();
                if (!b.get(9).equals("")) {
                    String[] h = b.get(9).split("; ");
                    lineList4.addAll(Arrays.asList(h));
                }
                ArrayList<UUID> lineList5 = new ArrayList<>();

                for (String p : lineList4) {
                    lineList5.add(UUID.fromString(p));
                }

                d.setTradeHistory(lineList5);
                d.setWeekTransactionLimit(Integer.parseInt(b.get(10)));
                d.setIncompleteTransaction(Integer.parseInt(b.get(11)));

                myList.add(d);
            }
        }
        return myList;

    }

    /**
     * @param u the user that the manager wants to add in
     * add the user into the txt file in the type of string
     */
    public void addUser(User u) throws IOException {
        try{
            FileOutputStream output = new FileOutputStream("phase1/src/username.txt", true);
            String name = u.getUsername();
            String s = u.getId().toString() + ", " + name + ", "  + u.getPassword()+ ", "  + u.getIsAdmin()+ ", "
                    + u.getIsFrozen()+ ", "  + u.getIsBorrow()+ ", ";
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
            for(UUID i: u.getTradeHistory()){
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

    /**
     * @param name the name of the user that the manager wants to get
     * find the user by the user name
     */
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

    /**
     * @param userId the ID of the user that the manager wants to get
     * find the user by the user ID
     */
    public User getUser(UUID userId) throws IOException {
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

    /**
     * @param id the id of the user that the manager wants to get
     * find the trade list of the user by the user id
     */
    public List<Trade> findTrade(UUID id){
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

    /**
     * @param name the name of the user that the manager check
     * @param password the password of the user that the manager check
     * Check if the name matches with the password
     */
    public boolean verifyUser(String name, String password) throws IOException {
        try{
            ArrayList<User> userList = splitUser(readFile());
            for(User u : userList){
                if(u.getUsername().equals(name) && u.getPassword().equals(password)) {
                    return true;}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * update all the information in the username.txt
     */
    public void updateFile() throws IOException {
        try {
            ArrayList<User> userList = splitUser(readFile());
            PrintWriter writer = new PrintWriter("phase1/src/username.txt");
            writer.print("");
            writer.close();
            for (User u : userList) {
                addUser(getUser(u.username));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}

