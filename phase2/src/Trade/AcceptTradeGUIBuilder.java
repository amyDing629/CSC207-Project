package Trade;

import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptTradeGUIBuilder implements TradeGUIBuilder {
    private final TradeGUI tg;
    Trade currTrade;
    ClientUser currUser;
    AcceptTradeController atc;
    Frame tf;

    public AcceptTradeGUIBuilder(ClientUser currUser, TradeManager tm, UserManager um, Frame tf){
        this.currUser = currUser;
        tg = new TradeGUI();
        atc = new AcceptTradeController(currUser, tm, um, tg);
        this.tf = tf;
    }

    @Override
    public void buildFrame(){
        tg.setFrame(800, 400, "Accept Trade Session");

    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        tg.initializeMsg(msgArea);
        tg.setNorth(panelN);

    }

    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton agree = new JButton("agree");
        JButton refuse = new JButton("refuse");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(refuse);
        tg.setEast(panelE);
        agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atc.agreeBut();
            }
        });


    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);
        tg.initializeList(tradeArea);
        tg.setWest(panelW);


    }

    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
        JLabel input = new JLabel("input trade number");
        JTextArea inputArea = new JTextArea("trade number");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return to trade menu");
        JButton update = new JButton("update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setSouth(panelS);
    }

    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);

    }

    public TradeGUI getTradeGUI(){
        return tg;
    }
}
