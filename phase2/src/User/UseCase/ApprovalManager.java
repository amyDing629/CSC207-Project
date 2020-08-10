package User.UseCase;



import User.Entity.ClientUser;
import User.Gateway.ApprovalItemDataAccess;
import User.Gateway.ApprovalUserDataAccess;
import User.Entity.ItemApprovals;
import User.Entity.UserApprovals;
import User.Gateway.DataAccess;

import java.util.ArrayList;

public class ApprovalManager {
    DataAccess itemDataAccess = new ApprovalItemDataAccess();
    DataAccess userDataAccess = new ApprovalUserDataAccess();


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
        itemDataAccess.removeObject(ua);
    }

    public void removeUserApproval(String ua){
        userDataAccess.removeObject(ua);
    }

    public ItemApprovals getItemApproval(String username){
        return (ItemApprovals) itemDataAccess.getObject(username);
    }

    public String AllItemApprovals(){
        StringBuilder result= new StringBuilder();
        ArrayList<ItemApprovals> itemApprovals=getItemApprovals();
        for (ItemApprovals i : itemApprovals) {
            result.append(i.toString());
        }
        return result.toString();
    }

    public String AllUserApprovals(){
        StringBuilder result= new StringBuilder();
        ArrayList<UserApprovals> userApprovals=getUserApprovals();
        for (UserApprovals i : userApprovals) {
            result.append(i.toString());
        }
        return result.toString();
    }
    public UserApprovals getUserApproval(String username){
        return (UserApprovals) userDataAccess.getObject(username);
    }
    public void addApprovals(ClientUser user,String name,String des){
        itemDataAccess.addObject(new ItemApprovals(user,name,des));
    }
    public void addApprovals(ClientUser user,String des){
        userDataAccess.addObject(new UserApprovals(user,des));
    }

    public boolean hasItemApprovals(String itemName){
        return itemDataAccess.hasObject(itemName);
    }
    public boolean hasUserApprovals(String userName){
        return itemDataAccess.hasObject(userName);
    }

}
