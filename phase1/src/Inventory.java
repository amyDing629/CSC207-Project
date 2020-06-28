import java.util.ArrayList;

/**
 * inventory: present items of unfrozen account
 */
public class Inventory {
    ArrayList<Item> lendingList;
    ArrayList<Item> frozenList;
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
    public void addItemLending(Item item){
        lendingList.add(item);
    }

    /**
     * delete the item from the inventory (when a trade is completed or clentUser remove the item from the lendinglist,
     * the item should be removed from the inventory.
     * @param item the item that should be deleted
     */
    public void deleteItemLending(Item item){
        if (lendingList.contains(item)){
            lendingList.remove(item);
        }else{
            System.out.println("the item is not in the inventory");
        }
    }



    public ArrayList<Item> getFrozenList(){
        return frozenList;
    }

    public void addItemFrozen(Item item){
        frozenList.add(item);
    }

    /**
     * the inventory that shows tradable items.
     * @return unfrozenList
     */
    public ArrayList<Item> getUnfrozenList(){
        ArrayList<Item> unfrozenList = new ArrayList<Item>();
        for (Item item: lendingList){
            if (!frozenList.contains(item)){
                unfrozenList.add(item);
            }

        }
        return unfrozenList;
    }

    public Item getItem(String name){
        for (Item item: lendingList){
            if (item.getName() == name){
                return item;
            }
        }
        return null;
    }
}
