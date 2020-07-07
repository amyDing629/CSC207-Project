package Inventory;

import java.io.*;
import java.util.ArrayList;

/**
 * inventory: present items of unfrozen account
 */
public class Inventory {
    ArrayList<Item> lendingList;

    /**
     * Constructor, inventory has an instance variable lendingList.
     */
    public Inventory() {
        lendingList = new ArrayList<Item>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("phase1/src/Inventory.Inventory.txt"));
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
     *
     * @return lendingList
     */
    public ArrayList<Item> getLendingList() {
        return lendingList;
    }

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
     * add the item into the inventory (when clientuser adds Inventory.Item to the lending list, inventory should add the item)
     *
     * @param item the item added
     */
    public void addItem(Item item) throws IOException {
        lendingList.add(item);
        updateFile();
    }


    public void deleteItem(Item item) throws IOException {
        if (lendingList.contains(item)) {
            lendingList.remove(item);
            updateFile();
        } else {
            System.out.println("the item is not in the inventory");
        }
    }

    public void editItem(Item item, String target, String newContent) throws IOException {
        if (target.equals("description")){
            item.setDescription(newContent);
        }else{
            item.setOwner(newContent);
        }
        updateFile();
    }

    public void updateFile() throws IOException {
        File file = new File("phase1/src/Inventory.Inventory.txt");
        file.delete();
        for (Item item: lendingList){
            addItemToFile(item);
        }
    }

    private void addItemToFile(Item item) throws IOException{
        try {
            FileOutputStream fos = new FileOutputStream("phase1/src/inventory.txt", true);
            fos.write((item.getName()+","+item.getDescription()+","+item.getOwnerName()+"\n").getBytes());
        }catch(IOException e){
            System.out.println("cannot edit file");

        }
    }


    public Item getItem(String name){
        for (Item item: lendingList){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }
}
