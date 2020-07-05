import MeetingSystem.MeetingSystem;
import MeetingSystem.Meeting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TradeUI {
    TradeManager tm = new TradeManager();
    ClientUser currUser;
    Trade trade;
    TradeController tc;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    TradePresenter tp;

    public TradeUI(ClientUser currUser, UUID tradeId) throws IOException {
        TradeManager tradeManager = new TradeManager();
        this.currUser = currUser;
        trade = tm.getTrade(tradeId);
        tc = new TradeController(currUser);
        tp = new TradePresenter(currUser, trade);
    }

    //view trade information
    //1st meeting not complete,run meeting system
    //1st meeting complete(temporary), run second meeting system
    //1st meeting complete(permanent), show your trade has completed
    public void run() {
        boolean becomeComplete = false;

        int exit = 0;
        while (exit != 1) {
            while (true) {
                tp.presentTradeInfo();
                System.out.println("type 1 to exit, type anything to continue with current trade");
                try {
                    String line = br.readLine();
                    if (line.equals("1")) {
                        exit = 1;
                        break;
                    } else {
                        if (becomeComplete){
                            tc.completeTrade(trade);
                        }
                        switch (tc.checkTradeMeeting(trade)) {
                            case "complete":
                                System.out.println("the trade has been completed");
                                break;
                            case "confirm trade":
                                confirmTrade();
                                break;
                            case "first meeting" :
                                MeetingSystem mt = new MeetingSystem(trade.getUsers(), true);
                                mt.run(currUser.getId());
                                ArrayList<Object> result = mt.runResult();
                                if (result.get(2) == "complete" && trade.getDuration() == -1) {
                                    becomeComplete = true;
                                }
                                break;
                            case "second meeting":
                                Meeting sm = trade.getSecondMeeting();
                                MeetingSystem smt = new MeetingSystem(trade.getUsers(), false);
                                smt.run(currUser.getId());
                                ArrayList<Object> result2 = smt.runResult();
                                if (result2.get(2) == "complete") {
                                    becomeComplete = true;
                                }
                                break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // true means that the trade has been completed
    /*
    private boolean confirmSecondMeeting(Meeting sm) throws IOException {
        boolean becomeComplete = false;
        while (true) {
            System.out.println("menu:\n 1. confirm meeting\n2. not confirm meeting\n3. exit");
            try {
                String line = br.readLine();
                if (!line.equals("1") && !line.equals("2")) {
                    throw new IOException("Wrong input, please type again.");
                } else {
                    if (line.equals("1")){
                        if (sm.getConfirmedStatuses(currUser.getId())){
                            System.out.println("you have confirmed the second meeting, please wait for another user to confirm");
                        }
                        else{
                            sm.setIdToConfirm(currUser.getId());
                            ArrayList<Boolean> bothTrue = new ArrayList<>(Arrays.asList(true,true));
                            if (!sm.getConfirmedStatusFull().values().equals(bothTrue)){
                                becomeComplete = true;
                                System.out.println("the second meeting has been confirmed, the trade has been completed");
                            }else{
                                System.out.println("you have confirmed the meeting, please wait for another user to confirm");
                            }
                        };
                    }
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error! Please type again!");
            }
        }
        return becomeComplete;
    }
    */

    private void confirmTrade(){
        while (true) {
            System.out.println("type 1 to confirm meeting, type 2 to not confirm");
            try {
                String confirm = br.readLine();
                if (confirm.equals("1")) {
                    tc.confirmTrade(trade);
                    break;
                }
                else if (confirm.equals("2")){
                    break;
                }else{
                    System.out.println("wrong input, please type again");
                }
            } catch (IOException e) {
                System.out.println("wrong input, please type again");
                e.printStackTrace();
            }
        }
    }

}

