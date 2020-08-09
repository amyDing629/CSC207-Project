package Inventory;

import User.Gateway.DataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * [use case class]
 * the object that edits Item list in gateway
 */
public class Inventory {

    DataAccess dataAccess = new InvDataAccess();

    /**
     * Return the lendingList of all users
     *
     * @return the lending list
     */
    public List<Item> getLendingList() {
        ArrayList<Item> result = new ArrayList<>();
        for (Object item : dataAccess.getList()) {
            result.add((Item) item);
        }
        return result;
    }

    /**
     * get a list of items that is not in the trade
     *
     * @return available item list
     */
    public List<Item> getAvailableList() {
        ArrayList<Item> result = new ArrayList<>();
        for (Object item : dataAccess.getList()) {
            Item i = (Item) item;
            if (!i.getIsInTrade()) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * add the item into the inventory
     * @param item the item added
     */
    public void addItem(Item item){
        dataAccess.addObject(item);
    }

    /**
     * Removes the item from .ser file and return true, if it exists; or return false.
     *
     * @param item the deleted item
     */
    public boolean deleteItem(String item) {
        if (dataAccess.hasObject(getItem(item))) {
            dataAccess.removeObject(getItem(item));
            return true;
        } else {
            return false;
        }
    }

    /**
     * get item through its name
     * @param name the item name you want to get
     * @return item
     */
    public Item getItem(String name){
        return (Item) dataAccess.getObject(name);
    }


    public Item createItem(String name, UUID owner){
        return new Item(name, owner);
    }

    public void setDescription(String des, Item item){
        item.setDescription(des);
    }

    public boolean getIsInTrade(String it){
        return getItem(it).getIsInTrade();

    }

    public void setIsInTrade(String it, boolean inTrade){
        getItem(it).setIsInTrade(inTrade);
    }

    public String getName(Item it){
        return it.getName();
    }

    public String getDescription(Item it){
        return it.getDescription();
    }

    public void setDescription(String it, String des){
        getItem(it).setDescription(des);
    }

    public UUID getOwnerUUID(String it){
        return getItem(it).getOwnerUUID();
    }

    boolean itemExist(String name){
        for (Object it: dataAccess.getList()){
            if (getName((Item)it).equals(name)){
                return true;
            }
        }
        return false;
    }

    public void add(Item it) {
        dataAccess.addObject(it);
    }

    /**
     * user select an item and record the item in the system
     * @param line the input from user of the item selected.
     * @return wheter the item has been selected.
     */
    boolean selectItem(String line){
        for (Object it: dataAccess.getList()){
            if (getName((Item)it).equals(line)){
                //System.out.println(it + " has been selected");
                return true;
            }
        }
        return false;
    }


}