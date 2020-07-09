package User;

import Main.GateWay;
import Trade.Trade;
import Trade.TradeManager;

import java.io.*;
import java.util.*;

/**
 * [UseCase class]
 * The renew and modification of users
 */
public class UserManager {


    /**
     * @param name the name of the user that the manager wants to get
     * find the user by the user name
     */
    public User getUser(String name) throws IOException {
        try{
            FileEditor f = new FileEditor();
            if(f.readFile().size() == 0){return null;}
            //ArrayList<User> userList = splitUser(readFile());
            for(User u : GateWay.users){
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
            FileEditor f = new FileEditor();
            if(f.readFile().size() == 0){return null;}
            //ArrayList<User> userList = splitUser(readFile());
            for(User u : GateWay.users){
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
            FileEditor f = new FileEditor();
            if(f.readFile().size() == 0){return null;}
            TradeManager a = new TradeManager();
            for(User u : GateWay.users){
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
        //ArrayList<User> userList = splitUser(readFile());
        for(User u : GateWay.users){
            if(u.getUsername().equals(name) && u.getPassword().equals(password)) {
                return true;}
        }
        return false;
    }



}

