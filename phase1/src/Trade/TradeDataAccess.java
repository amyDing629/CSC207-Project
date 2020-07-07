package Trade;

import java.time.format.DateTimeFormatter;

/**
 * read and update trade.txt file (part of job from tradeManager)
 */
public class TradeDataAccess {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
/*
    public void readFile(){
        trade.Trade trade;
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
                    trade = new trade.OnewayTrade(user1Id,user2Id,item1,duration,tradeTime);


                }else{
                    Inventory.Item item2 = iv.getItem(lst[6]);
                    trade = new trade.TwowayTrade(user1Id,user2Id,item1,item2,duration,tradeTime);
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
                    trade.getMeeting().setStatus(fm[2]);

                }
                if (!scdMeeting.equals("null")){
                    String[] sm = scdMeeting.split("/");
                    LocalDateTime smTime = LocalDateTime.parse(sm[0],formatter);
                    trade.setSecondMeeting(smTime, sm[1], users);
                    trade.getSecondMeeting().setStatus(sm[2]);
                }

                trade.setStatus(lst[9]);
                trade.setId(tradeId);
                tradeList.add(trade);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

 */
}
