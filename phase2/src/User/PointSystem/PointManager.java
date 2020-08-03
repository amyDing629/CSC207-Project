package User.PointSystem;

import User.ClientUser;
import User.UserManager;
import Trade.TradeManager;
import Trade.Trade;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

/**
 * [Use Case] class
 * Responsible for managing award points of all users
 */
public class PointManager {
    public UserManager um;
    public TradeManager tm;
    private HashMap<UUID, Integer> pointList;

    /**
     * Constructs the Point Manager to manage points for client users.
     */
    public PointManager(UserManager um, TradeManager tm){
        this.um = um;
        this.tm = tm;
        this.pointList = new HashMap<>();
    }

    /**
     * Return the list of user id and corresponding points.
     * @return pointList
     */
    public HashMap<UUID, Integer> getPointList() {return this.pointList;}

    /**
     * Add new users to the point list when they are recorded in UserManager.
     */
    public void addUsersToPointList() {
        for (ClientUser user: this.um.getUserList()) {
            if (!this.pointList.containsKey(user.getId())) {
                this.pointList.put(user.getId(), 0);
            }
        }
    }

    /**
     * Set the point of all users to zero.
     */
    public void setZero(){
        for (UUID userId: this.pointList.keySet()){
            this.pointList.replace(userId, 0);
        }
    }

    /** TODO: Note that the dateTime must be in the past
     * Update the number of trades that each user create since date dateTime.
     * @param dateTime Count the number of trades after this dateTime.
     */
    public void CountPointsSince(LocalDateTime dateTime) {
        for (UUID userId: this.pointList.keySet()) {
            ClientUser user = this.um.getUser(userId);
            List<Trade> trades = this.tm.getAllTrade(user);
            int count = 0;
            for (Trade t: trades) {
                if (t.getCreateTime().isAfter(dateTime)) {
                    count++;
                }
            }this.pointList.replace(userId, count);
        }
    }

    /**
     * Get the ID of users who trade most frequently
     */
    public HashMap<UUID, Integer> getMostFreq() {
        HashMap<UUID, Integer> result = new HashMap<>();
        List<Integer> valueList = new ArrayList<>(this.pointList.values());
        Integer max = Collections.max(valueList);
        for (Map.Entry e: this.pointList.entrySet()){
            if (e.getValue().equals(max)) {
                result.put((UUID) e.getKey(), max);
            }
        }return result;
    }

    /**
     * TODO: Delete
     * Temporary test method.
     */
    public void addPoints(String username, int num) {
        for (ClientUser u: this.um.getUserList()) {
            if (u.getUsername().equals(username)) {
                this.pointList.put(u.getId(), this.pointList.get(u.getId()) + num);
            }
        }
    }



}
