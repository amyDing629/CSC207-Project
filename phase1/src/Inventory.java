import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> lendingList;


    public Inventory() {
        lendingList = new ArrayList<Item>();
    }


    public ArrayList<Item> getLendingList() {
        return lendingList;
    }

    public void addItem(Item item){
        lendingList.add(item);
    }

    public void deleteItem(Item item){
        if (lendingList.contains(item)){
            lendingList.remove(item);
        }else{
            System.out.println("the item is not in the inventory");
        }
    }
}
