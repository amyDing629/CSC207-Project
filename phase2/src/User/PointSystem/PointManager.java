package User.PointSystem;

import Trade.Entity.Trade;
import Trade.UseCase.TradeManager;
import Trade.TradeStatus;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import java.time.LocalDateTime;
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
    private final Map<UUID, Integer> pointList;

    /**
     * The points needed to exchange one bonus trade which will not count towards being frozen
     */
    private int exStandard;

    /**
     * Constructs the Point Manager to manage points for client users.
     */
    public PointManager(){
        this.um = new UserManager();
        this.tm = new TradeManager();
        this.pointList = new HashMap<>();
        this.exStandard = 5;
    }

    /**
     * Set (update) the bonus points for particular user and update the pointList.
     */
    public void setUserPoints(UUID user) {
        int newPoint = this.tm.getComplete(user).size() - um.getSelectedBonusTrades(user).size() * this.exStandard;
        um.getUser(user).setBonusPoints(newPoint);
        this.pointList.put(um.getUser(user).getId(), newPoint);
    }

    /**
     * Return the list of user id and corresponding points.
     * @return pointList
     */
    public Map<UUID, Integer> getPointList() {return this.pointList;}


    /**
     * Set the list of user id and the bonus points the use has. (setter for pointList)
     */
    public void setAllPoints() {
        for (ClientUser user: this.um.getUserList()) {
            this.setUserPoints(user.getId());
        }
    }

    /**
     * Return the number of bonus points for user
     * @param user the specific user
     */
    public int getUserPoints(ClientUser user) {
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



    /** TODO: May be deleted
     * Update the number of trades that each user create since date dateTime.
     * @param dateTime Count the number of trades (not cancelled) after this dateTime.
     */
    public void CountPointsSince(LocalDateTime dateTime) {
        for (UUID userId: this.pointList.keySet()) {
            List<Trade> trades = this.tm.getAllTrade(userId);
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


}
