package Inventory;

import Trade.Trade;

import java.io.*;
import java.util.ArrayList;

/**
 * [gateway class]
 * the class that read and write all the item information from ItemList.txt into ItemList in gateway
 */
public class InvDataAccess {
    /**
     * the place we store information
     */
    private final Inventory iv;

    /**
     * [constructor]
     */
    public InvDataAccess(Inventory iv){
        this.iv = iv;
    }

    /**
     * read all the items from ItemList.txt
     */
    public void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("phase2/src/ItemList.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] lst = line.split(",");
                Item newItem = new Item(lst[0], lst[2]);
                newItem.setDescription(lst[1]);
                newItem.setIsInTrade(Boolean.parseBoolean(lst[3]));
                iv.addItem(newItem);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     *
     * @param item the item wanted to add to file
     * @throws IOException when the item cannot be added to file
     */
    private void addItemToFile(Item item) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("phase2/src/ItemList.txt", true);
            fos.write((item.getName()+","+item.getDescription()+","+item.getOwnerName()+","+item.getIsInTrade()+"\n").getBytes());
        }catch(IOException e){
            throw new IOException("cannot add item to file");

        }
    }


    /**
     * update trade.txt with information in trade list of gateway.
     */
    public void updateFile(){
        File file = new File("phase2/src/itemList.txt");
        try {
            if(!file.exists()) {
                boolean result = file.createNewFile();
                if (!result){
                    System.out.println("the trade file is not updated successfully");
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

    public void serialize(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("phase2/src/itemList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(iv.getLendingList());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public ArrayList<Item> deSerialize(){
        try {

            FileInputStream fileIn = new FileInputStream("phase2/src/itemList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Item> res = (ArrayList<Item>)in.readObject();
            in.close();
            fileIn.close();
            iv.setLendingList(res);
            return res;
        } catch (IOException | ClassNotFoundException i) {
            ArrayList<Item> res = new ArrayList<>();
            i.printStackTrace();
            iv.setLendingList(res);
            return res;
        }

    }

}
