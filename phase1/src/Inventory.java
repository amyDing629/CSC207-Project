import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> lendingList;


    /**
     * Constructor, inventory has an instance variable lendingList.
     */
    public Inventory() {
        lendingList = new ArrayList<Item>();
    }

    /**
     * getter for the lending list
     * @return lendingList
     */
    public ArrayList<Item> getLendingList() {
        return lendingList;
    }

    /**
     * add the item into the inventory (when clientuser adds Item to the lending list, inventory should add the item)
     * @param item the item added
     */
    public void addItem(Item item){
        lendingList.add(item);
    }

    /**
     * delete the item from the inventory (when a trade is completed or clentUser remove the item from the lendinglist,
     * the item should be removed from the inventory.
     * @param item the item that should be deleted
     */
    public void deleteItem(Item item){
        if (lendingList.contains(item)){
            lendingList.remove(item);
        }else{
            System.out.println("the item is not in the inventory");
        }
    }
}
