package Inventory;

import Main.GateWay;

import java.io.*;

public class InvDataAccess {

    public void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("phase1/src/Inventory.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] lst = line.split(",");
                Item newItem = new Item(lst[0], lst[2]);
                newItem.setDescription(lst[1]);
                GateWay.inventory.add(newItem);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update the new lendingList to file
     * @throws IOException when the update is failed
     */
    public void updateFile() throws IOException {
        File file = new File("phase1/src/Inventory.Inventory.txt");
        boolean result = file.delete();
        if (!result){
            System.out.println("can not update inventory files");
        }
        for (Item item: GateWay.inventory){
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
}
