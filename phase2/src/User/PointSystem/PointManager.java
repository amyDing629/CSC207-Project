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
    //private int exStandard;

    /**
     * Constructs the Point Manager to manage points for client users.
     */
    public PointManager(){
        this.um = new UserManager();
        this.tm = new TradeManager();
        this.pointList = new HashMap<>();
        //this.exStandard = 5;
    }

    /**
     * Set (update) the bonus points for particular user and update the pointList.
     */
    public void setUserPoints(UUID userId) {
        ClientUser user = um.getUser(userId);
        int newPoint = this.tm.getComplete(userId).size() - um.getSelectedBonusTrades(userId).size() * user.getExStandard();
        ClientUser user1 = um.popUser(userId);
        user1.setBonusPoints(newPoint);
        this.pointList.put(user1.getId(), newPoint);
        um.addUser(user1);
    }

    /**
     * Return the list of user id and corresponding points.
     * @return pointList
     */
//    public Map<UUID, Integer> getPointList() {return this.pointList;}


//    /**
//     * Set the list of user id and the bonus points the use has. (setter for pointList)
//     */
//    public void setAllPoints() {
//        for (ClientUser user: this.um.getUserList()) {
//            this.setUserPoints(user.getId());
//        }
//    }

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
    //public int getExStandard(){return this.exStandard;}

    /**
     * Set the exchange standard to a new value
     * @param newStandard the new exchange standard
     */
    //public void setExStandard(int newStandard) {
        //this.exStandard = newStandard;
    //}


}
