package Trade;

import Inventory.Item;
import Main.GateWay;
import Trade.MeetingSystem.Meeting;
import Trade.MeetingSystem.MeetingEditor;
import Trade.MeetingSystem.MeetingStatus;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * read and update trade.txt file (part of job from tradeManager)
 */
public class TradeDataAccess {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void readFile(){
        Trade trade;
        try {
            Inventory.Inventory iv = new Inventory.Inventory();
            BufferedReader reader = new BufferedReader(new FileReader("phase1/src/trade.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] lst = line.split(",");
                UUID tradeId = UUID.fromString(lst[0]);
                UUID user1Id = UUID.fromString(lst[3]);
                UUID user2Id = UUID.fromString(lst[4]);
                int duration = Integer.parseInt(lst[2]);
                ArrayList<UUID> users = new ArrayList<UUID>();
                users.add(user1Id);
                users.add(user2Id);
                LocalDateTime tradeTime = LocalDateTime.parse(lst[10],formatter);
                Inventory.Item item1 = iv.getItem(lst[5]);
                String fstMeeting = lst[7];
                String scdMeeting = lst[8];
                HashMap<UUID, Boolean> idToC = new HashMap<>();
                HashMap<UUID, MeetingEditor> idToE = new HashMap<>();

                if (lst[1].equals("oneway")){
                    trade = new OnewayTrade(user1Id,user2Id,item1,duration,tradeTime);


                }else{
                    Inventory.Item item2 = iv.getItem(lst[6]);
                    trade = new TwowayTrade(user1Id,user2Id,item1,item2,duration,tradeTime);
                }
                if (!fstMeeting.equals("null")) {
                    String[] fm = fstMeeting.split("/");
                    LocalDateTime fmTime = LocalDateTime.parse(fm[0], formatter);
                    trade.setMeeting(fmTime, fm[1], users);
                    //set confirmed status
                    String[] confirmMap = fm[4].split(";");
                    idToC.put(user1Id,Boolean.parseBoolean(confirmMap[0]));
                    idToC.put(user2Id,Boolean.parseBoolean(confirmMap[1]));
                    trade.getMeeting().setConfirmedStatusFull(idToC);
                    //set edition time
                    String[] editTime = fm[3].split(";");
                    MeetingEditor me1 = new MeetingEditor(user1Id);
                    me1.setTimeOfEdition(Integer.parseInt(editTime[0]));
                    MeetingEditor me2 = new MeetingEditor(user2Id);
                    me2.setTimeOfEdition(Integer.parseInt(editTime[1]));
                    idToE.put(user1Id,new MeetingEditor(user1Id));
                    idToE.put(user2Id,new MeetingEditor(user2Id));
                    trade.getMeeting().setIdToEditor(idToE);
                    trade.getMeeting().setStatus(MeetingStatus.valueOf(fm[2]));

                }
                if (!scdMeeting.equals("null")){
                    String[] sm = scdMeeting.split("/");
                    LocalDateTime smTime = LocalDateTime.parse(sm[0],formatter);
                    trade.setSecondMeeting(smTime, sm[1], users);
                    trade.getSecondMeeting().setStatus(MeetingStatus.valueOf(sm[2]));
                }

                trade.setStatus(lst[9]);
                trade.setId(tradeId);
                GateWay.trades.add(trade);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addTradeToFile(Trade trade) {
        try {
            FileOutputStream fos = new FileOutputStream("phase1/src/trade.txt", true);
            UUID id = trade.getId();
            String type;
            type = trade.getType();
            Integer duration = trade.getDuration();
            UUID user1 = trade.getUsers().get(0);
            UUID user2 = trade.getUsers().get(1);
            ArrayList<Item> items = trade.getItemList();
            String item1; String item2;
            if (items.size() == 1){
                item1 = items.get(0).getName();
                item2 = null;
            }else{
                item1 = items.get(0).getName();
                item2 = items.get(1).getName();
            }
            Meeting fm = trade.getMeeting();
            String fmStr = null;
            if (fm != null){
                HashMap<UUID, MeetingEditor> idToE = fm.getIdToEditor();
                String idToEdStr = idToE.get(user1).getTimeOfEdition() + ";"+
                        idToE.get(user2).getTimeOfEdition();
                HashMap<UUID, Boolean> conStatus = fm.getConfirmedStatusFull();
                String idToCoStr = conStatus.get(user1) + ";" + conStatus.get(user2);
                HashMap<UUID, Boolean> agreeStatus = fm.getAgreedStatusFull();
                String idToAgreeStr = agreeStatus.get(user1) + ";" + agreeStatus.get(user2);
                //2020-06-30 11:49/home/incomplete/0;0/false;false
                fmStr = fm.getDateTime().format(formatter)+"/"+fm.getPlace()+"/"+fm.getStatus()
                        +"/"+idToEdStr+"/"+ idToCoStr+"/"+idToAgreeStr;

            }
            Meeting sm = trade.getSecondMeeting();
            String smStr;
            if (sm == null){
                smStr = null;
            }else{
                HashMap<UUID, Boolean> conStatus = sm.getConfirmedStatusFull();
                String idToCoStr = conStatus.get(user1) + ";" + conStatus.get(user2);
                smStr = sm.getDateTime().format(formatter)+"/"+sm.getPlace()+"/"+sm.getStatus()+"/"+idToCoStr;
            }
            String status = trade.getStatus();
            String time = trade.getCreateTime().format(formatter);

            fos.write((id+","+type+","+duration+","+user1+","+user2+","+item1+","+item2+","+fmStr+","
                    +smStr+","+status+","+time+"\n").getBytes());
            fos.close();
        }catch(IOException e){
            System.out.println("cannot edit file");
        }
    }

    public void updateFile() throws IOException {
        File file = new File("phase1/src/trade.txt");
        try {
            if(!file.exists()) {
                boolean result = file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (GateWay.trades.size()>0){
            for (Trade trade: GateWay.trades){
                addTradeToFile(trade);
            }
        }
    }

}
