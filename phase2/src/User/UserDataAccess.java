package User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDataAccess implements DataAccess {

    private final String txtFilePath = "phase2/src/username.txt";
    private final String serFilePath = "phase2/src/user.ser";
    private List<ClientUser> userList;

    /**
     * [constructor]
     */
    public UserDataAccess() {
        userList = new ArrayList<>();

        try {
            File serFile = new File(serFilePath);
            if (serFile.exists()) {
                deSerialize();
            } else {
                serFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getObject(String name) {
        deSerialize();
        for (ClientUser u : userList) {
            if (u.getUsername().equals(name))
                return u;
        }
        return null;
    }

    @Override
    public Object getObject(UUID uuid) {
        deSerialize();
        for (ClientUser u : userList) {
            if (u.getId().equals(uuid))
                return u;
        }
        return null;
    }

    @Override
    public boolean hasObject(String name) {
        deSerialize();
        for (ClientUser u : userList) {
            if (u.getUsername().equals(name))
                return true;
        }
        return false;
    }

    public void updateSer() {
        File writer = new File(serFilePath);
        writer.deleteOnExit();
        serialize();
    }

    @Override
    public void addObject(Object o) {
        deSerialize();
        userList.add((ClientUser) o);
        updateSer();
    }

    /**
     * Serializes the userList to the user.ser file
     */
    public void serialize() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(serFilePath);
            OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(userList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    /**
     * Deserializes the user.ser file to the userList
     */
    // source: https://stackoverflow.com/questions/31540556/casting-object-to-list-results-in-unchecked-cast-warning
    @SuppressWarnings("unchecked")
    public void deSerialize() {
        try {
            FileInputStream fileIn = new FileInputStream(serFilePath);
            InputStream buffer = new BufferedInputStream(fileIn);
            ObjectInputStream in = new ObjectInputStream(buffer);

            userList = (List<ClientUser>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

    }

//    @Override
//    public ClientUser getUser(String name) {
//        deSerialize();
//        for (ClientUser u : userList) {
//            if (u.getUsername().equals(name))
//                return u;
//        }
//        return null;
//    }
//
//    @Override
//    public ClientUser getUser(UUID uuid) {
//        deSerialize();
//        for (ClientUser u : userList) {
//            if (u.getId().equals(uuid))
//                return u;
//        }
//        return null;
//    }
//    @Override
//    public boolean hasUser(String name) {
//        deSerialize();
//        for (ClientUser u : userList) {
//            if (u.getUsername().equals(name))
//                return true;
//        }
//        return false;
//    }
//    @Override
//    public void addUser(ClientUser clientUser) {
//        deSerialize();
//        userList.add(clientUser);
//        updateSer();
//    }


//    /**
//     * @param u the user that the manager wants to add in
//     * add the user into the txt file in the type of string
//     */
//    private void addUser(ClientUser u) {
//        try{
//            FileOutputStream output = new FileOutputStream(serFilePath, true);
//            String name = u.getUsername();
//            String s = u.getId().toString() + ", " + name + ", "  + u.getPassword()+ ", "  + u.getIsAdmin()+ ", "
//                    + u.getIsFrozen()+ ", "  + u.getIsBorrow()+ ", ";
//            StringBuilder m = new StringBuilder();
//            for(String i: u.getNotification()){
//                m.append(i).append("; ");
//            }
//            s = s + m + ", ";
//            StringBuilder n = new StringBuilder();
//            for(String i: u.getWishLend()){
//                n.append(i).append("; ");
//            }
//            s = s + n + ", ";
//            StringBuilder k = new StringBuilder();
//            for(String i: u.getWishBorrow()){
//                k.append(i).append("; ");
//            }
//            s = s + k + ", ";
//            StringBuilder l = new StringBuilder();
//            for(UUID i: u.getTradeHistory()){
//                l.append(i).append("; ");
//            }
//            s = s + l + ", ";
//            s = s+ u.getWeekTransactionLimit() + ", ";
//            s = s+ u.getIncompleteTransactionLimit() + ", ";
//
//            s = s + u.getLendCounter() + ", ";
//            s = s+ u.getBorrowCounter() + ", ";
//            s =  s+ u.getEnd();
//            s = s + "\n";
//            output.write(s.getBytes());
//            output.close();
//            //userList.add(u);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * update all the information in the username.txt
//     */
//    public void updateFile() {
//        try {
//            PrintWriter writer = new PrintWriter(txtFilePath);
//            writer.print("");
//            writer.close();
//            for (ClientUser u : userList) {
//                addUser(u);
//            }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * read the username.txt file, return a list of list that contains all the information of the user
//     */
//    public ArrayList<ArrayList<String>> readFile() {
//        ArrayList<ArrayList<String>> myList = new ArrayList<>();
//        try {
//            BufferedReader in = new BufferedReader(new FileReader(txtFilePath));
//            while(in.ready()) {
//                String line = in.readLine();
//                String[] parts = line.split(", ");
//                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(parts));
//                myList.add(lineList);
//            }
//            in.close();
//            if(!myList.isEmpty()){
//                if(myList.get(0).size()==0){
//                    myList.remove(0);
//                }
//            }
//            return myList;
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        return myList;
//    }
//
//    /**
//     * @param a the  list of list that contains all the information of the user
//     * return list of all users , set the attribute information of all the users
//     */
//    public void splitUser(ArrayList<ArrayList<String>> a, TradeManager tm, UserManager um) {
//        for (ArrayList<String> b : a) {
//            String[] c = b.get(6).split("; ");
//            ArrayList<String> lineList = new ArrayList<>(Arrays.asList(c));
//
//            ClientUser d = new ClientUser(b.get(1), b.get(2), Boolean.parseBoolean(b.get(3)));
//            d.setId(UUID.fromString(b.get(0)));
//            d.setNotification(lineList);
//
//
//            UUID uid = UUID.fromString(b.get(0));
//            d.setId(uid);
//
//            d.setFrozen(b.get(4).equals("true"));
//
//            d.setBorrow(b.get(5).equals("true"));
//
//            ArrayList<String> lineList2 = new ArrayList<>();
//            if (!b.get(7).equals("")) {
//                String[] f = b.get(7).split("; ");
//                lineList2.addAll(Arrays.asList(f));
//                d.setWishLend(lineList2);
//            }
//            d.setWishLend(lineList2);
//
//            ArrayList<String> lineList3 = new ArrayList<>();
//            if (!b.get(7).equals("")) {
//                String[] g = b.get(8).split("; ");
//                lineList3.addAll(Arrays.asList(g));
//                d.setWishBorrow(lineList3);
//            }
//            d.setWishBorrow(lineList3);
//
//            ArrayList<String> lineList4 = new ArrayList<>();
//            if (!b.get(9).equals("")) {
//                String[] h = b.get(9).split("; ");
//                lineList4.addAll(Arrays.asList(h));
//            }
//            ArrayList<UUID> lineList5 = new ArrayList<>();
//
//            for (String p : lineList4) {
//                lineList5.add(UUID.fromString(p));
//            }
//
//            d.setTradeHistory(lineList5);
//            d.setWeekTransactionLimit(Integer.parseInt(b.get(10)));
//            d.setIncompleteTransaction(Integer.parseInt(b.get(11)));
//            d.setLendCounter(Integer.parseInt(b.get(12)));
//            d.setBorrowCounter(Integer.parseInt(b.get(13)));
//            d.setEnd(LocalDateTime.parse(b.get(14)));
//
//            um.getUserList().add(d);
//
//        }
//    }
}

