package User.Gateway;

import User.Entity.ItemApprovals;
import User.Entity.UserApprovals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApprovalUserDataAccess implements DataAccess {
    private final String serFilePath = "phase2/src/UserApprovals.ser";
    private List<UserApprovals> UserApprovalsList;

    /**
     * [constructor]
     */
    // https://stackoverflow.com/questions/1205995/what-is-the-list-of-valid-suppresswarnings-warning-names-in-java
    @SuppressWarnings("all")
    public ApprovalUserDataAccess(){
        UserApprovalsList=new ArrayList<>();
        try {
            File serFile = new File(serFilePath);
            if (serFile.exists()) {
                deSerialize();
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
        return new ArrayList<>(UserApprovalsList);
    }

    @Override
    public UserApprovals getObject(String name) {
        deSerialize();
        for (UserApprovals userApprovals : UserApprovalsList) {
            if (userApprovals.getCurUserName().equals(name)) {
                return userApprovals;
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
        deSerialize();
        UserApprovalsList.add((UserApprovals)o);
        updateSer();
    }

    @Override
    public boolean hasObject(Object o) {
        UserApprovals check=(UserApprovals) o;
        for (UserApprovals s : UserApprovalsList) {
            if (s.getCurUserName().equals(check.getCurUserName()) && s.getFstString().equals(check.getFstString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeObject(String o) {
        UserApprovalsList.remove(getObject(o));
    }

    @Override
    public void removeObject(UUID o) {

    }

    @Override
    public void updateSer() {
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

                UserApprovalsList = (List<UserApprovals>) in.readObject();
                in.close();
                fileIn.close();
                //System.out.println("deSerialize: "+lendingList);
            }

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    @Override
    public void setList(List<Object> UserApprovalsList) {
        this.UserApprovalsList.clear();
        for(Object a: UserApprovalsList){
            this.UserApprovalsList.add((UserApprovals) a);
        }
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

            out.writeObject(UserApprovalsList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
