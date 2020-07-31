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

    public void removeItem(String name){
        itemApproval.removeIf(strings -> strings.get(1).equals(name));
    }
}
