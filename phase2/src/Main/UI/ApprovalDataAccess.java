package Main.UI;
import User.ItemApprovalManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * [gateway class]
 * the class that read and write all the item information from ItemApproval.txt into IApprovalItem and ApprovalUser
 * in gateway
 */
public class ApprovalDataAccess {
    private final ItemApprovalManager iam;


    /**
     * [constructor]
     */
    public ApprovalDataAccess(ItemApprovalManager iam) {
        this.iam = iam;
    }

    /**
     * read all the information from ItemApproval.txt into gateway ApprovalItem list
     */
    public void readItem() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "phase2/src/ItemApproval.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (line.charAt(0) == '1') {
                    String[] parts = line.split("/");
                    ArrayList<String> hi = new ArrayList<>(Arrays.asList(parts));
                    iam.getItemApproval().add(hi);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read all the information from ItemApproval.txt into gateway ApprovalUser list
     */
    public void readUser(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "phase2/src/ItemApproval.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (line.charAt(0) == '2') {
                    String[] parts = line.split("/");
                    ArrayList<String> hi = new ArrayList<>(Arrays.asList(parts));
                    iam.getUserApproval().add(hi);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update all the information from ApprovalItem and ApprovalUser list of gateway into ItemApproval.txt
     */
    public void updateFile() throws IOException {
        ArrayList<ArrayList<String>> item=iam.getItemApproval();
        ArrayList<ArrayList<String>> user=iam.getUserApproval();
        PrintWriter writer = new PrintWriter("phase2/src/ItemApproval.txt");
        writer.print("");
        writer.close();
        for (ArrayList<String> strings : item) {
            String data =strings.get(0)+"/"+strings.get(1)+"/"+strings.get(2)+"/"+strings.get(3)+"\n";
            File file = new File("phase2/src/ItemApproval.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(data);
            br.close();
            fr.close();
        }
        for (ArrayList<String> strings : user) {
            String data =strings.get(0)+"/"+strings.get(1)+"/"+strings.get(2)+"\n";
            File file = new File("phase2/src/ItemApproval.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(data);
            br.close();
            fr.close();
        }
    }
}