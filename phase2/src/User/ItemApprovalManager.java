package User;

import Inventory.Item;
import Main.GateWay;

import java.util.ArrayList;

public class ItemApprovalManager {
    private ArrayList<ArrayList<String>> itemApproval;
    private ArrayList<ArrayList<String>> userApproval;


    public ArrayList<ArrayList<String>> getItemApproval(){
        return itemApproval;
    }

    public ArrayList<ArrayList<String>> getUserApproval(){
        return userApproval;
    }
}
