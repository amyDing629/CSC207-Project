package User.PointSystem;

import User.ClientUser;
import User.UserManager;
import Trade.TradeManager;
import Trade.Trade;
import Trade.TradeStatus;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

/**
 * [Use Case] class
 * Responsible for managing bonus points of all users
 *
 */
public class PointManager {
    public UserManager um;
    public TradeManager tm;

    /**
     * A list of all users with the bonus points they earn
     */
    private Map<UUID, Integer> pointList;

    /**
     * The points needed to exchange one bonus trade which will not count towards being frozen
     */
    private int exStandard;

    /**
     * Constructs the Point Manager to manage points for client users.
     */
    public PointManager(UserManager um, TradeManager tm){
        this.um = um;
        this.tm = tm;
        this.pointList = new HashMap<>();
        this.exStandard = 5;
    }

    /**
     * Return the list of user id and corresponding points.
     * @return pointList
     */
    public Map<UUID, Integer> getPointList() {return this.pointList;}

    /**
     * Set the list of user id and the bonus points the use has.
     */
    public void setPointList() {
        for (ClientUser user: this.um.getUserList()) {
            if (!this.pointList.containsKey(user.getId())) {
                this.pointList.put(user.getId(), user.getBonusPoints());
            }
        }
    }

    /**
     * Return the number of bonus points for user
     * @param user the specific user
     */
    public int getPointOfUser(ClientUser user) {
        return this.pointList.get(user.getId());
    }

    /**
     * Return the set value of points needed to exchange one bonus trade
     */
    public int getExStandard(){return this.exStandard;}

    /**
     * Set the exchange standard to a new value
     * @param newStandard the new exchange standard
     */
    public void setExStandard(int newStandard) {
        this.exStandard = newStandard;
    }
/*----------------------------------------GETTERS & SETTERS------------------------------------------------------
    ///**
     //* Set the point of all users to zero.
     //*/
    //public void setZero(){
        //for (UUID userId: this.pointList.keySet()){
            //this.pointList.replace(userId, 0);
        //}
    //}

    /**
     * Update the bonus point for all users.
     */
    public void updatePointsForAll(){
        for (UUID userId: this.pointList.keySet()) {
            ClientUser user = um.getUser(userId);
            List<Trade> allTrades = tm.getAllTrade(user);
            int newPoint = allTrades.size() - user.getBonusTradeList().size();
            this.pointList.put(userId, newPoint); //update the pointList in the manager
            user.setBonusPoints(newPoint); //update the bonus point number within ClientUser entity
        }
    }

    /**
     * TODO: updatePoints for particular user
     */
    public void updatePoints(ClientUser user) {

    }

    /** TODO: Note that the dateTime must be in the past
     * Update the number of trades that each user create since date dateTime.
     * @param dateTime Count the number of trades (not cancelled) after this dateTime.
     */
    public void CountPointsSince(LocalDateTime dateTime) {
        for (UUID userId: this.pointList.keySet()) {
            ClientUser user = this.um.getUser(userId);
            List<Trade> trades = this.tm.getAllTrade(user);
            int count = 0;
            for (Trade t: trades) {
                if (t.getCreateTime().isAfter(dateTime) && !t.getStatus().equals(TradeStatus.cancelled)) {
                    count++;
                }
            }this.pointList.replace(userId, count);
        }
    }

    /**
     * Get the ID of users who trade most frequently
     */
    public Map<UUID, Integer> getMostFreq() {
        Map<UUID, Integer> result = new HashMap<>();
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
