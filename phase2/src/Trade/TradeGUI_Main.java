package Trade;

import User.ClientUser;
import User.UserManager;


import javax.swing.*;
import java.util.UUID;

public class TradeGUI_Main {
    ClientUser currUser;
    Trade trade;
    TradeController tc;
    TradePresenter tp;


    /**
     * constructor
     * @param currUser the user that is using the system
     * @param tradeId the trade id of the current trade
     */
    public TradeGUI_Main(ClientUser currUser, UUID tradeId, TradeManager tm, UserManager um){
        this.currUser = currUser;
        trade = tm.getTrade(tradeId);
        tc = new TradeController(currUser, trade, tm, um);
        tp = new TradePresenter(currUser, trade);
    }

    public void run(){
        JFrame frame = new JFrame("Select Trade Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);



        
    }








}
