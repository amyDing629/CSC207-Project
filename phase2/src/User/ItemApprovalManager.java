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
    public void addApproval(String id,String s,String d){
        ArrayList<String> b = new ArrayList<>();
        b.add(id);
        b.add(s);
        b.add(d);
        userApproval.add(b);
    }
    public ArrayList<ArrayList<String>> getUserApproval(){
        return userApproval;
    }
    public void removeUser(String name){
        userApproval.removeIf(strings -> strings.get(1).equals(name));
    }
    public void removeItem(String name){
        itemApproval.removeIf(strings -> strings.get(1).equals(name));
    }
}
