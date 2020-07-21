package User;

import java.util.ArrayList;

public class ItemApprovalManager {
    private final ArrayList<ArrayList<String>> itemApproval;
    private final ArrayList<ArrayList<String>> userApproval;

    public ItemApprovalManager(){
        itemApproval = new ArrayList<ArrayList<String>>();
        userApproval = new ArrayList<ArrayList<String>>();
    }


    public ArrayList<ArrayList<String>> getItemApproval(){
        return itemApproval;
    }

    public ArrayList<ArrayList<String>> getUserApproval(){
        return userApproval;
    }
}
