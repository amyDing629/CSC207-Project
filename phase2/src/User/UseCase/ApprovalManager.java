package User.UseCase;

import Main.UI.ApprovalItemDataAccess;
import Main.UI.ApprovalUserDataAccess;
import Main.UI.ItemApprovals;
import Main.UI.UserApprovals;
import User.Entity.ClientUser;
import User.Gateway.DataAccess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ApprovalManager {
    DataAccess itemDataAccess = new ApprovalItemDataAccess();
    DataAccess userDataAccess = new ApprovalUserDataAccess();

    private ArrayList<ItemApprovals> itemApproval;
    private ArrayList<UserApprovals> userApproval;


    public ArrayList<ItemApprovals> getItemApprovals(){
        ArrayList<ItemApprovals> result = new ArrayList<>();
        for (Object o : itemDataAccess.getList()) {
            result.add((ItemApprovals) o);
        }
        return result;
    }

    public ArrayList<UserApprovals> getUserApprovals(){
        ArrayList<UserApprovals> result = new ArrayList<>();
        for (Object o : userDataAccess.getList()) {
            result.add((UserApprovals) o);
        }
        return result;
    }

    public void removeItemApproval(String ua){
        userDataAccess.removeObject(ua);
    }

    public void removeUserApproval(String ua){
        itemDataAccess.removeObject(ua);
    }

    public ItemApprovals getItemApproval(String username){
        return (ItemApprovals) itemDataAccess.getObject(username);
    }

    public UserApprovals getUserApproval(String username){
        return (UserApprovals) userDataAccess.getObject(username);
    }
    public void addApprovals(ItemApprovals item) throws FileNotFoundException {
        itemDataAccess.addObject(item);
    }
    public void addApprovals(UserApprovals user) throws FileNotFoundException {
        userDataAccess.addObject(user);
    }

    public boolean hasApprovals(ItemApprovals item){
        return itemDataAccess.hasObject(item);
    }
    public boolean hasApprovals(UserApprovals user){
        return itemDataAccess.hasObject(user);
    }

}
