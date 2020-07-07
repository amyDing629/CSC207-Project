package Inventory;

import java.io.*;
import java.util.ArrayList;

/**
 * [Use Case Class]
 * inventory: present existed items. Edit(add, delete, edit) existed items. Get item through item name.
 * Read and write Inventory.txt (temporary, may move to another class).
 */
public class Inventory {
    /**
     * all existed items in user's lending list.
     */
    ArrayList<Item> lendingList;

    /**
     * [Constructor]
     * get lendingList from file (read file will move to another class).
     */
    public Inventory() {
        lendingList = new ArrayList<Item>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("phase1/src/Inventory.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] lst = line.split(",");
                Item newItem = new Item(lst[0], lst[2]);
                lendingList.add(newItem);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for the lending list
     * @return lendingList
     */
    public ArrayList<Item> getLendingList() {
        return lendingList;
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
    public void addItem(Item item) throws IOException {
        lendingList.add(item);
        updateFile();
    }


    /**
     *
     * @param item the deleted item
     * @throws IOException when the item is not found in the inventory
     */
    public void deleteItem(Item item) throws IOException {
        if (lendingList.contains(item)) {
            lendingList.remove(item);
            updateFile();
        } else {
            throw new IOException("the item is not in the inventory");
        }
    }

    /**
     *
     * @param item the item you want to edit
     * @param target the type that it wants to edit
     * @param newContent new content
     * @throws IOException when the the new edition can not be updated in the file.
     */
    public void editItem(Item item, String target, String newContent) throws IOException {
        if (target.equals("description")){
            item.setDescription(newContent);
        }else{
            item.setOwner(newContent);
        }
        updateFile();
    }

    /**
     * update the new lendingList to file
     * @throws IOException when the update is failed
     */
    public void updateFile() throws IOException {
        File file = new File("phase1/src/Inventory.Inventory.txt");
        boolean result = file.delete();
        for (Item item: lendingList){
            addItemToFile(item);
        }
    }

    /**
     *
     * @param item the item wanted to add to file
     * @throws IOException when the item cannot be added to file
     */
    private void addItemToFile(Item item) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("phase1/src/inventory.txt", true);
            fos.write((item.getName()+","+item.getDescription()+","+item.getOwnerName()+"\n").getBytes());
        }catch(IOException e){
            throw new IOException("cannot add item to file");

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
}