package Inventory.GateWay;

import Inventory.Entity.Item;
import User.Gateway.DataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * [gateway class]
 * This class reads and writes all the item information from ItemList.ser into lendingList
 */
public class InvDataAccess implements DataAccess {

    private final String serFilePath = "phase2/src/itemList.ser";
    private List<Item> lendingList;

    /**
     * [constructor]
     * Create temporary item list. Check whether required file existed. If existed, deserialze the file, if not, create new file.
     */
    // https://stackoverflow.com/questions/1205995/what-is-the-list-of-valid-suppresswarnings-warning-names-in-java
    @SuppressWarnings("all")
    public InvDataAccess() {
        lendingList = new ArrayList<>();

        try {
            File serFile = new File(serFilePath);
            if (serFile.exists() && !(serFile.length() == 0)) {
                deSerialize();
            } else {
                serFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * serialize the file
     */
    public void serialize(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(serFilePath);
            OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(lendingList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * get the list from file
     * @return item list
     */
    @Override
    public List<Object> getList() {
        deSerialize();
        return new ArrayList<>(lendingList);
    }

    /**
     *
     * @param name the name of the object
     * @return Item
     */
    @Override
    public Object getObject(String name) {
        deSerialize();
        for (Item item : lendingList) {
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }

    /**
     * no use
     */
    @Override
    public Object getObject(UUID uuid) {
        return null;
    }

    /**
     * add item to file
     * @param o: item
     */
    @Override
    public void addObject(Object o) {
        deSerialize();
        lendingList.add((Item) o);
        updateSer();
    }

    /**
     * update list info to file
     */
    @Override @SuppressWarnings("ALL")
    public void updateSer(){
        File file = new File(serFilePath);
        file.delete();
        try {
            if(!file.exists()) {
                boolean result = file.createNewFile();
                if (!result){
                    System.out.println("the new file is not created");
                }
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serialize();
    }

    // source: https://stackoverflow.com/questions/31540556/casting-object-to-list-results-in-unchecked-cast-warning
    @SuppressWarnings("unchecked")
    /**
     * update file info to list
     */
    public void deSerialize() {
        try {
            File file = new File(serFilePath);
            if (!(file.length() == 0)){
                FileInputStream fileIn = new FileInputStream(serFilePath);
                InputStream buffer = new BufferedInputStream(fileIn);
                ObjectInputStream in = new ObjectInputStream(buffer);

                lendingList = (List<Item>) in.readObject();
                in.close();
                fileIn.close();
            }

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * no use
     * @param userList na
     */
    @Override
    public void setList(List<Object> userList) {

    }


    /**
     * whether the item is in the item list
     * @param o: item
     * @return boolean
     */
    @Override
    public boolean hasObject(Object o) {
        deSerialize();
        for (Item i: lendingList){
            if (i.getName().equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeObject(String o) {
        deSerialize();
        lendingList.removeIf(i -> i.getName().equals(o));
        updateSer();

    }

    @Override
    public void removeObject(UUID o) {

    }



}
