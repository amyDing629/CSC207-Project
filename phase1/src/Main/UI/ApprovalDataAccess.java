package Main.UI;
import Main.GateWay;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ApprovalDataAccess {

    public GateWay gw;

    public ApprovalDataAccess(GateWay gw) {
        this.gw = gw;
    }

    public void readItem() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "phase1/src/ItemApproval.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (line.charAt(0) == '1') {
                    String[] parts = line.split("/");
                    ArrayList<String> hi = new ArrayList<>(Arrays.asList(parts));
                    gw.getApprovalItem().add(hi);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readUser(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "phase1/src/ItemApproval.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (line.charAt(0) == '2') {
                    String[] parts = line.split("/");
                    ArrayList<String> hi = new ArrayList<>(Arrays.asList(parts));
                    gw.getApprovalUser().add(hi);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateFile() throws IOException {
        ArrayList<ArrayList<String>> item=gw.getApprovalItem();
        ArrayList<ArrayList<String>> user=gw.getApprovalUser();
        PrintWriter writer = new PrintWriter("phase1/src/ItemApproval.txt");
        writer.print("");
        writer.close();
        for (ArrayList<String> strings : item) {
            String data =strings.get(0)+"/"+strings.get(1)+"/"+strings.get(2)+"/"+strings.get(3)+"\n";
            File file = new File("phase1/src/ItemApproval.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(data);
            br.close();
            fr.close();
        }
        for (ArrayList<String> strings : user) {
            String data =strings.get(0)+"/"+strings.get(1)+"/"+strings.get(2)+"/"+strings.get(3)+"\n";
            File file = new File("phase1/src/ItemApproval.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(data);
            br.close();
            fr.close();
        }
    }
}