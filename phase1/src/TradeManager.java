import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is a use case class TradeManager
 * store Trades in system
 * Allow users to set up trade, cancel trade
 * Automatically update the trade history for both users in the trade.
 */
public class TradeManager {
    private ArrayList<Trade> tradeList;
    private UserManager userManager;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Constructor
     * tradeList stores all the Trade in the system.
     * currentUser is the User who is currently interacting with this TradeManager.
     */
    public TradeManager(){
        tradeList = new ArrayList<>();
        readFile();
    }

    public ArrayList<Trade> getTradeList(){
        return tradeList;
    }


    /**
     * Allow the currentUser to create a one-way trade with input otherUserId, item, and trade duration.
     * Update the trade history for both users
     * @param otherUserId the userId of another User in the particular Trade.
     * @param item the only one Item to be trade in this created Trade.
     * @param duration the duration of this Trade, unit (days). -1 means the Trade is permanent.
     * @return the created newTrade
     */
    public Trade createOnewayTrade(Integer currUserId, Integer otherUserId, Item item, int duration, LocalDateTime time)
            throws IOException {
        OnewayTrade newTrade = new OnewayTrade(currUserId, otherUserId, item, duration, time);
        setId(newTrade);
        tradeList.add(newTrade);
        updateFile();

        // Record this new Trade in system

        // Update trade history for both users
        this.updateTradeHistory(currUserId, otherUserId, newTrade);
        return newTrade;
    }

    /**
     * if the User wants to make a two way trade, tradeManager will create a two way trade.
     * @param otherUserId the userId of another User in the particular Trade
     * @param item1to2 the Item to be trade in this created Trade.
     * @param item2to1 the other Item to be trade in this created Trade.
     * @param duration the duration of this Trade, unit (days). -1 means the Trade is permanent.
     * @return the created newTrade
     */
    public Trade createTwowayTrade(Integer currUserId, Integer otherUserId, Item item1to2, Item item2to1, int duration,
                                   LocalDateTime time) throws IOException {
        TwowayTrade newTrade = new TwowayTrade(currUserId, otherUserId, item1to2, item2to1, duration, time);
        setId(newTrade);
        tradeList.add(newTrade);
        updateFile();
        // Update trade history for both users
        this.updateTradeHistory(currUserId, otherUserId, newTrade);
        return newTrade;
    }


    private void updateTradeHistory(Integer currUserId, Integer otherUserId, Trade newTrade){
        List<Trade> currentTradeHistory = userManager.findTrade(currUserId);
        currentTradeHistory.add(newTrade);
        List<Trade> otherTradeHistory = userManager.findTrade(otherUserId);
        otherTradeHistory.add(newTrade);
    }

    /**
     * set id to a trade when it is created.
     * @param trade
     */
    public void setId(Trade trade){
    }

    public Trade getTrade(int id) {
        for (Trade trade : tradeList) {
            if (trade.getId() == id) {
                return trade;
            }
        }
        return null;
    }

    public void readFile(){
        Trade trade;
        try {
            Inventory iv = new Inventory();
            BufferedReader reader = new BufferedReader(new FileReader("phase1/src/trade.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] lst = line.split(",");
                Integer tradeId = Integer.parseInt(lst[0]);
                Integer user1Id = Integer.parseInt(lst[3]);
                Integer user2Id = Integer.parseInt(lst[4]);
                Integer duration = Integer.parseInt(lst[2]);
                ArrayList<Integer> users = new ArrayList<Integer>();
                users.add(user1Id);
                users.add(user2Id);
                LocalDateTime tradeTime = LocalDateTime.parse(lst[-1],formatter);
                Item item1 = iv.getItem(lst[5]);
                String fstMeeting = lst[7];
                String scdMeeting = lst[8];
                HashMap<Integer, Boolean> idToC = new HashMap<Integer, Boolean>();
                HashMap<Integer, MeetingEditor> idToE = new HashMap<>();

                if (lst[1].equals("oneway")){
                    trade = new OnewayTrade(user1Id,user2Id,item1,duration,tradeTime);


                }else{
                    Item item2 = iv.getItem(lst[6]);
                    trade = new TwowayTrade(user1Id,user2Id,item1,item2,duration,tradeTime);
                }
                if (!fstMeeting.equals("")) {
                    String[] fm = fstMeeting.split("/");
                    LocalDateTime fmTime = LocalDateTime.parse(fm[0], formatter);
                    trade.setMeeting(fmTime, fm[1], users);
                    //set confirmed status
                    String[] confirmMap = fm[4].split(";");
                    idToC.put(user1Id,Boolean.parseBoolean(confirmMap[0]));
                    idToC.put(user2Id,Boolean.parseBoolean(confirmMap[0]));
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
                if (!scdMeeting.equals("")){
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

    public void addTradeToFile(Trade trade) throws IOException{
        try {
            FileOutputStream fos = new FileOutputStream("phase1/src/trade.txt", true);
            Integer id = trade.getId();
            String type;
            String item;
            if (OnewayTrade.class.isInstance(trade)){
                type = "oneway";
            }else{
                type = "twoway";
            }
            Integer duration = trade.getDuration();
            Integer user1 = trade.getUsers().get(0);
            Integer user2 = trade.getUsers().get(1);
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
            HashMap<Integer, MeetingEditor> idToE = fm.getIdToEditor();
            String idToEdStr = idToE.get(user1).getTimeOfEdition() + ";"+
                    idToE.get(user2).getTimeOfEdition();
            HashMap<Integer, Boolean> conStatus = fm.getConfirmedStatusFull();
            String idToCoStr = Boolean.toString(conStatus.get(user1)) + ";" + Boolean.toString(conStatus.get(user2));
            //2020-06-30 11:49/home/incomplete/0;0/false;false
            String fmStr = fm.getDateTime().format(formatter)+"/"+fm.getPlace()+"/"+fm.getStatus()
                    +"/"+idToEdStr+"/"+ idToCoStr;
            Meeting sm = trade.getSecondMeeting();
            String smStr;
            if (sm == null){
                smStr = null;
            }else{
                smStr = sm.getDateTime().format(formatter)+"/"+sm.getPlace()+"/"+sm.getStatus();
            }
            String status = trade.getStatus();
            String time = trade.getCreateTime().format(formatter);

            fos.write((id+","+type+","+duration+","+user1+","+user2+","+item1+","+item2+fmStr+","
                    +smStr+","+status+","+time+"\n").getBytes());
            fos.close();
        }catch(IOException e){
            System.out.println("cannot edit file");

        }
    }


    public void updateFile() throws IOException {
        File file = new File("phase1/src/trade.txt");
        file.delete();
        for (Trade trade: tradeList){
            addTradeToFile(trade);
        }
    }

    /**
     * abstract class
     * make trade happen, move items in User's list
     */
   // public void makeTrade(){

    //};



}
