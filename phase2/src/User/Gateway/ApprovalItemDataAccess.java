package User.Gateway;


import Trade.Trade;
import User.Entity.ItemApprovals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApprovalItemDataAccess implements DataAccess {
    private final String serFilePath = "phase2/src/ItemApprovals.ser";
    private List<ItemApprovals> ItemApprovalsList;

    /**
     * [constructor]
     */
    // https://stackoverflow.com/questions/1205995/what-is-the-list-of-valid-suppresswarnings-warning-names-in-java
    @SuppressWarnings("all")
    public ApprovalItemDataAccess(){
        ItemApprovalsList=new ArrayList<>();
        try {
            File serFile = new File(serFilePath);
            if (serFile.exists()) {
                deSerialize();
            } else {
                serFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Object> getList() {
        deSerialize();
        return new ArrayList<>(ItemApprovalsList);
    }

    @Override
    public ItemApprovals getObject(String name) {
        for (ItemApprovals itemApprovals : ItemApprovalsList) {
            if (itemApprovals.getCurUserName().equals(name)) {
                return itemApprovals;
            }
        }
        return null;
    }

    @Override
    public Object getObject(UUID uuid) {
        return null;
    }

    @Override
    public void addObject(Object o) {
        ItemApprovalsList.add((ItemApprovals)o);
        updateSer();
    }

    @Override
    public boolean hasObject(Object o) {
        for (ItemApprovals itemApprovals : ItemApprovalsList) {
            if (o.equals(itemApprovals)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeObject(String o) {
        ItemApprovalsList.remove(getObject(o));
    }

    @Override
    public void removeObject(UUID o) {

    }


    @Override
    public void updateSer() {
        File file = new File(serFilePath);
        file.delete();
        try {
            file.delete();
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

    /**
     * Deserializes the user.ser file to the userList
     */
    // source: https://stackoverflow.com/questions/31540556/casting-object-to-list-results-in-unchecked-cast-warning
    @SuppressWarnings("unchecked")
    public void deSerialize() {
        try {
            File file = new File(serFilePath);
            if (!(file.length() == 0)){
                FileInputStream fileIn = new FileInputStream(serFilePath);
                InputStream buffer = new BufferedInputStream(fileIn);
                ObjectInputStream in = new ObjectInputStream(buffer);

                ItemApprovalsList = (List<ItemApprovals>) in.readObject();
                in.close();
                fileIn.close();
                //System.out.println("deSerialize: "+lendingList);
            }

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    @Override
    public void setList(List<Object> ItemApprovalsList) {
        this.ItemApprovalsList.clear();
        for(Object a: ItemApprovalsList){
            this.ItemApprovalsList.add((ItemApprovals) a);
        }
        updateSer();
    }

    /**
     * Serializes the userList to the user.ser file
     */
    public void serialize() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(serFilePath);
            OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(ItemApprovalsList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}


