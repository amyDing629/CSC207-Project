package User.PointSystem;

import User.ClientUser;
import User.UserManager;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

/**
 * [Use Case] class
 * Responsible for managing award points of all users
 */
public class PointManager {
    private UserManager um;
    private HashMap<UUID, Integer> pointList;

    public PointManager(){
        this.pointList = new HashMap<>();
    }

    /***/
    public HashMap<UUID, Integer> getPointList() {return this.pointList;}

    /**
     * Set up the point list or update the list when new users are recorded in UserManager.
     */
    public void setPointList() {
        for (ClientUser user: this.um.getUserList()) {
            if (!this.pointList.containsKey(user.getId())) {
                this.pointList.put(user.getId(), 0);
            }
        }
    }

    //TODO: Complete
    public void sortListByPoints() {}

    //TODO: Complete
    public void getMostFreq() {}
}
