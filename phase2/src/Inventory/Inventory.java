package Inventory;

import java.io.*;
import java.util.ArrayList;

/**
 * [use case class]
 * the object that edits Item list in gateway
 */
public class Inventory {
    /**
     * the place we store information
     */
    ArrayList<Item> lendingList;


    /**
     * [Constructor]
     * get lendingList from file (read file will move to another class).
     */
    public Inventory(){
        lendingList = new ArrayList<Item>();
    }

    /**
     * getter for the lending list
     * @return lendingList
     */
    public ArrayList<Item> getLendingList() {
        return lendingList;
    }

    public void setLendingList(ArrayList<Item> itemList){
        lendingList = itemList;
    }

    /**
     * get a list of items that is not in the trade
     * @return available item list
     */
    public ArrayList<Item> getAvailableList() {
        ArrayList<Item> result = new ArrayList<Item>();
        for (Item item : lendingList) {
            if (!item.getIsInTrade()) {
                result.add(item);
            }
        }
        return result;
    }


    /**
     * add the item into the inventory
     * @param item the item added
     */
    public void addItem(Item item){
        lendingList.add(item);
    }


    /**
     *
     * @param item the deleted item
     * @throws IOException when the item is not found in the inventory
     */
    public void deleteItem(Item item) throws IOException {
        if (lendingList.contains(item)) {
            lendingList.remove(item);
        } else {
            throw new IOException("the item is not in the inventory");
        }
    }


    /**
     * get item through its name
     * @param name the item name you want to get
     * @return item
     */
    public Item getItem(String name){
        for (Item item: lendingList){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public Item createItem(String name, String owner){
        return new Item(name, owner);
    }

    public void setDescription(String des, Item item){
        item.setDescription(des);
    }
}