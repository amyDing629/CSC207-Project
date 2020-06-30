import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
            ClassLoader classLoader = this.getClass().getClassLoader();
            File configFile=new File(classLoader.getResource("inventory.txt").getFile());
            Scanner myReader = new Scanner(configFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] lst = data.split(",");
                Item newItem = new Item(lst[0], lst[2]);
                lendingList.add(newItem);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
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

    /**
     * add the item into the inventory (when clientuser adds Item to the lending list, inventory should add the item)
     *
     * @param item the item added
     */
    public void addItemLending(Item item) {
        lendingList.add(item);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"));
            writer.write(item.getName()+","+item.getDescription()+","+item.getOwnerName());
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }


    public void deleteItemLending(Item item) throws FileNotFoundException {
        if (lendingList.contains(item)){
            lendingList.remove(item);
        }else{
            System.out.println("the item is not in the inventory");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"));
            String lineToRemove = item.getName() + "," + item.getDescription() + "," + item.getOwnerName();
            String currLine;
            while((currLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currLine.trim();
                if(!trimmedLine.equals(lineToRemove)){
                    writer.write(currLine + System.getProperty("line.separator"));
                };
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //name can not be changed
    public void editItemLending(Item newItem) throws FileNotFoundException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"));
            String name = newItem.getName();
            String currLine;
            while((currLine = reader.readLine()) != null){
                if (name.equals(currLine.split(",")[0])){
                    String trimmedLine = currLine.trim();
                    writer.write(newItem.getName() + "," + newItem.getDescription() + newItem.getOwnerName());
                }else{
                    throw new IOException("the item you want to exit is not existed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
